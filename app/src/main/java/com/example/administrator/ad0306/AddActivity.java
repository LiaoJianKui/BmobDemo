package com.example.administrator.ad0306;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Administrator on 2017/3/6.
 */

public class AddActivity extends AppCompatActivity {
    private EditText edtName;
    private EditText edtAge;
    private EditText edtAddress;
    private Button  btnCommit;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity);
        edtName= (EditText) findViewById(R.id.etName);
        edtAge= (EditText) findViewById(R.id.etAge);
        edtAddress= (EditText) findViewById(R.id.etAddress);
        btnCommit= (Button) findViewById(R.id.btnCommit);
        btnCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
                finish();
            }
        });

    }

    private void add() {
        String name=edtName.getText().toString().trim();
        String  age=edtAge.getText().toString().trim();
        String address =edtAddress.getText().toString().trim();
        Person p1=new Person();
        p1.setName(name);
        p1.setAge(Integer.valueOf(age));
        p1.setAddress(address);
        p1.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if(e==null){

                }else{
                    Toast.makeText(AddActivity.this, "添加数据失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
