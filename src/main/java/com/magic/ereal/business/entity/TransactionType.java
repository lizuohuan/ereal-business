package com.magic.ereal.business.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 事务类别 -- 实体
 * @author lzh
 * @create 2017/4/27 10:33
 */
public class TransactionType implements Serializable {


    private Integer id;

    /** 事务类别名 */
    private String transactionName;

    /**业务*/
    /**事务子类*/
    private List<TransactionSub> transactionSubs;

    /***/
    private List<JobTime> jobTimes;


    public List<JobTime> getJobTimes() {
        return jobTimes;
    }

    public void setJobTimes(List<JobTime> jobTimes) {
        this.jobTimes = jobTimes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTransactionName() {
        return transactionName;
    }

    public void setTransactionName(String transactionName) {
        this.transactionName = transactionName;
    }

    public List<TransactionSub> getTransactionSubs() {
        return transactionSubs;
    }

    public void setTransactionSubs(List<TransactionSub> transactionSubs) {
        this.transactionSubs = transactionSubs;
    }
}
