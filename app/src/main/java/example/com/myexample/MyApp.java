package example.com.myexample;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.dash.zxinglibrary.activity.ZXingLibrary;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.mob.MobSDK;
import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.zhy.autolayout.config.AutoLayoutConifg;

/**
 * @author zhangjunyou
 * @date 2018/7/5
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */

public class MyApp extends Application {
    public static Context context;
    {
        PlatformConfig.setQQZone("1106987266", "ADfkMiRlA8Vrv9Gq");
    }
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        Fresco.initialize(context);
        AutoLayoutConifg.getInstance().useDeviceSize();
        //友盟初始化5b434f048f4a9d21cc000054
        UMConfigure.init(this, "5b434f048f4a9d21cc000054", "Umeng", UMConfigure.DEVICE_TYPE_PHONE, "ADfkMiRlA8Vrv9Gq");
        /**
         * 设置组件化的Log开关
         * 参数: boolean 默认为false，如需查看LOG设置为true
         */
        UMConfigure.setLogEnabled(true);
        UMShareAPI.get(this);
        //短信验证
        MobSDK.init(this);
        //腾讯bugly
        CrashReport.initCrashReport(getApplicationContext(), "f16c2df2e4", true);
        //初始化二维码工具类
        ZXingLibrary.initDisplayOpinion(this);
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
