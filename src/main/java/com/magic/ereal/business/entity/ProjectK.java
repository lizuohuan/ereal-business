package com.magic.ereal.business.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 外部项目 内部结项 & 外部结项   K值比例分配
 * Created by Eric Xie on 2017/5/9 0009.
 */
public class ProjectK implements Serializable {

    /**主键ID*/
    private Integer id;

    /**项目验收 记录ID*/
    private Integer projectRecordId;

    /**用户ID*/
    private Integer userId;

    /**用户名字*/
    private String userName;

    /**比例分配*/
    private Double ratio;

    /**当条记录K值*/
    private Double sumK;

    /**创建时间*/
    private Date createTime;

    /** 当前所在项目的角色  0:A导师、1:项目经理 2:普通成员 */
    private Integer role;

    /**创建人*/
    private Integer createUserId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectRecordId() {
        return projectRecordId;
    }

    public void setProjectRecordId(Integer projectRecordId) {
        this.projectRecordId = projectRecordId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Double getRatio() {
        return ratio;
    }

    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }

    public Double getSumK() {
        return sumK;
    }

    public void setSumK(Double sumK) {
        this.sumK = sumK;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
