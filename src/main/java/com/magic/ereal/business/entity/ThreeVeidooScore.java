package com.magic.ereal.business.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 第三维度 评分 entity
 * Created by Eric Xie on 2017/5/23 0023.
 */
public class ThreeVeidooScore implements Serializable {

    /**主键ID*/
    private Integer id;

    /**第三维度 配置项ID*/
    private Integer threeVerdooId;

    /**针对用户ID*/
    private Integer userId;

    /**评分数*/
    private Integer score;

    /**创建者ID*/
    private Integer createUserId;

    /**月份*/
    private Date monthTime;

    /**创建时间*/
    private Date createTime;

    /**针对用户的 名字*/
    private String userName;

    /**创建者名字*/
    private String createUserName;

    /**指标名称*/
    private String targetName;

    /**开始时间*/
    private Date startTime;

    /**结束时间*/
    private Date endTime;

    /**备注*/
    private String msg;

    /** 公司ID */
    private Integer companyId;

    /** 部门ID */
    private Integer departmentId;


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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getThreeVerdooId() {
        return threeVerdooId;
    }

    public void setThreeVerdooId(Integer threeVerdooId) {
        this.threeVerdooId = threeVerdooId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Date getMonthTime() {
        return monthTime;
    }

    public void setMonthTime(Date monthTime) {
        this.monthTime = monthTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    /** 获取 公司ID */
    public Integer getCompanyId() {
        return this.companyId;
    }

    /** 设置 公司ID */
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    /** 获取 部门ID */
    public Integer getDepartmentId() {
        return this.departmentId;
    }

    /** 设置 部门ID */
    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }
}
