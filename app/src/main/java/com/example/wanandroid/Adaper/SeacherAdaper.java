package com.example.wanandroid.Adaper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wanandroid.R;
import com.example.wanandroid.SearCherActivity;
import com.example.wanandroid.base.SearcheData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 裘翔 on 2019/10/29.
 */

public class SeacherAdaper extends RecyclerView.Adapter<SeacherAdaper.ViewHolder>{
    private Context context;
    private List<SearcheData.DataBean.DatasBean> list;

    public SeacherAdaper(Context context, List<SearcheData.DataBean.DatasBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public SeacherAdaper.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.rec_item, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(SeacherAdaper.ViewHolder holder, int position) {
        holder.tvname.setText(list.get(position).getShareUser());
        holder.tv_name_page.setText(list.get(position).getSuperChapterName());
        holder.tv_desc.setText(list.get(position).getTitle());
        holder.tv_time.setText(list.get(position).getNiceDate());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvname;
        TextView tv_name_page;
        TextView tv_desc;
        TextView tv_time;
        public ViewHolder(View itemView) {
            super(itemView);
            tvname=itemView.findViewById(R.id.tvname);
            tv_name_page=itemView.findViewById(R.id.tv_name_homepage);
            tv_desc=itemView.findViewById(R.id.tv_desc_homepage);
            tv_time=itemView.findViewById(R.id.tv_time_homepage);
        }
    }
}
