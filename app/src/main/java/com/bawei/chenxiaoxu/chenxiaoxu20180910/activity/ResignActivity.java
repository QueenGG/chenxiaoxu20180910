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
import com.bawei.chenxiaoxu.chenxiaoxu20180910.bean.Re;
import com.bawei.chenxiaoxu.chenxiaoxu20180910.di.IView;
import com.bawei.chenxiaoxu.chenxiaoxu20180910.ui.IPesenterImp;
import com.google.gson.Gson;

public class ResignActivity extends AppCompatActivity implements IView{

    private EditText ed_name1;
    private EditText ed_password1;
    private Button btn_re;
    private IPesenterImp iPesenter;
    private String name;
    private String password;
    @SuppressLint("HandlerLeak")
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==0){
                String string= (String) msg.obj;
                Gson gson = new Gson();
                Re re = gson.fromJson(string, Re.class);
                if(re.getCode().equals("1")){
                    Toast.makeText(ResignActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(ResignActivity.this, string, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ResignActivity.this,MainActivity.class);
                    startActivity(intent);
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resign);
        init();
    }

    private void init() {
        ed_name1 = findViewById(R.id.ed_name1);
        ed_password1 = findViewById(R.id.ed_password1);
        btn_re = findViewById(R.id.btn_re);

        iPesenter = new IPesenterImp(this);

        name = ed_name1.getText().toString();
        password = ed_password1.getText().toString();
        btn_re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPesenter.resign(name,password);
            }
        });
    }

    @Override
    public void error(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(ResignActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void success(final String string) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(ResignActivity.this,MainActivity.class);
                startActivity(intent);
                Toast.makeText(ResignActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
            }
        });
       /* Message message = new Message();
        message.what=0;
        message.obj=string;
        handler.sendMessage(message);*/
    }

    @Override
    public void error2(String message) {

    }

    @Override
    public void success2(String string) {

    }
}
