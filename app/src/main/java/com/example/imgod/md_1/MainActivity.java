package com.example.imgod.md_1;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout drawlayout_main;
    private NavigationView navigat_view_main;

    private View headView;
    private ImageView img_header_icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
    }

    //初始化视图
    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawlayout_main = (DrawerLayout) findViewById(R.id.drawlayout_main);
        navigat_view_main = (NavigationView) findViewById(R.id.navigat_view_main);
        //我们先用NavigarView拿到Headview,然后才能拿到HeadView中的其他View
        headView = navigat_view_main.getHeaderView(0);
        img_header_icon = (ImageView) headView.findViewById(R.id.img_header_icon);

        //设置NavigarView中菜单的颜色
//        navigat_view_main.setItemTextColor(ColorStateList.valueOf(Color.rgb(0xff,0x66,0x00)));
        //设置NavigarView中菜单的背景
        navigat_view_main.setItemBackgroundResource(R.drawable.menu_item_drawable);
    }


    //初始化事件
    private void initEvent() {
        //把我们的toolBar设置上去
        setSupportActionBar(toolbar);
        //ActionBarDrawerToggle 把toolbar drawLayout关联起来
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawlayout_main, toolbar, R.string.open, R.string.close);
        actionBarDrawerToggle.syncState();//同步状态
        drawlayout_main.addDrawerListener(actionBarDrawerToggle);
        //给NavigationView设置一个item选择事件,
        navigat_view_main.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.header_menu1:
                        Toast.makeText(MainActivity.this, "点击了Material Design", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.header_menu2:
                        Toast.makeText(MainActivity.this, "点击了我的收藏", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.header_menu3:
                        Toast.makeText(MainActivity.this, "点击了关于", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Log.e("main", "default");
                        break;
                }
                item.setChecked(true);
                drawlayout_main.closeDrawers();
                return true;
            }
        });

        //给侧滑菜单中的headview中的icon添加一个点击事件
        img_header_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "点击了Header View头像", Toast.LENGTH_SHORT).show();
                drawlayout_main.closeDrawer(GravityCompat.START);//关闭侧滑
            }
        });
    }


    //back键点击事件,如果抽屉是打开的.那就先关上
    @Override
    public void onBackPressed() {
        if (drawlayout_main.isDrawerOpen(GravityCompat.START)) {
            drawlayout_main.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
