package com.example.administrator.vattihomepda;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by Administrator on 2020/8/31.
 */

public class StockOutScan extends AppCompatActivity {
    //包装条码
    private EditText meditText;
    //详细信息
    private Button button_detail;
    private TextView userView;
    private String username;
    private Integer total;
    private Integer scantotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stockoutscan);
        button_detail = (Button) findViewById(R.id.btn_detail);
        button_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StockOutScan.this,PackageDetailOut.class);
                startActivity(intent);
            }
        });



        meditText = (EditText) findViewById(R.id.txtBarCode);

        meditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // 这两个条件必须同时成立，如果仅仅用了enter判断，就会执行两次
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    String scancode=meditText.getText().toString();
                    scancode = scancode.replace("\n","");
                    scancode = scancode.replace("\r","");
                    if(scancode.contains("/")){
                        scancode = scancode.substring(0,scancode.indexOf("/"));
                    }
                    if(StaticEntity.listpackage.size()>0){
                        for(int i=0;i<StaticEntity.listpackage.size();i++){
                            if(scancode.contains(StaticEntity.listpackage.get(i).getPackageid()) && StaticEntity.listpackage.get(i).getIsscancomplete().equals(0)){
                                StaticEntity.listpackage.get(i).setStockoutname(username);
                                StaticEntity.listpackage.get(i).setStockouttime(new Date());
                                StaticEntity.listpackage.get(i).setIsscancomplete(1);
                                if(scantotal ==null){
                                    scantotal=0;
                                }
                                scantotal=scantotal+1;
                                TextView totalscan = (TextView) findViewById(R.id.scantotal);
                                totalscan.setText(scantotal.toString());
                                meditText.setText("");
                                TextView barcode= (TextView) findViewById(R.id.barcodeinfo);
                                barcode.setText(scancode);
                                TextView ordertnumber = (TextView) findViewById(R.id.ordertnumberinfos);
                                ordertnumber.setText( StaticEntity.listpackage.get(i).getOrdertnumber().toString());
                                TextView labback = (TextView) findViewById(R.id.labbackinfo);
                                labback.setText("成功");
                                break;
                            }else if(scancode.contains(StaticEntity.listpackage.get(i).getPackageid()) && StaticEntity.listpackage.get(i).getIsscancomplete().equals(1)){
                                meditText.setText("");
                                TextView barcode= (TextView) findViewById(R.id.barcodeinfo);
                                barcode.setText(scancode);
                                TextView labback = (TextView) findViewById(R.id.labbackinfo);
                                labback.setText("此条已已经扫描");
                                break;
                            }else if((i==(StaticEntity.listpackage.size()-1)) && !scancode.equals(StaticEntity.listpackage.get(i).getPackageid())){
                                //弹出是否更换包装
                                meditText.setText("");
                                final String finalScancode = scancode;
                                AlertDialog alertDialog2 = new AlertDialog.Builder(StockOutScan.this)
                                        .setMessage("更换扫描订单号")
                                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {//添加"Yes"按钮
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                scanresulth();
                                                fristquest(finalScancode);
                                            }
                                        })
                                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {//添加取消
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                Toast.makeText(StockOutScan.this, "已经返回", Toast.LENGTH_SHORT).show();
                                            }
                                        })
                                        .create();
                                alertDialog2.show();
                            }
                        }
                        scanresult();
                    }else{
                        meditText.setText("");
                        fristquest(scancode);
                        scanresult();
                    }
                    // 执行发送消息等操作
                    //Toast.makeText(ChejianScan.this,result, LENGTH_LONG).show();
                }
                return false;
            }
        });


    }






    public void scanresult(){
        String username=StaticEntity.listuser.getUsername();
        if(total==scantotal && total !=null) {
            List<Map<String, Object>> ls = new ArrayList<Map<String, Object>>();
            for (int i = 0; i < StaticEntity.listpackage.size(); i++) {
                HashMap<String, Object> listm = new HashMap<String, Object>();
                if(StaticEntity.listpackage.get(i).getStockouttime() !=null){
                    listm.put("ordertnumber", StaticEntity.listpackage.get(i).getOrdertnumber());
                    listm.put("info", StaticEntity.listpackage.get(i).getInfo());
                    listm.put("packageid", StaticEntity.listpackage.get(i).getPackageid());
                    ls.add(listm);
                }
            }
            Gson gson2=new Gson();
            String js = StaticEntity.toURLEncoded(gson2.toJson(ls));
            String url = Constants.httpurl+"/warehousehttp_scanStockOutresult?scanusername="+username+"&packageresult="+js;
            String result = null;
            try {
                result = VattiHomeHttpQuest.packagescan(url);
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            StaticEntity.delpackage();
        }
    }


    public void fristquest(String scancode){
        String url =Constants.httpurl+"/warehousehttp_scanxin?scanloaction=4&packcode="+scancode+"&scanusername="+username;
        String result = null;
        try {
            result = VattiHomeHttpQuest.packagescan(url);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(result !=null){
            //JSONArray jsonArray = JSONArray.fromObject(result);
            //JSONObject jsonArray = new JSONObject(result);
            JSONArray jsonArray = null;
            try {
                //result = getJsonStrFromNetData(result);
                jsonArray = new JSONArray(result);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String ordertnumber =null;
            for(int i=0;i<jsonArray.length();i++){
                Package info = new Package();
                try {
                    info.setPackageid((jsonArray.getJSONObject(i).get("packageid")).toString());
                    info.setInfo((jsonArray.getJSONObject(i).get("info")).toString());
                    info.setPackagerpid((jsonArray.getJSONObject(i).get("packagerpid")).toString());
                    info.setOrdertnumber((jsonArray.getJSONObject(i).get("ordertnumber")).toString());
                    ordertnumber=(jsonArray.getJSONObject(i).get("ordertnumber")).toString();
                    info.setIsscancomplete(Integer.parseInt(jsonArray.getJSONObject(i).get("isscancomplete").toString()));
                    if(scancode.equals((jsonArray.getJSONObject(i).get("packageid")).toString()) && ((jsonArray.getJSONObject(i).get("isscancomplete").toString()).equals("0"))){
                        info.setIsscancomplete(1);
                        info.setStockinname(username);
                        info.setStockouttime(new Date());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                StaticEntity.addpackageinfo(info);
            }
            meditText.setText("");
            TextView barcode= (TextView) findViewById(R.id.barcodeinfo);
            TextView labback = (TextView) findViewById(R.id.labbackinfo);
            TextView totals = (TextView) findViewById(R.id.total);
            TextView totalscan = (TextView) findViewById(R.id.scantotal);
            TextView ordertnumber1 = (TextView) findViewById(R.id.ordertnumberinfos);
            ordertnumber1.setText(ordertnumber);
            //scantotal=1;
            scantotal=StaticEntity.yiScanStockOutPackageCount();
            totalscan.setText(scantotal.toString());
            //totalscan.setText("1");
            total=jsonArray.length();
            totals.setText(total.toString());
            barcode.setText(scancode);
            labback.setText("成功");
        }else{
            meditText.setText("");
            TextView labback = (TextView) findViewById(R.id.labbackinfo);
            labback.setText("请求失败");
            Toast.makeText(StockOutScan.this, "请求失败或条码错误", Toast.LENGTH_SHORT).show();
        }
    }



    public void scanresulth(){
        String username=StaticEntity.listuser.getUsername();
        List<Map<String, Object>> ls = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < StaticEntity.listpackage.size(); i++) {
            HashMap<String, Object> listm = new HashMap<String, Object>();
            if(StaticEntity.listpackage.get(i).getStockouttime() !=null){
                listm.put("ordertnumber", StaticEntity.listpackage.get(i).getOrdertnumber());
                listm.put("info", StaticEntity.listpackage.get(i).getInfo());
                listm.put("packageid", StaticEntity.listpackage.get(i).getPackageid());
                ls.add(listm);
            }
        }
        Gson gson2=new Gson();
        String js = StaticEntity.toURLEncoded(gson2.toJson(ls));
        String url = Constants.httpurl+"/warehousehttp_scanStockOutresult?scanusername="+username+"&packageresult="+js;
        String result = null;
        try {
            result = VattiHomeHttpQuest.packagescan(url);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (result==null){
            Toast.makeText(StockOutScan.this, "请求失败，请重新扫描再上传", Toast.LENGTH_SHORT).show();
        }else if(result.contains("success")){
            StaticEntity.delpackage();
        }
    }
}
