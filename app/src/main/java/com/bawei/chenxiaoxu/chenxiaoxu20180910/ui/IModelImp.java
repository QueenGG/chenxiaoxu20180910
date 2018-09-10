package com.bawei.chenxiaoxu.chenxiaoxu20180910.ui;

import com.bawei.chenxiaoxu.chenxiaoxu20180910.di.IModel;
import com.bawei.chenxiaoxu.chenxiaoxu20180910.utils.Okhttputils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by _ヽ陌路离殇ゞ on 2018/9/10.
 */

public class IModelImp implements IModel {
    private String URL_RESIGN="https://www.zhaoapi.cn/quarter/register";
    private String URL_LOGIN="https://www.zhaoapi.cn/user/login";

    @Override
    public void getLoData(String name, String password, final CallBack2 callBack2) {
        FormBody body = new FormBody.Builder()
                .add("mobile", name)
                .add("password", password)

                .build();
        Okhttputils.getInstance().post(URL_LOGIN, body, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                String message = e.getMessage();
                callBack2.error2(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                callBack2.success2(string);
            }
        });
    }

    @Override
    public void getData(String name, String password, final CallBack callBack) {
        FormBody body = new FormBody.Builder()
                .add("mobile", name)
                .add("password", password)

                .build();
        Okhttputils.getInstance().post(URL_RESIGN, body, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                String message = e.getMessage();
                callBack.error(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                callBack.success(string);
            }
        });


    }
}
