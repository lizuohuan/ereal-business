package com.magic.ereal.business.mapper;

import com.magic.ereal.business.entity.ExcelOfWorkDiary;
import com.magic.ereal.business.entity.ExcelOfWorkDiarySub;
import com.magic.ereal.business.entity.WorkDiarySub;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 传递卡 详情列 持久层接口
 * Created by Eric Xie on 2017/4/21 0021.
 */
public interface IWorkDiarySubMapper {


    WorkDiarySub queryNewSub(@Param("wordDiaryId") Integer wordDiaryId);

    /**
     * 传递卡导出
     * @param companyId 公司ID
     * @param departmentId 部门ID
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     */
    List<ExcelOfWorkDiary> excelOfWorkDiary(@Param("companyId") Integer companyId,
                                            @Param("departmentId") Integer departmentId,
                                            @Param("startTime") Date startTime,
                                            @Param("endTime") Date endTime);

    /**
     *  导出 工作学习时间
     * @param map
     * @return
     */
    List<ExcelOfWorkDiarySub> countExcelOfWorkDiarySub(Map<String,Object> map);

    /**
     *  批量新增 传递卡 详情数据
     * @param workDiarySubs
     * @return
     */
    Integer batchAddWorkDiarySub(@Param("workDiarySubs") List<WorkDiarySub> workDiarySubs);
    /**
     *  新增 传递卡 详情数据
     * @param workDiarySub
     * @return
     */
    Integer addWorkDiarySub(@Param("workDiarySub") WorkDiarySub workDiarySub);

    /**
     *  通过 传递卡 查询 该传递卡下的详情数据
     * @param workDiaryId
     * @return
     */
    List<WorkDiarySub> queryWorkDiarySubByWorkDiary(@Param("workDiaryId") Integer workDiaryId);


    /**
     *  通过 传递卡 查询 该传递卡下的详情数据
     * @param workDiaryId
     * @return
     */
    List<WorkDiarySub> queryWorkDiarySubByWorkDiary2(@Param("workDiaryId") Integer workDiaryId);


    /**
     *  根据ID 批量更新
     * @param workDiarySubs
     * @return
     */
    Integer batchUpdateWorkDiarySub(@Param("workDiarySubs") List<WorkDiarySub> workDiarySubs);

    /**
     * 根据ID 删除
     * @param id
     * @return
     */
    Integer delWorkDiarySub(@Param("id") Integer id);

    /**
     * 根据id查看详情
     * @param id
     * @return
     */
    WorkDiarySub info(@Param("id") Integer id);

    /**
     *  查询 当天 开始时间 到 结束时间 是否添加过
     * @param startTime
     * @param endTime
     * @param workDiaryId
     * @return
     */
    WorkDiarySub queryByDateAndWorkDiary(@Param("startTime") Date startTime,@Param("endTime") Date endTime,
                                         @Param("workDiaryId") Integer workDiaryId);

    /**
     *  查询 工作日志记录 除 当前工作日志记录
     *   用户查询判断 当前更新的时间 是否和其他工作日志 有交集
     * @param workId 工作ID
     * @param subId 子类ID
     * @return
     */
    List<WorkDiarySub> queryWorkDiarySubByWork(@Param("workId") Integer workId,@Param("subId") Integer subId);

    /**
     * 是否此时间段已存在
     * @param startTime
     * @param endTime
     * @param userId
     * @return
     */
    List<WorkDiarySub> isHave(@Param("startTime") Date startTime,
                              @Param("endTime") Date endTime,
                              @Param("userId") Integer userId ,
                              @Param("id") Integer id,
                               @Param("workTime") Date workTime);
}
