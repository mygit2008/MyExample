package example.com.myexample.model;

import example.com.myexample.bean.UserInfo;
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

public class MineModel {


    public void getUserInfo(String uid, final IMineModel iMineModel) {
        RetrofitUtil.getInstence().API().getUserInfo(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserInfo>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UserInfo userInfo) {
                        iMineModel.success(userInfo);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface IMineModel {
        void success(UserInfo userInfo);
    }
}
