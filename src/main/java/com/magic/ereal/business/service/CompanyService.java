package com.magic.ereal.business.service;

import com.magic.ereal.business.entity.Company;
import com.magic.ereal.business.entity.Log;
import com.magic.ereal.business.entity.User;
import com.magic.ereal.business.mapper.ICompanyMapper;
import com.magic.ereal.business.mapper.IDepartmentMapper;
import com.magic.ereal.business.mapper.ILogMapper;
import com.magic.ereal.business.mapper.IUserMapper;
import com.magic.ereal.business.util.LoginHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 公司 业务层
 * Created by Eric Xie on 2017/4/21 0021.
 */
@Service
public class CompanyService {

    @Resource
    private ICompanyMapper companyMapper;
    @Resource
    private IUserMapper userMapper;
    @Resource
    private IDepartmentMapper departmentMapper;
    @Resource
    private ILogMapper logMapper;


    /**
     * 更新分公司名称
     * @param company
     * @return
     */
    public void updateCompany(Company company){
        companyMapper.updateCompany(company);
    }

    /**
     * 根据分公司ID 查询分公司 包括 分公司下所有的部门、部门下所有的员工
     * @param companyId 分公司ID
     * @param accreditType 授权类型 1：项目授权  2：日志授权
     * @return
     */
    public Company queryCompanyById(Integer companyId, Integer accreditType, Integer userId){
        if(null == accreditType ){
            accreditType = 100;
        }
        if(null == userId ){
            userId = 0;
        }
        return  companyMapper.queryCompanyById(companyId,accreditType,userId);
    }

    /**
     *  添加公司
     * @param company
     */
    public void addCompany(Company company){
        companyMapper.addCompany(company);
    }

    /**
     * 公司列表
     * @param
     */
    public List<Company> listForWeb(){
        return companyMapper.listForWeb();
    }


    public  List<Company> queryAllCompany(){
        return companyMapper.queryAllCompany(100,0);
    }

    public  List<Company> queryBaseCompany(){
        return companyMapper.queryBaseCompany();
    }

    @Transactional
    public void delCompany(Company company,String ip,Integer userId) throws Exception {
        companyMapper.updateCompany(company);
        departmentMapper.updateDepartmentByCompany(company.getId(),0);

        // 记录日志
        Log log = new Log();
        log.setIp(ip);
        log.setUserId(userId);
        log.setType(0);
        logMapper.addLog(log);

        List<User> users = userMapper.queryUserByCompany(company.getId());
        if(null != users && users.size() > 0){
            for (User user : users) {
                LoginHelper.delObject(user.getToken());
            }
        }
    }
}
