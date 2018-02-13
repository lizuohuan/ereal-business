package com.magic.ereal.business.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 传递卡 导出 entity
 * Created by Eric Xie on 2017/7/11 0011.
 */
public class ExcelOfWorkDiary implements Serializable {


    /** 用户名 */
    private String userName;

    /** 工作日期 */
    private Date workTime;

    /** 名称 -对应表格 项目 */
    private String typeName;

    /** 工作内容 - 对应表格 阶段 */
    private String jobContent;

    /** 分类 */
    private String categoryName;

    /** 工作期间 - 实习期间 */
    private String during;

    /** 用时 */
    private double time;

    /** 性质 */
    private String transactionType;

    private Date startTime;

    private Date endTime;

    public Date getStartTime() {
        return startTime;
    }

    public ExcelOfWorkDiary setStartTime(Date startTime) {
        this.startTime = startTime;
        return this;
    }

    public Date getEndTime() {
        return endTime;
    }

    public ExcelOfWorkDiary setEndTime(Date endTime) {
        this.endTime = endTime;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getWorkTime() {
        return workTime;
    }

    public void setWorkTime(Date workTime) {
        this.workTime = workTime;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getJobContent() {
        return jobContent;
    }

    public void setJobContent(String jobContent) {
        this.jobContent = jobContent;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDuring() {
        return during;
    }

    public void setDuring(String during) {
        this.during = during;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
}
