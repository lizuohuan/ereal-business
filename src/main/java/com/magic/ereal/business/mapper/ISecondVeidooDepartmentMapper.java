package com.magic.ereal.business.mapper;

import com.magic.ereal.business.entity.SecondVeidooDepartment;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by Eric Xie on 2017/7/19 0019.
 */
public interface ISecondVeidooDepartmentMapper {


    /**
     * 判断查询 该公司下的职能部门 是否全部配置了计算方式
     * @param companyId
     * @return 0:否  1:是
     */
    Integer countDepartmentMethod(@Param("companyId") Integer companyId);

    Integer addSecondVeidooDepartment(SecondVeidooDepartment secondVeidooDepartment);


    Integer updateSecondVeidooDepartment(@Param("secondVeidooDepartment") SecondVeidooDepartment secondVeidooDepartment);


    /**
     * 查询 部门第二维 最新的一种计算方式
     * @param time 如果不为空 则在 截至此时间 内最新的一种计算方式
     * @param departmentId 部门ID 可为空
     * @return
     */
    List<SecondVeidooDepartment> queryVeidooMethod(@Param("time") Date time, @Param("departmentId") Integer departmentId, @Param("companyId") Integer companyId);


    SecondVeidooDepartment queryVeidooMethodByTime(@Param("time") Date time,@Param("departmentId") Integer departmentId);

}
