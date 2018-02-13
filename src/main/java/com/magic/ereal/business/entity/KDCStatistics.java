package com.magic.ereal.business.entity;

/**
 * K 值统计(部门/公司) -- 实体
 * @author lzh
 * @create 2017/5/18 10:25
 */
public class KDCStatistics {


    /** k外 */
    private Double kw;

    /** k内 */
    private Double kn;


    /***************月***************/

    /** 月度 k 目标 */
    private Double monthKTarget;

    /** 月度完成率 */
    private Double monthSchedule;


    /***************年***************/

    /** 已承接所有外部项目总k */
    private Double yearAllTkw;

    /** 已完成外部项目总k */
    private Double yearCompleteTkw;

    /** k外完成率 */
    private Double yearKwSchedule;


    /** 已承接所有内部项目总k */
    private Double yearAllTkn;

    /** 已完成内部项目总k */
    private Double yearCompleteTkn;

    /** k内完成率 */
    private Double yearKnSchedule;

    /** 员工名 */
    private String userName;

    /** 部门名 */
    private String departmentName;

    /** 公司名 */
    private String companyName;

    /** k项目完成率 */
    private Double kProjectSchedule;
    public Double getKw() {
        if (null == kw) {
            return 0.0;
        }
        return kw;
    }

    public void setKw(Double kw) {
        this.kw = kw;
    }

    public Double getKn() {
        if (null == kn) {
            return 0.0;
        }
        return kn;
    }

    public void setKn(Double kn) {
        this.kn = kn;
    }

    public Double getMonthKTarget() {
        if (null == monthKTarget) {
            return 0.0;
        }
        return monthKTarget;
    }

    public void setMonthKTarget(Double monthKTarget) {
        this.monthKTarget = monthKTarget;
    }

    public Double getMonthSchedule() {
        if (null == monthSchedule) {
            return 0.0;
        }
        return monthSchedule;
    }

    public void setMonthSchedule(Double monthSchedule) {
        this.monthSchedule = monthSchedule;
    }

    public Double getYearAllTkw() {
        if (null == yearAllTkw) {
            return 0.0;
        }
        return yearAllTkw;
    }

    public void setYearAllTkw(Double yearAllTkw) {
        this.yearAllTkw = yearAllTkw;
    }

    public Double getYearCompleteTkw() {
        if (null == yearCompleteTkw) {
            return 0.0;
        }
        return yearCompleteTkw;
    }

    public void setYearCompleteTkw(Double yearCompleteTkw) {
        this.yearCompleteTkw = yearCompleteTkw;
    }

    public Double getYearKwSchedule() {
        if (null == yearKwSchedule) {
            return 0.0;
        }
        return yearKwSchedule;
    }

    public void setYearKwSchedule(Double yearKwSchedule) {
        this.yearKwSchedule = yearKwSchedule;
    }

    public Double getYearAllTkn() {
        if (null == yearAllTkn) {
            return 0.0;
        }
        return yearAllTkn;
    }

    public void setYearAllTkn(Double yearAllTkn) {
        this.yearAllTkn = yearAllTkn;
    }

    public Double getYearCompleteTkn() {
        if (null == yearCompleteTkn) {
            return 0.0;
        }
        return yearCompleteTkn;
    }

    public void setYearCompleteTkn(Double yearCompleteTkn) {
        this.yearCompleteTkn = yearCompleteTkn;
    }

    public Double getYearKnSchedule() {
        if (null == yearKnSchedule) {
            return 0.0;
        }
        return yearKnSchedule;
    }

    public void setYearKnSchedule(Double yearKnSchedule) {
        this.yearKnSchedule = yearKnSchedule;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }


    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Double getkProjectSchedule() {
        return kProjectSchedule;
    }

    public void setkProjectSchedule(Double kProjectSchedule) {
        this.kProjectSchedule = kProjectSchedule;
    }
}
