package com.magic.ereal.business.service;

import com.magic.ereal.business.entity.OfferAward;
import com.magic.ereal.business.entity.CustomAwards;
import com.magic.ereal.business.entity.PageArgs;
import com.magic.ereal.business.entity.PageList;
import com.magic.ereal.business.mapper.IOfferAwardMapper;
import com.magic.ereal.business.mapper.ICustomAwardsMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 自定义奖项 业务
 * Created by Eric Xie on 2017/8/21 0021.
 */
@Service
public class CustomAwardsService {

    @Resource
    private ICustomAwardsMapper customAwardsMapper;
    @Resource
    private IOfferAwardMapper offerAwardMapper;


    /**
     * 删除颁发的奖项
     * @param id
     */
    public void delOfferAward(Integer id){
        offerAwardMapper.delOfferAward(id);
    }

    /**
     * 动态获取 颁发的奖励
     * @param pageArgs
     * @param baseParams
     * @return
     */
    public PageList<OfferAward> queryOfferAwardByItems(PageArgs pageArgs,Map<String,Object> baseParams){
        List<OfferAward> dataList = new ArrayList<>();
        Integer count = offerAwardMapper.countOfferAwardByItems(baseParams);
        if(count > 0){
            baseParams.put("limit",pageArgs.getPage());
            baseParams.put("limitSize",pageArgs.getPageSize());
            dataList = offerAwardMapper.queryOfferAwardByItems(baseParams);
        }
        return new PageList<>(dataList,count);
    }


    /**
     * 颁发奖励
     * @param offerAward
     */
    public void addAdwards(OfferAward offerAward){
        offerAwardMapper.addOfferAward(offerAward);
    }


    /**
     * 更新 自定义奖项
     * @param customAwards
     */
    public void updateCustomAwards(CustomAwards customAwards){
        customAwardsMapper.updateCustomAwards(customAwards);
    }

    /**
     * 新增自定义奖项
     * @param customAwards
     */
    public void addCustomAwards(CustomAwards customAwards){
        customAwardsMapper.addCustomAwards(customAwards);
    }

    /**
     * 分页查询自定义奖项
     * @param pageArgs
     * @param baseParams
     * @return
     */
    public PageList<CustomAwards> queryCustomAwardsByItems(PageArgs pageArgs, Map<String,Object> baseParams){
        List<CustomAwards> dataList = new ArrayList<>();
        Integer count = customAwardsMapper.countCustomAwardsList(baseParams);
        if(count > 0){
            baseParams.put("limit",pageArgs.getPage());
            baseParams.put("limitSize",pageArgs.getPageSize());
            dataList = customAwardsMapper.queryCustomAwardsList(baseParams);
        }
        return new PageList<>(dataList,count);
    }

    public List<CustomAwards> queryCustomAwardsByType(Integer type){
        return customAwardsMapper.queryCustomAwardsByType(type);
    }



}
