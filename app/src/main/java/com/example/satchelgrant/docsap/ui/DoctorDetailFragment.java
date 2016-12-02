package com.example.satchelgrant.docsap.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.satchelgrant.docsap.R;
import com.example.satchelgrant.docsap.models.Doctor;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;


public class DoctorDetailFragment extends Fragment {
    @Bind(R.id.doctorImageView) ImageView mDoctorImageView;
    @Bind(R.id.doctorNameTextView) TextView mDoctorNameView;
    @Bind(R.id.ratingTextView) TextView mRatingView;
    @Bind(R.id.specialtyDetailTextView) TextView mSpecialtyView;
    @Bind(R.id.bioTextView) TextView mBioView;
    @Bind(R.id.phoneTextView) TextView mPhoneView;
    @Bind(R.id.addressTextView) TextView mAddressView;
    @Bind(R.id.saveDoctorButton) Button mSaveButton;

    private Doctor mDoctor;

    public static DoctorDetailFragment newInstance(Doctor doctor) {
        DoctorDetailFragment doctorDetailFragment = new DoctorDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("doctor", Parcels.wrap(doctor));
        doctorDetailFragment.setArguments(args);
        return doctorDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDoctor = Parcels.unwrap(getArguments().getParcelable("doctor"));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doctor_detail, container, false);
        ButterKnife.bind(this, view);
        Picasso.with(view.getContext()).load(mDoctor.getImageUrl()).into(mDoctorImageView);

        mDoctorNameView.setText(mDoctor.getFullNameWithTitle());
        mRatingView.setText(mDoctor.getBetterDoctorRating());
        mSpecialtyView.setText(android.text.TextUtils.join(", ", mDoctor.getSpecialties()));
        mBioView.setText(mDoctor.getBio());
        mPhoneView.setText(mDoctor.getPhoneNumber());
        mAddressView.setText(mDoctor.getAddress());
        return view;
    }
}
