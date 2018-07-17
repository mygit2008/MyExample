package example.com.myexample.model;

import java.io.File;

import example.com.myexample.bean.UploadBean;
import example.com.myexample.utils.RetrofitUtil;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;

/**
 * @author zhangjunyou
 * @date 2018/7/10
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */

public class UploadModel {

    public void uploadFile(String uid, MultipartBody.Part file, IUploadModel iUploadModel) {
        RetrofitUtil.getInstence().API().uploadFile(uid,file)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UploadBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UploadBean uploadBean) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface IUploadModel{
        void success(UploadBean uploadBean);
    }
}
