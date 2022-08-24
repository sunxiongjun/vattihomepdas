package com.example.administrator.vattihomepda;

/**
 * Created by Administrator on 2022/5/12.
 */

public class QualityProblem {
    public Integer code;
    public String name;

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }


    public void setCode(Integer code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
