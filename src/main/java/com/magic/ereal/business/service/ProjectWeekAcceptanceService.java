package com.magic.ereal.business.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.magic.ereal.business.entity.ProjectTypeSection;
import com.magic.ereal.business.entity.ProjectWeekAcceptance;
import com.magic.ereal.business.entity.ProjectWeekJson;
import com.magic.ereal.business.exception.InterfaceCommonException;
import com.magic.ereal.business.mapper.IProjectInteriorWeekKAllocationMapper;
import com.magic.ereal.business.mapper.IProjectTypeSectionMapper;
import com.magic.ereal.business.mapper.IProjectWeekAcceptanceMapper;
import com.magic.ereal.business.util.IsEmpty;
import com.magic.ereal.business.util.StatusConstant;
import com.sun.xml.internal.bind.v2.TODO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.List;

/**
 * 外部项目 周验收 -- 业务
 * @author lzh
 * @create 2017/5/4 16:26
 */
@Service
public class ProjectWeekAcceptanceService {

    @Resource
    private IProjectWeekAcceptanceMapper projectWeekAcceptanceMapper;
    @Resource
    private IProjectTypeSectionMapper projectTypeSectionMapper;
    @Resource
    private IProjectInteriorWeekKAllocationMapper projectInteriorWeekKAllocationMapper;


    /**
     * 查询周验收的 基础数据
     * @param id
     * @return
     */
    public ProjectWeekAcceptance queryProjectWeekAcceptanceById(Integer id){
        return projectWeekAcceptanceMapper.queryProjectWeekAcceptanceById(id);
    }

    /**
     * 申请周验收
     * @param acceptance
     */
    public void save(ProjectWeekAcceptance acceptance) {
        projectWeekAcceptanceMapper.addProjectWeekAcceptance(acceptance);
    }


    /**
     * 进行验收
     * @param acceptance
     */
    @Transactional
    public void update(ProjectWeekAcceptance acceptance) throws Exception {

        // TODO: 2017/5/5  临时k比例  1:8.5
        Double temporaryK = 8.5;

        //总k
        double totalK = 0.0;
        double sumK = 0.0;

        //周验收数据 集合
        List<ProjectWeekJson> list = JSONArray.parseArray(acceptance.getSectionDetail(),ProjectWeekJson.class);
        //获取阶段数据
        List<ProjectTypeSection> projectTypeSections = projectTypeSectionMapper.getByProjectId(acceptance.getProjectId());
        out:for (ProjectTypeSection section : projectTypeSections) {
            for (ProjectWeekJson json : list) {
                if (json.getSectionId().equals(section.getId())) {
                    double sk = section.getSectionDays() * temporaryK / 135 * (json.getQuality() / 100 * json.getSchedule() / 100);
                    // 计算增量K 在下面进行计算
                    json.setSk(sk); // 当前阶段的总K值
                    json.setSectionNum(section.getSectionNum());
                    totalK += sk;
                    continue out;
                }
            }
        }
        // 查询上一周的K值 对比 设置是K值  增减
        ProjectWeekAcceptance weekAcceptance = projectWeekAcceptanceMapper.queryProjectPreWeek(acceptance.getProjectId());

        // 计算增量K
        if(null != weekAcceptance &&  null != weekAcceptance.getSectionDetail()){
            // 上一周的K值集合
            List<ProjectWeekJson> preList = JSONArray.parseArray(weekAcceptance.getSectionDetail(),ProjectWeekJson.class);
            // 计算增量
            out:for (ProjectWeekJson preWeek : preList){
                for (ProjectWeekJson week : list) {
                    if(week.getSectionId().equals(preWeek.getSectionId())){
                        // 对比阶段K值增减量  设置增量K
                        week.setK(Math.abs(week.getSk() - preWeek.getSk()));
                        continue out;
                    }
                }
            }
        }else {
            for (ProjectWeekJson week : list) {
                week.setK(week.getSk());
            }
        }
        //审核状态  0:提交  1:验收完成
        acceptance.setStatus(1);
        DecimalFormat    df   = new DecimalFormat("######0.0000");
        double preTotalK = weekAcceptance == null ? 0 : weekAcceptance.getTotalK();
        totalK = Double.parseDouble(df.format(totalK));
        preTotalK = Double.parseDouble(df.format(preTotalK));
        if(totalK == preTotalK){
            sumK = 0.0;
            for (ProjectWeekJson json : list){
                json.setK(0.0);
            }
        }else {
            sumK = Math.abs(totalK - preTotalK);
        }
        acceptance.setIsAdd(totalK >= preTotalK ? 1 : 0);
        acceptance.setSumK(sumK);
        acceptance.setTotalK(totalK);
        acceptance.setSectionDetail(JSONArray.toJSONString(list));
        projectWeekAcceptanceMapper.updateProjectWeekAcceptance(acceptance);

    }


    public ProjectWeekAcceptance queryProjectPreWeek(Integer projectId){
        return projectWeekAcceptanceMapper.queryProjectPreWeek(projectId);
    }

    /**
     *  查询项目 的周验收列表详情
     * @param projectId 项目ID
     * @return 周验收集合
     */
    public List<ProjectWeekAcceptance> list(Integer projectId) {
        return projectWeekAcceptanceMapper.queryProjectWeekAcceptanceByProject(projectId);
    }



}
