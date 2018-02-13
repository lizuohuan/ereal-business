package com.magic.ereal.business.mapper;

import com.magic.ereal.business.entity.Awards;
import com.magic.ereal.business.entity.DepartmentAwards;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by Eric Xie on 2017/7/3 0003.
 */
public interface IDepartmentAwardsMapper {


    Integer addDepartmentAwards(@Param("departmentAwards") DepartmentAwards departmentAwards);


    DepartmentAwards queryDepartmentAwards(@Param("departmentId") Integer departmentId,@Param("month") Date month,
                                           @Param("type") Integer type);


    List<DepartmentAwards> queryDepartmentAwardsByItems(@Param("type") Integer type,@Param("companyId") Integer companyId, @Param("month") Date month,
                                                        @Param("limit") Integer limit,@Param("limitSize") Integer limitSize);


    Integer  countDepartmentAwardsByItems(@Param("type") Integer type,@Param("companyId") Integer companyId, @Param("month") Date month);


    List<Awards> queryAwardsByUser(@Param("userId") Integer userId);

}
