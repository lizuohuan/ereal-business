package com.magic.ereal.business.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 事务子类 -- 实体
 * @author lzh
 * @create 2017/4/27 10:29
 */
public class TransactionSub implements Serializable {

    private Integer id;

    /** 事务子类名 */
    private String transactionSubName;
    /** 事务类别id */
    private Integer transactionTypeId;

    /** 是否显示 逻辑删除  0 不显示   1：显示  缺省值 1 */
    private Integer isShow;

    /** 是否允许修改 0 ： 不允许 1 ： 允许 */
    private Integer isUpdate;


    /** 业务 */
    /** 事务类别名 */
    private String transactionTypeName;

    /** 工作类型集合 */
    private List<JobType> jobTypes;


    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTransactionSubName() {
        return transactionSubName;
    }

    public void setTransactionSubName(String transactionSubName) {
        this.transactionSubName = transactionSubName;
    }

    public Integer getTransactionTypeId() {
        return transactionTypeId;
    }

    public void setTransactionTypeId(Integer transactionTypeId) {
        this.transactionTypeId = transactionTypeId;
    }

    public String getTransactionTypeName() {
        return transactionTypeName;
    }

    public void setTransactionTypeName(String transactionTypeName) {
        this.transactionTypeName = transactionTypeName;
    }

    public List<JobType> getJobTypes() {
        return jobTypes;
    }

    public void setJobTypes(List<JobType> jobTypes) {
        this.jobTypes = jobTypes;
    }

    public Integer getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Integer isUpdate) {
        this.isUpdate = isUpdate;
    }
}
