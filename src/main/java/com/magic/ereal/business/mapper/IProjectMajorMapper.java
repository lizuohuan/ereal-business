package com.magic.ereal.business.mapper;

import com.magic.ereal.business.entity.ProjectMajor;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 内部项目专业 -- 接口
 * @author lzh
 * @create 2017/4/28 10:27
 */
public interface IProjectMajorMapper {

    /**
     * 新增
     * @param projectMajor
     */
    void save(ProjectMajor projectMajor);

    /**
     * 更新
     * @param projectMajor
     */
    void update(ProjectMajor projectMajor);

    /**
     * 列表
     * @return
     */
    List<ProjectMajor> list();

    /**
     * 详情
     * @param id
     * @return
     */
    ProjectMajor info(@Param("id")Integer id);
}
