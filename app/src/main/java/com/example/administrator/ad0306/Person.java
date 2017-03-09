package com.example.administrator.ad0306;

import cn.bmob.v3.BmobObject;

/**
 * Created by yls on 2017/3/6.
 */

public class Person extends BmobObject{
    private int image;
    private String name;
    private int age;
    private String address;

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
