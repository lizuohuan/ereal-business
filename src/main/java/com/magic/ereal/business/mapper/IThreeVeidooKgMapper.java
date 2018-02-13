package com.magic.ereal.business.mapper;

import com.magic.ereal.business.entity.ThreeVeidoo;
import com.magic.ereal.business.entity.ThreeVeidooKg;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 第三维  职能部门个人KG打分记录 / 个人K团队得分记录 Mapper
 * Created by Eric Xie on 2017/6/2 0002.
 */
public interface IThreeVeidooKgMapper {


    void addThreeVeidooKg (@Param("threeVeidooKg") ThreeVeidooKg threeVeidooKg);


    void batchAddThreeVeidooKg(@Param("threeVeidooKgs") List<ThreeVeidooKg> threeVeidooKgs);


    void updateThreeVeidooKg(@Param("threeVeidooKg") ThreeVeidooKg threeVeidooKg);

    /**
     * 主要用于验证 每月职能添加一次
     * @param userId
     * @param startTime
     * @param endTime
     * @return
     */
    ThreeVeidooKg queryByItems(@Param("userId") Integer userId,@Param("startTime") Date startTime,
                               @Param("endTime") Date endTime,@Param("type") Integer type);

    /**
     * 主要用于验证 每月职能添加一次
     * @return
     */
    Integer batchQueryByItems(@Param("threeVeidooKgs") List<ThreeVeidooKg> threeVeidooKgs);


    List<ThreeVeidooKg> queryThreeVeidooKgByItem(@Param("departmentId") Integer departmentId, @Param("limit") Integer limit,
                                                 @Param("limitSize") Integer limitSize, @Param("type") Integer type,
                                                 @Param("startTime") Date startTime,@Param("endTime") Date endTime,
                                                 @Param("companyId") Integer companyId);


    Integer countThreeVeidooKgByItem(@Param("departmentId") Integer departmentId,@Param("type") Integer type,
                                     @Param("startTime") Date startTime,@Param("endTime") Date endTime,
                                     @Param("companyId") Integer companyId);


}
