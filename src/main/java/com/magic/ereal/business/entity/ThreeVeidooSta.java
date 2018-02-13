package com.magic.ereal.business.entity;

import java.io.Serializable;

/**
 * 第三维 文化 指标分数  -- 实体
 * @author lzh
 * @create 2017/6/1 15:27
 */
public class ThreeVeidooSta implements Serializable {

    private Integer id;

    /**指标名称*/
    private String targetName;

    /**分数*/
    private Double score;

    private String msg;


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}
