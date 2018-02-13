package com.magic.ereal.business.entity;

import java.io.Serializable;

/**
 *  项目效率及质量实时监控 成员
 * Created by Eric Xie on 2018/1/3 0003.
 */
public class ExportProjectControl implements Serializable {


    /** 项目成员名称 */
    private String userName;

    /** 当前总用时(小时) */
    private Double curreTotalH;

    /** 当前总K */
    private Double curreTotalK;

    /** 效率 */
    private Double efficiency;

    /** 当前所在项目的角色  0:A导师、1:项目经理 2:普通成员 */
    private Integer role;


    /** 获取 项目成员名称 */
    public String getUserName() {
        return this.userName;
    }

    /** 设置 项目成员名称 */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /** 获取 当前总用时(小时) */
    public Double getCurreTotalH() {
        return this.curreTotalH;
    }

    /** 设置 当前总用时(小时) */
    public void setCurreTotalH(Double curreTotalH) {
        this.curreTotalH = curreTotalH;
    }

    /** 获取 当前总K */
    public Double getCurreTotalK() {
        if(null == this.curreTotalK){
            return 0.0;
        }
        return this.curreTotalK;
    }

    /** 设置 当前总K */
    public void setCurreTotalK(Double curreTotalK) {
        this.curreTotalK = curreTotalK;
    }

    /** 获取 效率 */
    public Double getEfficiency() {
        return this.efficiency;
    }

    /** 设置 效率 */
    public void setEfficiency(Double efficiency) {
        this.efficiency = efficiency;
    }

    /** 获取 当前所在项目的角色  0:A导师、1:项目经理 2:普通成员 */
    public Integer getRole() {
        return this.role;
    }

    /** 设置 当前所在项目的角色  0:A导师、1:项目经理 2:普通成员 */
    public void setRole(Integer role) {
        this.role = role;
    }
}
