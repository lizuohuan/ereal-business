package com.magic.ereal.business.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 三维统计 业务 entity
 * Created by Eric Xie on 2017/6/22 0022.
 */
public class VeidooStatistics implements Serializable {

    /** K临时 */
    private Double kl;

    /** K常规 */
    private Double kc;

    /** K内 */
    private Double kn;

    /** K外 */
    private Double kw;

    /** K完成率 */
    private Double kwcl;

    /** K可比 */
    private Double kkb;

    /** K项目完成率 */
    private Double kProjectSchedule;

    /** K目标 */
    private Double kmb;

    /** 任务完成率 */
    private Double rwcl;

    /** 文化工程完成率 */
    private Double wwcl;

    /** 当前部门 三维绩效 排名 */
    private Integer ranking;

    /** 当前部门 三维绩效 总分数 */
    private Double totalScore;

    /** 第一维排名 */
    private Integer oneRanking;

    /** 第二维排名 */
    private Integer twoRanking;

    /** 第三维排名 */
    private Integer threeRanking;

    /** 个人K可比排名 */
    private Integer kkbRanking;

    /** 公司文化考核得分 */
    private Double companyWwcl;

    /** 部门文化考核得分 */
    private Double departmentWwcl;

    /** 部门ID */
    private Integer departmentId;

    /** kg */
    private Double kg;

    /** tz */
    private Double tz;

    /** yd */
    private Double yd;

    /** hy */
    private Double hy;

    /** wx */
    private Double wx;

    /** zb */
    private Double zb;

    /** 查询个人的 第三维评分 集合 */
    private List<UsersStatisticsThree> threeList;

    /** 查询个人上个时间段的 第三维评分 集合 */
    private List<UsersStatisticsThree> lastThreeList;

    /** 团队人数 */
    private Integer persons;

    public Integer getPersons() {
        return persons;
    }

    public void setPersons(Integer persons) {
        this.persons = persons;
    }

    public Double getKg() {
        return kg;
    }

    public void setKg(Double kg) {
        this.kg = kg;
    }

    public Double getTz() {
        return tz;
    }

    public void setTz(Double tz) {
        this.tz = tz;
    }

    public Double getYd() {
        return yd;
    }

    public void setYd(Double yd) {
        this.yd = yd;
    }

    public Double getHy() {
        return hy;
    }

    public void setHy(Double hy) {
        this.hy = hy;
    }

    public Double getWx() {
        return wx;
    }

    public void setWx(Double wx) {
        this.wx = wx;
    }

    public Double getZb() {
        return zb;
    }

    public void setZb(Double zb) {
        this.zb = zb;
    }

    public List<UsersStatisticsThree> getLastThreeList() {
        return lastThreeList;
    }

    public void setLastThreeList(List<UsersStatisticsThree> lastThreeList) {
        this.lastThreeList = lastThreeList;
    }

    public Double getKkb() {
        return kkb;
    }

    public void setKkb(Double kkb) {
        this.kkb = kkb;
    }

    public Double getCompanyWwcl() {
        return companyWwcl;
    }

    public void setCompanyWwcl(Double companyWwcl) {
        this.companyWwcl = companyWwcl;
    }

    public Double getDepartmentWwcl() {
        return departmentWwcl;
    }

    public void setDepartmentWwcl(Double departmentWwcl) {
        this.departmentWwcl = departmentWwcl;
    }

    public Integer getKkbRanking() {
        return kkbRanking;
    }

    public void setKkbRanking(Integer kkbRanking) {
        this.kkbRanking = kkbRanking;
    }

    public List<UsersStatisticsThree> getThreeList() {
        return threeList;
    }

    public void setThreeList(List<UsersStatisticsThree> threeList) {
        this.threeList = threeList;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Double getKl() {
        return kl;
    }

    public void setKl(Double kl) {
        this.kl = kl;
    }

    public Double getKc() {
        return kc;
    }

    public void setKc(Double kc) {
        this.kc = kc;
    }

    public Double getKn() {
        return kn;
    }

    public void setKn(Double kn) {
        this.kn = kn;
    }

    public Double getKw() {
        return kw;
    }

    public void setKw(Double kw) {
        this.kw = kw;
    }

    public Double getKwcl() {
        return kwcl;
    }

    public void setKwcl(Double kwcl) {
        this.kwcl = kwcl;
    }

    public Double getkProjectSchedule() {
        return kProjectSchedule;
    }

    public void setkProjectSchedule(Double kProjectSchedule) {
        this.kProjectSchedule = kProjectSchedule;
    }

    public Double getKmb() {
        return kmb;
    }

    public void setKmb(Double kmb) {
        this.kmb = kmb;
    }

    public Double getRwcl() {
        return rwcl;
    }

    public void setRwcl(Double rwcl) {
        this.rwcl = rwcl;
    }

    public Double getWwcl() {
        return wwcl;
    }

    public void setWwcl(Double wwcl) {
        this.wwcl = wwcl;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public Integer getOneRanking() {
        return oneRanking;
    }

    public void setOneRanking(Integer oneRanking) {
        this.oneRanking = oneRanking;
    }

    public Integer getTwoRanking() {
        return twoRanking;
    }

    public void setTwoRanking(Integer twoRanking) {
        this.twoRanking = twoRanking;
    }

    public Integer getThreeRanking() {
        return threeRanking;
    }

    public void setThreeRanking(Integer threeRanking) {
        this.threeRanking = threeRanking;
    }

    public Double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Double totalScore) {
        this.totalScore = totalScore;
    }
}
