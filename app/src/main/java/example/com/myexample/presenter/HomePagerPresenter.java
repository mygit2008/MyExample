package example.com.myexample.presenter;

import example.com.myexample.bean.FoodsBean;
import example.com.myexample.model.HomePagerModel;
import example.com.myexample.view.HomePagerView;

/**
 * @author zhangjunyou
 * @date 2018/7/11
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */

public class HomePagerPresenter {
    private HomePagerModel homePagerModel;
    private HomePagerView homePagerView;

    public HomePagerPresenter(HomePagerView homePagerView) {
        this.homePagerView = homePagerView;
        homePagerModel = new HomePagerModel();
    }

    public void detach() {
        if (homePagerView != null) {
            homePagerView = null;
        }
    }

    public void getHome() {
        homePagerModel.getHome(new HomePagerModel.IHomePagerModel() {
            @Override
            public void success(FoodsBean foodsBean) {
                if (homePagerView != null) {
                    homePagerView.success(foodsBean);
                }
            }
        });
    }
}
