package com.skylele.myapplication.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.skylele.myapplication.R;
import com.skylele.myapplication.utils.DisplayUtils;


/**
 * Created by cxg on 2018/4/17.
 *
 * @version 1.0.0
 */

public class DemoView extends View {
    Paint mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private int mRadius;
    private int mContentColor;
    private int mTitleColor;
    private int mIconColor;
    private int mContentSize;
    private int mTitleSize;
    private int mContentMarginTop;
    private int mRadiusMarginLeft;
    private String mTitle;
    private String mContent;

    public DemoView(Context context) {
        this(context, null);
    }

    public DemoView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DemoView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (attrs != null) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.QuotaManagerItemView);
            mContentColor = ta.getColor(R.styleable.QuotaManagerItemView_qmi_content_color, getResources().getColor(R.color.red_F3304c));
            mTitleColor = ta.getColor(R.styleable.QuotaManagerItemView_qmi_title_color, getResources().getColor(R.color.colorPrimaryDark));
            mIconColor = ta.getColor(R.styleable.QuotaManagerItemView_qmi_icon_color, getResources().getColor(R.color.red_F3304c));
            mContentSize = ta.getInt(R.styleable.QuotaManagerItemView_qmi_content_size, DisplayUtils.sp2px(context, 14));
            mTitleSize = ta.getInt(R.styleable.QuotaManagerItemView_qmi_title_size, DisplayUtils.sp2px(context, 12));
            mRadius = ta.getInt(R.styleable.QuotaManagerItemView_qmi_icon_radius, DisplayUtils.sp2px(context, 4));
            mRadiusMarginLeft = ta.getInt(R.styleable.QuotaManagerItemView_qmi_icon_marginRight, DisplayUtils.sp2px(context, 10));
            mContentMarginTop = ta.getInt(R.styleable.QuotaManagerItemView_qmi_content_marginTop, DisplayUtils.sp2px(context, 3));
            mContent = ta.getString(R.styleable.QuotaManagerItemView_qmi_content);
            mTitle = ta.getString(R.styleable.QuotaManagerItemView_qmi_title);
            ta.recycle();
            mCirclePaint.setColor(mIconColor);
        }

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        mTextPaint.reset();
        //title
        mTextPaint.setColor(mTitleColor);
        mTextPaint.setTextSize(mTitleSize);
        Rect titleBounds = new Rect();
        mTextPaint.getTextBounds(mTitle, 0, mTitle.length(), titleBounds);

        Paint.FontMetricsInt fontMetricsTitle = mTextPaint.getFontMetricsInt();
        int titleBaseline = (titleBounds.height() - fontMetricsTitle.bottom + fontMetricsTitle.top) / 2 - fontMetricsTitle.top;
        canvas.drawText(mTitle, mRadius * 2 + mRadiusMarginLeft, titleBaseline, mTextPaint);
        //icon
        canvas.drawCircle(mRadius, titleBounds.height() / 2, mRadius, mCirclePaint);
        //内容
        mTextPaint.setColor(mContentColor);
        mTextPaint.setTextSize(mContentSize);

        Rect contentBounds = new Rect();
        mTextPaint.getTextBounds(mContent, 0, mTitle.length(), contentBounds);
        Paint.FontMetricsInt fontMetricsContent = mTextPaint.getFontMetricsInt();
        int contentBaseline = (contentBounds.height() - fontMetricsContent.bottom + fontMetricsContent.top) / 2 - fontMetricsContent.top + titleBounds.height() + mContentMarginTop;
        canvas.drawText(mContent, mRadius * 2 + mRadiusMarginLeft, contentBaseline, mTextPaint);
    }

    public DemoView setTitle(String title) {
        this.mTitle = title;
        return this;
    }

    public DemoView setContent(String content) {
        this.mContent = content;
        return this;
    }

    public DemoView setIconColor(int color) {
        this.mIconColor = color;
        return this;
    }

    public void flush() {
        invalidate();
    }

}
