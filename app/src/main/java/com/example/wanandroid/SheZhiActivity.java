package com.example.wanandroid;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wanandroid.util.Constants;
import com.example.wanandroid.util.SpUtil;
import com.example.wanandroid.util.UIModeUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SheZhiActivity extends AppCompatActivity {

    @BindView(R.id.cb_cache)
    CheckBox mCbCache;
    @BindView(R.id.cb_img)
    CheckBox mCbImg;
    @BindView(R.id.cb_night)
    CheckBox mCbNight;
    @BindView(R.id.tv_clear)
    TextView mTvClear;
    @BindView(R.id.lv_clear)
    LinearLayout mLvClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_she_zhi);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        int currentNightMode = this.getResources()
                .getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        if (currentNightMode == Configuration.UI_MODE_NIGHT_NO) {
            //判断当前是日间模式
            mCbNight.setChecked(false);
        } else {
            mCbNight.setChecked(true);
        }
        noimg();

        mCbNight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //切换模式
                //切换日夜间模式的时候Activity会重新创建
                //对应的这个碎片也会重建,重建的时候SwitchCompat会设置默认值
                //设置默认值的时候这个回调会被调用
                //if (用户点击的情况下){
                if (buttonView.isPressed()) {
                    //切换并保存模式
                    UIModeUtil.changeModeUI(SheZhiActivity.this);
                    //保存当前碎片的type
                    SpUtil.setParam(Constants.DAY_NIGHT_FRAGMENT_POS,MainActivity.MAIN);
                }
            }
        });
    }

    private void noimg() {
        mCbImg.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SpUtil.setParam("img", isChecked);
            }
        });
    }

}
