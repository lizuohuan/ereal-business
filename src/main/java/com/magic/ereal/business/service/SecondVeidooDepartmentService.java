package com.magic.ereal.business.service;

import com.magic.ereal.business.entity.SecondVeidooDepartment;
import com.magic.ereal.business.mapper.ISecondVeidooDepartmentMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Eric Xie on 2017/7/19 0019.
 */
@Service
public class SecondVeidooDepartmentService {

    @Resource
    private ISecondVeidooDepartmentMapper secondVeidooDepartmentMapper;


    public void addSecondVeidooDepartment(SecondVeidooDepartment secondVeidooDepartment){
        SecondVeidooDepartment s = secondVeidooDepartmentMapper.queryVeidooMethodByTime(null, secondVeidooDepartment.getDepartmentId());
        if(null != s){
            secondVeidooDepartment.setId(s.getId());
            secondVeidooDepartmentMapper.updateSecondVeidooDepartment(secondVeidooDepartment);
        }else{
            secondVeidooDepartmentMapper.addSecondVeidooDepartment(secondVeidooDepartment);
        }
    }

    public List<SecondVeidooDepartment> queryDepartmentMethod(Integer companyId){
        return secondVeidooDepartmentMapper.queryVeidooMethod(null,null,companyId);
    }



}
