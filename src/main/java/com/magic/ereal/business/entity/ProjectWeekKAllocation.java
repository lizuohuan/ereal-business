package com.magic.ereal.business.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 项目周验收报告后的 K值结果分配 详细
 * Created by Eric Xie on 2017/4/28 0028.
 */
public class ProjectWeekKAllocation implements Serializable {

    /**主键ID*/
    private Integer id;

    /**项目周验收 ID*/
    private Integer projectWeekAcceptanceId;

    /**项目周验收的 阶段ID*/
    private Integer projectTypeSectionId;

    /**被分配者的ID*/
    private Integer userId;

    /**分配比例 单位: %*/
    private Double ratio;

    /**当前阶段的 总K值*/
    private Double sectionSumK;

    /**创建时间*/
    private Date createTime;

    /**更新时间*/
    private Date updateTime;

    /**分配者 ID*/
    private Integer createUserId;

    /**阶段值  第一阶段 ... 值： 1 2 3 4*/
    private Integer sectionNum;

    /** 当前所在项目的角色  0:A导师、1:项目经理 2:普通成员 */
    private Integer role;

    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectWeekAcceptanceId() {
        return projectWeekAcceptanceId;
    }

    public void setProjectWeekAcceptanceId(Integer projectWeekAcceptanceId) {
        this.projectWeekAcceptanceId = projectWeekAcceptanceId;
    }

    public Integer getProjectTypeSectionId() {
        return projectTypeSectionId;
    }

    public void setProjectTypeSectionId(Integer projectTypeSectionId) {
        this.projectTypeSectionId = projectTypeSectionId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Double getRatio() {
        return ratio;
    }

    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }

    public Double getSectionSumK() {
        return sectionSumK;
    }

    public void setSectionSumK(Double sectionSumK) {
        this.sectionSumK = sectionSumK;
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

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Integer getSectionNum() {
        return sectionNum;
    }

    public void setSectionNum(Integer sectionNum) {
        this.sectionNum = sectionNum;
    }

    /** 获取 当前所在项目的角色  0:A导师、1:项目经理 2:普通成员 */
    public Integer getRole() {
        return this.role;
    }

    /** 设置 当前所在项目的角色  0:A导师、1:项目经理 2:普通成员 */
    public void setRole(Integer role) {
        this.role = role;
    }
}
