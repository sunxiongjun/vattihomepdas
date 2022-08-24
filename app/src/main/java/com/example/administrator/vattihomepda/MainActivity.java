package com.example.administrator.vattihomepda;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static android.widget.Toast.LENGTH_LONG;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private EditText username;
    private EditText userpassword;
    private SharedPreferences preferences;
    private SharedPreferences serveradd;
    private SharedPreferences .Editor editor;
    private CheckBox remember;
    private Button serverbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = (EditText) findViewById(R.id.username1);
        userpassword = (EditText) findViewById(R.id.userpassword1);

        serveradd  = getSharedPreferences("SP_INFO", Context.MODE_PRIVATE);
        String name = serveradd.getString("server","");
        Constants.createurl(name);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        remember = (CheckBox) findViewById(R.id.remember);
        boolean isRemember = preferences.getBoolean("remember_password",false);
        if (isRemember){
            String Name = preferences.getString("Name","");
            String Password = preferences.getString("Password","");
            username.setText(Name);
            userpassword.setText(Password);
            remember.setChecked(true);
        }

        button = (Button) findViewById(R.id.btn_login);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = null;
                String usernam=username.getText().toString();
                String userpass=userpassword.getText().toString();
                if(TextUtils.isEmpty(usernam) || TextUtils.isEmpty(userpass)){
                    Toast.makeText(MainActivity.this, "用户名或者密码不能为空", Toast.LENGTH_SHORT).show();
                }else{
                    try {
                        result=VattiHomeHttpQuest.loginquest(Constants.httpurl+"/loginhttp?username="+usernam+"&userpassword="+userpass);
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if(result.contains("success")){
                        StaticEntity.adduser(usernam,userpass);
                        editor = preferences.edit();
                        if(remember.isChecked()){
                            editor.putBoolean("remember_password",true);
                            editor.putString("Name",usernam);
                            editor.putString("Password",userpass);
                        }else{editor.clear();}
                        editor.apply();
                        StaticEntity.delpackage();
                        denglu(result);
                        Intent intent = new Intent(MainActivity.this,SectionPackageType.class);
                        intent.putExtra("hello",result);
                        startActivity(intent);
                    }else {
                        Toast.makeText(MainActivity.this,result, LENGTH_LONG).show();
                    }
                }
            }
        });


        serverbutton= (Button) findViewById(R.id.btn_server);
        serverbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this,ServerAddress.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        /**
         * 在onCreateOptionsMenu执行后，菜单被显示前调用；如果菜单已经被创建，则在菜单显示前被调用。 同样的，
         * 返回true则显示该menu,false 则不显示; （可以通过此方法动态的改变菜单的状态，比如加载不同的菜单等） TODO
         * Auto-generated method stub

        Intent intent = new Intent(MainActivity.this,ServerAddress.class);
        startActivity(intent);*/
        //menu.add(Menu.NONE,  Menu.First+1 , 0, "设置");
        //return super.onPrepareOptionsMenu(menu);
        super.onCreateOptionsMenu(menu);
        //menu.add(0 , ITEM0 , 0 ,  "设置");
        //menu.findItem(ITEM0);
        return true;
    }

    public void denglu(String result){
        JSONObject jsonArray = null;
        StartInitializing.listProduction.clear();
        try {
            jsonArray = new JSONObject(result);
            String ss = jsonArray.getString("msg");
            if(!ss.contains("[]")){
                JSONArray dd = new JSONArray(ss);
                for(int i=0;i<dd.length();i++){
                    ProductionProcesses info = new ProductionProcesses();
                    info.setProductionpid(Integer.parseInt(dd.getJSONObject(i).get("productionpid").toString()));
                    info.setProductionpdes(dd.getJSONObject(i).get("productionpdes").toString());
                    StartInitializing.listProduction.add(info);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
