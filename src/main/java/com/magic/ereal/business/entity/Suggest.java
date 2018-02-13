package com.magic.ereal.business.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 意见反馈 entity
 * Created by Eric Xie on 2017/5/10 0010.
 */
public class Suggest implements Serializable {


    private Integer id;

    private String content;

    private Integer userId;

    private Date createTime;

    private String userName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
