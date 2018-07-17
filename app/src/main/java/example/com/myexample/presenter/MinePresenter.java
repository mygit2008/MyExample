package example.com.myexample.presenter;

import example.com.myexample.bean.UserInfo;
import example.com.myexample.model.MineModel;
import example.com.myexample.view.IMineView;

/**
 * @author zhangjunyou
 * @date 2018/7/9
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */

public class MinePresenter {
    private IMineView iMineView;
    private MineModel mineModel;

    public MinePresenter(IMineView iMineView) {
        this.iMineView = iMineView;
        mineModel = new MineModel();
    }

    public void detach() {
        if (iMineView != null) {
            iMineView = null;
        }
    }

    public void getUserInfo(String uid) {
        mineModel.getUserInfo(uid, new MineModel.IMineModel() {
            @Override
            public void success(UserInfo userInfo) {
                if (iMineView!=null){
                    iMineView.success(userInfo);
                }
            }
        });
    }
}
