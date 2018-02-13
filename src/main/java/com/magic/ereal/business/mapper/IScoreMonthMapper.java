package com.magic.ereal.business.mapper;

import com.magic.ereal.business.entity.ScoreMonth;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 *  月度 值总评价得分 暂定用于K常规、K临时 计算标准 持久层
 * Created by Eric Xie on 2017/5/18 0018.
 */
public interface IScoreMonthMapper {


    /**
     * 新增月度评分
     * @param scoreMonth
     * @return
     */
    Integer addScoreMonth(@Param("scoreMonth") ScoreMonth scoreMonth);

    /**
     *  批量新增月度评分
     * @param scoreMonths
     * @return
     */
    Integer batchAddScoreMonth(@Param("scoreMonths") List<ScoreMonth> scoreMonths);

    /**
     * 查询部门 月度的 K临时 K常规 评分
     *  参数必选
     * @param departmentId 部门ID
     * @param date 阅读日期  SQL 已经做日期格式化
     * @param type 查询类型 1 K临时 2 K常规
     * @return
     */
    ScoreMonth queryScoreMonthByDepartment(@Param("departmentId") Integer departmentId, @Param("date") Date date,
                                           @Param("type") Integer type);





}
