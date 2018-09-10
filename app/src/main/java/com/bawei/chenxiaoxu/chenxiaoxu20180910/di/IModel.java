package com.bawei.chenxiaoxu.chenxiaoxu20180910.di;

import javax.security.auth.callback.Callback;

/**
 * Created by _ヽ陌路离殇ゞ on 2018/9/10.
 */

public interface IModel {
    void getLoData(String name, String password, CallBack2 callBack2);

    interface CallBack{
        void error(String message);
        void success(String string);
    }
    interface CallBack2{
        void error2(String message);
        void success2(String string);
    }
    void getData(String name, String password ,CallBack callBack);
}
