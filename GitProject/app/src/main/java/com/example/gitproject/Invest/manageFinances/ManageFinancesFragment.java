package com.example.gitproject.Invest.manageFinances;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.framework.BaseFragment;
import com.example.gitproject.R;
import com.example.net.bean.ProductBean;


public class ManageFinancesFragment extends BaseFragment<ManageFinancesPresenter> implements ManageFinancesView {


    private RecyclerView financesRv;
    private ProgressBar financesProgress;
    private FinancesAdapter financesAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_manage_finances;
    }

    @Override
    protected void initView() {

        financesRv = (RecyclerView) inflate.findViewById(R.id.finances_rv);
        financesProgress = (ProgressBar) inflate.findViewById(R.id.finances_progress);
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
    }

    @Override
    public void onProduct(ProductBean productBean) {
        financesAdapter.getData().addAll(productBean.getResult());
        financesAdapter.notifyDataSetChanged();



    }

    @Override
    public void showLoading() {
        financesProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        financesProgress.setVisibility(View.GONE);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getActivity(), ""+error, Toast.LENGTH_SHORT).show();
    }
}