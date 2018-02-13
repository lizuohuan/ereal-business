package com.magic.ereal.business.entity;

import java.io.Serializable;
import java.util.Date;

/**
 *
 *  团队奖项 entity
 *
 * Created by Eric Xie on 2017/7/3 0003.
 */
public class DepartmentAwards implements Serializable {

    /** 主键ID */
    private Integer id;

    /** 部门ID */
    private Integer departmentId;

    /** 部门名称 */
    private String departmentName;

    /** 奖项类型 奖项类型 0:三维绩效奖 1：结项奖 */
    private Integer type;

    /** 奖项名称 */
    private String awardsName;

    /** 针对月份 */
    private Date month;

    /** 创建时间 */
    private Date createTime;

    /** 确认数据人ID */
    private Integer createUserId;

    /** 确认数据人 名称 */
    private String createUserName;

    /** 排名 */
    private Integer ranking;


    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public Integer getId() {
        return id;
    }

    public String getAwardsName() {
        return awardsName;
    }

    public void setAwardsName(String awardsName) {
        this.awardsName = awardsName;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getMonth() {
        return month;
    }

    public void setMonth(Date month) {
        this.month = month;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }
}
