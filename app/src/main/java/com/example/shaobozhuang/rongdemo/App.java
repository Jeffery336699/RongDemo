package com.example.shaobozhuang.rongdemo;

import android.app.Application;

import java.util.HashMap;
import java.util.Map;

import io.rong.imkit.RongIM;

public class App extends Application {

    public static final Map<String,String> map=new HashMap<>();

    @Override
    public void onCreate() {
        super.onCreate();
        RongIM.init(this);
    }

    static {
        map.put("001","+yT8TDoDQyxuG7Ry4cgIgia/ZUzYgLbwf91+pDTMDKhZeEfuJBR1ynewXOmlaMENEx5hSAU2NpY7T4x2sXFPcQ==");//001,唐俊
        map.put("002","ub02CDdoIyOjxqz4MCY+X9WBj1YLFNGG1Kc7J+pTlzZtIE9Z/ekt+hMhkl+o2rYrDU+SAHvu+T4=");//002,谢凯
        map.put("003","wtWjRlDwoCrbn8qHCAUOpdWBj1YLFNGG1Kc7J+pTlzZtIE9Z/ekt+pkBVedxQ+q7T+rjBHHDLq4=");//003，小妹
    }
}
