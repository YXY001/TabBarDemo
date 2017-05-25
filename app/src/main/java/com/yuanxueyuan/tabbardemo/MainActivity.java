package com.yuanxueyuan.tabbardemo;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.yuanxueyuan.tabbar.BaseFragment;
import com.yuanxueyuan.tabbar.TabBar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TabBar.TabBarDelegate{

    private TabBar tabBar;
    private List<String> textList;
    private List<Integer> unSelectResId;
    private List<Integer> selectResId;
    private List<BaseFragment> fragments;
    private List<Integer> colors;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabBar = (TabBar) findViewById(R.id.tab_bar);
        textList = new ArrayList<>();
        unSelectResId = new ArrayList<>();
        selectResId = new ArrayList<>();
        fragments = new ArrayList<>();
        colors = new ArrayList<>();

        initText();
        initUnSelectImg();
        initselectImg();
        initFragment();
        initFragmentManager();
        initTextColor();
        tabBar.setTabBar();
        tabBar.setDelegate(this);
    }


    private void initText(){
        for (int i = 0; i< 3;i++) {
            textList.add(i+"我是谁");
        }
        tabBar.initTxt(textList);
    }

    private void initUnSelectImg(){
        unSelectResId.add(R.mipmap.ic_launcher);
        unSelectResId.add(R.mipmap.ic_launcher);
        unSelectResId.add(R.mipmap.ic_launcher);
        tabBar.initUnSelectResId(unSelectResId);
    }

    private void initselectImg(){
        selectResId.add(R.mipmap.alarm_message_video_read_normal);
        selectResId.add(R.mipmap.alarm_message_video_read_normal);
        selectResId.add(R.mipmap.alarm_message_video_read_normal);
        tabBar.initSelectResId(selectResId);
    }

    private void initFragment(){
        BaseFragment fragment1 = new Fragment1();
        BaseFragment fragment2 = new Fragment2();
        BaseFragment fragment3 = new Fragment3();
        fragments.add(fragment1);
        fragments.add(fragment2);
        fragments.add(fragment3);
        tabBar.initFragment(fragments);
    }

    private void initFragmentManager(){
        fragmentManager = getSupportFragmentManager();
        tabBar.initFragmentManager(fragmentManager);
    }

    private void initTextColor(){
        colors.add(R.color.colorPrimaryDark);
        colors.add(R.color.colorAccent);
        tabBar.initTxtColor(colors);
   }

    @Override
    public void onClick(int index) {
        Log.i("yuanxueyuan",index+"");
    }
}
