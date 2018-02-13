package com.magic.ereal.business.service;

import com.magic.ereal.business.entity.*;
import com.magic.ereal.business.exception.InterfaceCommonException;
import com.magic.ereal.business.mapper.IProjectAcceptanceRecordMapper;
import com.magic.ereal.business.mapper.IProjectMapper;
import com.magic.ereal.business.mapper.IProjectTypeMapper;
import com.magic.ereal.business.mapper.IUserMapper;
import com.magic.ereal.business.util.*;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

/**
 * 外部项目 业务层
 * Created by Eric Xie on 2017/4/26 0026.
 */
@Service
public class ProjectService {

    @Resource
    private IProjectMapper projectMapper;
    @Resource
    private IUserMapper userMapper;
    @Resource
    private IProjectAcceptanceRecordMapper projectAcceptanceRecordMapper;
    @Resource
    private ProjectAcceptanceRecordService projectAcceptanceRecordService;
    @Resource
    private IProjectTypeMapper projectTypeMapper;
    @Resource
    private AllConfigService allConfigService;


    /**
     * 导入外部项目
     * @param excelUrl
     * @throws Exception
     */
    public void importExcelProject(String excelUrl) throws Exception{
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
            List<Project> projects = new ArrayList<>(); // 读取出来的用户列表
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
                if (CommonUtil.isEmpty(values.get(7))) {
                    throw new InterfaceCommonException(StatusConstant.OBJECT_NOT_EXIST, "是否对内必须填写");
                }
                if (CommonUtil.isEmpty(values.get(8))) {
                    throw new InterfaceCommonException(StatusConstant.OBJECT_NOT_EXIST, "是否对内必须填写");
                }
                if (CommonUtil.isEmpty(values.get(9))) {
                    throw new InterfaceCommonException(StatusConstant.OBJECT_NOT_EXIST, "是否对内必须填写");
                }

                Project temp = new Project();
                temp.setProjectNumber(values.get(0));
                temp.setProjectName(values.get(1));
                temp.setProjectNameShort(values.get(2));
                temp.setCustomerUnit(values.get(3));
                temp.setCustomerDepartment(values.get(4));
                temp.setReceiveTime(null == values.get(5) ? null : DateStringUtil.stringToDate(values.get(5),"yyyy-MM-dd"));
                temp.setSubmitTime(null == values.get(6) ? null : DateStringUtil.stringToDate(values.get(6),"yyyy-MM-dd"));
                temp.setCreateUserId(((User)LoginHelper.getCurrentUser()).getId());
                temp.setCustomerRemarks(values.get(7));
                temp.setRiskRemarks(values.get(8));
                temp.setOtherRemarks(values.get(9));
                projects.add(temp);
            }

            if (projects.size() == 0) {
                throw new InterfaceCommonException(StatusConstant.Fail_CODE, "表格数据为空");
            }
            if(projects.size() > 0){
                projectMapper.batchAddProject(projects);
            }
        }

    }

    public List<ExcelProject> excelProjects(Integer projectGroupId, Integer status, Integer departmentId, Integer projectTypeId){
        List<ExcelProject> excelProjects = projectMapper.excelProject(projectGroupId,status,departmentId,projectTypeId);
        return excelProjects;
    }

    /**
     * 外部项目报表导出
     * @param projectGroupId
     * @param status
     * @param departmentId
     * @param projectTypeId
     */
    public List<ExportProject> exportProject(Integer projectGroupId, Integer status, Integer departmentId, Integer projectTypeId){
        Map<String,Object> map = new HashMap<>();
        map.put("projectGroupId",projectGroupId);
        map.put("status",status);
        map.put("departmentId",departmentId);
        map.put("projectTypeId",projectTypeId);
        List<ExportProject> exportProjects = projectMapper.exportProject(map);
        // 计算各种效率
        if(null != exportProjects && exportProjects.size() > 0){
            AllConfig config = allConfigService.getConfig();
            for (ExportProject project : exportProjects) {
                // 初始K
                project.setInitK((project.getInitWorkload() == null ? 0 : project.getInitWorkload()) * config.getDayHour() / config.getkHour());
                project.setScheduleProject(null == project.getScheduleProject() ? 0.0 : project.getScheduleProject() * 100);
                if(null != project.getExportProjectControlList() && project.getExportProjectControlList().size() > 0){
                    for (ExportProjectControl projectControl : project.getExportProjectControlList()) {
                        if(null ==  projectControl.getCurreTotalH() ||  projectControl.getCurreTotalH() == 0.0){
                            projectControl.setEfficiency(0.0);
                        }
                        else{
                            projectControl.setEfficiency(projectControl.getCurreTotalK() * config.getkHour() / projectControl.getCurreTotalH());
                        }
                        if(projectControl.getRole() == 0){
                            project.setAExportProjectControl(projectControl);
                        }
                        else if(projectControl.getRole() == 1){
                            project.setPMExportProjectControl(projectControl);
                        }
                        else{
                            if(null == project.getOtherProjectControlList()){
                                project.setOtherProjectControlList(new ArrayList<>());
                            }
                            if(project.getOtherProjectControlList().size() <= 5){
                                project.getOtherProjectControlList().add(projectControl);
                            }
                        }
                    }
                }
                if(null == project.getOtherProjectControlList()){
                    project.setOtherProjectControlList(new ArrayList<>());
                }
                int oi = project.getOtherProjectControlList().size();
                for (int i = 0; i < 5 - oi; i++) {



                    project.getOtherProjectControlList().add(new ExportProjectControl());
                }

                project.setCount(5 - (null == project.getOtherProjectControlList() ? 0 : project.getOtherProjectControlList().size()));
                // 设置内外部结项记录
                if(null != project.getProjectRecordList() && project.getProjectRecordList().size() > 0){

                    for (ProjectAcceptanceRecord record : project.getProjectRecordList()) {
                        if(2 == record.getType() && 1 == record.getStatus()){
                            // 项目完结记录
                            ExportProjectK k = new ExportProjectK();
                            k.setReceiveTime(record.getCreateTime());

                            getProjectK(record, k);
                            project.setFinishRecord(k);
                            continue;
                        }
                        if(null != record.getProjectKs() && record.getProjectKs().size() > 0){
                            ExportProjectK k = new ExportProjectK();
                            k.setReceiveTime(record.getCreateTime());
                            getProjectK(record, k);
                            if(null == project.getOtherProjectRecordList()){
                                project.setOtherProjectRecordList(new ArrayList<>());
                            }
                            if (project.getOtherProjectRecordList().size() <= 5) {
                                project.getOtherProjectRecordList().add(k);
                            }
                        }
                    }
                }
                else{
                    // 没有内外部结项记录
                    if(null == project.getProjectRecordList()){
                        project.setProjectRecordList(new ArrayList<>());
                    }
                    int size = project.getProjectRecordList().size();
                    for (int i = 0; i < size; i++) {


                    }


                }


                if(null == project.getOtherProjectRecordList()){
                    project.setOtherProjectRecordList(new ArrayList<>());
                }
                else{
                    for (ExportProjectK exportProjectK : project.getOtherProjectRecordList()) {
                        if(null == exportProjectK.getUserKList()){
                            exportProjectK.setUserKList(new ArrayList<>());
                        }
                        int size = exportProjectK.getUserKList().size();
                        for (int i = 0; i < 5 - size; i++) {
                            exportProjectK.getUserKList().add(new UserK());
                        }
                    }
                }
                int size = project.getOtherProjectRecordList().size();
                for (int i = 0; i < 5 - size; i++) {
                    ExportProjectK e = new ExportProjectK();
                    e.setUserKList(new ArrayList<>());
                    for (int i1 = 0; i1 < 5; i1++) {
                        e.getUserKList().add(new UserK());
                    }
                    project.getOtherProjectRecordList().add(e);
                }

                project.setRecordCount(5 - (null == project.getOtherProjectRecordList() ? 0 : project.getOtherProjectRecordList().size()));
                // 设置周验收 开始
                if(null != project.getWeekAcceptanceList() && project.getWeekAcceptanceList().size() > 0){

                    for (ProjectWeekAcceptance week : project.getWeekAcceptanceList()) {
                        ProjectWeekExport projectWeek = new ProjectWeekExport();
                        projectWeek.setTotalK(null == week.getSumK() ? 0.0 : week.getSumK());
                        projectWeek.setReceiveTime(week.getUpdateTime());
                        projectWeek.setScore((null == week.getTotalK() ? 0 : week.getTotalK()) / (project.getInitWorkload() * config.getDayHour() / config.getkHour()));

                        List<ProjectWeekJson> list = com.alibaba.fastjson.JSONArray.parseArray(week.getSectionDetail(),ProjectWeekJson.class);

                        List<WeekExport> weekExportList = new ArrayList<>();
                        for (ProjectWeekJson weekJson : list) {
                            WeekExport weekExport = new WeekExport();
                            weekExport.setSectionNum(weekJson.getSectionNum());
                            weekExport.setQuality(weekJson.getQuality());
                            weekExport.setSchedule(weekJson.getSchedule());
                            weekExportList.add(weekExport);

                        }

                        if(null != week.getProjectWeekKAllocations() && week.getProjectWeekKAllocations().size() > 0){
                            double ak = 0.0;
                            double pmk = 0.0;
                            List<UserK> userKs = new ArrayList<>();

                            for (WeekExport weekExport : weekExportList) {
                                for (ProjectWeekKAllocation allocation : week.getProjectWeekKAllocations()) {
                                    if(0 == allocation.getRole()){
                                        weekExport.setARatio(allocation.getRatio());
                                    }
                                    else if(1 == allocation.getRole()){
                                        weekExport.setPMRatio(allocation.getRatio());
                                    }
                                    else{
                                        if(null == weekExport.getUserKs()){
                                            weekExport.setUserKs(new ArrayList<>());
                                        }
                                        if(weekExport.getUserKs().size() < 5){
                                            UserK r = new UserK();
                                            r.setUserRatio(allocation.getRatio());
                                            r.setId(allocation.getUserId());
                                            if(!weekExport.getUserKs().contains(r)){
                                                weekExport.getUserKs().add(r);
                                            }
                                        }
                                    }
                                }

                            }

                            for (ProjectWeekKAllocation allocation : week.getProjectWeekKAllocations()) {
//                                for (WeekExport weekExport : weekExportList) {
//                                    if(allocation.getSectionNum().equals(weekExport.getSectionNum())){
//                                        if(0 == allocation.getRole()){
//                                            weekExport.setARatio(allocation.getRatio());
//                                        }
//                                        else if(1 == allocation.getRole()){
//                                            weekExport.setPMRatio(allocation.getRatio());
//                                        }
//                                        else{
//                                            if(null == weekExport.getUserKs()){
//                                                weekExport.setUserKs(new ArrayList<>());
//                                            }
//                                            if(weekExport.getUserKs().size() < 5){
//                                                UserK r = new UserK();
//                                                r.setUserRatio(allocation.getRatio());
//                                                r.setId(allocation.getUserId());
//                                                weekExport.getUserKs().add(r);
//                                            }
//                                        }
//                                    }
//                                }
                                // 补充数据

                                for (WeekExport weekExport : weekExportList) {
                                    if(null == weekExport.getUserKs()){
                                        weekExport.setUserKs(new ArrayList<>());
                                    }
                                    int size1 = weekExport.getUserKs().size();
                                    for (int i = 0; i < 5 - size1; i++) {
                                        weekExport.getUserKs().add(new UserK());
                                    }
                                }

                                // 当前所在项目的角色  0:A导师、1:项目经理 2:普通成员
                                if(0 == allocation.getRole()){
                                    ak += (allocation.getSectionSumK() * allocation.getRatio() / 100);
                                }
                                else if(1 == allocation.getRole()){
                                    pmk += (allocation.getSectionSumK() * allocation.getRatio() / 100);
                                }
                                else{
                                    if(userKs.size() < 5){
                                        boolean isExist = false;
                                        for (UserK userK : userKs) {
                                            if(userK.getId().equals(allocation.getUserId())){
                                                isExist = true;
                                                userK.setUserK(userK.getUserK() + (allocation.getSectionSumK() * allocation.getRatio() / 100));
                                            }
                                        }
                                        if(!isExist){
                                            UserK k = new UserK();
                                            k.setId(allocation.getUserId());
                                            k.setUserK(allocation.getSectionSumK() * allocation.getRatio() / 100);
                                            userKs.add(k);
                                        }
                                    }
                                }
                            }
                            projectWeek.setAK(ak);
                            projectWeek.setPMK(pmk);
                            // 补充数据
                            int size1 = userKs.size();
                            for (int i = 0; i < 5 - size1; i++) {
                                userKs.add(new UserK());
                            }
                            projectWeek.setTotalUserKs(userKs);
                        }



                        // 设置剩余数量
                        for (WeekExport weekExport : weekExportList) {

                            if(null == weekExport.getUserKs()){
                                weekExport.setUserKs(new ArrayList<>());
                            }
                            int size1 = weekExport.getUserKs().size();
                            for (int i = 0; i < 5 - size1; i++) {
                                weekExport.getUserKs().add(new UserK());
                            }
                            weekExport.setCount(5 - (null == weekExport.getUserKs() ? 0 : weekExport.getUserKs().size()));
                        }

                        if(null == projectWeek.getTotalUserKs()){
                            projectWeek.setTotalUserKs(new ArrayList<>());
                        }
                        int size1 = projectWeek.getTotalUserKs().size();
                        for (int i = 0; i < 5 - size1; i++) {
                            projectWeek.getTotalUserKs().add(new UserK());
                        }
                        projectWeek.setCount(5 - (null == projectWeek.getTotalUserKs() ? 0 : projectWeek.getTotalUserKs().size()));
                        projectWeek.setWeekExports(weekExportList);

                        // 装进项目
                        if(null == project.getProjectWeekExports()){
                            project.setProjectWeekExports(new ArrayList<>());
                        }
                        if(project.getProjectWeekExports().size() < 15){
                            project.getProjectWeekExports().add(projectWeek);
                        }
                    }

                }
                // 设置周验收 结束
                if(null == project.getFinishRecord()){
                    ExportProjectK finishRecord = new ExportProjectK();
                    finishRecord.setUserKList(new ArrayList<>());
                    for (int i = 0; i < 5; i++) {
                        finishRecord.getUserKList().add(new UserK());
                    }
                    project.setFinishRecord(finishRecord);
                }
                else{

                    if(null == project.getFinishRecord().getUserKList()){
                        project.getFinishRecord().setUserKList(new ArrayList<>());
                    }
                    int size1 = project.getFinishRecord().getUserKList().size();
                    for (int i = 0; i < 5 - size1; i++) {
                        project.getFinishRecord().getUserKList().add(new UserK());
                    }

                }
                if(null == project.getProjectRecordList()){
                    project.setProjectRecordList(new ArrayList<>());
                }
                int size1 = project.getProjectRecordList().size();
                for (int i = 0; i < 5 - size1; i++) {

                    ProjectAcceptanceRecord e = new ProjectAcceptanceRecord();
                    e.setProjectKs(new ArrayList<>());
                    for (int i1 = 0; i1 < 5; i1++) {
                        e.getProjectKs().add(new ProjectK());
                    }
                    project.getProjectRecordList().add(e);
                }
            }
        }
        return exportProjects;
    }

    private void getProjectK(ProjectAcceptanceRecord record, ExportProjectK k) {
        k.setScore(record.getScore());
        k.setTotalK(record.getBadSumK());
        for (ProjectK projectK : record.getProjectKs()) {
            //  0:A导师、1:项目经理 2:普通成员
            if(projectK.getRole() == 0){
                k.setAK(projectK.getSumK());
                k.setARatio(projectK.getRatio());
            }
            else if(projectK.getRole() == 1){
                k.setPMK(projectK.getSumK());
                k.setPMRatio(projectK.getRatio());
            }
            else{
                if(null == k.getUserKList()){
                    k.setUserKList(new ArrayList<>());
                }
                if(k.getUserKList().size() < 5){
                    UserK userK = new UserK();
                    userK.setUserK(projectK.getSumK());
                    userK.setUserRatio(projectK.getRatio());
                    k.getUserKList().add(userK);
                }
            }
        }
        k.setCount(5 - (null == k.getUserKList() ? 0 :  k.getUserKList().size()));
    }


    /**
     * 获取 外部项目列表
     *
     * @param userId
     * @param roleId
     * @param pageNO
     * @param pageSize
     * @param sortType 0:项目已获总K 排序  1：项目进度 排序 2：当周新增K值 排序 3：当月新增K值 排序 4：项目效率 排序
     * @return
     */
    public List<Project> queryProjectByItem(Integer userId, Integer roleId, Integer pageNO, Integer pageSize,
                                            Integer status, Integer department, Integer projectType, Integer sortType) {


        //获取这个时间的这周星期一的日期 开始时间
        Date startWeekTime = DateTimeHelper.getWeekByDate(new Date(), 1);
        //获取这个时间的这周星期日的日期 结束时间
        Date endWeekTime = DateTimeHelper.getWeekByDate(new Date(), 7);
        startWeekTime = DateTimeHelper.setDateField(startWeekTime, 0, 0, 0);
        endWeekTime = DateTimeHelper.setDateField(endWeekTime, 23, 59, 59);


        //获取这个时间的这月月初的日期 开始时间
        Date startMonthTime = DateTimeHelper.getMonthByDate(new Date(), "first");
        //获取这个时间的这月月末的日期 结束时间
        Date endMonthTime = DateTimeHelper.getMonthByDate(new Date(), "last");
        startMonthTime = DateTimeHelper.setDateField(startMonthTime, 0, 0, 0);
        endMonthTime = DateTimeHelper.setDateField(endMonthTime, 23, 59, 59);

        List<Project> projects = projectMapper.queryProjectByItem(userId, roleId, (pageNO - 1) * pageSize, pageSize,
                status, department, projectType, sortType, startWeekTime, endWeekTime, startMonthTime, endMonthTime);

//        AllConfig config = allConfigService.getConfig();
//
//        for (Project project : projects){
//            //  计算效率
//            countEfficiency(project);
//            // 计算进度
//            if(project.getInitWorkload() == null){
//                project.setProgress(0.0);
//            }else{
//                project.setProgress(project.getTotalK() / (project.getInitWorkload() * config.getDayHour() / config.getkHour()));
//            }
//        }
        return projects;
    }

    /**
     * 获取 外部项目列表 (web)
     *
     * @param userId
     * @param roleId
     * @param pageArgs
     * @return
     */
    public PageList<Project> list(Integer userId, Integer roleId, PageArgs pageArgs,
                                  String projectNumber, String projectName, Integer projectGroupId, Integer status, Integer departmentId,
                                  Integer projectTypeId, Integer isManagerId,Integer isTerminate) {
        PageList<Project> pageList = new PageList<>();
        //总条数
        int count = projectMapper.countProjectByItemForWeb(userId, roleId, projectNumber, projectName,
                projectGroupId, status, departmentId, projectTypeId, isManagerId,isTerminate);

        List<Project> projects = projectMapper.queryProjectByItemForWeb(userId, roleId, pageArgs.getPageStart(), pageArgs.getPageSize(),
                projectNumber, projectName, projectGroupId, status, departmentId, projectTypeId, isManagerId,isTerminate);
        AllConfig config = allConfigService.getConfig();
         // 计算效率
        for (Project project : projects) {
            //  计算效率
            countEfficiency(project);
//            // 计算进度
//            if (project.getInitWorkload() == null) {
//                project.setProgress(0.0);
//            } else {
//                project.setProgress(project.getTotalK() == null ? 0 : project.getTotalK() / (project.getInitWorkload() * config.getDayHour() / config.getkHour()));
//            }
        }
        pageList.setList(projects);
        pageList.setTotalSize(count);
        return pageList;
    }

    /**
     * 创建项目
     *
     * @param project
     */
    public void addProject(Project project) throws Exception {
        projectMapper.addProject(project);
    }

    /**
     * 更新 项目 不为空的字段 根据ID
     * 注：不更新的字段 需置空
     *
     * @param project
     * @return
     */
    @Transactional
    public void updateProject(Project project, ProjectAcceptanceRecord projectAcceptanceRecord) throws Exception {
        if(null != project.getConnectStatus() && project.getConnectStatus() == 0){
            project.setStartTime(new Date());
        }
        projectMapper.updateProject(project);
        if (null != projectAcceptanceRecord) {
            if (null == projectAcceptanceRecord.getType()) {
                throw new InterfaceCommonException(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
            } else {
                if (null == projectAcceptanceRecord.getProjectId()) {
                    throw new InterfaceCommonException(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
                }
                if (null == projectAcceptanceRecord.getStatus()) {
                    throw new InterfaceCommonException(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
                } else {
                    //验收状态 0：等待验收  1：通过  2：验收失败
                    if (projectAcceptanceRecord.getStatus() != 0) {
                        if (null == projectAcceptanceRecord.getScore()) {
                            throw new InterfaceCommonException(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
                        }
                    }
                }
            }
            projectAcceptanceRecordMapper.save(projectAcceptanceRecord);
        }
    }

    /**
     * 更新 项目 不为空的字段 根据ID
     * 注：不更新的字段 需置空
     *
     * @return
     */
    @Transactional
    public void updateProject(Project project)  {
        projectMapper.updateProject(project);
    }

    /**
     * 值总审核
     * 注：不更新的字段 需置空
     *
     * @param project
     * @return
     */
    public void updateProjectGeneralManagerOnDuty(Project project) throws Exception {
        projectMapper.updateProject(project);
    }


    /**
     * 项目管理处分配 更新
     * 注：不更新的字段 需置空
     *
     * @param project
     * @return
     */
    public void updateProjectManagerCommittee(Project project) throws Exception {
        User user = userMapper.queryManagerByDepartment(project.getDepartmentId());
        if (null == user) {
            throw new InterfaceCommonException(StatusConstant.NO_DATA, "此部门暂无团队长，请先设置");
        }
        //交接状态 0：已创建(待分配) 1：已分配 2：未通过 3：通过
        project.setConnectStatus(1);
//        if (user.getId().equals(project.getbTeacherId())) {
//            throw new InterfaceCommonException(StatusConstant.ARGUMENTS_EXCEPTION,
//                    "A导师(团队长)和B导师不能为同一人，请更换项目组或者B导师");
//        }
        if(project.getaTeacher().equals(project.getbTeacherId()) ||
                project.getaTeacher().equals(project.getcTeacherId())
                || project.getbTeacherId().equals(project.getcTeacherId())){
            throw new InterfaceCommonException(StatusConstant.ARGUMENTS_EXCEPTION,
                    "A导师、B导师、C导师不能为同一人");
        }
//        project.setaTeacher(user.getId());
        projectMapper.updateProject(project);
    }


    /**
     * 包含 周验收 数据 && 用户在该项目中所得的K值总和
     *
     * @param id
     * @return
     */
    public Project queryProjectById(Integer id) {
        Project project = projectMapper.queryProjectById(id);
        // 初始工作量K值计算
        AllConfig config = allConfigService.getConfig();
        project.setInitK((project.getInitWorkload() == null ? 0 : project.getInitWorkload()) * config.getDayHour() / config.getkHour());
        countEfficiency(project);
        //验收进展状态列表 （内部验收、外部验收、周验收）
        project.setAcceptanceList(projectAcceptanceRecordService.acceptanceList(id));
        return project;
    }


    /**
     * 通过ID 获取 项目的基础数据(数据库表字段的所有数据 不包含业务数据)
     *
     * @param projectId 项目ID
     * @return
     */
    public Project queryBaseProjectById(Integer projectId) {
        return projectMapper.queryBaseProjectById(projectId);
    }


    /**
     * 计算项目 效率
     *
     * @param project
     */
    private void countEfficiency(Project project) {
        // 计算效率
        project.setEfficiency(null == project.getEfficiency() ? 0 : project.getEfficiency() / 100);
    }


    /**
     * 批量内部结项审核通过
     *
     * @param projectIds
     */
    @Transactional
    public void saveUpdateList(String projectIds, Integer userId) {
        List<Project> list = new ArrayList<>();
        List<ProjectAcceptanceRecord> list1 = new ArrayList<>();
        String[] proIds = projectIds.split(",");
        Integer[] projectIdsInt = new Integer[proIds.length];
        for (int i = 0; i < proIds.length; i++) {
            projectIdsInt[i] = Integer.parseInt(proIds[i]);
            //项目
            Project project = new Project();
            project.setId(Integer.parseInt(proIds[i]));
            project.setStatus(StatusConstant.INTERIOR_OVER);
            project.setOverTime(new Date());
            list.add(project);
        }

        /**
         11：等待c导师审核/抽查/打分（根据b导师打分显示）
         13：不通过（打回）*/
        //b导师打分集合
        List<ProjectAcceptanceRecord> pList = projectAcceptanceRecordMapper.getInnerAllKList(projectIdsInt, 11);
        //上次c导师打分未通过集合
        List<ProjectAcceptanceRecord> pList1 = projectAcceptanceRecordMapper.getInnerAllKList(projectIdsInt, 13);
        //项目集合
        List<Project> prList = projectMapper.getByIds(projectIdsInt);
        for (ProjectAcceptanceRecord record : pList) {
            ProjectAcceptanceRecord p = new ProjectAcceptanceRecord();
            p.setCreateUserId(userId);
            p.setStatus(12);
            p.setProjectId(record.getProjectId());
            p.setType(1);
            for (Project project : prList) {
                if (project.getId().equals(record.getProjectId())) {
                    //当前总k值
                    // TODO: 2017/5/10 计算外部结项k值 初始工作量 * 8.5 / 135 * 进度分 / 100
                    p.setSumK(project.getInitWorkload() * 8.5 / 135 * record.getScore() / 100);
                    p.setScore(record.getScore());
                    break;
                }
            }
            //k差值 默认当前总k值
            Double badSumK = p.getSumK();
            //是否上升 isAdd  0:否  1：是 默认上升
            p.setIsAdd(1);
            //存在
            if (pList1.contains(record.getId())) {
                for (ProjectAcceptanceRecord acceptanceRecord : pList1) {
                    if (record.getProjectId().equals(acceptanceRecord.getProjectId())) {
                        //k差值
                        badSumK = p.getSumK() - acceptanceRecord.getSumK();
                        //是否上升 isAdd  0:否  1：是
                        if (badSumK < 0) {
                            p.setIsAdd(0);
                        }
                    }
                }
            }
            p.setBadSumK(badSumK);
            list1.add(p);
        }
        projectAcceptanceRecordMapper.saveList(list1);
        projectMapper.updateListStatus(list);
    }


    /**
     * 获取需要可审核的项目
     *
     * @param pageNO   页码
     * @param pageSize 条数
     * @param userId   c导师id
     * @return
     */
    public List<Project> getAuditProject(Integer pageNO, Integer pageSize, Integer userId, Integer roleId,Integer departmentId,
                                         Integer projectType,Integer sortType) throws Exception {

        //获取这个时间的这周星期一的日期 开始时间
        Date startWeekTime = DateTimeHelper.getWeekByDate(new Date(), 1);
        //获取这个时间的这周星期日的日期 结束时间
        Date endWeekTime = DateTimeHelper.getWeekByDate(new Date(), 7);
        startWeekTime = DateTimeHelper.setDateField(startWeekTime, 0, 0, 0);
        endWeekTime = DateTimeHelper.setDateField(endWeekTime, 23, 59, 59);


        //获取这个时间的这月月初的日期 开始时间
        Date startMonthTime = DateTimeHelper.getMonthByDate(new Date(), "first");
        //获取这个时间的这月月末的日期 结束时间
        Date endMonthTime = DateTimeHelper.getMonthByDate(new Date(), "last");
        startMonthTime = DateTimeHelper.setDateField(startMonthTime, 0, 0, 0);
        endMonthTime = DateTimeHelper.setDateField(endMonthTime, 23, 59, 59);


        List<Project> auditProject = projectMapper.getAuditProject((pageNO - 1) * pageSize, pageSize, userId, roleId,
                departmentId,projectType,sortType,startWeekTime,endWeekTime,startMonthTime,endMonthTime);
//        AllConfig config = allConfigService.getConfig();
//        for (Project project : auditProject) {
//            countEfficiency(project);
//            if (project.getInitWorkload() == null) {
//                project.setProgress(0.0);
//            } else {
//                project.setProgress(project.getTotalK() / (project.getInitWorkload() * config.getDayHour() / config.getkHour()));
//            }
//        }
        return auditProject;

    }

    /**
     * 获取需要可审核的项目
     *
     * @param pageNO   页码
     * @param pageSize 条数
     * @param userId   c导师id
     * @return
     */
    public PageList<Project> getAuditProjectForWeb(Integer pageNO, Integer pageSize, Integer userId, Integer roleId) throws Exception {

        List<Project> auditProject = projectMapper.getAuditProjectForWeb((pageNO - 1) * pageSize, pageSize, userId, roleId);
        int count = projectMapper.countAuditProject(userId,roleId);
        return new PageList<>(auditProject,count);

    }

    /**
     * 根据时间段获取破题统计 (统计图)
     *
     * @param startTime    开始时间
     * @param endTime      结束时间
     * @param departmentId 部门id
     * @return
     */
    public Map<String, Object> getByTimeStatistics(Long startTime, Long endTime, Integer departmentId, Integer roleId) {


        Map<String, Object> map = new HashMap<>();

        Date start = DateTimeHelper.setDateField(new Date(startTime), 0, 0, 0);
        Date end = DateTimeHelper.setDateField(new Date(endTime), 23, 59, 59);


        /**type 1未破   2 半破   3 全破 */
        //此时间段全部项目
        List<PoStatistics> allList = projectMapper.getByTime(start, end, departmentId, null, roleId);
        //此时间段未破项目
        List<PoStatistics> poNoneList = projectMapper.getByTime(start, end, departmentId, 1, roleId);
        //此时间段半破项目
        List<PoStatistics> poHalfList = projectMapper.getByTime(start, end, departmentId, 2, roleId);
        //此时间段全破项目
        List<PoStatistics> poAllList = projectMapper.getByTime(start, end, departmentId, 3, roleId);
        //部门名数组
        String[] departmentNameAry = new String[allList.size()];
        //此时间段全部项目 数组
        Integer[] allAry = new Integer[allList.size()];
        //此时间段未破项目 数组
        Integer[] poNoneAry = new Integer[allList.size()];
        //此时间段半破项目 数组
        Integer[] poHalfAry = new Integer[allList.size()];
        //此时间段全破项目 数组
        Integer[] poAllAry = new Integer[allList.size()];

        int i = 0;
        for (PoStatistics statistics : allList) {
            departmentNameAry[i] = statistics.getDepartmentName();
            //全部
            allAry[i] = statistics.getNum();

            //未破
            poNoneAry[i] = 0;
            for (PoStatistics poStatistics : poNoneList) {
                if (statistics.getDepartmentId().equals(poStatistics.getDepartmentId())) {
                    poNoneAry[i] = poStatistics.getNum();
                    break;
                }
            }
//            poNoneAry[i] = 0;
            //未破
            for (PoStatistics poStatistics : poHalfList) {
                if (statistics.getDepartmentId().equals(poStatistics.getDepartmentId())) {
                    poHalfAry[i] = poStatistics.getNum();
                    break;
                }
            }

            //半破
            poHalfAry[i] = 0;
            for (PoStatistics poStatistics : poHalfList) {
                if (statistics.getDepartmentId().equals(poStatistics.getDepartmentId())) {
                    poHalfAry[i] = poStatistics.getNum();
                    break;
                }
            }
            //全破
            poAllAry[i] = 0;
            for (PoStatistics poStatistics : poAllList) {
                if (statistics.getDepartmentId().equals(poStatistics.getDepartmentId())) {
                    poAllAry[i] = poStatistics.getNum();
                    break;
                }
            }
            i++;
        }
        map.put("departmentNameAry", departmentNameAry);
        map.put("allAry", allAry);
        map.put("poNoneAry", poNoneAry);
        map.put("poHalfAry", poHalfAry);
        map.put("poAllAry", poAllAry);
        return map;
    }

    /**
     * 根据时间段获取破题统计
     *
     * @param startTime    开始时间
     * @param endTime      结束时间
     * @param departmentId 部门id
     * @return
     */
    public Map<String, Object> getByTimeStatisticsData(Long startTime, Long endTime, Integer departmentId, Integer roleId) {

        Map<String, Object> map = new HashMap<>();

        Date start = DateTimeHelper.setDateField(new Date(startTime), 0, 0, 0);
        Date end = DateTimeHelper.setDateField(new Date(endTime), 23, 59, 59);

        /**type 1未破   2 半破   3 全破 */
        //此时间段未破项目
        List<PoStatistics> poNoneList = projectMapper.getByTime(start, end, departmentId, 1, roleId);
        //未破项目总数量
        Integer poNoneTotalNum = 0;
        for (PoStatistics statistics : poNoneList) {
            poNoneTotalNum += statistics.getNum();
        }
        //此时间段半破项目
        List<PoStatistics> poHalfList = projectMapper.getByTime(start, end, departmentId, 2, roleId);
        //半破项目总数量
        Integer poHalfTotalNum = 0;
        for (PoStatistics statistics : poHalfList) {
            poHalfTotalNum += statistics.getNum();
        }
        //此时间段全破项目
        List<PoStatistics> poAllList = projectMapper.getByTime(start, end, departmentId, 3, roleId);
        //全破项目总数量
        Integer poAllTotalNum = 0;
        for (PoStatistics statistics : poAllList) {
            poAllTotalNum += statistics.getNum();
        }

        map.put("poNoneTotalNum", poNoneTotalNum);
        map.put("poHalfTotalNum", poHalfTotalNum);
        map.put("poAllTotalNum", poAllTotalNum);
        return map;
    }


    /**
     * 传递卡 外部项目筛选
     *
     * @param userId 用户id
     * @return
     */
    public Set<Project> getWorkDiaryPro(Integer userId,Integer roleId) {
        return projectMapper.getWorkDiaryPro(userId,roleId);
    }

    public void delProject(Integer projectId){
        projectMapper.delProject(projectId);
    }

}
