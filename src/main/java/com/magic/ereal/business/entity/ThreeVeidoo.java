package com.magic.ereal.business.entity;

import java.io.Serializable;

/**
 * 第三维 配置项
 * Created by Eric Xie on 2017/5/23 0023.
 */

public class ThreeVeidoo implements Serializable {

    private Integer id;

    /**指标名称*/
    private String targetName;

    /**权重*/
    private Integer weight;

    /**描述*/
    private String describe;

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

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
