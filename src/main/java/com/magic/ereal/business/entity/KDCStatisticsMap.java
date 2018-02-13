package com.magic.ereal.business.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * K 值统计(部门/公司)(统计图) -- 实体
 * @author lzh
 * @create 2017/5/22 17:27
 */
public class KDCStatisticsMap implements Serializable {

    /** 时间 */
    private Date dateTime;

    /** k */
    private Double k;

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Double getK() {
        return k;
    }

    public void setK(Double k) {
        this.k = k;
    }
}
