package com.z1Finance.z1FinanceCore;

public interface IBasePresenter<ViewT> {

    void onViewActive(ViewT view);

    void onViewInactive();

    void onInitView();

}
