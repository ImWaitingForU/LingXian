package com.lingxian.lingxian.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.lingxian.lingxian.R;

/**
 * Created by Administrator on 2016/7/11 0011.
 */
public class LoginActivity extends Activity {


    private Button login;
    private TextView regist;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.loginview);

        initViews();
    }

    /**
     * 初始化界面
     */
    private void initViews() {
        login= (Button) findViewById(R.id.id_btn_signin);
        regist= (TextView) findViewById(R.id.id_tv_newuser);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent1);
            }
        });
        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(LoginActivity.this,RegistActivity.class);
                startActivity(intent2);
            }
        });
    }
}
