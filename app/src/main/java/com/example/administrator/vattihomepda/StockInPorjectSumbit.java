package com.example.administrator.vattihomepda;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.RestrictTo;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * Created by Administrator on 2022/3/16.
 */

public class StockInPorjectSumbit extends AppCompatActivity {
    //母码
    private EditText meditTexts;
    //库位条码
    private EditText stockTexts;
    //提交
    private Button button_detail;
    private TextView userView;
    private String username;
    private Integer total;
    private Integer scantotal;
    private String sss;

    private String monthercode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stock_inscan_project_sumbit);
        username = StaticEntity.listuser.getUsername();
        TextView test = (TextView) findViewById(R.id.labuserinfo);
        test.setText(username);


          //库位条码获取
        stockTexts = (EditText) findViewById(R.id.txtstockCodeProjects);
        stockTexts.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == android.view.KeyEvent.KEYCODE_ENTER && event.getAction() == android.view.KeyEvent.ACTION_DOWN){
                    String stockcode=stockTexts.getText().toString();
                    stockcode = stockcode.replace("\n","");
                    stockcode = stockcode.replace("\r","");
                    stockTexts.setText(stockcode);
                    sss=stockcode;
                    stockTexts.setText(stockcode);
                    meditTexts.setFocusable(true);
                    meditTexts.setFocusableInTouchMode(true);
                    meditTexts.requestFocus();
                }
                return false;
            }
        });

        //母码获取
        meditTexts = (EditText) findViewById(R.id.txtBarCodeProjects);

        meditTexts.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == android.view.KeyEvent.KEYCODE_ENTER && event.getAction() == android.view.KeyEvent.ACTION_DOWN){
                    String stockcoded=meditTexts.getText().toString();
                    stockcoded = stockcoded.replace("\n","");
                    stockcoded = stockcoded.replace("\r","");
                    meditTexts.setText(stockcoded);
                    monthercode=stockcoded;

                    scanresultD();
                }
                return false;
            }
        });


        button_detail = (Button) findViewById(R.id.btn_sumbit);
        button_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //请求提交后定位到
                scanresults();
                stockTexts.setText("");
                meditTexts.setText("");
                stockTexts.setFocusable(true);
                stockTexts.setFocusableInTouchMode(true);
                stockTexts.requestFocus();
            }
        });


    }




    public void scanresults(){
        String url = Constants.httpurl+"/packageTotalHttp_stockIn?username="+username+"&location="+sss+"&packageTid="+monthercode+"&appKey=GgZpukBRo0&appSecret=o4eroSApzrJlkISSdzqpSAjrEBSTPhA5";
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
        if (result.contains("success")){
            Toast.makeText(StockInPorjectSumbit.this, "扫描成功", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(StockInPorjectSumbit.this, result, Toast.LENGTH_SHORT).show();
        }
    }


    public void scanresultD(){
        String url = Constants.httpurl+"/packageTotalHttp_sum?packageTid="+monthercode+"&appKey=GgZpukBRo0&appSecret=o4eroSApzrJlkISSdzqpSAjrEBSTPhA5";
        String result = null;
        String ddd =null;
        try {
            result = VattiHomeHttpQuest.packagescan(url);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        if(result!=null){
            TextView totals = (TextView) findViewById(R.id.totald);
            //JSONObject json = JSONObject.from
            try {
                JSONObject jsonArrays = new JSONObject(result);
                ddd=jsonArrays.getString("success");
                totals.setText(ddd);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            //Toast.makeText(StockInPorjectSumbit.this, ddd, Toast.LENGTH_SHORT).show();
        }
        //totals.setText(Integer.parseInt(result));
    }
}
