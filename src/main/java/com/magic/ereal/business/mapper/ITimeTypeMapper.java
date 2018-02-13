package com.magic.ereal.business.mapper;

import com.magic.ereal.business.entity.TimeType;
import com.magic.ereal.business.entity.TimeTypeEntity;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 时间类型接口
 * Created by Eric Xie on 2017/4/20 0020.
 */
public interface ITimeTypeMapper {


    /**
     * 新增时间类型
     * @param timeType
     * @return
     */
    Integer addTimeType(@Param("timeType") TimeType timeType);

    /**
     *  更新时间类型名称
     * @param timeType
     * @return
     */
    Integer updateTimeType(@Param("timeType") TimeType timeType);

    /**
     *  逻辑删除 时间类型
     * @param id
     * @return
     */
    Integer delTimeType(@Param("id") Integer id);

    /**
     *  查询所有有效的 时间类型
     * @return
     */
    List<TimeType> queryAllTimeType();

    /**
     * 统计日志 工作时长等
     *  userId、companyId、departmentId 不能同时存在 只能单选
     * @param userId 用户ID
     * @param companyId 分公司ID
     * @param departmentId 部门ID
     * @param time 时间 年月日 / 年月 当 flag:0 时，统计年月日 flag:1 统计 年月
     * @param flag 当 flag:0 时，统计年月日 flag:1 统计 年月
     * @return
     */
    List<TimeTypeEntity> countWorkDiary(@Param("userId") Integer userId, @Param("companyId") Integer companyId,
                                        @Param("departmentId") Integer departmentId, @Param("time") String time,@Param("flag") Integer flag);

}
