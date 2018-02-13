package com.magic.ereal.business.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 项目组 entity 暂不用
 * Created by Eric Xie on 2017/4/20 0020.
 */
public class ProjectGroup implements Serializable {

    /**主键ID*/
    private Integer id;

    /**组员集合(包括 项目经理ID) 格式 1,2,3*/
    private String member;

    /**项目经理 ID*/
    private Integer projectManagerId;

    /**所属部门ID*/
    private Integer departmentId;

    /**全项目组人员集合 包括项目经理，可通过 项目经理ID 确认项目经理*/
    private List<User> members;

    /**项目经理*/
    private User projectManager;

    /**创建时间*/
    private Date createTime;

    /**更新时间*/
    private Date updateTime;

    /**创建者ID*/
    private Integer createUserId;

    /** 项目经理名字 */
    private String userName;

    /**项目组名称*/
    private String projectName;

    /**是否有效  0:无效 1:有效*/
    private Integer isValid;


    //业务
    /** 部门名 */
    private String departmentName;
    /** 创建人 */
    private String createUserName;

    /** 公司id */
    private Integer companyId;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public Integer getProjectManagerId() {
        return projectManagerId;
    }

    public void setProjectManagerId(Integer projectManagerId) {
        this.projectManagerId = projectManagerId;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    public User getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(User projectManager) {
        this.projectManager = projectManager;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
}
