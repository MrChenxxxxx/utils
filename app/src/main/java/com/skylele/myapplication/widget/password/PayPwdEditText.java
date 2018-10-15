package com.skylele.myapplication.widget.password;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.skylele.myapplication.R;


public class PayPwdEditText extends AbstractPayPwdEditText {
    public PayPwdEditText(Context context) {
        super(context);
    }

    public PayPwdEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PayPwdEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    public View normalView() {
        return LayoutInflater.from(context).inflate(R.layout.view_pay_pwd_view_normal, null, false);
    }

    @Override
    public View checkView() {
        return LayoutInflater.from(context).inflate(R.layout.view_pay_pwd_view_check, null, false);

    }
}
