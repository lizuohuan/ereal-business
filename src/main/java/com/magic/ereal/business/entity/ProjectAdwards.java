package com.magic.ereal.business.entity;

import java.io.Serializable;

/**
 *
 *  统计月度 结项奖 entity
 *
 * Created by Eric Xie on 2017/7/3 0003.
 */
public class ProjectAdwards implements Serializable {

    /** 部门ID */
    private Integer departmentId;

    /** 部门名称 */
    private String departmentName;

    /** 任务完成率 */
    private Double rwcl;

    /** 目标结项数 */
    private Integer targetNum;

    /** 已完成结项数 */
    private Integer finishNum;

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Double getRwcl() {
        return rwcl;
    }

    public void setRwcl(Double rwcl) {
        this.rwcl = rwcl;
    }

    public Integer getTargetNum() {
        return targetNum;
    }

    public void setTargetNum(Integer targetNum) {
        this.targetNum = targetNum;
    }

    public Integer getFinishNum() {
        return finishNum;
    }

    public void setFinishNum(Integer finishNum) {
        this.finishNum = finishNum;
    }
}
