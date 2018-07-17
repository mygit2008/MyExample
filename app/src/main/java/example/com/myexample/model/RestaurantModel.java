package example.com.myexample.model;

import example.com.myexample.bean.RestaurantBean;
import example.com.myexample.utils.RetrofitUtil2;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author zhangjunyou
 * @date 2018/7/13
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */

public class RestaurantModel {

    public void getRestaurant(final IRestaurantModel iRestaurantModel) {
        RetrofitUtil2.getInstence().API().getRestaurant()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RestaurantBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RestaurantBean restaurantBean) {
                        iRestaurantModel.success(restaurantBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface IRestaurantModel {
        void success(RestaurantBean restaurantBean);
    }
}
