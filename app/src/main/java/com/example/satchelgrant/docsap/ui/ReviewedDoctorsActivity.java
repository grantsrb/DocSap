package com.example.satchelgrant.docsap.ui;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        ButterKnife.bind(this);

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("Search Bar", "No functionality yet!");
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        return super.onOptionsItemSelected(menuItem);
    }
}
