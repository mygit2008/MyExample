package example.com.myexample.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import example.com.myexample.R;
import example.com.myexample.bean.FoodsBean;

/**
 * @author zhangjunyou
 * @date 2018/7/11
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */

public class HomePagerAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<FoodsBean.DataBean> data;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public HomePagerAdapter(Context context, List<FoodsBean.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tuijian, parent, false);
        ViewHolder2 viewHolder4 = new ViewHolder2(view);
        return viewHolder4;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ViewHolder2 viewHolder = (ViewHolder2) holder;
        List<FoodsBean.DataBean.SpusBean> spus = data.get(position).getSpus();
        viewHolder.img.setImageURI(Uri.parse(spus.get(0).getPic_url()));
        viewHolder.tv.setText(spus.get(0).getName());
        viewHolder.price.setText("￥" + spus.get(0).getSkus().get(0).getPrice());
        viewHolder.tianjia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener!=null){
                    onItemClickListener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    class ViewHolder2 extends RecyclerView.ViewHolder {
        SimpleDraweeView img;
        TextView tv;
        TextView price;
        ImageView tianjia;

        public ViewHolder2(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.class_img);
            tv = itemView.findViewById(R.id.class_title);
            price = itemView.findViewById(R.id.class_price);
            tianjia = itemView.findViewById(R.id.tianjia);
        }
    }
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
