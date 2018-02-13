package com.magic.ereal.business.entity;

import java.io.Serializable;

/**
 * 导出内部项目 所用的 项目组 信息 entity
 * Created by Eric Xie on 2017/7/11 0011.
 */
public class ExcelUserK implements Serializable {

    /** 用户名 */
    private String userName;

    /** 用时 */
    private double time;

    /** K */
    private double k;

    /** 是否是项目经理 */
    private Integer isManager;
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public double getK() {
        return k;
    }

    public void setK(double k) {
        this.k = k;
    }

    public Integer getIsManager() {
        return isManager;
    }

    public void setIsManager(Integer isManager) {
        this.isManager = isManager;
    }
}
