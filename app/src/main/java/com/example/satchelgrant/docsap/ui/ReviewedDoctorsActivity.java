package com.example.satchelgrant.docsap.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.satchelgrant.docsap.Constants;
import com.example.satchelgrant.docsap.R;
import com.example.satchelgrant.docsap.adapters.FirebaseDoctorListAdapter;
import com.example.satchelgrant.docsap.adapters.FirebaseDoctorViewHolder;
import com.example.satchelgrant.docsap.models.Doctor;
import com.example.satchelgrant.docsap.util.OnStartDragListener;
import com.example.satchelgrant.docsap.util.TouchHelperCallback;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ReviewedDoctorsActivity extends AppCompatActivity implements OnStartDragListener {
    private DatabaseReference mDoctorsRef;
    private FirebaseDoctorListAdapter mFirebaseAdapter;
    private ItemTouchHelper mItemTouchHelper;
    @Bind(R.id.doctorRecycler) RecyclerView doctorRecyclerView;
    @Bind(R.id.submittedSearch) TextView mBanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        ButterKnife.bind(this);
        mBanner.setVisibility(View.GONE);
        mDoctorsRef = FirebaseDatabase.getInstance().getReference(Constants.CHILD_DOCTORS);
        this.setUpFirebaseAdapter();

    }

    public void setUpFirebaseAdapter() {
        mFirebaseAdapter = new FirebaseDoctorListAdapter(Doctor.class, R.layout.doctor_list_item_drag,
                FirebaseDoctorViewHolder.class, mDoctorsRef, this, this);

        doctorRecyclerView.setHasFixedSize(true);
        doctorRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        doctorRecyclerView.setAdapter(mFirebaseAdapter);

        ItemTouchHelper.Callback callback = new TouchHelperCallback(mFirebaseAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(doctorRecyclerView);
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
        int id = menuItem.getItemId();
        if(id == R.id.home_search) {
            Intent intent = new Intent(ReviewedDoctorsActivity.this, SplashActivity.class);
            this.startActivity(intent);
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }
}
