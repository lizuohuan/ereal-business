package com.magic.ereal.business.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Eric Xie on 2018/1/9 0009.
 */
public class ProjectWeekExport implements Serializable {


    private double totalK;

    private double AK;

    private double PMK;

    private List<UserK> totalUserKs;

    private double score;

    private String BName;

    private Date receiveTime;

    private int count;

    /** 阶段数据 */
    private List<WeekExport> weekExports;


    public double getTotalK() {
        return this.totalK;
    }

    public void setTotalK(double totalK) {
        this.totalK = totalK;
    }

    public double getAK() {
        return this.AK;
    }

    public void setAK(double AK) {
        this.AK = AK;
    }

    public double getPMK() {
        return this.PMK;
    }

    public void setPMK(double PMK) {
        this.PMK = PMK;
    }

    public List<UserK> getTotalUserKs() {
        return this.totalUserKs;
    }

    public void setTotalUserKs(List<UserK> totalUserKs) {
        this.totalUserKs = totalUserKs;
    }

    public double getScore() {
        return this.score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getBName() {
        return this.BName;
    }

    public void setBName(String BName) {
        this.BName = BName;
    }

    public Date getReceiveTime() {
        return this.receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    /** 获取 阶段数据 */
    public List<WeekExport> getWeekExports() {
        return this.weekExports;
    }

    /** 设置 阶段数据 */
    public void setWeekExports(List<WeekExport> weekExports) {
        this.weekExports = weekExports;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
