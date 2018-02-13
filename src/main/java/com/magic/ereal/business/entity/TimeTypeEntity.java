package com.magic.ereal.business.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 业务实体 工作类型
 * 用于 工作日志统计
 * Created by Eric Xie on 2017/4/24 0024.
 */
public class TimeTypeEntity implements Serializable {

    /**工作时长  单位：分*/
    private Double jobTime;

    /**工作名称*/
    private String timeName;

    /**时间类型ID*/
    private Integer timeTypeId;

    private Date workTime;

    private String userName;

    private Integer userId;


    public Date getWorkTime() {
        return workTime;
    }

    public void setWorkTime(Date workTime) {
        this.workTime = workTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Double getJobTime() {
        return jobTime;
    }

    public void setJobTime(Double jobTime) {
        this.jobTime = jobTime;
    }

    public String getTimeName() {
        return timeName;
    }

    public void setTimeName(String timeName) {
        this.timeName = timeName;
    }

    public Integer getTimeTypeId() {
        return timeTypeId;
    }

    public void setTimeTypeId(Integer timeTypeId) {
        this.timeTypeId = timeTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimeTypeEntity that = (TimeTypeEntity) o;

        return workTime.equals(that.workTime);

    }

    @Override
    public int hashCode() {
        return workTime.hashCode();
    }
}
