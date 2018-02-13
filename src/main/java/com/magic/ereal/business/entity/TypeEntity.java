package com.magic.ereal.business.entity;

import java.io.Serializable;

/**
 * 业务实体
 * Created by Eric Xie on 2017/5/18 0018.
 */
public class TypeEntity implements Serializable {

    private Integer typeId;

    private String typeName;

    private Double time;

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Double getTime() {
        return time;
    }

    public void setTime(Double time) {
        this.time = time;
    }
}
