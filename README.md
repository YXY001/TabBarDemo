TabBarDemo
==
开发者使用TabBar 可以很方便的实现仿照`微信` 或者`QQ`的下方的tab页，只需要传递几个参数即可完成

## 使用方法
* 1、xml中使用
```
<com.yuanxueyuan.tabbar.TabBar
android:id="@+id/tab_bar"
android:layout_width="match_parent"
android:layout_height="match_parent"/>
```
* 2、代码中实现
  * 1.主要就是设置数据
    * `initTxt`设置tabBar下方的文字
    * `initUnSelectResId`设置未选中时的图片
    * `initSelectResId`设置选中时的图片
    * `initFragment`设置每个界面的fragment
      * `注意` fragment必须要继承BaseFragment，下方将介绍一下BaseFragment
    * `initFragmentManager`设置fragment的管理器
    * `initTxtColor`设置文字的颜色，list中颜色值只需要两个，其中第一个为未选中时的颜色，另一个为选中时的颜色
  * 2.`TabBarDelegate`实现此类
    * 重写`onClick`方法，其中参数index表示当前选中的tab页的下标，从0开始，可以自己进行处理点击tab页的事件
* 3、BaseFragment介绍：
  * 1.使用方法：\
    继承BaseFragment，然后重写`onLazyLoadOnce`和`onVisibleToUser`和 `onInvisibleToUser`方法
  * 2.onLazyLoadOnce：\
    只加载一次，可以节省流量
  * 3.onVisibleToUser： \
    每次都调用，fragment显示时调用
  * 4.onInvisibleToUser: \
    每次都调用，当fragment不显示时，
    

    
    
