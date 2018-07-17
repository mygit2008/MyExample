package example.com.myexample.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import example.com.myexample.MyApp;
import example.com.myexample.R;
import example.com.myexample.bean.FoodsBean;
import example.com.myexample.custom.AddSubView;

/**
 * @author zhangjunyou
 * @date 2018/7/12
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */

public class RightAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<FoodsBean.DataBean.SpusBean> spus;
    private OnItemClickListener onItemClickListener;
    private int count = 0;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public RightAdapter(Context context, List<FoodsBean.DataBean.SpusBean> spus) {
        this.context = context;
        this.spus = spus;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.right_layout, parent, false);
        RightViewHolder rightViewHolder = new RightViewHolder(view);
        return rightViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final RightViewHolder rightViewHolder = (RightViewHolder) holder;
        rightViewHolder.sdv.setImageURI(Uri.parse(spus.get(position).getPic_url()));
        rightViewHolder.title.setText(spus.get(position).getName());
        rightViewHolder.count.setText("月售:" + spus.get(position).getMonth_saled_content());
        rightViewHolder.price.setText("￥ " + spus.get(position).getSkus().get(0).getPrice() + "");
        rightViewHolder.jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rightViewHolder.num.setVisibility(View.VISIBLE);
                rightViewHolder.jian.setVisibility(View.VISIBLE);
                String numm = rightViewHolder.num.getText().toString();
                count = Integer.valueOf(numm);
                count++;
                rightViewHolder.num.setText("" + count);
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(count);
                }
            }
        });
        rightViewHolder.jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count == 1) {
                    Toast.makeText(MyApp.context, "数量不能小于1", Toast.LENGTH_SHORT).show();
                } else {
                    String numm = rightViewHolder.num.getText().toString();
                    count = Integer.valueOf(numm);
                    count--;
                    rightViewHolder.num.setText(count + "");
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(count);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return spus.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    class RightViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView sdv;
        TextView title, count, price, num;
        ImageView jian, jia;

        public RightViewHolder(View itemView) {
            super(itemView);
            sdv = itemView.findViewById(R.id.sdv);
            title = itemView.findViewById(R.id.title);
            count = itemView.findViewById(R.id.count);
            price = itemView.findViewById(R.id.price);
            jian = itemView.findViewById(R.id.jian);
            jia = itemView.findViewById(R.id.jia);
            num = itemView.findViewById(R.id.num);
        }
    }
}
