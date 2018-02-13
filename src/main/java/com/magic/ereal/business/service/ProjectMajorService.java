package com.magic.ereal.business.service;

import com.magic.ereal.business.entity.ProjectMajor;
import com.magic.ereal.business.mapper.IProjectMajorMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 内部项目专业 -- 业务
 * @author lzh
 * @create 2017/4/28 11:23
 */
@Service
public class ProjectMajorService {

    @Resource
    private IProjectMajorMapper projectMajorMapper;

    /**
     * 新增
     * @param projectMajor
     */
    public void save(ProjectMajor projectMajor) throws Exception {
        projectMajorMapper.save(projectMajor);
    }

    /**
     * 更新
     * @param projectMajor
     */
    public void update(ProjectMajor projectMajor) throws Exception {
        projectMajorMapper.update(projectMajor);
    }

    /**
     * 列表
     * @return
     */
    public List<ProjectMajor> list() {
        return projectMajorMapper.list();
    }

    /**
     * 详情
     * @param id
     * @return
     */
    public ProjectMajor info(Integer id) throws Exception {
        return projectMajorMapper.info(id);
    }
}
