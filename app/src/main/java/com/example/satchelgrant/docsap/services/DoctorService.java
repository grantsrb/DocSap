package com.example.satchelgrant.docsap.services;

import com.example.satchelgrant.docsap.Constants;
import com.example.satchelgrant.docsap.models.Doctor;

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
        if(!Pattern.matches(".*\\W.*|[a-zA-Z]{0}", query)) { //Validation
            urlBuild.addQueryParameter("query", query);
        }
        if(!Pattern.matches(".*\\W.*|[a-zA-Z]{0}", name)) { //Validation
            urlBuild.addQueryParameter("name", name);
        }
        if(!Pattern.matches(".*\\W.*|[a-zA-Z]{0}", specialty)) { //Validation
            urlBuild.addQueryParameter("specialty_uid", specialty);
        }
        urlBuild.addQueryParameter("user_location", "45.5231,122.6765");
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
        return null;
    }
}
