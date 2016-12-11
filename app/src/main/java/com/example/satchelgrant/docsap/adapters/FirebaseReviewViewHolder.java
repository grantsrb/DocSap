package com.example.satchelgrant.docsap.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.satchelgrant.docsap.R;
import com.example.satchelgrant.docsap.models.Review;

/**
 * Created by satchelgrant on 12/10/16.
 */

public class FirebaseReviewViewHolder extends RecyclerView.ViewHolder {

    private Context mContext;
    private View mView;

    public FirebaseReviewViewHolder(View itemView) {
        super(itemView);
        mContext = itemView.getContext();
        mView = itemView;
    }

    public void bindReview(Review review) {
        TextView content = (TextView) mView.findViewById(R.id.reviewContentDisplay);
        RatingBar ratingBar = (RatingBar) mView.findViewById(R.id.reviewRatingBar);

        content.setText(review.getReview());
        ratingBar.setRating(review.getRating());

    }

}
