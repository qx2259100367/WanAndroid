package com.example.wanandroid.presenter;

import android.util.Log;

import com.example.wanandroid.base.BasePresenter;
import com.example.wanandroid.bean.ShouCangBean;
import com.example.wanandroid.model.ShouCangModel;
import com.example.wanandroid.net.ResultCallBack;
import com.example.wanandroid.view.ShouCangView;

/**
 * Created by 裘翔 on 2019/10/30.
 */

public class ShouCangPresenter extends BasePresenter<ShouCangView> implements ResultCallBack<ShouCangBean> {

    private ShouCangModel shouCangModel;
    @Override
    protected void initModel() {
        this.shouCangModel=new ShouCangModel();
    }

    public void loadData() {
        Log.i("liuzhen","initDatap");
         shouCangModel.loadData(this);
    }

    @Override
    public void onSuccess(ShouCangBean bean) {
        int errorCode = bean.getErrorCode();
        if (errorCode==0){
             mView.onSuccess(bean);
        }else {
             mView.onFailed(bean.getErrorCode()+"");
        }
    }

    @Override
    public void onFail(String msg) {
            mView.onFailed(msg);
    }
}
