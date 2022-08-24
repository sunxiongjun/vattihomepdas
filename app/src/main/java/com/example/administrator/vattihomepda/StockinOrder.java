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

import static android.widget.Toast.LENGTH_LONG;

/**
 * Created by Administrator on 2021/6/15.
 */

public class StockinOrder extends AppCompatActivity {
    private EditText meditText;
    private TextView userView;
    private String username;
    private EditText assortText;
    private String sss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stockinorder);
        username = StaticEntity.listuser.getUsername();
        TextView test= (TextView) findViewById(R.id.labuserinfo);
        test.setText(username);


        meditText = (EditText) findViewById(R.id.txtBarCodeorder);
        meditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // 这两个条件必须同时成立，如果仅仅用了enter判断，就会执行两次
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    String scancode=meditText.getText().toString();
                    scancode = scancode.replace("\n","");
                    scancode = scancode.replace("\r","");
                    //String stockcode=assortText.getText().toString();
                    String url =Constants.httpurl+"/warehousehttp_scanorder?scanloaction=2&packcode="+scancode+"&scanusername="+username+"&stockcode="+sss;
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
                    Toast.makeText(StockinOrder.this,"入库成功", LENGTH_LONG).show();
                }
                return false;
            }
        });


        assortText = (EditText) findViewById(R.id.txtassortCodeorder);
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
