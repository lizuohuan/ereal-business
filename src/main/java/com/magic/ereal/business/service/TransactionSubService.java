package com.magic.ereal.business.service;

import com.magic.ereal.business.entity.JobType;
import com.magic.ereal.business.entity.Project;
import com.magic.ereal.business.entity.ProjectInterior;
import com.magic.ereal.business.entity.TransactionSub;
import com.magic.ereal.business.mapper.IProjectInteriorMapper;
import com.magic.ereal.business.mapper.IProjectKMapper;
import com.magic.ereal.business.mapper.IProjectMapper;
import com.magic.ereal.business.mapper.ITransactionSubMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Eric Xie on 2017/4/27 0027.
 */
@Service
public class TransactionSubService {

    @Resource
    private ITransactionSubMapper transactionSubMapper;
    @Resource
    private IProjectMapper projectMapper;
    @Resource
    private IProjectInteriorMapper projectInteriorMapper;

    public void addTransactionSub(TransactionSub transactionSub){
        transactionSubMapper.addTransactionSub(transactionSub);
    }

    public void updateTransactionSub(TransactionSub transactionSub){
        transactionSubMapper.updateTransactionSub(transactionSub);
    }


    public List<TransactionSub> queryTransactionSubByItem(Integer isShow){
        return transactionSubMapper.queryTransactionSubByItem(isShow);
    }


    public List<TransactionSub> queryAllTransactionSub(Integer userId,Integer roleId){
        List<TransactionSub> transactionSubs = transactionSubMapper.queryAllTransactionSub(userId);
        for (TransactionSub sub : transactionSubs){
            if(3 == sub.getId()){
                // 内部项目
                Set<ProjectInterior> workDiaryProInterior = projectInteriorMapper.getWorkDiaryProInterior(userId);
                List<JobType> jobs = new ArrayList<>();
                for (ProjectInterior pro : workDiaryProInterior){
                    JobType j = new JobType();
                    j.setId(pro.getId());
                    j.setJobTypeName(pro.getShortName());
                    jobs.add(j);
                }
                sub.setJobTypes(jobs);
            }
            if(4 == sub.getId()){
                // 外部部项目
                Set<Project> workDiaryPro = projectMapper.getWorkDiaryPro(userId,roleId);
                List<JobType> jobs = new ArrayList<>();
                for (Project pro : workDiaryPro){
                    JobType j = new JobType();
                    j.setId(pro.getId());
                    j.setJobTypeName(pro.getProjectNameShort());
                    jobs.add(j);
                }
                sub.setJobTypes(jobs);
            }

        }

        return transactionSubs;
    }




}
