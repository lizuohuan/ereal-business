package com.magic.ereal.business.mapper;

import com.magic.ereal.business.entity.SecondVeidoo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by Eric Xie on 2017/5/22 0022.
 */
public interface ISecondVeidooMapper {


    /**
     * 二维 统计
     * @param departmentId 部门ID
     * @param date 月度时间 SQL 已做日期格式化
     * @param type 统计类型 1：项目部 2：其他职能部门
     * @param method 统计公式选择
     *               考核方式：
     * 项目团队： type:1
     * 考核方式一：内部结项数/目标结项数*100%
     * 考核方式二：（（K内+K外）/（1.7*0.7））/目标结项数*100%
     *
     * 职能部门： type:2
     * 考核方式一：完成任务数（量化的指标）/目标完成数（量化的指标）*100%
     * 考核方式二：值总评价分数*0.3+总经理评价分数*0.7
     * @return 数据
     */
    Double statisticsSecondVeidoo(@Param("departmentId") Integer departmentId, @Param("date") Date date,
                                  @Param("type") Integer type,@Param("method") Integer method);

    /**
     * 更新
     * @param secondVeidoo
     * @return
     */
    Integer updateSecondVeidoo(@Param("secondVeidoo") SecondVeidoo secondVeidoo);

    /**
     * 根据类型
     * @param type
     * @return
     */
    SecondVeidoo querySecondVeidooByType(@Param("type") Integer type);

}
