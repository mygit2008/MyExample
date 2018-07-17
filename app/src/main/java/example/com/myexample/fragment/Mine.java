package example.com.myexample.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.umeng.analytics.MobclickAgent;
import com.zhy.autolayout.AutoRelativeLayout;

import example.com.myexample.MyApp;
import example.com.myexample.R;
import example.com.myexample.activity.EditProfileActivity;
import example.com.myexample.activity.SettingsActivity;
import example.com.myexample.bean.UserInfo;
import example.com.myexample.presenter.MinePresenter;
import example.com.myexample.view.IMineView;

import static android.R.attr.data;

/**
 * @author zhangjunyou
 * @date 2018/7/6
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */

public class Mine extends Fragment implements View.OnClickListener,IMineView {
    private View view;
    private SimpleDraweeView userIcn;
    /**
     * 用户名
     */
    private TextView userName;
    /**
     * 13813575369
     */
    private TextView userTel;
    /**
     * 0.00
     */
    private TextView price;
    /**
     * 0
     */
    private TextView num;
    /**
     * 2
     */
    private TextView msg;
    private AutoRelativeLayout geren;
    private AutoRelativeLayout xihuan;
    private AutoRelativeLayout you;
    private AutoRelativeLayout shezi;
    private SharedPreferences sp;
    private MinePresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mine_layout, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        sp = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        presenter = new MinePresenter(this);
        userIcn = (SimpleDraweeView) view.findViewById(R.id.user_icn);
        userName = (TextView) view.findViewById(R.id.user_name);
        userTel = (TextView) view.findViewById(R.id.user_tel);
        price = (TextView) view.findViewById(R.id.price);
        num = (TextView) view.findViewById(R.id.num);
        msg = (TextView) view.findViewById(R.id.msg);
        geren = (AutoRelativeLayout) view.findViewById(R.id.geren);
        geren.setOnClickListener(this);
        xihuan = (AutoRelativeLayout) view.findViewById(R.id.xihuan);
        xihuan.setOnClickListener(this);
        you = (AutoRelativeLayout) view.findViewById(R.id.you);
        you.setOnClickListener(this);
        shezi = (AutoRelativeLayout) view.findViewById(R.id.shezi);
        shezi.setOnClickListener(this);
    }
    @Override
    public void onResume() {
        super.onResume();
        if (sp.getBoolean("userfalg", false)) {
            userIcn.setImageURI(Uri.parse(sp.getString("iconur", "")));
            userName.setText(sp.getString("screenname", ""));
        } else {
            if (sp.getString("uid", "0") != null) {
                presenter.getUserInfo(sp.getString("uid", "1"));
            }
        }
        MobclickAgent.onResume(MyApp.context);
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(MyApp.context);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.geren:
                Intent intent = new Intent(MyApp.context, EditProfileActivity.class);
                startActivity(intent);
                break;
            case R.id.xihuan:
                break;
            case R.id.you:
                break;
            case R.id.shezi:
                Intent intent1 = new Intent(MyApp.context, SettingsActivity.class);
                startActivity(intent1);
                break;
        }
    }

    @Override
    public void success(UserInfo userInfo) {
        if (userInfo.getData() != null) {
            UserInfo.DataBean data = userInfo.getData();
            userIcn.setImageURI(Uri.parse(data.getIcon()));
            userName.setText(data.getNickname()+"");
            userTel.setText(data.getUsername()+"");
        }
    }
}
