package com.bawei.chenxiaoxu.chenxiaoxu20180910.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bawei.chenxiaoxu.chenxiaoxu20180910.R;
import com.bawei.chenxiaoxu.chenxiaoxu20180910.bean.Lo;
import com.bawei.chenxiaoxu.chenxiaoxu20180910.di.IView;
import com.bawei.chenxiaoxu.chenxiaoxu20180910.ui.IPesenterImp;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity implements View.OnClickListener ,IView{

    private EditText ed_name;
    private EditText ed_password;
    private Button btn_login;
    private Button btn_resign;
    private IPesenterImp iPesenter;
    private String name;
    private String password;
    @SuppressLint("HandlerLeak")
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==0){
                String string= (String) msg.obj;
                Gson gson = new Gson();
                Lo lo = gson.fromJson(string, Lo.class);
                if(lo.getCode().equals("1")){
                    Toast.makeText(MainActivity.this, "用户名或密码有误", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, ShowActivity.class);
                    startActivity(intent);
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        onclick();
    }

    private void onclick() {
        btn_login.setOnClickListener(this);
        btn_resign.setOnClickListener(this);
    }

    private void init() {
        ed_name = findViewById(R.id.ed_name);
        ed_password = findViewById(R.id.ed_password);
        btn_login = findViewById(R.id.btn_login);
        btn_resign = findViewById(R.id.btn_resign);

        iPesenter = new IPesenterImp(this);
    }

    @Override
    public void onClick(View v) {
        name = ed_name.getText().toString();
        password = ed_password.getText().toString();
        switch (v.getId()){
            case R.id.btn_resign:
                Intent intent = new Intent(MainActivity.this, ResignActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_login:
                iPesenter.login(name,password);
                break;

        }
    }


    @Override
    public void error(String message) {

    }

    @Override
    public void success(String string) {

    }

    @Override
    public void error2(final String message) {
            runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void success2(final String string) {
        /*runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, string, Toast.LENGTH_SHORT).show();
            }
        });*/
        Message message = new Message();
        message.what=0;
        message.obj=string;
        handler.sendMessage(message);
    }
}
