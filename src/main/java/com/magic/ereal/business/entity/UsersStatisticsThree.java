package com.magic.ereal.business.entity;

import java.io.Serializable;

/**
 * 数据统计记录 记录三维各个配置得分 -- 实体
 * @author lzh
 * @create 2017/6/1 15:56
 */
public class UsersStatisticsThree implements Serializable {


    private Integer id;

    /** 用户id */
    private Integer userId;

    /** 记录统计数据的id */
    private Integer userStatisticsId;

    /** 三维 配置的id */
    private Integer threeVeidooId;

    /** 分数 */
    private Double threeVeidooScore;

    /** 扣分情况 */
    private String msg;

    /** 三维 配置的名字 */
    private String threeVeidooName;


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

    public Integer getUserStatisticsId() {
        return userStatisticsId;
    }

    public void setUserStatisticsId(Integer userStatisticsId) {
        this.userStatisticsId = userStatisticsId;
    }

    public Integer getThreeVeidooId() {
        return threeVeidooId;
    }

    public void setThreeVeidooId(Integer threeVeidooId) {
        this.threeVeidooId = threeVeidooId;
    }

    public Double getThreeVeidooScore() {
        return threeVeidooScore;
    }

    public void setThreeVeidooScore(Double threeVeidooScore) {
        this.threeVeidooScore = threeVeidooScore;
    }

    public String getThreeVeidooName() {
        return threeVeidooName;
    }

    public void setThreeVeidooName(String threeVeidooName) {
        this.threeVeidooName = threeVeidooName;
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


}
