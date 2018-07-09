package com.skylele.myapplication.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import com.skylele.myapplication.R;
import com.skylele.myapplication.widget.viewpage.DepthPageTransformer;
import com.skylele.myapplication.widget.viewpage.RotateDownPageTransformer;
import com.skylele.myapplication.widget.viewpage.ZoomOutPageTransformer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cxg on 2018/4/25.
 *
 * @version 1.0.0
 */

public class WidgetUseActivity extends Activity {

    private ViewPager mViewPager;
    private int[] mImgIds = new int[]{R.drawable.guide_image1,
            R.drawable.guide_image2, R.drawable.guide_image3};
    private List<ImageView> mImageViews = new ArrayList<ImageView>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_widget_use);
        findViewById(R.id.btn_vp_transformer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changerViewpagerTransformer();
            }
        });

        initData();

        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);

        mViewPager.setAdapter(new PagerAdapter() {
            @Override
            public Object instantiateItem(ViewGroup container, int position) {

                container.addView(mImageViews.get(position));
                return mImageViews.get(position);
            }

            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {

                container.removeView(mImageViews.get(position));
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public int getCount() {
                return mImgIds.length;
            }
        });

        mViewPager.setPageTransformer(true, new RotateDownPageTransformer());
    }

    boolean vpTransformer = false;

    private void changerViewpagerTransformer() {
        if (vpTransformer) {
            vpTransformer = false;
            mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        } else {
            vpTransformer = true;
            mViewPager.setPageTransformer(true, new DepthPageTransformer());
        }
    }


    private void initData() {
        for (int imgId : mImgIds) {
            ImageView imageView = new ImageView(getApplicationContext());
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageResource(imgId);
            mImageViews.add(imageView);
        }
    }
}
