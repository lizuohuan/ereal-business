package com.magic.ereal.business.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 外部项目周验收 entity
 * Created by Eric Xie on 2017/4/28 0028.
 */
public class ProjectWeekAcceptance implements Serializable {

    /**主键ID*/
    private Integer id;

    /**项目ID*/
    private Integer projectId;

    /**四阶段 打分详细 JSON数据*/
    private String sectionDetail;

    /**该周验收的K值 增量*/
    private Double sumK;

    /**该周验收的总K值*/
    private Double totalK;

    /**发起周验收时间*/
    private Date createTime;

    /**验收人提交周验收时间*/
    private Date updateTime;

    /**审核状态  0:提交  1:验收完成 2:已经分配比例*/
    private Integer status;

    /** 项目周验收报告后的 K值结果分配  */
    private List<ProjectWeekKAllocation> projectWeekKAllocations;

    /** 备注 */
    private String remark;

    /**该周是否是增量  0：否  1：是*/
    private Integer isAdd;


    public Double getTotalK() {
        return totalK;
    }

    public void setTotalK(Double totalK) {
        this.totalK = totalK;
    }

    public Integer getIsAdd() {
        return isAdd;
    }

    public void setIsAdd(Integer isAdd) {
        this.isAdd = isAdd;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getSectionDetail() {
        return sectionDetail;
    }

    public void setSectionDetail(String sectionDetail) {
        this.sectionDetail = sectionDetail;
    }

    public Double getSumK() {
        return sumK;
    }

    public void setSumK(Double sumK) {
        this.sumK = sumK;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<ProjectWeekKAllocation> getProjectWeekKAllocations() {
        return projectWeekKAllocations;
    }

    public void setProjectWeekKAllocations(List<ProjectWeekKAllocation> projectWeekKAllocations) {
        this.projectWeekKAllocations = projectWeekKAllocations;
    }
}
