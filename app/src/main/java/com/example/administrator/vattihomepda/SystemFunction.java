package com.example.administrator.vattihomepda;

/**
 * Created by Administrator on 2022/5/13.
 */

public class SystemFunction {

    public static Integer findIdQualityProblem(String name){
        for(int i=0;i<StartInitializing.listQualityProblen.size();i++){
            if(StartInitializing.listQualityProblen.get(i).getName().equals(name)){
                return StartInitializing.listQualityProblen.get(i).getCode();
            }
        }
        return null;
    }


    public static Integer findIdProcesses(String name){
        for(int i=0;i<StartInitializing.listProduction.size();i++){
            if(StartInitializing.listProduction.get(i).getProductionpdes().equals(name)){
                return StartInitializing.listProduction.get(i).getProductionpid();
            }
        }
        return null;
    }
}
