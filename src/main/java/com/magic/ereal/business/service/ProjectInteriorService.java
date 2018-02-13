package com.magic.ereal.business.service;

import com.magic.ereal.business.entity.*;
import com.magic.ereal.business.exception.InterfaceCommonException;
import com.magic.ereal.business.mapper.IProjectGroupMapper;
import com.magic.ereal.business.mapper.IProjectInteriorMapper;
import com.magic.ereal.business.mapper.IUserMapper;
import com.magic.ereal.business.util.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

/**
 * 内部项目 -- 业务
 * @author lzh
 * @create 2017/4/28 10:50
 */
@Service
public class ProjectInteriorService {


    @Resource
    private IProjectInteriorMapper projectInteriorMapper;
    @Resource
    private IUserMapper userMapper;
    @Resource
    private IProjectGroupMapper projectGroupMapper;

    /**
     * 导入内部项目
     * @param excelUrl
     * @throws Exception
     */
    public void importExcelProjectInterior(String excelUrl) throws Exception{

        int HttpResult; // 服务器返回的状态
        URL url = new URL(excelUrl); // 创建URL
        URLConnection urlConn = url.openConnection();
        urlConn.connect();
        HttpURLConnection httpConn = (HttpURLConnection) urlConn;
        HttpResult = httpConn.getResponseCode();
        if (HttpResult != HttpURLConnection.HTTP_OK)
            throw new InterfaceCommonException(StatusConstant.Fail_CODE, "数据读取失败");
        else {
            Map<Integer, List<String>> map = ExcelReader.readExcelContent(urlConn.getInputStream());
            List<ProjectInterior> projectInteriors = new ArrayList<>(); // 读取出来的用户列表
            // 解析数据
            for (Integer cellNum : map.keySet()) {
                List<String> values = map.get(cellNum);
                if (CommonUtil.isEmpty(values.get(0))) {
                    throw new InterfaceCommonException(StatusConstant.OBJECT_NOT_EXIST, "项目编号必须填写");
                }
                if (CommonUtil.isEmpty(values.get(1))) {
                    throw new InterfaceCommonException(StatusConstant.OBJECT_NOT_EXIST, "项目名必须填写");
                }
                if (CommonUtil.isEmpty(values.get(2))) {
                    throw new InterfaceCommonException(StatusConstant.OBJECT_NOT_EXIST, "项目简称必须填写");
                }
                if (null == values.get(3)) {
                    throw new InterfaceCommonException(StatusConstant.OBJECT_NOT_EXIST, "初始工作量必须填写");
                }
                if (CommonUtil.isEmpty(values.get(4))) {
                    throw new InterfaceCommonException(StatusConstant.OBJECT_NOT_EXIST, "项目启动时间必须填写");
                }
                if (CommonUtil.isEmpty(values.get(6))) {
                    throw new InterfaceCommonException(StatusConstant.OBJECT_NOT_EXIST, "是否对内必须填写");
                }

                ProjectInterior temp = new ProjectInterior();
                temp.setProjectNumber(values.get(0));
                temp.setProjectName(values.get(1));
                temp.setShortName(values.get(2));
                temp.setInitWorkload(Double.valueOf(values.get(3)));
                temp.setStartTime(null == values.get(4) ? null : DateStringUtil.stringToDate(values.get(4),"yyyy-MM-dd"));
                temp.setEndTime(null == values.get(5) ? null : DateStringUtil.stringToDate(values.get(5),"yyyy-MM-dd"));
                temp.setCreateUserId(((User)LoginHelper.getCurrentUser()).getId());
                if("是".equals(values.get(6))){
                    temp.setAtHome(0);
                }else if("否".equals(values.get(6))) {
                    temp.setAtHome(1);
                }
                projectInteriors.add(temp);
            }

            if (projectInteriors.size() == 0) {
                throw new InterfaceCommonException(StatusConstant.Fail_CODE, "表格数据为空");
            }
            if(projectInteriors.size() > 0){
                projectInteriorMapper.batchAddProjectInterior(projectInteriors);
            }
        }

    }


    public void delProjectInterior(Integer projectId){
        projectInteriorMapper.delProjectInterior(projectId);
    }

    public ProjectInterior queryBaseInfo(Integer id){
        return projectInteriorMapper.queryBaseInfo(id);
    }

    /**
     * 通过内部项目ID  查询项目 包括所有字段
     * @param id
     * @return
     */
    public ProjectInterior queryProjectInteriorById(Integer id){
        ProjectInterior projectInterior = projectInteriorMapper.queryProjectInteriorById(id);
        // 计算效率
        countEfficiency(projectInterior);
        return projectInterior;
    }


    public List<ProjectInterior> queryProjectInteriorByItem(Map<String,Object> map){
        List<ProjectInterior> projectInteriors = projectInteriorMapper.queryProjectInteriorByItem(map);
        // 计算效率
        for (ProjectInterior projectInterior : projectInteriors){
            countEfficiency(projectInterior);
        }
        return projectInteriors;
    }


    /**
     *  计算项目 效率
     * @param project
     */
    private void countEfficiency(ProjectInterior project) {
        if(null != project && null != project.getUseTime() && project.getUseTime() != 0){
            Double sumK = 0.0;
            if(null != project.getAcceptances()){
                for (ProjectInteriorWeekAcceptance weekAcceptance : project.getAcceptances()){
                    if(null != weekAcceptance.getIsAdd() && weekAcceptance.getIsAdd() == 0){
                        sumK -= weekAcceptance.getSumK() == null? 0.0 : weekAcceptance.getSumK();
                    }else {
                        sumK += weekAcceptance.getSumK() == null? 0.0 : weekAcceptance.getSumK();
                    }
                }
            }
            // 计算效率
            project.setEfficiency(sumK * 135 / project.getUseTime());
        }
    }

    /**
     * 内部项目 立项
     * @param projectInterior
     */
    public void save(ProjectInterior projectInterior) throws Exception{

        List<ProjectGroup> list = projectGroupMapper.queryProjectGroupByDepartment(projectInterior.getDepartmentId());
        if (null == list || list.size() < 1 ) {
            throw new InterfaceCommonException(StatusConstant.NO_DATA,"此部门暂无项目组，请先设置");
        }
//        User user = userMapper.queryManagerByDepartment(projectInterior.getDepartmentId());
//        if (null == user) {
//            throw new InterfaceCommonException(StatusConstant.NO_DATA,"此部门暂无团队长，请先设置");
//        }
//        projectInterior.setAllocationUserId(user.getId());
        projectInteriorMapper.save(projectInterior);
    }

    /**
     * 更新项目信息
     * @param projectInterior
     */
    public void update(ProjectInterior projectInterior) {
        projectInteriorMapper.update(projectInterior);
    }

    /**
     * 更新项目全部信息 （包括为空时）
     * @param projectInterior
     */
    public void updateAll(ProjectInterior projectInterior) throws Exception{
        projectInteriorMapper.updateAll(projectInterior);
    }

    /**
     * 分页查询内部项目列表
     * @param projectInterior
     * @param roleId
     * @param pageArgs
     * @return
     */
    public PageList<ProjectInterior> list(ProjectInterior projectInterior ,Integer roleId, PageArgs pageArgs,Integer userId, Integer isManagerId) throws Exception{
        PageList<ProjectInterior> pageList = new PageList<>();
        Map<String,Object> map = new HashMap<>();
        map.put("p",projectInterior);
        map.put("roleId",roleId);
        map.put("isManagerId",isManagerId);
        map.put("userId",userId);
        //获取总数
        int count = projectInteriorMapper.countList(map);
        //总条数
        if (count > 0) {
            map.put("pageArgs", pageArgs);
            // 计算效率
           List<ProjectInterior> projectInteriors =  projectInteriorMapper.list(map);
            for (ProjectInterior p : projectInteriors){
                countEfficiency(p);
            }
            pageList.setList(projectInteriors);
        }
        pageList.setTotalSize(count);
        return pageList;
    }

    /**
     * 项目详情
     * @param id
     * @return
     */
    public ProjectInterior info(Integer id) throws Exception{
        return projectInteriorMapper.info(id);
    }

    /**
     * 传递卡 内部项目筛选
     * @param userId 用户id
     * @return
     */
    public Set<ProjectInterior> getWorkDiaryProInterior(Integer userId) {
        return projectInteriorMapper.getWorkDiaryProInterior(userId);
    }
}
