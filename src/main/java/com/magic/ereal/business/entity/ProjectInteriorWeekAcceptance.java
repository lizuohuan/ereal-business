package com.magic.ereal.business.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 内部项目 周验收 entity
 * Created by Eric Xie on 2017/5/2 0002.
 */
public class ProjectInteriorWeekAcceptance implements Serializable {

    /**主键ID*/
    private Integer id;

    /**内部项目ID*/
    private Integer projectInteriorId;

    /**总进度 百分比*/
    private Double progress;

    /**P 值*/
    private Double p;

    /**A 值*/
    private Double a;

    /**N 值*/
    private Double n;

    /**E 值*/
    private Double e;

    /**L 值*/
    private Double l;

    /**审核状态  0:提交  1:验收完成 2:已分配K值*/
    private Integer status;

    /**本周K值增量*/
    private Double sumK;

    /**本周总K值*/
    private Double totalK;

    /**备注*/
    private String remarks;

    /**创建时间*/
    private Date createTime;

    /**更新时间*/
    private Date updateTime;

    /**更新时间 字符串*/
    private String createTimes;

    /**该周是否是增量  0：否  1：是*/
    private Integer isAdd;

    /**
     *  分配比例数据集合 该周
     */
    private List<ProjectInteriorWeekKAllocation> weekAllocations;

    /**该周用户所有的工时*/
    private List<UserH> userHs;


    public Double getTotalK() {
        return totalK;
    }

    public void setTotalK(Double totalK) {
        this.totalK = totalK;
    }

    public List<UserH> getUserHs() {
        return userHs;
    }

    public void setUserHs(List<UserH> userHs) {
        this.userHs = userHs;
    }

    public List<ProjectInteriorWeekKAllocation> getWeekAllocations() {
        return weekAllocations;
    }

    public void setWeekAllocations(List<ProjectInteriorWeekKAllocation> weekAllocations) {
        this.weekAllocations = weekAllocations;
    }


    public Integer getIsAdd() {
        return isAdd;
    }

    public void setIsAdd(Integer isAdd) {
        this.isAdd = isAdd;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectInteriorId() {
        return projectInteriorId;
    }

    public void setProjectInteriorId(Integer projectInteriorId) {
        this.projectInteriorId = projectInteriorId;
    }

    public Double getProgress() {
        return progress;
    }

    public void setProgress(Double progress) {
        this.progress = progress;
    }

    public Double getP() {
        return p;
    }

    public void setP(Double p) {
        this.p = p;
    }

    public Double getA() {
        return a;
    }

    public void setA(Double a) {
        this.a = a;
    }

    public Double getN() {
        return n;
    }

    public void setN(Double n) {
        this.n = n;
    }

    public Double getE() {
        return e;
    }

    public void setE(Double e) {
        this.e = e;
    }

    public Double getL() {
        return l;
    }

    public void setL(Double l) {
        this.l = l;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Double getSumK() {
        return sumK;
    }

    public void setSumK(Double sumK) {
        this.sumK = sumK;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateTimes() {
        return createTimes;
    }

    public void setCreateTimes(String createTimes) {
        this.createTimes = createTimes;
    }
}
