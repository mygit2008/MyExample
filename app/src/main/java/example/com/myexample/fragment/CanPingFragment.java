package example.com.myexample.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.analytics.MobclickAgent;

import java.util.List;

import example.com.myexample.MyApp;
import example.com.myexample.R;
import example.com.myexample.activity.ShopCartActivity;
import example.com.myexample.adapter.LeftAdapter;
import example.com.myexample.adapter.RightAdapter;
import example.com.myexample.bean.FoodsBean;
import example.com.myexample.presenter.HomePagerPresenter;
import example.com.myexample.view.HomePagerView;

/**
 * @author zhangjunyou
 * @date 2018/7/12
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */

public class CanPingFragment extends Fragment implements View.OnClickListener, HomePagerView {
    private View view;
    private RecyclerView brlv;
    private RecyclerView crlv;
    /**
     * ￥0.00
     */
    private TextView price;
    /**
     * 点好了
     */
    private Button gwc_btn;
    private ImageView sao;
    private ImageView saoq;
    /**
     * 0
     */
    private TextView gwcn;
    private HomePagerPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pingjia_layout, container, false);
        initView(view);
        presenter = new HomePagerPresenter(this);
        presenter.getHome();
        return view;
    }

    private void initView(View view) {
        brlv = (RecyclerView) view.findViewById(R.id.brlv);
        crlv = (RecyclerView) view.findViewById(R.id.crlv);
        price = (TextView) view.findViewById(R.id.price);
        gwc_btn = (Button) view.findViewById(R.id.gwc_btn);
        gwc_btn.setOnClickListener(this);
        sao = (ImageView) view.findViewById(R.id.sao);
        saoq = (ImageView) view.findViewById(R.id.saoq);
        gwcn = (TextView) view.findViewById(R.id.gwcn);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.gwc_btn:
                Intent intent = new Intent(MyApp.context, ShopCartActivity.class);
                getActivity().startActivity(intent);
                break;
        }
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

    @Override
    public void success(FoodsBean foodsBean) {
        final List<FoodsBean.DataBean> data = foodsBean.getData();
        brlv.setLayoutManager(new LinearLayoutManager(MyApp.context));
        final LeftAdapter adapter = new LeftAdapter(data, MyApp.context);
        //条目分割线
        brlv.setAdapter(adapter);
        brlv.addItemDecoration(new DividerItemDecoration(getContext(), RecyclerView.VERTICAL));
        //默认选择第一个
        adapter.changeCheck(0, true);
        adapter.setOnItemClickListener(new LeftAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                adapter.changeCheck(position, true);
                List<FoodsBean.DataBean.SpusBean> spus = data.get(position).getSpus();
                crlv.setLayoutManager(new LinearLayoutManager(MyApp.context));
                final RightAdapter rightAdapter = new RightAdapter(MyApp.context, spus);
                crlv.setAdapter(rightAdapter);
                crlv.addItemDecoration(new DividerItemDecoration(getContext(), RecyclerView.VERTICAL));
                rightAdapter.setOnItemClickListener(new RightAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        sao.setVisibility(View.VISIBLE);
                        saoq.setVisibility(View.VISIBLE);
                        gwcn.setVisibility(View.VISIBLE);
                        gwcn.setText(position + "");
                        gwc_btn.setText("去结算");
                    }
                });
            }
        });
    }
}
