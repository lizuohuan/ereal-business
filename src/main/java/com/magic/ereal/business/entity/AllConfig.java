package com.magic.ereal.business.entity;

import java.io.Serializable;

/**
 * 全局配置
 * Created by Eric Xie on 2017/5/18 0018.
 */
public class AllConfig implements Serializable {

    private Integer id;

    /** 1 天 等于多少小时 默认 8.5小时*/
    private Double dayHour;

    /**1 K 等于多少小时  默认 135小时*/
    private Double kHour;

    /** k目标 */
    private Double kTarget;

    /** 基本薪资  */
    private Double baseSalary;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getDayHour() {
        return dayHour;
    }

    public void setDayHour(Double dayHour) {
        this.dayHour = dayHour;
    }

    public Double getkHour() {
        return kHour;
    }

    public void setkHour(Double kHour) {
        this.kHour = kHour;
    }

    public Double getkTarget() {
        return kTarget;
    }

    public void setkTarget(Double kTarget) {
        this.kTarget = kTarget;
    }

    public Double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(Double baseSalary) {
        this.baseSalary = baseSalary;
    }
}
