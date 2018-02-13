package com.magic.ereal.business.mapper;

import com.magic.ereal.business.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * K 值统计 -- 接口
 * @author lzh
 * @create 2017/5/17 11:17
 */
public interface IKStatisticsMapper {

    /**
     * 查询时间段的k值 个人
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param userId 用户id
     * @param timeType 查询时间阶段类型 1:周 2：月 3：年
     * @return
     */
    KPersonageStatistics getByTimePersonage(@Param("startTime") Date startTime ,
                                         @Param("endTime") Date endTime ,
                                         @Param("userId") Integer userId ,
                                         @Param("timeType") Integer timeType );


    /**
     * 查询时间段的k值 个人（团队） 统计图（柱状）
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param departmentId 团队id
     * @return
     */
    List<KPersonageStatistics> getByTimePersonageMap(@Param("startTime") Date startTime ,
                                            @Param("endTime") Date endTime ,
                                            @Param("departmentId") Integer departmentId);



    /*****************个人统计 月 年 （统计图）*****************/

    /**
     * 统计外部项目的k值 外部结项 内部结项
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param userId 用户id
     * @return
     */
    List<ProjectK> getPersonageProjectK(@Param("startTime") Date startTime ,
                                        @Param("endTime") Date endTime ,
                                        @Param("userId") Integer userId);

    /**
     * 统计外部项目的k值 周验收
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param userId 用户id
     * @return
     */
    List<ProjectWeekKAllocation> getPersonageProjectWeek(@Param("startTime") Date startTime ,
                                                         @Param("endTime") Date endTime ,
                                                         @Param("userId") Integer userId) ;
    /**
     * 统计内部项目的k值 周验收
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param userId 用户id
     * @return
     */
    List<ProjectInteriorWeekKAllocation> getPersonageProjectInWeek(@Param("startTime") Date startTime ,
                                                                   @Param("endTime") Date endTime ,
                                                                   @Param("userId") Integer userId) ;

    /**
     * 统计外部项目的k值 外部结项 内部结项（12个月）
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param userId 用户id
     * @return
     */
    List<KStatisticsPMonth> getPersonageProjectKMonth(@Param("startTime") Date startTime ,
                                                      @Param("endTime") Date endTime ,
                                                      @Param("userId") Integer userId) ;

    /**
     * 统计外部项目的k值 周验收（12个月）
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param userId 用户id
     * @return
     */
    List<KStatisticsPMonth> getPersonageProjectWeekMonth(@Param("startTime") Date startTime ,
                                                      @Param("endTime") Date endTime ,
                                                      @Param("userId") Integer userId) ;
    /**
     * 统计内部项目的k值 周验收（12个月）
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param userId 用户id
     * @return
     */
    List<KStatisticsPMonth> getPersonageProjectInWeekMonth(@Param("startTime") Date startTime ,
                                                      @Param("endTime") Date endTime ,
                                                      @Param("userId") Integer userId) ;



    /*****************部门/公司统计 月 年 （统计图）*****************/
    /**
     * 查询时间段的k值 部门/公司 数据
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param departmentId 团队id
     * @param companyId 公司id
     * @param type  1:部门 2：公司
     * @param timeType 查询时间阶段类型 1:周 2：月 3：年
     * @return
     */
    KDCStatistics getByTimeDeCom(@Param("startTime") Date startTime ,
                                @Param("endTime") Date endTime ,
                                @Param("departmentId") Integer departmentId ,
                                @Param("companyId") Integer companyId ,
                                @Param("type") Integer type ,
                                @Param("timeType") Integer timeType );
    /**
     * 查询时间段的k值 部门/公司 数据 test
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param departmentId 团队id
     * @param companyId 公司id
     * @param type  1:部门 2：公司
     * @param timeType 查询时间阶段类型 1:周 2：月 3：年
     * @return
     */
    KDCStatistics test(@Param("startTime") Date startTime ,
                                 @Param("endTime") Date endTime ,
                                 @Param("departmentId") Integer departmentId ,
                                 @Param("companyId") Integer companyId ,
                                 @Param("type") Integer type ,
                                 @Param("timeType") Integer timeType );

    /**
     * 团队部门周统计 柱状图
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param departmentId 部门id
     * @return
     */
    List<KDCStatistics> getByTimeWeekDepartmentMap(@Param("startTime") Date startTime ,
                                      @Param("endTime") Date endTime ,
                                      @Param("departmentId") Integer departmentId);

    /**
     * 公司周统计 柱状图
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param companyId 公司id
     * @return
     */
    List<KDCStatistics> getByTimeWeekCompanyMap(@Param("startTime") Date startTime ,
                                         @Param("endTime") Date endTime ,
                                         @Param("companyId") Integer companyId);


    /**
     * 获取外部项目周验收 折线图使用 部门公司
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param departmentId 团队id
     * @param companyId 公司id
     * @param type  1:部门 2：公司
     * @param timeType 查询时间阶段类型 2：月 3：年
     * @return
     */
    List<KDCStatisticsMap> getByTimeProjectWeekKMap(@Param("startTime") Date startTime ,
                                                    @Param("endTime") Date endTime ,
                                                    @Param("departmentId") Integer departmentId ,
                                                    @Param("companyId") Integer companyId ,
                                                    @Param("type") Integer type ,
                                                    @Param("timeType") Integer timeType );


    /**
     * 获取外部项目结项k值验收 折线图使用 部门公司
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param departmentId 团队id
     * @param companyId 公司id
     * @param type  1:部门 2：公司
     * @param timeType 查询时间阶段类型 2：月 3：年
     * @return
     */
    List<KDCStatisticsMap> getByTimeProjectKMap(@Param("startTime") Date startTime ,
                                                @Param("endTime") Date endTime ,
                                                @Param("departmentId") Integer departmentId ,
                                                @Param("companyId") Integer companyId ,
                                                @Param("type") Integer type ,
                                                @Param("timeType") Integer timeType );



    /**
     * 获取外部项目结项k值验收 折线图使用 部门公司
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param departmentId 团队id
     * @param companyId 公司id
     * @param type  1:部门 2：公司
     * @param timeType 查询时间阶段类型 2：月 3：年
     * @return
     */
    List<KDCStatisticsMap> getByTimeProjectKMapForAPI(@Param("startTime") Date startTime ,
                                                @Param("endTime") Date endTime ,
                                                @Param("departmentId") Integer departmentId ,
                                                @Param("companyId") Integer companyId ,
                                                @Param("type") Integer type ,
                                                @Param("timeType") Integer timeType );


    /**
     * 获取内部项目周验收 折线图使用 部门公司
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param departmentId 团队id
     * @param companyId 公司id
     * @param type  1:部门 2：公司
     * @param timeType 查询时间阶段类型 2：月 3：年
     * @return
     */
    List<KDCStatisticsMap> getByTimeProjectInMap(@Param("startTime") Date startTime ,
                                                 @Param("endTime") Date endTime ,
                                                 @Param("departmentId") Integer departmentId ,
                                                 @Param("companyId") Integer companyId ,
                                                 @Param("type") Integer type ,
                                                 @Param("timeType") Integer timeType );



    /**
     * 一维统计 web
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param userId 用户id
     * @param departmentId 团队id
     * @param companyId 公司id
     * @param type 查询类型 0 :个人 1:部门 2：公司
     * @param groupType 分组类型  0 :个人 1:部门 2：公司
     * @return
     */
    List<OneStatistics> oneDimensional(@Param("startTime") Date startTime ,
                                 @Param("endTime") Date endTime ,
                                 @Param("userId") Integer userId ,
                                 @Param("departmentId") Integer departmentId ,
                                 @Param("companyId") Integer companyId ,
                                 @Param("type") Integer type ,
                                 @Param("groupType") Integer groupType,
                                 @Param("pageArgs") PageArgs pageArgs);

    /**
     * 一维统计 web
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param userId 用户id
     * @param departmentId 团队id
     * @param companyId 公司id
     * @param type 查询类型 0 :个人 1:部门 2：公司
     * @param groupType 分组类型  0 :个人 1:部门 2：公司
     * @return
     */
    List<OneStatistics> homeStatistics(@Param("startTime") Date startTime ,
                                 @Param("endTime") Date endTime ,
                                 @Param("userId") Integer userId ,
                                 @Param("departmentId") Integer departmentId ,
                                 @Param("companyId") Integer companyId ,
                                 @Param("type") Integer type ,
                                 @Param("groupType") Integer groupType,
                                 @Param("pageArgs") PageArgs pageArgs);

    /**
     * 一维统计总条数 web
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param userId 用户id
     * @param departmentId 团队id
     * @param companyId 公司id
     * @param type 查询类型 0 :个人 1:部门 2：公司
     * @param groupType 分组类型  0 :个人 1:部门 2：公司
     * @return
     */
    List<Object> oneDimensionalCount(@Param("startTime") Date startTime ,
                                 @Param("endTime") Date endTime ,
                                 @Param("userId") Integer userId ,
                                 @Param("departmentId") Integer departmentId ,
                                 @Param("companyId") Integer companyId ,
                                 @Param("type") Integer type ,
                                 @Param("groupType") Integer groupType);

    /**
     * 个人K可比 统计
     * @return
     */
    List<UserK> personKk(@Param("startTime") Date startTime ,
                         @Param("endTime") Date endTime ,@Param("userId") Integer userId ,
                         @Param("departmentId") Integer departmentId ,
                         @Param("companyId") Integer companyId);




}
