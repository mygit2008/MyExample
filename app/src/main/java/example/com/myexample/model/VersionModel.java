package example.com.myexample.model;

import example.com.myexample.bean.VersionBean;
import example.com.myexample.utils.RetrofitUtil;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author zhangjunyou
 * @date 2018/7/16
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */

public class VersionModel {

    public void getVersion(final IVersionModel iVersionModel) {
        RetrofitUtil.getInstence().API().getVersion()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<VersionBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(VersionBean versionBean) {
                        iVersionModel.success(versionBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface IVersionModel {
        void success(VersionBean versionBean);
    }
}
