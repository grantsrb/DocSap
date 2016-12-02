package com.example.satchelgrant.docsap;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.satchelgrant.docsap.models.Doctor;

import java.util.ArrayList;

/**
 * Created by satchelgrant on 12/2/16.
 */

public class DoctorRecListAdapter extends RecyclerView.Adapter<DoctorRecListAdapter.DoctorViewHolder> {
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;
    private ArrayList<Doctor> mDoctors;
    private Context mContext;

    public DoctorRecListAdapter(Context context, ArrayList<Doctor> doctors) {
        mContext = context;
        mDoctors = doctors;
    }

    private class DoctorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    }

}
