package com.example.wanandroid;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wanandroid.Adaper.SearchAdapter;
import com.example.wanandroid.base.BaseActivity;
import com.example.wanandroid.bean.Searchforwords;
import com.example.wanandroid.bean.Stringadd;
import com.example.wanandroid.presenter.SearChPresenter;
import com.example.wanandroid.view.SearChView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SearChActivity extends BaseActivity<SearChPresenter> implements SearChView {

    @BindView(R.id.search_iv)
    ImageView mSearchIv;
    @BindView(R.id.search_name)
    EditText mSearchName;
    @BindView(R.id.search_btn)
    TextView mSearchBtn;
    @BindView(R.id.search_recy)
    RecyclerView mSearchRecy;

    private ArrayList<Stringadd> stringadd;
    private SearchAdapter searchAdapter;


    @Override
    protected SearChPresenter initPresenter() {
        return new SearChPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sear_ch;
    }

    @Override
    protected void initData() {
        mPresenter.loadData();
        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = mSearchName.getText().toString();
                stringadd.add(new Stringadd(s));
                Intent intent = new Intent(SearChActivity.this, SearCherActivity.class);
                intent.putExtra("k",s);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onSuccess(Searchforwords searchforwords) {
        List<Searchforwords.DataBean> data = searchforwords.getData();
        initshu(data);
    }

    private void initshu(List<Searchforwords.DataBean> data) {
        stringadd = new ArrayList<>();
        mSearchRecy.setLayoutManager(new LinearLayoutManager(this));
        searchAdapter = new SearchAdapter(data, stringadd, SearChActivity.this);
        mSearchRecy.setAdapter(searchAdapter);

    }

    @Override
    public void onFailed(String str) {
        Toast.makeText(this, "str", Toast.LENGTH_SHORT).show();
    }
}
