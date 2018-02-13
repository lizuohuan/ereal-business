package com.magic.ereal.business.entity;

/**
 * 破题统计
 * @author lzh
 * @create 2017/5/15 11:20
 */
public class PoStatistics {

    /** 部门id */
    private Integer departmentId;

    /** 部门名 */
    private String departmentName;

    /** 数量 */
    private Integer num;

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PoStatistics that = (PoStatistics) o;

        return departmentId.equals(that.departmentId);

    }

    @Override
    public int hashCode() {
        return departmentId.hashCode();
    }
}
