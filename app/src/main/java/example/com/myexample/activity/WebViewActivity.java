package example.com.myexample.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import example.com.myexample.R;

public class WebViewActivity extends AppCompatActivity {

    private String detailUrl;
    private WebView mStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        initView();
        //接收地址
        Intent intent = getIntent();
        detailUrl = intent.getStringExtra("detailUrl");
        mStr.loadUrl(detailUrl);
    }

    private void initView() {
        mStr = (WebView) findViewById(R.id.str);
        WebSettings settings = mStr.getSettings();
    }
}
