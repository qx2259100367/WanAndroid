package com.example.wanandroid;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wanandroid.Adaper.SeacherAdaper;
import com.example.wanandroid.base.BaseActivity;
import com.example.wanandroid.base.SearcheData;
import com.example.wanandroid.presenter.SearCherPresenter;
import com.example.wanandroid.view.SearCherView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearCherActivity extends BaseActivity<SearCherPresenter> implements SearCherView{
    @BindView(R.id.searcher_back)
    ImageView mSearcherBack;
    @BindView(R.id.searcher_name)
    TextView mSearcherName;
    @BindView(R.id.searcher_tool)
    Toolbar mSearcherTool;
    @BindView(R.id.appBarLayout)
    AppBarLayout mAppBarLayout;
    @BindView(R.id.searcher_recy)
    RecyclerView mSearcherRecy;
    private int page = 1;
    private List<SearcheData.DataBean.DatasBean> datas;
    private SeacherAdaper seacherAdaper;

    @Override
    protected SearCherPresenter initPresenter() {
        return new SearCherPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sear_cher;
    }

    @Override
    protected void initData() {
        String k = getIntent().getStringExtra("k");
        mSearcherName.setText(k);
        mPresenter.loadData(page, k);
        mSearcherBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onSuccess(SearcheData searcheData) {
        datas = searcheData.getData().getDatas();
        mSearcherRecy.setLayoutManager(new LinearLayoutManager(this));
        seacherAdaper = new SeacherAdaper(this, datas);
        mSearcherRecy.setAdapter(seacherAdaper);
    }

    @Override
    public void onFailed(String str) {
        Toast.makeText(this,"sss",Toast.LENGTH_LONG).show();
    }
}
