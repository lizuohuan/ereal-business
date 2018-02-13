package com.magic.ereal.business.entity;

import java.io.Serializable;

/**
 * 时间类型 entity
 * Created by Eric Xie on 2017/4/20 0020.
 */
public class TimeType implements Serializable {

    /**主键*/
    private Integer id;

    /**时间类型名称*/
    private String timeName;

    /**是否有效 0:无效 1:有效  缺省值 1*/
    private Integer isValid;

    /**工作时长 单位分*/
    private Integer jobTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTimeName() {
        return timeName;
    }

    public void setTimeName(String timeName) {
        this.timeName = timeName;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public Integer getJobTime() {
        return jobTime;
    }

    public void setJobTime(Integer jobTime) {
        this.jobTime = jobTime;
    }
}
