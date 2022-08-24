package com.example.administrator.vattihomepda;

import java.util.Date;

/**
 * Created by Administrator on 2020/8/28.
 */

public class Package {
    public String packageid;
    public String ordertnumber;
    public String doorscancode;
    public String cabinetsname;
    public String cabinetsdes;
    public Integer packing; //包数
    public Integer isscancomplete; //是否扫描
    public String packagerpid;  //G01，G02等
    public String info;//柜体或门槔等
    public Integer inorout;//出入库
    public String materialcode;
    public Integer pmid;
    public Integer width;
    public String remark;
    public Integer boxid;   //IF纸箱ID
    public String createby;
    public Date createtime;
    public String updateby;
    public Date updatetime;

    public Date scantime;
    public String scanname;
    public String stockoutname;
    public Date stockouttime;
    public String stockinname;
    public Date stockintime;
    public Date assorttime;
    public String assortname;
    public String location;

    public String getPackageid() {
        return packageid;
    }

    public void setPackageid(String packageid) {
        this.packageid = packageid;
    }

    public String getOrdertnumber() {
        return ordertnumber;
    }

    public void setOrdertnumber(String ordertnumber) {
        this.ordertnumber = ordertnumber;
    }

    public String getDoorscancode() {
        return doorscancode;
    }

    public void setDoorscancode(String doorscancode) {
        this.doorscancode = doorscancode;
    }

    public String getCabinetsname() {
        return cabinetsname;
    }

    public void setCabinetsname(String cabinetsname) {
        this.cabinetsname = cabinetsname;
    }

    public String getCabinetsdes() {
        return cabinetsdes;
    }

    public void setCabinetsdes(String cabinetsdes) {
        this.cabinetsdes = cabinetsdes;
    }

    public Integer getPacking() {
        return packing;
    }

    public void setPacking(Integer packing) {
        this.packing = packing;
    }

    public Integer getIsscancomplete() {
        return isscancomplete;
    }

    public void setIsscancomplete(Integer isscancomplete) {
        this.isscancomplete = isscancomplete;
    }

    public String getPackagerpid() {
        return packagerpid;
    }

    public void setPackagerpid(String packagerpid) {
        this.packagerpid = packagerpid;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Integer getInorout() {
        return inorout;
    }

    public void setInorout(Integer inorout) {
        this.inorout = inorout;
    }

    public String getMaterialcode() {
        return materialcode;
    }

    public void setMaterialcode(String materialcode) {
        this.materialcode = materialcode;
    }

    public Integer getPmid() {
        return pmid;
    }

    public void setPmid(Integer pmid) {
        this.pmid = pmid;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getBoxid() {
        return boxid;
    }

    public void setBoxid(Integer boxid) {
        this.boxid = boxid;
    }

    public String getCreateby() {
        return createby;
    }

    public void setCreateby(String createby) {
        this.createby = createby;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getUpdateby() {
        return updateby;
    }

    public void setUpdateby(String updateby) {
        this.updateby = updateby;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Date getScantime() {
        return scantime;
    }

    public void setScantime(Date scantime) {
        this.scantime = scantime;
    }

    public String getScanname() {
        return scanname;
    }

    public void setScanname(String scanname) {
        this.scanname = scanname;
    }

    public String getStockoutname() {
        return stockoutname;
    }

    public void setStockoutname(String stockoutname) {
        this.stockoutname = stockoutname;
    }

    public Date getStockouttime() {
        return stockouttime;
    }

    public void setStockouttime(Date stockouttime) {
        this.stockouttime = stockouttime;
    }

    public String getStockinname() {
        return stockinname;
    }

    public void setStockinname(String stockinname) {
        this.stockinname = stockinname;
    }

    public Date getStockintime() {
        return stockintime;
    }

    public void setStockintime(Date stockintime) {
        this.stockintime = stockintime;
    }

    public Date getAssorttime() {
        return assorttime;
    }

    public void setAssorttime(Date assorttime) {
        this.assorttime = assorttime;
    }

    public String getAssortname() {
        return assortname;
    }

    public void setAssortname(String assortname) {
        this.assortname = assortname;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
