package com.magic.ereal.business.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 角色 -- 实体
 * @author lzh
 * @create 2017/4/17 14:31
 */
public class Role implements Serializable {

    private Integer id;

    /** 角色名 */
    private String roleName;

    /** 角色类型 0：总公司角色  1常规角色 */
    private Integer type;

    /** 角色等级 1：经理级别  0：普通员工 */
    private Integer level;

    /** 角色描述 */
    private String describe;

    /** 角色权限集合 */
    private List<Menu> menuList = new ArrayList<>();

    /** 角色对应的 权限集合  后台多角色使用 */
    private List<RoleMenu> roleUrls;

    /** 是否有效 0 无效 1 有效  缺省值 1 */
    private Integer isValid;

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public List<RoleMenu> getRoleUrls() {
        return roleUrls;
    }

    public void setRoleUrls(List<RoleMenu> roleUrls) {
        this.roleUrls = roleUrls;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }

    /** 获取 是否有效 0 无效 1 有效  缺省值 1 */
    public Integer getIsValid() {
        return this.isValid;
    }

    /** 设置 是否有效 0 无效 1 有效  缺省值 1 */
    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }
}
