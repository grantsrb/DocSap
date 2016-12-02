package com.example.satchelgrant.docsap.models;

import java.util.ArrayList;

/**
 * Created by satchelgrant on 12/1/16.
 */

public class Doctor {
    private String mFirstName;
    private String mLastName;
    private String mAddress;
    private ArrayList<String> mSpecialties;
    private String mSchool;
    private String mTitle;
    private String mImageUrl;
    private String mBio;
    private String mYelpRating;
    private String mBetterDoctorRating;

    public Doctor (String firstName,String lastName,String address,String school,String title,String imageUrl,String bio,String yelpRating,String betterDocRating,ArrayList<String> specialties) {
        mFirstName = firstName;
        mLastName = lastName;
        mAddress = address;
        mSpecialties = specialties;
        mSchool = school;
        mTitle = title;
        mImageUrl = imageUrl;
        mBio = bio;
        mYelpRating = yelpRating;
        mBetterDoctorRating = betterDocRating;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public String getFullName() {
        return mFirstName + " " + mLastName;
    }

    public String getFullNameWithTitle() {
        return mFirstName + " " + mLastName + ", " + mTitle;
    }

    public String getAddress() {
        return mAddress;
    }

    public String getSchool() {
        return mSchool;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public String getBio() {
        return mBio;
    }

    public String getYelpRating() {
        return mYelpRating;
    }

    public String getBetterDoctorRating() {
        return mBetterDoctorRating;
    }

    public ArrayList<String> getSpecialties() {
        return mSpecialties;
    }
}
