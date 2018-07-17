package example.com.myexample.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

import example.com.myexample.R;
import example.com.myexample.adapter.TitleFragmentPagerAdapter;
import example.com.myexample.fragment.CanPingFragment;
import example.com.myexample.fragment.PingJiaFragment;

public class ClassfiyActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView back;
    private TabLayout tab;
    private ViewPager viewpager;
    private String[] titles = new String[]{"餐品", "评价"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classfiy);
        initView();
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new CanPingFragment());
        fragments.add(new PingJiaFragment());
        TitleFragmentPagerAdapter adapter = new TitleFragmentPagerAdapter(getSupportFragmentManager(), fragments, titles);
        viewpager.setAdapter(adapter);
        tab.setupWithViewPager(viewpager);
    }

    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(this);
        tab = (TabLayout) findViewById(R.id.tab);
        viewpager = (ViewPager) findViewById(R.id.viewpager);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.back:
                Intent intent = new Intent(ClassfiyActivity.this, ShowActivity.class);
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
