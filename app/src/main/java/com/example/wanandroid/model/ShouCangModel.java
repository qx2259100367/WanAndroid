package com.example.wanandroid.model;

import android.util.Log;

import com.example.wanandroid.ApiServer;
import com.example.wanandroid.base.BaseModel;
import com.example.wanandroid.bean.ShouCangBean;
import com.example.wanandroid.net.BaseObserver;
import com.example.wanandroid.net.HttpUtils;
import com.example.wanandroid.net.ResultCallBack;
import com.example.wanandroid.net.RxUtils;
import com.example.wanandroid.presenter.ShouCangPresenter;

/**
 * Created by 裘翔 on 2019/10/30.
 */

public class ShouCangModel extends BaseModel{
    public void loadData(final ResultCallBack<ShouCangBean> callBack) {
        ApiServer apiserver = HttpUtils.getInstance().getApiserver(ApiServer.homeUrl, ApiServer.class);
        apiserver.getShouCangData()
                .compose(RxUtils.<ShouCangBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<ShouCangBean>(this) {
                    @Override
                    protected void onSuccess(ShouCangBean shouCangBean) {
                        Log.i("shoucang","sssssssss"+shouCangBean.toString());
                         callBack.onSuccess(shouCangBean);
                    }

                    @Override
                    protected void error(String err) {
                        Log.i("liuzhen","initData"+err.toString());
                         callBack.onFail(err);
                    }
                });
    }
}
