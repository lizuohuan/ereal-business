package com.magic.ereal.business.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 请假情况 -- 实体
 * @author lzh
 * @create 2017/6/8 10:21
 */
public class MonthDaysLeave implements Serializable {


    private Integer id;

    /** 请假的员工id */
    private Integer userId;

    /** 员工名 */
    private String userName;

    /** 请假时间 */
    private Date leaveTime;

    /** 记录创建时间 */
    private Date createTime;

    /** 配置月id */
    private Integer monthDaysId;

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

    public Date getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(Date leaveTime) {
        this.leaveTime = leaveTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getMonthDaysId() {
        return monthDaysId;
    }

    public void setMonthDaysId(Integer monthDaysId) {
        this.monthDaysId = monthDaysId;
    }
}
