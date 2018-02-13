package com.magic.ereal.business.entity;

import java.util.Date;

/**
 * 统计 个人 年 统计 12月
 * @author lzh
 * @create 2017/5/18 20:13
 */
public class KStatisticsPMonth {

    /** 月份 */
    private Date monthTime;

    /** 每月k值 */
    private Double tk;

    public Date getMonthTime() {
        return monthTime;
    }

    public void setMonthTime(Date monthTime) {
        this.monthTime = monthTime;
    }

    public Double getTk() {
        return tk;
    }

    public void setTk(Double tk) {
        this.tk = tk;
    }
}
