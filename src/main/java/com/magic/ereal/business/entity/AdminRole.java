package com.magic.ereal.business.entity;

import java.io.Serializable;

/**
 *  用户 多 角色 entity
 * Created by Eric Xie on 2017/6/12 0012.
 */
public class AdminRole implements Serializable {


    private Integer id;

    /** 用户ID */
    private Integer userId;

    /** 角色ID */
    private Integer roleId;


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

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
