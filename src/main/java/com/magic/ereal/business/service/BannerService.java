package com.magic.ereal.business.service;

import com.magic.ereal.business.entity.*;
import com.magic.ereal.business.enums.SystemInfoEnum;
import com.magic.ereal.business.mapper.*;
import com.magic.ereal.business.push.PushMessageUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * banner消息 -- 业务
 * @author lzh
 * @create 2017/5/24 16:47
 */
@Service
public class BannerService {

    @Resource
    private IBannerMapper bannerMapper;
    @Resource
    private IUsersStatisticsMapper usersStatisticsMapper;
    @Resource
    private IUserMapper userMapper;
    @Resource
    private ISystemInfoMapper systemInfoMapper;
    @Resource
    private ICompanyBannerMapper companyBannerMapper;


    public List<Banner> queryBannerByType(Integer type){
        List<Banner> banners = bannerMapper.queryBannerByType(type);
        return banners;
    }

    public List<Banner> queryBannerByType_(Integer type,Integer companyId){
        List<Banner> banners = bannerMapper.queryBannerByType_(type);
        List<Banner> banners1 = companyBannerMapper.queryBannerForAPP(companyId, type);
        List<Banner> data = new ArrayList<>();
        if(null != banners && banners.size() > 0){
            for (Banner banner : banners) {
                banner.setSource(0);
            }
        }
        if(null != banners1 && banners1.size() > 0){
            for (Banner banner : banners1) {
                banner.setSource(1);
            }
        }
        data.addAll(banners);
        data.addAll(banners1);
        return data;
    }

    /**
     * 添加banner
     * @param banner
     * @param ids  如果type 为 2 banner为三维数据审核banner时，三维数据ID集合 必传
     *             用于更新 三维绑定的数据
     */
    @Transactional
    public void save(Banner banner,List<Integer> ids) {
        bannerMapper.save(banner);
        List<UsersStatistics> statisticses = new ArrayList<>();
        for (Integer id : ids) {
            UsersStatistics statistics = new UsersStatistics();
            statistics.setId(id);
            if(banner.getDimensionType() == 0){
                //一维banner
                statistics.setOneBannerId(banner.getId());
                statistics.setOneStatus(2);
            }
            else if(banner.getDimensionType() == 1){
                // 二维banner
                statistics.setTwoBannerId(banner.getId());
                statistics.setTwoStatus(2);
            }
            else if(banner.getDimensionType() == 2){
                // 三维banner
                statistics.setThreeBannerId(banner.getId());
            }
            else {
                continue;
            }
            statisticses.add(statistics);
        }
        if(statisticses.size() > 0){
            usersStatisticsMapper.updateListById(statisticses);
        }
    }
    /**
     * 更新banner
     * @param banner
     */
    @Transactional
    public void update(Banner banner) throws Exception {
        bannerMapper.update(banner);
        if (null != banner.getStatus()) {
            if( 2 == banner.getStatus()){
                //如果拒绝
                Banner info = bannerMapper.info(banner.getId());
                if(null == info){
                    throw new Exception("对象不存在");
                }
                if(info.getDimensionType() == 0){
                    usersStatisticsMapper.delUsersStatistics(info.getId(),null,null);
                }
                else if(info.getDimensionType() == 1){
                    usersStatisticsMapper.delUsersStatistics(null,info.getId(),null);
                }
                // 更新字段
            }
            if(1 == banner.getStatus() ){
                Banner banner1 = bannerMapper.info(banner.getId());
                // 审核通过 推送给该公司下所有员工
                List<User> users = userMapper.queryUserByCompany(banner1.getCompanyId());
                List<SystemInfo> infos = new ArrayList<>();
                Map<String,String> params = new HashMap<>();
                params.put("type",SystemInfoEnum.STATISTICS_INFO.ordinal()+"");
                String title = banner1.getTitle();
                String content = banner1.getDimensionType() + 1 + "维数据发布";
                if(null != users){
                    for (User user : users) {
                        SystemInfo info = new SystemInfo();
                        info.setType(SystemInfoEnum.STATISTICS_INFO.ordinal());
                        info.setUserId(user.getId());
                        info.setContent(content);
                        info.setTitle(title);
                        infos.add(info);
                        PushMessageUtil.pushMessages(user,title,params);
                    }
                }
                if(infos.size() > 0){
                    systemInfoMapper.batchAddSystemInfo(infos);
                }


            }
        }
    }
    /**
     * 更新banner 所有字段
     * @param banner
     */
    public void updateAll(Banner banner) {
        bannerMapper.updateAll(banner);
    }

    /**
     * app端首页banner轮播
     * @return
     */
    public List<Banner> listForApp(Integer companyId) {
        List<Banner> data = new ArrayList<>();
        List<Banner> banners = bannerMapper.listForAppBanner(companyId);
        if(null != banners && banners.size() > 0){
            for (Banner banner : banners) {
                banner.setSource(0);
            }
        }
        List<Banner> banners1 = companyBannerMapper.queryBannerForAPP(companyId, 0);
        if(null != banners1 && banners1.size() > 0){
            for (Banner banner : banners1) {
                banner.setSource(1);
            }
        }
        data.addAll(banners);
        data.addAll(banners1);
        return data;
    }

    /**
     * web端banner列表
     * @param banner 实体 name isShow title 查询条件
     * @param pageArgs 分页实体
     * @return
     */
    public PageList<Banner> list(Banner banner, PageArgs pageArgs) {
        PageList<Banner> pageList = new PageList<>();
        Map<String ,Object> map = new HashMap<>();
        map.put("b",banner);
        int count = bannerMapper.count(map);
        pageList.setTotalSize(count);
        if (count > 0) {
            map.put("pageArgs",pageArgs);
            pageList.setList(bannerMapper.list(map));
        }
        return pageList;
    }


    /**
     * 详情
     * @param id
     * @return
     */
    public Banner info(Integer id) {
        return bannerMapper.info(id);
    }

    /**
     * 详情
     * @param id
     * @return
     */
    public Banner infoApp(Integer id,Integer source) {
        if(source == 0){
            return bannerMapper.info(id);
        }
        else if(source == 1){
            return companyBannerMapper.queryBannerByIdForAPP(id);
        }
        return bannerMapper.info(id);
    }

    /**
     * 根据三维类型 获取列表 app端
     * @return
     */
    public List<Banner> getByDimensionType(Integer dimensionType, Integer pageNO,Integer pageSize) {
        PageArgs pageArgs = new PageArgs();
        if (null != pageNO && null != pageSize) {
            pageArgs.setPage(pageNO-1);
            pageArgs.setPageSize(pageSize);
        }
        return bannerMapper.getByDimensionType(dimensionType,pageArgs);
    }

}
