package com.magic.ereal.business.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 导出外部项目 entity
 * Created by Eric Xie on 2017/7/11 0011.
 */
public class ExcelProject implements Serializable {

    /** 项目ID */
    private Integer projectId;

    /** 客户单位 */
    private String customerUnit;

    /** 客户部门 */
    private String customerDepartment;

    /** 课题类型名称 */
    private String projectTypeName;

    /** 项目名字 */
    private String projectName;

    /** 项目简称 */
    private String projectNameShort;

    /** 项目所属部门 */
    private String departmentName;

    /** 项目进度 */
    private double progress;

    /** 项目启动时间 */
    private Date startTime;

    /** 项目经理 */
    private String projectManager;

    /** C导师名字 */
    private String cTeacherName;

    /** 客户定位 */
    private String customerRemarks;

    /** 项目风险 */
    private String riskRemarks;

    /** 项目状态 */
    private Integer status;

    /** 破题得分 */
    private double poScore;

    /** 破题时间 */
    private Date poTime;

    /** 内部结项得分 */
    private double interiorScore;

    /** 内部结项时间 */
    private Date interiorTime;

    /** 外部结项得分 */
    private double exteriorScore;

    /** 外部结项时间 */
    private Date exteriorTime;

    public Integer getConnectStatus() {
        return connectStatus;
    }

    public ExcelProject setConnectStatus(Integer connectStatus) {
        this.connectStatus = connectStatus;
        return this;
    }

    /** 项目状态 */
    private Integer connectStatus;

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getCustomerUnit() {
        return customerUnit;
    }

    public void setCustomerUnit(String customerUnit) {
        this.customerUnit = customerUnit;
    }

    public String getCustomerDepartment() {
        return customerDepartment;
    }

    public void setCustomerDepartment(String customerDepartment) {
        this.customerDepartment = customerDepartment;
    }

    public String getProjectTypeName() {
        return projectTypeName;
    }

    public void setProjectTypeName(String projectTypeName) {
        this.projectTypeName = projectTypeName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectNameShort() {
        return projectNameShort;
    }

    public void setProjectNameShort(String projectNameShort) {
        this.projectNameShort = projectNameShort;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public double getProgress() {
        return progress;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(String projectManager) {
        this.projectManager = projectManager;
    }

    public String getcTeacherName() {
        return cTeacherName;
    }

    public void setcTeacherName(String cTeacherName) {
        this.cTeacherName = cTeacherName;
    }

    public String getCustomerRemarks() {
        return customerRemarks;
    }

    public void setCustomerRemarks(String customerRemarks) {
        this.customerRemarks = customerRemarks;
    }

    public String getRiskRemarks() {
        return riskRemarks;
    }

    public void setRiskRemarks(String riskRemarks) {
        this.riskRemarks = riskRemarks;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public double getPoScore() {
        return poScore;
    }

    public void setPoScore(double poScore) {
        this.poScore = poScore;
    }

    public Date getPoTime() {
        return poTime;
    }

    public void setPoTime(Date poTime) {
        this.poTime = poTime;
    }

    public double getInteriorScore() {
        return interiorScore;
    }

    public void setInteriorScore(double interiorScore) {
        this.interiorScore = interiorScore;
    }

    public Date getInteriorTime() {
        return interiorTime;
    }

    public void setInteriorTime(Date interiorTime) {
        this.interiorTime = interiorTime;
    }

    public double getExteriorScore() {
        return exteriorScore;
    }

    public void setExteriorScore(double exteriorScore) {
        this.exteriorScore = exteriorScore;
    }

    public Date getExteriorTime() {
        return exteriorTime;
    }

    public void setExteriorTime(Date exteriorTime) {
        this.exteriorTime = exteriorTime;
    }
}
