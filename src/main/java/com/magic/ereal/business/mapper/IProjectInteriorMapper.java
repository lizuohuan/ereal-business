package com.magic.ereal.business.mapper;

import com.magic.ereal.business.entity.ExcelProjectInterior;
import com.magic.ereal.business.entity.ProjectInterior;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 内部项目 -- 接口
 * @author lzh
 * @create 2017/4/27 17:38
 */
public interface IProjectInteriorMapper {


    /**
     * 批量新增内部项目
     * @param projectInteriors
     * @return
     */
    Integer batchAddProjectInterior(@Param("projectInteriors") List<ProjectInterior> projectInteriors);

    /**
     * 导出内部项目 台帐
     * @return
     */
    List<ExcelProjectInterior> excelProjectInterior(Map<String,Object> map);



    Integer delProjectInterior(@Param("projectId") Integer projectId);

    /**
     *  查询内部项目 基础信息
     * @param id ID
     * @return
     */
    ProjectInterior queryBaseInfo(@Param("id") Integer id);

    /**
     * 内部项目 立项
     * @param projectInterior
     */
    void save(ProjectInterior projectInterior);

    /**
     * 更新项目信息
     * @param projectInterior
     */
    void update(ProjectInterior projectInterior);

    /**
     * 更新项目全部信息 （包括为空时）
     * @param projectInterior
     */
    void updateAll(ProjectInterior projectInterior);

    /**
     * 分页查询内部项目列表
     * @param map
     * @return
     */
    List<ProjectInterior> list(Map<String ,Object> map);
 /**
     * 分页查询内部项目列表
     * @param map
     * @return
     */
    int countList(Map<String ,Object> map);

    /**
     * 项目详情
     * @param id
     * @return
     */
    ProjectInterior info(@Param("id") Integer id);

    /**
     *  通过用户ID和该用户的角色 获取 内部项目列表
     * @param userId 用户ID
     * @param roleId 角色ID
     * @param status 项目状态 0 : 审核中（绩效专员选择团队）  1：审核通过（通过后团队长分配项目组）  2 ： 审核失败
     * @return 内部项目集合
     */
    List<ProjectInterior> queryProjectInteriorByItem(Map<String,Object> map);

    /**
     * 通过ID 查询 内部项目 所有字段
     * @param id
     * @return
     */
    ProjectInterior queryProjectInteriorById(@Param("id") Integer id);

    /**
     * 传递卡 内部项目筛选
     * @param userId 用户id
     * @return
     */
    Set<ProjectInterior> getWorkDiaryProInterior(@Param("userId") Integer userId);

}
