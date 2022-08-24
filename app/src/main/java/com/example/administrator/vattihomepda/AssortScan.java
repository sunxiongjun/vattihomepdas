package com.example.administrator.vattihomepda;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * Created by Administrator on 2020/8/31.
 */

public class AssortScan extends AppCompatActivity {
    private EditText meditText;
    private TextView userView;
    private String username;
    private EditText assortText;
    private String sss;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assortscan);
        username = StaticEntity.listuser.getUsername();
        TextView test= (TextView) findViewById(R.id.labuserinfo);
        test.setText(username);

        //assortText = (EditText) findViewById(R.id.txtassortCode);
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
                    //String stockcode=assortText.getText().toString();
                    String url =Constants.httpurl+"/warehousehttp_scanxin?scanloaction=2&packcode="+scancode+"&scanusername="+username+"&stockcode="+sss;
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
                    meditText.setText("");
                    assortText.setText("");
                    TextView barcode= (TextView) findViewById(R.id.barcodeinfo);
                    TextView labback = (TextView) findViewById(R.id.labbackinfo);
                    barcode.setText(scancode);
                    labback.setText(result);
                    assortText.setFocusable(true);
                    assortText.setFocusableInTouchMode(true);
                    assortText.requestFocus();
                    // 执行发送消息等操作
                    //Toast.makeText(ChejianScan.this,result, LENGTH_LONG).show();
                }
                return false;
            }
        });


        assortText = (EditText) findViewById(R.id.txtassortCode);
        assortText.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN){
                    String stockcode=assortText.getText().toString();
                    stockcode = stockcode.replace("\n","");
                    stockcode = stockcode.replace("\r","");
                    assortText.setText(stockcode);
                    sss=stockcode;
                    meditText.setFocusable(true);
                    meditText.setFocusableInTouchMode(true);
                    meditText.requestFocus();
                }
                return false;
            }
        });
    }
}
