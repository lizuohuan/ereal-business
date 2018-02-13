package com.magic.ereal.business.service;

import com.magic.ereal.business.entity.Department;
import com.magic.ereal.business.entity.Log;
import com.magic.ereal.business.entity.ProjectAdwards;
import com.magic.ereal.business.entity.User;
import com.magic.ereal.business.mapper.IDepartmentMapper;
import com.magic.ereal.business.mapper.ILogMapper;
import com.magic.ereal.business.mapper.IUserMapper;
import com.magic.ereal.business.util.DateTimeHelper;
import com.magic.ereal.business.util.LoginHelper;
import freemarker.template.Template;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.annotation.Resource;
import java.util.*;

/**
 * 部门 -- 业务
 * @author lzh
 * @create 2017/4/20 17:58
 */
@Service
public class DepartmentService {


    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private IDepartmentMapper departmentMapper;
    @Resource
    private IUserMapper userMapper;
    @Resource
    private ILogMapper logMapper;


    public List<Department> queryAllDepartment(){
        return departmentMapper.queryAllDepartment();
    }


    public void test(String templatePath) throws Exception{

    }

    /**
     * 导出内部台帐 数据
     * @param companyId
     * @return
     */
    public List<Department> queryDepartmentOfExcelProjectInterior(Integer companyId ,Date year){
        return departmentMapper.queryDepartmentOfExcelProjectInterior(companyId,year);
    }

    /**
     * 通过UserId 查询 当前分公司下所有的 项目团队
     * @param companyId Y用户ID  如果为空  查询所有的 项目团队
     * @return
     */
    public List<Department> queryDepartmentByUser(Integer companyId){
        return departmentMapper.queryDepartmentByUser(companyId);
    }

    /**
     * 通过UserId 查询 当前分公司下所有的 部门
     * @param userId
     * @return
     */
    public List<Department> queryDepartment(Integer userId){
        return departmentMapper.queryDepartment(userId);
    }

    public List<Department> getContactsByUser(Integer type,Integer userId){
        return  departmentMapper.getContactsByUser(userId,type);
    }

    /**
     * 通过分公司ID 获取分公司下所有的部门 以及 部门下的员工
     * @param companyId
     * @return
     */
    public List<Department> queryDepartmentByCompanyId(Integer companyId){
        Map<String,Object> map = new HashMap<>();
        map.put("companyId",companyId);
        return departmentMapper.queryDepartmentByCompanyId(map);
    }

    /**
     * 获取公司下所有部门
     * @param companyId 公司id
     * @param type 部门类型  0：平台部门   1：分公司部门
     * @param isProjectDepartmentId 是否查询项目部门  0：职能部门  1：项目部门
     * @return
     */
    public List<Department> getAllDepartmentByCompanyIdForWeb(Integer companyId, Integer type,
                                                              Integer isProjectDepartmentId){
        try {
            return departmentMapper.getAllDepartmentByCompanyIdForWeb(companyId, type,isProjectDepartmentId);
        } catch (Exception e) {
            logger.error("服务器超时，获取部门列表失败",e);
            return new ArrayList<>();
        }
    }

    /**
     *  新增部门
     * @param department
     * @return
     */
    public void addDepartment(Department department){
        try {
            departmentMapper.addDepartment(department);
        } catch (Exception e) {
            logger.error("服务器超时，添加失败");
        }
    }

    /**
     * 更新部门
     * @param department
     * @return
     */
    public void updateDepartment(Department department){
        try {
            departmentMapper.updateDepartment(department);
        } catch (Exception e) {
            logger.error("服务器超时，更新失败");
        }
    }


    public List<Department> getDepartmentByProject(Integer companyId,Integer projectId,Integer isProject){
        return departmentMapper.getDepartmentByProject(companyId,projectId,isProject);
    }

  /**
     * 更新部门
     * @param department
     * @return
     */
    @Transactional
    public void delDepartment(Department department,Integer userId,String ip) throws Exception{
        try {
            departmentMapper.updateDepartment(department);
            //记录日志
            Log log = new Log();
            log.setUserId(userId);
            log.setType(1);
            log.setIp(ip);
            logMapper.addLog(log);
            // 将所有在改部门下的用户 弹下线
            List<User> users = userMapper.queryUserByDepartmentId(department.getId());
            if(null != users && users.size() > 0){
                for (User user : users) {
                    LoginHelper.delObject(user.getToken());
                }
            }
        } catch (Exception e) {
            logger.error("服务器超时，更新失败");
        }
    }


    /**
     * 获取所有部门
     * @return
     */
    public List<Department> getAllForWeb() throws Exception{
        return departmentMapper.getAllForWeb();
    }


    /**
     * 获取公司下所有部门（下拉列表使用）（存在A导师的部门）
     * @return
     */
    public List<Department> getAllForWebGroup() throws Exception{
        return departmentMapper.getAllForWebGroup();
    }



    public Department info(Integer departmentId){
        return departmentMapper.info(departmentId);
    }


    /**
     *  结项奖
     * @param companyId
     * @param timeStamp
     * @return
     * @throws Exception
     */
    public List<ProjectAdwards> statisesProjectAdwards(Integer companyId, Long timeStamp) throws Exception{
        Map<String, Date> dateMap = DateTimeHelper.getDateByTimeType(1, timeStamp);
        return departmentMapper.statisesProjectAdwards(companyId,dateMap.get("startTime"),dateMap.get("endTime"));
    }
}
