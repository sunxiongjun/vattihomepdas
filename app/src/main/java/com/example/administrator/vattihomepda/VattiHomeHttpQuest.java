package com.example.administrator.vattihomepda;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by Administrator on 2020/8/27.
 */

public class VattiHomeHttpQuest {
    public static String loginquest(String httpurl) throws ExecutionException, InterruptedException, IOException{
        String result = null;
        URL url = new URL(httpurl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);
        final HttpURLConnection finalConnection = connection;
        FutureTask<String> ask1 = new FutureTask<>(new Callable<String>(){
            String result1 = null;
            @Override
            public String call() throws Exception {
                finalConnection.connect();
                if(finalConnection.getResponseCode() == 200){
                    InputStream is = finalConnection.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                    // 存放数据
                    StringBuffer sbf  = new StringBuffer();
                    String temp = null;
                    while ((temp = br.readLine()) != null) {
                        sbf.append(temp);
                        sbf.append("\r\n");
                    }
                    result1 = sbf .toString();
                }
                return result1;
            }
        });

        new Thread(ask1).start();
        try {
            result = ask1.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String packagescan(String httpurl) throws ExecutionException, InterruptedException, IOException{
        String result = null;
        URL url = new URL(httpurl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);
        final HttpURLConnection finalConnection = connection;
        FutureTask<String> ask1 = new FutureTask<>(new Callable<String>(){
            String result1 = null;
            @Override
            public String call() throws Exception {
                finalConnection.connect();
                if(finalConnection.getResponseCode() == 200){
                    InputStream is = finalConnection.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                    // 存放数据
                    StringBuffer sbf  = new StringBuffer();
                    String temp = null;
                    while ((temp = br.readLine()) != null) {
                        sbf.append(temp);
                        sbf.append("\r\n");
                    }
                    result1 = sbf .toString();
                }
                return result1;
            }
        });

        new Thread(ask1).start();
        try {
            result = ask1.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String packagescanpost(String httpurl, final String bodyjson) throws ExecutionException, InterruptedException, IOException{
        String result = null;
        URL url = new URL(httpurl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setConnectTimeout(15000);
        connection.setReadTimeout(60000);
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setUseCaches(false);
        final HttpURLConnection finalConnection = connection;
        System.out.print("ttttttttttttttttt");
        FutureTask<String> ask1 = new FutureTask<>(new Callable<String>(){
            String result1 = null;
            @Override
            public String call() throws Exception {
                finalConnection.connect();
                System.out.print("ssssssssssssssss");
                OutputStream os = finalConnection.getOutputStream();
                os.write(bodyjson.getBytes());
                //finalConnection.connect();
                os.flush();
                System.out.print("ddddddddddddddddddddddddddddd");
                if(finalConnection.getResponseCode() == 200){
                    InputStream is = finalConnection.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                    // 存放数据
                    StringBuffer sbf  = new StringBuffer();
                    String temp = null;
                    while ((temp = br.readLine()) != null) {
                        sbf.append(temp);
                        sbf.append("\r\n");
                    }
                    result1 = sbf .toString();
                    finalConnection.disconnect();
                }
                return result1;
            }
        });
        new Thread(ask1).start();
        try {
            result = ask1.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }



}
