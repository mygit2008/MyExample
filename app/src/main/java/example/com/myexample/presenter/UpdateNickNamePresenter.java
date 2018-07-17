package example.com.myexample.presenter;

import example.com.myexample.bean.UpdateNickNameBean;
import example.com.myexample.model.UpdateNickNameModel;
import example.com.myexample.view.IUpdateNickNameView;

import static android.R.attr.name;

/**
 * @author zhangjunyou
 * @date 2018/7/10
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */

public class UpdateNickNamePresenter {
    private UpdateNickNameModel updateNickNameModel;
    private IUpdateNickNameView iUpdateNickNameView;

    public UpdateNickNamePresenter(IUpdateNickNameView iUpdateNickNameView) {
        this.iUpdateNickNameView = iUpdateNickNameView;
        updateNickNameModel = new UpdateNickNameModel();
    }

    public void detach() {
        if (iUpdateNickNameView != null) {
            iUpdateNickNameView = null;
        }
    }

    public void updateNickName(String uid, String name) {
        updateNickNameModel.updateNickName(uid, name, new UpdateNickNameModel.IUpdateNickNameModel() {
            @Override
            public void success(UpdateNickNameBean updateNickNameBean) {
                if (iUpdateNickNameView != null) {
                    iUpdateNickNameView.successName(updateNickNameBean);
                }
            }
        });
    }
}
