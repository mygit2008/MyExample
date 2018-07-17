package example.com.myexample.presenter;

import example.com.myexample.bean.RestaurantBean;
import example.com.myexample.model.RestaurantModel;
import example.com.myexample.view.IRestaurantView;

/**
 * @author zhangjunyou
 * @date 2018/7/13
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */

public class RestaurantPresenter {
    private RestaurantModel restaurantModel;
    private IRestaurantView iRestaurantView;

    public RestaurantPresenter(IRestaurantView iRestaurantView) {
        this.iRestaurantView = iRestaurantView;
        restaurantModel = new RestaurantModel();
    }

    public void detach() {
        if (iRestaurantView != null) {
            iRestaurantView = null;
        }
    }

    public void getRestaurant() {
        restaurantModel.getRestaurant(new RestaurantModel.IRestaurantModel() {
            @Override
            public void success(RestaurantBean restaurantBean) {
                if (iRestaurantView != null) {
                    iRestaurantView.success(restaurantBean);
                }
            }
        });
    }
}
