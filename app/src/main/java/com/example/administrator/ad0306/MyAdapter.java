package com.example.administrator.ad0306;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by yls on 2017/3/6.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {

    List<Person> array;
    DeleteListener listener;
    MyAdapter(List<Person> array,DeleteListener listener){
        this.array=array;
        this.listener=listener;
    }

    public void changData(List<Person> arrayLists) {
        this.array=arrayLists;
        notifyDataSetChanged();

    }

    public class MyHolder extends RecyclerView.ViewHolder {
        ImageView image,ivDelete;
        TextView tvName,tvAge,tvAddress;
        public MyHolder(View itemView) {
            super(itemView);
            image= (ImageView) itemView.findViewById(R.id.image);
            ivDelete= (ImageView) itemView.findViewById(R.id.ivDelete);
            tvName= (TextView) itemView.findViewById(R.id.tvName);
            tvAge= (TextView) itemView.findViewById(R.id.tvAge);
            tvAddress= (TextView) itemView.findViewById(R.id.tvAddress);

        }
    }
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.person_item,parent,false);
        MyHolder holder=new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        final Person person=array.get(position);
        BmobFile file= person.getImage();
        if(file!=null){
            String imgUrl=file.getFileUrl();
            if(imgUrl!=null){
                Glide.with(holder.image.getContext()).load(imgUrl).into(holder.image);
            }
        }
        holder.tvName.setText(person.getName());
        holder.tvAge.setText(String.valueOf(person.getAge()));
        holder.tvAddress.setText(person.getAddress());
        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                person.delete(new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        listener.refresh();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return array.size();
    }


}
