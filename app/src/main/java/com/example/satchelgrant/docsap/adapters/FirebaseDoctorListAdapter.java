package com.example.satchelgrant.docsap.adapters;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;

import com.example.satchelgrant.docsap.models.Doctor;
import com.example.satchelgrant.docsap.util.ItemTouchHelperAdapter;
import com.example.satchelgrant.docsap.util.OnStartDragListener;
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
    protected void populateViewHolder(final FirebaseDoctorViewHolder viewHolder, Doctor model, int position) {
        viewHolder.bindDoctor(model);
        viewHolder.mImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (MotionEventCompat.getActionMasked(motionEvent) == MotionEvent.ACTION_DOWN) {
                    mStartDragListener.onStartDrag(viewHolder);
                }
                return false;
            }
        });
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        notifyItemMoved(fromPosition, toPosition); // Could make permanent changes here but it would make no sense
        return false;
    }

    @Override
    public void onItemDismiss(int position) {
//        getRef(position).removeValue();  // Could remove a value here, but it makes no sense to
    }
}
