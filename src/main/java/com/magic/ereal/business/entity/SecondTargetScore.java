package com.magic.ereal.business.entity;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * 第二维 第三种计算方式打分 entity
 *
 * Created by Eric Xie on 2017/7/3 0003.
 */
public class SecondTargetScore implements Serializable {


    /** 主键ID */
    private Integer id;

    /** 打分 */
    private Double score;

    /** 第二维配置 */
    private Integer secondTargetId;

    /** 创建时间 */
    private Date  createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Integer getSecondTargetId() {
        return secondTargetId;
    }

    public void setSecondTargetId(Integer secondTargetId) {
        this.secondTargetId = secondTargetId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
