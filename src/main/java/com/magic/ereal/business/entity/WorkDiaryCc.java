package com.magic.ereal.business.entity;

import java.io.Serializable;

/**
 * 日志被抄送 -- 实体
 * @author lzh
 * @create 2017/4/25 15:52
 */
public class WorkDiaryCc extends User implements Serializable {

    private Integer workDiaryCcId;

    /** 日志id */
    private Integer workDiaryId;

    /** 被抄送人id */
    private Integer userId;

    public Integer getWorkDiaryCcId() {
        return workDiaryCcId;
    }

    public void setWorkDiaryCcId(Integer workDiaryCcId) {
        this.workDiaryCcId = workDiaryCcId;
    }

    public Integer getWorkDiaryId() {
        return workDiaryId;
    }

    public void setWorkDiaryId(Integer workDiaryId) {
        this.workDiaryId = workDiaryId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
