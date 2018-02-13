package com.magic.ereal.business.mapper;

import com.magic.ereal.business.entity.JobType;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 工作类型 持久层
 * Created by Eric Xie on 2017/4/21 0021.
 */
public interface IJobTypeMapper {


    JobType queryJobTypeById(@Param("id") Integer id);

    /**
     * 批量新增
     * @param jobTypes
     * @return
     */
    Integer batchAddJobType(@Param("jobTypes") List<JobType> jobTypes);

    Integer addJobType(@Param("jobType") JobType jobType);

    /**
     * 根据ID 更新工作类型
     * @param jobType
     * @return
     */
    Integer updateJobType(@Param("jobType") JobType jobType);

    /**
     * 根据事务类型查询 工作类型
     * @param transaction 事务类型
     * @param userId 当事务类型为 临时类型时，字段必传
     * @return 工作类型集合
     */
    List<JobType> queryJobTypeByTransaction(@Param("transaction") Integer transaction,@Param("userId") Integer userId);


    /**
     * 根据事务类型查询 工作类型
     * @param transaction 事务类型
     * @param userId 当事务类型为 临时类型时，字段必传
     * @return 工作类型集合
     */
    List<JobType> queryJobTypeByTransactionMap(Map<String,Object> map);
    /**
     * 批量查询根据事务类型查询 工作类型
     * @param transactionIds 事务类型
     * @param userId 当事务类型为 临时类型时，字段必传
     * @return 工作类型集合
     */
    List<JobType> queryJobTypeByTransactions(@Param("transactionIds") List<Integer> transactionIds,@Param("userId") Integer userId);

    /**
     * 根据事务类型查询 工作类型(web)（下拉列表）
     * @param transaction 事务类型
     * @param departmentId 当事务类型为 临时/常规类型时，字段必传
     * @return 工作类型集合
     */
    List<JobType> getJobTypeByTransactionForWeb(@Param("transaction") Integer transaction,
                                                @Param("departmentId") Integer departmentId);

    /**
     * 根据事务类型查询 工作类型(web)
     * @param map
     * @return
     */
    List<JobType> list(Map<String ,Object> map);
}
