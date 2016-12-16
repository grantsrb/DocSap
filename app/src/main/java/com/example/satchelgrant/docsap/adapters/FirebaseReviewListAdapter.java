package com.example.satchelgrant.docsap.adapters;

import android.content.Context;

import com.example.satchelgrant.docsap.models.Review;
import com.example.satchelgrant.docsap.util.ItemTouchHelperAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

/**
 * Created by satchelgrant on 12/16/16.
 */

public class FirebaseReviewListAdapter extends FirebaseRecyclerAdapter<Review, FirebaseReviewViewHolder> implements ItemTouchHelperAdapter {
    private DatabaseReference mRef;
    private Context mContext;

    public FirebaseReviewListAdapter(Class<Review> modelClass, int modelLayout, Class<FirebaseReviewViewHolder> viewHolderClass,
                                     Query ref, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        mRef = ref.getRef();
        mContext = context;
    }

    @Override
    protected void populateViewHolder(final FirebaseReviewViewHolder viewHolder, Review model, int position) {
        viewHolder.bindReview(model);
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
