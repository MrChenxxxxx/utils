package com.skylele.myapplication.activity;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.skylele.myapplication.R;
import com.skylele.myapplication.widget.popWindow.MyPopupWindow;
import com.skylele.myapplication.widget.popWindow.MyPopupWindow1;
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
//                changerViewpagerTransformer();
//                MyPopupWindow popupWindow = new MyPopupWindow(WidgetUseActivity.this);
//                popupWindow.showAsDropDown(view);
//                popupWindow.showAtLocation(findViewById(R.id.title), Gravity.NO_GRAVITY, 0, 0);

//                bottomwindow(view);

                showMyPopupWindow(view);
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

    private void showMyPopupWindow(View view) {
        MyPopupWindow1 popupWindow = new MyPopupWindow1(WidgetUseActivity.this);
        popupWindow.showAtLocation(view,Gravity.BOTTOM|Gravity.CENTER_VERTICAL,0,0);
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

    PopupWindow popupWindow;

    void bottomwindow(View view) {
        if (popupWindow != null && popupWindow.isShowing()) {
            return;
        }
        LinearLayout layout = (LinearLayout) getLayoutInflater().inflate(R.layout.view_window_popup, null);
        popupWindow = new PopupWindow(layout,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        //点击空白处时，隐藏掉pop窗口
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        //添加弹出、弹入的动画
        popupWindow.setAnimationStyle(R.style.Popupwindow);
//        int[] location = new int[2];
//        view.getLocationOnScreen(location);
//        popupWindow.showAtLocation(view, Gravity.LEFT | Gravity.BOTTOM, 0, -location[1]);
        popupWindow.showAtLocation(view, Gravity.LEFT | Gravity.BOTTOM, 0,0);
        //添加按键事件监听
        setButtonListeners(layout);
        //添加pop窗口关闭事件，主要是实现关闭时改变背景的透明度
//        popupWindow.setOnDismissListener(new poponDismissListener());
        backgroundAlpha(0.3f);
    }

    private void setButtonListeners(LinearLayout layout) {
        Button camera = (Button) layout.findViewById(R.id.camera);
        Button gallery = (Button) layout.findViewById(R.id.gallery);
        Button savepicture = (Button) layout.findViewById(R.id.savepicture);
        Button cancel = (Button) layout.findViewById(R.id.cancel);

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popupWindow != null && popupWindow.isShowing()) {
                    //在此处添加你的按键处理 xxx
                    popupWindow.dismiss();
                }
            }
        });
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popupWindow != null && popupWindow.isShowing()) {
                    //在此处添加你的按键处理 xxx
                    popupWindow.dismiss();
                }
            }
        });
        savepicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popupWindow != null && popupWindow.isShowing()) {
                    //在此处添加你的按键处理 xxx
                    popupWindow.dismiss();
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }
            }
        });
    }

    /**
     * 设置添加屏幕的背景透明度
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha)
    {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }
}
