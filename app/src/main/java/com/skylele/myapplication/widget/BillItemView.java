package com.skylele.myapplication.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.skylele.myapplication.R;
import com.skylele.myapplication.utils.DisplayUtils;


/**
 * Created by cxg on 2017/7/17.
 */

public class BillItemView extends FrameLayout {

    private int mTextSize; // 字体大小
    private int mLeftColor; // 左边字体颜色
    private int mRightColor; // 右边字体颜色
    private String mLeftText; // 左边字体内容
    private String mRightText; // 右边字体内容
    private TextView mTvLeft;
    private TextView mTvRight;
    private int mLeftSize;
    private int mRightSize;

    public BillItemView(Context context) {
        this(context, null);
    }

    public BillItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BillItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.view_bill_item, this, true);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.BillItemView);
        mTextSize = a.getDimensionPixelSize(R.styleable.BillItemView_biv_contentSize, -1);
        mLeftSize = a.getDimensionPixelSize(R.styleable.BillItemView_biv_leftSize, DisplayUtils.sp2px(context, 20));
        mRightSize = a.getDimensionPixelSize(R.styleable.BillItemView_biv_rightSize, DisplayUtils.sp2px(context, 20));
        mLeftColor = a.getColor(R.styleable.BillItemView_biv_leftColor, Color.LTGRAY);
        mRightColor = a.getColor(R.styleable.BillItemView_biv_rightColor, Color.LTGRAY);
        mLeftText = a.getString(R.styleable.BillItemView_biv_leftText);
        mRightText = a.getString(R.styleable.BillItemView_biv_rightText);

        mTvLeft = (TextView) findViewById(R.id.tv_left);
        mTvRight = (TextView) findViewById(R.id.tv_right);

        // 设置权重
        float rightWeight = a.getFloat(R.styleable.BillItemView_biv_rightWeight, 1f);
        float leftWeight = a.getFloat(R.styleable.BillItemView_biv_leftWeight, 1f);
        // 设置位置
        int leftGravity = a.getInteger(R.styleable.BillItemView_biv_leftGravity, Gravity.LEFT);
        int rightGravity = a.getInteger(R.styleable.BillItemView_biv_rightGravity, Gravity.RIGHT);

        // 这里权重设置，是反着来的，不知道系统对weight做了什么操作
        mTvLeft.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, rightWeight));
        mTvRight.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, leftWeight));
        mTvLeft.setGravity(leftGravity);
        mTvRight.setGravity(rightGravity);


        a.recycle();
        initView();
    }

    private void initView() {


        setTextContent(mTvLeft, getText(mLeftText));
        setTextContent(mTvRight, getText(mRightText));
        setTextColor(mTvLeft, mLeftColor);
        setTextColor(mTvRight, mRightColor);
        setTextSize();
    }

    /**
     * 设置文本内容。
     *
     * @param tv
     * @param content
     */
    private void setTextContent(TextView tv, String content) {
        tv.setText(content);
    }

    /**
     * 动态修改右边文本内容。
     *
     * @param content
     */
    public void setTextContent(String content) {
        mTvRight.setText(content);
    }

    /**
     * 设置字体大小。
     */
    private void setTextSize() {
        if (mTextSize > 0) {
            mTvLeft.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize);
            mTvRight.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize);
        } else {
            mTvLeft.setTextSize(TypedValue.COMPLEX_UNIT_PX, mLeftSize);
            mTvRight.setTextSize(TypedValue.COMPLEX_UNIT_PX, mRightSize);
        }
    }

    /**
     * 设置字体颜色。
     *
     * @param tv
     * @param color
     */
    private void setTextColor(TextView tv, int color) {
        tv.setTextColor(color);
    }

    private String getText(String content) {
        if (content == null || TextUtils.isEmpty(content)) {
            return "";
        }
        return content;
    }
}
