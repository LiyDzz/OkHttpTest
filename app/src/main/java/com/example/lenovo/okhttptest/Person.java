package com.example.lenovo.okhttptest;

import android.util.Log;

/**
 * Created by 李艳东 on 2017/5/9.
 */

public class Person {
    private String name;
    private String age;

    public String getAge() {
        Log.e("tag", "nihao");
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
