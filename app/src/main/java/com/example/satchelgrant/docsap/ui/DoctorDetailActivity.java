package com.example.satchelgrant.docsap.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.satchelgrant.docsap.R;
import com.example.satchelgrant.docsap.adapters.DoctorPagerAdapter;
import com.example.satchelgrant.docsap.models.Doctor;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DoctorDetailActivity extends AppCompatActivity {
    @Bind(R.id.viewPager) ViewPager mViewPager;
    private DoctorPagerAdapter mPagerAdapter;
    ArrayList<Doctor> mDoctors = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_detail);
        ButterKnife.bind(this);

        mDoctors = Parcels.unwrap(getIntent().getParcelableExtra("doctors"));
        int startingPosition = getIntent().getIntExtra("position", 0);

        mPagerAdapter = new DoctorPagerAdapter(getSupportFragmentManager(), mDoctors);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setCurrentItem(startingPosition);
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
            Intent intent = new Intent(DoctorDetailActivity.this, SplashActivity.class);
            this.startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
