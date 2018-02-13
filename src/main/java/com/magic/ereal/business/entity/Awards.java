package com.magic.ereal.business.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 统计 奖项列表 entity
 * Created by Eric Xie on 2017/6/7 0007.
 */
public class Awards implements Serializable {

    /**奖项名*/
    private String awardsName;

    /**奖项时间*/
    private Date time;

    public String getAwardsName() {
        return awardsName;
    }

    public void setAwardsName(String awardsName) {
        this.awardsName = awardsName;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
