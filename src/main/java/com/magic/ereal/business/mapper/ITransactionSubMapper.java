package com.magic.ereal.business.mapper;

import com.magic.ereal.business.entity.TransactionSub;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 事务类型 子类 持久层接口
 * Created by Eric Xie on 2017/4/27 0027.
 */
public interface ITransactionSubMapper {

    Integer addTransactionSub(@Param("transactionSub") TransactionSub transactionSub);

    /**
     *  更新 名称 和 是否显示
     * @param transactionSub
     * @return
     */
    Integer updateTransactionSub(@Param("transactionSub") TransactionSub transactionSub);

    /**
     * 查询 事务类型 子类集合 不包含其 工作类型集合
     * @param isShow 0: 不显示的  1:显示的   null : 查询所有
     * @return
     */
    List<TransactionSub> queryTransactionSubByItem(@Param("isShow")Integer isShow);


    /**
     * 查询 事务类型 子类集合 不包含其 工作类型集合
     * @param isShow 0: 不显示的  1:显示的   null : 查询所有
     * @return
     */
    List<TransactionSub> queryAllTransactionSub(@Param("userId") Integer userId);



}
