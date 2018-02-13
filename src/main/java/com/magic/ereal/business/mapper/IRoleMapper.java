package com.magic.ereal.business.mapper;

import com.magic.ereal.business.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色 -- 接口
 * @author lzh
 * @create 2017/4/18 9:36
 */
public interface IRoleMapper {


    /**
     * 角色列表
     * @param type 角色类型 0：平台角色  1分公司角色
     * @return
     */
    List<Role> list(@Param("type") Integer type);


    /**
     * 角色列表
     * @param type 角色类型 0：平台角色  1分公司角色
     * @return
     */
    List<Role> listWeb(@Param("type") Integer type);


    /**
     * 添加角色
     * @param role 角色实体
     * @return
     */
    void insert(Role role);


    /**
     *  后台用户查询角色集合
     * @param userId
     * @return
     */
    List<Role> queryAdminRole(@Param("userId") Integer userId);

    /**
     *  根据ID 更新不为空的字段
     * @param role
     * @return
     */
    Integer updateRole(@Param("role") Role role);
}
