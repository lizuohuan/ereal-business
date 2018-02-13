package com.magic.ereal.business.service;

import com.magic.ereal.business.entity.Log;
import com.magic.ereal.business.entity.PageList;
import com.magic.ereal.business.mapper.ILogMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by Eric Xie on 2017/7/5 0005.
 */
@Service
public class LogService {

    @Resource
    private ILogMapper logMapper;


    public PageList<Log> queryLogByItems(Integer pageNO, Integer pageSize, Date startTime,Date endTime){
        List<Log> logs = logMapper.queryLog(startTime, endTime, (pageNO - 1) * pageSize, pageSize);
        Integer count = logMapper.countLog(startTime, endTime);
        return new PageList<>(logs,count);
    }

}
