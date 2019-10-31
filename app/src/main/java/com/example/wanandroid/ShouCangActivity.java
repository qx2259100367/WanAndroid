package com.example.wanandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.wanandroid.base.BaseActivity;
import com.example.wanandroid.bean.ShouCangBean;
import com.example.wanandroid.presenter.ShouCangPresenter;
import com.example.wanandroid.view.ShouCangView;

public class ShouCangActivity extends BaseActivity<ShouCangPresenter> implements ShouCangView{

    @Override
    protected ShouCangPresenter initPresenter() {
        return new ShouCangPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shou_cang;
    }

    @Override
    protected void initData() {
        Log.i("liuzhen","initData");
        mPresenter.loadData();

    }

    @Override
    public void onSuccess(ShouCangBean shouCangBean) {

    }

    @Override
    public void onFailed(String str) {
        startActivity(new Intent(ShouCangActivity.this,LoginActivity.class));
    }
}
