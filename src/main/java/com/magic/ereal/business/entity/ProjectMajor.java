package com.magic.ereal.business.entity;

import java.io.Serializable;

/**
 * 内部项目专业 -- 实体
 * @author lzh
 * @create 2017/4/28 10:25
 */
public class ProjectMajor implements Serializable {


    private Integer id;
    /**专业名*/
    private String majorName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }
}
