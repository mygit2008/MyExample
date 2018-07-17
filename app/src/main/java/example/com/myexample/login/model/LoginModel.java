package example.com.myexample.login.model;

import example.com.myexample.login.bean.LoginBean;
import example.com.myexample.utils.RetrofitUtil;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author zhangjunyou
 * @date 2018/7/9
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */

public class LoginModel {

    public void login(String moblie, String password, final ILoginModel iLoginModel) {
        RetrofitUtil.getInstence().API().login(moblie, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        iLoginModel.success(loginBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface ILoginModel {
        void success(LoginBean loginBean);
    }
}
