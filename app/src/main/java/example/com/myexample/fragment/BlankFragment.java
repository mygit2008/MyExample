package example.com.myexample.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.umeng.analytics.MobclickAgent;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import example.com.myexample.MyApp;
import example.com.myexample.R;
import example.com.myexample.activity.ShopCartActivity;
import example.com.myexample.adapter.HomePagerAdapter;
import example.com.myexample.bean.FoodsBean;
import example.com.myexample.presenter.HomePagerPresenter;
import example.com.myexample.view.HomePagerView;

/**
 * @author zhangjunyou
 * @date 2018/7/11
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */

public class BlankFragment extends Fragment implements HomePagerView, View.OnClickListener {
    private View view;
    private RecyclerView rlv;
    private HomePagerPresenter pagerPresenter;
    private Banner banner;
    private ArrayList<String> imges;
    private ImageView gwc;
    private ImageView gwcqp;
    /**
     * 1
     */
    private TextView gwcnum;
    private int num = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_layout, container, false);
        initView(view);
        pagerPresenter = new HomePagerPresenter(this);
        pagerPresenter.getHome();
        return view;
    }

    private void initView(View view) {
        rlv = (RecyclerView) view.findViewById(R.id.rlv);
        rlv.setLayoutManager(new GridLayoutManager(MyApp.context, 2));
        banner = (Banner) view.findViewById(R.id.banner);
        imges = new ArrayList<>();
        imges.add("http://p0.meituan.net/wmproduct/b048ddd48b50ef1a691bcfa610c864bf97633.jpg");
        imges.add("http://p0.meituan.net/wmproduct/e3d7b0132f5a6a6b5b3cc7b35347b69252795.jpg");
        imges.add("http://p0.meituan.net/wmproduct/473bac2da673603f41afa7d93a858d8d80826.jpg");
        imges.add("http://p1.meituan.net/wmproduct/71a5bcccdcbf08587ae35356087bf21e59626.jpg");
        imges.add("http://p0.meituan.net/wmproduct/ac3c90cbdc42cfb11eced4b8b1462d9a44583.jpg");
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                imageView.setImageURI(Uri.parse((String) path));
            }

            @Override
            public ImageView createImageView(Context context) {
                GenericDraweeHierarchy builder = GenericDraweeHierarchyBuilder.newInstance(getResources())
                        //设置圆角
                        .setRoundingParams(RoundingParams.fromCornersRadius(20)).build();
                SimpleDraweeView simpleDraweeView = new SimpleDraweeView(context);
                simpleDraweeView.setHierarchy(builder);
                return simpleDraweeView;
            }
        });
        banner.setImages(imges);
        banner.start();
        gwc = (ImageView) view.findViewById(R.id.gwc);
        gwc.setOnClickListener(this);
        gwcqp = (ImageView) view.findViewById(R.id.gwcqp);
        gwcnum = (TextView) view.findViewById(R.id.gwcnum);
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
        List<FoodsBean.DataBean> data = foodsBean.getData();
        HomePagerAdapter adapter = new HomePagerAdapter(MyApp.context, data);
        rlv.setAdapter(adapter);
        adapter.setOnItemClickListener(new HomePagerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                gwcnum.setText("" + num++);
                gwcqp.setVisibility(View.VISIBLE);
                gwcnum.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.gwc:
                Intent intent = new Intent(MyApp.context, ShopCartActivity.class);
                getActivity().startActivity(intent);
                break;
        }
    }
}
