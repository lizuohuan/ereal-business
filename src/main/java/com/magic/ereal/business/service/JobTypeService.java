package com.magic.ereal.business.service;

import com.magic.ereal.business.entity.JobType;
import com.magic.ereal.business.entity.PageArgs;
import com.magic.ereal.business.entity.PageList;
import com.magic.ereal.business.entity.User;
import com.magic.ereal.business.enums.TransactionType;
import com.magic.ereal.business.exception.InterfaceCommonException;
import com.magic.ereal.business.mapper.IJobTypeMapper;
import com.magic.ereal.business.util.ExcelReader;
import com.magic.ereal.business.util.StatusConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 工作类型 -- 业务
 * @author lzh
 * @create 2017/4/21 11:43
 */
@Service
public class JobTypeService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private IJobTypeMapper jobTypeMapper;


    public JobType queryJobTypeById(Integer id){
        return jobTypeMapper.queryJobTypeById(id);
    }


    public void importExcelJobType(String resourcesUrl) throws Exception {

        int HttpResult; // 服务器返回的状态
        URL url = new URL(resourcesUrl); // 创建URL
        URLConnection urlConn = url.openConnection();
        urlConn.connect();
        HttpURLConnection httpConn = (HttpURLConnection) urlConn;
        HttpResult = httpConn.getResponseCode();
        if (HttpResult != HttpURLConnection.HTTP_OK)
            throw new InterfaceCommonException(StatusConstant.Fail_CODE, "数据读取失败");
        else {
            Map<Integer, List<String>> map = ExcelReader.readExcelContent(urlConn.getInputStream());
            List<JobType> importJobType = new ArrayList<>(); // 读取出来的用户列表
            // 解析数据
            for (Integer cellNum : map.keySet()) {
                List<String> values = map.get(cellNum);
                if (null == values.get(0)) {
                    throw new InterfaceCommonException(StatusConstant.OBJECT_NOT_EXIST, "类型名称必须填写");
                }
                JobType temp = new JobType();
                temp.setJobTypeName(values.get(0));
                importJobType.add(temp);
            }

            if (importJobType.size() == 0) {
                throw new InterfaceCommonException(StatusConstant.Fail_CODE, "表格数据为空");
            }
            if(importJobType.size() > 0){
                jobTypeMapper.batchAddJobType(importJobType);
            }
        }
    }

    public void addJobType(List<JobType> jobTypes){
        jobTypeMapper.batchAddJobType(jobTypes);
    }

    /**
     * 添加工作类型
     * @param jobType
     */
    public void addJobType(JobType jobType){
        jobTypeMapper.addJobType(jobType);
    }

    /**
     * 根据ID 更新工作类型
     * @param jobType
     * @return
     */
    public void updateJobType(JobType jobType){
        jobTypeMapper.updateJobType(jobType);
    }

    /**
     * 根据事务类型查询 工作类型
     * @param transaction 事务类型
     * @param userId 当事务类型为 临时类型时，字段必传
     * @return 工作类型集合
     */
    public List<JobType> queryJobTypeByTransaction(Integer transaction, Integer userId){
        if (TransactionType.TEMPORARY.ordinal() == transaction || TransactionType.GENERAL.ordinal() == transaction) {
            if (null == userId) {
                logger.error("用户id不能为空");
                throw new InterfaceCommonException(StatusConstant.FIELD_NOT_NULL,"用户id不能为空");
            }
        }
        return jobTypeMapper.queryJobTypeByTransaction(transaction,userId);
    }

    /**
     * 根据事务类型查询 工作类型 （web）(下拉列表)
     * @param transaction 事务类型
     * @param departmentId 当事务类型为 临时/常规类型时，字段必传
     * @return 工作类型集合
     */
    public List<JobType> getJobTypeByTransactionForWeb(Integer transaction, Integer departmentId){
        return jobTypeMapper.getJobTypeByTransactionForWeb(transaction,departmentId);
    }


    /**
     * 分页查询 工作类型 （web）
     * @param transaction 事务类型
     * @param departmentId 当事务类型为 临时/常规类型时，字段必传
     * @return 工作类型集合
     */
    public PageList<JobType> list(PageArgs pageArgs, Integer transaction, Integer departmentId , Integer companyId ,String jobTypeName){
        PageList<JobType> pageList = new PageList<>();
        Map<String,Object> map = new HashMap<>();
        if (null != transaction) {
            if (TransactionType.TEMPORARY.ordinal() == transaction || TransactionType.GENERAL.ordinal() == transaction) {
                if (null == departmentId) {
                    logger.error("部门id不能为空");
                    throw new InterfaceCommonException(StatusConstant.FIELD_NOT_NULL,"部门id不能为空");
                }
            }
        }
        map.put("transaction",transaction);
        map.put("departmentId",departmentId);
        map.put("companyId",companyId);
        map.put("jobTypeName",jobTypeName);
        //获取总数
        List<JobType> totalJobType = jobTypeMapper.list(map);
        //总条数
        int count = 0;
        if (null != totalJobType) {
            count = totalJobType.size();
            map.put("pageArgs", pageArgs);
            pageList.setList(jobTypeMapper.list(map));
        }
        pageList.setTotalSize(count);
        return pageList;
    }


    public List<JobType> queryJobTypeByTransactionForAPI(Integer transactionType, Integer userId){
        return jobTypeMapper.queryJobTypeByTransaction(transactionType,userId);
    }






}
