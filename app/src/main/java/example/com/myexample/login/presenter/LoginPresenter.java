package example.com.myexample.login.presenter;

import example.com.myexample.MyApp;
import example.com.myexample.login.bean.LoginBean;
import example.com.myexample.login.model.LoginModel;
import example.com.myexample.login.view.ILoginView;
import example.com.myexample.utils.MobilePasswordVerificationUtil;

/**
 * @author zhangjunyou
 * @date 2018/7/9
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */

public class LoginPresenter {
    private LoginModel loginModel;
    private ILoginView iLoginView;

    public LoginPresenter(ILoginView iLoginView) {
        this.iLoginView = iLoginView;
        loginModel = new LoginModel();
    }

    public void detach() {
        if (iLoginView != null) {
            iLoginView = null;
        }
    }

    public void login(String mobile, String password) {
        if (MobilePasswordVerificationUtil.isMobile(MyApp.context, mobile) && MobilePasswordVerificationUtil.isPassword(MyApp.context, password)) {
            loginModel.login(mobile, password, new LoginModel.ILoginModel() {
                @Override
                public void success(LoginBean loginBean) {
                    if (iLoginView != null) {
                        iLoginView.success(loginBean);
                    }
                }
            });
        }
    }
}
