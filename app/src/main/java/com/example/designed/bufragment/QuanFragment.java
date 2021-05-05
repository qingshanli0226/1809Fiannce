package com.example.designed.bufragment;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;


import com.blankj.utilcode.util.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.designed.R;
import com.example.designed.adapter.LiAdapter;
import com.example.designed.welcome.IWelcomeView;
import com.example.designed.welcome.WelcomePresenter;
import com.fiannce.bawei.framework.BaseFragment;
import com.fiannce.bawei.framework.IBaseView;
import com.fiannce.bawei.net.model.HomeBean;
import com.fiannce.bawei.net.model.Libean;
import com.fiannce.bawei.net.model.VersionBean;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;


import java.util.ArrayList;
import java.util.List;

import top.littlefogcat.danmakulib.danmaku.Danmaku;
import top.littlefogcat.danmakulib.danmaku.DanmakuManager;

import static top.littlefogcat.danmakulib.danmaku.Danmaku.COLOR_RED;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuanFragment extends BaseFragment implements IBaseView, IWelcomeView {

    private FrameLayout fragment;
    LiAdapter liAdapter;
    private WelcomePresenter welcomePresenter;
    private RecyclerView rv;
    private SmartRefreshLayout sm;
    List<Libean.ResultBean> list = new ArrayList<>();

    public QuanFragment() {
        // Required empty public constructor
    }

    @Override
    protected void initPresenter() {
        welcomePresenter = new WelcomePresenter(this);
    }

    @Override
    protected void initData() {
        welcomePresenter.getLiData();


    }

    @Override
    protected void initView() {
        fragment = (FrameLayout) findViewById(R.id.fragment);
        rv = (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        sm = (SmartRefreshLayout) findViewById(R.id.sm);

        DanmakuManager dm = DanmakuManager.getInstance();
        dm.setDanmakuContainer(fragment);
        dm.init(getContext(),fragment);

        Danmaku danmaku = new Danmaku();
        danmaku.text=getResources().getString(R.string.you);
        danmaku.size=100;
        danmaku.color = COLOR_RED;
        dm.send(danmaku);





//        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
//            @Override
//            public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
//                return makeMovementFlags(ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, ItemTouchHelper.UP | ItemTouchHelper.DOWN);
//            }
//
//            @Override
//            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
//                return false;
//            }
//
//            @Override
//            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//                int adapterPosition = viewHolder.getAdapterPosition();
//
//
//            }
//        });
//
//        itemTouchHelper.attachToRecyclerView(rv);


    }

    @Override
    protected int getLoutId() {
        return R.layout.fragment_quan;
    }

    @Override
    public void showLoading() {
        loadingPage.showLoadingView();
    }

    @Override
    public void hideLoading() {
        loadingPage.showSuccessView();
    }

    @Override
    public void showError(String error) {
        loadingPage.showError(error);
    }

    @Override
    public void onHomeData(HomeBean homeBean) {

    }

    @Override
    public void onVersionData(VersionBean versionBean) {

    }

    @Override
    public void onLiData(Libean libean) {
        list.addAll(libean.getResult());

        LogUtils.json(libean);
        liAdapter= new LiAdapter(R.layout.layout, list);

        rv.setAdapter(liAdapter);

        rv.setTop(0);
        liAdapter.notifyDataSetChanged();

        liAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.textView){
                    list.remove(position);
                    liAdapter.notifyItemRemoved(position);

                }
            }
        });

        sm.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                welcomePresenter.getHomeData();
                liAdapter.notifyDataSetChanged();
                sm.finishLoadMore();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                welcomePresenter.getHomeData();
                liAdapter.notifyDataSetChanged();
                sm.finishRefresh();
            }
        });

    }




//    @Override
//    public boolean onTouch(View view, MotionEvent motionEvent) {
//
//        switch (motionEvent.getAction()){
//            case MotionEvent.ACTION_DOWN:
//                lastX = (int) motionEvent.getX();
//               // lastY = (int) motionEvent.getY();
//
//                return  true;
//
//            case  MotionEvent.ACTION_MOVE:
//
//                if (lastX >500 &&motionEvent.getRawX()<lastX){
//                    if (){
//
//                    }
//                }
//
//                break;
//        }
//
//        return true;
//    }
}
