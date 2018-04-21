package com.teamcreative.totassignment.util;

import android.content.Context;
import android.util.TypedValue;

public class ViewUtil {
    public static int dpToPx(Context context, int dp) {
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, ((float) dp), context.getResources().getDisplayMetrics()));
    }
}
