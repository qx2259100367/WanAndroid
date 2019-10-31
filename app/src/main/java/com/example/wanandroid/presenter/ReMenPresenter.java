package com.example.wanandroid.presenter;

import com.example.wanandroid.base.BasePresenter;
import com.example.wanandroid.bean.ReMenBean;
import com.example.wanandroid.model.ReMenModel;
import com.example.wanandroid.net.ResultCallBack;
import com.example.wanandroid.view.ReMenView;

/**
 * Created by 裘翔 on 2019/10/28.
 */

public class ReMenPresenter extends BasePresenter<ReMenView> implements ResultCallBack<ReMenBean> {
    private ReMenView reMenView;
    private ReMenModel reMenModel;
    @Override
    protected void initModel() {
         this.reMenView=reMenView;
         this.reMenModel=new ReMenModel();
    }

    public void loadData() {
        reMenModel.loadData(this);
    }

    @Override
    public void onSuccess(ReMenBean bean) {
          if (mView!=null){
              mView.onSuccess(bean);
          }
    }

    @Override
    public void onFail(String msg) {
         mView.onFailed(msg);
    }
}
