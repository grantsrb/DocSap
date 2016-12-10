package com.example.satchelgrant.docsap.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by satchelgrant on 12/9/16.
 */

public class FirebaseReviewViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    View mView;
    Context mContext;

    public FirebaseReviewViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        mView.setOnClickListener(this);
    }
}
