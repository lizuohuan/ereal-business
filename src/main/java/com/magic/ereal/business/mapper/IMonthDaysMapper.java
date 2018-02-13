package com.magic.ereal.business.mapper;

import com.magic.ereal.business.entity.MonthDays;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 配置 每月 应出勤天数 -- 接口
 * @author lzh
 * @create 2017/6/8 10:30
 */
public interface IMonthDaysMapper {


    /**
     * 添加
     * @param monthDays
     */
    void save(MonthDays monthDays);

    /**
     * 更新
     * @param monthDays
     */
    void update(MonthDays monthDays);

    /**
     * 列表
     * @param map
     * @return
     */
    List<MonthDays> list(Map<String,Object> map);


    /**
     * 获取已经配置了的月份
     * @param date
     * @return
     */
    List<MonthDays> queryMonth(@Param("date") Date date);


    /**
     * 总条数
     * @param map
     * @return
     */
    int listCount(Map<String,Object> map);


    /**
     * 判断当月是否已配置应出勤天数
     * @param dateTime
     * @return
     */
    int isHave(@Param("dateTime") Date dateTime);

    MonthDays queryMonthDaysById(@Param("id") Integer id);

}
