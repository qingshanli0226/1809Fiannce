package com.fiance.chengtianle.LiCai;

import com.fiance.framework.IBaseView;
import com.fiance.net.mode.LcBean;

public interface ILiCaiView extends IBaseView {
    void onLiCaiData(LcBean lcBean);
}
