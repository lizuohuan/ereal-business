package com.magic.ereal.business.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 待审核的维度的数据
 * Created by Eric Xie on 2017/6/20 0020.
 */
public class PendingVeidoo implements Serializable {


    private Integer companyId;

    private String companyName;

    private Date startTime;

    private Date endTime;

    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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
}
