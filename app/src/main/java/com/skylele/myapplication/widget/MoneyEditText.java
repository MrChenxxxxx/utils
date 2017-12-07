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

        @Override
        public CharSequence filter(CharSequence source, int start, int end,
                                   Spanned dest, int dstart, int dend) {
            //判断小数点是否在第一位
            if (source.toString().equals(".") && dstart == 0 && dend == 0) {
                //给小数点前面加0
                MoneyEditText.this.setText(0 + "" + source + dest);
                //设置光标
                MoneyEditText.this.setSelection(2);
            }
            //判断小数点是否存在并且小数点后面是否已有两个字符
            if (dest.toString().indexOf(".") != -1 && (dest.length() - dest.toString().indexOf(".")) > 2) {
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
