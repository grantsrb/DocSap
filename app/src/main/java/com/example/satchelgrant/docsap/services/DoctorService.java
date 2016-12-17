package com.example.satchelgrant.docsap.services;

import com.example.satchelgrant.docsap.Constants;
import com.example.satchelgrant.docsap.models.Doctor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by satchelgrant on 12/1/16.
 */

public class DoctorService {
    public static void getDoctors(String query, String name, String specialty, Callback callback){
        OkHttpClient client = new OkHttpClient();

        // Constructs URL for request
        HttpUrl.Builder urlBuild = HttpUrl.parse(Constants.REQUEST_URL_BASE).newBuilder();

        //Validation for api call
        if(!Pattern.matches(".*\\W.*|[a-zA-Z]{0}", query) && !Pattern.matches(".*\\W.*|[a-zA-Z]{0}", specialty)) {
            urlBuild.addQueryParameter("query", query);
            urlBuild.addQueryParameter("specialty_uid", specialty);
        } else if(!Pattern.matches(".*\\W.*|[a-zA-Z]{0}", query)) {
            urlBuild.addQueryParameter("query", query);
        } else if(!Pattern.matches(".*\\W.*|[a-zA-Z]{0}", specialty)) {
            urlBuild.addQueryParameter("query", specialty);
        }
        if(!Pattern.matches(".*\\W.*|[a-zA-Z]{0}", name)) { // More validation
            urlBuild.addQueryParameter("name", name);
        }

        urlBuild.addQueryParameter("user_location", "45.5231,-122.6765");
        urlBuild.addQueryParameter("skip", "0");
        urlBuild.addQueryParameter("limit", "10");
        urlBuild.addQueryParameter("user_key", Constants.DOCTOR_KEY);
        String url = urlBuild.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Doctor> processResponse(Response response) {
        ArrayList<Doctor> doctors = new ArrayList<>();

        try {
            if(response.isSuccessful()) {
                String jsonData = response.body().string();
                JSONObject results = new JSONObject(jsonData);
                JSONArray doctorsResults = results.getJSONArray("data");
                String address = "No Portland Address Provided";
                String phoneNum = "No Phone Provided";
                String uid;
                for(int i = 0; i < doctorsResults.length(); i++) {
                    JSONObject doctor = doctorsResults.getJSONObject(i);
                    uid = doctor.getString("uid");
                    if(doctor.has("practices")) {
                        JSONArray practices = doctor.getJSONArray("practices");
                        for (int j = 0; j < practices.length(); j++) {
                            JSONObject addressJson = practices.getJSONObject(j).getJSONObject("visit_address");
                            if (addressJson.getString("city").equals("Portland")) {
                                address = addressJson.getString("street");
                                if (!addressJson.isNull("street2")) {
                                    String street2 = addressJson.getString("street2");
                                    address = address + ", " + street2;
                                }
                                address = address + "\n" + addressJson.getString("city") + ", " +
                                        addressJson.getString("state") + " " + addressJson.getString("zip");
                                JSONArray phones = practices.getJSONObject(j).getJSONArray("phones");
                                for (int k = 0; k < phones.length(); k++) {
                                    if (phones.getJSONObject(k).getString("type").equals("landline")) {
                                        phoneNum = phones.getJSONObject(k).getString("number");
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                    }

                    JSONObject profile = doctor.getJSONObject("profile");
                    String firstName = profile.getString("first_name");
                    String lastName = profile.getString("last_name");
                    String title = "";
                    if(profile.has("title"))
                        title = profile.getString("title");
                    String imageUrl = profile.getString("image_url");
                    String bio = profile.getString("bio");
                    ArrayList<String> specialties = new ArrayList<>();
                    JSONArray specialtiesResults = new JSONArray();
                    if(doctor.has("specialties"))
                        specialtiesResults = doctor.getJSONArray("specialties");
                    for(int j = 0; j < specialtiesResults.length(); j++) {
                        specialties.add(specialtiesResults.getJSONObject(j).getString("name"));
                    }
                    JSONArray ratings = doctor.getJSONArray("ratings");
                    String betterDocRating = "None";
                    for(int j = 0; j < ratings.length(); j++) {
                        JSONObject rating = ratings.getJSONObject(j);
                        if(rating.getString("provider").equals("betterdoctor")) {
                            betterDocRating = rating.getString("rating");
                            break;
                        }
                    }
                    Doctor newDoctor = new Doctor(uid, firstName,lastName,address,title,imageUrl,bio,betterDocRating,phoneNum,specialties);
                    doctors.add(newDoctor);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return doctors;
    }
}
