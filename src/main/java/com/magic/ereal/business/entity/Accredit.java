package com.magic.ereal.business.entity;

import java.io.Serializable;

/**
 * 授权用户
 * Created by Eric Xie on 2017/5/23 0023.
 */
public class Accredit implements Serializable {

    /**主键ID*/
    private Integer id;

    /**被指向的用户ID  被授权者*/
    private Integer toUserId;

    /**授权者*/
    private Integer fromUserId;

    /**授权类型 1：项目授权  2：日志授权*/
    private Integer type;


    private String userName;
    private String avatar;
    private String departmentName;
    private String roleName;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getToUserId() {
        return toUserId;
    }

    public void setToUserId(Integer toUserId) {
        this.toUserId = toUserId;
    }

    public Integer getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(Integer fromUserId) {
        this.fromUserId = fromUserId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
