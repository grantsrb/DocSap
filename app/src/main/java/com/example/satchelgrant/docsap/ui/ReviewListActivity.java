package com.example.satchelgrant.docsap.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.satchelgrant.docsap.R;
import com.example.satchelgrant.docsap.models.Doctor;

import org.parceler.Parcels;

public class ReviewListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);

        Intent incomingIntent = getIntent();
        Doctor doctor = Parcels.unwrap(incomingIntent.getParcelableExtra("doctor"));
        String docId = doctor.getDoctorId();

        ReviewsFragment detailFragment = ReviewsFragment.newInstance(doctor);
        FragmentTransaction ft = ((FragmentActivity) this).getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.reviewFragmentContainer, detailFragment);
        ft.commit();
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
