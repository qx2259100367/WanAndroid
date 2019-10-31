package com.example.wanandroid.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.wanandroid.Adaper.ProjectDataAdapter;
import com.example.wanandroid.Main_Data_Activity;
import com.example.wanandroid.R;
import com.example.wanandroid.base.BaseFragment;
import com.example.wanandroid.bean.ProjectDataBean;
import com.example.wanandroid.presenter.ProjectDataPresenter;
import com.example.wanandroid.view.ProjectDataView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 裘翔 on 2019/10/25.
 */

public class ProjectDataFragment extends BaseFragment<ProjectDataView, ProjectDataPresenter> implements ProjectDataView {
    @BindView(R.id.pro_rec)
    RecyclerView mProRec;
    @BindView(R.id.pro_small)
    SmartRefreshLayout mProSmall;
    private int page = 1;
    private String id;
    private List<ProjectDataBean.DataBean.DatasBean> datasBeans;
    private ProjectDataAdapter dataAdapter;
    private String name;
    private String link;
    private Intent intent;

    @Override
    protected ProjectDataPresenter initPresenter() {
        return new ProjectDataPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.project_data_layout;
    }

    @Override
    public void initData() {
        id = getArguments().getString("id");
        mPresenter.loadData(page, Integer.parseInt(id));
    }

    @Override
    public void initView() {
        mProRec.setLayoutManager(new LinearLayoutManager(getContext()));
        datasBeans = new ArrayList<>();
        dataAdapter = new ProjectDataAdapter(datasBeans, getContext());
        mProRec.setAdapter(dataAdapter);
        dataAdapter.setOnClickItem(new ProjectDataAdapter.OnClickItem() {
            @Override
            public void setonItemClick(View v, int position) {
                name = datasBeans.get(position).getTitle();
                link = datasBeans.get(position).getLink();
                intent = new Intent(getActivity(), Main_Data_Activity.class);
                intent.putExtra("name", name);
                intent.putExtra("link", link);
                startActivity(intent);
            }
        });
        mProSmall.setOnLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                page++;
                mPresenter.loadData(page, Integer.parseInt(id));
                mProSmall.finishLoadMore();
            }

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                datasBeans.clear();
                page = 0;
                initData();
                mProSmall.finishRefresh();
            }
        });
    }

    @Override
    public void onSuccess(ProjectDataBean bean) {
        datasBeans.addAll(bean.getData().getDatas());
        dataAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailed(String str) {
        Toast.makeText(getContext(), "str", Toast.LENGTH_SHORT).show();
    }
}
