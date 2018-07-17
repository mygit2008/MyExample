package example.com.myexample.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;
import com.zhy.autolayout.AutoRelativeLayout;

import java.io.File;

import example.com.myexample.MyApp;
import example.com.myexample.R;
import example.com.myexample.utils.CleanMessageUtil;
import example.com.myexample.utils.ConfirmPopWindow;
import example.com.myexample.utils.PopWindowUtil;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView back;
    private AutoRelativeLayout qingc;
    private AutoRelativeLayout versions;
    private ImageView tuisong;
    private String sdPath;
    private File outCachePath;
    private File outFilePath;
    /**
     * 0MB
     */
    private TextView cache;
    private AlertDialog.Builder ibuilder;
    private boolean flag = true;
    /**
     * 普通
     */
    private TextView editText;
    /**
     * 仅WI-FI网络
     */
    private TextView wifi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        initView();
        String totalCacheSize = CleanMessageUtil.getTotalCacheSize(MyApp.context);
        cache.setText(totalCacheSize);
    }

    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(this);
        qingc = (AutoRelativeLayout) findViewById(R.id.qingc);
        qingc.setOnClickListener(this);
        versions = (AutoRelativeLayout) findViewById(R.id.versions);
        versions.setOnClickListener(this);
        tuisong = (ImageView) findViewById(R.id.tuisong);
        tuisong.setOnClickListener(this);
        cache = (TextView) findViewById(R.id.cache);
        editText = (TextView) findViewById(R.id.editText);
        wifi = (TextView) findViewById(R.id.wifi);
        editText.setOnClickListener(this);
        wifi.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.back:
                finish();
                break;
            case R.id.qingc:
                show();
                break;
            case R.id.versions:
                Intent intent = new Intent(SettingsActivity.this, VersionsActivity.class);
                startActivity(intent);
                break;
            case R.id.tuisong:
                if (flag) {
                    tuisong.setBackgroundResource(R.mipmap.kaiguan2);
                    flag = false;
                } else {
                    tuisong.setBackgroundResource(R.mipmap.kaiguan);
                    flag = true;
                }
                break;
            case R.id.editText:
                new ConfirmPopWindow(SettingsActivity.this, R.layout.confirm_dialog, editText).showAtBottom(editText);
                break;
            case R.id.wifi:
                new ConfirmPopWindow(SettingsActivity.this, R.layout.confirm_dialog2, wifi).showAtBottom(wifi);
                break;
        }
    }

    private void show() {
        ibuilder = new AlertDialog.Builder(SettingsActivity.this);
        ibuilder.setTitle("清除缓存");
        ibuilder.setMessage("清除缓存会导致下载的内容删除，是否确定?");
        ibuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        ibuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                CleanMessageUtil.clearAllCache(getApplicationContext());
                cache.setText(CleanMessageUtil.getTotalCacheSize(MyApp.context));
                dialog.dismiss();
            }
        }).create().show();
    }

    @Override
    protected void onResume() {
        super.onResume();

        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}