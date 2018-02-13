package com.magic.ereal.business.mapper;

import com.magic.ereal.business.entity.WorkDiaryStatusDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 传递卡/工作日志  状态显示详情 持久层接口
 * Created by Eric Xie on 2017/4/21 0021.
 */
public interface IWorkDiaryStatusDetailMapper {


    /**
     *  新增 详情
     * @param detail
     * @return
     */
    Integer addWorkDiaryStatusDetail(@Param("detail") WorkDiaryStatusDetail detail);

    /**
     * 批量新增
     * @param details
     * @return
     */
    Integer batchAddWorkDiaryStatusDetail(@Param("details") List<WorkDiaryStatusDetail> details);


    /**
     *  根据 传递卡  查询 传递卡的状态进度详情集合
     * @param workDiaryId
     * @return
     */
    List<WorkDiaryStatusDetail> queryByWorkDiary(@Param("workDiaryId") Integer workDiaryId);

}
