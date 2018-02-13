package com.magic.ereal.business.mapper;

import com.magic.ereal.business.entity.ProjectAcceptanceRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 外部项目 结项记录 -- 接口
 * @author lzh
 * @create 2017/5/3 15:03
 */
public interface IProjectAcceptanceRecordMapper {

    /**
     * 结项记录集合
     * @param projectId 外部项目id
     * @param type 验收类型 0：破题  1：内部  2：外部
     * @return
     */
    List<ProjectAcceptanceRecord> list(@Param("projectId") Integer projectId , @Param("type") Integer type);

    /**
     * 结项内外部结项记录集合
     * @param projectId 外部项目id
     * @return
     */
    List<ProjectAcceptanceRecord> queryRecordByProject(@Param("projectId") Integer projectId);

    /**
     * 结项内外部结项记录集合
     * @param projectId 外部项目id
     * @return
     */
    List<ProjectAcceptanceRecord> queryRecordByProject2(@Param("projectId") Integer projectId);

    /**
     * 进行记录
     * @param projectAcceptanceRecord
     */
    void save(ProjectAcceptanceRecord projectAcceptanceRecord);

    /**
     * 最新一条
     * @param projectId 外部项目id
     * @param type 验收类型 0：破题  1：内部  2：外部
     * @return
     */
    ProjectAcceptanceRecord lastOne(@Param("projectId") Integer projectId , @Param("type") Integer type);

    /**
     * 批量插入记录
     * @param list
     */
    void saveList(List<ProjectAcceptanceRecord> list);

    /**
     * 获取上一次k值
     * @param projectId
     * @param type 验收类型 0：破题  1：内部  2：外部
     * @param status 13：不通过(内部验收) 2：验收失败(外部验收)
     * @return
     */
    List<ProjectAcceptanceRecord> getKList(@Param("projectId") Integer projectId ,
                                           @Param("type") Integer type,
                                           @Param("status") Integer status);

    /**
     * 获取上一次k值
     * @param projectId
     * @param type 验收类型 0：破题  1：内部  2：外部
     * @param status 13：不通过(内部验收) 2：验收失败(外部验收)
     * @return
     */
    List<ProjectAcceptanceRecord> getKListTwo(@Param("projectId") Integer projectId ,
                                           @Param("type") Integer type,
                                           @Param("status") Integer status);

    List<ProjectAcceptanceRecord> getInnerAllKList(@Param("projectIds") Integer[] projectIds,
                                              @Param("status") Integer status);

    /**
     * 获取当前记录详情
     * @param id
     * @return
     */
    ProjectAcceptanceRecord info(@Param("id")Integer id);
}
