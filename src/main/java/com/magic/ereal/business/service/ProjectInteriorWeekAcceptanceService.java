package com.magic.ereal.business.service;

import com.magic.ereal.business.entity.ProjectInterior;
import com.magic.ereal.business.entity.ProjectInteriorWeekAcceptance;
import com.magic.ereal.business.entity.ProjectInteriorWeekKAllocation;
import com.magic.ereal.business.exception.InterfaceCommonException;
import com.magic.ereal.business.mapper.IProjectInteriorMapper;
import com.magic.ereal.business.mapper.IProjectInteriorWeekAcceptanceMapper;
import com.magic.ereal.business.mapper.IProjectInteriorWeekKAllocationMapper;
import com.magic.ereal.business.util.StatusConstant;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 内部项目 业务层
 * Created by Eric Xie on 2017/5/2 0002.
 */
@Service
public class ProjectInteriorWeekAcceptanceService {

    @Resource
    private IProjectInteriorWeekAcceptanceMapper projectInteriorWeekAcceptanceMapper;
    @Resource
    private IProjectInteriorWeekKAllocationMapper projectInteriorWeekKAllocationMapper;
    @Resource
    private IProjectInteriorMapper projectInteriorMapper;


    /**
     * 通过ID 获取 周验收详情， 包括 分配比例的数据
     * @param id
     * @return
     */
    public ProjectInteriorWeekAcceptance queryAcceptanceById(Integer id){
        return projectInteriorWeekAcceptanceMapper.queryAcceptanceById(id);
    }



    /**
     *  新增/申请内部周验收
     * @param acceptance
     */
    public void addProjectInteriorWeekAcceptance(ProjectInteriorWeekAcceptance acceptance){
        

        List<ProjectInteriorWeekAcceptance> list = 
                projectInteriorWeekAcceptanceMapper.queryProjectInteriorWeekAcceptanceByProject(
                        acceptance.getProjectInteriorId());
        for (ProjectInteriorWeekAcceptance projectInteriorWeekAcceptance : list) {
            //审核状态  0:提交  1:验收完成 2:已分配K值
            if (0 == projectInteriorWeekAcceptance.getStatus()) {
                throw new InterfaceCommonException(StatusConstant.ARGUMENTS_EXCEPTION,"您已提交申请周验收，正在等待验收...");
            }
        }
        projectInteriorWeekAcceptanceMapper.addProjectInteriorWeekAcceptance(acceptance);
    }

    /**
     * 内部周验收 通过审核
     * @param acceptance 周验收对象 总进度必须有值
     */
    @Transactional
    public void approvedProjectInteriorWeek(ProjectInteriorWeekAcceptance acceptance,Integer isFinish) throws Exception{


        // TODO: 2017/5/18 质量系数=（100×20%+85×20%+95×20%+120×20%+110×20%）/100=1.02
        // TODO: 2017/5/18 质量系数=（P 得分 ×W P +A 得分 ×W A +N 得分 ×W N +E 得分 ×W E +L 得分 ×W L ）/100


        /**P 值*/
        Double p ;
        /**A 值*/
        Double a ;
        /**N 值*/
        Double n = null;
        /**E 值*/
        Double e = null;
        /**L 值*/
        Double l = null;
        /** 质量系数 */
        Double qualityFactorQFactor = 0.0;
        if (null == acceptance) {
            throw new InterfaceCommonException(StatusConstant.FIELD_NOT_NULL,"字段不能为空");
        } else {
            if (null == acceptance.getProjectInteriorId() || null == acceptance.getProgress()) {
                throw new InterfaceCommonException(StatusConstant.FIELD_NOT_NULL,"字段不能为空");
            }
        }


        //内部项目详情
        ProjectInterior projectInterior = projectInteriorMapper.info(acceptance.getProjectInteriorId());
        if (null == projectInterior) {
            throw new InterfaceCommonException(StatusConstant.ARGUMENTS_EXCEPTION,"未查询到对应内部项目");
        }
        if (null == acceptance.getP() || null == acceptance.getA()) {
            throw new InterfaceCommonException(StatusConstant.FIELD_NOT_NULL,"字段不能为空");
        }
        p = acceptance.getP();
        a = acceptance.getA();
        if (acceptance.getProgress() >= 50) {
            //总进度 >= 50% 时
            if (null == acceptance.getE() || null == acceptance.getL()) {
                throw new InterfaceCommonException(StatusConstant.FIELD_NOT_NULL,"字段不能为空");
            }
            e = acceptance.getE();
            l = acceptance.getL();
            //是否对内 0：对内  1：对外
            if (projectInterior.getAtHome() == 1 ) {
                if (null == acceptance.getN()) {
                    throw new InterfaceCommonException(StatusConstant.FIELD_NOT_NULL,"字段不能为空");
                }
                n = acceptance.getN();
                qualityFactorQFactor = p * 0.2 + a * 0.2 + e * 0.2 + l * 0.2 + n * 0.2;
            } else {
                qualityFactorQFactor = p * 0.25 + a * 0.25 + e * 0.25 + l * 0.25;
            }
        } else {
            qualityFactorQFactor = p * 0.5 + a * 0.5;
        }


        Double progress = 0.0;
        // 查询上一周的 周验收数据
        ProjectInteriorWeekAcceptance preAcceptance = projectInteriorWeekAcceptanceMapper.queryPreAcceptance(projectInterior.getId());
        if(null != preAcceptance && null != preAcceptance.getProgress()){
            progress = preAcceptance.getTotalK() == null ?  0.0 : preAcceptance.getTotalK();
        }
        // 计算本周K值总和
        // 本周总K值
        Double totalK = acceptance.getProgress() == 0 ? 0 : projectInterior.getInitWorkload() * 8.5 / 135 * (acceptance.getProgress() / 100) * (qualityFactorQFactor / 100);
        //本周的增量
        Double sumK = totalK - progress;
//        Double sumK = projectInterior.getInitWorkload() * 8.5 / 135 * ((acceptance.getProgress() - progress) / 100) * (qualityFactorQFactor / 100);
        acceptance.setStatus(1);
        acceptance.setIsAdd(sumK < 0 ? 0 : 1);
        acceptance.setSumK(Math.abs(sumK));
        acceptance.setUpdateTime(new Date());
        acceptance.setA(a);
        acceptance.setE(e);
        acceptance.setL(l);
        acceptance.setN(n);
        acceptance.setP(p);
        acceptance.setTotalK(totalK);
        projectInteriorWeekAcceptanceMapper.updateProjectInteriorWeekAcceptance(acceptance);
        // 如果评分为 100，则自动结束内部项目
        // 结项由客户端决定
        if(null != isFinish && isFinish == 1){
            ProjectInterior interior = new ProjectInterior();
            interior.setId(projectInterior.getId());
            interior.setProjectStatus(1);
            interior.setOverTime(new Date());
            projectInteriorMapper.update(interior);
        }
//        if(acceptance.getProgress() >= 100){
//
//        }
    }

    /**
     * 比例分配
     * @param allocations 比例分配数据集合
     *                    之前需要判断 当周的状态 必须为审核之后
     * @throws Exception
     */
    @Transactional
    public void allocationRatio(List<ProjectInteriorWeekKAllocation> allocations) throws Exception{

        if(null == allocations || allocations.size() == 0){
            throw new Exception("集合为空");
        }
        ProjectInteriorWeekAcceptance weekAcceptance = projectInteriorWeekAcceptanceMapper.queryBaseAcceptanceById(allocations.get(0).getWeekId());
        if (null == weekAcceptance) {
            throw new InterfaceCommonException(StatusConstant.ARGUMENTS_EXCEPTION,"未查询到对应的周验收提交数据");
        } else {
            //审核状态  0:提交  1:验收完成 2:已分配K值
            if (weekAcceptance.getStatus() == 0) {
                throw new InterfaceCommonException(StatusConstant.ORDER_STATUS_ABNORMITY,"当前状态错误，请先验收进行验收，验收完成后，再进行K值比例分配");
            }
            if (weekAcceptance.getStatus() == 2) {
                throw new InterfaceCommonException(StatusConstant.ORDER_STATUS_ABNORMITY,"当前状态错误，K值比例分配已经完成，不能再次进行分配");
            }
        }
        for (ProjectInteriorWeekKAllocation allocation : allocations){
            allocation.setK((weekAcceptance.getIsAdd() == 0 ? -weekAcceptance.getSumK() : weekAcceptance.getSumK()) * (allocation.getRatio() / 100));
        }
        projectInteriorWeekKAllocationMapper.batchAddProjectInteriorWeekKAllocation(allocations);
        ProjectInteriorWeekAcceptance acceptance = new ProjectInteriorWeekAcceptance();
        acceptance.setStatus(2); // 已经分配
        acceptance.setId(weekAcceptance.getId());
        projectInteriorWeekAcceptanceMapper.updateProjectInteriorWeekAcceptance(acceptance);
    }


    /**
     * 在 提交成员分配结果页面，查询周验收基础数据 以及 员工的工时列表
     * @param weekId 周ID
     * @return
     */
    public ProjectInteriorWeekAcceptance queryAcceptanceIncludeUserH(Integer weekId){
        return projectInteriorWeekAcceptanceMapper.queryAcceptanceIncludeUserH(weekId);
    }





}
