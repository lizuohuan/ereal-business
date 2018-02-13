package com.magic.ereal.business.mapper;

import com.magic.ereal.business.entity.Banner;
import com.magic.ereal.business.entity.PageArgs;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * banner消息 -- 接口
 * @author lzh
 * @create 2017/5/24 16:04
 */
public interface IBannerMapper {

    /**
     *  根据类型获取 Banner
     * @param type 类型
     * @return
     */
    List<Banner> queryBannerByType(@Param("type") Integer type);
  /**
     *  根据类型获取 Banner
     * @param type 类型
     * @return
     */
    List<Banner> queryBannerByType_(@Param("type") Integer type);

    /**
     * 添加banner
     * @param banner
     */
    void save(Banner banner);

    /**
     * 更新banner
     * @param banner
     */
    void update(Banner banner);

    /**
     * 更新banner 所有字段
     * @param banner
     */
    void updateAll(Banner banner);

    /**
     * web端banner列表
     * @param map
     * @return
     */
    List<Banner> list(Map<String ,Object> map);
    /**
     * web端banner 总条数
     * @param map
     * @return
     */
    int count(Map<String ,Object> map);

    /**
     * app端首页banner轮播
     * @return
     */
    List<Banner> listForApp(@Param("companyId") Integer companyId);

 /**
     * app端首页banner轮播
     * @return
     */
    List<Banner> listForAppBanner(@Param("companyId") Integer companyId);

    /**
     * 详情
     * @param id
     * @return
     */
    Banner info(@Param("id") Integer id);


    /**
     * 根据三维类型 获取列表 app端
     * @param dimensionType
     * @param pageArgs 分页
     * @return
     */
    List<Banner> getByDimensionType(@Param("dimensionType") Integer dimensionType,@Param("pageArgs") PageArgs pageArgs);

}
