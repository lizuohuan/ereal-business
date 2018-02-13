package com.magic.ereal.business.entity;

import java.io.Serializable;

/**
 *  第二维度 配置
 * Created by Eric Xie on 2017/5/22 0022.
 */
public class SecondVeidoo implements Serializable {

    /**主键ID*/
    private Integer id;

    /**考核方式：
     项目团队：
     考核方式一：内部结项数/目标结项数*100%
     考核方式二：（（K内+K外）/（1.7*0.7））/目标结项数*100%
     考核方式三：外部项目内部结项数/目标结项数100%
     考核方式四：外部项目外部结项数/目标结项数100%

     职能部门：
     考核方式一：完成任务数（量化的指标）/目标完成数（量化的指标）*100%
     考核方式二：值总评价分数*0.3+总经理评价分数*0.7
     考核方式三：权重 * 分数 */
    private Integer method;

    /**
     * 针对部门类型：
     * 1：项目部
     * 2：其他职能部门
     */
    private Integer type;

    /**总经理权重 计算职能部门第二维*/
    private Double weightManager;

    /**值总权重 计算职能部门第二维*/
    private Double dutyManager;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMethod() {
        return method;
    }

    public void setMethod(Integer method) {
        this.method = method;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Double getWeightManager() {
        return weightManager;
    }

    public void setWeightManager(Double weightManager) {
        this.weightManager = weightManager;
    }

    public Double getDutyManager() {
        return dutyManager;
    }

    public void setDutyManager(Double dutyManager) {
        this.dutyManager = dutyManager;
    }
}
