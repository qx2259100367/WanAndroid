package com.example.wanandroid.view;

import com.example.wanandroid.base.BaseView;
import com.example.wanandroid.base.SearcheData;

/**
 * Created by 裘翔 on 2019/10/29.
 */

public interface SearCherView extends BaseView{
    void onSuccess(SearcheData searcheData);
    void onFailed(String str);
}
