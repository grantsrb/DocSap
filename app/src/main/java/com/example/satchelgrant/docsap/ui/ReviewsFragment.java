package com.example.satchelgrant.docsap.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.satchelgrant.docsap.Constants;
import com.example.satchelgrant.docsap.R;
import com.example.satchelgrant.docsap.adapters.FirebaseReviewListAdapter;
import com.example.satchelgrant.docsap.adapters.FirebaseReviewViewHolder;
import com.example.satchelgrant.docsap.models.Doctor;
import com.example.satchelgrant.docsap.models.Review;
import com.example.satchelgrant.docsap.util.TouchHelperCallback;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;


public class ReviewsFragment extends Fragment {
    @Bind(R.id.reviewBanner) TextView mBanner;
    @Bind(R.id.reviewRecycler) RecyclerView mRecyclerView;

    private DatabaseReference mDoctorRef;
    private FirebaseReviewListAdapter mAdapter;
    private ItemTouchHelper mItemTouchHelper;
    private Doctor mDoctor;

    public ReviewsFragment() {
        // Required empty public constructor
    }

    public static ReviewsFragment newInstance(Doctor doctor) {
        ReviewsFragment newFragment = new ReviewsFragment();
        Bundle args = new Bundle();
        args.putParcelable(Constants.DOCTOR_KEY, Parcels.wrap(doctor));
        newFragment.setArguments(args);
        return newFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDoctor = Parcels.unwrap(getArguments().getParcelable(Constants.DOCTOR_KEY));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reviews, container, false);
        ButterKnife.bind(this, view);

        mDoctorRef = FirebaseDatabase.getInstance().getReference(Constants.CHILD_REVIEWS).child(mDoctor.getDoctorId());
        mBanner.setText(mDoctor.getFullNameWithTitle() + " Reviews");

        this.setUpFirebaseAdapter();
        return view;
    }

    public void setUpFirebaseAdapter() {
        mAdapter = new FirebaseReviewListAdapter(Review.class, R.layout.review_list_item,
                FirebaseReviewViewHolder.class, mDoctorRef, getActivity());

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);

        ItemTouchHelper.Callback callback = new TouchHelperCallback(mAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mAdapter.cleanup();
    }

}
