package com.magic.ereal.business.mapper;

import com.magic.ereal.business.entity.AllConfig;
import org.apache.ibatis.annotations.Param;

/**
 * 全局配置
 * Created by Eric Xie on 2017/5/18 0018.
 */
public interface IAllConfigMapper {


    AllConfig queryConfig();


    Integer updateConfig(@Param("allConfig") AllConfig allConfig);

}
