package com.magic.ereal.business.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 导出外部项目 entity
 * Created by Eric Xie on 2018/1/9 0009.
 */
public class ExportProjectK implements Serializable {


    private Date receiveTime;

    private Double score;

    private Double ARatio;

    private Double PMRatio;


    private Double totalK;

    private Double AK;

    private Double PMK;

    private List<UserK> userKList;

    /** 空余 */
    private Integer count;


    public Date getReceiveTime() {
        return this.receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public Double getScore() {
        return this.score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Double getARatio() {
        return this.ARatio;
    }

    public void setARatio(Double ARatio) {
        this.ARatio = ARatio;
    }

    public Double getPMRatio() {
        return this.PMRatio;
    }

    public void setPMRatio(Double PMRatio) {
        this.PMRatio = PMRatio;
    }

    public Double getTotalK() {
        return this.totalK;
    }

    public void setTotalK(Double totalK) {
        this.totalK = totalK;
    }

    public Double getAK() {
        return this.AK;
    }

    public void setAK(Double AK) {
        this.AK = AK;
    }

    public Double getPMK() {
        return this.PMK;
    }

    public void setPMK(Double PMK) {
        this.PMK = PMK;
    }

    public List<UserK> getUserKList() {
        return this.userKList;
    }

    public void setUserKList(List<UserK> userKList) {
        this.userKList = userKList;
    }

    public Integer getCount() {
        return this.count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
