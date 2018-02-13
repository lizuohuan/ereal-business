package com.magic.ereal.business.mapper;

import com.magic.ereal.business.entity.PageArgs;
import com.magic.ereal.business.entity.SecondTarget;
import com.magic.ereal.business.entity.TwoStatistics;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by Eric Xie on 2017/5/22 0022.
 */
public interface ISecondTargetMapper {


    Integer addSecondTarget(@Param("secondTarget") SecondTarget secondTarget);


    Integer updateSecondTarget(@Param("secondTarget") SecondTarget secondTarget);


    Integer delSecondTarget(@Param("id") Integer id);


    List<SecondTarget> querySecondTarget(@Param("departmentId") Integer departmentId, @Param("startTime") Date startTime,
                                            @Param("endTime") Date endTime,
                                         @Param("limit") Integer limit,@Param("limitSize") Integer limitSize,
                                         @Param("isScore") Integer isScore,
                                         @Param("isProjectDepartment") Integer isProjectDepartment);


    Integer countQuerySecondTarget(@Param("departmentId") Integer departmentId, @Param("startTime") Date startTime,
                                   @Param("endTime") Date endTime,@Param("isScore") Integer isScore,@Param("isProjectDepartment") Integer isProjectDepartment);


    /**
     *  判断 该公司下所有部门 该季度下的 第二维目标是否制定
     *  如果没有制定 则返回 0 制定后 返回 1
     * @param companyId
     * @param startTime
     * @param endTime
     * @return
     */
    Integer countSecondTargetByCompanyId(@Param("companyId") Integer companyId,@Param("startTime") Date startTime,
                                         @Param("endTime") Date endTime);

    /**
     * 查询 部门二维 目标数据
     * @param departmentId
     * @param startTime
     * @param endTime
     * @return
     */
    SecondTarget querySecondTargetByItem(@Param("departmentId") Integer departmentId, @Param("startTime") Date startTime,
                                         @Param("endTime") Date endTime);


    /**
     * 二维统计 web
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param userId 用户id
     * @param departmentId 团队id
     * @param companyId 公司id
     * @param type 查询类型 0 :个人 1:部门 2：公司
     * @param groupType 分组类型  0 :个人 1:部门 2：公司
     * @return
     */
    List<TwoStatistics> listForWeb(@Param("startTime") Date startTime ,
                                   @Param("endTime") Date endTime ,
                                   @Param("userId") Integer userId ,
                                   @Param("departmentId") Integer departmentId ,
                                   @Param("companyId") Integer companyId ,
                                   @Param("type") Integer type ,
                                   @Param("groupType") Integer groupType,
                                   @Param("pageArgs") PageArgs pageArgs,
                                   @Param("monthStartTime") Date monthStartTime,
                                   @Param("monthEndTime") Date monthEndTime);

    /**
     * 二维统计条数 web
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param userId 用户id
     * @param departmentId 团队id
     * @param companyId 公司id
     * @param type 查询类型 0 :个人 1:部门 2：公司
     * @param groupType 分组类型  0 :个人 1:部门 2：公司
     * @return
     */
    List<Object> listForWebCount(@Param("startTime") Date startTime ,
                        @Param("endTime") Date endTime ,
                        @Param("userId") Integer userId ,
                        @Param("departmentId") Integer departmentId ,
                        @Param("companyId") Integer companyId ,
                        @Param("type") Integer type ,
                        @Param("groupType") Integer groupType);


    /**
     * 二维统计 第三种计算方式 web
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param userId 用户id
     * @param departmentId 团队id
     * @param companyId 公司id
     * @param type 查询类型 0 :个人 1:部门 2：公司
     * @param groupType 分组类型  0 :个人 1:部门 2：公司
     * @return
     */
    List<TwoStatistics> listForWebThree(@Param("startTime") Date startTime ,
                                   @Param("endTime") Date endTime ,
                                   @Param("userId") Integer userId ,
                                   @Param("departmentId") Integer departmentId ,
                                   @Param("companyId") Integer companyId ,
                                   @Param("type") Integer type ,
                                   @Param("groupType") Integer groupType,
                                   @Param("pageArgs") PageArgs pageArgs);

    /**
     * 二维统计条数 第三种计算方式 web
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param userId 用户id
     * @param departmentId 团队id
     * @param companyId 公司id
     * @param type 查询类型 0 :个人 1:部门 2：公司
     * @param groupType 分组类型  0 :个人 1:部门 2：公司
     * @return
     */
    List<Object> listForWebCountThree(@Param("startTime") Date startTime ,
                        @Param("endTime") Date endTime ,
                        @Param("userId") Integer userId ,
                        @Param("departmentId") Integer departmentId ,
                        @Param("companyId") Integer companyId ,
                        @Param("type") Integer type ,
                        @Param("groupType") Integer groupType);
}
