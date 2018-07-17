package example.com.myexample.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.umeng.analytics.MobclickAgent;

import example.com.myexample.MyApp;
import example.com.myexample.R;

/**
 * @author zhangjunyou
 * @date 2018/7/12
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */

public class PingJiaFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.canping_layout,container,false);
        return view;
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
}
