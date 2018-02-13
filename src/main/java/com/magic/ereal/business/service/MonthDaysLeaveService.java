package com.magic.ereal.business.service;

import com.magic.ereal.business.entity.MonthDaysLeave;
import com.magic.ereal.business.entity.MonthDaysUser;
import com.magic.ereal.business.exception.InterfaceCommonException;
import com.magic.ereal.business.mapper.IMonthDaysLeaveMapper;
import com.magic.ereal.business.mapper.IMonthDaysUserMapper;
import com.magic.ereal.business.util.DateTimeHelper;
import com.magic.ereal.business.util.StatusConstant;
import com.magic.ereal.business.util.Timestamp;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.*;

/**
 * 请假情况 -- 业务
 * @author lzh
 * @create 2017/6/8 16:07
 */
@Service
public class MonthDaysLeaveService {

    @Resource
    private IMonthDaysLeaveMapper monthDaysLeaveMapper;
    @Resource
    private IMonthDaysUserMapper monthDaysUserMapper;

    /**
     * 列表
     * @param monthDaysLeave
     * @return
     */
    public List<MonthDaysLeave> list(MonthDaysLeave monthDaysLeave){
        Map<String ,Object> map = new HashMap<>();
        map.put("m",monthDaysLeave);
        return monthDaysLeaveMapper.list(map);
    }

    /**
     * 批量添加 请假时间
     * @param startTimeL
     * @param endTimeL
     * @param userId
     * @param monthDaysId
     * @throws ParseException
     */
    @Transactional
    public void save(Long startTimeL ,Long endTimeL ,Integer userId ,Integer monthDaysId) throws ParseException {
        //请假的时间集合
        List<MonthDaysLeave> list = new ArrayList<>();
        //转成date类型
        Date startTime = Timestamp.parseDate(String.valueOf(startTimeL / 1000),"yyyy-MM-dd");
        Date endTime = Timestamp.parseDate(String.valueOf(endTimeL / 1000),"yyyy-MM-dd");
        //请假天数
        Integer days = Integer.parseInt(String.valueOf(DateTimeHelper.getDaysOfTwoDate(endTime,startTime))) + 1;

        //转成Integer类型
        Integer startTimeInt = Timestamp.timesTamp(startTime,"yyyy-MM-dd");
        Integer endTimeInt = Timestamp.timesTamp(endTime,"yyyy-MM-dd");

        for (int i = startTimeInt ; i <= endTimeInt ; i += 3600 * 24) {
            int count = monthDaysLeaveMapper.isHave(Timestamp.parseDate(String.valueOf(i) ,"yyyy-MM-dd") ,userId);
            if (count > 0 ) {
                throw new InterfaceCommonException(StatusConstant.OBJECT_EXIST,
                        Timestamp.TimeStamp2Date(String.valueOf(i) ,"yyyy-MM-dd")+"已存在请假记录，请不要重复请假");
            }
            MonthDaysLeave monthDaysLeave = new MonthDaysLeave();
            monthDaysLeave.setUserId(userId);
            monthDaysLeave.setMonthDaysId(monthDaysId);
            monthDaysLeave.setLeaveTime(Timestamp.parseDate(String.valueOf(i),"yyyy-MM-dd"));
            list.add(monthDaysLeave);
        }

        //请假详情
        MonthDaysUser monthDaysUser = monthDaysUserMapper.info(userId,monthDaysId,1);
        if (null == monthDaysUser) {
            //如果不存在 添加
            monthDaysUser = new MonthDaysUser();
            monthDaysUser.setType(1);
            monthDaysUser.setDayNum(days);
            monthDaysUser.setUserId(userId);
            monthDaysUserMapper.save(monthDaysUser);
        } else {
            //如果存在 更新
            monthDaysUser.setDayNum(monthDaysUser.getDayNum() + days);
            monthDaysUserMapper.update(monthDaysUser);
        }
        //批量添加请假时间
        monthDaysLeaveMapper.save(list);
    }

    /**
     * 删除请假记录
     * @param id
     * @param userId
     * @param monthDaysId
     */
    @Transactional
    public void delete(Integer id ,Integer userId ,Integer monthDaysId) {

        MonthDaysLeave monthDaysLeave = monthDaysLeaveMapper.info(id);
        if (null == monthDaysLeave ) {
            throw new InterfaceCommonException(StatusConstant.NO_DATA,"没有请假记录");
        }
        //请假详情
        MonthDaysUser monthDaysUser = monthDaysUserMapper.info(userId,monthDaysId,1);
        if (null == monthDaysUser) {
            throw new InterfaceCommonException(StatusConstant.NO_DATA,"没有请假记录");
        } else {
            if (monthDaysUser.getDayNum() == 0) {
                throw new InterfaceCommonException(StatusConstant.NO_DATA,"没有请假记录");
            }
            //如果存在 更新
            monthDaysUser.setDayNum(monthDaysUser.getDayNum() - 1);
            monthDaysUserMapper.update(monthDaysUser);
        }
        monthDaysLeaveMapper.delete(id);
    }


}
