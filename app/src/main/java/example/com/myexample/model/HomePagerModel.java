package example.com.myexample.model;

import example.com.myexample.bean.FoodsBean;
import example.com.myexample.utils.RetrofitUtil2;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author zhangjunyou
 * @date 2018/7/11
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */

public class HomePagerModel {

    public void getHome(final IHomePagerModel iHomePagerModel) {
        RetrofitUtil2.getInstence().API().getHome()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FoodsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FoodsBean foodsBean) {
                        iHomePagerModel.success(foodsBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface IHomePagerModel {
        void success(FoodsBean foodsBean);
    }
}
