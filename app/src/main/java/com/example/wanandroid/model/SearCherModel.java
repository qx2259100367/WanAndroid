package com.example.wanandroid.model;

import com.example.wanandroid.ApiServer;
import com.example.wanandroid.base.BaseModel;
import com.example.wanandroid.base.SearcheData;
import com.example.wanandroid.net.BaseObserver;
import com.example.wanandroid.net.HttpUtils;
import com.example.wanandroid.net.ResultCallBack;
import com.example.wanandroid.net.RxUtils;
import com.example.wanandroid.presenter.SearCherPresenter;

/**
 * Created by 裘翔 on 2019/10/29.
 */

public class SearCherModel extends BaseModel{
    public void loadData(final ResultCallBack<SearcheData> callBack, int page, String k) {
        ApiServer apiserver = HttpUtils.getInstance().getApiserver(ApiServer.homeUrl, ApiServer.class);
        apiserver.getSearcherData(page,k)
                .compose(RxUtils.<SearcheData>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<SearcheData>(this) {
                    @Override
                    protected void onSuccess(SearcheData searcheData) {
                         callBack.onSuccess(searcheData);
                    }

                    @Override
                    protected void error(String err) {

                    }
                });
    }
}
