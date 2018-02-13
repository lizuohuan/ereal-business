package com.magic.ereal.business.mapper;

import com.magic.ereal.business.entity.ThreeVeidoo;
import com.magic.ereal.business.entity.ThreeVeidooTemp;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 第三维统计
 * Created by Eric Xie on 2017/5/22 0022.
 */
public interface IThreeVeidooMapper {

    /**
     * 添加第三维配置
     * @param threeVeidoo
     * @return
     */
    Integer addThreeVeidoo(@Param("threeVeidoo") ThreeVeidoo threeVeidoo);

    /**
     * 修改第三维配置
     * @param threeVeidoo
     * @return
     */
    Integer updateThreeVeidoo(@Param("threeVeidoo") ThreeVeidoo threeVeidoo);

    /**
     * 查询第三维配置
     * @return
     */
    List<ThreeVeidoo> queryAllThreeVeidoo();

    /**
     * 排除一和二配置
     * @return
     */
    List<ThreeVeidoo> queryExcludeOneOrTwo();



    /**
     *  第三维统计
     * @param startTime
     * @param endTime
     * @param companyId
     * @return
     */
    List<ThreeVeidooTemp> statisticsThreeVeidoo(@Param("startTime") Date startTime,
                                                @Param("endTime") Date endTime,
                                                @Param("companyId") Integer companyId,
                                                @Param("limit") Integer limit,
                                                @Param("limitSize") Integer limitSize);






    /**
     *  个人 第三维统计
     * @param startTime
     * @param endTime
     * @return
     */
    List<ThreeVeidooTemp> statisticsThreeVeidooForUser(@Param("startTime") Date startTime,
                                                @Param("endTime") Date endTime,
                                                 @Param("lastStartTime") Date lastStartTime,
                                                @Param("lastEndTime") Date lastEndTime,
                                                @Param("userId") Integer userId,
                                                 @Param("departmentId") Integer departmentId,
                                                       @Param("companyId") Integer companyId,
                                                       @Param("ccId") Integer ccId);







    List<Object> countStatisticsThreeVeidoo(
                                       @Param("companyId") Integer companyId ,
                                       @Param("startTime") Date startTime,
                                       @Param("endTime") Date endTime);







    /**
     * 统计个人个人 或者 团队 工作学习 日均值
     *  用户、部门、公司ID 只能传其中一个
     *
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param userId 用户ID  统计用户
     * @param departmentId 部门ID 统计部门下
     * @param  companyId 公司ID 统计整个公司
     * @return
     */
    double statisticsPerson(@Param("startTime") Date startTime,@Param("endTime") Date endTime,
                            @Param("userId") Integer userId,@Param("departmentId") Integer departmentId,
                            @Param("companyId") Integer companyId);





    double statisticsYd(@Param("startTime") Date startTime,@Param("endTime") Date endTime,
                        @Param("userId") Integer userId,@Param("departmentId") Integer departmentId);
}
