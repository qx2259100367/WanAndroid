package com.example.wanandroid.util;

import android.os.Environment;

import com.example.wanandroid.BaseApp;

import java.io.File;

public interface Constants {
    boolean isDebug = true;


    String PATH_SDCARD = Environment.getExternalStorageDirectory().getAbsolutePath() +
            File.separator + "codeest" + File.separator + "GeekNews";

    String FILE_PROVIDER_AUTHORITY="com.baidu.geek.fileprovider";

    //网络缓存的地址
    String PATH_DATA = BaseApp.getBaseApp().getCacheDir().getAbsolutePath() +
            File.separator + "data";

    String PATH_CACHE = PATH_DATA + "/NetCache";
    String DATA = "data";
    //夜间模式
    String MODE = "mode";
    String NIGHT_CURRENT_FRAG_POS = "fragment_pos";
    //保存设置日夜间模式时碎片的position
    String DAY_NIGHT_FRAGMENT_POS = "day_night_fragment_pos";
}
