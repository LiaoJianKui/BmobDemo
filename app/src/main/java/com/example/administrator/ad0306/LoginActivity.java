package com.example.administrator.ad0306;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/3/9.
 */

public class LoginActivity extends AppCompatActivity {
    private EditText etName;
    private EditText etPassword;
    private Button btnLogin;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        etName= (EditText) findViewById(R.id.etNameLogin);
        etPassword= (EditText) findViewById(R.id.etPasswordLogin);
        btnLogin= (Button) findViewById(R.id.btnLogin1);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=  etName.getText().toString().trim();
                String password=  etPassword.getText().toString().trim();
                if(TextUtils.isEmpty(name)&&TextUtils.isEmpty(password)){
                    Toast.makeText(LoginActivity.this, "请输入用户名或密码", Toast.LENGTH_SHORT).show();
                }else{
                    if(name.equals("zhangsan")&&password.equals("1234")){
                        startActivity(new Intent(LoginActivity.this,MainActivity.class));
                    }

                }
            }

        });
    }

}
