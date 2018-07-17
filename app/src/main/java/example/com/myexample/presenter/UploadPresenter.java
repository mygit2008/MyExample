package example.com.myexample.presenter;

import java.io.File;

import example.com.myexample.bean.UploadBean;
import example.com.myexample.model.UploadModel;
import example.com.myexample.view.IUploadView;
import okhttp3.MultipartBody;

/**
 * @author zhangjunyou
 * @date 2018/7/10
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */

public class UploadPresenter {
    private UploadModel uploadModel;
    private IUploadView iUploadView;

    public UploadPresenter(IUploadView iUploadView) {
        this.iUploadView = iUploadView;
        uploadModel = new UploadModel();
    }

    public void detach() {
        if (iUploadView != null) {
            iUploadView = null;
        }
    }

    public void uploadFile(String uid, MultipartBody.Part file) {
        uploadModel.uploadFile(uid, file, new UploadModel.IUploadModel() {
            @Override
            public void success(UploadBean uploadBean) {
                if (iUploadView != null) {
                    iUploadView.successIcon(uploadBean);
                }
            }
        });
    }
}
