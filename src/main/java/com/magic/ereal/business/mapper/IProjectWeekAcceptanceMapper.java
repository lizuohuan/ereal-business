package com.magic.ereal.business.mapper;

import com.magic.ereal.business.entity.ProjectWeekAcceptance;
import com.magic.ereal.business.entity.UserK;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 外部项目 周验收 持久层接口
 * Created by Eric Xie on 2017/4/28 0028.
 */
public interface IProjectWeekAcceptanceMapper {

    /**
     *  新增周验收 团队张发起周验收 请求时 创建
     * @param acceptance 待添加的周验收实体
     * @return 影响行数
     */
    Integer addProjectWeekAcceptance(@Param("acceptance") ProjectWeekAcceptance acceptance);


    /**
     * 更新周验收 不为空的字段 根据ID
     * @param acceptance 待更新的周验收实体
     * @return 影响行数
     */
    Integer updateProjectWeekAcceptance(@Param("acceptance") ProjectWeekAcceptance acceptance);


    /**
     *  查询项目 的周验收详情
     * @param projectId 项目ID
     * @return 周验收集合
     */
    List<ProjectWeekAcceptance> queryProjectWeekAcceptanceByProject(@Param("projectId") Integer projectId);


    /**
     *  查询项目 的周验收详情
     * @param projectId 项目ID
     * @return 周验收集合
     */
    List<ProjectWeekAcceptance> queryProjectWeekAcceptanceByProject2(@Param("projectId") Integer projectId);


    /**
     *  通过ID 查询 单个 周验收数据
     * @param id ID
     * @return
     */
    ProjectWeekAcceptance queryProjectWeekAcceptanceById(@Param("id") Integer id);


    /**
     * 统计 参与该项目 所有用户 目前的 K值总和
     * @param projectId
     * @return
     */
    List<UserK> queryUserKByProject(@Param("projectId") Integer projectId);

    /**
     * 查询项目 上一周的 数据
     * @param projectId 项目ID
     * @return
     */
    ProjectWeekAcceptance queryProjectPreWeek(@Param("projectId") Integer projectId);

}
