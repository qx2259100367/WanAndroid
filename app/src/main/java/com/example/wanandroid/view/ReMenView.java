package com.example.wanandroid.view;

import com.example.wanandroid.base.BaseView;
import com.example.wanandroid.bean.ReMenBean;

/**
 * Created by 裘翔 on 2019/10/28.
 */

public interface ReMenView extends BaseView{
    void onSuccess(ReMenBean reMenBean);
    void onFailed(String str);
}
