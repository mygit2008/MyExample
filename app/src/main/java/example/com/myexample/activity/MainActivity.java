package example.com.myexample.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

import example.com.myexample.R;

public class MainActivity extends AppCompatActivity {

    private ViewPager vp;
    private List<Integer> imgs;
    private List<ImageView> imageViews;
    int touchCount;
    int currentItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgs = new ArrayList<>();
        imgs.add(R.mipmap.one);
        imgs.add(R.mipmap.two);
        imgs.add(R.mipmap.three);
        imageViews = new ArrayList<>();
        for (int i = 0; i < imgs.size(); i++) {
            imageViews.add(new ImageView(this));
        }
        initView();
    }

    private void initView() {
        vp = (ViewPager) findViewById(R.id.vp);
        vp.setAdapter(new GuideAdapter());
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                currentItem = position;
//                Log.i("Guide","监听改变"+position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        vp.setOnTouchListener(new View.OnTouchListener() {
            float startX;
            float startY;
            float endX;
            float endY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startX = event.getX();
                        startY = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        endX = event.getX();
                        endY = event.getY();
                        WindowManager windowManager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
                        //获取屏幕的宽度
                        Point size = new Point();
                        windowManager.getDefaultDisplay().getSize(size);
                        int width = size.x;
                        //首先要确定的是，是否到了最后一页，然后判断是否向左滑动，并且滑动距离是否符合，我这里的判断距离是屏幕宽度的4分之一（这里可以适当控制）
                        if (currentItem == (imageViews.size() - 1) && startX - endX > 0 && startX - endX >= (width / 4)) {
                            goToMainActivity();
                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
                        }
                        break;
                }
                return false;
            }
        });
    }

    private void goToMainActivity() {
        Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * Viewpager适配器
     */
    private class GuideAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return imageViews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = imageViews.get(position);
            imageView.setImageResource(imgs.get(position));
//            ViewGroup.LayoutParams viewLayoutParams = new ViewGroup.LayoutParams
//                    (
//                            DisplayUtils.dip2px(GuideActivity.this, 170),
//                            DisplayUtils.dip2px(GuideActivity.this, 200)
//                    );
            container.addView(imageView);//设置图片的宽高
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(imageViews.get(position));//释放滑动过后的前一页
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
