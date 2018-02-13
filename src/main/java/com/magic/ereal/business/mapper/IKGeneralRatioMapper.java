package com.magic.ereal.business.mapper;

import com.magic.ereal.business.entity.KGeneralRatio;
import com.magic.ereal.business.entity.KGeneralUser;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by Eric Xie on 2017/6/30 0030.
 */
public interface IKGeneralRatioMapper {


    /**
     *  获取 该时间段内，填写过常规事务 的员工
     * @param departmentId
     * @param startTime
     * @param endTime
     * @param jobTypeId
     * @return
     */
    List<KGeneralUser> queryKGeneralUserByDepartment(@Param("departmentId") Integer departmentId,@Param("startTime") Date startTime,
                                                     @Param("endTime") Date endTime, @Param("jobTypeId") Integer jobTypeId);


    Integer addKGeneralRatio(@Param("kGeneralRatio") KGeneralRatio kGeneralRatio);


    Integer updateKGeneralRatio(@Param("kGeneralRatio") KGeneralRatio kGeneralRatio);


    Integer delKGeneralRatio(@Param("id") Integer id);


    KGeneralRatio queryKGeneralRatio(@Param("startTime") Date startTime, @Param("endTime") Date endTime,
                                     @Param("userId") Integer userId, @Param("jobTypeId") Integer jobTypeId);




    List<KGeneralRatio> queryKGeneralRatioByItems(@Param("startTime") Date startTime, @Param("endTime") Date endTime,
                                                  @Param("userId") Integer userId, @Param("jobTypeId") Integer jobTypeId,
                                                  @Param("departmentId") Integer departmentId,
                                                  @Param("limit") Integer limit, @Param("limitSize") Integer limitSize);


    Integer countKGeneralRatioByItems(@Param("startTime") Date startTime,@Param("endTime") Date endTime,
                                      @Param("departmentId") Integer departmentId,
                                                  @Param("userId") Integer userId,@Param("jobTypeId") Integer jobTypeId);





}
