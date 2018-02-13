package com.magic.ereal.business.service;

import com.magic.ereal.business.entity.MonthDays;
import com.magic.ereal.business.entity.PageArgs;
import com.magic.ereal.business.entity.PageList;
import com.magic.ereal.business.exception.InterfaceCommonException;
import com.magic.ereal.business.mapper.IMonthDaysMapper;
import com.magic.ereal.business.util.StatusConstant;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 配置 每月 应出勤天数 -- 业务
 * @author lzh
 * @create 2017/6/8 10:53
 */
@Service
public class MonthDaysService {

    @Resource
    private IMonthDaysMapper monthDaysMapper;

    /**
     * 配置出勤天数
     * @param monthDays
     */
    @Transactional
    public void save(MonthDays monthDays) {
        if (monthDaysMapper.isHave(monthDays.getDateTime()) > 0) {
            throw new InterfaceCommonException(StatusConstant.OBJECT_EXIST,"本月已配置应出勤天数");
        }
        monthDaysMapper.save(monthDays);
    }

    /**
     * 更新
     * @param monthDays
     */
    @Transactional
    public void update(MonthDays monthDays) {
        monthDaysMapper.update(monthDays);
    }


    /**
     * 列表
     * @param monthDays
     * @param pageArgs
     * @return
     */
    public PageList<MonthDays> list(MonthDays monthDays , PageArgs pageArgs) {
        PageList<MonthDays> pageList = new PageList<>();
        Map<String ,Object> map = new HashMap<>();
        map.put("monthDays",monthDays);
        map.put("dateTime",null == monthDays.getDateTime() ? null : monthDays.getDateTime());
        int count = monthDaysMapper.listCount(map);
        if (count > 0) {
            map.put("pageArgs",pageArgs);
            pageList.setList(monthDaysMapper.list(map));
        }
        pageList.setTotalSize(count);
        return pageList;
    }



    public List<MonthDays> queryMonth(){
        return monthDaysMapper.queryMonth(new Date());
    }
}
