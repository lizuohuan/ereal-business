package com.magic.ereal.business.entity;

import java.io.Serializable;

/**
 * 验收进展状态列表 （内部验收、外部验收、周验收）
 * @author lzh
 * @create 2017/5/10 10:43
 */
public class Acceptance implements Serializable {
    private String acceptanceName;  //名
    private Integer status;         //状态 (type=1 or 2  0：等待验收  1：通过  2：验收失败 3:已分配比例 ) （type = 3周验收：0:提交  1:验收完成 2:已经分配比例）
    private Double score;           //分数
    private Integer type;           //类型 1：内部  2：外部 3：周验收
    private Integer objectId;       //各种验收id 根据类型判断

    private Integer isCanNBJX;      //是否可以内部结项  0：不能  2：可以

    public String getAcceptanceName() {
        return acceptanceName;
    }

    public void setAcceptanceName(String acceptanceName) {
        this.acceptanceName = acceptanceName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getObjectId() {
        return objectId;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    public Integer getIsCanNBJX() {
        return isCanNBJX;
    }

    public void setIsCanNBJX(Integer isCanNBJX) {
        this.isCanNBJX = isCanNBJX;
    }
}
