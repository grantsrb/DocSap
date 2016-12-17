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

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setSpecialties(ArrayList<String> specialties) {
        this.specialties = specialties;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setBetterDoctorRating(String betterDoctorRating) {
        this.betterDoctorRating = betterDoctorRating;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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
