package com.magic.ereal.business.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 工作日志 导出 entity
 * Created by Eric Xie on 2017/7/10 0010.
 */
public class UserExcelOfWorkDiary implements Serializable,Comparable<UserExcelOfWorkDiary> {

    /** 用户ID */
    private Integer userId;

    /** 用户名 */
    private String userName;

    /** 总排名 */
    private Integer ranking;

    /** 日均时间 */
    private double averageTime;

    /** 折算天数 */
    private double convertDays;

    /** 出勤天数 */
    private double attendanceDays;

    /** 总时间 */
    private Double totalTime;


    /** 学习总时间 */
    private double studyTotalTime;

    /** 工作总时间 */
    private double workTotalTime;

    /** 运动总时间 */
    private double sportTotalTime;

    /** 日志详细记录集合 */
    private List<ExcelOfWorkDiarySub> subs;


    public double getStudyTotalTime() {
        return studyTotalTime;
    }

    public void setStudyTotalTime(double studyTotalTime) {
        this.studyTotalTime = studyTotalTime;
    }

    public double getWorkTotalTime() {
        return workTotalTime;
    }

    public void setWorkTotalTime(double workTotalTime) {
        this.workTotalTime = workTotalTime;
    }

    public double getSportTotalTime() {
        return sportTotalTime;
    }

    public void setSportTotalTime(double sportTotalTime) {
        this.sportTotalTime = sportTotalTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public double getAverageTime() {
        return averageTime;
    }

    public void setAverageTime(double averageTime) {
        this.averageTime = averageTime;
    }

    public double getConvertDays() {
        return convertDays;
    }

    public void setConvertDays(double convertDays) {
        this.convertDays = convertDays;
    }

    public double getAttendanceDays() {
        return attendanceDays;
    }

    public void setAttendanceDays(double attendanceDays) {
        this.attendanceDays = attendanceDays;
    }

    public Double getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Double totalTime) {
        this.totalTime = totalTime;
    }

    public List<ExcelOfWorkDiarySub> getSubs() {
        return subs;
    }

    public void setSubs(List<ExcelOfWorkDiarySub> subs) {
        this.subs = subs;
    }

    @Override
    public int compareTo(UserExcelOfWorkDiary o) {
        return o.getTotalTime().compareTo(totalTime);
    }
}
