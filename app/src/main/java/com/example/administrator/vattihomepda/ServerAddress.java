package com.example.administrator.vattihomepda;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;



/**
 * Created by Administrator on 2020/9/11.
 */

public class ServerAddress extends AppCompatActivity {
    private Button button;
    private EditText serveraddress;
    private SharedPreferences preferences;
    private SharedPreferences .Editor editor1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.serveraddress);
        serveraddress = (EditText) findViewById(R.id.serveraddress);

        preferences  = getSharedPreferences("SP_INFO", Context.MODE_WORLD_READABLE);


        button = (Button) findViewById(R.id.btn_ok);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ads=serveraddress.getText().toString();
                if(TextUtils.isEmpty(ads)){
                    Toast.makeText(ServerAddress.this, "服务器地址不能为空", Toast.LENGTH_SHORT).show();
                }else{
                    editor1 = preferences.edit();
                    editor1.putString("server",ads);
                    editor1.commit();
                    Intent intent = new Intent(ServerAddress.this,MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
