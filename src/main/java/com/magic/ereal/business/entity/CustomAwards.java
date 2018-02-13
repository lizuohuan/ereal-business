package com.magic.ereal.business.entity;

import java.io.Serializable;

/**
 * 自定义奖项
 * Created by Eric Xie on 2017/8/21 0021.
 */
public class CustomAwards implements Serializable {


    private Integer id;

    /** 类型：0 个人奖项 1：团队奖项 */
    private Integer type;

    /** 奖项名称 */
    private String awardsName;

    /** 是否有效 0：无效 1：有效 默认为  1 */
    private Integer isValid;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /** 获取 类型：0 个人奖项 1：团队奖项 */
    public Integer getType() {
        return this.type;
    }

    /** 设置 类型：0 个人奖项 1：团队奖项 */
    public void setType(Integer type) {
        this.type = type;
    }

    /** 获取 奖项名称 */
    public String getAwardsName() {
        return this.awardsName;
    }

    /** 设置 奖项名称 */
    public void setAwardsName(String awardsName) {
        this.awardsName = awardsName;
    }

    /** 获取 是否有效 0：无效 1：有效 默认为  1 */
    public Integer getIsValid() {
        return this.isValid;
    }

    /** 设置 是否有效 0：无效 1：有效 默认为  1 */
    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }
}
