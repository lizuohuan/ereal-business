package com.magic.ereal.business.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统消息 entity
 * Created by Eric Xie on 2017/5/10 0010.
 */
public class SystemInfo implements Serializable {

    /**主键ID*/
    private Integer id;

    /**消息类型 0:外部项目通知消息 1:日志通知消息 2:统计消息 3:其他消息 4:内部项目消息*/
    private Integer type;

    /**消息title*/
    private String title;

    /**内容*/
    private String content;

    /**用户ID*/
    private Integer userId;

    /**创建时间*/
    private Date createTime;




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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
}
