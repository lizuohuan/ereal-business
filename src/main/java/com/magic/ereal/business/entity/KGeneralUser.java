package com.magic.ereal.business.entity;

import java.io.Serializable;

/**
 * K常规 用户信息
 * Created by Eric Xie on 2017/6/30 0030.
 */
public class KGeneralUser implements Serializable {

    /** 用户ID */
    private Integer userId;

    /** 事务类型ID */
    private Integer jobTypeId;

    /** 事务类型名称 */
    private String jobTypeName;

    /** 用户名称 */
    private String userName;

    /** 额定工作时间 (天) */
    private Double jobTypeTime;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getJobTypeId() {
        return jobTypeId;
    }

    public void setJobTypeId(Integer jobTypeId) {
        this.jobTypeId = jobTypeId;
    }

    public String getJobTypeName() {
        return jobTypeName;
    }

    public void setJobTypeName(String jobTypeName) {
        this.jobTypeName = jobTypeName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Double getJobTypeTime() {
        return jobTypeTime;
    }

    public void setJobTypeTime(Double jobTypeTime) {
        this.jobTypeTime = jobTypeTime;
    }
}
