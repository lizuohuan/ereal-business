package com.magic.ereal.business.entity;

import java.io.Serializable;

/**
 *  内部项目周验收比例分配时，成员当期工时
 * Created by Eric Xie on 2017/5/4 0004.
 */
public class UserH implements Serializable {


    /**用户ID*/
    private Integer userId;

    /**当期工时*/
    private Double time;

    /**总工时*/
    private Double totalTime;

    /**用户名字*/
    private String userName;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Double getTime() {
        return time;
    }

    public void setTime(Double time) {
        this.time = time;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Double getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Double totalTime) {
        this.totalTime = totalTime;
    }
}
