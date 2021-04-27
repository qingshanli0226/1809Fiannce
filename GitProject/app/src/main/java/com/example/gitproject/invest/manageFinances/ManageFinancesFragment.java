package com.example.gitproject.invest.manageFinances;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.example.framework.BaseFragment;
import com.example.gitproject.invest.manageFinances.adapter.FinancesAdapter;
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
    private int lastx;
    @Override
    protected void initData() {
        mPresenter.getIvnest();
        financesRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        financesRv.setAdapter(financesAdapter);
        //走马灯
        financesLamp.setSelected(true);

//        financesAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
//            @Override
//            public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
//                switch (view.getId()) {
//                    case R.id.item_del:
//                        financesAdapter.getData().remove(position);
//                        financesAdapter.notifyDataSetChanged();
//                        financesAdapter.hideDel();
//                        break;
//                }
//            }
//        });


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