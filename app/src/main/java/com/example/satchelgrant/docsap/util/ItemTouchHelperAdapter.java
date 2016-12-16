package com.example.satchelgrant.docsap.util;

/**
 * Created by satchelgrant on 12/15/16.
 */

public interface ItemTouchHelperAdapter {
    boolean onItemMove(int fromPosition, int toPosition);
    void onItemDismiss(int position);
}
