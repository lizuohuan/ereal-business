package com.magic.ereal.business.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 第三维统计时用
 * Created by Eric Xie on 2017/6/2 0002.
 */
public class ThreeVeidooTemp implements Serializable {


    private Integer userId;

    private String userName;


    private String departmentName;

    private Integer departmentId;



    private Double kg;

    private Double tz;

    private Double yd;

    private Double hy;

    private Double wx;

    private Double zb;

    /**个人K文化*/
    private Double personKCulture;

    /**团队K文化*/
    private Double teamKCulture;

    /**团队K文化完成率*/
    private Double teamKCultureFinishRate;

    /**打分记录集合*/
    private List<ThreeVeidooSta> threeVeidooStas;

    /** 当前时间阶段打分记录数据 */
    private List<UsersStatisticsThree> usersStatisticsThrees;


    /** 上个时间阶段打分记录数据 */
    private List<UsersStatisticsThree> lastUsersStatisticsThrees;


    public List<UsersStatisticsThree> getUsersStatisticsThrees() {
        return usersStatisticsThrees;
    }

    public void setUsersStatisticsThrees(List<UsersStatisticsThree> usersStatisticsThrees) {
        this.usersStatisticsThrees = usersStatisticsThrees;
    }

    public List<UsersStatisticsThree> getLastUsersStatisticsThrees() {
        return lastUsersStatisticsThrees;
    }

    public void setLastUsersStatisticsThrees(List<UsersStatisticsThree> lastUsersStatisticsThrees) {
        this.lastUsersStatisticsThrees = lastUsersStatisticsThrees;
    }

    public List<ThreeVeidooSta> getThreeVeidooStas() {
        return threeVeidooStas;
    }

    public void setThreeVeidooStas(List<ThreeVeidooSta> threeVeidooStas) {
        this.threeVeidooStas = threeVeidooStas;
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

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Double getPersonKCulture() {
        return personKCulture;
    }

    public void setPersonKCulture(Double personKCulture) {
        this.personKCulture = personKCulture;
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

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
}
