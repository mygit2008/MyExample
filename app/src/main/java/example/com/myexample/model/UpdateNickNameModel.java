package example.com.myexample.model;

import example.com.myexample.bean.UpdateNickNameBean;
import example.com.myexample.utils.RetrofitUtil;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author zhangjunyou
 * @date 2018/7/10
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */

public class UpdateNickNameModel {

    public void updateNickName(String uid, String name, final IUpdateNickNameModel iUpdateNickNameModel) {
        RetrofitUtil.getInstence().API().updateNickName(uid, name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UpdateNickNameBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UpdateNickNameBean updateNickNameBean) {
                        iUpdateNickNameModel.success(updateNickNameBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface IUpdateNickNameModel {
        void success(UpdateNickNameBean updateNickNameBean);
    }
}
