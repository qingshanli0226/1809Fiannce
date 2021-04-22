package com.finance.zg6.ui.investment.ui.allfiannce.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.finance.net.bean.ProductBean;
import com.finance.zg.R;
import com.finance.zg6.ui.view.ProgressView;

import java.util.List;

public class AllFinanceAdapter extends BaseQuickAdapter<ProductBean.ResultBean, BaseViewHolder> {

    public AllFinanceAdapter(@Nullable List<ProductBean.ResultBean> data) {
        super(R.layout.itme_all_finance_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProductBean.ResultBean item) {
        TextView view = helper.getView(R.id.all_fiannce_name);
        TextView view1 = helper.getView(R.id.all_finance_money);
        TextView view2 = helper.getView(R.id.all_finance_year_Rate);
        TextView view3 = helper.getView(R.id.all_finance_year_suodingDays);
        TextView view4 = helper.getView(R.id.all_finance_minTouMoney);
        TextView view5 = helper.getView(R.id.all_finance_memberNum);
        ProgressView view6 = helper.getView(R.id.all_finance_progress_view);

        view.setText(""+item.getName());
        view1.setText(""+item.getMoney());
        view2.setText(""+item.getYearRate());
        view3.setText(""+item.getSuodingDays());
        view4.setText(""+item.getMinTouMoney());
        view5.setText(""+item.getMemberNum());
        view6.startProgress(Integer.parseInt(item.getProgress()),false);
    }
}
