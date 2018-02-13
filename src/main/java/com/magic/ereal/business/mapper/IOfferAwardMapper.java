package com.magic.ereal.business.mapper;

import com.magic.ereal.business.entity.Awards;
import com.magic.ereal.business.entity.OfferAward;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Eric Xie on 2017/8/21 0021.
 */
public interface IOfferAwardMapper {


    Integer addOfferAward(OfferAward offerAward);


    Integer delOfferAward(@Param("id") Integer id);


    List<OfferAward> queryOfferAwardByItems(Map<String,Object> map);

    Integer countOfferAwardByItems(Map<String,Object> map);


    List<Awards> queryAwardsByUser(@Param("userId") Integer userId);

}
