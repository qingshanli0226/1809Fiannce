package com.example.gitproject.Invest.manageFinances;

import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.framework.BaseFragment;
import com.example.gitproject.Invest.manageFinances.adapter.FinancesAdapter;
import com.example.gitproject.R;
import com.example.net.bean.ProductBean;



public class ManageFinancesFragment extends BaseFragment<ManageFinancesPresenter> implements IManageFinancesView {


    private RecyclerView financesRv;
    private FinancesAdapter financesAdapter;
    private TextView financesLamp;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_manage_finances;
    }

    @Override
    protected void initView() {


        financesRv = (RecyclerView) findViewById(R.id.finances_rv);
        financesLamp = (TextView) findViewById(R.id.finances_lamp);


        financesAdapter = new FinancesAdapter();
    }

    @Override
    protected void initPrensenter() {
        mPresenter = new ManageFinancesPresenter(this);

    }

    @Override
    protected void initData() {
        mPresenter.getIvnest();
        financesRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        financesRv.setAdapter(financesAdapter);
        //走马灯
        financesLamp.setSelected(true);




        financesRv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        v.scrollTo(100,0);

                        return false;
                    }
                });
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        Toast.makeText(getActivity(), "aaa", Toast.LENGTH_SHORT).show();
//                        getActivity().getParent().
                        v.scrollTo(1000,0);
                        financesRv.requestDisallowInterceptTouchEvent(false);

                        break;
                    case MotionEvent.ACTION_MOVE:
                        if(event.getRawX()>500) {
                            Toast.makeText(getActivity(), "bbb", Toast.LENGTH_SHORT).show();
                            financesRv.requestDisallowInterceptTouchEvent(true);
                            v.scrollTo(1000,0);

                        } else{
                            Toast.makeText(getActivity(), "bbb", Toast.LENGTH_SHORT).show();
                            financesRv.requestDisallowInterceptTouchEvent(true);
                        }

                        Toast.makeText(getActivity(), "ccc", Toast.LENGTH_SHORT).show();


                        break;
                }
                return false;
            }
        });

    }

    @Override
    public void onProduct(ProductBean productBean) {
        financesAdapter.getData().addAll(productBean.getResult());
        financesAdapter.notifyDataSetChanged();
        loadPage.showSuccessLayout();

    }
    @Override
    public void showLoading() {
        loadPage.showLoadLayout(false);

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String error) {
        loadPage.showErrorText(error);
    }

    @Override
    public void onClickCenter() {

    }

    @Override
    public void onClickLeft() {

    }

    @Override
    public void onClickRight() {

    }
}