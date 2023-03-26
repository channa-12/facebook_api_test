package com.z1Finance.z1FinanceCore;


import android.content.Context;

public interface IBaseView {

    void showToastMessage(String message);

    void setProgressBar(boolean show);

    void initView();

    Context getContext();

}