package com.magic.ereal.business.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 传递卡 详情列 entity
 * Created by Eric Xie on 2017/4/21 0021.
 */
public class WorkDiarySub implements Serializable {

    /**主键ID*/
    private Integer id;

    /**项目类型 父ID*/
    private Integer fatherId;

    /**工作类型ID*/
    private Integer jobTypeId;

    /**时间类型*/
    private Integer timeTypeId;

    /**开始时间*/
    private Date startTime;

    /**结束时间*/
    private Date endTime;

    /**开始时间*/
    private String startTimeStr;

    /**结束时间*/
    private String endTimeStr;

    /**工作内容*/
    private String jobContent;

    /**创建时间*/
    private Date createTime;

    /**更新时间*/
    private Date updateTime;

    /**工作日志 主表ID*/
    private Integer workDiaryId;

    /**当工作类型为 项目时  绑定的项目ID*/
    private Integer projectId;

    // 业务字段
    private String jobTypeName;

    private String timeTypeName;

    private String transactionTypeName;
    /**开始时间戳*/
    private Long startTimeL;
    /**结束时间戳*/
    private Long endTimeL ;

    private Integer transactionSubId;
    private String transactionSubName;

    /***/
    private Integer transactionTypeId;

    public String getEndTimeStr() {
        return endTimeStr;
    }

    public void setEndTimeStr(String endTimeStr) {
        this.endTimeStr = endTimeStr;
    }

    public String getStartTimeStr() {
        return startTimeStr;
    }

    public void setStartTimeStr(String startTimeStr) {
        this.startTimeStr = startTimeStr;
    }

    public Integer getTransactionTypeId() {
        return transactionTypeId;
    }

    public void setTransactionTypeId(Integer transactionTypeId) {
        this.transactionTypeId = transactionTypeId;
    }

    public Integer getFatherId() {
        return fatherId;
    }

    public void setFatherId(Integer fatherId) {
        this.fatherId = fatherId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Long getStartTimeL() {
        return startTimeL;
    }

    public void setStartTimeL(Long startTimeL) {
        this.startTimeL = startTimeL;
    }

    public Long getEndTimeL() {
        return endTimeL;
    }

    public void setEndTimeL(Long endTimeL) {
        this.endTimeL = endTimeL;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getJobTypeId() {
        return jobTypeId;
    }

    public void setJobTypeId(Integer jobTypeId) {
        this.jobTypeId = jobTypeId;
    }

    public Integer getTimeTypeId() {
        return timeTypeId;
    }

    public void setTimeTypeId(Integer timeTypeId) {
        this.timeTypeId = timeTypeId;
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

    public String getJobContent() {
        return jobContent;
    }

    public void setJobContent(String jobContent) {
        this.jobContent = jobContent;
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

    public Integer getWorkDiaryId() {
        return workDiaryId;
    }

    public void setWorkDiaryId(Integer workDiaryId) {
        this.workDiaryId = workDiaryId;
    }

    public String getJobTypeName() {
        return jobTypeName;
    }

    public void setJobTypeName(String jobTypeName) {
        this.jobTypeName = jobTypeName;
    }

    public String getTimeTypeName() {
        return timeTypeName;
    }

    public void setTimeTypeName(String timeTypeName) {
        this.timeTypeName = timeTypeName;
    }

    public String getTransactionTypeName() {
        return transactionTypeName;
    }

    public void setTransactionTypeName(String transactionTypeName) {
        this.transactionTypeName = transactionTypeName;
    }

    public String getTransactionSubName() {
        return transactionSubName;
    }

    public void setTransactionSubName(String transactionSubName) {
        this.transactionSubName = transactionSubName;
    }

    public Integer getTransactionSubId() {
        return transactionSubId;
    }

    public void setTransactionSubId(Integer transactionSubId) {
        this.transactionSubId = transactionSubId;
    }
}
