package com.magic.ereal.business.mapper;

import com.magic.ereal.business.entity.ProjectTypeSection;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 项目类型  阶段
 * Created by Eric Xie on 2017/4/26 0026.
 */
public interface IProjectTypeSectionMapper {


    /**
     * 新增 项目类型 阶段数据
     * 添加时，提醒确认之后 提交，请勿随意修改
     * @param section
     * @return
     */
    Integer addProjectTypeSection(@Param("section") ProjectTypeSection section);

    /**
     * 更新不为空的字段 根据ID，更新前，请提醒 请勿随意修改
     *
     * @param section
     * @return
     */
    Integer updateProjectTypeSection(@Param("section") ProjectTypeSection section);

    /**
     * 通过 项目类型 / 课题类型 查询 阶段集合
     * @param projectTypeId
     * @return
     */
    List<ProjectTypeSection> queryProjectTypeSectionByType(@Param("projectTypeId") Integer projectTypeId);


    /**
     * 根据 项目类型 / 课题类型删除
     * @param projectTypeId
     */
    void delete(@Param("projectTypeId") Integer projectTypeId);

    /**
     * 根据项目id获取阶段数据
     * @param projectId
     * @return
     */
    List<ProjectTypeSection> getByProjectId(@Param("projectId") Integer projectId);

}
