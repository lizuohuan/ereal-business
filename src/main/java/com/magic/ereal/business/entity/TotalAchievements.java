package com.magic.ereal.business.entity;

import java.io.Serializable;

/**
 * 三维绩效总汇表 -- 实体
 * @author lzh
 * @create 2017/6/7 10:17
 */
public class TotalAchievements implements Serializable,Comparable<TotalAchievements> {

    /** 部门ID */
    private Integer departmentId;

    /** 部门名 */
    private String departmentName;

    /** 总得分 */
    private Double totalScore;

    /** 排名 */
    private Integer ranking;

    /** 实际k值 （一维） */
    private Double actualK;

    /** 目标k值 （一维） */
    private Double targetK;

    /** k值完成率（一维） */
    private Double kSchedule;

    /** 得分（一维） */
    private Double oneScore;

    /** 实际结项（二维） */
    private Double actualJX;

    /** 目标结项（二维） */
    private Double targetJX;

    /** 结项完成率（二维） */
    private Double kProjectSchedule;

    /** 得分（二维） */
    private Double twoScore;

    /** 团队得分（三维） */
    private Double teamScore;

    /** 平均值（三维） */
    private Double averageScore;

    /** 得分（三维） */
    private Double threeScore;

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Double totalScore) {
        this.totalScore = totalScore;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public Double getActualK() {
        return actualK;
    }

    public void setActualK(Double actualK) {
        this.actualK = actualK;
    }

    public Double getTargetK() {
        return targetK;
    }

    public void setTargetK(Double targetK) {
        this.targetK = targetK;
    }

    public Double getkSchedule() {
        return kSchedule;
    }

    public TotalAchievements setkSchedule(Double kSchedule) {
        this.kSchedule = kSchedule;
        return this;
    }

    public Double getOneScore() {
        return oneScore;
    }

    public void setOneScore(Double oneScore) {
        this.oneScore = oneScore;
    }

    public Double getActualJX() {
        return actualJX;
    }

    public void setActualJX(Double actualJX) {
        this.actualJX = actualJX;
    }

    public Double getTargetJX() {
        return targetJX;
    }

    public void setTargetJX(Double targetJX) {
        this.targetJX = targetJX;
    }

    public Double getkProjectSchedule() {
        return kProjectSchedule;
    }

    public void setkProjectSchedule(Double kProjectSchedule) {
        this.kProjectSchedule = kProjectSchedule;
    }

    public Double getTwoScore() {
        return twoScore;
    }

    public void setTwoScore(Double twoScore) {
        this.twoScore = twoScore;
    }

    public Double getTeamScore() {
        return teamScore;
    }

    public void setTeamScore(Double teamScore) {
        this.teamScore = teamScore;
    }

    public Double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(Double averageScore) {
        this.averageScore = averageScore;
    }

    public Double getThreeScore() {
        return threeScore;
    }

    public void setThreeScore(Double threeScore) {
        this.threeScore = threeScore;
    }

    @Override
    public int compareTo(TotalAchievements o) {
        return o.getTotalScore().compareTo(this.getTotalScore());
    }
}
