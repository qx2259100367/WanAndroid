package com.example.wanandroid;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.wanandroid.base.BaseActivity;
import com.example.wanandroid.bean.ReMenBean;
import com.example.wanandroid.presenter.ReMenPresenter;
import com.example.wanandroid.util.SystemFacade;
import com.example.wanandroid.view.ReMenView;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

import butterknife.BindView;

public class ReMenActivity extends BaseActivity<ReMenPresenter> implements ReMenView{
    @BindView(R.id.toolbarTitle)
    TextView mToolbarTitle;
    @BindView(R.id.usage_toolbar)
    Toolbar mUsageToolbar;
    @BindView(R.id.appBarLayout)
    AppBarLayout mAppBarLayout;
    @BindView(R.id.item_navigation_flow_layout)
    TagFlowLayout mItemNavigationFlowLayout;

    @Override
    protected ReMenPresenter initPresenter() {
        return new ReMenPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_re_men;
    }

    @Override
    protected void initData() {
        mPresenter.loadData();
    }

    @Override
    protected void initView() {
        mUsageToolbar.setTitle("");
        mUsageToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onSuccess(ReMenBean reMenBean) {
        final List<ReMenBean.DataBean> data = reMenBean.getData();
        mItemNavigationFlowLayout.setAdapter(new TagAdapter<ReMenBean.DataBean>(data) {
            @Override
            public View getView(FlowLayout parent, int position, ReMenBean.DataBean dataBean) {
                TextView tv = (TextView) LayoutInflater.from(ReMenActivity.this).inflate(R.layout.textview_item, null);
                String name = dataBean.getName();
                tv.setText(name);
                 tv.setBackgroundColor(SystemFacade.randomColor());
//                tv.setBackgroundColor(SystemFacade.randomColor());
                tv.setTextColor(Color.WHITE);
                mItemNavigationFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                    @Override
                    public boolean onTagClick(View view, int position, FlowLayout parent) {
                        ReMenBean.DataBean bean = data.get(position);
                        String beanName = bean.getName();

                        String link = bean.getLink();
                        Intent intent = new Intent(ReMenActivity.this, Main_Data_Activity.class);
                        intent.putExtra("link",link);
                        intent.putExtra("name",beanName);
                        startActivity(intent);
                        return true;
                    }
                });
                return tv;
            }
        });
    }

    @Override
    public void onFailed(String str) {

    }
}
