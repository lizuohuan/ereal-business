package com.magic.ereal.business.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 导出外部项目 entity
 * Created by Eric Xie on 2018/1/3 0003.
 */
public class ExportProject implements Serializable {

    /** 项目ID */
    private Integer id;

    /** 客户公司名称 */
    private String companyName;

    /** 课题类型 */
    private String projectTypeName;

    /** 客户部门名称 */
    private String customDepartmentName;

    /** 项目名称 */
    private String projectName;

    /** 项目简称 */
    private String projectShortName;

    /** 客户定位 */
    private String location;

    /** 项目分配部门 */
    private String departmentName;

    /** 项目经理 */
    private String projectManagerName;

    /** A导师名字 */
    private String aTeacherName;

    /** B导师名字*/
    private String bTeacherName;

    /** C导师名字 */
    private String cTeacherName;

    /** 项目开始日期 */
    private Date startTime;

    /** 结项日期 */
    private Date finishTime;

    private Integer initWorkload;

    private Double initK;

    /** 此项目类型的四个阶段 */
    private List<ProjectTypeSection> sections;

    /** 项目状态 */
    private Integer status;

    /** 破题质量分 */
    private Double poScore;

    /** 破题时间 */
    private Date poTime;

    /** 内部结项质量分 */
    private Double internalProjectScore;

    /** 内部结项日期 */
    private Date internalProjectDate;

    /** 项目总进度 */
    private Double scheduleProject;

    /** 项目结项质量分 */
    private Double qualityScore;

    /** 项目当前总K值 */
    private Double totalK;

    /** 项目当前总用时 */
    private Double totalH;

    /** 项目当前总效率 */
    private Double totalEfficiency;

    /**  项目效率及质量实时监控 成员 */
    private List<ExportProjectControl> exportProjectControlList;

    /**  项目效率及质量实时监控 成员 */
    private List<ExportProjectControl> otherProjectControlList;

    private Integer count;

    /** A导师 */
    private ExportProjectControl AExportProjectControl;

    /** 项目经理 */
    private ExportProjectControl PMExportProjectControl;

    /** 外部项目 内、外部结项记录 */
    private List<ProjectAcceptanceRecord> projectRecordList;

    /** 项目完结记录 */
    private ExportProjectK finishRecord;

    /** 导出记录 */
    private List<ExportProjectK> otherProjectRecordList;

    /** 记录剩余条数 */
    private Integer recordCount;


    /** 外部项目 周验收记录 */
    private List<ProjectWeekAcceptance> weekAcceptanceList;

    /** 导出周验收记录 */
    private List<ProjectWeekExport> projectWeekExports;


    public String getCompanyName() {
        return this.companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * 获取 课题类型
     */
    public String getProjectTypeName() {
        return this.projectTypeName;
    }

    /**
     * 设置 课题类型
     */
    public void setProjectTypeName(String projectTypeName) {
        this.projectTypeName = projectTypeName;
    }

    /**
     * 获取 客户部门名称
     */
    public String getCustomDepartmentName() {
        return this.customDepartmentName;
    }

    /**
     * 设置 客户部门名称
     */
    public void setCustomDepartmentName(String customDepartmentName) {
        this.customDepartmentName = customDepartmentName;
    }

    public String getProjectName() {
        return this.projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * 获取 客户定位
     */
    public String getLocation() {
        return this.location;
    }

    /**
     * 设置 客户定位
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * 获取 项目分配部门
     */
    public String getDepartmentName() {
        return this.departmentName;
    }

    /**
     * 设置 项目分配部门
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    /**
     * 获取 项目经理
     */
    public String getProjectManagerName() {
        return this.projectManagerName;
    }

    /**
     * 设置 项目经理
     */
    public void setProjectManagerName(String projectManagerName) {
        this.projectManagerName = projectManagerName;
    }

    public String getATeacherName() {
        return this.aTeacherName;
    }

    public void setATeacherName(String aTeacherName) {
        this.aTeacherName = aTeacherName;
    }

    public String getBTeacherName() {
        return this.bTeacherName;
    }

    public void setBTeacherName(String bTeacherName) {
        this.bTeacherName = bTeacherName;
    }

    public String getCTeacherName() {
        return this.cTeacherName;
    }

    public void setCTeacherName(String cTeacherName) {
        this.cTeacherName = cTeacherName;
    }

    public Date getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /** 获取 结项日期 */
    public Date getFinishTime() {
        return this.finishTime;
    }

    /** 设置 结项日期 */
    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public Integer getInitWorkload() {
        return this.initWorkload;
    }

    public void setInitWorkload(Integer initWorkload) {
        this.initWorkload = initWorkload;
    }

    public Double getInitK() {
        return this.initK;
    }

    public void setInitK(Double initK) {
        this.initK = initK;
    }

    /** 获取 此项目类型的四个阶段 */
    public List<ProjectTypeSection> getSections() {
        return this.sections;
    }

    /** 设置 此项目类型的四个阶段 */
    public void setSections(List<ProjectTypeSection> sections) {
        this.sections = sections;
    }

    /** 获取 项目状态 */
    public Integer getStatus() {
        return this.status;
    }

    /** 设置 项目状态 */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /** 获取 破题质量分 */
    public Double getPoScore() {
        return this.poScore;
    }

    /** 设置 破题质量分 */
    public void setPoScore(Double poScore) {
        this.poScore = poScore;
    }

    /** 获取 破题时间 */
    public Date getPoTime() {
        return this.poTime;
    }

    /** 设置 破题时间 */
    public void setPoTime(Date poTime) {
        this.poTime = poTime;
    }

    /** 获取 内部结项质量分 */
    public Double getInternalProjectScore() {
        return this.internalProjectScore;
    }

    /** 设置 内部结项质量分 */
    public void setInternalProjectScore(Double internalProjectScore) {
        this.internalProjectScore = internalProjectScore;
    }

    /** 获取 内部结项日期 */
    public Date getInternalProjectDate() {
        return this.internalProjectDate;
    }

    /** 设置 内部结项日期 */
    public void setInternalProjectDate(Date internalProjectDate) {
        this.internalProjectDate = internalProjectDate;
    }

    /** 获取 项目总进度 */
    public Double getScheduleProject() {
        if(null == this.scheduleProject){
            return 0.0;
        }
        return this.scheduleProject;
    }

    /** 设置 项目总进度 */
    public void setScheduleProject(Double scheduleProject) {
        this.scheduleProject = scheduleProject;
    }

    /** 获取 项目结项质量分 */
    public Double getQualityScore() {
        return this.qualityScore;
    }

    /** 设置 项目结项质量分 */
    public void setQualityScore(Double qualityScore) {
        this.qualityScore = qualityScore;
    }

    /** 获取 项目当前总K值 */
    public Double getTotalK() {
        return this.totalK;
    }

    /** 设置 项目当前总K值 */
    public void setTotalK(Double totalK) {
        this.totalK = totalK;
    }

    /** 获取 项目当前总用时 */
    public Double getTotalH() {
        return this.totalH;
    }

    /** 设置 项目当前总用时 */
    public void setTotalH(Double totalH) {
        this.totalH = totalH;
    }

    /** 获取 项目当前总效率 */
    public Double getTotalEfficiency() {
        if(null == this.totalEfficiency){
            return 0.0;
        }
        return this.totalEfficiency;
    }

    /** 设置 项目当前总效率 */
    public void setTotalEfficiency(Double totalEfficiency) {
        this.totalEfficiency = totalEfficiency;
    }

    /** 获取  项目效率及质量实时监控 成员 */
    public List<ExportProjectControl> getExportProjectControlList() {
        return this.exportProjectControlList;
    }

    /** 设置  项目效率及质量实时监控 成员 */
    public void setExportProjectControlList(List<ExportProjectControl> exportProjectControlList) {
        this.exportProjectControlList = exportProjectControlList;
    }

    /** 获取 外部项目 内、外部结项记录 */
    public List<ProjectAcceptanceRecord> getProjectRecordList() {
        return this.projectRecordList;
    }

    /** 设置 外部项目 内、外部结项记录 */
    public void setProjectRecordList(List<ProjectAcceptanceRecord> projectRecordList) {
        this.projectRecordList = projectRecordList;
    }

    /** 获取 外部项目 周验收记录 */
    public List<ProjectWeekAcceptance> getWeekAcceptanceList() {
        return this.weekAcceptanceList;
    }

    /** 设置 外部项目 周验收记录 */
    public void setWeekAcceptanceList(List<ProjectWeekAcceptance> weekAcceptanceList) {
        this.weekAcceptanceList = weekAcceptanceList;
    }

    public String getProjectShortName() {
        return this.projectShortName;
    }

    public void setProjectShortName(String projectShortName) {
        this.projectShortName = projectShortName;
    }

    /** 获取 项目ID */
    public Integer getId() {
        return this.id;
    }

    /** 设置 项目ID */
    public void setId(Integer id) {
        this.id = id;
    }

    /** 获取 A导师 */
    public ExportProjectControl getAExportProjectControl() {
        return this.AExportProjectControl;
    }

    /** 设置 A导师 */
    public void setAExportProjectControl(ExportProjectControl AExportProjectControl) {
        this.AExportProjectControl = AExportProjectControl;
    }

    /** 获取 项目经理 */
    public ExportProjectControl getPMExportProjectControl() {
        return this.PMExportProjectControl;
    }

    /** 设置 项目经理 */
    public void setPMExportProjectControl(ExportProjectControl PMExportProjectControl) {
        this.PMExportProjectControl = PMExportProjectControl;
    }

    /** 获取  项目效率及质量实时监控 成员 */
    public List<ExportProjectControl> getOtherProjectControlList() {
        return this.otherProjectControlList;
    }

    /** 设置  项目效率及质量实时监控 成员 */
    public void setOtherProjectControlList(List<ExportProjectControl> otherProjectControlList) {
        this.otherProjectControlList = otherProjectControlList;
    }

    public Integer getCount() {
        return 4 - (null == otherProjectControlList ? 0 : otherProjectControlList.size());
    }

    public void setCount(Integer count) {
        this.count = count;
    }



    /** 获取 导出记录 */
    public List<ExportProjectK> getOtherProjectRecordList() {
        return this.otherProjectRecordList;
    }

    /** 设置 导出记录 */
    public void setOtherProjectRecordList(List<ExportProjectK> otherProjectRecordList) {
        this.otherProjectRecordList = otherProjectRecordList;
    }

    /** 获取 项目完结记录 */
    public ExportProjectK getFinishRecord() {
        return this.finishRecord;
    }

    /** 设置 项目完结记录 */
    public void setFinishRecord(ExportProjectK finishRecord) {
        this.finishRecord = finishRecord;
    }

    /** 获取 记录剩余条数 */
    public Integer getRecordCount() {
        return this.recordCount;
    }

    /** 设置 记录剩余条数 */
    public void setRecordCount(Integer recordCount) {
        this.recordCount = recordCount;
    }

    /** 获取 导出周验收记录 */
    public List<ProjectWeekExport> getProjectWeekExports() {
        return this.projectWeekExports;
    }

    /** 设置 导出周验收记录 */
    public void setProjectWeekExports(List<ProjectWeekExport> projectWeekExports) {
        this.projectWeekExports = projectWeekExports;
    }
}
