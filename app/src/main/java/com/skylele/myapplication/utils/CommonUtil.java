package com.skylele.myapplication.utils;

import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.TextAppearanceSpan;
import android.widget.TextView;

/**
 * Created by cxg on 2017/12/12.
 */

public class CommonUtil {
    /**
     * 可以改变字体颜色、大小 通过配置style
     *
     * @param context
     * @param textView 要改变的对象
     * @param start    开始位置
     * @param end      结束位置
     * @param style    修改的文字的样式，可以把样式配置在style.xml 文件中
     */
    public static void changeTextStyle(Context context, TextView textView, int start, int end, int style) {
        try {
            SpannableString styledText = new SpannableString(textView.getText());
            TextAppearanceSpan appearanceSpan = new TextAppearanceSpan(context, style);
            styledText.setSpan(appearanceSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            textView.setText(styledText, TextView.BufferType.SPANNABLE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
