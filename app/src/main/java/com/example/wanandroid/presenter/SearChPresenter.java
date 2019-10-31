package com.example.wanandroid.presenter;

import com.example.wanandroid.base.BasePresenter;
import com.example.wanandroid.bean.Searchforwords;
import com.example.wanandroid.model.SearChModel;
import com.example.wanandroid.net.ResultCallBack;
import com.example.wanandroid.view.SearChView;

/**
 * Created by 裘翔 on 2019/10/28.
 */

public class SearChPresenter extends BasePresenter<SearChView> implements ResultCallBack<Searchforwords> {
    private SearChView searChView;
    private SearChModel searChModel;
    @Override
    protected void initModel() {
      this.searChView=searChView;
      this.searChModel=new SearChModel();
    }

    public void loadData() {
        searChModel.loadData(this);
    }

    @Override
    public void onSuccess(Searchforwords bean) {
        if (mView!=null){
            mView.onSuccess(bean);
        }
    }

    @Override
    public void onFail(String msg) {
            mView.onFailed(msg);
    }
}
