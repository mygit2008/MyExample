package example.com.myexample.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import example.com.myexample.R;


/**
 * 弹窗视图
 */
public class ConfirmPopWindow extends PopupWindow implements View.OnClickListener {
    private Context context;
    private TextView ll_chat, ll_friend;
    private TextView textView;

    public ConfirmPopWindow(Context context,int layout,TextView tv) {
        super(context);
        this.context = context;
        this.textView = tv;
        initalize(layout,tv);
    }
 
    private void initalize(int layout,TextView tv) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view =  inflater.inflate(layout, null);
        ll_chat = view.findViewById(R.id.ll_chat);//发起群聊
        ll_friend = view.findViewById(R.id.ll_friend);//添加好友
        ll_chat.setOnClickListener(this);
        ll_friend.setOnClickListener(this);
        setContentView(view);
        initWindow(tv);
    }
 
    private void initWindow(TextView tv) {
//        DisplayMetrics d = context.getResources().getDisplayMetrics();
        this.setWidth(tv.getWidth());//d.widthPixels * 0.20
        this.setHeight(LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.update();
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x00000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        backgroundAlpha((Activity) context, 0.8f);//0.0-1.0
        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha((Activity) context, 1f);
            }
        });
    }
 
    //设置添加屏幕的背景透明度
    public void backgroundAlpha(Activity context, float bgAlpha) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        context.getWindow().setAttributes(lp);
    }
 
    public void showAtBottom(View view) {
        //弹窗位置设置
        showAsDropDown(view, 0, 0);
    }
 
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_chat:
                textView.setText(ll_chat.getText().toString());
                Toast.makeText(context, ll_chat.getText().toString(), Toast.LENGTH_SHORT).show();
                dismiss();
                break;
            case R.id.ll_friend:
                textView.setText(ll_friend.getText().toString());
                Toast.makeText(context, ll_friend.getText().toString(), Toast.LENGTH_SHORT).show();
                dismiss();
                break;
            default:
                break;
        }
    }
 
}