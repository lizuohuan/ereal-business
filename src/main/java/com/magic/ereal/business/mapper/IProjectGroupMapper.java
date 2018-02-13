package com.magic.ereal.business.mapper;

import com.magic.ereal.business.entity.ProjectGroup;
import com.magic.ereal.business.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 项目组 持久层接口
 * Created by Eric Xie on 2017/4/20 0020.
 */
public interface IProjectGroupMapper {


    /**
     *  通过部门ID 查询 部门下所有的项目经理
     * @param departmentId
     * @return
     */
    List<User> queryProjectGroupManagerByDepartmentId(@Param("departmentId") Integer departmentId);

    /**
     *  新增项目组
     * @param projectGroup
     * @return
     */
    Integer addProjectGroup(@Param("projectGroup") ProjectGroup projectGroup);

    /**
     *  根据ID 更新项目组 不为空的字段
     * @param projectGroup
     * @return
     */
    Integer updateProjectGroup(@Param("projectGroup") ProjectGroup projectGroup);


    /**
     *  根据部门ID 查询 项目组 包括项目组所有的成员部分字段
     * @param departmentId
     * @return
     */
    List<ProjectGroup> queryProjectGroupByDepartment(@Param("departmentId") Integer departmentId);


    /**
     * 逻辑删除 项目组
     * @param id
     * @return
     */
    Integer delProjectGroup(@Param("id") Integer id);


    /**
     *  查询用户所在的项目组 不包含项目组组员信息
     * @param userId 用户ID
     * @return 项目组对象
     */
    ProjectGroup queryProjectGroupByUser(@Param("userId") Integer userId);


    /**
     * 根据ID 查询项目组  包含组员信息
     * @param id 项目组ID
     * @return ProjectGroup
     */
    ProjectGroup queryProjectGroupIncludeUsersById(@Param("id") Integer id);

    /**
     * 项目组集合
     * @param map
     * @return
     */
    List<ProjectGroup> list(Map<String , Object> map);
}
