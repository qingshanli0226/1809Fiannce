package com.example.gitproject.Invest.manageFinances;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.framework.BaseFragment;
import com.example.gitproject.R;
import com.example.net.bean.ProductBean;
import com.yatoooon.screenadaptation.ScreenAdapterTools;


public class ManageFinancesFragment extends BaseFragment<ManageFinancesPresenter> implements ManageFinancesView {


    private RecyclerView financesRv;
    private FinancesAdapter financesAdapter;
    private TextView financesLamp;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_manage_finances;
    }

    @Override
    protected void initView() {
        ScreenAdapterTools.getInstance().loadView(inflate);

        financesRv = (RecyclerView) inflate.findViewById(R.id.finances_rv);
        financesLamp = (TextView) inflate.findViewById(R.id.finances_lamp);


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

        financesLamp.setSelected(true);
    }

    @Override
    public void onProduct(ProductBean productBean) {
        financesAdapter.getData().addAll(productBean.getResult());
        financesAdapter.notifyDataSetChanged();


    }
    @Override
    public void showLoading() {
        load.setVisibility(View.VISIBLE);
        if (animationDrawable != null) {
            animationDrawable.start();
        }

    }

    @Override
    public void hideLoading() {
        if (animationDrawable != null) {
            animationDrawable.stop();
        }
        load.setVisibility(View.GONE);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getActivity(), "" + error, Toast.LENGTH_SHORT).show();
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