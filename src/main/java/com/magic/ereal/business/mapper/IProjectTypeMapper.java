package com.magic.ereal.business.mapper;

import com.magic.ereal.business.entity.ProjectType;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 项目类型 / 课题类型 持久层接口
 * Created by Eric Xie on 2017/4/26 0026.
 */
public interface IProjectTypeMapper {


    /**
     * 新增 项目类型
     * @param projectType
     * @return
     */
    Integer addProjectType(@Param("projectType") ProjectType projectType);

    /**
     * 更新项目类型 不为空的字段 通过ID
     * @param projectType
     * @return
     */
    Integer updateProjectType(@Param("projectType") ProjectType projectType);

    /**
     * 分页查询 项目课题类型  不包括 阶段数据
     * @param map {
     *            isShow 是否显示   null :查询所有，1：查询 显示的 0: 查询不显示的
     *            pageArgs 分页实体
     *             }
     * @param
     * @param
     * @return
     */
    List<ProjectType> queryProjectType(Map<String,Object> map);

    /**
     * 根据ID 查询 阶段数据 包括 阶段数据
     * @param id
     * @return
     */
    ProjectType queryProjectTypeById(@Param("id") Integer id);

    /**
     * 下拉列表使用
     * @return
     */
    List<ProjectType> listSelect();

    /**
     * 查询所有的 课题类型
     * @return
     */
    List<ProjectType> queryAllType();

}
