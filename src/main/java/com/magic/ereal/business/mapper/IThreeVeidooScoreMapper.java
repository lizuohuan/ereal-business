package com.magic.ereal.business.mapper;

import com.magic.ereal.business.entity.ThreeVeidooScore;
import com.magic.ereal.business.entity.ThreeVeidooSta;
import com.magic.ereal.business.entity.UsersStatisticsThree;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 第三维 评分 持久层接口
 * Created by Eric Xie on 2017/5/23 0023.
 */
public interface IThreeVeidooScoreMapper {


    /**
     *  实时统计个人在时间段内的打分数据
     *  主要用于 个人移动端数据统计
     * @param map
     * @return
     */
    List<UsersStatisticsThree> queryThreeVeidooScoreByUserMap(Map<String,Object>  map);

    /**
     * 查询 用户 月份 评分数据   两个参数必传
     * 可用于 验证用户 当月是否评分过
     * @param userId 用户ID
     * @return
     */
    List<ThreeVeidooScore> queryThreeVeidooScoreByUser(@Param("userId") Integer userId,@Param("startTime") Date startTime,
                                                       @Param("endTime")Date endTime);


    /**
     *  查询该时间段 该用户 维度是否评分
     * @param userId 用户ID
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param threeVeidooId 维度ID
     * @return
     */
    ThreeVeidooScore queryThreeVeidooScoreByUserWeb(@Param("userId") Integer userId,@Param("startTime") Date startTime,
                                                 @Param("endTime")Date endTime,@Param("threeVeidooId") Integer threeVeidooId);

    /**
     * 查询 用户 月份 评分数据   两个参数必传
     * @return
     */
    List<ThreeVeidooSta> queryThreeVeidooScoreByMap(Map<String,Object> map);



    /**
     * 查询 部门 月份 评分数据   两个参数必传
     * 可用于 验证用户 当月是否评分过
     * @param departmentId 部门
     * @return
     */
    List<ThreeVeidooScore> queryThreeVeidooScoreByDepartment(@Param("departmentId") Integer departmentId,@Param("startTime") Date startTime,
                                                             @Param("endTime")Date endTime);




    /**
     * 新增
     *  每一个用户 每一个月 只能评分一次
     * @param score
     * @return
     */
    Integer addThreeVeidooScore(@Param("score") ThreeVeidooScore score);

    /**
     * 批量新增
     * @param scores
     * @return
     */
    Integer batchAddThreeVeidooScore(@Param("scores") List<ThreeVeidooScore> scores);


    /**
     * 根据ID 更新
     * @param score
     * @return
     */
    Integer updateThreeVeidooScore(@Param("score") ThreeVeidooScore score);

    /**
     * 查询
     * @param departmentId 部门ID
     * @param date 月度时间
     * @param limit
     * @param limitSize
     * @return
     */
    List<ThreeVeidooScore> queryThreeVeidooScore(@Param("departmentId") Integer departmentId, @Param("userId") Integer userId, @Param("startTime") Date startTime,
                                  @Param("endTime") Date endTime,@Param("limit") Integer limit, @Param("limitSize") Integer limitSize);

    /**
     * 统计数量
     * @param departmentId
     * @param date
     * @return
     */
    Integer countThreeVeidooScore(@Param("departmentId") Integer departmentId, @Param("userId") Integer userId, @Param("startTime") Date startTime,
                                  @Param("endTime")Date endTime);


}
