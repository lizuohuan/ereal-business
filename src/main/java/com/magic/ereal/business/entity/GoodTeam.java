package com.magic.ereal.business.entity;

import java.io.Serializable;
import java.util.Date;

/**
 *  月度 优秀团队 奖
 * Created by Eric Xie on 2017/6/6 0006.
 */
public class GoodTeam implements Serializable {


    private Integer departmentId;

    private String departmentName;

    private Double score;

    private Date month;


    private Integer ranking;

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
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

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Date getMonth() {
        return month;
    }

    public void setMonth(Date month) {
        this.month = month;
    }
}
