package com.magic.ereal.business.mapper;

import com.magic.ereal.business.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 外部项目管理 持久层接口
 * Created by Eric Xie on 2017/4/26 0026.
 */
public interface IProjectMapper {

    /**
     * 导出外部项目
     * @param map
     * @return
     */
    List<ExportProject> exportProject(Map<String,Object> map);

    /**
     * 批量新增外部项目
     * @param projects
     * @return
     */
    Integer batchAddProject(@Param("projects") List<Project> projects);

    List<ExcelProject> excelProject(@Param("projectGroupId") Integer projectGroupId,
                                     @Param("status") Integer status,
                                     @Param("departmentId") Integer departmentId,
                                     @Param("projectTypeId") Integer projectTypeId);


    /**
     * 删除项目数据
     * @param projectId 项目ID
     * @return
     */
    Integer delProject(@Param("projectId") Integer projectId);

    /**
     *  通过ID 获取 项目的基础数据(数据库表字段的所有数据 不包含业务数据)
     * @param id 项目ID
     * @return
     */
    Project queryBaseProjectById(@Param("id") Integer id);

    /**
     *  根据项目ID  包含 周验收 数据 && 用户在该项目中所得的K值总和
     * @param id 项目ID
     * @return
     */
    Project queryProjectById(@Param("id") Integer id);


    /**
     * 新增项目，部分字段初始，详见配置 以及 需求文档
     * @param project
     * @return
     */
    Integer addProject(@Param("project") Project project);


    /**
     *  更新 项目 不为空的字段 根据ID
     *  某些影响项目 流程的更新权限，不给
     *  注：不更新的字段 需置空
     * @param project
     * @return
     */
    Integer updateProject(@Param("project") Project project);

    /**
     * 获取用户 关联的项目列表
     * 目前允许以下角色调用接口：
     *  A、B、C导师 以及普通用户
     * @param userId 用户ID
     * @param roleId 该用户角色
     * @return
     */
    List<Project> queryProjectByItem(@Param("userId") Integer userId,@Param("roleId") Integer roleId,
                                     @Param("limit") Integer limit,@Param("limitSize") Integer limitSize,
                                     @Param("status") Integer status,@Param("departmentId") Integer departmentId,
                                     @Param("projectTypeId") Integer projectTypeId,@Param("sortType") Integer sortType ,
                                     @Param("startWeek") Date startWeek,@Param("endWeek") Date endWeek,
                                     @Param("startMonth") Date startMonth,@Param("endMonth") Date endMonth);


    /**
     * 获取用户 关联的项目列表
     * 目前允许以下角色调用接口：
     *  A、B、C导师 以及普通用户
     * @param userId 用户ID
     * @param roleId 该用户角色
     * @return
     */
    List<Project> queryProjectByItemForWeb(@Param("userId") Integer userId,@Param("roleId") Integer roleId,
                                     @Param("limit") Integer limit,@Param("limitSize") Integer limitSize,
                                           @Param("projectNumber") String projectNumber,
                                           @Param("projectName") String projectName,
                                           @Param("projectGroupId") Integer projectGroupId,
                                           @Param("status") Integer status,
                                           @Param("departmentId") Integer departmentId,
                                           @Param("projectTypeId") Integer projectTypeId,
                                           @Param("isManagerId") Integer isManagerId,
                                           @Param("isTerminate") Integer isTerminate);
    /**
     * 获取用户 关联的项目列表
     * 目前允许以下角色调用接口：
     *  A、B、C导师 以及普通用户
     * @param userId 用户ID
     * @param roleId 该用户角色
     * @return
     */
    Integer countProjectByItemForWeb(@Param("userId") Integer userId,
                                     @Param("roleId") Integer roleId,
                                     @Param("projectNumber") String projectNumber,
                                     @Param("projectName") String projectName,
                                     @Param("projectGroupId") Integer projectGroupId,
                                     @Param("status") Integer status,
                                     @Param("departmentId") Integer departmentId,
                                     @Param("projectTypeId") Integer projectTypeId,
                                     @Param("isManagerId") Integer isManagerId,
                                     @Param("isTerminate") Integer isTerminate);

    /**
     * 批量通过内部验收
     * @param list
     */
    void updateListStatus(List<Project> list);

    /**
     * 根据ids 获取项目
     * @param ids
     * @return
     */
    List<Project> getByIds(@Param("ids")Integer[] ids);

    /**
     * 获取需要可审核的项目
     * @param limit
     * @param limitSize
     * @param cTeacherId
     * @return
     */
    List<Project> getAuditProject(@Param("limit") Integer limit,
                                  @Param("limitSize") Integer limitSize,
                                  @Param("userId")Integer userId,
                                  @Param("roleId") Integer roleId,
                                  @Param("departmentId") Integer departmentId,
                                  @Param("projectTypeId") Integer projectType,
                                  @Param("sortType") Integer sortType,
                                  @Param("startWeek") Date startWeek,@Param("endWeek") Date endWeek,
                                  @Param("startMonth") Date startMonth,@Param("endMonth") Date endMonth);

    /**
     * 获取需要可审核的项目
     * @param limit
     * @param limitSize
     * @return
     */
    List<Project> getAuditProjectForWeb(@Param("limit") Integer limit,
                                  @Param("limitSize") Integer limitSize,
                                  @Param("userId")Integer userId,
                                  @Param("roleId") Integer roleId);

    /**
     * 获取需要可审核的项目
     * @return
     */
    Integer countAuditProject( @Param("userId")Integer userId,
                                  @Param("roleId") Integer roleId);

    /**
     * 根据时间段获取破题统计
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param departmentId 部门id
     * @param type 1未破   2 半破   3 全破
     * @param roleId 角色id
     * @return
     */
    List<PoStatistics> getByTime(@Param("startTime") Date startTime ,
                                 @Param("endTime") Date endTime ,
                                 @Param("departmentId") Integer departmentId ,
                                 @Param("type") Integer type,
                                 @Param("roleId") Integer roleId);

    /**
     * 传递卡 外部项目筛选
     * @param userId 用户id
     * @return
     */
    Set<Project> getWorkDiaryPro(@Param("userId") Integer userId,@Param("roleId") Integer roleId);
}
