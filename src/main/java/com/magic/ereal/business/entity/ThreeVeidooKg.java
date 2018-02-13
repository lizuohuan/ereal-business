package com.magic.ereal.business.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 第三维  职能部门个人KG打分记录 / 个人K团队得分记录 entity
 * Created by Eric Xie on 2017/6/2 0002.
 */
public class ThreeVeidooKg implements Serializable {

    /**主键ID*/
    private Integer id;

    /**得分*/
    private Double score;

    /**用户ID*/
    private Integer userId;

    /**时间区间 - 开始*/
    private Date startTime;

    /**时间区间 - 结束*/
    private Date endTime;

    /**打分时间*/
    private Date createTime;

    /**0:职能部门个人KG得分 记录   1:个人K团队得分 记录*/
    private Integer type;

    /**创建者ID*/
    private Integer createUserId;

    /**0: 周时间  1：月时间*/
    private Integer dateType;

    private String userName;

    private String departmentName;


    private String companyName;

    public String getCompanyName() {
        return companyName;
    }

    public ThreeVeidooKg setCompanyName(String companyName) {
        this.companyName = companyName;
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

    public Integer getDateType() {
        return dateType;
    }

    public void setDateType(Integer dateType) {
        this.dateType = dateType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }
}
