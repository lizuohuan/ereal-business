package com.magic.ereal.business.mapper;

import com.magic.ereal.business.entity.SecondTargetScoreDepartment;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by Eric Xie on 2017/7/6 0006.
 */
public interface ISecondTargetScoreDepartmentMapper {


    Integer addSecondTargetScoreDepartment(@Param("scoreDepartment") SecondTargetScoreDepartment scoreDepartment);



    Integer updateSecondTargetScoreDepartment(@Param("scoreDepartment") SecondTargetScoreDepartment scoreDepartment);


    SecondTargetScoreDepartment queryScoreDepartment(@Param("departmentId") Integer departmentId, @Param("startTime") Date startTime,
                                 @Param("endTime") Date endTime);


    List<SecondTargetScoreDepartment> queryScoreDepartmentByItems(@Param("departmentId") Integer departmentId, @Param("startTime") Date startTime,
                                                                  @Param("endTime") Date endTime,@Param("limit") Integer limit,
                                                                  @Param("limitSize") Integer limitSize);




    Integer countScoreDepartmentByItems(@Param("departmentId") Integer departmentId, @Param("startTime") Date startTime,
                                                                  @Param("endTime") Date endTime);






}
