package com.magic.ereal.business.mapper;

import com.magic.ereal.business.entity.Awards;
import com.magic.ereal.business.entity.SpiritAwards;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by Eric Xie on 2017/6/6 0006.
 */
public interface ISpiritAwardsMapper {



    Integer addSpiritAwards(@Param("spiritAwards") SpiritAwards spiritAwards);



    Integer batchAddSpiritAwards(@Param("spiritAwardss") List<SpiritAwards> spiritAwardss);




    List<SpiritAwards>  querySpiritAwardsByItems(@Param("month") Date month,@Param("limit") Integer limit,
                                                 @Param("limitSize") Integer limitSize,@Param("type") Integer type);




    Integer  countSpiritAwardsByItems(@Param("month") Date month,@Param("type") Integer type);


    /**
     * 统计 个人 一真精神奖 和 优秀 执委奖
     * @param userId
     * @return
     */
    List<Awards> querySpiritAwards(@Param("userId") Integer userId);


    List<Awards> querySpiritAwardsByUser(@Param("userId") Integer userId,@Param("month") Date month,
                                         @Param("type") Integer type);




}
