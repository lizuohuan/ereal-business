package com.magic.ereal.business.service;

import com.alibaba.fastjson.JSONArray;
import com.magic.ereal.business.entity.UsersStatisticsThree;
import com.magic.ereal.business.exception.InterfaceCommonException;
import com.magic.ereal.business.mapper.IUsersStatisticsThreeMapper;
import com.magic.ereal.business.util.StatusConstant;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 发布后第三维数据 -- 业务
 * @author lzh
 * @create 2017/6/2 9:39
 */
@Service
public class UsersStatisticsThreeService {


    @Resource
    private IUsersStatisticsThreeMapper usersStatisticsThreeMapper;

    /**
     * 单条添加
     * @param usersStatisticsThree
     * @throws Exception
     */
    @Transactional
    public void save(UsersStatisticsThree usersStatisticsThree) throws Exception {
        usersStatisticsThreeMapper.save(usersStatisticsThree);
    }

    /**
     * 批量添加
     * @param usersStatisticsThree
     * @throws Exception
     */
    @Transactional
    public void saveList(String usersStatisticsThree) throws Exception {
        List<UsersStatisticsThree> usersStatisticsThrees = JSONArray.parseArray(usersStatisticsThree,UsersStatisticsThree.class);
        if (null == usersStatisticsThree || usersStatisticsThrees.size() < 1) {
            throw new InterfaceCommonException(StatusConstant.FIELD_NOT_NULL,"字段不能为空");
        } else {
            if (null == usersStatisticsThrees.get(0).getUserStatisticsId()) {
                throw new InterfaceCommonException(StatusConstant.FIELD_NOT_NULL,"字段不能为空");
            }
            //1.先进行删除
            usersStatisticsThreeMapper.deleteByUserStatisticsId(usersStatisticsThrees.get(0).getUserStatisticsId());
        }
        //2.批量添加
        usersStatisticsThreeMapper.saveList(usersStatisticsThrees);
    }

    /**
     * 列表
     * @param userStatisticsId
     * @return
     */
    public List<UsersStatisticsThree> list(Integer userStatisticsId){
        return usersStatisticsThreeMapper.list(userStatisticsId);
    }


}
