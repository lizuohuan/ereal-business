package com.magic.ereal.business.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 外部项目 结项记录 -- 实体
 * @author lzh
 * @create 2017/5/3 14:52
 */
public class ProjectAcceptanceRecord implements Serializable {


    private Integer id;

    /** 外部项目id */
    private Integer projectId;

    /** 验收类型 0：破题  1：内部  2：外部 */
    private Integer type;

    /** 验收状态 0：等待验收  1：通过  2：验收失败
     （10-20区间 内部结项）
      10：等待验收（a导师申请结项，b导师进行打分）
      11：等待c导师审核/抽查/打分（根据b导师打分显示）
      12：通过
      13：不通过（打回）*/
    private Integer status;

    /** 分数 */
    private Double score;

    /** 总k值 */
    private Double sumK;

    /**备注*/
    private String remarks;

    /** 记录时间 */
    private Date createTime;

    /** 创建者id */
    private Integer createUserId;
    /** 是否上升 0：否  1是 */
    private Integer isAdd;

    /** 本次和上次总k值的差 */
    private Double badSumK;

    /** 外部项目 内部结项 & 外部结项   K值比例分配集合 */
    private List<ProjectK> projectKs;

    /** 验收的   绩效专员 */
    private Integer performanceUserId;


    public Integer getPerformanceUserId() {
        return performanceUserId;
    }

    public void setPerformanceUserId(Integer performanceUserId) {
        this.performanceUserId = performanceUserId;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public List<ProjectK> getProjectKs() {
        return projectKs;
    }

    public void setProjectKs(List<ProjectK> projectKs) {
        this.projectKs = projectKs;
    }

    public Double getSumK() {
        return sumK;
    }

    public void setSumK(Double sumK) {
        this.sumK = sumK;
    }

    public Integer getIsAdd() {
        return isAdd;
    }

    public void setIsAdd(Integer isAdd) {
        this.isAdd = isAdd;
    }

    public Double getBadSumK() {
        return badSumK;
    }

    public void setBadSumK(Double badSumK) {
        this.badSumK = badSumK;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProjectAcceptanceRecord that = (ProjectAcceptanceRecord) o;

        return id.equals(that.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
