package example.com.myexample.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.umeng.analytics.MobclickAgent;

import example.com.myexample.R;

public class ShopCartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_cart);
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
