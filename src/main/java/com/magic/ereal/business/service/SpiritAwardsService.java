package com.magic.ereal.business.service;

import com.magic.ereal.business.entity.Awards;
import com.magic.ereal.business.entity.PageArgs;
import com.magic.ereal.business.entity.PageList;
import com.magic.ereal.business.entity.SpiritAwards;
import com.magic.ereal.business.mapper.IDepartmentAwardsMapper;
import com.magic.ereal.business.mapper.IOfferAwardMapper;
import com.magic.ereal.business.mapper.ISpiritAwardsMapper;
import com.magic.ereal.business.mapper.IUsersStatisticsMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 精神奖 业务层
 * Created by Eric Xie on 2017/6/6 0006.
 */
@Service
public class SpiritAwardsService {


    @Resource
    private ISpiritAwardsMapper spiritAwardsMapper;
    @Resource
    private IUsersStatisticsMapper usersStatisticsMapper;
    @Resource
    private IDepartmentAwardsMapper departmentAwardsMapper;
    @Resource
    private IOfferAwardMapper offerAwardMapper;



    public void addSpiritAwards(SpiritAwards spiritAwards){
        spiritAwardsMapper.addSpiritAwards(spiritAwards);
    }
    public void addSpiritAwards(List<SpiritAwards> spiritAwardss){
        spiritAwardsMapper.batchAddSpiritAwards(spiritAwardss);
    }



    public PageList<SpiritAwards> querySpiritAwardsByItems(Date month, PageArgs pageArgs,Integer type){
        List<SpiritAwards> dataList = spiritAwardsMapper.querySpiritAwardsByItems(month,pageArgs.getPageStart(),pageArgs.getPageSize(),type);
        Integer count = spiritAwardsMapper.countSpiritAwardsByItems(month,type);
        return new PageList<>(dataList,count);
    }


    /**
     * 移动端奖项统计
     * @param userId
     */
    public List<Awards> awards(Integer userId){
        List<Awards> data = new ArrayList<>();
        // 一真精神奖  和  优秀执委奖 统计
        data.addAll(spiritAwardsMapper.querySpiritAwards(userId));
//        // 团队奖项
        data.addAll(departmentAwardsMapper.queryAwardsByUser(userId));
        // 自定义奖项
        data.addAll(offerAwardMapper.queryAwardsByUser(userId));
        return data;
    }



}
