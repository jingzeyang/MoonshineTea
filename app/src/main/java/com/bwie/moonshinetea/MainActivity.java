package com.bwie.moonshinetea;

import android.annotation.SuppressLint;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.bwie.moonshinetea.fragment.Fragment_tab;
import com.bwie.moonshinetea.utils.NewsFragmentTabHost;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    NewsFragmentTabHost mTabHost;
    TextView title;

    @DrawableRes
    private int mImagers[] = {
            R.drawable.tab_center,
            R.drawable.tab_assistant,
            R.drawable.tab_contest,
            R.drawable.tab_counter
    };
    //标题
    private String mFragmentTags[] = {
            "商品",
            "优惠",
            "购物车",
            "我的"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        title = (TextView) findViewById(R.id.title);
        mTabHost = (NewsFragmentTabHost) findViewById(R.id.tanhost);
        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
        mTabHost.getTabWidget().setDividerDrawable(null);
        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                title.setText(s);
            }
        });
        Bundle bundle = null;
        for (int i = 0; i < mImagers.length; i++) {
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mFragmentTags[i]).setIndicator(getImageView(i, mFragmentTags[i]));
            bundle = new Bundle();
            mTabHost.addTab(tabSpec, Fragment_tab.class, bundle);
            // 设置Tab按钮的背景
            mTabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.color.pedo_actionbar_bkg);

        }

    }

    private View getImageView(int index, String str) {
        @SuppressLint("InflateParams")
        View view = getLayoutInflater().inflate(R.layout.tab_indicator, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.tab_iv_image);
        TextView label = (TextView) view.findViewById(R.id.tab_label);
        label.setText(str);
        imageView.setImageResource(mImagers[index]);
        return view;
    }
}
