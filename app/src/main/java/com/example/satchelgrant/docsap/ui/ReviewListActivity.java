package com.example.satchelgrant.docsap.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.satchelgrant.docsap.Constants;
import com.example.satchelgrant.docsap.R;
import com.example.satchelgrant.docsap.adapters.FirebaseReviewViewHolder;
import com.example.satchelgrant.docsap.models.Doctor;
import com.example.satchelgrant.docsap.models.Review;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ReviewListActivity extends AppCompatActivity {
    @Bind(R.id.submittedSearch) TextView mBanner;
    @Bind(R.id.doctorRecycler) RecyclerView mRecyclerView;

    private DatabaseReference mDoctorRef;
    private FirebaseRecyclerAdapter mFirebaseRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        ButterKnife.bind(this);

        Intent incomingIntent = getIntent();
        Doctor doctor = Parcels.unwrap(incomingIntent.getParcelableExtra("doctor"));
        String docId = doctor.getDoctorId();

        mDoctorRef = FirebaseDatabase.getInstance().getReference(Constants.CHILD_REVIEWS).child(docId);

        mBanner.setText(doctor.getFullNameWithTitle() + " Reviews");

        this.setUpFirebaseAdapter();
    }

    public void setUpFirebaseAdapter() {
        mFirebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Review, FirebaseReviewViewHolder>(Review.class, R.layout.review_list_item, FirebaseReviewViewHolder.class, mDoctorRef) {
            @Override
            protected void populateViewHolder(FirebaseReviewViewHolder viewHolder, Review model, int position) {
                viewHolder.bindReview(model);
            }
        };
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseRecyclerAdapter);
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
            Intent intent = new Intent(ReviewListActivity.this, SplashActivity.class);
            this.startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
