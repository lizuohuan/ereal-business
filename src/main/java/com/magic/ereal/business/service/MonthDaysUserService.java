package com.magic.ereal.business.service;

import com.magic.ereal.business.entity.MonthDays;
import com.magic.ereal.business.entity.MonthDaysUser;
import com.magic.ereal.business.entity.PageList;
import com.magic.ereal.business.exception.InterfaceCommonException;
import com.magic.ereal.business.mapper.IMonthDaysMapper;
import com.magic.ereal.business.mapper.IMonthDaysUserMapper;
import com.magic.ereal.business.util.StatusConstant;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 员工本月的出勤情况 或其他情况 -- 业务
 * @author lzh
 * @create 2017/6/8 13:48
 */
@Service
public class MonthDaysUserService {

    @Resource
    private IMonthDaysUserMapper monthDaysUserMapper;
    @Resource
    private IMonthDaysMapper monthDaysMapper;

    /**
     * 添加
     * @param monthDaysUser
     */
    @Transactional
    public void save(MonthDaysUser monthDaysUser) throws Exception {
        /*if (monthDaysUser.getType().equals(1)) {
            throw new InterfaceCommonException(StatusConstant.ARGUMENTS_EXCEPTION,"请先配置请假日期");
        }*/
        // 获取本月 换哥喊我注释的
//        monthDaysUser.setMonthDaysId(new Date().getMonth() + 1);
        MonthDaysUser info = monthDaysUserMapper.info(monthDaysUser.getUserId(), monthDaysUser.getMonthDaysId(), monthDaysUser.getType());
        if(null != info){
            throw new InterfaceCommonException(StatusConstant.ARGUMENTS_EXCEPTION,"已经添加过出勤");
        }
        MonthDays monthDays = monthDaysMapper.queryMonthDaysById(monthDaysUser.getMonthDaysId());
        if(null == monthDays){
            throw new InterfaceCommonException(StatusConstant.ARGUMENTS_EXCEPTION,"当月出勤天数未配置");
        }
        if(monthDaysUser.getDayNum() > monthDays.getDays()){
            throw new InterfaceCommonException(StatusConstant.ARGUMENTS_EXCEPTION,"配置天数大于当月出勤天数");
        }
        monthDaysUserMapper.save(monthDaysUser);
    }


    /**
     * 更新
     * @param monthDaysUser
     */
    @Transactional
    public void update(MonthDaysUser monthDaysUser) {
        /*if (monthDaysUser.getType().equals(1)) {
            throw new InterfaceCommonException(StatusConstant.ARGUMENTS_EXCEPTION,"请先配置请假日期");
        }*/
        monthDaysUserMapper.update(monthDaysUser);
    }

    /**
     * 列表
     * @param monthDaysUser
     */
    @Transactional
    public PageList<MonthDaysUser> list(MonthDaysUser monthDaysUser, Integer pageNO, Integer pageSize,Integer source) {
        Map<String ,Object> map = new HashMap<>();
        map.put("m",monthDaysUser);
        map.put("limit",(pageNO - 1 ) * pageSize);
        map.put("limitSize", pageSize);
        map.put("source", source);
        List<MonthDaysUser> list = monthDaysUserMapper.list(map);
        Integer count = monthDaysUserMapper.countList(map);
        return new PageList<MonthDaysUser>(list,count);
    }

    /**
     * 详情
     * @param userId
     * @param monthDaysId
     * @param type
     * @return
     */
    public MonthDaysUser info(Integer userId ,Integer monthDaysId ,Integer type) {
        return monthDaysUserMapper.info(userId, monthDaysId, type);
    }


}
