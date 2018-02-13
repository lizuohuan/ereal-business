package com.magic.ereal.business.service;

import com.magic.ereal.business.entity.SystemInfo;
import com.magic.ereal.business.enums.SystemInfoEnum;
import com.magic.ereal.business.mapper.ISystemInfoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 系统消息 业务层
 * Created by Eric Xie on 2017/5/10 0010.
 */
@Service
public class SystemInfoService {

    @Resource
    private ISystemInfoMapper systemInfoMapper;


    /**
     * 获取相关类型 的 消息详情
     *  如果消息类型为 其他类型时，userId 为 null
     * @param userId 用户ID
     * @param type 类型
     * @return
     */
    public List<SystemInfo> queryInfoByType(Integer userId,Integer type,Integer pageNO,Integer pageSize){
//        if(SystemInfoEnum.OTHER_INFO.ordinal() == type){
//            userId = null;
//        }
        return systemInfoMapper.queryInfoByType(type,userId,(pageNO - 1) * pageSize,pageSize);
    }

    /**
     * 获取最新的消息列表
     * @param userId 用户ID
     * @return
     */
    public List<SystemInfo> queryNewInfoByType(Integer userId){
        return systemInfoMapper.queryNewInfoByType(userId);
    }

    /**
     *  新增系统消息
     * @param systemInfo 消息对象
     */
    public void addSystemInfo(SystemInfo systemInfo){
        systemInfoMapper.addSystemInfo(systemInfo);
    }

    /**
     * 批量新增系统消息
     * @param infoList 消息集合对象
     */
    public void addSystemInfo(List<SystemInfo> infoList){
        systemInfoMapper.batchAddSystemInfo(infoList);
    }


}
