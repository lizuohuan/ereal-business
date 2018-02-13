package com.magic.ereal.business.mapper;

import com.magic.ereal.business.entity.SystemInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统消息 持久层接口
 * Created by Eric Xie on 2017/5/10 0010.
 */
public interface ISystemInfoMapper {


    /**
     *  新增 系统消息
     * @param info 消息对象
     * @return 影响行数
     */
    Integer addSystemInfo(@Param("info") SystemInfo info);

    /**
     * 批量新增 系统消息
     * @param infoList 消息集合
     * @return 影响行数
     */
    Integer batchAddSystemInfo(@Param("infoList") List<SystemInfo> infoList);

    /**
     * 获取每种类型 最新的一条数据
     * @return 消息集合
     */
    List<SystemInfo> queryNewInfoByType(Integer userId);

    /**
     *  通过类型 获取 该用户的 消息通知列表
     * @param type 类型
     * @param userId 用户ID
     * @return 消息集合
     */
    List<SystemInfo> queryInfoByType(@Param("type") Integer type,@Param("userId") Integer userId,
                                     @Param("limit") Integer limit,@Param("limitSize") Integer limitSize);

}
