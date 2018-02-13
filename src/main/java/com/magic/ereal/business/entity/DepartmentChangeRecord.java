package com.magic.ereal.business.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户部门变更记录
 * Created by Eric Xie on 2017/8/28 0028.
 */
public class DepartmentChangeRecord implements Serializable {


    /** 主键 */
    private Integer id;

    /** 用户ID */
    private Integer userId;

    /** 变更前部门ID */
    private Integer fromDepartmentId;

    /** 变更后部门ID */
    private Integer toDepartmentId;

    /** 变更月份 */
    private Date dateMonth;

    /** 创建时间 */
    private Date createTime;


    /** 获取 主键 */
    public Integer getId() {
        return this.id;
    }

    /** 设置 主键 */
    public void setId(Integer id) {
        this.id = id;
    }

    /** 获取 用户ID */
    public Integer getUserId() {
        return this.userId;
    }

    /** 设置 用户ID */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /** 获取 变更前部门ID */
    public Integer getFromDepartmentId() {
        return this.fromDepartmentId;
    }

    /** 设置 变更前部门ID */
    public void setFromDepartmentId(Integer fromDepartmentId) {
        this.fromDepartmentId = fromDepartmentId;
    }

    /** 获取 变更后部门ID */
    public Integer getToDepartmentId() {
        return this.toDepartmentId;
    }

    /** 设置 变更后部门ID */
    public void setToDepartmentId(Integer toDepartmentId) {
        this.toDepartmentId = toDepartmentId;
    }

    /** 获取 变更月份 */
    public Date getDateMonth() {
        return this.dateMonth;
    }

    /** 设置 变更月份 */
    public void setDateMonth(Date dateMonth) {
        this.dateMonth = dateMonth;
    }

    /** 获取 创建时间 */
    public Date getCreateTime() {
        return this.createTime;
    }

    /** 设置 创建时间 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
