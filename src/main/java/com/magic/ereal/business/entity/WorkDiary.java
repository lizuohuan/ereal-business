package com.magic.ereal.business.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 传递卡/工作日志 entity
 * Created by Eric Xie on 2017/4/20 0020.
 */
public class WorkDiary implements Serializable {

    /**主键ID*/
    private Integer id;

    /**工作日期*/
    private Date workTime;


    /**传递卡/工作日志 状态*/
    private Integer status;

    /**审核人Id*/
    private Integer verifier;

    /**审核人名字*/
    private String verifierName;

    /**创建人*/
    private Integer userId;
    private String userName;

    /**创建人对象*/
    private User user;

    /**创建时间*/
    private Date createTime;

    /**最后更新时间*/
    private Date updateTime;

    /**当天工作的日志列表*/
    private List<WorkDiarySub> workDiarySubs;

    /**状态进度详情集合*/
    private List<WorkDiaryStatusDetail> details;

    /**抄送人集合*/
    private List<WorkDiaryCc> workDiaryCcList;

    /**当前人 是否具有 审核权限*/
    private boolean isApproved;

    /** 部门名 */
    private String departmentName;

    /**总工时*/
    private Double totalTime;

    /** 当天个人工作学习时间 */
    private Double studyTime;

    /** 当天 个人运动时间 */
    private Double sportTime;

    public Double getStudyTime() {
        return studyTime;
    }

    public void setStudyTime(Double studyTime) {
        this.studyTime = studyTime;
    }

    public Double getSportTime() {
        return sportTime;
    }

    public void setSportTime(Double sportTime) {
        this.sportTime = sportTime;
    }

    public Double getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Double totalTime) {
        this.totalTime = totalTime;
    }

    public boolean getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(boolean approved) {
        isApproved = approved;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getWorkTime() {
        return workTime;
    }

    public void setWorkTime(Date workTime) {
        this.workTime = workTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getVerifier() {
        return verifier;
    }

    public void setVerifier(Integer verifier) {
        this.verifier = verifier;
    }

    public String getVerifierName() {
        return verifierName;
    }

    public void setVerifierName(String verifierName) {
        this.verifierName = verifierName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public List<WorkDiaryStatusDetail> getDetails() {
        return details;
    }

    public void setDetails(List<WorkDiaryStatusDetail> details) {
        this.details = details;
    }

    public List<WorkDiarySub> getWorkDiarySubs() {
        return workDiarySubs;
    }

    public void setWorkDiarySubs(List<WorkDiarySub> workDiarySubs) {
        this.workDiarySubs = workDiarySubs;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<WorkDiaryCc> getWorkDiaryCcList() {
        return workDiaryCcList;
    }

    public void setWorkDiaryCcList(List<WorkDiaryCc> workDiaryCcList) {
        this.workDiaryCcList = workDiaryCcList;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
