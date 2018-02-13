package com.magic.ereal.business.service;

import com.alibaba.fastjson.JSONArray;
import com.magic.ereal.business.entity.ProjectWeekAcceptance;
import com.magic.ereal.business.entity.ProjectWeekKAllocation;
import com.magic.ereal.business.exception.InterfaceCommonException;
import com.magic.ereal.business.mapper.IProjectWeekAcceptanceMapper;
import com.magic.ereal.business.mapper.IProjectWeekKAllocationMapper;
import com.magic.ereal.business.util.StatusConstant;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 项目周验收报告后的 K值结果分配  -- 业务
 * @author lzh
 * @create 2017/5/5 11:20
 */
@Service
public class ProjectWeekKAllocationService {

    @Resource
    private IProjectWeekKAllocationMapper projectWeekKAllocationMapper;
    @Resource
    private IProjectWeekAcceptanceMapper projectWeekAcceptanceMapper;

    /**
     * 添加
     * @param projectWeekKAllocation 项目周验收报告后的 K值结果分配
     *                               每个阶段的总K值  由客户端提交上来
     * @param createUserId 创建者id
     * @throws Exception
     */
    @Transactional
    public void save(String projectWeekKAllocation ,Integer createUserId) throws Exception {
        List<ProjectWeekKAllocation> list = JSONArray.parseArray(projectWeekKAllocation,ProjectWeekKAllocation.class);
        for (ProjectWeekKAllocation allocation : list) {
            if (null == allocation.getProjectTypeSectionId() || null == allocation.getRatio() ||
                    null == allocation.getProjectWeekAcceptanceId() || null == allocation.getUserId() || null == allocation.getSectionSumK()) {
                throw new InterfaceCommonException(StatusConstant.FIELD_NOT_NULL,"字段不能为空");
            }
            allocation.setCreateUserId(createUserId);
        }
        if (list.size() > 0) {
            projectWeekKAllocationMapper.batchAddProjectWeekKAllocation(list);
            ProjectWeekAcceptance acceptance = new ProjectWeekAcceptance();
            acceptance.setStatus(2); // 设置此周验收数据状态为已经分配
            acceptance.setId(list.get(0).getProjectWeekAcceptanceId());
            acceptance.setUpdateTime(new Date());
            projectWeekAcceptanceMapper.updateProjectWeekAcceptance(acceptance);
        }
    }


    /**
     * 添加
     * @param allocations 项目周验收报告后的 K值结果分配
     * @param weekId 周ID
     * @throws Exception
     */
    @Transactional
    public void save(List<ProjectWeekKAllocation> allocations,Integer weekId) throws Exception {
        if (allocations.size() > 0) {
            projectWeekKAllocationMapper.batchAddProjectWeekKAllocation(allocations);
            ProjectWeekAcceptance acceptance = new ProjectWeekAcceptance();
            acceptance.setStatus(2); // 设置此周验收数据状态为已经分配
            acceptance.setId(weekId);
            acceptance.setUpdateTime(new Date());
            projectWeekAcceptanceMapper.updateProjectWeekAcceptance(acceptance);
        }
    }


    public List<ProjectWeekKAllocation> queryKAllocation(Integer weekId){
        return projectWeekKAllocationMapper.list(weekId);
    }
}
