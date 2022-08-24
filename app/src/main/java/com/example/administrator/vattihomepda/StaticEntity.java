package com.example.administrator.vattihomepda;

/**
 * Created by Administrator on 2020/8/28.
 */
import android.util.Log;

import com.example.administrator.vattihomepda.User;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StaticEntity {
    public static User listuser = new User();
    public static List<Package> listpackage = new ArrayList<Package>();




    public static void adduser(String username,String userpassword){
        listuser.setUsername(username);
        listuser.setUserpassword(userpassword);
    }

    public static void addpackage(List<Package> con){
        listpackage.addAll(con);
    }
    public static void addpackageinfo(Package con){
        listpackage.add(con);
    }
    public static void delpackage(){
        StaticEntity.listpackage.clear();
    }


    public static Integer yiscanpackagecount(){
        int d=0;
        if(StaticEntity.listpackage.size()>0){
            for(int i=0;i<StaticEntity.listpackage.size();i++){
                if(StaticEntity.listpackage.get(i).getInorout()==1){
                    d++;
                }
            }
        }
        return d;
    }


    public static Integer yiScanStockOutPackageCount(){
        int d=0;
        if(StaticEntity.listpackage.size()>0){
            for(int i=0;i<StaticEntity.listpackage.size();i++){
                if(StaticEntity.listpackage.get(i).getIsscancomplete()==1){
                    d++;
                }
            }
        }
        return d;
    }









    public static String toURLEncoded(String paramString) {
        if (paramString == null || paramString.equals("")) {
            return "";
        }

        try
        {
            String str = new String(paramString.getBytes(), "UTF-8");
            str = URLEncoder.encode(str, "UTF-8");
            return str;
        }
        catch (Exception localException)
        {
            Log.e("toURLEncoded error:", "toURLEncoded: " );
        }

        return "";
    }



    }
