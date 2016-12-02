package com.example.satchelgrant.docsap.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.satchelgrant.docsap.models.Doctor;
import com.example.satchelgrant.docsap.ui.DoctorDetailFragment;

import java.util.ArrayList;

/**
 * Created by satchelgrant on 12/2/16.
 */

public class DoctorPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Doctor> mDoctors;

    public DoctorPagerAdapter(FragmentManager fm, ArrayList<Doctor> doctors) {
        super(fm);
        mDoctors = doctors;
    }

    @Override
    public Fragment getItem(int position) {
        return DoctorDetailFragment.newInstance(mDoctors.get(position));
    }

    @Override
    public int getCount() {
        return mDoctors.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mDoctors.get(position).getFullNameWithTitle();
    }
}
