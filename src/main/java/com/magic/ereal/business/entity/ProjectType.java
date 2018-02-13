package com.magic.ereal.business.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 项目类型 / 课题类型  entity
 * Created by Eric Xie on 2017/4/26 0026.
 */
public class ProjectType implements Serializable {

    /**主键ID*/
    private Integer id;

    /**项目类型名称*/
    private String projectTypeName;

    /**是否显示  0:否  1:是  缺省值 1*/
    private Integer isShow;

    /**此项目类型的四个阶段*/
    private List<ProjectTypeSection> sections;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectTypeName() {
        return projectTypeName;
    }

    public void setProjectTypeName(String projectTypeName) {
        this.projectTypeName = projectTypeName;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public List<ProjectTypeSection> getSections() {
        return sections;
    }

    public void setSections(List<ProjectTypeSection> sections) {
        this.sections = sections;
    }
}
