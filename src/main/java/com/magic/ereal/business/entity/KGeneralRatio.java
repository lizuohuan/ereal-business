package com.magic.ereal.business.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * K常规打分
 * Created by Eric Xie on 2017/6/30 0030.
 */
public class KGeneralRatio implements Serializable {


    private Integer id;

    private Integer userId;

    private String userName;

    private Double ratio;

    private Date startTime;

    private Date endTime;

    private Integer createUserId;

    private String createUserName;

    private Date createTime;

    private Integer jobTypeId;

    private String jobTypeName;

    private double score;

    /** 额定工作时间 */
    private double jobTypeTime;


    public double getScore() {
        return score;
    }

    public KGeneralRatio setScore(double score) {
        this.score = score;
        return this;
    }

    public String getJobTypeName() {
        return jobTypeName;
    }

    public void setJobTypeName(String jobTypeName) {
        this.jobTypeName = jobTypeName;
    }

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Double getRatio() {
        return ratio;
    }

    public void setRatio(Double ratio) {
        this.ratio = ratio;
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

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getJobTypeId() {
        return jobTypeId;
    }

    public void setJobTypeId(Integer jobTypeId) {
        this.jobTypeId = jobTypeId;
    }

    /** 获取 额定工作时间 */
    public double getJobTypeTime() {
        return this.jobTypeTime;
    }

    /** 设置 额定工作时间 */
    public void setJobTypeTime(double jobTypeTime) {
        this.jobTypeTime = jobTypeTime;
    }
}
