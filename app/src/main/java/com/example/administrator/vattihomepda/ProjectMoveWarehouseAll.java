package com.example.administrator.vattihomepda;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Administrator on 2022/3/21.
 */

public class ProjectMoveWarehouseAll extends AppCompatActivity {
    //母码
    private EditText meditTextsMoveAll;
    //库位条码
    private EditText stockTextsMoveAll;
    //提交
    private Button MoveAll_detail;
    private String username;
    private String sss;

    private String monthercode;

    //选中类型整单或单个
    private String scanType;



    private List<String> list = new ArrayList<String>();
    private TextView textview;
    private Spinner spinnertext;
    private ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_move_warehouse_all);
        username = StaticEntity.listuser.getUsername();
        TextView test = (TextView) findViewById(R.id.labuserinfo);
        test.setText(username);
        initSpinner();



        //库位条码获取
        stockTextsMoveAll = (EditText) findViewById(R.id.txtassortCodeMove);
        stockTextsMoveAll.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == android.view.KeyEvent.KEYCODE_ENTER && event.getAction() == android.view.KeyEvent.ACTION_DOWN){
                    String stockcode=stockTextsMoveAll.getText().toString();
                    stockcode = stockcode.replace("\n","");
                    stockcode = stockcode.replace("\r","");
                    sss=stockcode;
                    stockTextsMoveAll.setText(stockcode);
                    meditTextsMoveAll.setFocusable(true);
                    meditTextsMoveAll.setFocusableInTouchMode(true);
                    meditTextsMoveAll.requestFocus();
                }
                return false;
            }
        });


        //母码获取
        meditTextsMoveAll = (EditText) findViewById(R.id.txtBarCode);

        meditTextsMoveAll.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == android.view.KeyEvent.KEYCODE_ENTER && event.getAction() == android.view.KeyEvent.ACTION_DOWN){
                    String stockcoded=meditTextsMoveAll.getText().toString();
                    stockcoded = stockcoded.replace("\n","");
                    stockcoded = stockcoded.replace("\r","");
                    meditTextsMoveAll.setText(stockcoded);
                    monthercode=stockcoded;
                }
                return false;
            }
        });



        MoveAll_detail = (Button) findViewById(R.id.btn_sumbit_romve);
        MoveAll_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //请求提交后定位到
                scanresults();
                meditTextsMoveAll.setText("");
                stockTextsMoveAll.setText("");
                stockTextsMoveAll.setFocusable(true);
                stockTextsMoveAll.setFocusableInTouchMode(true);
                stockTextsMoveAll.requestFocus();
            }
        });
    }




    public void scanresults(){
        StringBuffer url = new StringBuffer(Constants.httpurl+"/stockMoveProject_location?location="+sss+"&packageid="+monthercode+"&appKey=GgZpukBRo0&appSecret=o4eroSApzrJlkISSdzqpSAjrEBSTPhA5");
        if(scanType.equals("整单")){
            url.append("&isAllOrder=1");
        }else{
            url.append("&isAllOrder=2");
        }

        //String url = Constants.httpurl+"/packageTotalHttp_stockIn?username="+username+"&location="+sss+"&packageTid="+monthercode+"&appKey=GgZpukBRo0&appSecret=o4eroSApzrJlkISSdzqpSAjrEBSTPhA5";
        String result = null;
        try {
            result = VattiHomeHttpQuest.packagescan(url.toString());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (result.contains("success")){
            Toast.makeText(ProjectMoveWarehouseAll.this, "扫描成功", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(ProjectMoveWarehouseAll.this, result, Toast.LENGTH_SHORT).show();
        }
    }


    public void initSpinner(){
        list.add("整单");
        list.add("单个");
        //textview = (TextView) findViewById(R.id.textView1);
        spinnertext = (Spinner) findViewById(R.id.spinnerMoveWarehouse);
        //第二步：为下拉列表定义一个适配器
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        //第三步：设置下拉列表下拉时的菜单样式
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //第四步：将适配器添加到下拉列表上
        spinnertext.setAdapter(adapter);
        spinnertext.setOnItemSelectedListener(new ProjectMoveWarehouseAll.MySelectedListener());
    }



    class MySelectedListener implements AdapterView.OnItemSelectedListener{

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            //Toast.makeText(ProjectMoveWarehouseAll.this, adapter.getItem(position), Toast.LENGTH_SHORT).show();
            //parent.setVisibility(View.VISIBLE);
            String sfdsfwe= adapter.getItem(position);
            scanType=sfdsfwe;
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }
}
