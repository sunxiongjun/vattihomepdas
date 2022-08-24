package com.example.administrator.vattihomepda;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static android.R.id.list;

/**
 * Created by Administrator on 2021/12/2.
 */

public class CharacterScan extends AppCompatActivity {
    private EditText meditText;   //系统条码
    private EditText problemDetail; //详细信息
    private List<String> list = new ArrayList<String>();
    private Spinner spinnertext;
    private ArrayAdapter<String> adapter;

    private ArrayAdapter<Map> adapters;
    private String username;
    private String systemCodeText;
    private Button button;

    private List<String> listProblen = new ArrayList<String>();
    private Spinner spinnertextProblem;
    private ArrayAdapter<String> adapterProblem;

    private Spinner spinnertextProblemT;
    private ArrayAdapter<String> adapterProblemT;


    private EditText total;//总件数
    private EditText txtNumber;//不良件数


/*    private List<String> sinperNumber = new ArrayList<String>();
    private Spinner sinperNumberText;
    private ArrayAdapter<String> adapterNumber;*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.character_scan);
        initSpinner();
        //initSpinnerProblem();
       // initSpinnerProblemT();
        //initSpinnerNumber();
        username = StaticEntity.listuser.getUsername();
        TextView test= (TextView) findViewById(R.id.labuserinfo);
        test.setText(username);


        //系统条码获取
        meditText = (EditText) findViewById(R.id.txtSystemCode);
        meditText.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == android.view.KeyEvent.KEYCODE_ENTER && event.getAction() == android.view.KeyEvent.ACTION_DOWN){
                    String stockcode=meditText.getText().toString();
                    stockcode = stockcode.replace("\n","");
                    stockcode = stockcode.replace("\r","");
                    meditText.setText(stockcode);
                    systemCodeText=stockcode;
                }
                return false;
            }
        });
        //详细说明获取
        problemDetail = (EditText) findViewById(R.id.problemDetail);
       total =(EditText) findViewById(R.id.total);
       txtNumber=(EditText)findViewById(R.id.txtNumbers);

        //提交至后台
        button = (Button) findViewById(R.id.btn_sumbit);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String detal =null;
                if(!problemDetail.getText().toString().equals("") && !problemDetail.getText().toString().isEmpty()){
                    try {
                        detal = new String(problemDetail.getText().toString().getBytes("UTF-8"),"UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
               StringBuffer url = new StringBuffer(Constants.httpurl+"/ordertWorkPointHttp_scan");
                if(systemCodeText!=null && !"".equals(systemCodeText)){
                    url.append("?systemNo=").append(systemCodeText)
                            .append("&userName=").append(username)
                            .append("&problemDetail=").append(detal)
                            .append("&productionCode=").append(SystemFunction.findIdProcesses(spinnertext.getSelectedItem().toString()))
                           // .append("&problemCode=").append(SystemFunction.findIdQualityProblem(spinnertextProblem.getSelectedItem().toString()))
                            .append("&appKey=").append("GgZpukBRo0")
                            .append("&appSecret=").append("o4eroSApzrJlkISSdzqpSAjrEBSTPhA5")
                            //.append("&problemCodeT=").append(SystemFunction.findIdQualityProblem(spinnertextProblemT.getSelectedItem().toString()))
                            .append("&txtNumber=").append(txtNumber.getText().toString())
                            .append("&total=").append(total.getText().toString());
                    try {
                        String result = VattiHomeHttpQuest.packagescan(url.toString());
                        JSONObject obj = new JSONObject(result);
                        if(obj.getString("success").contains("true")){
                            Toast.makeText(CharacterScan.this, "扫描成功", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(CharacterScan.this, obj.getString("msg"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    meditText.setText("");
                    meditText.setFocusable(true);
                    meditText.setFocusableInTouchMode(true);
                    meditText.requestFocus();
                }else{
                    Toast.makeText(CharacterScan.this, "请扫描系统单号", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    public void initSpinner(){
        if(StartInitializing.listProduction.size()>0){
            for(int i=0;i<StartInitializing.listProduction.size();i++){
                list.add(StartInitializing.listProduction.get(i).getProductionpdes());
            }
        }
        spinnertext = (Spinner) findViewById(R.id.spinner1);
        //第二步：为下拉列表定义一个适配器
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        //第三步：设置下拉列表下拉时的菜单样式
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //第四步：将适配器添加到下拉列表上
        spinnertext.setAdapter(adapter);
        spinnertext.setOnItemSelectedListener(new MySelectedListener());
    }


    class MySelectedListener implements AdapterView.OnItemSelectedListener{

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            parent.setVisibility(View.VISIBLE);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }
  /*  public void initSpinnerProblem(){
        if(StartInitializing.listQualityProblen.size()>0){
            for(int i=0;i<StartInitializing.listQualityProblen.size();i++){
                listProblen.add(StartInitializing.listQualityProblen.get(i).getName());
            }
        }
        //textproblem = (TextView) findViewById(R.id.textproblem);
        spinnertextProblem = (Spinner) findViewById(R.id.sinperproblem);
        //第二步：为下拉列表定义一个适配器
        adapterProblem = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_checked, listProblen);

        //adapterProblem = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listProblen);
        //第三步：设置下拉列表下拉时的菜单样式
        adapterProblem.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //第四步：将适配器添加到下拉列表上
        spinnertextProblem.setAdapter(adapterProblem);
        spinnertextProblem.setOnItemSelectedListener(new MyProblemSelectedListener());
    }


    class MyProblemSelectedListener implements AdapterView.OnItemSelectedListener{

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
           // Toast.makeText(CharacterScan.this, spinnertextProblem.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
            parent.setVisibility(View.VISIBLE);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    private void initSpinnerProblemT() {

        spinnertextProblemT = (Spinner) findViewById(R.id.sinperproblemT);
        //第二步：为下拉列表定义一个适配器
        adapterProblemT = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_checked, listProblen);

        //第三步：设置下拉列表下拉时的菜单样式
        adapterProblemT.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //第四步：将适配器添加到下拉列表上
        spinnertextProblemT.setAdapter(adapterProblem);
        spinnertextProblemT.setOnItemSelectedListener(new MyProblemSelectedListenerT());
    }

    class MyProblemSelectedListenerT implements AdapterView.OnItemSelectedListener{
        boolean isSpinnerFirst = true ;
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            // Toast.makeText(CharacterScan.this, spinnertextProblem.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
            if(isSpinnerFirst){
                view.setVisibility(View.INVISIBLE) ;
            }else{
                parent.setVisibility(View.VISIBLE);
            }
            isSpinnerFirst = false ;

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }*/

/*
    private void initSpinnerNumber() {
        sinperNumberText = (Spinner) findViewById(R.id.sinperNumber);
        String[] ss = {"1","2","3","4","5","6","7","8","9","10","11"};
        //第二步：为下拉列表定义一个适配器
        adapterNumber = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_checked, ss);

        //第三步：设置下拉列表下拉时的菜单样式
        adapterProblemT.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //第四步：将适配器添加到下拉列表上
        sinperNumberText.setAdapter(adapterNumber);
        sinperNumberText.setOnItemSelectedListener(new MyProblemSelectedListenerNumber());
    }

    class MyProblemSelectedListenerNumber implements AdapterView.OnItemSelectedListener{

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            // Toast.makeText(CharacterScan.this, spinnertextProblem.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
            parent.setVisibility(View.VISIBLE);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }
*/







}
