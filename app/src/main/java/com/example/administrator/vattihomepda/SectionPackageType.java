package com.example.administrator.vattihomepda;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static android.widget.Toast.LENGTH_LONG;
import static com.example.administrator.vattihomepda.VattiHomeHttpQuest.packagescan;


/**
 * Created by Administrator on 2020/8/31.
 */

public class SectionPackageType extends AppCompatActivity {
    private Button button_stockin;
    private Button button_stockout;
    private Button button_chejian;
    private Button button_assort;
    private Button button_stockinorder;
    private Button button_assortR;
    private Button button_project;
    private Button button_stockOutSideIn;
    private Button button_characterScan;
    private Button button_stockMove;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sectionpackagetype);


        button_stockOutSideIn = (Button) findViewById(R.id.btn_outSideStockIn);

        button_stockOutSideIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StaticEntity.delpackage();
                Intent intent = new Intent(SectionPackageType.this,StockInOutSide.class);
                startActivity(intent);
            }
        });



        button_stockin = (Button) findViewById(R.id.btn_stockin);

        button_stockin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StaticEntity.delpackage();
                Intent intent = new Intent(SectionPackageType.this,StockinScan.class);
                startActivity(intent);
            }
        });

        button_stockout = (Button) findViewById(R.id.btn_stockout);

        button_stockout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StaticEntity.delpackage();
                Intent intent = new Intent(SectionPackageType.this,StockOutScan.class);
                startActivity(intent);
            }
        });


        button_chejian = (Button) findViewById(R.id.btn_chejian);

        button_chejian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StaticEntity.delpackage();
                Intent intent = new Intent(SectionPackageType.this,ChejianScan.class);
                startActivity(intent);
            }
        });


        button_assort = (Button) findViewById(R.id.btn_assort);

        button_assort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StaticEntity.delpackage();
                Intent intent = new Intent(SectionPackageType.this,AssortScan.class);
                startActivity(intent);
            }
        });



/*        button_assortR = (Button) findViewById(R.id.btn_assortRetail);

        button_assortR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StaticEntity.delpackage();
                Intent intent = new Intent(SectionPackageType.this,AssortScanRetail.class);
                startActivity(intent);
            }
        });*/


        button_stockinorder = (Button) findViewById(R.id.btn_stockinorder);

        button_stockinorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SectionPackageType.this,StockinOrder.class);
                startActivity(intent);
            }
        });

       button_characterScan = (Button) findViewById(R.id.btn_characterscan);

        button_characterScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url =Constants.httpurl+"/qualityProblem_all?appKey=GgZpukBRo0&appSecret=o4eroSApzrJlkISSdzqpSAjrEBSTPhA5";
                try {
                    String ss = VattiHomeHttpQuest.packagescan(url);
                    if(ss!=null && !"".equals(ss)){
                        StartInitializing.listQualityProblen.clear();
                        JSONArray dd = new JSONArray(ss);
                        for(int i=0;i<dd.length();i++){
                            QualityProblem qualityProblem = new QualityProblem();
                            qualityProblem.setCode(Integer.parseInt(dd.getJSONObject(i).get("code").toString()));
                            qualityProblem.setName(dd.getJSONObject(i).get("name").toString());
                            StartInitializing.listQualityProblen.add(qualityProblem);
                        }
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
                Intent intent = new Intent(SectionPackageType.this,CharacterScan.class);
                startActivity(intent);
            }
        });


        button_project= (Button) findViewById(R.id.btn_project);
        button_project.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SectionPackageType.this,SectionProjectType.class);
                startActivity(intent);
            }
        });


        button_stockMove= (Button) findViewById(R.id.btn_stock_move);
        button_stockMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SectionPackageType.this,StockMoveWarehouse.class);
                startActivity(intent);
            }
        });
    }
}
