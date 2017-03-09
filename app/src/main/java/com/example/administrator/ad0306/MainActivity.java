package com.example.administrator.ad0306;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class MainActivity extends AppCompatActivity implements DeleteListener{
    private List<Person> arrayLists = new ArrayList<Person>();
    private Button mBtnAdd;
    private TextView tvResult;
    private MyAdapter myadapter;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bmob.initialize(this, "2e09ecb53eab7b6bf22f7d7a31f5f22e");
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        LinearLayoutManager manager = new LinearLayoutManager(MainActivity.this);
        mRecyclerView.setLayoutManager(manager);
        myadapter = new MyAdapter(arrayLists,MainActivity.this);
        mRecyclerView.setAdapter(myadapter);

        mBtnAdd = (Button) findViewById(R.id.btnAdd);
        mBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });
        queryData();

    }

    @Override
    protected void onResume() {
        super.onResume();
            queryData();
    }

    private void queryData() {
        BmobQuery<Person> bmobQuery = new BmobQuery<Person>();
        bmobQuery.findObjects(new FindListener<Person>() {
            @Override
            public void done(List<Person> list, BmobException e) {
                if (e == null) {
                    arrayLists=list;
                    myadapter.changData(arrayLists);
                    ((TextView) findViewById(R.id.tvResult)).setText("数据返回" + arrayLists.size());
                    myadapter.notifyDataSetChanged();
                }

            }
        });
    }



    @Override
    public void delete(String name) {

    }

    public void refresh() {
            queryData();
        }

}

