package com.yuanxueyuan.tabbar;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * @author yuanxueyuan
 * @Description: 展示底层布局
 * @date 2017/5/18 14:31
 */

public class TabBar extends LinearLayout implements View.OnClickListener, ViewPager.OnPageChangeListener {

    //控件
    private Context context;
    private FragmentManager fragmentManager;
    private LinearLayout page1LL, page2LL, page3LL, page4LL, page5LL;
    private TextView page1Txt, page2Txt, page3Txt, page4Txt, page5Txt;
    private ImageView page1Img, page2Img, page3Img, page4Img, page5Img;
    private ViewPager viewPager;
    private FragmentAdapter fragmentAdapter;


    //变量
    /**
     * 数组形式
     */
    private String[] txts;//展示文字
    private
    @DrawableRes
    int[] unSelectResId;//展示不被选中图片
    private
    @DrawableRes
    int[] selectResId;//展示被选中图片
    private Fragment[] fragments;
    private
    @ColorRes
    int[] colorId;//颜色id
    /**
     * 集合形式
     */
    private List<String> txtsList;//展示文字
    private List<Integer> unSelectResIdList;//展示不被选中图片
    private List<Integer> selectResIdList;//展示被选中图片
    private List<BaseFragment> fragmentList;
    private List<Integer> colorIdList;//颜色id

    private int size;//各个控件的大小

    //组件id
    private int [] pageLLId = {R.id.ll_page1, R.id.ll_page2, R.id.ll_page3, R.id.ll_page4, R.id.ll_page5};


    public TabBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        View view = LayoutInflater.from(context).inflate(R.layout.content_main, this);
        initView(view);
    }




    /**
     * @author yuanxueyuan
     * @Title: initView
     * @Description: 初始化布局
     * @date 2017/5/18 14:42
     */
    private void initView(View view) {
        //LinearLayout
        page1LL = (LinearLayout) view.findViewById(R.id.ll_page1);
        page2LL = (LinearLayout) view.findViewById(R.id.ll_page2);
        page3LL = (LinearLayout) view.findViewById(R.id.ll_page3);
        page4LL = (LinearLayout) view.findViewById(R.id.ll_page4);
        page5LL = (LinearLayout) view.findViewById(R.id.ll_page5);

        //TextView
        page1Txt = (TextView) view.findViewById(R.id.txt_page1);
        page2Txt = (TextView) view.findViewById(R.id.txt_page2);
        page3Txt = (TextView) view.findViewById(R.id.txt_page3);
        page4Txt = (TextView) view.findViewById(R.id.txt_page4);
        page5Txt = (TextView) view.findViewById(R.id.txt_page5);

        //ImageView
        page1Img = (ImageView) view.findViewById(R.id.img_page1);
        page2Img = (ImageView) view.findViewById(R.id.img_page2);
        page3Img = (ImageView) view.findViewById(R.id.img_page3);
        page4Img = (ImageView) view.findViewById(R.id.img_page4);
        page5Img = (ImageView) view.findViewById(R.id.img_page5);

        //viewPager
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);

        //set listener
        setListener();

    }

    public void setTabBar(){
        //设置tabBar
        if (txts != null && txts.length > 0) {

        } else if (txtsList != null && txtsList.size() > 0){
            setTabBar(this.txtsList, this.unSelectResIdList, this.selectResIdList, this.fragmentList);
        }
    }

    /**
     * @author yuanxueyuan
     * @Title: initTxt
     * @Description: 初始化文字
     * @date 2017/5/18 21:17
     */
    public void initTxt(String[] txts) {
        this.txts = txts;
    }


    /**
     * @author yuanxueyuan
     * @Title: initTxt
     * @Description: 初始化文字
     * @date 2017/5/18 21:17
     */
    public void initTxt(List<String> txtsList) {
        this.txtsList = txtsList;
    }

    /**
     * @author yuanxueyuan
     * @Title: initUnSelectResId
     * @Description: 初始化未被选中的图片资源
     * @date 2017/5/18 21:17
     */
    public void initUnSelectResId(@DrawableRes int[] unSelectResId) {
        this.unSelectResId = unSelectResId;
    }

    /**
     * @author yuanxueyuan
     * @Title: initUnSelectResId
     * @Description: 初始化未被选中的图片资源
     * @date 2017/5/18 21:17
     */
    public void initUnSelectResId(List<Integer> unSelectResIdList) {
        this.unSelectResIdList = unSelectResIdList;
    }

    /**
     * @author yuanxueyuan
     * @Title: initSelectResId
     * @Description: 初始化被选中的图片资源
     * @date 2017/5/18 21:17
     */
    public void initSelectResId(@DrawableRes int[] selectResId) {
        this.selectResId = selectResId;
    }

    /**
     * @author yuanxueyuan
     * @Title: initSelectResId
     * @Description: 初始化被选中的图片资源
     * @date 2017/5/18 21:17
     */
    public void initSelectResId(List<Integer> selectResIdList) {
        this.selectResIdList = selectResIdList;
    }

    /**
     * @author yuanxueyuan
     * @Title: initSelectResId
     * @Description: 初始化被选中的图片资源
     * @date 2017/5/18 21:17
     */
    public void initFragment(List<BaseFragment> fragmentsList) {
        this.fragmentList = fragmentsList;
    }

    /**
     * @author yuanxueyuan
     * @Title: initSelectResId
     * @Description: 初始化被选中的图片资源
     * @date 2017/5/18 21:17
     */
    public void initFragment(BaseFragment[] fragments) {
        this.fragments = fragments;
        int size = this.fragments.length;
        for (int i = 0; i < size; i++) {
            if (fragments[i] == null) {
                continue;
            }
            fragmentList.add(fragments[i]);
        }
    }

    /**
     * @author yuanxueyuan
     * @Title: initTxtColor
     * @Description: 初始化颜色值, 第一个颜色表示未选中的颜色值，第二个表示选中的颜色值
     * @date 2017/5/18 22:38
     */
    public void initTxtColor(@ColorRes int[] colorId) {
        this.colorId = colorId;
    }

    /**
     * @author yuanxueyuan
     * @Title: initTxtColor
     * @Description: 初始化颜色值, 第一个颜色表示未选中的颜色值，第二个表示选中的颜色值
     * @date 2017/5/18 22:38
     */
    public void initTxtColor(List<Integer> colorId) {
        this.colorIdList = colorId;
    }


    /**
    * @author  yuanxueyuan
    * @Title:  initFragmentManager
    * @Description: 初始化fragmentManager
    * @date 2017/5/24 11:39
    */
    public void initFragmentManager(FragmentManager fragmentManager){
        this.fragmentManager = fragmentManager;
    }
    
    /** 
    * @author  yuanxueyuan
    * @Title:  setListener
    * @Description: 设置监听事件
    * @date 2017/5/24 13:24
    */
    private void setListener(){
        page1LL.setOnClickListener(this);
        page2LL.setOnClickListener(this);
        page3LL.setOnClickListener(this);
        page4LL.setOnClickListener(this);
        page5LL.setOnClickListener(this);
        viewPager.addOnPageChangeListener(this);
    }

    /**
     * @author yuanxueyuan
     * @Title: verificationValues
     * @Description: 验证参数的大小是否一致
     * @date 2017/5/18 22:02
     */
    private boolean verificationValues(int txtsLength, int unSelectResIdLength, int selectResIdLength, int fragmentsLength) {
        if (txtsLength != unSelectResIdLength || txtsLength != selectResIdLength || txtsLength != fragmentsLength) {
            Toast.makeText(context, R.string.values_un, Toast.LENGTH_SHORT).show();
            return false;
        }
        size = txtsLength;
        return true;
    }

    /**
     * @author yuanxueyuan
     * @Title: setTabBar
     * @Description: 设置下方菜单栏的个数
     * @date 2017/5/18 14:54
     */
    private void setTabBar(List<String> txtsList, List<Integer> unSelectResIdList, List<Integer> selectResIdList, List<BaseFragment> fragmentList) {

        //先进行验证
        if (txtsList == null || txtsList.size() <= 0 || unSelectResIdList == null || unSelectResIdList.size() <= 0 || selectResIdList == null || selectResIdList.size() <= 0) {
            Log.e(Constant.ClassName, "have null");
            return;
        }
        boolean verification = verificationValues(txtsList.size(), unSelectResIdList.size(), selectResIdList.size(), fragmentList.size());

        if (verification) {
            fragmentAdapter = new FragmentAdapter(fragmentManager,fragmentList);
            viewPager.setAdapter(fragmentAdapter);
            //关闭预加载，默认一次只加载一个Fragment
            viewPager.setOffscreenPageLimit(3);
            setSelectTabBar(1);
            viewPager.setCurrentItem(0);
        }
    }


    /**
     * @author yuanxueyuan
     * @Title: setDeFaultTabBar
     * @Description: 设置默认的TabBar
     * @date 2017/5/18 22:45
     */
    private void setDeFaultTabBar() {

        if (colorIdList == null || colorIdList.size() <= 0) {
            Log.e(Constant.ClassName, "colorIdList == null");
            return;
        }
        int color = colorIdList.get(0);

        if (txtsList == null || txtsList.size() < 0) {
            Log.e(Constant.ClassName, "txtList == null");
            return;
        }
        int txtSize = txtsList.size();

        switch (txtSize) {
            case Constant.ONE_TAB_HOST:
                Toast.makeText(context, R.string.min_three, Toast.LENGTH_SHORT).show();
                break;
            case Constant.TWO_TAB_HOST:
                Toast.makeText(context, R.string.min_three, Toast.LENGTH_SHORT).show();
                break;
            case Constant.THREE_TAB_HOST:
                //first
                if (txtsList.get(0) == null) {
                    txtsList.set(0, "");
                }
                page1Txt.setText(txtsList.get(0));
                page1Txt.setTextColor(ContextCompat.getColor(context,color));
                page1Img.setImageResource(unSelectResIdList.get(0));

                //second
                if (txtsList.get(1) == null) {
                    txtsList.set(1, "");
                }
                page2Txt.setText(txtsList.get(1));
                page2Txt.setTextColor(ContextCompat.getColor(context,color));
                page2Img.setImageResource(unSelectResIdList.get(1));

                //third
                if (txtsList.get(2) == null) {
                    txtsList.set(2, "");
                }
                page3Txt.setText(txtsList.get(2));
                page3Txt.setTextColor(ContextCompat.getColor(context,color));
                page3Img.setImageResource(unSelectResIdList.get(2));

                //Fourth、Fifth，hidden
                page4LL.setVisibility(GONE);
                page5LL.setVisibility(GONE);

                //fragment
                break;
            case Constant.FOUR_TAB_HOST:
                //first
                if (txtsList.get(0) == null) {
                    txtsList.set(0, "");
                }
                page1Txt.setText(txtsList.get(0));
                page1Txt.setTextColor(ContextCompat.getColor(context,color));
                page1Img.setImageResource(unSelectResIdList.get(0));

                //second
                if (txtsList.get(1) == null) {
                    txtsList.set(1, "");
                }
                page2Txt.setText(txtsList.get(1));
                page2Txt.setTextColor(ContextCompat.getColor(context,color));
                page2Img.setImageResource(unSelectResIdList.get(1));

                //third
                if (txtsList.get(2) == null) {
                    txtsList.set(2, "");
                }
                page3Txt.setText(txtsList.get(2));
                page3Txt.setTextColor(ContextCompat.getColor(context,color));
                page3Img.setImageResource(unSelectResIdList.get(2));

                //Fourth
                if (txtsList.get(3) == null) {
                    txtsList.set(3, "");
                }
                page4Txt.setText(txtsList.get(3));
                page4Txt.setTextColor(ContextCompat.getColor(context,color));
                page4Img.setImageResource(unSelectResIdList.get(3));

                //Fifth hidden
                page5LL.setVisibility(GONE);
                break;
            case Constant.FIVE_TAB_HOST:
                //first
                if (txtsList.get(0) == null) {
                    txtsList.set(0, "");
                }
                page1Txt.setText(txtsList.get(0));
                page1Txt.setTextColor(ContextCompat.getColor(context,color));
                page1Img.setImageResource(unSelectResIdList.get(0));

                //second
                if (txtsList.get(1) == null) {
                    txtsList.set(1, "");
                }
                page2Txt.setText(txtsList.get(1));
                page2Txt.setTextColor(ContextCompat.getColor(context,color));
                page2Img.setImageResource(unSelectResIdList.get(1));

                //third
                if (txtsList.get(2) == null) {
                    txtsList.set(2, "");
                }
                page3Txt.setText(txtsList.get(2));
                page3Txt.setTextColor(ContextCompat.getColor(context,color));
                page3Img.setImageResource(unSelectResIdList.get(2));

                //Fourth
                if (txtsList.get(3) == null) {
                    txtsList.set(3, "");
                }
                page4Txt.setText(txtsList.get(3));
                page4Txt.setTextColor(ContextCompat.getColor(context,color));
                page4Img.setImageResource(unSelectResIdList.get(3));

                //Fifth
                if (txtsList.get(4) == null) {
                    txtsList.set(4, "");
                }
                page5Txt.setText(txtsList.get(4));
                page5Txt.setTextColor(ContextCompat.getColor(context,color));
                page5Img.setImageResource(unSelectResIdList.get(4));
                break;
            default:
                Toast.makeText(context, R.string.max_five, Toast.LENGTH_SHORT).show();
                break;
        }
    }

    /**
     * @param index
     *         下标
     * @author yuanxueyuan
     * @Title: setSelectTabBar
     * @Description: 设置选中的tabBar
     * @date 2017/5/18 22:46
     */
    public void setSelectTabBar(int index) {

        setDeFaultTabBar(); //先将全部设置默认的tabBar

        if (index > txtsList.size()) {
            Log.e(Constant.ClassName,"index > txtsList.size()");
            return;
        }

        if (colorIdList == null || colorIdList.size() <= 0) {
            Log.e(Constant.ClassName, "colorIdList == null");
            return;
        }

        int size = colorIdList.size();
        int color;
        if (size == 1) {
            color = colorIdList.get(0);
        } else {
            color = colorIdList.get(1);
        }

        switch (index) {
            case Constant.ONE_TAB_HOST:
                page1Img.setImageResource(selectResIdList.get(0));
                page1Txt.setTextColor(ContextCompat.getColor(context,color));
                break;
            case Constant.TWO_TAB_HOST:
                page2Img.setImageResource(selectResIdList.get(1));
                page2Txt.setTextColor(ContextCompat.getColor(context,color));
                break;
            case Constant.THREE_TAB_HOST:
                page3Img.setImageResource(selectResIdList.get(2));
                page3Txt.setTextColor(ContextCompat.getColor(context,color));
                break;
            case Constant.FOUR_TAB_HOST:
                page4Img.setImageResource(selectResIdList.get(3));
                page4Txt.setTextColor(ContextCompat.getColor(context,color));
                break;
            case Constant.FIVE_TAB_HOST:
                page5Img.setImageResource(selectResIdList.get(4));
                page5Txt.setTextColor(ContextCompat.getColor(context,color));
                break;
            default:
                break;
        }
    }

    /**
    * @author  yuanxueyuan
    * @Title:  onClick
    * @Description: 点击事件
    * @date 2017/5/24 14:21
     * @param
    */
    @Override
    public void onClick(View view) {
        int id = view.getId();
        for (int i = 0; i < pageLLId.length; i++) {
            if (id == pageLLId [i]) {
                setSelectTabBar(i + 1);
                viewPager.setCurrentItem(i);
            }
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    /**
    * @author  yuanxueyuan
    * @Title:  onPageSelected
    * @Description: 滑动时的监听
    * @date 2017/5/24 14:22
    */
    @Override
    public void onPageSelected(int position) {
        setSelectTabBar(position + 1);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
