package example.com.myexample.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import example.com.myexample.R;

public class SousuoActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mBack;
    /**
     * 搜索店铺，菜品名称
     */
    private EditText mSouSuo;
    /**
     * 搜索
     */
    private TextView mSouBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sousuo);
        initView();
    }

    private void initView() {
        mBack = (ImageView) findViewById(R.id.back);
        mBack.setOnClickListener(this);
        mSouSuo = (EditText) findViewById(R.id.sou_suo);
        mSouBtn = (TextView) findViewById(R.id.sou_btn);
        mSouBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.back:
                break;
            case R.id.sou_btn:
                break;
        }
    }
}
