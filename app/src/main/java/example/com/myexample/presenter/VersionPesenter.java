package example.com.myexample.presenter;

import example.com.myexample.bean.VersionBean;
import example.com.myexample.model.VersionModel;
import example.com.myexample.view.IVersionView;

/**
 * @author zhangjunyou
 * @date 2018/7/16
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */

public class VersionPesenter {
    private VersionModel versionModel;
    private IVersionView iVersionView;

    public VersionPesenter(IVersionView iVersionView) {
        this.iVersionView = iVersionView;
        versionModel = new VersionModel();
    }

    public void detach() {
        if (iVersionView != null) {
            iVersionView = null;
        }
    }

    public void getVersion() {
        versionModel.getVersion(new VersionModel.IVersionModel() {
            @Override
            public void success(VersionBean versionBean) {
                if (iVersionView != null) {
                    iVersionView.success(versionBean);
                }
            }
        });
    }
}
