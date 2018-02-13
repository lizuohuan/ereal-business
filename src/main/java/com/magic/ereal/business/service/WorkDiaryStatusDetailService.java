package com.magic.ereal.business.service;

import com.magic.ereal.business.entity.WorkDiaryStatusDetail;
import com.magic.ereal.business.mapper.IWorkDiaryStatusDetailMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 传递卡/工作日志  状态显示详情 -- 业务
 * @author lzh
 * @create 2017/4/24 14:19
 */
@Service
public class WorkDiaryStatusDetailService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private IWorkDiaryStatusDetailMapper workDiaryStatusDetailMapper;

    /**
     *  根据 传递卡  查询 传递卡的状态进度详情集合
     * @param workDiaryId
     * @return
     */
    public List<WorkDiaryStatusDetail> queryByWorkDiary(Integer workDiaryId) throws Exception {
        return workDiaryStatusDetailMapper.queryByWorkDiary(workDiaryId);
    }

}
