package example.com.myexample.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.List;

import example.com.myexample.R;
import example.com.myexample.bean.RestaurantBean;

/**
 * @author zhangjunyou
 * @date 2018/7/13
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */

public class RestaurantAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<RestaurantBean.DataBean> data;
    private boolean flag = true;

    public RestaurantAdapter(Context context, List<RestaurantBean.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.restaurant_layout, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final MyViewHolder viewHolder = (MyViewHolder) holder;
        viewHolder.class_img.setImageURI(Uri.parse(data.get(position).getPic_url()));
        viewHolder.shangjia.setText(data.get(position).getName());
        viewHolder.yueshou.setText(data.get(position).getMonth_sales_tip());
        viewHolder.juli.setText(data.get(position).getDistance());
        viewHolder.renjun.setText(data.get(position).getAverage_price_tip());
        viewHolder.xih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag) {
                    viewHolder.xih.setBackgroundResource(R.mipmap.xih1);
                    flag = false;
                } else {
                    viewHolder.xih.setBackgroundResource(R.mipmap.xih2);
                    flag = true;
                }
            }
        });
        viewHolder.quality.setText(data.get(position).getQuality_score() + "");
        String s = viewHolder.quality.getText().toString();
        Double score = Double.valueOf(s);
        if (score > 0 && score < 1) {
            ImageView imageView = new ImageView(context);
            imageView.setImageResource(R.mipmap.bxing);
            viewHolder.quality_score.addView(imageView);
        } else if (score == 1) {
            ImageView imageView = new ImageView(context);
            imageView.setImageResource(R.mipmap.xing);
            viewHolder.quality_score.addView(imageView);
        } else if (score > 1 && score < 2) {
            ImageView imageView = new ImageView(context);
            imageView.setImageResource(R.mipmap.xing);
            ImageView imageView1 = new ImageView(context);
            imageView1.setImageResource(R.mipmap.bxing);
            viewHolder.quality_score.addView(imageView);
            viewHolder.quality_score.addView(imageView1);
        } else if (score == 2) {
            ImageView imageView = new ImageView(context);
            imageView.setImageResource(R.mipmap.xing);
            ImageView imageView1 = new ImageView(context);
            imageView1.setImageResource(R.mipmap.xing);
            viewHolder.quality_score.addView(imageView);
            viewHolder.quality_score.addView(imageView1);
        } else if (score > 2 && score < 3) {
            ImageView imageView = new ImageView(context);
            imageView.setImageResource(R.mipmap.xing);
            ImageView imageView1 = new ImageView(context);
            imageView1.setImageResource(R.mipmap.xing);
            ImageView imageView2 = new ImageView(context);
            imageView2.setImageResource(R.mipmap.bxing);
            viewHolder.quality_score.addView(imageView);
            viewHolder.quality_score.addView(imageView1);
            viewHolder.quality_score.addView(imageView2);
        } else if (score == 3) {
            ImageView imageView = new ImageView(context);
            imageView.setImageResource(R.mipmap.xing);
            ImageView imageView1 = new ImageView(context);
            imageView1.setImageResource(R.mipmap.xing);
            ImageView imageView2 = new ImageView(context);
            imageView2.setImageResource(R.mipmap.xing);
            viewHolder.quality_score.addView(imageView);
            viewHolder.quality_score.addView(imageView1);
            viewHolder.quality_score.addView(imageView2);
        } else if (score > 3 && score < 4) {
            ImageView imageView = new ImageView(context);
            imageView.setImageResource(R.mipmap.xing);
            ImageView imageView1 = new ImageView(context);
            imageView1.setImageResource(R.mipmap.xing);
            ImageView imageView2 = new ImageView(context);
            imageView2.setImageResource(R.mipmap.xing);
            ImageView imageView3 = new ImageView(context);
            imageView3.setImageResource(R.mipmap.bxing);
            viewHolder.quality_score.addView(imageView);
            viewHolder.quality_score.addView(imageView1);
            viewHolder.quality_score.addView(imageView2);
            viewHolder.quality_score.addView(imageView3);
        } else if (score == 4) {
            ImageView imageView = new ImageView(context);
            imageView.setImageResource(R.mipmap.xing);
            ImageView imageView1 = new ImageView(context);
            imageView1.setImageResource(R.mipmap.xing);
            ImageView imageView2 = new ImageView(context);
            imageView2.setImageResource(R.mipmap.xing);
            ImageView imageView3 = new ImageView(context);
            imageView3.setImageResource(R.mipmap.xing);
            viewHolder.quality_score.addView(imageView);
            viewHolder.quality_score.addView(imageView1);
            viewHolder.quality_score.addView(imageView2);
            viewHolder.quality_score.addView(imageView3);
        } else if (score > 4 && score < 5) {
            ImageView imageView = new ImageView(context);
            imageView.setImageResource(R.mipmap.xing);
            ImageView imageView1 = new ImageView(context);
            imageView1.setImageResource(R.mipmap.xing);
            ImageView imageView2 = new ImageView(context);
            imageView2.setImageResource(R.mipmap.xing);
            ImageView imageView3 = new ImageView(context);
            imageView3.setImageResource(R.mipmap.xing);
            ImageView imageView4 = new ImageView(context);
            imageView4.setImageResource(R.mipmap.bxing);
            viewHolder.quality_score.addView(imageView);
            viewHolder.quality_score.addView(imageView1);
            viewHolder.quality_score.addView(imageView2);
            viewHolder.quality_score.addView(imageView3);
            viewHolder.quality_score.addView(imageView4);
        } else if (score == 5) {
            ImageView imageView = new ImageView(context);
            imageView.setImageResource(R.mipmap.xing);
            ImageView imageView1 = new ImageView(context);
            imageView1.setImageResource(R.mipmap.xing);
            ImageView imageView2 = new ImageView(context);
            imageView2.setImageResource(R.mipmap.xing);
            ImageView imageView3 = new ImageView(context);
            imageView3.setImageResource(R.mipmap.xing);
            ImageView imageView4 = new ImageView(context);
            imageView4.setImageResource(R.mipmap.xing);
            viewHolder.quality_score.addView(imageView);
            viewHolder.quality_score.addView(imageView1);
            viewHolder.quality_score.addView(imageView2);
            viewHolder.quality_score.addView(imageView3);
            viewHolder.quality_score.addView(imageView4);
        }
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView shangjia, yueshou, juli, renjun, quality;
        ImageView xih;
        SimpleDraweeView class_img;
        AutoLinearLayout quality_score;

        public MyViewHolder(View itemView) {
            super(itemView);
            shangjia = itemView.findViewById(R.id.shangjia);
            yueshou = itemView.findViewById(R.id.yueshou);
            juli = itemView.findViewById(R.id.juli);
            xih = itemView.findViewById(R.id.xih);
            class_img = itemView.findViewById(R.id.class_img);
            renjun = itemView.findViewById(R.id.renjun);
            quality_score = itemView.findViewById(R.id.quality_score);
            quality = itemView.findViewById(R.id.quality);
        }
    }
}
