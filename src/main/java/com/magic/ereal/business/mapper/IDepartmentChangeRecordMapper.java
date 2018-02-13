package com.magic.ereal.business.mapper;

import com.magic.ereal.business.entity.DepartmentChangeRecord;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * 部门变更 Mapper
 * Created by Eric Xie on 2017/8/28 0028.
 */
public interface IDepartmentChangeRecordMapper {


    /**
     * 新增部门变更记录
     * @param record
     * @return
     */
    Integer addDepartmentChangeRecord(DepartmentChangeRecord record);

    /**
     * 修改变更记录
     * @param record
     * @return
     */
    Integer updateDepartmentChangeRecord(DepartmentChangeRecord record);

    /**
     * 获取用户 指定月份的所在部门
     * @param userId
     * @param dateMonth
     * @return
     */
    DepartmentChangeRecord queryRecord(@Param("userId") Integer userId, @Param("dateMonth") Date dateMonth);


}
