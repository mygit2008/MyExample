package example.com.myexample.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;
import com.zhy.autolayout.AutoRelativeLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import example.com.myexample.MyApp;
import example.com.myexample.R;
import example.com.myexample.activity.MapActivity;
import example.com.myexample.activity.SousuoActivity;
import example.com.myexample.adapter.RestaurantAdapter;
import example.com.myexample.bean.RestaurantBean;
import example.com.myexample.presenter.RestaurantPresenter;
import example.com.myexample.view.IRestaurantView;

/**
 * @author zhangjunyou
 * @date 2018/7/6
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */

public class SubscribeF extends Fragment implements View.OnClickListener, IRestaurantView {
    private View view;
    /**
     * 地址
     */
    private TextView mAddress;
    private AutoRelativeLayout mSousuo;
    private RecyclerView mSrlv;
    private RestaurantPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.subscribe_layout, container, false);
        EventBus.getDefault().register(this);
        initView(view);
        presenter = new RestaurantPresenter(this);
        presenter.getRestaurant();
        return view;
    }



    @Subscribe
    public void onEventMainThread(String string) {
        mAddress.setText(string);
    }

    private void initView(View view) {
        mAddress = (TextView) view.findViewById(R.id.address);
        mAddress.setOnClickListener(this);
        mSousuo = (AutoRelativeLayout) view.findViewById(R.id.sousuo);
        mSousuo.setOnClickListener(this);
        mSrlv = (RecyclerView) view.findViewById(R.id.srlv);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.address:
                Intent intent1 = new Intent(MyApp.context, MapActivity.class);
                getActivity().startActivity(intent1);
                break;
            case R.id.sousuo:
                Intent intent = new Intent(MyApp.context, SousuoActivity.class);
                getActivity().startActivity(intent);
                break;
        }
    }

    @Override
    public void success(RestaurantBean restaurantBean) {
        List<RestaurantBean.DataBean> data = restaurantBean.getData();
        mSrlv.setLayoutManager(new LinearLayoutManager(MyApp.context));
        RestaurantAdapter adapter = new RestaurantAdapter(MyApp.context, data);
        mSrlv.setAdapter(adapter);
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
