package com.magic.ereal.business.service;

import com.magic.ereal.business.entity.PageArgs;
import com.magic.ereal.business.entity.PageList;
import com.magic.ereal.business.entity.Suggest;
import com.magic.ereal.business.mapper.ISuggestMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 意见反馈
 * Created by Eric Xie on 2017/5/10 0010.
 */
@Service
public class SuggestService {


    @Resource
    private ISuggestMapper suggestMapper;


    public void addSuggest(Suggest suggest){
        suggestMapper.addSuggest(suggest);
    }


    public PageList<Suggest> querySuggest(Integer pageNO, Integer pageSize, Date startTime, Date endTime){
        List<Suggest> suggests = suggestMapper.querySuggest(startTime, endTime, (pageNO - 1) * pageSize, pageSize);
        Integer count = suggestMapper.countSuggest(startTime, endTime);
        return new PageList<>(suggests,count);
    }


}
