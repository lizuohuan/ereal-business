package com.magic.ereal.business.service;

import com.magic.ereal.business.entity.ProjectTypeSection;
import com.magic.ereal.business.mapper.IProjectTypeSectionMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 项目类型 / 课题分类 业务层
 * Created by Eric Xie on 2017/4/26 0026.
 */
@Service
public class ProjectTypeSectionService {

    @Resource
    private IProjectTypeSectionMapper projectTypeSectionMapper;

    /**
     * 根据项目id获取阶段数据
     * @param projectId 项目id
     * @return
     */
    public List<ProjectTypeSection> getByProjectId(Integer projectId) {
        return projectTypeSectionMapper.getByProjectId(projectId);
    }

}
