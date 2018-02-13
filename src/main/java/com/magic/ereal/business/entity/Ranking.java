package com.magic.ereal.business.entity;

import java.io.Serializable;

/**
 * 团队/用户排名 及分数 --- 实体
 * @author lzh
 * @create 2017/6/2 15:45
 */
public class Ranking implements Serializable {

    /** id */
    private Integer id;

    /** 部门名 */
    private String name;

    /** 分数 */
    private Double score;

    /** 头像 */
    private String avatar;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
