package com.magic.ereal.business.entity;

import java.io.Serializable;

/**
 * 项目组 和 成员 关系
 * Created by Eric Xie on 2017/5/2 0002.
 */
public class ProjectGroupUser implements Serializable {

    private Integer id;

    private Integer userId;

    private Integer projectGroupId;

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

    public Integer getProjectGroupId() {
        return projectGroupId;
    }

    public void setProjectGroupId(Integer projectGroupId) {
        this.projectGroupId = projectGroupId;
    }
}
