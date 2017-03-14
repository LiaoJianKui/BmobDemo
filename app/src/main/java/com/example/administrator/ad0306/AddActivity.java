package com.example.administrator.ad0306;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadFileListener;

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
        //申请读取内存的权限
        String[] permission=new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if(ContextCompat.checkSelfPermission(AddActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)!=
                PackageManager.PERMISSION_GRANTED){
                 requestPermissions(permission,1001);
        }


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
        String path="/storage/emulated/0/dog3.jpg";
        File jpgFile=new File(path);
        if(jpgFile.exists()){
            Toast.makeText(this, "文件地址正确", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "文件地址错误", Toast.LENGTH_SHORT).show();
        }
        final BmobFile bmobFile=new BmobFile(new File(path));
        bmobFile.uploadblock(new UploadFileListener() {
            @Override
            public void done(BmobException e) {
                if (e==null) {
                    Toast.makeText(AddActivity.this, "文件上传成功", Toast.LENGTH_SHORT).show();

                    String name = edtName.getText().toString().trim();
                    String age = edtAge.getText().toString().trim();
                    String address = edtAddress.getText().toString().trim();
                    Person p1 = new Person();
                    p1.setImage(bmobFile);
                    p1.setName(name);
                    p1.setAge(Integer.valueOf(age));
                    p1.setAddress(address);
                    p1.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            if (e == null) {

                            } else {
                                Toast.makeText(AddActivity.this, "添加数据失败", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
