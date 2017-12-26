package com.skylele.myapplication.utils;

import android.content.Context;

/**
 * Created by cxg on 2017/7/17.
 */
public class DisplayUtils {

    public static int sp2px(Context context, int spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}
