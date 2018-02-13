package com.magic.ereal.business.mapper;

import com.magic.ereal.business.entity.MonthDaysLeave;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 请假情况 -- 接口
 * @author lzh
 * @create 2017/6/8 11:36
 */
public interface IMonthDaysLeaveMapper {

    /**
     * 批量添加
     * @param list
     */
    void save(List<MonthDaysLeave> list);

    /**
     * 删除
     * @param id
     */
    void delete(Integer id);


    /**
     * 列表
     * @param map
     * @return
     */
    List<MonthDaysLeave> list(Map<String, Object> map);

    /**
     * 当天是否已存在请假
     * @param date
     * @param userId
     * @return
     */
    int isHave(@Param("date") Date date ,@Param("userId") Integer userId);


    MonthDaysLeave info(@Param("id") Integer id);
}
