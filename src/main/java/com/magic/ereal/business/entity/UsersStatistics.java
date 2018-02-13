package com.magic.ereal.business.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 用户的统计数据 -- 实体
 * @author lzh
 * @create 2017/5/26 16:40
 */
public class UsersStatistics implements Serializable {


    private Integer id;

    /** 员工id */
    private Integer userId;

    /** 当前阶段总工时 */
    private Double totalHour;

    /** 当前阶段k可比 */
    private Double kkb;

    /** 当前阶段个人换算系数 */
    private Double hsxs;

    /** 当前阶段k临时 */
    private Double kl;

    /** 当前阶段k常规 */
    private Double kc;

    /** 当前阶段k目标 */
    private Double kmb;

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

    /** 项目完成率 */
    private Double kProjectSchedule;

    /** 当前阶段k值完成率（一维） */
    private Double kwcl;

    /** 当前阶段任务完成率（二维） */
    private Double rwcl;

    /** 当前阶段文化工程分（三维） */
    private Double wwcl;

    /** 当前阶段三维绩效总得分 */
    private Double jxTotalNum;

    /** 当前阶段开始时间 */
    private Date startTime;

    /** 当前阶段结束时间 */
    private Date endTime;

    /** 用户名 */
    private String userName;

    /** 部门id */
    private Integer departmentId;

    /** 部门名 */
    private String departmentName;

    /**公司名字*/
    private String companyName;

    /** 1:员工  2:部门 */
    private Integer type;
    /** 三维的文化（各个配置）分集合 */
    private List<UsersStatisticsThree> list;

    /**团队三维绩效得分*/
    private Double departmentScore;

    /** k内 */
    private Double kn;

    /** k外 */
    private Double kw;


    /**个人K文化*/
    private Double personKCulture;

    /**团队K文化*/
    private Double teamKCulture;

    /**团队K文化完成率*/
    private Double teamKCultureFinishRate;

    /**一维审核 关联的Banner*/
    private Integer oneBannerId;

    /**二维审核 关联的Banner*/
    private Integer twoBannerId;

    /**三维审核 关联的Banner*/
    private Integer threeBannerId;

    /** 头像 */
    private String avatar;

    /** 1维数据状态 0：未确认  1：已确认  2：已发布 */
    private Integer oneStatus;

    /** 2维数据状态 0：未确认  1：已确认  2：已发布 */
    private Integer twoStatus;


    /** 目标结项 */
    private Double targetNum;

    /** 实际结项 */
    private Double actualNum;

    public Double getTargetNum() {
        return targetNum;
    }

    public UsersStatistics setTargetNum(Double targetNum) {
        this.targetNum = targetNum;
        return this;
    }

    public Double getActualNum() {
        return actualNum;
    }

    public UsersStatistics setActualNum(Double actualNum) {
        this.actualNum = actualNum;
        return this;
    }

    public Integer getOneStatus() {
        return oneStatus;
    }

    public void setOneStatus(Integer oneStatus) {
        this.oneStatus = oneStatus;
    }

    public Integer getTwoStatus() {
        return twoStatus;
    }

    public void setTwoStatus(Integer twoStatus) {
        this.twoStatus = twoStatus;
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getOneBannerId() {
        return oneBannerId;
    }

    public void setOneBannerId(Integer oneBannerId) {
        this.oneBannerId = oneBannerId;
    }

    public Integer getTwoBannerId() {
        return twoBannerId;
    }

    public void setTwoBannerId(Integer twoBannerId) {
        this.twoBannerId = twoBannerId;
    }

    public Integer getThreeBannerId() {
        return threeBannerId;
    }

    public void setThreeBannerId(Integer threeBannerId) {
        this.threeBannerId = threeBannerId;
    }

    public Double getDepartmentScore() {
        return departmentScore;
    }

    public void setDepartmentScore(Double departmentScore) {
        this.departmentScore = departmentScore;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Double getTotalHour() {
        return totalHour;
    }

    public void setTotalHour(Double totalHour) {
        this.totalHour = totalHour;
    }

    public Double getKkb() {
        return kkb;
    }

    public void setKkb(Double kkb) {
        this.kkb = kkb;
    }

    public Double getHsxs() {
        return hsxs;
    }

    public void setHsxs(Double hsxs) {
        this.hsxs = hsxs;
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

    public Double getKmb() {
        return kmb;
    }

    public void setKmb(Double kmb) {
        this.kmb = kmb;
    }

    public Double getKwcl() {
        return kwcl;
    }

    public void setKwcl(Double kwcl) {
        this.kwcl = kwcl;
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

    public Double getJxTotalNum() {
        return jxTotalNum;
    }

    public void setJxTotalNum(Double jxTotalNum) {
        this.jxTotalNum = jxTotalNum;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public List<UsersStatisticsThree> getList() {
        return list;
    }

    public void setList(List<UsersStatisticsThree> list) {
        this.list = list;
    }

    public Double getkProjectSchedule() {
        return kProjectSchedule;
    }

    public void setkProjectSchedule(Double kProjectSchedule) {
        this.kProjectSchedule = kProjectSchedule;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Double getPersonKCulture() {
        return personKCulture;
    }

    public void setPersonKCulture(Double personKCulture) {
        this.personKCulture = personKCulture;
    }

    public Double getTeamKCulture() {
        return teamKCulture;
    }

    public void setTeamKCulture(Double teamKCulture) {
        this.teamKCulture = teamKCulture;
    }

    public Double getTeamKCultureFinishRate() {
        return teamKCultureFinishRate;
    }

    public void setTeamKCultureFinishRate(Double teamKCultureFinishRate) {
        this.teamKCultureFinishRate = teamKCultureFinishRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersStatistics that = (UsersStatistics) o;

        if (!userId.equals(that.userId)) return false;
        if (!startTime.equals(that.startTime)) return false;
        if (!endTime.equals(that.endTime)) return false;
//        if (!departmentId.equals(that.departmentId)) return false;
        return type.equals(that.type);

    }

    @Override
    public int hashCode() {
        int result = userId.hashCode();
        result = 31 * result + startTime.hashCode();
        result = 31 * result + endTime.hashCode();
//        result = 31 * result + departmentId.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }
}
