package com.example.satchelgrant.docsap.models;

import org.parceler.Parcel;

import java.util.ArrayList;

/**
 * Created by satchelgrant on 12/1/16.
 */

@Parcel
public class Doctor {
    private String mFirstName;
    private String mLastName;
    private String mAddress;
    private ArrayList<String> mSpecialties;
    private String mTitle;
    private String mImageUrl;
    private String mBio;
    private String mBetterDoctorRating;

    public Doctor() {}

    public Doctor (String firstName,String lastName,String address,String title,String imageUrl,String bio,String betterDocRating,ArrayList<String> specialties) {
        mFirstName = firstName;
        mLastName = lastName;
        mAddress = address;
        mSpecialties = specialties;
        mTitle = title;
        mImageUrl = imageUrl;
        mBio = bio;
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


    public String getTitle() {
        return mTitle;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public String getBio() {
        return mBio;
    }


    public String getBetterDoctorRating() {
        return mBetterDoctorRating;
    }

    public ArrayList<String> getSpecialties() {
        return mSpecialties;
    }
}
