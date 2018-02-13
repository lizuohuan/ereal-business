package com.magic.ereal.business.mapper;

import com.magic.ereal.business.entity.KStatistics;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * 三维统计 持久层
 * Created by Eric Xie on 2017/5/18 0018.
 */
public interface IStatisticsMapper {


    /**
     * 统计首页 上月的 K临时、K常规、K内、K外 公司
     * @param date 上月日期 MYSQL 已做 日期格式化
     * @return
     */
    KStatistics statisticsHomeK(@Param("companyId") Integer companyId, @Param("startTime") Date startTime,
                                @Param("endTime") Date endTime);

    /**
     * 统计首页 上月的 K临时、K常规、K内、K外 部门
     * @param date 上月日期 MYSQL 已做 日期格式化
     * @return
     */
    KStatistics statisticsHomeKForDepartment(@Param("departmentId") Integer departmentId, @Param("startTime") Date startTime,
                                @Param("endTime") Date endTime);


}
