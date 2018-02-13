package com.magic.ereal.business.mapper;

import com.magic.ereal.business.entity.ProjectK;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *  外部项目 内部结项 & 外部结项   K值比例分配 持久层
 * Created by Eric Xie on 2017/5/9 0009.
 */
public interface IProjectKMapper {


    /**
     * 批量新增 分配结果
     * @param projectKs
     * @return
     */
    Integer batchAddProjectK(@Param("projectKs") List<ProjectK> projectKs);

    /**
     * 通过记录ID 查询当条记录结果的K值分配
     * @param projectRecordId
     * @return
     */
    List<ProjectK> queryProjectKByProjectRecordId(@Param("projectRecordId") Integer projectRecordId);


    /**
     * 通过记录ID 查询当条记录结果的K值分配 导出
     * @param projectRecordId
     * @return
     */
    List<ProjectK> queryProjectKByProjectRecordId2(@Param("projectRecordId") Integer projectRecordId);


}
