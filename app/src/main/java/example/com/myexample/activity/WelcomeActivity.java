package example.com.myexample.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.umeng.analytics.MobclickAgent;

import example.com.myexample.MyApp;
import example.com.myexample.R;
import example.com.myexample.login.LoginActivity;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView login;
    private ImageView sdv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        initView();
    }

    private void initView() {
        login = (ImageView) findViewById(R.id.login);
        login.setOnClickListener(this);
        sdv = (ImageView) findViewById(R.id.sdv);
        Glide.with(MyApp.context).load(R.drawable.ws).into(new GlideDrawableImageViewTarget(sdv, 1));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.login:
                Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(intent);
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
}
