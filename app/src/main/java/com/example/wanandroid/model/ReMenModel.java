package com.example.wanandroid.model;

import com.example.wanandroid.ApiServer;
import com.example.wanandroid.base.BaseModel;
import com.example.wanandroid.bean.ReMenBean;
import com.example.wanandroid.net.BaseObserver;
import com.example.wanandroid.net.HttpUtils;
import com.example.wanandroid.net.ResultCallBack;
import com.example.wanandroid.net.RxUtils;
import com.example.wanandroid.presenter.ReMenPresenter;

/**
 * Created by 裘翔 on 2019/10/28.
 */

public class ReMenModel extends BaseModel{

    public void loadData(final ResultCallBack<ReMenBean> callBack) {
        ApiServer apiserver = HttpUtils.getInstance().getApiserver(ApiServer.ReMenUrl, ApiServer.class);
        apiserver.getReMen()
                .compose(RxUtils.<ReMenBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<ReMenBean>(this) {
                    @Override
                    protected void onSuccess(ReMenBean reMenBean) {
                        callBack.onSuccess(reMenBean);
                    }

                    @Override
                    protected void error(String err) {
                        callBack.onFail(err);
                    }
                });
    }
}
