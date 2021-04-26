package com.fiannce.bawei.a1809fiannce.event;

import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fiannce.bawei.a1809fiannce.R;
import com.fiannce.bawei.framework.BaseActivity;

import androidx.viewpager.widget.ViewPager;


@Route(path = "/event/EventActivity")
public class EventActivity extends BaseActivity {
    private LinearLayout itemView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_event;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {

    }

    int lastX;
    @Override
    protected void initView() {
        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new EventFragmentAdapter(getSupportFragmentManager()));
        itemView = findViewById(R.id.itemView);
        findViewById(R.id.itemView).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        itemView.getParent().requestDisallowInterceptTouchEvent(true);
                        lastX = (int) event.getRawX();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        int diffX = (int) (lastX-event.getRawX());
                        itemView.scrollTo(diffX,0);

                        break;
                }
                return true;
            }
        });
    }
}
