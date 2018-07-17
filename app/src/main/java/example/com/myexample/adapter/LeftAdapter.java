package example.com.myexample.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import example.com.myexample.R;
import example.com.myexample.bean.FoodsBean;

/**
 * @author zhangjunyou
 * @date 2018/7/12
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */

public class LeftAdapter extends RecyclerView.Adapter {
    private List<FoodsBean.DataBean> data;
    private Context context;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public LeftAdapter(List<FoodsBean.DataBean> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.left_layout, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MyViewHolder viewHolder = (MyViewHolder) holder;
        viewHolder.title.setText(data.get(position).getName());
        //设置字体颜色
        if (data.get(position).getChecked()) {
            viewHolder.title.setBackgroundColor(context.getResources().getColor(R.color.colorbai));
        } else {
            viewHolder.title.setBackgroundColor(context.getResources().getColor(R.color.colorhui));
        }
        viewHolder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    /**
     * 选中后，改变字体颜色和背景色
     *
     * @param position
     * @param bool
     */
    public void changeCheck(int position, boolean bool) {
        //先还原一下
        for (int i = 0; i < data.size(); i++) {
            data.get(i).setChecked(false);
        }
        FoodsBean.DataBean dataBean = data.get(position);
        dataBean.setChecked(bool);
        //刷新界面
        notifyDataSetChanged();
    }
}
