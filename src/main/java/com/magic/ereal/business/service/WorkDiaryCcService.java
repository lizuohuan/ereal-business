package com.magic.ereal.business.service;

import com.magic.ereal.business.entity.WorkDiaryCc;
import com.magic.ereal.business.exception.InterfaceCommonException;
import com.magic.ereal.business.mapper.IWorkDiaryCcMapper;
import com.magic.ereal.business.util.StatusConstant;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 日志抄送人 -- 业务
 * @author lzh
 * @create 2017/4/25 17:57
 */
@Service
public class WorkDiaryCcService {

    @Resource
    private IWorkDiaryCcMapper workDiaryCcMapper;

    /**
     * 批量添加抄送人
     * @param userIds 被抄送人id （逗号隔开）
     * @param workDiaryId 日志id
     */
    public void save(String userIds,Integer workDiaryId) {
        List<WorkDiaryCc> workDiaryCcs = new ArrayList<>();
        if (null == userIds || "".equals(userIds) || null == workDiaryId) {
            throw new InterfaceCommonException(StatusConstant.FIELD_NOT_NULL,"字段不能为空");
        }
        String[] userId = userIds.split(",");
        for (int i = 0 ; i < userId.length ; i ++ ) {
            WorkDiaryCc workDiaryCc = new WorkDiaryCc();
            workDiaryCc.setUserId(Integer.parseInt(userId[i]));
            workDiaryCc.setWorkDiaryId(workDiaryId);
            workDiaryCcs.add(workDiaryCc);
        }
        workDiaryCcMapper.save(workDiaryCcs);
    }
}
