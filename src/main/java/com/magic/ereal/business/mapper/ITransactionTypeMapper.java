package com.magic.ereal.business.mapper;

import com.magic.ereal.business.entity.TransactionType;

import java.util.List;

/**
 * 事务类别 -- 接口
 * @author lzh
 * @create 2017/4/27 15:04
 */
public interface ITransactionTypeMapper {

    /**
     * 事务类别列表
     * */
    List<TransactionType> list();

    /**
     * 添加
     * @param transactionType
     */
    void save(TransactionType transactionType);

    /**
     * 更新
     * @param transactionType
     */
    void update(TransactionType transactionType);
}
