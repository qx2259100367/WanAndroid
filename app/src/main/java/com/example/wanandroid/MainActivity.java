package com.example.wanandroid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wanandroid.Adaper.MyFragmentAdaper;
import com.example.wanandroid.Fragment.Projectfragment;
import com.example.wanandroid.Fragment.knowfragment;
import com.example.wanandroid.Fragment.mainfragment;
import com.example.wanandroid.Fragment.navgationfragment;
import com.example.wanandroid.Fragment.office_accounts_fragment;
import com.example.wanandroid.util.Constants;
import com.example.wanandroid.util.SpUtil;
import com.example.wanandroid.util.ToastUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.Toolbar)
    Toolbar mToolbar;
    @BindView(R.id.MyVp)
    ViewPager mMyVp;
    @BindView(R.id.myTab)
    TabLayout mMyTab;
    @BindView(R.id.Nav)
    NavigationView mNav;
    @BindView(R.id.Dr)
    DrawerLayout mDr;
    @BindView(R.id.Toor)
    TextView mToor;
    private ArrayList<Fragment> fragments;
    private mainfragment mainfragment;
    private knowfragment knowfragment;
    private office_accounts_fragment office_accounts_fragment;
    private navgationfragment navgationfragment;
    private Projectfragment projectfragment;
    private MyFragmentAdaper fragmentAdaper;
    public static final int MAIN = 5;
    private int id;
    private int position;
    public int mLastFragmentPosition;
    private MenuItem mItem;
    private FragmentManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initToolbar();
        initFragment();
        initLisenter();

    }

    private void initLisenter() {
        mNav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                switch (item.getItemId()) {
                    case R.id.nav_item_wan_android:
                        startActivity(new Intent(MainActivity.this,MainActivity.class));
                        break;
                    case R.id.nav_item_my_collect:
                        startActivity(new Intent(MainActivity.this, ShouCangActivity.class));
                        break;
                    case R.id.nav_item_setting:
                        startActivity(new Intent(MainActivity.this,SheZhiActivity.class));
                        break;
                    case R.id.nav_item_about_us:
                        startActivity(new Intent(MainActivity.this,GuanYuActivity.class));
                        break;
                    case R.id.nav_debark:
//                        SharedPreferences mysp = getSharedPreferences("mysp", Context.MODE_PRIVATE);
//                        mysp.edit().clear();

                        break;
                }
                return false;
            }
        });
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        mainfragment = new mainfragment();
        knowfragment = new knowfragment();
        office_accounts_fragment = new office_accounts_fragment();
        navgationfragment = new navgationfragment();
        projectfragment = new Projectfragment();
        fragments.add(mainfragment);
        fragments.add(knowfragment);
        fragments.add(office_accounts_fragment);
        fragments.add(navgationfragment);
        fragments.add(projectfragment);
        fragmentAdaper = new MyFragmentAdaper(getSupportFragmentManager(), fragments);
        mMyVp.setAdapter(fragmentAdaper);
        mMyTab.setupWithViewPager(mMyVp);
        mMyTab.getTabAt(0).setIcon(R.drawable.main_select).setText("首页");
        mMyTab.getTabAt(1).setIcon(R.drawable.know_select).setText("知识体系");
        mMyTab.getTabAt(2).setIcon(R.drawable.fficial_select).setText("公众号");
        mMyTab.getTabAt(3).setIcon(R.drawable.navgation_select).setText("导航");
        mMyTab.getTabAt(4).setIcon(R.drawable.projects_select).setText("项目");
    }

    private void initToolbar() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(MainActivity.this,
                mDr, mToolbar, R.string.app_name, R.string.app_name) {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                //获取mDrawerLayout中的第一个子布局，也就是布局中的RelativeLayout
                //获取抽屉的view
                View mContent = mDr.getChildAt(0);
                float scale = 1 - slideOffset;
                float endScale = 0.8f + scale * 0.2f;
                float startScale = 1 - 0.3f * scale;

                //设置左边菜单滑动后的占据屏幕大小
                drawerView.setScaleX(startScale);
                drawerView.setScaleY(startScale);
                //设置菜单透明度
                drawerView.setAlpha(0.6f + 0.4f * (1 - scale));

                //设置内容界面水平和垂直方向偏转量
                //在滑动时内容界面的宽度为 屏幕宽度减去菜单界面所占宽度
                mContent.setTranslationX(drawerView.getMeasuredWidth() * (1 - scale));
                //设置内容界面操作无效（比如有button就会点击无效）
                mContent.invalidate();
                //设置右边菜单滑动后的占据屏幕大小
                mContent.setScaleX(endScale);
                mContent.setScaleY(endScale);
            }
        };
        mDr.addDrawerListener(toggle);
        toggle.syncState();
        mMyTab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                position = tab.getPosition();
                if (position == 0) {
                    mToor.setText("首页");
                } else if (position == 1) {
                    mToor.setText("知识体系");
                } else if (position == 2) {
                    mToor.setText("公众号");
                } else if (position == 3) {
                    mToor.setText("导航");
                } else if (position == 4) {
                    mToor.setText("项目");
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        View headerView = mNav.getHeaderView(0);
        TextView login = headerView.findViewById(R.id.nav_header_login_tv);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //常用网站
            case R.id.action_usage:
                Intent intent = new Intent(this, ReMenActivity.class);
                startActivity(intent);
                break;
            //搜索
            case R.id.action_search:
                Intent intent1 = new Intent(this, SearChActivity.class);
                startActivity(intent1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

//    private void addFragment() {
//        manager = getSupportFragmentManager();
//        FragmentTransaction tr = manager.beginTransaction();
//        tr.add(R.id.MyVp,fragments.get(0));
//        tr.commit();
//    }
}
