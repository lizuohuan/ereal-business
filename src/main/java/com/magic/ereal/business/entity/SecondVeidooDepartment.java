package com.magic.ereal.business.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 第二维 其他职能部门 计算方式 entity
 * Created by Eric Xie on 2017/7/19 0019.
 */
public class SecondVeidooDepartment implements Serializable {


    /**
     * ID
     */
    private Integer id;

    /**
     * 部门ID
     */
    private Integer departmentId;

    /** 部门名称 */
    private String departmentName;

    /**
     * 生效时间
     */
    private Date time;

    /**
     * 考核方式：
     * 考核方式一：完成任务数（量化的指标）/目标完成数（量化的指标）*100%
     * 考核方式二：值总评价分数*0.3+总经理评价分数*0.7
     * 考核方式三：指标权重 * 分数
     * 考核方式四：外部项目内部结项数/目标结项数100%
     * 考核方式五：外部项目外部结项数/目标结项数100%
     * 注：考核方式4、5属于第二期功能
     * */
    private Integer method;


    public String getDepartmentName() {
        return departmentName;
    }

    public SecondVeidooDepartment setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public SecondVeidooDepartment setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public SecondVeidooDepartment setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
        return this;
    }

    public Date getTime() {
        return time;
    }

    public SecondVeidooDepartment setTime(Date time) {
        this.time = time;
        return this;
    }

    public Integer getMethod() {
        return method;
    }

    public SecondVeidooDepartment setMethod(Integer method) {
        this.method = method;
        return this;
    }
}
