package com.example.administrator.vattihomepda;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * Created by Administrator on 2021/12/1.
 */

public class StockInOutSide extends AppCompatActivity {
    private EditText meditText;
    private TextView userView;
    private String username;
    private EditText outSideText;
    private String sss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stock_outside_in);
        username = StaticEntity.listuser.getUsername();
        TextView test = (TextView) findViewById(R.id.labuserinfo);
        test.setText(username);


        outSideText = (EditText) findViewById(R.id.txtOutsideBarCode);
        outSideText.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN){
                    String stockcode=outSideText.getText().toString();
                    stockcode = stockcode.replace("\n","");
                    stockcode = stockcode.replace("\r","");
                    outSideText.setText(stockcode);
                    sss=stockcode;
                    meditText.setFocusable(true);
                    meditText.setFocusableInTouchMode(true);
                    meditText.requestFocus();
                }
                return false;
            }
        });


        meditText = (EditText) findViewById(R.id.txtBarCodeOutside);

        meditText.setOnKeyListener(new View.OnKeyListener(){
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
                    String url = Constants.httpurl+"/warehousehttp_stockOutSideIn?scanusername="+username+"&packcode="+scancode+"&stockcode="+sss;
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
                    Toast.makeText(StockInOutSide.this, result, Toast.LENGTH_SHORT).show();
                    //scanresults(scancode,outSideText.getText().toString());
                    meditText.setText("");
                }
                return false;
            }
        });


    }



    public void scanresults(String scancode,String location){
        String url = Constants.httpurl+"/warehousehttp_stockOutSideIn?scanusername="+username+"&stockcode="+location+"&packcode="+scancode+"&appKey=GgZpukBRo0&appSecret=o4eroSApzrJlkISSdzqpSAjrEBSTPhA5";
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

        Toast.makeText(StockInOutSide.this, result, Toast.LENGTH_SHORT).show();
/*        if (result==null){
            Toast.makeText(StockInOutSide.this, "请求失败，请重新扫描", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(StockInOutSide.this, result, Toast.LENGTH_SHORT).show();
        }*/
    }
}
