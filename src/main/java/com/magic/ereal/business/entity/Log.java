package com.magic.ereal.business.entity;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * 操作日志数据
 *
 * Created by Eric Xie on 2017/7/4 0004.
 */
public class Log implements Serializable {


    private Integer id;

    /** 0:删除公司 1:删除部门 */
    private Integer type;

    private Integer userId;

    private String userName;

    private String ip;

    private Date createTime;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
