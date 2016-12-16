package com.example.satchelgrant.docsap.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.satchelgrant.docsap.R;
import com.example.satchelgrant.docsap.models.Doctor;
import com.example.satchelgrant.docsap.ui.ReviewListActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

/**
 * Created by satchelgrant on 12/9/16.
 */

public class FirebaseDoctorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;

    View mView;
    Context mContext;
    Doctor mDoctor;
    public ImageView mImageView;

    public FirebaseDoctorViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        mView.setOnClickListener(this);
    }

    public void bindDoctor(Doctor doctor) {
        mDoctor = doctor;
        ImageView mImageView = (ImageView) mView.findViewById(R.id.doctorImageHolder);
        TextView doctorNameView = (TextView) mView.findViewById(R.id.doctorNameHolder);
        TextView doctorSpecialtyView = (TextView) mView.findViewById(R.id.speciatiesTextView);
        TextView doctorRatingView = (TextView) mView.findViewById(R.id.ratingTextView);

        doctorNameView.setText(doctor.getFullNameWithTitle());
        doctorSpecialtyView.setText(android.text.TextUtils.join(", ", doctor.getSpecialties()));
        doctorRatingView.setText(doctor.getBetterDoctorRating());
        Picasso.with(mContext)
                .load(doctor.getImageUrl())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(mImageView);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(mContext, ReviewListActivity.class);
        intent.putExtra("doctorId", mDoctor.getDoctorId());
        intent.putExtra("doctor", Parcels.wrap(mDoctor));
        mContext.startActivity(intent);
    }
}
