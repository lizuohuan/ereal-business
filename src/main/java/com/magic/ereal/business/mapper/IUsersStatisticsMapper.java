package com.magic.ereal.business.mapper;

import com.magic.ereal.business.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 用户的统计数据 -- 接口
 * @author lzh
 * @create 2017/5/31 17:45
 */
public interface IUsersStatisticsMapper {


    /**
     *  根据bannerID 删除数据  参数 只能存在一个
     * @param oneBannerId
     * @param twoBannerId
     * @param threeBannerId
     * @return
     */
    Integer delUsersStatistics(@Param("oneBannerId") Integer oneBannerId,@Param("twoBannerId") Integer twoBannerId,
                               @Param("threeBannerId") Integer threeBannerId);


    /**
     *  获取 没有审核通过的 三维数据
     * @param companyId 公司ID
     * @param veidooType 维度类型
     * @param limit
     * @param limitSize
     * @return
     */
    List<UsersStatistics> queryPendingData(@Param("companyId") Integer companyId,@Param("veidooType") Integer veidooType,
                                           @Param("limit") Integer limit,@Param("limitSize") Integer limitSize,
                                           @Param("startTime") Date startTime,@Param("endTime") Date endTime);


    /**
     *  获取 没有审核通过的 三维数据
     * @param companyId 公司ID
     * @param veidooType 维度类型
     * @return
     */
    Integer countPendingData(@Param("companyId") Integer companyId,@Param("veidooType") Integer veidooType,
                                           @Param("startTime") Date startTime,@Param("endTime") Date endTime);


    /**
     *
     * @param map
     * @return
     */
    List<UsersStatistics> list(Map<String,Object> map);

    /**
     * 发布统计数据
     * @param usersStatistics
     */
    void save(UsersStatistics usersStatistics);
    /**
     * 发布统计数据 批量
     * @param list
     */
    void saveList(List<UsersStatistics> list);

    /**
     * 更新不为空的字段
     * @param usersStatistics
     */
    void update(@Param("u") UsersStatistics usersStatistics);

    /**
     * 更新不为空的字段
     * @param usersStatistics
     */
    void updateById(@Param("u") UsersStatistics usersStatistics);

    /**
     * 更新不为空的字段
     * @param list
     */
    void updateList(List<UsersStatistics> list);

    /**
     * 更新不为空的字段
     * @param list
     */
    void updateListById(List<UsersStatistics> list);

    /**
     * 更新全部字段
     * @param usersStatistics
     */
    void updateAll(UsersStatistics usersStatistics);

    /**
     * 获取详情
     * @param id
     * @return
     */
    UsersStatistics info(@Param("id") Integer id );

    /**
     * 获取三维统计
     * @param userId 用户id
     * @param departmentId 部门id
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param type 类型 1:员工  2:部门
     * @return
     */
    UsersStatistics getStatistics(@Param("userId") Integer userId ,
                                  @Param("departmentId") Integer departmentId ,
                                  @Param("startTime") Date startTime ,
                                  @Param("endTime") Date endTime ,
                                  @Param("type") Integer type );
    /**
     * 获取三维统计
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     */
    List<UsersStatistics> getStatisticsList(@Param("startTime") Date startTime,
                                            @Param("endTime") Date endTime ,
                                            @Param("companyId") Integer companyId,
                                            @Param("type") Integer type ,
                                            @Param("departmentId") Integer departmentId );
    /**
     * 获取公司所有人的数据三维统计
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     */
    List<UsersStatistics> getStatisticsCompanyList(@Param("startTime") Date startTime,
                                            @Param("endTime") Date endTime ,
                                            @Param("companyId") Integer companyId);





    /**
     * 统计月度 优秀团队奖
     * @param startTime
     * @param endTime
     * @return
     */
    List<GoodTeam> statisticsGoodTeam(@Param("startTime") Date startTime, @Param("endTime") Date endTime);

    /**
     * 获取部门所有人的数据三维统计
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     */
    List<UsersStatistics> getStatisticsDepartmentList(@Param("startTime") Date startTime,
                                            @Param("endTime") Date endTime ,
                                            @Param("departmentId") Integer departmentId);

    /**
     * 数据公示 三维绩效考核得分 统计
     * @param startTime
     * @param endTime
     * @param companyId
     * @return
     */
    List<UsersStatistics> getCompanySta(@Param("startTime") Date startTime,
                                        @Param("endTime") Date endTime,
                                        @Param("companyId") Integer companyId);



    /**
     * 数据公示 三维绩效考核得分 统计
     * @param startTime
     * @param endTime
     * @param companyId
     * @return
     */
    List<UsersStatistics> getCompanyStaOfUser(@Param("startTime") Date startTime,
                                        @Param("endTime") Date endTime,
                                        @Param("companyId") Integer companyId);




    /**
     * 获取团队文化工程得分
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param companyId 公司ID
     * @return
     */
    List<UserK> queryTeamKCulture(@Param("startTime") Date startTime,
                                  @Param("endTime") Date endTime,
                                  @Param("companyId") Integer companyId,
                                  @Param("departmentId") Integer departmentId,
                                  @Param("ccId") Integer ccId,
                                  @Param("userId") Integer userId);

    /**
     * 获取团队文化工程完成率
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param companyId 公司ID
     * @return
     */
    List<UserK> queryteamKCultureFinishRate(@Param("startTime") Date startTime,
                                            @Param("endTime") Date endTime,
                                            @Param("companyId") Integer companyId,
                                            @Param("departmentId") Integer departmentId);



    /**
     * 获取个人文化工程得分
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param companyId 公司ID
     * @param departmentId 部门ID
     * @param userId 员工ID
     * @return
     */
    List<UserK> querypersonKCulture(@Param("startTime") Date startTime,
                                  @Param("endTime") Date endTime,
                                  @Param("companyId") Integer companyId,
                                  @Param("departmentId") Integer departmentId,
                                  @Param("userId")Integer userId);




    /**
     * 获取 团队K总完成率
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param companyId 公司ID
     * @return
     */
    List<UserK> queryTeamKFinishRate(@Param("startTime") Date startTime,
                                            @Param("endTime") Date endTime,
                                            @Param("companyId") Integer companyId,
                                            @Param("departmentId") Integer departmentId);



    /**
     * 获取 团队结项完成率
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param companyId 公司ID
     * @return
     */
    List<UserK> queryteamPostProjectFinishRate(@Param("startTime") Date startTime,
                                            @Param("endTime") Date endTime,
                                            @Param("companyId") Integer companyId,
                                            @Param("departmentId") Integer departmentId);



    /**
     * 移动端 统计 三维绩效奖(团队)
     * @param userId 用户ID
     * @return
     */
    List<Awards> goodTeamAwards(@Param("userId") Integer userId);






    /**
     * 三维绩效总汇表
     * @param startTime
     * @param endTime
     * @param companyId
     * @return
     */
    List<TotalAchievements> getTotalAchievements(@Param("startTime") Date startTime,
                                                 @Param("endTime") Date endTime,
                                                 @Param("companyId") Integer companyId);




    /**
     * 三维绩效总汇表 之 一维
     * @param startTime
     * @param endTime
     * @param companyId
     * @return
     */
    List<TotalAchievements> getTotalAchievementsOfOne(@Param("startTime") Date startTime,
                                                 @Param("endTime") Date endTime,
                                                 @Param("companyId") Integer companyId);

    /**
     * 三维绩效总汇表 之 二维
     * @param startTime
     * @param endTime
     * @param companyId
     * @return
     */
    List<TotalAchievements> getTotalAchievementsOfTwo(@Param("startTime") Date startTime,
                                                 @Param("endTime") Date endTime,
                                                 @Param("companyId") Integer companyId);


    /**
     * 后台 工时统计
     * @param companyId 分公司ID
     * @param departmentId 部门ID
     * @param type 0:个人工作时间统计  1:个人学习时间统计 2:个人运动时间统计  3:个人工作学习总时间
     */
    List<UserK> statisticsWorkTime(@Param("companyId") Integer companyId,@Param("departmentId") Integer departmentId,
                                   @Param("type") Integer type,
                                   @Param("startTime") Date startTime,@Param("endTime") Date endTime);

    // *************************************************************************************//
    // ***************************      修正后       ***************************************//
    // *************************************************************************************//
    // *************************************************************************************//

    /**
     *  修正后的三维统计 移动端接口
     *  公司ID 和 部门ID 互斥
     * @param startTime
     * @param endTime
     * @param companyId
     * @param departmentId
     * @return
     */
    VeidooStatistics statisticsVeidooForAPI(@Param("startTime") Date startTime,@Param("endTime") Date  endTime,
                                           @Param("companyId") Integer companyId,
                                           @Param("departmentId") Integer departmentId);


    /**
     * 统计该分公司下 部门 三维排名集合
     * @param startTime
     * @param endTime
     * @param companyId
     * @return
     */
    List<VeidooDepartment> statisticsVeidooForAPIRankingForAPI(@Param("startTime") Date startTime,@Param("endTime") Date  endTime,
                                                               @Param("companyId") Integer companyId);



    /**
     *  修正后的三维统计 移动端接口
     *  公司ID 和 部门ID 互斥
     * @param startTime
     * @param endTime
     * @param companyId
     * @param departmentId
     * @return
     */
    VeidooStatistics statisticsVeidooByDepartmentForAPI(@Param("startTime") Date startTime,@Param("endTime") Date endTime,
                                            @Param("departmentId") Integer departmentId);




    /**
     *  修正后的三维统计 移动端接口
     *  统计  1、2、3维排名 数据
     * @param startTime
     * @param endTime
     * @param departmentId
     * @return
     */
    List<VeidooStatistics> statisticsVeidooRankingForAPI(@Param("startTime") Date startTime,@Param("endTime") Date endTime,
                                            @Param("departmentId") Integer departmentId);


    /**
     *  修正后的第三维统计 移动端接口
     *  统计  第三维3维排名 数据
     * @param startTime
     * @param endTime
     * @param companyId
     * @return
     */
    List<VeidooStatistics> statisticsVeidooRankingForAPIByCompanyId(@Param("startTime") Date startTime,@Param("endTime") Date endTime,
                                            @Param("companyId") Integer companyId);


    /**
     *  修正后的三维统计 移动端接口
     *  个人统计
     * @param startTime
     * @param endTime
     * @return
     */
    VeidooStatistics statisticsVeidooByUserForAPI(@Param("lastStartTime") Date lastStartTime,@Param("lastEndTime") Date lastEndTime,
                                                  @Param("startTime") Date startTime,@Param("endTime") Date endTime,
                                                        @Param("userId") Integer userId);


    /**
     *  修正后的三维统计 移动端接口
     *  个人K可比排名
     * @param startTime
     * @param endTime
     * @return
     */
    List<User> statisticsVeidooKKBRanking(@Param("startTime") Date startTime,@Param("endTime") Date endTime,
                                                        @Param("companyId") Integer companyId);





}
