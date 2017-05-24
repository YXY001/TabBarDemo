package com.yuanxueyuan.tabbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.List;


/**
 * @author yuanxueyuan
 * @Description: fragment的adapter
 * @date 2017/5/18  17:20
 */

public class FragmentAdapter extends FragmentPagerAdapter {
    private List<BaseFragment> list_fragment;
    public FragmentAdapter(FragmentManager fm, List<BaseFragment> list) {
        super(fm);
        list_fragment = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list_fragment.get(position);
    }

    @Override
    public int getCount() {
        return list_fragment.size();
    }

    //防止重新销毁视图
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //如果注释这行，那么不管怎么切换，page都不会被销毁
        super.destroyItem(container, position, object);
    }
}
