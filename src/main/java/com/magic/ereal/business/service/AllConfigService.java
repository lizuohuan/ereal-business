package com.magic.ereal.business.service;

import com.magic.ereal.business.cache.MemcachedUtil;
import com.magic.ereal.business.entity.AllConfig;
import com.magic.ereal.business.mapper.IAllConfigMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Eric Xie on 2017/5/18 0018.
 */
@Service
public class AllConfigService {

    @Resource
    private IAllConfigMapper allConfigMapper;

    private static final String CONFIG = "allConfig";

    /**获取 全局配置*/
    public AllConfig getConfig(){
        AllConfig config = (AllConfig) MemcachedUtil.getInstance().get(CONFIG);
        if(null == config){
            config = allConfigMapper.queryConfig();
            MemcachedUtil.getInstance().add(CONFIG,config);
        }
        return config;
    }


    public void updateAllConfig(AllConfig allConfig){
        allConfigMapper.updateConfig(allConfig);
        AllConfig config = (AllConfig) MemcachedUtil.getInstance().get(CONFIG);
        if(null == config){
            MemcachedUtil.getInstance().add(CONFIG,allConfig);
        }else{
            MemcachedUtil.getInstance().replace(CONFIG,allConfig);
        }
    }


}
