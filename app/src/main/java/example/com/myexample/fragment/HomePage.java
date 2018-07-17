package example.com.myexample.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

import example.com.myexample.MyApp;
import example.com.myexample.R;
import example.com.myexample.activity.ClassfiyActivity;
import example.com.myexample.adapter.TitleFragmentPagerAdapter;

/**
 * @author zhangjunyou
 * @date 2018/7/6
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */

public class HomePage extends Fragment implements View.OnClickListener {
    private View view;
    private ImageView classfiy;
    private TabLayout tab;
    private ViewPager viewpager;
    private String[] titles = new String[]{"热销", "招牌", "主食", "小吃", "饮品"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homepage_layout, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            fragments.add(new BlankFragment());
        }
        TitleFragmentPagerAdapter adapter = new TitleFragmentPagerAdapter(getFragmentManager(), fragments, titles);
        viewpager.setAdapter(adapter);
        tab.setupWithViewPager(viewpager);
    }

    @Override
    public void onResume() {
        super.onResume();

        MobclickAgent.onResume(MyApp.context);
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(MyApp.context);
    }
    private void initView(View view) {
        classfiy = (ImageView) view.findViewById(R.id.classfiy);
        classfiy.setOnClickListener(this);
        tab = (TabLayout) view.findViewById(R.id.tab);
        viewpager = (ViewPager) view.findViewById(R.id.viewpager);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.classfiy:
                Intent intent = new Intent(MyApp.context, ClassfiyActivity.class);
                getActivity().startActivity(intent);
                break;
        }
    }
}
