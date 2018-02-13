package com.magic.ereal.business.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 导出Excel 工作时间
 * Created by Eric Xie on 2017/7/10 0010.
 */
public class ExcelOfWorkDiarySub implements Serializable {


    /** 学习总时间 */
    private Double studyTotalTime;

    /** 工作总时间 */
    private Double workTotalTime;

    /** 运动总时间 */
    private Double sportTotalTime;

    /** 年月日 日期 */
    private Date date;

    /** 学习时间 */
    private double studyTime;

    /** 工作时间 */
    private double workTime;

    /** 运动时间 */
    private double sportTime;


    public Double getStudyTotalTime() {
        return studyTotalTime;
    }

    public void setStudyTotalTime(Double studyTotalTime) {
        this.studyTotalTime = studyTotalTime;
    }

    public Double getWorkTotalTime() {
        return workTotalTime;
    }

    public void setWorkTotalTime(Double workTotalTime) {
        this.workTotalTime = workTotalTime;
    }

    public Double getSportTotalTime() {
        return sportTotalTime;
    }

    public void setSportTotalTime(Double sportTotalTime) {
        this.sportTotalTime = sportTotalTime;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getStudyTime() {
        return studyTime;
    }

    public void setStudyTime(double studyTime) {
        this.studyTime = studyTime;
    }

    public double getWorkTime() {
        return workTime;
    }

    public void setWorkTime(double workTime) {
        this.workTime = workTime;
    }

    public double getSportTime() {
        return sportTime;
    }

    public void setSportTime(double sportTime) {
        this.sportTime = sportTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExcelOfWorkDiarySub that = (ExcelOfWorkDiarySub) o;

        return date.equals(that.date);

    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }
}
