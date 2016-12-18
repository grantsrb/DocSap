package com.example.satchelgrant.docsap.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.satchelgrant.docsap.R;
import com.example.satchelgrant.docsap.models.Review;
import com.example.satchelgrant.docsap.util.ItemTouchHelperViewHolder;

/**
 * Created by satchelgrant on 12/10/16.
 */

public class FirebaseReviewViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {

    private Context mContext;
    private View mView;
    public RatingBar mRatingBar;

    public FirebaseReviewViewHolder(View itemView) {
        super(itemView);
        mContext = itemView.getContext();
        mView = itemView;
    }

    public void bindReview(Review review) {
        TextView content = (TextView) mView.findViewById(R.id.reviewContentDisplay);
        mRatingBar = (RatingBar) mView.findViewById(R.id.reviewRatingBar);

        content.setText(review.getReview());
        mRatingBar.setRating(review.getRating());

    }

    @Override
    public void onItemSelected() {
        itemView.animate()
                .alpha(0.7f)
                .scaleX(0.9f)
                .scaleY(0.9f)
                .setDuration(500);
    }

    @Override
    public void onItemClear() {
        itemView.animate()
                .alpha(1f)
                .scaleX(1f)
                .scaleY(1f);
    }
}
