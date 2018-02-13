package com.magic.ereal.business.entity;

import java.io.Serializable;

/**
 * 项目类型 / 课题类型 阶段 entity
 * Created by Eric Xie on 2017/4/26 0026.
 */
public class ProjectTypeSection implements Serializable {

    /**主键ID*/
    private Integer id;

    /**项目类型 / 课题类型 ID*/
    private Integer projectTypeId;

    /**阶段名称*/
    private String sectionName;

    /**阶段描述*/
    private String sectionDescribe;

    /**阶段所需要花费的时长 单位：天*/
    private Double sectionDays;

    /**阶段值  第一阶段 ... 值： 1 2 3 4*/
    private Integer sectionNum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectTypeId() {
        return projectTypeId;
    }

    public void setProjectTypeId(Integer projectTypeId) {
        this.projectTypeId = projectTypeId;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getSectionDescribe() {
        return sectionDescribe;
    }

    public void setSectionDescribe(String sectionDescribe) {
        this.sectionDescribe = sectionDescribe;
    }

    public Double getSectionDays() {
        return sectionDays;
    }

    public void setSectionDays(Double sectionDays) {
        this.sectionDays = sectionDays;
    }

    public Integer getSectionNum() {
        return sectionNum;
    }

    public void setSectionNum(Integer sectionNum) {
        this.sectionNum = sectionNum;
    }
}
