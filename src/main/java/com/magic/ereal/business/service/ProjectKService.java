package com.magic.ereal.business.service;

import com.alibaba.fastjson.JSONArray;
import com.magic.ereal.business.entity.ProjectAcceptanceRecord;
import com.magic.ereal.business.entity.ProjectK;
import com.magic.ereal.business.exception.InterfaceCommonException;
import com.magic.ereal.business.mapper.IProjectAcceptanceRecordMapper;
import com.magic.ereal.business.mapper.IProjectKMapper;
import com.magic.ereal.business.mapper.IUserMapper;
import com.magic.ereal.business.util.StatusConstant;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 外部项目 内部结项 & 外部结项   K值比例分配 -- 业务
 * @author lzh
 * @create 2017/5/9 18:23
 */
@Service
public class ProjectKService {

    @Resource
    private IProjectKMapper projectKMapper;
    @Resource
    private IUserMapper userMapper;
    @Resource
    private IProjectAcceptanceRecordMapper projectAcceptanceRecordMapper;
    /**
     * 批量新增 分配结果
     * @param projectKs
     * @param createTimeUserId 创建记录的用户
     * @return
     */
    public void batchAddProjectK(String projectKs ,Integer createTimeUserId) throws Exception {
        List<ProjectK> list = JSONArray.parseArray(projectKs,ProjectK.class);
        if (null != list && list.size() > 0) {
            List<ProjectK> projectKs1 =  projectKMapper.queryProjectKByProjectRecordId(list.get(0).getProjectRecordId());
            

            

            if (projectKs1.size() > 0) {
                throw new InterfaceCommonException(StatusConstant.OBJECT_EXIST,"已分配成果，不能继续分配");
            } else {
                ProjectAcceptanceRecord p =
                        projectAcceptanceRecordMapper.info(list.get(0).getProjectRecordId());
                if (p.getStatus().equals(12) || p.getStatus().equals(13)
                        || p.getStatus().equals(1) || p.getStatus().equals(2)) {
                    for (ProjectK k : list) {
                        //// TODO: 2017/5/11 计算结项审核k值
                        k.setSumK(p.getBadSumK() * k.getRatio() / 100);
                        k.setCreateUserId(createTimeUserId);
                    }
                } else {
                    throw new InterfaceCommonException(StatusConstant.ORDER_STATUS_ABNORMITY,"记录状态异常，不能进行分配");
                }

            }

        } else {
            throw new InterfaceCommonException(StatusConstant.FIELD_NOT_NULL,"字段不能为空");
        }
        projectKMapper.batchAddProjectK(list);
    }

    /**
     * 获取外部项目 k值分配 和 耗时
     * @param projectRecordId 记录ID
     * @param type 0：内部  1：外部
     * @return
     */
    public Map<String,Object> getKAndUser(Integer projectRecordId ,Integer type ) {
        Map<String ,Object> map = new HashMap<>();
        map.put("projectK",projectKMapper.queryProjectKByProjectRecordId(projectRecordId));
        if (type == 0 ) {
            map.put("userH",userMapper.queryInnerUserH(projectRecordId));
        } else if (type == 1){
            map.put("userH",userMapper.queryOuterUserH(projectRecordId));
        } else {
            throw new InterfaceCommonException(StatusConstant.ORDER_STATUS_ABNORMITY,"类型异常");
        }
        return map;

    }
}
