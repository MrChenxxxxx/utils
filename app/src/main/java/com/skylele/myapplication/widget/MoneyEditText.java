package com.skylele.myapplication.widget;

import android.content.Context;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by cxg on 2017/12/7.
 */

public class MoneyEditText extends EditText {
    private static final String TAG = "MoneyEditText";
    //小数点后的位数
    private static final int POINTER_LENGTH = 2;
    private static final String POINTER = ".";

    public MoneyEditText(Context context) {
        super(context, null);
    }

    public MoneyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private void init() {
        setFilters(new InputFilter[]{new InputMoney()});
    }

    /**
     * 限制只能输入金额
     *
     * @author zengchao
     */
    private class InputMoney implements InputFilter {
        /**
         * @param source    新输入的字符串
         * @param start     新输入的字符串起始下标，一般为0
         * @param end       新输入的字符串终点下标，一般为source长度-1
         * @param dest      输入之前文本框内容
         * @param dstart    原内容起始坐标，一般为0
         * @param dend      原内容终点坐标，一般为dest长度-1
         * @return          输入内容
         */
        @Override
        public CharSequence filter(CharSequence source, int start, int end,
                                   Spanned dest, int dstart, int dend) {
            //判断小数点是否在第一位
            if (source.toString().equals(POINTER)) {
                if (dstart == 0 && dend == 0) {
                    //给小数点前面加0
                    MoneyEditText.this.setText(0 + "" + source + dest);
                    //设置光标
                    MoneyEditText.this.setSelection(2);
                }
                //小数点后的位数不能大于 POINTER_LENGTH
                if (dest.length() - POINTER_LENGTH > dstart) {
                    return "";
                }
            }

            //判断小数点是否存在并且小数点后面是否已有两个字符
            if (dest.toString().indexOf(POINTER) != -1 && (dest.length() - dest.toString().indexOf(POINTER)) > 2) {
                //判断现在输入的字符是不是在小数点后面
                if ((dest.length() - dstart) < 3) {
                    //过滤当前输入的字符
                    return "";
                }
            }
            return null;
        }
    }
}
