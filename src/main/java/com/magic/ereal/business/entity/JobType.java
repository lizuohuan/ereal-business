package com.magic.ereal.business.entity;

import java.io.Serializable;

/**
 * 工作类型 entity
 * Created by Eric Xie on 2017/4/21 0021.
 */
public class JobType implements Serializable {

    /**主键*/
    private Integer id;

    /**工作类型名称*/
    private String jobTypeName;

    /**额定工作时间 如：1.2  单位 H*/
    private Double jobTypeTime;

    /**事务类型Id*/
    private Integer transactionSubId;

    /**部门ID 当事务类型为 临时类型时 和 常规事务类型 字段有效*/
    private Integer departmentId;

    /** 是否有效 0 无效 1有效 */
    private Integer isValid;

    //业务
    /** 部门名 */
    private String departmentName;
    /** 事务类型名 */
    private String transactionSubName;
    /** 事务类别名 */
    private String transactionName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJobTypeName() {
        return jobTypeName;
    }

    public void setJobTypeName(String jobTypeName) {
        this.jobTypeName = jobTypeName;
    }

    public Double getJobTypeTime() {
        return jobTypeTime;
    }

    public void setJobTypeTime(Double jobTypeTime) {
        this.jobTypeTime = jobTypeTime;
    }

    public Integer getTransactionSubId() {
        return transactionSubId;
    }

    public void setTransactionSubId(Integer transactionSubId) {
        this.transactionSubId = transactionSubId;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getTransactionSubName() {
        return transactionSubName;
    }

    public void setTransactionSubName(String transactionSubName) {
        this.transactionSubName = transactionSubName;
    }

    public String getTransactionName() {
        return transactionName;
    }

    public void setTransactionName(String transactionName) {
        this.transactionName = transactionName;
    }

    /** 获取 是否有效 0 无效 1有效 */
    public Integer getIsValid() {
        return this.isValid;
    }

    /** 设置 是否有效 0 无效 1有效 */
    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }
}
