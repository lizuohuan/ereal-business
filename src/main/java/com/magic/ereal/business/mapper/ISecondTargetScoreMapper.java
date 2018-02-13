package com.magic.ereal.business.mapper;

import com.magic.ereal.business.entity.SecondTargetScore;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Eric Xie on 2017/7/3 0003.
 */
public interface ISecondTargetScoreMapper {



    Integer addSecondTargetScore(@Param("secondTargetScore") SecondTargetScore secondTargetScore);



    Double querySecondTargetScoreBySecondTargetId(@Param("secondTargetId") Integer secondTargetId);

}
