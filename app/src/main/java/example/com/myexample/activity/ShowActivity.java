package example.com.myexample.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.dash.zxinglibrary.activity.CaptureActivity;
import com.dash.zxinglibrary.activity.CodeUtils;
import com.umeng.analytics.MobclickAgent;
import com.zhy.autolayout.AutoFrameLayout;

import example.com.myexample.MyApp;
import example.com.myexample.R;
import example.com.myexample.fragment.HomePage;
import example.com.myexample.fragment.Mine;
import example.com.myexample.fragment.SubscribeF;

public class ShowActivity extends AppCompatActivity implements View.OnClickListener {

    private AutoFrameLayout container;
    private RadioButton home;
    private RadioButton my;
    private FragmentManager fragmentManager;
    private RadioButton sao;
    private boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        initView();
    }

    private void initView() {
        container = (AutoFrameLayout) findViewById(R.id.container);
        home = (RadioButton) findViewById(R.id.home);
        home.setOnClickListener(this);
        my = (RadioButton) findViewById(R.id.my);
        my.setOnClickListener(this);
        fragmentManager = getSupportFragmentManager();
        sao = (RadioButton) findViewById(R.id.sao);
        sao.setOnClickListener(this);
        sao.performClick();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                fragmentManager.beginTransaction().replace(R.id.container, new HomePage()).commit();
                break;
            case R.id.home:
                fragmentManager.beginTransaction().replace(R.id.container, new SubscribeF()).commit();
                flag = true;
                break;
            case R.id.sao:
                if (flag) {
                    fragmentManager.beginTransaction().replace(R.id.container, new HomePage()).commit();
                    flag = false;
                } else {
                    Intent intent = new Intent(ShowActivity.this, CaptureActivity.class);
                    startActivityForResult(intent, 5);
                    flag = true;
                }
                break;
            case R.id.my:
                fragmentManager.beginTransaction().replace(R.id.container, new Mine()).commit();
                flag = true;
                break;
        }
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 5) {
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    //拿到最终结果
                    Intent intent = new Intent(MyApp.context, WebViewActivity.class);
                    intent.putExtra("detailUrl", result);
                    startActivity(intent);
                    Toast.makeText(this, "解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(ShowActivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
