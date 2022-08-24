package com.example.administrator.vattihomepda;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static android.widget.Toast.LENGTH_LONG;
import static com.example.administrator.vattihomepda.VattiHomeHttpQuest.packagescan;

/**
 * Created by Administrator on 2020/8/31.
 */

public class ChejianScan extends AppCompatActivity {
    private EditText meditText;
    private TextView userView;
    private String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chejianscan);
        username = StaticEntity.listuser.getUsername();
        TextView test= (TextView) findViewById(R.id.labuserinfo);
        test.setText(username);


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
                    Log.v("ChejianScan",scancode);
                    String url =Constants.httpurl+"/warehousehttp_scanChenJian?packcode="+scancode+"&scanusername="+username;
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
                    TextView barcode= (TextView) findViewById(R.id.barcodeinfo);
                    TextView labback = (TextView) findViewById(R.id.labbackinfo);
                    barcode.setText(scancode);
                    labback.setText(result);
                    // 执行发送消息等操作
                    //Toast.makeText(ChejianScan.this,result, LENGTH_LONG).show();
                }
                return false;
            }
        });

    }
}
