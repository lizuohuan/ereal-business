package com.magic.ereal.business.entity;

import java.io.Serializable;

/**
 *
 * 业务 entity 查询 外部项目详情时，统计 项目组 每个人在该项目中 获得的所有的K值
 * Created by Eric Xie on 2017/4/28 0028.
 */
public class UserK implements Serializable,Comparable<UserK> {
    public Integer getId() {
        return id;
    }

    public UserK setId(Integer id) {
        this.id = id;
        return this;
    }

    private Integer id;

    /**姓名*/
    private String name;

    /**用户在当前项目中目前获得的总K值*/
    private Double userK;

    private Double userRatio;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getUserK() {
        if (null == userK) {
            return 0.0;
        }
        return userK;
    }

    public void setUserK(Double userK) {
        this.userK = userK;
    }

    @Override
    public int compareTo(UserK o) {
        return o.getUserK().compareTo(getUserK());
    }

    public Double getUserRatio() {
        return this.userRatio;
    }

    public void setUserRatio(Double userRatio) {
        this.userRatio = userRatio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserK userK = (UserK) o;

        return id.equals(userK.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
