package com.magic.ereal.business.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 配置 每月 应出勤天数 -- 实体
 * @author lzh
 * @create 2017/6/8 9:42
 */
public class MonthDays implements Serializable {


    /***/
    private Integer id;

    /** 月 */
    private Date dateTime;

    /** 应出勤天数 */
    private Integer days;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }
}
