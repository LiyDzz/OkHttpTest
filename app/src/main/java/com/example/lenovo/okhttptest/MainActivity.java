package com.example.lenovo.okhttptest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends Activity implements View.OnClickListener {
    private Button get, post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        get = (Button) findViewById(R.id.get);
        post = (Button) findViewById(R.id.post);

        get.setOnClickListener(this);
        post.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.get:
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        String url = "http://192.168.1.132:8080/netWork.json";
                        //实例化Okhttp对象
                        OkHttpClient client = new OkHttpClient();
                        //创建请求对象
                        Request request = new Request.Builder().url(url).build();
                        try {

                            //get同步请求
                           /* Response response = client.newCall(request).execute();
                            String reslut = response.body().string();
                            Log.e("tag", "Get同步请求：" + reslut);*/

                            Callback call = new Callback() {
                                @Override
                                public void onFailure(Call call, IOException e) {

                                }

                                @Override
                                public void onResponse(Call call, Response response) throws IOException {
                                    String string = response.body().string();
                                    Log.e("tag", "get异步请求：" + string);
                                }
                            };
                            client.newCall(request).enqueue(call);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }.start();

                break;
            case R.id.post:
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        String url2 = "http://192.168.1.132:8080/netWork.json";
                        MediaType mediaType = MediaType.parse("application/json charset=utf-8");
                        OkHttpClient httpClient = new OkHttpClient();
                        String json = "{\"name\":\"zhangsan\",\"age\":20}";
                        RequestBody body = RequestBody.create(mediaType, json);
                        Request request1 = new Request.Builder().url(url2).post(body).build();
                        try {
                            /*Response response = httpClient.newCall(request1).execute();
                            String s = response.body().string();
                            Log.e("tag", "post同步请求：" + s);*/

                            //发送请求
                            Callback calls = new Callback() {
                                @Override
                                public void onFailure(Call call, IOException e) {

                                }

                                @Override
                                public void onResponse(Call call, Response response) throws IOException {
                                    String s = response.body().string();
                                    Log.e("tag", "post异步请求：" + s);
                                }
                            };
                            httpClient.newCall(request1).enqueue(calls);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }.start();

                break;
        }
    }
}
