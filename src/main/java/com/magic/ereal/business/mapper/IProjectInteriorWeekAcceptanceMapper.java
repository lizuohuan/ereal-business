package com.magic.ereal.business.mapper;

import com.magic.ereal.business.entity.ExcelProjectInteriorNewReport;
import com.magic.ereal.business.entity.ProjectInteriorWeekAcceptance;
import com.magic.ereal.business.entity.UserK;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 内部项目周验收 持久层接口
 * Created by Eric Xie on 2017/5/2 0002.
 */
public interface IProjectInteriorWeekAcceptanceMapper {


    /**
     * 获取项目 最新的一次周数据
     * @param projectId
     * @return
     */
    List<ExcelProjectInteriorNewReport> queryExcelProjectInteriorNewReport(@Param("projectId") Integer projectId);

    /**
     * 新增 内部项目周验收
     * @param acceptance
     * @return
     */
    Integer addProjectInteriorWeekAcceptance(@Param("acceptance") ProjectInteriorWeekAcceptance acceptance);


    /**
     *  根据ID 更新内部项目周验收 不为空的字段
     * @param acceptance
     * @return
     */
    Integer updateProjectInteriorWeekAcceptance(@Param("acceptance") ProjectInteriorWeekAcceptance acceptance);


    /**
     * 根据 内部项目ID  查询 该项目的 周验收集合数据
     * @param projectId 内部项目ID
     * @return
     */
    List<ProjectInteriorWeekAcceptance> queryProjectInteriorWeekAcceptanceByProject(@Param("projectId") Integer projectId);

    /**
     * 根据 内部项目ID  查询 该项目的 周验收集合数据
     * @param projectId 内部项目ID
     * @return
     */
    List<ProjectInteriorWeekAcceptance> queryProjectInteriorWeekAcceptanceByProjectDESC(@Param("projectId") Integer projectId);

    /**
     * 查询该项目 上一周的 周验收报告
     * @param projectId
     * @return
     */
    ProjectInteriorWeekAcceptance queryPreAcceptance(@Param("projectId") Integer projectId);


    /**
     * 通过ID 获取 周验收详情， 包括 分配比例的数据
     * @param id
     * @return
     */
    ProjectInteriorWeekAcceptance queryAcceptanceById(@Param("id") Integer id);


    /**
     * 通过周ID 查询 成员比例分配 页面的 基础数据 包括 团队里面的员工基础数据  暂不用
     * @param id 周ID
     * @return
     */
    ProjectInteriorWeekAcceptance queryAcceptanceApplicationData(@Param("id") Integer id);


    /**
     * 通过ID 获取 周验收详情 基础字段数据
     * @param id
     * @return
     */
    ProjectInteriorWeekAcceptance queryBaseAcceptanceById(@Param("id") Integer id);


    /**
     * 通过 内部项目ID 统计 参与该项目用户 在该项目中获得的K值
     * @param projectInteriorId
     * @return
     */
    List<UserK> queryUserKByProjectInterior(@Param("projectInteriorId") Integer projectInteriorId);

    /**
     * 通过周ID 获取基本信息 包括 参与该项目的员工 在本周内的工时
     * @param weekId 周ID
     * @return
     */
    ProjectInteriorWeekAcceptance queryAcceptanceIncludeUserH(@Param("weekId") Integer weekId);



}
