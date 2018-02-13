package com.magic.ereal.business.mapper;

import com.magic.ereal.business.entity.Department;
import com.magic.ereal.business.entity.ProjectAdwards;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.javassist.bytecode.stackmap.TypeData;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 部门 持久层接口
 * Created by Eric Xie on 2017/4/20 0020.
 */
public interface IDepartmentMapper {


    List<Department> queryAllDepartment();

    /**
     * 权限转移时，获取能转移的部门
     * @param companyId
     * @param projectId
     * @param isProject
     * @return
     */
    List<Department> getDepartmentByProject(@Param("companyId") Integer companyId,@Param("projectId") Integer projectId,
                                            @Param("isProject") Integer isProject);

    List<Department> queryDepartmentByCompany(@Param("companyId") Integer companyId);

    /**
     *  导出部门下 内部项目 台帐
     * @param companyId
     * @return
     */
    List<Department> queryDepartmentOfExcelProjectInterior(@Param("companyId") Integer companyId,@Param("year") Date year);


    /**
     *  新增部门
     * @param department
     * @return
     */
    Integer addDepartment(@Param("department") Department department);

    /**
     *  删除部门
     * @param id
     * @return
     */
    Integer delDepartment(@Param("id") Integer id);

    /**
     * 更新部门不为空的字段
     * @param department
     * @return
     */
    Integer updateDepartment(@Param("department") Department department);

    /**
     * 通过分公司ID 查询部门 (包括部门下所有的员工)
     * @param companyId
     * @return
     */
    List<Department> queryDepartmentByCompanyId(Map<String,Object> map);

    /**
     *  根据ID 查询部门 (包括部门下所有的员工)
     * @param id
     * @return
     */
    Department queryDepartmentById(@Param("id") Integer id,@Param("accreditType") Integer accreditType,@Param("userId") Integer userId);
    /**
     *  根据ID 查询部门
     * @param id
     * @return
     */
    Department info(@Param("id") Integer id);

    /**
     * 获取公司下所有部门
     * @param companyId 公司id
     * @param type 部门类型  0：平台部门   1：分公司部门
     * @return
     */
    List<Department> getAllDepartmentByCompanyIdForWeb(@Param("companyId") Integer companyId,
                                                       @Param("type")Integer type,
                                                       @Param("isProjectDepartmentId") Integer isProjectDepartmentId);


    /**
     * 获取所有部门
     * @return
     */
    List<Department> getAllForWeb();


    /**
     * 获取公司下所有部门（下拉列表使用）（存在A导师的部门）
     * @return
     */
    List<Department> getAllForWebGroup();

    /**
     * 根据用户ID 获取 联系人
     * @param userId 用户ID
     * @param type 0:平台  1:常规分公司
     * @return
     */
    List<Department> getContactsByUser(@Param("userId") Integer userId,@Param("type") Integer type);


    /**
     * 通过UserId 查询 当前分公司下所有的 项目团队
     * @param companyId 公司ID  如果为空  查询所有的 项目团队
     * @return
     */
    List<Department> queryDepartmentByUser(@Param("companyId") Integer companyId);

    /**
     * 通过UserId 查询 当前分公司下所有的 部门
     * @return
     */
    List<Department> queryDepartment(@Param("userId") Integer userId);


    /**
     * 统计 结项奖
     * @param companyId
     * @param startTime
     * @param endTime
     * @return
     */
    List<ProjectAdwards> statisesProjectAdwards(@Param("companyId") Integer companyId, @Param("startTime") Date startTime,
                                                @Param("endTime") Date endTime);

    Integer batchUpdateDepartment(@Param("departments") List<Department> departments);

    Integer updateDepartmentByCompany(@Param("companyId") Integer companyId,@Param("isValid") Integer isValid);
}
