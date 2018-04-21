package com.teamcreative.totassignment.util;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class ItemDecorationUtil extends RecyclerView.ItemDecoration {

    private int mHalfSpace;

    public ItemDecorationUtil(int space) {
        this.mHalfSpace = space / 2;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (parent.getPaddingLeft() != mHalfSpace) {
            parent.setPadding(mHalfSpace, mHalfSpace, mHalfSpace, mHalfSpace);
            parent.setClipToPadding(false);
        }

        outRect.top = mHalfSpace;
        outRect.right = mHalfSpace;
        outRect.bottom = mHalfSpace;
        outRect.left = mHalfSpace;
    }
}
