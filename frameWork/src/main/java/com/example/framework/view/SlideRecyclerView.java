package com.example.framework.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class SlideRecyclerView extends RecyclerView {

    private static final String TAG="SlideRecyclerView";
    private static final int INVALID_POSITION = -1;
    private static final int INVALID_CHILD_WIDTH  = -1;
    private static final int SNAP_VELOCITY  = 600;

    private VelocityTracker mVelocityTracker;   // 速度追踪器
    private int mTouchSlop; // 认为是滑动的最小距离（一般由系统提供）
    private Rect mTouchFrame;   // 子View所在的矩形范围
    private Scroller mScroller;
    private float mLastX;   // 滑动过程中记录上次触碰点X
    private float mFirstX, mFirstY; // 首次触碰范围
    private boolean mIsSlide;   // 是否滑动子View
    private ViewGroup mFlingView;   // 触碰的子View
    private int mPosition;  // 触碰的view的位置
    private int mMenuViewWidth;    // 菜单按钮宽度

    public SlideRecyclerView(@NonNull Context context) {
        this(context,null);
    }

    public SlideRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SlideRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        mScroller = new Scroller(context);
    }
}
