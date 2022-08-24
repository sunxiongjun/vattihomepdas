package com.example.administrator.vattihomepda;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Administrator on 2021/11/26.
 */

public class SectionProjectType extends AppCompatActivity {
    private Button button_stockinProject;
    private Button button_stockoutProject;
    private Button button_stockoutCodeProject;
    private Button button_stockinProjectSumbit;
    private Button buttonMoveWarehouseProject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sectionprojecttype);

        button_stockinProject = (Button) findViewById(R.id.btn_stockin_project);

        button_stockinProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SectionProjectType.this,StockinProject.class);
                startActivity(intent);
            }
        });

        button_stockoutProject = (Button) findViewById(R.id.btn_stockout_project);

        button_stockoutProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(SectionProjectType.this,StockoutProject.class);
                Intent intent = new Intent(SectionProjectType.this,StockOutProjectSumbit.class);
                startActivity(intent);
            }
        });

        button_stockoutCodeProject = (Button) findViewById(R.id.btn_stockout_code_project);

        button_stockoutCodeProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SectionProjectType.this,StockoutCodeProject.class);
                startActivity(intent);
            }
        });

        button_stockinProjectSumbit = (Button) findViewById(R.id.btn_stockin_project_sumbit);

        button_stockinProjectSumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SectionProjectType.this,StockInPorjectSumbit.class);
                startActivity(intent);
            }
        });



        buttonMoveWarehouseProject = (Button) findViewById(R.id.btn_all_move_warehose);

        buttonMoveWarehouseProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SectionProjectType.this,ProjectMoveWarehouseAll.class);
                startActivity(intent);
            }
        });
    }
}
