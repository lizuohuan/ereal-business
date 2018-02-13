package com.magic.ereal.business.mapper;

import com.magic.ereal.business.entity.Log;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by Eric Xie on 2017/7/5 0005.
 */
public interface ILogMapper {



    Integer addLog(@Param("log") Log log);


    List<Log> queryLog(@Param("startTime") Date startTime,@Param("endTime") Date endTime,
                       @Param("limit") Integer limit, @Param("limitSize") Integer limitSize);


    Integer countLog(@Param("startTime") Date startTime,@Param("endTime") Date endTime);

}
