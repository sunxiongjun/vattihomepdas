package com.example.administrator.vattihomepda;

/**
 * Created by Administrator on 2021/12/2.
 */

public class ProductionProcesses {
    public Integer productionpid;   //工序ID
    public String productionpname;  //工序名称
    public String productionpdes;   //工序描述
    public Integer workShop;
    public String nextprodid;   //下面工序的ID
    public Integer getProductionpid() {
        return productionpid;
    }
    public String getProductionpname() {
        return productionpname;
    }

    public String getProductionpdes() {
        return productionpdes;
    }

    public Integer getWorkShop() {
        return workShop;
    }

    public String getNextprodid() {
        return nextprodid;
    }
    public void setProductionpid(Integer productionpid) {
        this.productionpid = productionpid;
    }

    public void setProductionpname(String productionpname) {
        this.productionpname = productionpname;
    }

    public void setProductionpdes(String productionpdes) {
        this.productionpdes = productionpdes;
    }

    public void setWorkShop(Integer workShop) {
        this.workShop = workShop;
    }

    public void setNextprodid(String nextprodid) {
        this.nextprodid = nextprodid;
    }
}
