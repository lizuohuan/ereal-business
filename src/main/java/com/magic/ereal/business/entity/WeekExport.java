package com.magic.ereal.business.entity;

import java.io.Serializable;
import java.util.List;

/**
 *
 * Created by Eric Xie on 2018/1/9 0009.
 */
public class WeekExport implements Serializable {


    /** 阶段 进度 */
    private double schedule;

    /** 阶段 质量 */
    private double quality;

    /**阶段值  第一阶段 ... 值： 1 2 3 4*/
    private Integer sectionNum;

    /** 完成比例 */
    private double finishRatio;

    private double ARatio;

    private double PMRatio;

    private List<UserK> userKs;

    private int count;

    /** 获取 阶段 进度 */
    public double getSchedule() {
        return this.schedule;
    }

    /** 设置 阶段 进度 */
    public void setSchedule(double schedule) {
        this.schedule = schedule;
    }

    /** 获取 阶段 质量 */
    public double getQuality() {
        return this.quality;
    }

    /** 设置 阶段 质量 */
    public void setQuality(double quality) {
        this.quality = quality;
    }

    /**阶段值  第一阶段 ... 值： 1 2 3 4*/
    public Integer getSectionNum() {
        return this.sectionNum;
    }

    /**阶段值  第一阶段 ... 值： 1 2 3 4*/
    public void setSectionNum(Integer sectionNum) {
        this.sectionNum = sectionNum;
    }

    /** 获取 完成比例 */
    public double getFinishRatio() {
        return this.finishRatio;
    }

    /** 设置 完成比例 */
    public void setFinishRatio(double finishRatio) {
        this.finishRatio = finishRatio;
    }

    public double getARatio() {
        return this.ARatio;
    }

    public void setARatio(double ARatio) {
        this.ARatio = ARatio;
    }

    public double getPMRatio() {
        return this.PMRatio;
    }

    public void setPMRatio(double PMRatio) {
        this.PMRatio = PMRatio;
    }

    public List<UserK> getUserKs() {
        return this.userKs;
    }

    public void setUserKs(List<UserK> userKs) {
        this.userKs = userKs;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
