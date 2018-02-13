package com.magic.ereal.business.service;

import com.magic.ereal.business.entity.TimeType;
import com.magic.ereal.business.entity.TimeTypeEntity;
import com.magic.ereal.business.mapper.ITimeTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 时间类型 -- 业务
 * @author lzh
 * @create 2017/4/21 11:59
 */
@Service
public class TimeTypeService{

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private ITimeTypeMapper timeTypeMapper;


    /**
     * 新增时间类型
     * @param timeType
     * @return
     */
    public void addTimeType(TimeType timeType){
        timeTypeMapper.addTimeType(timeType);
    }

    /**
     *  更新时间类型名称
     * @param timeType
     * @return
     */
    public void updateTimeType(TimeType timeType){
        timeTypeMapper.updateTimeType(timeType);
    }


    /**
     *  查询所有有效的 时间类型
     * @return
     */
    public List<TimeType> queryAllTimeType(){
        return timeTypeMapper.queryAllTimeType();
    }

    /**
     * 统计日志 工作时长等
     *  userId、companyId、departmentId 不能同时存在 只能单选
     * @param userId 用户ID
     * @param companyId 分公司ID
     * @param departmentId 部门ID
     * @param time 时间 年月日 / 年月 当 flag:0 时，统计年月日 flag:1 统计 年月 不能为空
     * @param flag 当 flag:0 时，统计年月日 flag:1 统计 年月
     * @return
     */
    public List<TimeTypeEntity> countWorkDiary(Integer userId, Integer companyId, Integer departmentId,
                                               Integer flag, String time){
        return timeTypeMapper.countWorkDiary(userId,companyId,departmentId,time,flag);
    }


}
