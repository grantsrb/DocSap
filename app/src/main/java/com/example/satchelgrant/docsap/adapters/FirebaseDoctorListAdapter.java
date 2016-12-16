package com.example.satchelgrant.docsap.adapters;

import android.content.Context;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.example.satchelgrant.docsap.models.Doctor;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

/**
 * Created by satchelgrant on 12/15/16.
 */

public class FirebaseDoctorListAdapter extends FirebaseRecyclerAdapter<Doctor, FirebaseDoctorViewHolder> implements ItemTouchHelperAdapter {
    private DatabaseReference mRef;
    private OnStartDragListener mStartDragListener;
    private Context mContext;

    public FirebaseDoctorListAdapter(Class<Doctor> modelClass, int modelLayout, Class<FirebaseDoctorViewHolder> viewHolderClass,
                                     Query ref, OnStartDragListener startDragListener, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        mRef = ref.getRef();
        mContext = context;
        mStartDragListener = startDragListener;
    }

    @Override
    protected void populateViewHolder(FirebaseDoctorViewHolder viewHolder, Doctor model, int position) {
        viewHolder.bindDoctor(model);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        return false;
    }

    @Override
    public void onItemDismiss(int position) {

    }
}
