package com.magic.ereal.business.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 传递卡/工作日志  状态显示 详情对象
 *  如果角色是 干事 则显示 部门名称，其他则显示其 角色名称
 * Created by Eric Xie on 2017/4/21 0021.
 */
public class WorkDiaryStatusDetail implements Serializable {

    /**主键ID*/
    private Integer id;

    /**创建者ID*/
    private Integer userId;

    /**用户头像*/
    private String avatar;

    /**用户姓名*/
    private String userName;

    /**用户的角色名称*/
    private String roleName;

    /**用户部门名称*/
    private String departmentName;

    /**用户的角色ID*/
    private Integer roleId;

    /**备注*/
    private String notes;

    /**状态描述 ：提交工作日志 / 审核不通过 .....*/
    private String statusDescribe;

    /**工作日志/传递卡 ID*/
    private Integer workDiaryId;

    /**创建时间*/
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getStatusDescribe() {
        return statusDescribe;
    }

    public void setStatusDescribe(String statusDescribe) {
        this.statusDescribe = statusDescribe;
    }

    public Integer getWorkDiaryId() {
        return workDiaryId;
    }

    public void setWorkDiaryId(Integer workDiaryId) {
        this.workDiaryId = workDiaryId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
