package com.magic.ereal.business.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 内部项目 周验收发比例分配 entity
 * Created by Eric Xie on 2017/5/2 0002.
 */
public class ProjectInteriorWeekKAllocation implements Serializable {

    /**主键ID*/
    private Integer id;

    /**用户ID*/
    private Integer userId;

    /**分配的比例 单位 百分比*/
    private Double ratio;

    /**计算出来的K值*/
    private Double k;

    /**内部项目分配的周ID*/
    private Integer weekId;

    /**用户名称*/
    private String userName;

    /** 分配时间 */
    private Date createTime;

    /** 是否是项目经理 */
    private Integer isManager;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Double getK() {
        return k;
    }

    public void setK(Double k) {
        this.k = k;
    }

    public Integer getWeekId() {
        return weekId;
    }

    public void setWeekId(Integer weekId) {
        this.weekId = weekId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getIsManager() {
        return isManager;
    }

    public void setIsManager(Integer isManager) {
        this.isManager = isManager;
    }
}
