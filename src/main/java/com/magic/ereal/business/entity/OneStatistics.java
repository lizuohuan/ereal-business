package com.magic.ereal.business.entity;

/**
 * 一维统计 -- 实体
 * @author lzh
 * @create 2017/5/24 17:34
 */
public class OneStatistics {

    /** k外 */
    private Double kw;

    /** k内 */
    private Double kn;

    /** k临时 */
    private Double kl;

    /** k常规 */
    private Double kc;

    /** k目标 */
    private Double km;

    /** k可比 */
    private Double kk;

    /** k值完成率 */
    private Double kSchedule;

    /** 项目完成率 */
    private Double kProjectSchedule;

    /** 用户 */
    private String userName;

    /** 公司 */
    private String companyName;

    /** 公司 */
    private Integer companyId;

    /** 部门 */
    private String departmentName;

    /** 部门id */
    private Integer departmentId;

    /** 用户id */
    private Integer userId;

    /** 个人转换系数 */
    private Double zhxh;


    public Double getZhxh() {
        return zhxh;
    }

    public void setZhxh(Double zhxh) {
        this.zhxh = zhxh;
    }

    public Double getKw() {
        return kw;
    }

    public void setKw(Double kw) {
        this.kw = kw;
    }

    public Double getKn() {
        return kn;
    }

    public void setKn(Double kn) {
        this.kn = kn;
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

    public Double getKm() {
        return km;
    }

    public void setKm(Double km) {
        this.km = km;
    }

    public Double getKk() {
        return kk;
    }

    public void setKk(Double kk) {
        this.kk = kk;
    }

    public Double getkSchedule() {
        return kSchedule;
    }

    public void setkSchedule(Double kSchedule) {
        this.kSchedule = kSchedule;
    }

    public Double getkProjectSchedule() {
        return kProjectSchedule;
    }

    public void setkProjectSchedule(Double kProjectSchedule) {
        this.kProjectSchedule = kProjectSchedule;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /** 获取 k值完成率 */
    public Double getKSchedule() {
        return this.kSchedule;
    }

    /** 设置 k值完成率 */
    public void setKSchedule(Double kSchedule) {
        this.kSchedule = kSchedule;
    }

    /** 获取 项目完成率 */
    public Double getKProjectSchedule() {
        return this.kProjectSchedule;
    }

    /** 设置 项目完成率 */
    public void setKProjectSchedule(Double kProjectSchedule) {
        this.kProjectSchedule = kProjectSchedule;
    }

    /** 获取 公司 */
    public Integer getCompanyId() {
        return this.companyId;
    }

    /** 设置 公司 */
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
}
