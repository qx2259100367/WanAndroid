package com.example.wanandroid.view;

import com.example.wanandroid.base.BaseView;
import com.example.wanandroid.bean.SearChBean;
import com.example.wanandroid.bean.Searchforwords;

/**
 * Created by 裘翔 on 2019/10/28.
 */

public interface SearChView extends BaseView{
    void onSuccess(Searchforwords Searchforwords);
    void onFailed(String str);
}
