package com.example.administrator.vattihomepda;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

import static android.R.id.list;

/**
 * Created by Administrator on 2020/9/2.
 */

public class PackageDetail extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.packagedetail);
        //Spinner yiSpinner = (Spinner)findViewById(R.id.Spinner_yiscan);
        ArrayList<String> yiscan = new ArrayList<String>();
        ArrayList<String> noscan = new ArrayList<String>();
        //Spinner noSpinner = (Spinner)findViewById(R.id.Spinner_noscan);
        if(StaticEntity.listpackage.size()>0){
            for(int i=0;i<StaticEntity.listpackage.size();i++){
                if(StaticEntity.listpackage.get(i).getInorout().equals(1)){
                    yiscan.add(StaticEntity.listpackage.get(i).getPackagerpid());
                }else{
                    noscan.add(StaticEntity.listpackage.get(i).getPackagerpid());
                }
            }
        }
        ArrayList<String> scan = new ArrayList<String>();
        scan.add("已扫描");
        scan.addAll(yiscan);
        scan.add("未扫描");
        scan.addAll(noscan);

        ArrayAdapter<String> nadapter = new ArrayAdapter<String>(
                PackageDetail.this,android.R.layout.simple_list_item_1,scan);
        ListView nolistView = (ListView) findViewById(R.id.nolistView);
        nolistView.setAdapter(nadapter);//把构造好的适配器对象传进去
    }
}
