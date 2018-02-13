package com.magic.ereal.business.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 第二维 第二种计算方式使用的打分，针对职能部门
 * Created by Eric Xie on 2017/7/6 0006.
 */
public class SecondTargetScoreDepartment implements Serializable {

    private Integer id;

    private Double dutyGrade;

    private Double managerGrade;

    private Integer departmentId;

    private Date startTime;

    private Date endTime;

    private String departmentName;

    private String companyName;

    private Date dutyGradeTime;

    private Date managerGradeTime;

    public Date getDutyGradeTime() {
        return dutyGradeTime;
    }

    public void setDutyGradeTime(Date dutyGradeTime) {
        this.dutyGradeTime = dutyGradeTime;
    }

    public Date getManagerGradeTime() {
        return managerGradeTime;
    }

    public void setManagerGradeTime(Date managerGradeTime) {
        this.managerGradeTime = managerGradeTime;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getDutyGrade() {
        return dutyGrade;
    }

    public void setDutyGrade(Double dutyGrade) {
        this.dutyGrade = dutyGrade;
    }

    public Double getManagerGrade() {
        return managerGrade;
    }

    public void setManagerGrade(Double managerGrade) {
        this.managerGrade = managerGrade;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
