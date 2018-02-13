package com.magic.ereal.business.mapper;

import com.magic.ereal.business.entity.Suggest;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by Eric Xie on 2017/5/10 0010.
 */
public interface ISuggestMapper {


    Integer addSuggest(@Param("suggest") Suggest suggest);


    List<Suggest> querySuggest(@Param("startTime") Date startTime,@Param("endTime") Date endTime,
                               @Param("limit") Integer limit,@Param("limitSize") Integer limitSize);


    Integer countSuggest(@Param("startTime") Date startTime,@Param("endTime") Date endTime);

}
