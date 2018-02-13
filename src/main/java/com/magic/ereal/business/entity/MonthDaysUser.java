package com.magic.ereal.business.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 员工本月的出勤情况 或其他情况 -- 实体
 * @author lzh
 * @create 2017/6/8 9:47
 */
public class MonthDaysUser implements Serializable {


    private Integer id;

    /** 员工id */
    private Integer userId;

    /** 天数类型  1：请假
     2：本月转正前 上的天数类型
     3：本月月中入职未上天数类型
     4：本月离职后未上的天数类型 */
    private Integer type ;

    /** 天数 */
    private Integer dayNum;

    /** 员工名 */
    private String userName;


    private Integer monthDaysId;

    /**  */
    private Date dateTime;

    private Integer departmentId;

    private Integer companyId;

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getMonthDaysId() {
        return monthDaysId;
    }

    public void setMonthDaysId(Integer monthDaysId) {
        this.monthDaysId = monthDaysId;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getDayNum() {
        return dayNum;
    }

    public void setDayNum(Integer dayNum) {
        this.dayNum = dayNum;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
