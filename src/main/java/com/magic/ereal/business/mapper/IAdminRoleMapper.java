package com.magic.ereal.business.mapper;

import com.magic.ereal.business.entity.AdminRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Eric Xie on 2017/6/12 0012.
 */
public interface IAdminRoleMapper {


    Integer batchAddAdminRole(@Param("adminRoles") List<AdminRole> adminRoles);

}
