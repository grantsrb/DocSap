package com.example.satchelgrant.docsap.ui;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

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
}
