package com.example.wanandroid.view;

import com.example.wanandroid.base.BaseView;
import com.example.wanandroid.bean.ShouCangBean;

/**
 * Created by 裘翔 on 2019/10/30.
 */

public interface ShouCangView extends BaseView{
    void onSuccess(ShouCangBean shouCangBean);
    void onFailed(String str);
}
