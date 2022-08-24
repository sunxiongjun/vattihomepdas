package com.example.administrator.vattihomepda;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * Created by Administrator on 2021/11/26.
 */

public class StockinProject extends AppCompatActivity {
    private static final String TAG = StockinProject.class.getName();

    //母码
    private EditText meditTexts;
    //库位条码
    private EditText stockTexts;
    //详细信息
    private Button button_detail;
    private TextView userView;
    private String username;
    private Integer total;
    private Integer scantotal;
    private String sss;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stock_inscan_project);
        username = StaticEntity.listuser.getUsername();
        TextView test= (TextView) findViewById(R.id.labuserinfo);
        test.setText(username);



        button_detail = (Button) findViewById(R.id.btn_detailProject);
        button_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StockinProject.this,PackageDetail.class);
                startActivity(intent);
            }
        });


        meditTexts = (EditText) findViewById(R.id.txtBarCodeProject);

        meditTexts.setOnKeyListener(new View.OnKeyListener(){
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // 这两个条件必须同时成立，如果仅仅用了enter判断，就会执行两次
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    String scancode=meditTexts.getText().toString();
                    scancode = scancode.replace("\n","");
                    scancode = scancode.replace("\r","");
                    scanresults(scancode);
                    meditTexts.setText("");
                }
                return false;
            }
        });


        //库位条码获取
        stockTexts = (EditText) findViewById(R.id.txtstockCodeProject);
        stockTexts.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN){
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

    }

    public void scanresults(String scancode){
        String url = Constants.httpurl+"/packageTotalHttp_stockIn?username="+username+"&location="+sss+"&packageTid="+scancode+"&appKey=GgZpukBRo0&appSecret=o4eroSApzrJlkISSdzqpSAjrEBSTPhA5";
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
            Toast.makeText(StockinProject.this, "扫描成功", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(StockinProject.this, result, Toast.LENGTH_SHORT).show();
        }
    }






}
