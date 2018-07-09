package com.skylele.myapplication.widget.popWindow;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.skylele.myapplication.R;

/**
 * Created by cxg on 2018/7/9.
 *
 * @version 1.0.0
 */

public class MyPopupWindow1 extends PopupWindow {
    public MyPopupWindow1(Context context){
        View layout = LayoutInflater.from(context).inflate(R.layout.view_window_popup,null,false);
        setContentView(layout);

        //长宽必须设置，要不然不会显示
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        setFocusable(true);
        setBackgroundDrawable(new BitmapDrawable());
    }
}
