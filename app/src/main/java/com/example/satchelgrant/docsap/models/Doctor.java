package com.example.satchelgrant.docsap.models;

import org.parceler.Parcel;

import java.util.ArrayList;

/**
 * Created by satchelgrant on 12/1/16.
 */

@Parcel
public class Doctor {
    String firstName;
    String lastName;
    String doctorId;
    String address;
    ArrayList<String> specialties;
    String title;
    String imageUrl;
    String bio;
    String betterDoctorRating;
    String phoneNumber;
    String pushId;

    public Doctor() {}

    public Doctor (String uid, String firstName,String lastName,String address,String title,String imageUrl,String bio,String betterDocRating,String phoneNum,ArrayList<String> specialties) {
        this.doctorId = uid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.specialties = specialties;
        this.title = title;
        this.imageUrl = imageUrl;
        this.bio = bio;
        this.betterDoctorRating = betterDocRating;
        this.phoneNumber = phoneNum;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getFullNameWithTitle() {
        return firstName + " " + lastName + ", " + title;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getBio() {
        return bio;
    }


    public String getBetterDoctorRating() {
        return betterDoctorRating;
    }

    public ArrayList<String> getSpecialties() {
        return specialties;
    }

    public String getDoctorId() {
        return this.doctorId;
    }

    public String getPushId() {
        return this.pushId;
    }

    public void setPushId(String id) {
        this.pushId = id;
    }
}
