package com.magic.ereal.business.mapper;

import com.magic.ereal.business.entity.WorkDiaryCc;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 日志被抄送 -- 接口
 * @author lzh
 * @create 2017/4/25 16:05
 */
public interface IWorkDiaryCcMapper {

    /**
     * 批量添加抄送人
     * @param list
     */
    void save(@Param("list") List<WorkDiaryCc> list);

    /**
     * 抄送人集合
     * @return
     */
    List<WorkDiaryCc> list();
}
