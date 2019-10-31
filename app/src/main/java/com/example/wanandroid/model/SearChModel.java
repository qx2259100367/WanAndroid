package com.example.wanandroid.model;

import com.example.wanandroid.ApiServer;
import com.example.wanandroid.base.BaseModel;
import com.example.wanandroid.bean.Searchforwords;
import com.example.wanandroid.net.BaseObserver;
import com.example.wanandroid.net.HttpUtils;
import com.example.wanandroid.net.ResultCallBack;
import com.example.wanandroid.net.RxUtils;
import com.example.wanandroid.presenter.SearChPresenter;

/**
 * Created by 裘翔 on 2019/10/28.
 */

public class SearChModel extends BaseModel{

    public void loadData(final ResultCallBack<Searchforwords> callBack) {
        ApiServer apiserver = HttpUtils.getInstance().getApiserver(ApiServer.homeUrl, ApiServer.class);
        apiserver.getSearcherforwords()
                .compose(RxUtils.<Searchforwords>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<Searchforwords>(this) {
                    @Override
                    protected void onSuccess(Searchforwords searchforwords) {
                          callBack.onSuccess(searchforwords);
                    }

                    @Override
                    protected void error(String err) {
                           callBack.onFail(err);
                    }
                });
    }
}
