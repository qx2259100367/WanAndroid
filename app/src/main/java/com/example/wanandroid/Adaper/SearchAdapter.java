package com.example.wanandroid.Adaper;



import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wanandroid.R;
import com.example.wanandroid.bean.Searchforwords;
import com.example.wanandroid.bean.Stringadd;
import com.example.wanandroid.util.SystemFacade;
import com.zhy.view.flowlayout.FlowLayout;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.NonNull;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    private List<Searchforwords.DataBean> articlesBeans;
    private ArrayList<Stringadd> stringBuffer;
    private Context context;

    public SearchAdapter(List<Searchforwords.DataBean> articlesBeans, ArrayList<Stringadd> stringBuffer, Context context) {
        this.articlesBeans = articlesBeans;
        this.stringBuffer = stringBuffer;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.search_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        for (int i = 0; i < articlesBeans.size(); i++) {
            //获取视图,视图可以自定义,可以添加自己想要的效果
            TextView label = (TextView) View.inflate(context, R.layout.textview_item, null);
            //获取数据
            final String data = articlesBeans.get(i).getName();
            label.setTextColor(SystemFacade.randomColor());
            //绑定数据
            label.setText(data);
            final int finalI = i;
            label.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.OnClick(finalI);
                }
            });

            holder.mflow.addView(label);

        }
        if (stringBuffer .size()>0) {
            for (int j = 0; j < stringBuffer.size(); j++) {
                String name = stringBuffer.get(j).getName();
                holder.lishi.setText(name);

            }
            holder.ixan.setVisibility(View.GONE);
        } else {
            holder.imageView7.setVisibility(View.GONE);
            holder.ixan.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return articlesBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        FlowLayout mflow;
        TextView mname;
        TextView lishi;
        TextView qingkong;
        TextView ixan;
        ImageView imageView7;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mflow = itemView.findViewById(R.id.naviadaper_flow);
            mname = itemView.findViewById(R.id.naviadaper_name);
            qingkong = itemView.findViewById(R.id.naviadaper_fqingkong);
            lishi = itemView.findViewById(R.id.naviadaper_lishi);
            imageView7 = itemView.findViewById(R.id.imageView7);
            ixan = itemView.findViewById(R.id.ixan);
        }
    }

    private OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener {
        void OnClick(int position);
    }
}
