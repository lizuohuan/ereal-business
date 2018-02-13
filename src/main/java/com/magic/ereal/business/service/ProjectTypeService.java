package com.magic.ereal.business.service;

import com.alibaba.fastjson.JSONArray;
import com.magic.ereal.business.entity.PageArgs;
import com.magic.ereal.business.entity.PageList;
import com.magic.ereal.business.entity.ProjectType;
import com.magic.ereal.business.entity.ProjectTypeSection;
import com.magic.ereal.business.mapper.IProjectTypeMapper;
import com.magic.ereal.business.mapper.IProjectTypeSectionMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 项目类型 / 课题类型 -- 业务
 * @author lzh
 * @create 2017/5/4 9:42
 */
@Service
public class ProjectTypeService {


    @Resource
    private IProjectTypeMapper projectTypeMapper;
    @Resource
    private IProjectTypeSectionMapper projectTypeSectionMapper;
    /**
     * 新增 项目类型 同时新增阶段
     * @param projectType 课题类型
     * @param projectTypeSectionJson 阶段json数据
     * @return
     */
    @Transactional
    public void addProjectType(ProjectType projectType,String projectTypeSectionJson) throws Exception {
        projectTypeMapper.addProjectType(projectType);
        if (null != projectTypeSectionJson) {
            List<ProjectTypeSection> list = JSONArray.parseArray(projectTypeSectionJson, ProjectTypeSection.class);
            for (ProjectTypeSection projectTypeSection : list) {
                projectTypeSection.setProjectTypeId(projectType.getId());
                projectTypeSectionMapper.addProjectTypeSection(projectTypeSection);
            }
        }
    }

    /**
     * 更新项目类型 不为空的字段 通过ID
     * @param projectType 课题类型
     * @param projectTypeSectionJson 阶段json数据
     * @return
     */
    @Transactional
    public void updateProjectType(ProjectType projectType,String projectTypeSectionJson) throws Exception {
        projectTypeMapper.updateProjectType(projectType);
        if (null != projectTypeSectionJson) {
            //删除此类型所有阶段
            //重新进行添加
            projectTypeSectionMapper.delete(projectType.getId());
            List<ProjectTypeSection> list = JSONArray.parseArray(projectTypeSectionJson, ProjectTypeSection.class);
            for (ProjectTypeSection projectTypeSection : list) {
                projectTypeSection.setProjectTypeId(projectType.getId());
                projectTypeSectionMapper.addProjectTypeSection(projectTypeSection);
            }
        }
    }

    /**
     * 分页查询 项目课题类型  不包括 阶段数据
     * @param pageArgs 分页实体
     * @param isShow 是否显示   null :查询所有，1：查询 显示的 0: 查询不显示的
     * @return
     */
    public PageList<ProjectType> queryProjectType(PageArgs pageArgs, Integer isShow) throws Exception {
        PageList<ProjectType> pageList = new PageList<>();
        Map<String,Object> map = new HashMap<>();
        map.put("isShow",isShow);
        //总条数
        List<ProjectType> totalList = projectTypeMapper.queryProjectType(map);
        //总条数
        int count = 0;
        if (null != totalList) {
            count = totalList.size();
            map.put("pageArgs",pageArgs);
            pageList.setList(projectTypeMapper.queryProjectType(map));
        }
        pageList.setTotalSize(count);
        return pageList;
    }

    /**
     * 根据ID 查询 阶段数据 包括 阶段数据
     * @param id
     * @return
     */
    public ProjectType queryProjectTypeById(Integer id) throws Exception {
        return projectTypeMapper.queryProjectTypeById(id);
    }

    /**
     * 下拉列表使用
     * @return
     */
    public List<ProjectType> listSelect() throws Exception {
        return projectTypeMapper.listSelect();
    }


    /**
     * 查询所有的 课题类型
     * @return
     */
    public List<ProjectType> queryAllType() {
        return projectTypeMapper.queryAllType();
    }





}
