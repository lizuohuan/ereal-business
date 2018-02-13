package com.magic.ereal.business.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 业务实体
 * Created by Eric Xie on 2017/5/18 0018.
 */
public class JobTime implements Serializable {

    /**某一天*/
    private Date date;

    /**某一天的值*/
    private Double jobTime;

    private String jobTimeStr;

    public String getJobTimeStr() {
        return jobTimeStr;
    }

    public void setJobTimeStr(String jobTimeStr) {
        this.jobTimeStr = jobTimeStr;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getJobTime() {
        return jobTime;
    }

    public void setJobTime(Double jobTime) {
        this.jobTime = jobTime;
    }


}
