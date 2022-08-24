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
 * Created by Administrator on 2021/11/26.
 */

public class StockoutCodeProject extends AppCompatActivity {
    private TextView userView;
    private String username;
    private Integer total;
    private Integer scantotal;

    //子码
    private EditText meditTexts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stockout_code_project);
        username = StaticEntity.listuser.getUsername();
        TextView test = (TextView) findViewById(R.id.labuserinfo);
        test.setText(username);


        meditTexts = (EditText) findViewById(R.id.txtSunBarCode);

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


    }


    public void scanresults(String scancode){
        String url = Constants.httpurl+"/packageTotalHttp_stockOutSub?scanusername="+username+"&packageSid="+scancode+"&appKey=GgZpukBRo0&appSecret=o4eroSApzrJlkISSdzqpSAjrEBSTPhA5";
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
            Toast.makeText(StockoutCodeProject.this, "请求失败，请重新扫描", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(StockoutCodeProject.this, result, Toast.LENGTH_SHORT).show();
        }
    }

}
