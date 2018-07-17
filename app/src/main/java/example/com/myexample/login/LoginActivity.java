package example.com.myexample.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.zhy.autolayout.AutoRelativeLayout;

import org.json.JSONObject;

import java.util.Map;
import java.util.Set;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.utils.SMSLog;
import example.com.myexample.MyApp;
import example.com.myexample.R;
import example.com.myexample.activity.ShowActivity;
import example.com.myexample.login.bean.LoginBean;
import example.com.myexample.login.presenter.LoginPresenter;
import example.com.myexample.login.view.ILoginView;
import example.com.myexample.utils.CountDownTimerUtils;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, ILoginView {

    /**
     * 请输入手机号码
     */
    private EditText telNum;
    /**
     * 输入短信验证码
     */
    private EditText yanNum;
    private TextView yanIcn;
    private AutoRelativeLayout loginLayout;
    private Button button;
    /**
     * 常规登录方式
     */
    private TextView logType;
    /**
     * 其他登录方式
     */
    private ImageView qqBtn;
    private ImageView wxBtn;
    /**
     * 请输入手机号码
     */
    private EditText ctelNum;
    /**
     * 输入密码
     */
    private EditText pwd;
    private AutoRelativeLayout cloginType;
    private ImageView showHide;
    private boolean flag = true;
    private boolean flag2 = true;
    private CountDownTimerUtils timeCount;
    private LoginPresenter presenter;
    private Button ybutton;
    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        sp = getSharedPreferences("user", Context.MODE_PRIVATE);
        presenter = new LoginPresenter(this);
        UMShareConfig config = new UMShareConfig();
        config.isNeedAuthOnGetUserInfo(true);
        UMShareAPI.get(MyApp.context).setShareConfig(config);
    }

    private void initView() {
        telNum = (EditText) findViewById(R.id.tel_num);
        yanNum = (EditText) findViewById(R.id.yan_num);
        loginLayout = (AutoRelativeLayout) findViewById(R.id.login_layout);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
        logType = (TextView) findViewById(R.id.log_type);
        qqBtn = (ImageView) findViewById(R.id.qq_btn);
        ctelNum = (EditText) findViewById(R.id.ctel_num);
        pwd = (EditText) findViewById(R.id.pwd);
        cloginType = (AutoRelativeLayout) findViewById(R.id.clogin_type);
        yanIcn = (TextView) findViewById(R.id.yan_icn);
        showHide = (ImageView) findViewById(R.id.show_hide);
        showHide.setOnClickListener(this);
        wxBtn = (ImageView) findViewById(R.id.wx_btn);
        logType.setOnClickListener(this);
        yanIcn.setOnClickListener(this);
        qqBtn.setOnClickListener(this);
        ybutton = (Button) findViewById(R.id.ybutton);
        ybutton.setOnClickListener(this);
        EventHandler eh = new EventHandler() {
            @Override
            public void afterEvent(int event, int result, Object data) {
                Message msg = new Message();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
                mHandler.sendMessage(msg);
            }
        };
        SMSSDK.registerEventHandler(eh);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.button:
                String mobile = ctelNum.getText().toString();
                String password = pwd.getText().toString();
                presenter.login(mobile, password);
                break;
            case R.id.show_hide:
                if (flag) {
                    showHide.setBackgroundResource(R.mipmap.show_pwd);
                    pwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());//可见
                    flag = false;
                } else {
                    showHide.setBackgroundResource(R.mipmap.hide_pwd);
                    pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());//密码
                    flag = true;
                }
                break;
            case R.id.log_type:
                if (flag2) {
                    cloginType.setVisibility(View.VISIBLE);
                    loginLayout.setVisibility(View.INVISIBLE);
                    logType.setText("短信验证码登录方式");
                    flag2 = false;
                } else {
                    cloginType.setVisibility(View.INVISIBLE);
                    loginLayout.setVisibility(View.VISIBLE);
                    logType.setText("常规登录方式");
                    flag2 = true;
                }
                break;
            case R.id.yan_icn:
                yanIcn.setBackgroundResource(R.mipmap.daojishi);
                SMSSDK.getVerificationCode("+86", telNum.getText().toString());
                phone = telNum.getText().toString();
                timeCount = new CountDownTimerUtils(30000, 1000, yanIcn);
                timeCount.start();
                break;
            case R.id.qq_btn:
                UMShareAPI.get(this).getPlatformInfo(LoginActivity.this, SHARE_MEDIA.QQ, umAuthListener);
                break;
            case R.id.ybutton:
                SMSSDK.submitVerificationCode("+86", phone, yanNum.getText().toString());
                break;
        }
    }

    @Override
    public void success(LoginBean loginBean) {
        SharedPreferences.Editor edit = sp.edit();
        edit.putString("uid", loginBean.getData().getUid() + "");
        edit.putString("token", loginBean.getData().getToken() + "");
        edit.putString("iconur", loginBean.getData().getIcon());
        edit.putString("screenname", loginBean.getData().getUsername() + "");
        edit.putBoolean("userfalg", false);
        edit.commit();
        Intent intent = new Intent(LoginActivity.this, ShowActivity.class);
        startActivity(intent);
        LoginActivity.this.finish();
    }

    private Bundle bundle;
    private SharedPreferences sp;
    private UMAuthListener umAuthListener = new UMAuthListener() {
        @Override//授权成功回调获取用户信息
        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
            Set<String> keySet = map.keySet();
            Log.e("tag------------>", keySet.toString());
            sp = getSharedPreferences("user", Context.MODE_PRIVATE);
            SharedPreferences.Editor edit = sp.edit();
            for (String string : keySet) {
                Log.e("userinfo------------>", string);
                if (string.equals("screen_name")) {
                    //获取登录的名字//得到昵称
                    String screenname = map.get("screen_name");
                    //通过editor对象写入数据
                    edit.putString("screenname", screenname);
                    //提交数据存入到xml文件中
                    edit.commit();
                }
                if (string.equals("profile_image_url")) {
                    //获取登录的图片//得到头像
                    String imageUrl = map.get("profile_image_url");
                    //通过editor对象写入数据
                    edit.putString("iconur", imageUrl);
                    //提交数据存入到xml文件中
                    edit.commit();
                }
                edit.putBoolean("userfalg", true);
                edit.commit();
                Intent intent = new Intent(LoginActivity.this, ShowActivity.class);
                startActivity(intent);
                LoginActivity.this.finish();
            }
        }

        @Override//授权失败回调
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText(LoginActivity.this, "Authorize fail", Toast.LENGTH_SHORT).show();
        }

        @Override//SHARE_MEDIA  可以判断授权的第三方是什么
        public void onStart(SHARE_MEDIA share_media) {
        }

        @Override//取消授权回调
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(LoginActivity.this, "Authorize cancel", Toast.LENGTH_SHORT).show();
        }
    };
    Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int event = msg.arg1;
            int result = msg.arg2;
            Object data = msg.obj;
            Log.e("event", "event=" + event);
            if (result == SMSSDK.RESULT_COMPLETE) {
                System.out.println("--------result" + event);
                //短信注册成功后，返回MainActivity,然后提示新好友
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {//提交验证码成功
                    Toast.makeText(getApplicationContext(), "提交验证码成功", Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor edit = sp.edit();
                    edit.putBoolean("userfalg", false);
                    edit.commit();
                    Intent intent = new Intent(LoginActivity.this, ShowActivity.class);
                    startActivity(intent);
                    finish();
                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                    //已经验证
                    Toast.makeText(getApplicationContext(), "验证码已经发送", Toast.LENGTH_SHORT).show();
                } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                    //已经验证
                    Toast.makeText(getApplicationContext(), "获取国家列表成功", Toast.LENGTH_SHORT).show();
                }

            } else {
                int status = 0;
                try {
                    ((Throwable) data).printStackTrace();
                    Throwable throwable = (Throwable) data;

                    JSONObject object = new JSONObject(throwable.getMessage());
                    String des = object.optString("detail");
                    status = object.optInt("status");
                    if (!TextUtils.isEmpty(des)) {
                        Toast.makeText(MyApp.context, des, Toast.LENGTH_SHORT).show();
                        return;
                    }
                } catch (Exception e) {
                    SMSLog.getInstance().w(e);
                }
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
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
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterAllEventHandler();
    }
}
