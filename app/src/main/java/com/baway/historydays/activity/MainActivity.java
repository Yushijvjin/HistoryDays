package com.baway.historydays.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baway.historydays.R;
import com.baway.historydays.fragment.AboutFragment;
import com.baway.historydays.fragment.CollectFragment;
import com.baway.historydays.fragment.GirlFragment;
import com.baway.historydays.fragment.HistoryFragment;
import com.baway.historydays.fragment.ShowFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.ArrayList;

public class MainActivity extends AutoLayoutActivity implements View.OnClickListener {

    private LinearLayout menu_one;
    private LinearLayout menu_two;
    private LinearLayout menu_three;
    private LinearLayout menu_four;
    private HistoryFragment historyFragment;
    private GirlFragment girlFrigment;
    private FragmentManager manager;
    private CollectFragment collectFragment;
    private AboutFragment aboutFragment;
    ArrayList<ShowFragment> list_fragment = new ArrayList<>();

    private FragmentTransaction transaction;
    private ImageView title_image;
    private TextView title_text;
    private SlidingMenu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        main_initView();


    }

    private void main_initView() {
        title_image = (ImageView) findViewById(R.id.title_image);
        title_text = (TextView) findViewById(R.id.title_text);

        title_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menu.toggle();
            }
        });
        initSlidingMenu();
    }

    private void initSlidingMenu() {
        // 初始化SlidingMenu对象
        menu = new SlidingMenu(this);
        // 设置侧滑方式为左侧侧滑
        menu.setMode(SlidingMenu.LEFT);
        /*
         * 设置拖拽模式 SlidingMenu.TOUCHMODE_FULLSCREEN全屏触摸有效
         * SlidingMenu.TOUCHMODE_MARGIN 拖拽边缘有效 SlidingMenu.TOUCHMODE_NONE
         * 不响应触摸事件
         */
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        // 设置阴影的宽度
        menu.setShadowWidthRes(R.dimen.shadow_width);
        // 设置阴影的图片
        menu.setShadowDrawable(R.mipmap.ic_launcher);
        // 设置sldingMenu的剩余大小---=内容显示页对应的dp大小
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        // 设置滑动时的渐变程度
        menu.setFadeDegree(0.35f);
        // 使SlidingMenu附加在Activity右边
        // SlidingMenu.SLIDING_CONTENT 将侧滑栏设置为在内容位置
        // SlidingMenu.SLIDING_WINDOW 将侧滑栏设置为在整个窗口呈现
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        // 设置SlidingMenu关联的布局
        menu.setMenu(R.layout.menu);
        // 在SlidingMenu关联布局中查询控件

        initView(menu);
    }

    private void addFragment(int position) {
        transaction = manager.beginTransaction();
        //将fragment 加载进来
        for (int i = 0; i < list_fragment.size(); i++) {
            if (i != position) {
                transaction.hide(list_fragment.get(i).fragment);
            }
        }
        if (list_fragment.get(position).statue == 0) {
            transaction.add(R.id.fram_main_content, list_fragment.get(position).fragment);
            list_fragment.get(position).statue = 1;
            transaction.show(list_fragment.get(position).fragment);
        } else {
            transaction.show(list_fragment.get(position).fragment);
        }
        transaction.commit();

    }

    private void addList() {

        for (int i = 0; i < 4; i++) {
            ShowFragment showFragment = new ShowFragment();
            switch (i) {
                case 0:
                    showFragment.fragment = historyFragment;
                    break;
                case 1:
                    showFragment.fragment = girlFrigment;
                    break;
                case 2:
                    showFragment.fragment = collectFragment;
                    break;
                case 3:
                    showFragment.fragment = aboutFragment;
                    break;
            }
            list_fragment.add(showFragment);
        }
    }

    private void initView(SlidingMenu menu) {


        menu_one = (LinearLayout) menu.findViewById(R.id.menu_one);
        menu_two = (LinearLayout) menu.findViewById(R.id.menu_two);
        menu_three = (LinearLayout) menu.findViewById(R.id.menu_three);
        menu_four = (LinearLayout) menu.findViewById(R.id.menu_four);

        manager = getSupportFragmentManager();
        historyFragment = new HistoryFragment();
        girlFrigment = new GirlFragment();
        collectFragment = new CollectFragment();
        aboutFragment = new AboutFragment();

        //添加到集合中
        addList();
        addFragment(0);

        menu_one.setOnClickListener(this);
        menu_two.setOnClickListener(this);
        menu_three.setOnClickListener(this);
        menu_four.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {


        switch (view.getId()) {
            case R.id.menu_one://
                title_text.setText("历史上的今天");
                addFragment(0);
                menu.toggle();
                break;
            case R.id.menu_two://

                title_text.setText("妹纸");
                addFragment(1);
                menu.toggle();
                break;
            case R.id.menu_three://
                title_text.setText("收藏");

                addFragment(2);
                menu.toggle();
                break;
            case R.id.menu_four://
                title_text.setText("关于");

                addFragment(3);
                menu.toggle();
                break;
        }


    }

    protected void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("确定要退出吗?");
        builder.setTitle("提示");
        builder.setPositiveButton("确认",
                new android.content.DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        MainActivity.this.finish();
                    }
                });
        builder.setNegativeButton("取消",
                new android.content.DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.create().show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            dialog();
            return false;
        }
        return false;
    }

}
