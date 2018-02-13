package com.magic.ereal.business.mapper;

import com.magic.ereal.business.entity.ProjectGroupUser;
import com.magic.ereal.business.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 项目组 和成员 持久层接口
 * Created by Eric Xie on 2017/5/2 0002.
 */
public interface IProjectGroupUserMapper {

    /**
     * 批量新增 项目组 和成员关系
     * @param projectGroupUser
     * @return
     */
    Integer batchAddProjectGroupUser(@Param("projectGroupUsers") List<ProjectGroupUser> projectGroupUsers);

    /**
     * 新增用户 到 组
     * @param projectGroupUser
     * @return
     */
    Integer addProjectGroupUser(@Param("projectGroupUser") ProjectGroupUser projectGroupUser);

    /**
     *  通过 项目组ID  查询 该项目组下所有的成员
     * @param projectGroup
     * @return
     */
    List<User> queryUserByProjectGroupId(@Param("projectGroup") Integer projectGroup);


    Integer delUserForProjectGroup(@Param("userId") Integer userId,@Param("projectGroupId") Integer projectGroupId);

    /**
     * 根据项目组id删除用户
     * @param projectGroupId
     */
    void delete(@Param("projectGroupId") Integer projectGroupId);
}
