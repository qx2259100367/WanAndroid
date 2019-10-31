package com.example.wanandroid.presenter;

import android.widget.Toast;

import com.example.wanandroid.base.BasePresenter;
import com.example.wanandroid.base.SearcheData;
import com.example.wanandroid.model.SearCherModel;
import com.example.wanandroid.net.ResultCallBack;
import com.example.wanandroid.util.ToastUtil;
import com.example.wanandroid.view.SearCherView;

/**
 * Created by 裘翔 on 2019/10/29.
 */

public class SearCherPresenter extends BasePresenter<SearCherView> implements ResultCallBack<SearcheData> {
    private SearCherView searCherView;
    private SearCherModel searCherModel;
    @Override
    protected void initModel() {
         this.searCherView=searCherView;
         this.searCherModel=new SearCherModel();
    }

    public void loadData(int page, String k) {
        searCherModel.loadData(this,page,k);
    }

    @Override
    public void onSuccess(SearcheData bean) {
        if (mView!=null){
             mView.onSuccess(bean);
        }
    }

    @Override
    public void onFail(String msg) {
        ToastUtil.showLong(msg);
    }
}
