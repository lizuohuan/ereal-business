package com.magic.ereal.business.service;

import com.magic.ereal.business.entity.*;
import com.magic.ereal.business.exception.InterfaceCommonException;
import com.magic.ereal.business.mapper.IProjectGroupMapper;
import com.magic.ereal.business.mapper.IProjectGroupUserMapper;
import com.magic.ereal.business.util.StatusConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 项目 -- 业务
 * @author lzh
 * @create 2017/4/25 15:23
 */
@Service
public class ProjectGroupService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private IProjectGroupMapper projectGroupMapper;
    @Resource
    private IProjectGroupUserMapper projectGroupUserMapper;


    public List<User> queryProjectGroupManagerByDepartmentId(Integer departmentId){
        return projectGroupMapper.queryProjectGroupManagerByDepartmentId(departmentId);
    }

    /**
     *  新增用户到 项目组
     * @param userId
     * @param projectGroupId
     */
    public void addUserToProjectGroup(Integer userId,Integer projectGroupId){
        ProjectGroupUser projectGroupUser = new ProjectGroupUser();
        projectGroupUser.setProjectGroupId(projectGroupId);
        projectGroupUser.setUserId(userId);
        projectGroupUserMapper.addProjectGroupUser(projectGroupUser);
    }




    /**
     * 新建项目组
     * @param projectGroup
     */
    @Transactional
    public void addProjectGroup(ProjectGroup projectGroup) throws Exception{
        Integer count = projectGroupMapper.addProjectGroup(projectGroup);
        if (count < 1) {
            throw new InterfaceCommonException(StatusConstant.Fail_CODE,"新建项目组失败");
        }
        // 新增成员到 映射表
        List<ProjectGroupUser> users = new ArrayList<>();
        if(null != projectGroup.getMember()){
            String[] userIds = projectGroup.getMember().split(",");
            for (String id : userIds){
                ProjectGroupUser user = new ProjectGroupUser();
                user.setUserId(Integer.valueOf(id));
                user.setProjectGroupId(projectGroup.getId());
                users.add(user);
            }
        }
        if(users.size() > 0){
            projectGroupUserMapper.batchAddProjectGroupUser(users);
        }
    }

    /**
     * 根据ID 更新项目组 不为空的字段
     * @param projectGroup
     */
    @Transactional
    public void updateProjectGroup(ProjectGroup projectGroup) throws Exception{
        Integer count = projectGroupMapper.updateProjectGroup(projectGroup);
        if (count < 1) {
            throw new InterfaceCommonException(StatusConstant.Fail_CODE,"更新项目组失败");
        }
        projectGroupUserMapper.delete(projectGroup.getId());
        // 新增成员到 映射表
        List<ProjectGroupUser> users = new ArrayList<>();
        if(null != projectGroup.getMember()){
            String[] userIds = projectGroup.getMember().split(",");
            for (String id : userIds){
                ProjectGroupUser user = new ProjectGroupUser();
                user.setUserId(Integer.valueOf(id));
                user.setProjectGroupId(projectGroup.getId());
                users.add(user);
            }
            if(users.size() > 0){
                projectGroupUserMapper.batchAddProjectGroupUser(users);
            }
        }

    }

    /**
     * 根据部门ID 查询 项目组 包括项目组所有的成员部分字段
     * @param departmentId 部门id
     * @return
     */
    public List<ProjectGroup> queryProjectGroupByDepartment(Integer departmentId) {
        return projectGroupMapper.queryProjectGroupByDepartment(departmentId);
    }

    /**
     * 根据ID 查询项目组  包含组员信息
     * @param id 项目组ID
     * @return
     */
    public ProjectGroup queryProjectGroupIncludeUsersById(Integer id) {
        return projectGroupMapper.queryProjectGroupIncludeUsersById(id);
    }

    /**getAllForWebGroup
     * 项目组集合 （web）
     * @param departmentId 部门id
     * @param isValid 是否有效  0:无效 1:有效
     * @param pageArgs 分页实体
     * @return
     */
    public PageList<ProjectGroup> list(Integer departmentId, Integer isManager, String projectName, Integer isValid, PageArgs pageArgs) throws Exception {
        PageList<ProjectGroup> pageList = new PageList<>();
        ProjectGroup projectGroup = new ProjectGroup();
        projectGroup.setDepartmentId(departmentId);
        projectGroup.setProjectManagerId(isManager);
        projectGroup.setProjectName(projectName);
        projectGroup.setIsValid(isValid);
        Map<String,Object> map = new HashMap<>();
        map.put("p",projectGroup);
        //获取总数
        List<ProjectGroup> totalProjectGroup = projectGroupMapper.list(map);
        //总条数
        int count = 0;
        if (null != totalProjectGroup) {
            count = totalProjectGroup.size();
            map.put("pageArgs", pageArgs);
            pageList.setList(projectGroupMapper.list(map));
        }
        pageList.setTotalSize(count);
        return pageList;
    }

    /**
     * 项目组集合(下拉列表) （web）
     * @param departmentId 部门id
     * @return
     */
    public List<ProjectGroup> listSelect(Integer departmentId) throws Exception {
        ProjectGroup projectGroup = new ProjectGroup();
        projectGroup.setDepartmentId(departmentId);
        projectGroup.setIsValid(1);
        Map<String,Object> map = new HashMap<>();
        map.put("p",projectGroup);
        return projectGroupMapper.list(map);
    }


}
