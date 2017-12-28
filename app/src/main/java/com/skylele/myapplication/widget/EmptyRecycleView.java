package com.skylele.myapplication.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.AdapterDataObserver;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.skylele.myapplication.R;

/**
 * 带有空布局的recycleView
 * EmptyRecycleView 不是真正的recycleView，如果要用到recycleView方法这里没有，可以新增方法进行透传
 *
 * @author cxg
 * @date 2017/12/27
 */


public class EmptyRecycleView extends FrameLayout {

    private RecyclerView mRecycleView;
    private FrameLayout mEmptyView;
    private int mEmptyViewId;
    private final String TAG = "EmptyRecycleView";

    public EmptyRecycleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EmptyRecycleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.EmptyRecycleView);
        mEmptyViewId = ta.getResourceId(R.styleable.EmptyRecycleView_erv_empty_view, -1);
        ta.recycle();
        initView(context);
    }

    private void initView(Context context) {
        View inflate = View.inflate(context, R.layout.view_empty_recycle, null);
        mRecycleView = (RecyclerView) inflate.findViewById(R.id.rv_empty);
        mEmptyView = (FrameLayout) inflate.findViewById(R.id.fl_empty);
        if (mEmptyViewId != -1) {
            View emptyView = View.inflate(context, mEmptyViewId, null);
            mEmptyView.addView(emptyView);
        }
        addView(inflate);
    }

    /**
     * 代码动态设置空布局
     * @param emptyView
     */
    public void setEmptyView(View emptyView) {
        mEmptyView.removeAllViews();
        mEmptyView.addView(emptyView);
        checkIfEmpty();
    }

    public void setAdapter(RecyclerView.Adapter adapter) {

        final RecyclerView.Adapter oldAdapter = mRecycleView.getAdapter();
        if (oldAdapter != null) {
            oldAdapter.unregisterAdapterDataObserver(observer);
        }
        mRecycleView.setAdapter(adapter);
        if (adapter != null) {
            adapter.registerAdapterDataObserver(observer);
        }
        checkIfEmpty();
    }

    public void setLayoutManager(RecyclerView.LayoutManager layout) {
        mRecycleView.setLayoutManager(layout);
    }

    public void setItemAnimator(RecyclerView.ItemAnimator animator) {
        mRecycleView.setItemAnimator(animator);
    }

    public void scrollToPosition(int position) {
        mRecycleView.scrollToPosition(position);
    }

    public void addItemDecoration(RecyclerView.ItemDecoration decor) {
        mRecycleView.addItemDecoration(decor);
    }

    final private AdapterDataObserver observer = new AdapterDataObserver() {
        @Override
        public void onChanged() {
            checkIfEmpty();
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            Log.i(TAG, "onItemRangeInserted" + itemCount);
            checkIfEmpty();
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            checkIfEmpty();
        }
    };

    private void checkIfEmpty() {
        if (mRecycleView.getAdapter() != null) {
            boolean emptyViewVisible = mRecycleView.getAdapter().getItemCount() == 0;
            mEmptyView.setVisibility(emptyViewVisible ? VISIBLE : GONE);
            mRecycleView.setVisibility(emptyViewVisible ? GONE : VISIBLE);
        }
    }
}

