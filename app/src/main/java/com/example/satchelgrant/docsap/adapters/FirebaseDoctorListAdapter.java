package com.example.satchelgrant.docsap.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;

import com.example.satchelgrant.docsap.R;
import com.example.satchelgrant.docsap.models.Doctor;
import com.example.satchelgrant.docsap.ui.ReviewListActivity;
import com.example.satchelgrant.docsap.ui.ReviewsFragment;
import com.example.satchelgrant.docsap.util.ItemTouchHelperAdapter;
import com.example.satchelgrant.docsap.util.OnStartDragListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import org.parceler.Parcels;

import java.util.ArrayList;

/**
 * Created by satchelgrant on 12/15/16.
 */

public class FirebaseDoctorListAdapter extends FirebaseRecyclerAdapter<Doctor, FirebaseDoctorViewHolder> implements ItemTouchHelperAdapter {
    private DatabaseReference mRef;
    private OnStartDragListener mStartDragListener;
    private Context mContext;
    private ChildEventListener mChildEventListener;
    private ArrayList<Doctor> mDoctors = new ArrayList<>();
    private int mOrientation;

    public FirebaseDoctorListAdapter(Class<Doctor> modelClass, int modelLayout, Class<FirebaseDoctorViewHolder> viewHolderClass,
                                     Query ref, OnStartDragListener startDragListener, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        mRef = ref.getRef();
        mContext = context;
        mStartDragListener = startDragListener;

        mChildEventListener = mRef.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                mDoctors.add(dataSnapshot.getValue(Doctor.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void populateViewHolder(final FirebaseDoctorViewHolder viewHolder, Doctor model, int position) {
        viewHolder.bindDoctor(model);
        mOrientation = viewHolder.itemView.getResources().getConfiguration().orientation;
        if (mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            createReviewList(0);
        }
        viewHolder.mImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (MotionEventCompat.getActionMasked(motionEvent) == MotionEvent.ACTION_DOWN) {
                    mStartDragListener.onStartDrag(viewHolder);
                }
                return false;
            }
        });

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int itemPosition = viewHolder.getAdapterPosition();
                Doctor doctor = mDoctors.get(itemPosition);
                if (mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                    createReviewList(itemPosition);
                } else {
                    Intent intent = new Intent(mContext, ReviewListActivity.class);
                    intent.putExtra("doctorId", doctor.getDoctorId());
                    intent.putExtra("doctor", Parcels.wrap(doctor));
                    mContext.startActivity(intent);
                }
            }
        });
    }

    public void createReviewList(int position) {
        Doctor doctor = mDoctors.get(position);
        ReviewsFragment reviewsFragment = ReviewsFragment.newInstance(doctor);
        FragmentTransaction ft = ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.detailContainer, reviewsFragment);
        ft.commit();
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        notifyItemMoved(fromPosition, toPosition);
        return false;
    }

    @Override
    public void onItemDismiss(int position) {

    }
}
