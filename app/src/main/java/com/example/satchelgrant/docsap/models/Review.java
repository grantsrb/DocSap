package com.example.satchelgrant.docsap.models;

import org.parceler.Parcel;

/**
 * Created by satchelgrant on 12/9/16.
 */

@Parcel
public class Review {
    String userId;
    String pushId;
    String review;
    float rating;

    public Review() {}

    public Review(String uid, String review, float rating) {
        this.userId = uid;
        this.review = review;
        this.rating = rating;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getReview() {
        return this.review;
    }

    public float getRating() {
        return this.rating;
    }

    public String getPushId() {
        return this.pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

}
