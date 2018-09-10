package com.bawei.chenxiaoxu.chenxiaoxu20180910.ui;

import com.bawei.chenxiaoxu.chenxiaoxu20180910.di.IModel;
import com.bawei.chenxiaoxu.chenxiaoxu20180910.di.IPesenter;
import com.bawei.chenxiaoxu.chenxiaoxu20180910.di.IView;

import java.util.jar.Manifest;

/**
 * Created by _ヽ陌路离殇ゞ on 2018/9/10.
 */

public class IPesenterImp implements IPesenter{

    private IView iView;
    private final IModelImp iModel;

    public IPesenterImp(IView iView) {
        this.iView=iView;
        iModel = new IModelImp();
    }

    @Override
    public void resign(String name, String password) {
        iModel.getData(name, password, new IModel.CallBack() {
            @Override
            public void error(String message) {
                iView.error(message);
            }

            @Override
            public void success(String string) {
                iView.success(string);
            }
        });
    }

    @Override
    public void login(String name, String password) {
        iModel.getLoData(name, password, new IModel.CallBack2() {
            @Override
            public void error2(String message) {
                iView.error2(message);
            }

            @Override
            public void success2(String string) {
                iView.success2(string);
            }
        });
    }
}
