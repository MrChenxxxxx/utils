package com.skylele.myapplication.widget.password;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.skylele.myapplication.R;

public abstract class AbstractPayPwdEditText extends RelativeLayout {

    private EditText mPwdEt;
    private LinearLayout mPwLl;
    protected Context context;

    private int count = 6;

    public AbstractPayPwdEditText(Context context) {
        this(context, null);
    }

    public AbstractPayPwdEditText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AbstractPayPwdEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.edittext_pay_pwd, this, true);
        mPwdEt = findViewById(R.id.pay_pwd_et);
        mPwLl = findViewById(R.id.pay_pwd_ll);

        mPwdEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mPwdEt.setSelection(charSequence.length());
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String string = editable.toString();
                int countTemp = string.length();
                if (countTemp > count) {
                    countTemp = count;
                    mPwdEt.setText(string.substring(0, count));
                    mPwdEt.setSelection(count);
                }

                for (int i = 0; i < count; i++) {
                    if (countTemp <= i) {
                        ((FrameLayout) mPwLl.getChildAt(i)).getChildAt(0).setVisibility(VISIBLE);
                        ((FrameLayout) mPwLl.getChildAt(i)).getChildAt(1).setVisibility(GONE);

                    } else {
                        ((FrameLayout) mPwLl.getChildAt(i)).getChildAt(0).setVisibility(GONE);
                        ((FrameLayout) mPwLl.getChildAt(i)).getChildAt(1).setVisibility(VISIBLE);
                        if (listerner!=null){
                            listerner.onSelectListern( ((FrameLayout) mPwLl.getChildAt(i)).getChildAt(1),string.substring(i,i+1),i);
                        }
                    }
                }
            }
        });

        for (int i = 0; i < count; i++) {
            FrameLayout view = (FrameLayout) LayoutInflater.from(context).inflate(R.layout.view_pay_pwd_view, null, false);
            mPwLl.addView(view);
            MarginLayoutParams margin = new MarginLayoutParams(view.getLayoutParams());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(margin);
            layoutParams.width = 0;
            layoutParams.weight = 1;
            view.setLayoutParams(layoutParams);
            View noramlView = normalView();
            View checkView = checkView();
            if (noramlView != null) {
                view.addView(noramlView);
            }
            if (checkView != null) {
                checkView.setVisibility(GONE);
                view.addView(checkView);
            }
        }
    }

    public String getText() {
        return mPwdEt.getText().toString();
    }

    public abstract View normalView();

    public abstract View checkView();

    private View getDefaultNoramlView(Context context) {
        return LayoutInflater.from(context).inflate(R.layout.view_pay_pwd_view_normal, null, false);
    }

    ;

    private View getDefaultCheckView(Context context) {
        return LayoutInflater.from(context).inflate(R.layout.view_pay_pwd_view_check, null, false);
    }

    ;

    private IPayPwdListerner listerner;

    public void setListerner(IPayPwdListerner listerner) {
        this.listerner = listerner;
    }
}
