package com.magic.ereal.business.mapper;

import com.magic.ereal.business.entity.GoodTeam;
import com.magic.ereal.business.entity.UsersStatisticsThree;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 发布后第三维数据 -- 接口
 * @author lzh
 * @create 2017/5/31 17:45
 */
public interface IUsersStatisticsThreeMapper {

    /**
     * 添加
     * @param usersStatisticsThree
     */
    void save(UsersStatisticsThree usersStatisticsThree);

    /**
     * 批量添加
     * @param list
     */
    void saveList(List<UsersStatisticsThree> list);

    /**
     * 根据记录统计数据的id 删除
     * @param userStatisticsId
     */
    void deleteByUserStatisticsId(@Param("userStatisticsId") Integer userStatisticsId);

    /**
     * 根据记录统计数据的id 批量删除
     * @param userStatisticsIds
     */
    void deleteByUserStatisticsIdList(@Param("userStatisticsIds") List<Integer> userStatisticsIds);


    /**
     * 根据id 删除
     * @param id
     */
    void delete(@Param("id") Integer id);

    /**
     * 根据记录统计数据的id 查询列表
     * @param userStatisticsId
     * @return
     */
    List<UsersStatisticsThree> list(@Param("userStatisticsId") Integer userStatisticsId);

    /**
     * 根据记录统计数据的id 查询列表
     * @return
     */
    List<UsersStatisticsThree> listForAPI(Map<String,Object> map);



}
