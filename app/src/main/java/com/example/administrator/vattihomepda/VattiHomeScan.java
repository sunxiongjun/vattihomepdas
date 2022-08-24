package com.example.administrator.vattihomepda;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_LONG;

/**
 * Created by Administrator on 2020/8/27.
 */

public class VattiHomeScan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vattihomescan);
        String username = StaticEntity.listuser.getUsername();
        TextView test= (TextView) findViewById(R.id.labScanInfo);
        test.setText(username);

    }
}
