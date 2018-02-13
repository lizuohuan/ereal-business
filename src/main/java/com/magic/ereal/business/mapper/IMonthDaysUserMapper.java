package com.magic.ereal.business.mapper;

import com.magic.ereal.business.entity.MonthDaysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 员工本月的出勤情况 或其他情况 -- 接口
 * @author lzh
 * @create 2017/6/8 11:36
 */
public interface IMonthDaysUserMapper {

    /**
     * 计算用户的出勤天数
     * @param map
     * @return
     */
    int countDaysOfUser(Map<String,Object> map);

    /**
     * 添加
     * @param monthDaysUser
     */
    void save(MonthDaysUser monthDaysUser);

    /**
     * 更新
     * @param monthDaysUser
     */
    void update(MonthDaysUser monthDaysUser);


    /**
     * 列表
     * @param map
     * @return
     */
    List<MonthDaysUser> list(Map<String ,Object> map);


    /**
     * 列表
     * @param map
     * @return
     */
    Integer countList(Map<String ,Object> map);


    /**
     * 详情
     * @param userId
     * @param monthDaysId
     * @param type
     * @return
     */
    MonthDaysUser info(@Param("userId") Integer userId ,
                       @Param("monthDaysId") Integer monthDaysId ,
                       @Param("type") Integer type);


}
