package com.magic.ereal.business.mapper;

import com.magic.ereal.business.entity.ExcelOfWorkDiarySub;
import com.magic.ereal.business.entity.TimeTypeEntity;
import com.magic.ereal.business.entity.WorkDiary;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 工作日志/传递卡 持久层接口
 * Created by Eric Xie on 2017/4/20 0020.
 */
public interface IWorkDiaryMapper {


    /**
     * 通过审核人、状态查询日志
     * @param status
     * @param verifierId
     * @return
     */
    List<WorkDiary> queryWorkDiaryVerifier(@Param("status") Integer status,@Param("verifierId") Integer verifierId);

    List<WorkDiary> queryWorkDiaryByTime(@Param("departmentId") Integer departmentId,@Param("companyId") Integer companyId,@Param("time") Date time,
                                         @Param("limit") Integer limit,@Param("limitSize") Integer limitSize);


    Integer countWorkDiaryByTime(@Param("departmentId") Integer departmentId,@Param("companyId") Integer companyId,@Param("time") Date time);


    /**
     * 根据时间查询 日志
     * @param date
     * @return
     */
    WorkDiary queryWorkDiaryByDate(@Param("date") Date date,@Param("userId") Integer userId);

    /**
     *  获取用户最近 最新的一条 日志记录
     *  如没有 返回null
     * @param userId 用户ID
     * @return
     */
    WorkDiary queryNearWorkDiaryByUser(@Param("userId") Integer userId);


    /**
     * 新增工作日志/传递卡
     * 返回了 主键ID
     * @param workDiary 传递卡对象
     * @return 影响行数
     */
    Integer addWorkDiary(@Param("workDiary") WorkDiary workDiary);


    /**
     * 根据ID 更新不为空的字段 (建议不更新的字段 置 null,只设值需要更新的字段)
     * @param workDiary 待更新对象
     * @return 影响行数
     */
    Integer updateWorkDiary(@Param("workDiary") WorkDiary workDiary);

    /**
     * 根据ID 查询所有字段 包括名称等属性
     * @param id 传递卡ID
     * @return 传递卡对象
     */
    WorkDiary queryWorkDiaryById(@Param("id") Integer id);

    /**
     *  根据条件筛选 传递卡/工作日志 集合
     * @param userId 用户/创建人ID
     * @param status 传递卡/工作日志 状态
     * @param companyId 分公司ID
     * @param departmentId 部门ID
     * @param jobTypeName 工作类型名称 (模糊查询)
     * @param jobContent 工作内容 (模糊查询)
     * @param limit 分页起始
     * @param limitSize 分页截至
     * @param currentUserId 当前用户ID
     * @param  orderBy 排序方式 0：默认排序  1：总工时 降序排列 2：总工时 升序排列
     * @return WorkDiary 集合
     */
    List<WorkDiary> queryWorkDiaryByItem(@Param("userId") Integer userId,@Param("status") Integer status,@Param("companyId") Integer companyId,
                                         @Param("departmentId") Integer departmentId,@Param("jobTypeName") String jobTypeName,
                                         @Param("jobContent") String jobContent,@Param("limit") Integer limit,@Param("limitSize") Integer limitSize,
                                         @Param("currentUserId") Integer currentUserId,
                                         @Param("orderBy") Integer orderBy);
    /**
     *  根据条件筛选 传递卡/工作日志 集合
     * @param userId 用户/创建人ID
     * @param status 传递卡/工作日志 状态
     * @param companyId 分公司ID
     * @param departmentId 部门ID
     * @param jobTypeName 工作类型名称 (模糊查询)
     * @param jobContent 工作内容 (模糊查询)
     * @param limit 分页起始
     * @param limitSize 分页截至
     * @param currentUserId 当前用户ID
     * @param  orderBy 排序方式 0：默认排序  1：总工时 降序排列 2：总工时 升序排列
     * @return WorkDiary 集合
     */
    List<WorkDiary> queryWorkDiaryByItemForAPI(@Param("userId") Integer userId,@Param("status") Integer status,@Param("companyId") Integer companyId,
                                         @Param("departmentId") Integer departmentId,@Param("jobTypeName") String jobTypeName,
                                         @Param("jobContent") String jobContent,@Param("limit") Integer limit,@Param("limitSize") Integer limitSize,
                                         @Param("currentUserId") Integer currentUserId,
                                         @Param("orderBy") Integer orderBy);


    /**
     * 根据 创建人 和 工作日期 批量更新状态
     * @param workDiaries
     * @return
     */
    Integer batchUpdateWorkDiaryStatus(@Param("workDiaries") List<WorkDiary> workDiaries);

    /**
     * 根据条件筛选 传递卡/工作日志 集合 web端
     * @param map ：传递卡/工作日志 entity(
     *                  高级查询{
     *                  verifierName ：审核人名字
     *                  createTime ：创建时间
     *                  userName ：创建人名字
     *                  workTime ：工作日期
     *                  status ： 状态
     *                  } )
     *
     * @return
     */
    List<WorkDiary> listForWeb(Map<String, Object> map);

    /**
     * 条数
     * @param map
     * @return
     */
    Integer listForWebCount(Map<String, Object> map);
    /**
     * 根据 用户ID 和 时间 查询 当天提交的日志
     * @param userId
     * @param workTime
     * @return 如果为null，则用户当天没有创建 否则已经创建，只能新增其 子表数据
     */
    WorkDiary queryWorkDiaryByUser(@Param("userId") Integer userId, @Param("workTime")Date workTime);


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
                                        @Param("departmentId") Integer departmentId, @Param("time") Date time, @Param("flag") Integer flag);


    List<TimeTypeEntity> countWorkDiaryDetail(@Param("userId") Integer userId, @Param("companyId") Integer companyId,
                                              @Param("departmentId") Integer departmentId, @Param("time") Date time, @Param("flag") Integer flag);


    /**
     * 删除 工作日志
     * @param id
     * @return
     */
    Integer delWorkDiary(@Param("id") Integer id);


    /**
     *  查询用户 关联的 日志 通过抄送管理
     * @param userId 用户ID
     * @return
     */
    List<WorkDiary> queryWorkDiaryCCByUser(@Param("userId") Integer userId,@Param("limit") Integer limit,
                                           @Param("limitSize") Integer limitSize);

    /**
     * 批量更新传递卡状态
     * @param ids 要更新的传递卡id 集合 可以为空
     * @param type 1：ids不能为空 更新传回来的ids集合  2:更新我的团队 3：综合部经理更新公司全部
     * @param roleId 角色id
     * @param status 状态
     * @param departmentId 部门id
     */
    void updateListStatus(@Param("ids")Integer[] ids,
                          @Param("type")Integer type ,
                          @Param("roleId")Integer roleId ,
                          @Param("status")Integer status ,
                          @Param("departmentId") Integer departmentId);


    Integer batchUpdateWorkDiary(@Param("works") List<WorkDiary> works);


    /**
     * 判断部门下 或者公司下是否存在需要审核的传递卡
     * @param type 2:更新我的团队 3：综合部经理更新公司全部
     * @param departmentId 部门id
     * @return
     */
    List<WorkDiary> isHaveAuditWork(@Param("type")Integer type ,@Param("departmentId")Integer departmentId);


    /**
     *  批量审核   查询待审核的工作日志
     * @param userId 用户ID
     * @param roleId 角色ID
     * @param isManager 是否是经理级别  1 是经理   2是综合部经理
     * @return
     */
    List<WorkDiary> queryCheckpending(@Param("userId") Integer userId,
                                      @Param("isManager") Integer isManager,
                                      @Param("departmentId")Integer departmentId);


}
