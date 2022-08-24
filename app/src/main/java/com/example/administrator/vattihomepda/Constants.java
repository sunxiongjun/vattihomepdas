package com.example.administrator.vattihomepda;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Administrator on 2020/8/31.
 */

public class Constants {
    //public static final String  httpurl  = "http://192.168.8.243:8099";
    public static String httpurl;
    public static void createurl(String urls){
        httpurl=urls;
    }
}
