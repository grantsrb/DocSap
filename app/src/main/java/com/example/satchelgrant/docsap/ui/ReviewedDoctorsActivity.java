package com.example.satchelgrant.docsap.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.satchelgrant.docsap.Constants;
import com.example.satchelgrant.docsap.R;
import com.example.satchelgrant.docsap.adapters.FirebaseDoctorViewHolder;
import com.example.satchelgrant.docsap.models.Doctor;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ReviewedDoctorsActivity extends AppCompatActivity {
    private DatabaseReference mDoctorsRef;
    private FirebaseRecyclerAdapter mFirebaseAdapter;
    @Bind(R.id.doctorRecycler) RecyclerView doctorRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        ButterKnife.bind(this);

        mDoctorsRef = FirebaseDatabase.getInstance().getReference(Constants.CHILD_DOCTORS);
        this.setUpFirebaseAdapter();

    }

    public void setUpFirebaseAdapter() {
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Doctor, FirebaseDoctorViewHolder>(Doctor.class, R.layout.doctors_list_item,FirebaseDoctorViewHolder.class,mDoctorsRef) {
            @Override
            protected void populateViewHolder(FirebaseDoctorViewHolder viewHolder, Doctor model, int position) {
                viewHolder.bindDoctor(model);
            }
        };
        doctorRecyclerView.setHasFixedSize(true);
        doctorRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        doctorRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }
}
