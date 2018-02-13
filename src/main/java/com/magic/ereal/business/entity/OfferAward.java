package com.magic.ereal.business.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 自定义奖项的颁发
 * Created by Eric Xie on 2017/8/21 0021.
 */
public class OfferAward implements Serializable {


    /** 主键ID */
    private Integer id;

    /** 自定义奖项ID */
    private Integer customAwardsId;

    /** 自定义奖项名称 */
    private String customAwardsName;

    /** 获奖类型 */
    private Integer type;

    /** 月度 */
    private Date month;

    /** 目标ID 如果自定义奖项是部门，则部门ID，否则用户ID */
    private Integer targetId;

    /** 目标名称 */
    private String targetName;

    /** 创建时间 */
    private Date createTime;

    /** 获取 主键ID */
    public Integer getId() {
        return this.id;
    }

    /** 设置 主键ID */
    public void setId(Integer id) {
        this.id = id;
    }

    /** 获取 自定义奖项ID */
    public Integer getCustomAwardsId() {
        return this.customAwardsId;
    }

    /** 设置 自定义奖项ID */
    public void setCustomAwardsId(Integer customAwardsId) {
        this.customAwardsId = customAwardsId;
    }

    /** 获取 自定义奖项名称 */
    public String getCustomAwardsName() {
        return this.customAwardsName;
    }

    /** 设置 自定义奖项名称 */
    public void setCustomAwardsName(String customAwardsName) {
        this.customAwardsName = customAwardsName;
    }

    /** 获取 月度 */
    public Date getMonth() {
        return this.month;
    }

    /** 设置 月度 */
    public void setMonth(Date month) {
        this.month = month;
    }

    /** 获取 目标ID 如果自定义奖项是部门，则部门ID，否则用户ID */
    public Integer getTargetId() {
        return this.targetId;
    }

    /** 设置 目标ID 如果自定义奖项是部门，则部门ID，否则用户ID */
    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
    }

    /** 获取 创建时间 */
    public Date getCreateTime() {
        return this.createTime;
    }

    /** 设置 创建时间 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /** 获取 目标名称 */
    public String getTargetName() {
        return this.targetName;
    }

    /** 设置 目标名称 */
    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    /** 获取 获奖类型 */
    public Integer getType() {
        return this.type;
    }

    /** 设置 获奖类型 */
    public void setType(Integer type) {
        this.type = type;
    }
}
