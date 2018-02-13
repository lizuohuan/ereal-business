package com.magic.ereal.business.service;

import com.magic.ereal.business.entity.Role;
import com.magic.ereal.business.mapper.IRoleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色 -- 业务
 * @author lzh
 * @create 2017/4/20 16:13
 */
@Service
public class RoleService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private IRoleMapper roleMapper;

    /**
    * 角色列表
    * @param type 角色类型 0：总公司角色  1常规角色
    * @return
    */
    public List<Role> list(Integer type) {
        try {
            return roleMapper.list(type);
        } catch (Exception e) {
            logger.error("服务器超时，获取角色列表失败",e);
        }
        return null;
    }


    /**
    * 角色列表
    * @param type 角色类型 0：总公司角色  1常规角色
    * @return
    */
    public List<Role> list(Integer type,Integer flag) {
        try {
            if(null == flag){
                return roleMapper.list(type);
            }else{
                return roleMapper.listWeb(type);
            }
        } catch (Exception e) {
            logger.error("服务器超时，获取角色列表失败",e);
        }
        return null;
    }


    public void updateRole(String roleName,String describe,Integer id,Integer isValid){
        Role role = new Role();
        role.setId(id);
        role.setDescribe(describe);
        role.setRoleName(roleName);
        role.setRoleName(roleName);
        role.setIsValid(isValid);
        roleMapper.updateRole(role);
    }


    /**
     * 添加角色
     * @param role 角色实体
     */
    public void insert(Role role) {
        try {
            roleMapper.insert(role);
        } catch (Exception e) {
            logger.error("服务器超时,添加失败",e);
        }
    }
}
