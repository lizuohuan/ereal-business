package com.magic.ereal.business.mapper;

import com.magic.ereal.business.entity.ExcelUserK;
import com.magic.ereal.business.entity.ProjectInteriorWeekKAllocation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 内部项目 K值比例分配 持久层接口
 * Created by Eric Xie on 2017/5/2 0002.
 */
public interface IProjectInteriorWeekKAllocationMapper {


    /**
     * 查询K值
     * @param weekId
     * @return
     */
    List<ExcelUserK> queryExcelUserKByProject(@Param("weekId") Integer weekId);

    /**
     * 查询K值
     * @param weekId
     * @return
     */
    List<ExcelUserK> queryExcelUserK(@Param("weekId") Integer weekId);

    /**
     * 批量新增 比例分配
     * @param allocations
     * @return
     */
    Integer batchAddProjectInteriorWeekKAllocation(@Param("allocations") List<ProjectInteriorWeekKAllocation> allocations);

    /**
     *  查询 某周 的比例分配详情
     * @param weekId
     * @return
     */
    List<ProjectInteriorWeekKAllocation> queryAllocationByWeek(@Param("weekId") Integer weekId);

}
