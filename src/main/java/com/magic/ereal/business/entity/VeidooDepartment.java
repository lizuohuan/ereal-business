package com.magic.ereal.business.entity;

import java.io.Serializable;

/**
 * 三维统计 部门排名 entity
 * Created by Eric Xie on 2017/6/22 0022.
 */
public class VeidooDepartment implements Serializable,Comparable<VeidooDepartment> {

    /** 部门ID */
    private Integer departmentId;

    /** 三维总分 */
    private Double totalScore;

    /**部门名称*/
    private String departmentName;

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Double totalScore) {
        this.totalScore = totalScore;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public int compareTo(VeidooDepartment o) {
        return o.getTotalScore().compareTo(totalScore);
    }
}
