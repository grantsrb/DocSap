package com.example.satchelgrant.docsap.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class ReviewsFragment extends Fragment {
    @Bind(R.id.submittedSearch)
    TextView mBanner;
    @Bind(R.id.doctorRecycler)
    RecyclerView mRecyclerView;

    private DatabaseReference mDoctorRef;
    private FirebaseReviewListAdapter mAdapter;
    private ItemTouchHelper mItemTouchHelper;

    public ReviewsFragment() {
        // Required empty public constructor
    }

    public ReviewsFragment newInstance()

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reviews, container, false);

        ButterKnife.bind(this, view);

        Intent incomingIntent = getActivity().getIntent();
        Doctor doctor = Parcels.unwrap(incomingIntent.getParcelableExtra("doctor"));
        String docId = doctor.getDoctorId();

        mDoctorRef = FirebaseDatabase.getInstance().getReference(Constants.CHILD_REVIEWS).child(docId);

        mBanner.setText(doctor.getFullNameWithTitle() + " Reviews");

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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_home) {
            Intent intent = new Intent(getActivity(), SplashActivity.class);
            this.startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mAdapter.cleanup();
    }

}
