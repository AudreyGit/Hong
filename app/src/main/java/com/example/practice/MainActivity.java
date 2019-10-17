package com.example.practice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.NavigationMenu;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.drawable.DrawableWrapper;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{
    //tab标签切换
    private FrameLayout index_framlayout;
    private RadioGroup index_radiogroup;
    private RadioButton index_radiobutton1;
    private RadioButton index_radiobutton2;
    private RadioButton index_radiobutton3;
    static final int NUM_ITMS=3;//一共3个fragment
    //侧滑页面
    private DrawerLayout drawerLayout;
    //radiobutton的值显示textview中
    private String sexname;
    private TextView tv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //tab标签
        initView();//获取控件实现tab切换效果
        Toolbar toolbar=(Toolbar)findViewById(R.id.drawer_layout_rl_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.container);
        ActionBar actionBar = getSupportActionBar();    //得到ActionBar实例
        if (actionBar != null){
            //显示导航按钮
            actionBar.setDisplayHomeAsUpEnabled(true);
            //设置导航按钮图片
            actionBar.setHomeAsUpIndicator(R.mipmap.index_spinner);
        }
    }

    //radiogroup fragment实现tab标签切换功能------------------------------------------------
    protected void initView(){
        index_framlayout = (FrameLayout) findViewById(R.id.index_framelayout); //tab上方的区域
        index_radiogroup = (RadioGroup) findViewById(R.id.index_radiogroup);  //底部的四个tab
        index_radiobutton1 = (RadioButton) findViewById(R.id.index_radiobutton);
        index_radiobutton2 = (RadioButton) findViewById(R.id.class1_radiobutton);
        index_radiobutton3 = (RadioButton) findViewById(R.id.person_radiobutton);
        tv1=(TextView)findViewById(R.id.index_textview);

        index_radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
          @Override
          public void onCheckedChanged(RadioGroup group, int checkedId) {
              //将radiobutton中的值显示到toolbar的textview中
             if(index_radiobutton1.getId() == checkedId){
                 tv1.setText("");
                 RadioButton rb=(RadioButton)findViewById(index_radiogroup.getCheckedRadioButtonId());
                 sexname=rb.getText().toString();
                 tv1.setText(sexname);
             }else if(index_radiobutton2.getId() == checkedId){
                 tv1.setText("");
                 RadioButton rb=(RadioButton)findViewById(index_radiogroup.getCheckedRadioButtonId());
                 sexname=rb.getText().toString();
                 tv1.setText(sexname);
             }else if(index_radiobutton3.getId() == checkedId){
                 tv1.setText("");
                 RadioButton rb=(RadioButton)findViewById(index_radiogroup.getCheckedRadioButtonId());
                 sexname=rb.getText().toString();
                 tv1.setText(sexname);
             }
            int index = 0;
             switch (checkedId) {
                 case R.id.index_radiobutton:
                        index = 0;
                        break;
                 case R.id.class1_radiobutton:
                         index = 1;
                         break;
                 case R.id.person_radiobutton:
                         index = 2;
                        break;
                 }
                 //通过fragments这个adapter还有index来替换帧布局中的内容
            Fragment fragment = (Fragment) fragments.instantiateItem(index_framlayout,index);
            //一开始将帧布局中 的内容设置为第一个
            fragments.setPrimaryItem(index_framlayout, 0, fragment);
            fragments.finishUpdate(index_framlayout);
          }
        });
    }
    //第一次启动，让index_radiobutton这个radiobutton处于选中状态。
    // 在这之前，先要在布局文件中设置其他的某一个radiobutton
    // 属性为android:checked="true"，才会出发下面的这个check方法切换到index_radiobutton
    @Override
    protected void onStart() {
        super.onStart();
        index_radiogroup.check(R.id.index_radiobutton);
    }
    //用adapter来管理四个Fragment界面的变化。注意，我这里用的Fragment都是v4包里面的
    FragmentStatePagerAdapter fragments=new FragmentStatePagerAdapter(getSupportFragmentManager()   ) {
        @Override
        public int getCount() {
            return NUM_ITMS;//一共有3个Fragment
        }
        //进行Fragment的初始化
        @Override
        public Fragment getItem(int i) {
            Fragment fragment = null;
            switch (i) {
                case 0://index
                    fragment = new index_fragment();
                    break;
                case 1://class1
                    fragment = new class1_fragment();
                    break;
                case 2://person
                    fragment = new person_fragment();
                    break;
                default:
                    new index_fragment();
                    break;
            }
            return fragment;
        }
    };
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //显示侧滑菜单
                drawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

