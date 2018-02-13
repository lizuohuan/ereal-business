package com.magic.ereal.business.mapper;

import com.magic.ereal.business.entity.CustomAwards;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Eric Xie on 2017/8/21 0021.
 */
public interface ICustomAwardsMapper {


    Integer addCustomAwards(CustomAwards customAwards);


    Integer updateCustomAwards(@Param("customAwards") CustomAwards customAwards);


    List<CustomAwards> queryCustomAwardsList(Map<String,Object> map);


    Integer countCustomAwardsList(Map<String,Object> map);


    /**
     * 通过类型查询
     * @param type
     * @return
     */
    List<CustomAwards> queryCustomAwardsByType(@Param("type") Integer type);

}
