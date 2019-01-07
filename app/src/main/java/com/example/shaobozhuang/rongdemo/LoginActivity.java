package com.example.shaobozhuang.rongdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    @BindView(R.id.etId)
    EditText etId;
    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.btLogin)
    Button btLogin;
    final OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btLogin)
    public void onViewClicked() {
        String trim = etId.getText().toString();
        if (TextUtils.isEmpty(trim)) {
            Toast.makeText(this, "id不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!TextUtils.isEmpty(trim.trim())) {
            String token = App.map.get(trim);
            RongIM.connect(token, new RongIMClient.ConnectCallback() {
                @Override
                public void onTokenIncorrect() {
                    Log.e(TAG, "reToken Incorrect");
                }

                @Override
                public void onSuccess(String s) {
                    Toast.makeText(LoginActivity.this, "登录成功！" + s, Toast.LENGTH_SHORT).show();
                    new Thread(){
                        @Override
                        public void run() {
                            super.run();
                            post("http://api-cn.ronghub.com/user/checkOnline", "{\"userId\":\"001\"}");
                        }
                    }.start();

                }

                @Override
                public void onError(RongIMClient.ErrorCode e) {

                }
            });
        } else {
            Toast.makeText(this, "id不在后台数据库中，请去配置", Toast.LENGTH_SHORT).show();
        }

    }

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

   public void post(String url, String json) {
        try {
            RequestBody body = RequestBody.create(JSON, json);
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
            Response response = null;
            response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                Log.i(TAG, "post: -"+response.body().string());
            } else {
                Log.i(TAG, "post: Unexpected-"+response.message());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
