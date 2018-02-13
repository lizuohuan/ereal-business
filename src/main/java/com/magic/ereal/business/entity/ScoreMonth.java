package com.magic.ereal.business.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 月度 值总评价得分 暂定用于K常规、K临时 计算标准
 * Created by Eric Xie on 2017/5/18 0018.
 */
public class ScoreMonth implements Serializable {

    /**主键ID*/
    private Integer id;

    /**创建者ID*/
    private Integer createUserId;

    /**得分*/
    private Double score;

    /**得分类型 暂定： 1：常规事务得分 2：临时事务得分*/
    private Integer type;

    /**针对的部门ID*/
    private Integer departmentId;

    /**月度*/
    private Date month;

    private Date  startTime;

    private Date endTime;

    /**创建时间*/
    private Date createTime;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Date getMonth() {
        return month;
    }

    public void setMonth(Date month) {
        this.month = month;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
