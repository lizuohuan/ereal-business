package com.magic.ereal.business.service;

import com.magic.ereal.business.entity.TransactionType;
import com.magic.ereal.business.mapper.ITransactionTypeMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 事务类别 -- 业务
 * @author lzh
 * @create 2017/4/27 15:19
 */
@Service
public class TransactionTypeService {

    @Resource
    private ITransactionTypeMapper transactionTypeMapper;

    public List<TransactionType> list() throws Exception {
        return transactionTypeMapper.list();
    }

    public void save(TransactionType transactionType) throws Exception {
        transactionTypeMapper.save(transactionType);
    }

    public void update(TransactionType transactionType) throws Exception {
        transactionTypeMapper.update(transactionType);
    }

}
