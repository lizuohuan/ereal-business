package com.magic.ereal.business.mapper;

import com.magic.ereal.business.entity.ProjectWeekKAllocation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 项目周验收报告后的 K值结果分配  -- 接口
 * @author lzh
 * @create 2017/5/5 11:04
 */
public interface IProjectWeekKAllocationMapper {

    /**
     * 获取周验收 K值结果分配列表
     * @param projectWeekAcceptanceId
     * @return
     */
    List<ProjectWeekKAllocation> list(@Param("projectWeekAcceptanceId")Integer projectWeekAcceptanceId);

 /**
     * 获取周验收 K值结果分配列表
     * @param projectWeekAcceptanceId
     * @return
     */
    List<ProjectWeekKAllocation> list2(@Param("projectWeekAcceptanceId")Integer projectWeekAcceptanceId);

    /**
     * 添加
     * @param projectWeekKAllocation
     */
    void save(ProjectWeekKAllocation projectWeekKAllocation);

    /**
     * 批量新增 K值分配结果
     * @param allocations 结果集合
     * @return
     */
    Integer batchAddProjectWeekKAllocation(@Param("allocations") List<ProjectWeekKAllocation> allocations);

}
