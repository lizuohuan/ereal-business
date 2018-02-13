package com.magic.ereal.business.entity;

import java.io.Serializable;

/**
 * 二维 统计 -- 列表
 * @author lzh
 * @create 2017/5/31 14:58
 */
public class TwoStatistics implements Serializable {


    /** 员工名 */
    private String userName;

    /** 部门名 */
    private String departmentName;

    /** 公司名 */
    private String companyName;

    /** 员工ID */
    private String userId;

    /** 部门ID */
    private String departmentId;

    /** 公司ID */
    private String companyId;

    /** 完成率 */
    private Double schedule;

    /** 目标结项 */
    private Double targetNum;

    /** 实际结项 */
    private Double actualNum;

    public Double getTargetNum() {
        return targetNum;
    }

    public TwoStatistics setTargetNum(Double targetNum) {
        this.targetNum = targetNum;
        return this;
    }

    public Double getActualNum() {
        return actualNum;
    }

    public TwoStatistics setActualNum(Double actualNum) {
        this.actualNum = actualNum;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Double getSchedule() {
        return schedule;
    }

    public void setSchedule(Double schedule) {
        this.schedule = schedule;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }
}
