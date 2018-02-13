package com.magic.ereal.business.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 外部项目 entity
 * Created by Eric Xie on 2017/4/26 0026.
 */
public class Project implements Serializable {

    /**主键ID*/
    private Integer id;

    /**项目编号*/
    private String projectNumber;

    /**项目名称*/
    private String projectName;

    /**项目简称*/
    private String projectNameShort;

    /**项目类型/课题类型ID*/
    private Integer projectTypeId;

    /**项目类型对象*/
    private ProjectType projectType;
//
//    /**项目类型 0：外部项目  1：内部项目*/
//    private Integer type;

    /**客户单位名称*/
    private String customerUnit;

    /**客户专业部门名称*/
    private String customerDepartment;

    /**承接时间*/
    private Date receiveTime;

    /**项目启动书提交时间*/
    private Date submitTime;

    /**客户定位 备注*/
    private String customerRemarks;

    /**风险说明*/
    private String riskRemarks;

    /**其他市场信息备注*/
    private String otherRemarks;

    /**创建时间*/
    private Date createTime;

    /**创建者*/
    private Integer createUserId;

    /** 最新绩效专员ID */
    private Integer performanceUserId;

    /**更新时间*/
    private Date updateTime;

    /**项目分配时间*/
    private Date allocationTime;

    /**初始工作量*/
    private Double initWorkload;

    /**初始工作量*/
    private Double initK;

    /**分配到的 部门ID*/
    private Integer departmentId;

    /**B导师 用户ID*/
    private Integer bTeacherId;

    /**C导师 用户ID*/
    private Integer cTeacherId;

    /**破题状态  分配时：默认为  未破 5000 详见 常量*/
    private Integer status;

    /**内部结项时间*/
    private Date overTime;


//    /**内部结项 0:内部未结项  1:内部结项*/
//    private Integer isInteriorOver;
//
//    /**外部结项 0:外部未结项  1:外部结项*/
//    private Integer isExteriorOver;

    /**一真定位备注*/
    private String orientationRemarks;

    /**分配者 用户ID*/
    private Integer allocationUserId;

    /**审核人*/
    private Integer reviewerUserId;

    /**审核时间*/
    private Date reviewerTime;

    /**项目组ID*/
    private Integer projectGroupId;

    /**A导师 / 分配给某部门的 部门经理 ID*/
    private Integer aTeacher;

    /** A导师名字 */
    private String aTeacherName;

    /** 交接状态 0：已创建(待分配) 1：已分配 2：未通过 3：通过 */
    private Integer connectStatus;

    /**该项目的周验收数据集合*/
    private List<ProjectWeekAcceptance> acceptances;

    /**该项目 所有参与该项目 获得的K值*/
    private List<UserK> userKs;

    /**项目经理 名称*/
    private String projectManager;

    /**项目类型名称*/
    private String projectTypeName;

    /**外部结项时间*/
    private Date exteriorOverTime;

    /**已用时*/
    private Double useTime;

    /**B导师 姓名*/
    private String bTeacherName;

    /**C导师 姓名*/
    private String cTeacherName;

    /**创建者 外部项目 外部结项 验收人 姓名*/
    private String commissionerName;

    /**效率*/
    private Double efficiency;

    /**当前项目的 总K值*/
    private Double totalK;

    /**值总备注*/
    private String dutyRemarks;

    /**部门名称*/
    private String departmentName;

    /** 项目组名字 */
    private String projectGroupName;

    /** 半破时间 */
    private Date poHalfTime;

   /** 全破时间 */
    private Date poAllTime;

    /** 验收进展状态列表 （内部验收、外部验收、周验收） */
    private List<Acceptance> acceptanceList;

    /**项目进度*/
    private Double progress;

    /** 启动时间 */
    private Date startTime;

    /** 是否终止项目  0:否  1:是  缺省值  0 */
    private Integer isTerminate;


    private Integer isValid;

    public Integer getIsTerminate() {
        return isTerminate;
    }

    public Project setIsTerminate(Integer isTerminate) {
        this.isTerminate = isTerminate;
        return this;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Integer getPerformanceUserId() {
        return performanceUserId;
    }

    public void setPerformanceUserId(Integer performanceUserId) {
        this.performanceUserId = performanceUserId;
    }

    public String getCommissionerName() {
        return commissionerName;
    }

    public void setCommissionerName(String commissionerName) {
        this.commissionerName = commissionerName;
    }

    public Double getInitK() {
        return initK;
    }

    public void setInitK(Double initK) {
        this.initK = initK;
    }

    public Date getOverTime() {
        return overTime;
    }

    public void setOverTime(Date overTime) {
        this.overTime = overTime;
    }

    public ProjectType getProjectType() {
        return projectType;
    }

    public void setProjectType(ProjectType projectType) {
        this.projectType = projectType;
    }

    public Double getProgress() {
        return progress;
    }

    public void setProgress(Double progress) {
        this.progress = progress;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDutyRemarks() {
        return dutyRemarks;
    }

    public void setDutyRemarks(String dutyRemarks) {
        this.dutyRemarks = dutyRemarks;
    }

    public Double getTotalK() {
        return totalK;
    }

    public void setTotalK(Double totalK) {
        this.totalK = totalK;
    }

    public Double getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(Double efficiency) {
        this.efficiency = efficiency;
    }

    public Double getUseTime() {
        return useTime;
    }

    public void setUseTime(Double useTime) {
        this.useTime = useTime;
    }

    public String getbTeacherName() {
        return bTeacherName;
    }

    public void setbTeacherName(String bTeacherName) {
        this.bTeacherName = bTeacherName;
    }

    public String getcTeacherName() {
        return cTeacherName;
    }

    public void setcTeacherName(String cTeacherName) {
        this.cTeacherName = cTeacherName;
    }

    public Date getExteriorOverTime() {
        return exteriorOverTime;
    }

    public void setExteriorOverTime(Date exteriorOverTime) {
        this.exteriorOverTime = exteriorOverTime;
    }

    public String getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(String projectManager) {
        this.projectManager = projectManager;
    }

    public String getProjectTypeName() {
        return projectTypeName;
    }

    public void setProjectTypeName(String projectTypeName) {
        this.projectTypeName = projectTypeName;
    }

    public List<UserK> getUserKs() {
        return userKs;
    }

    public void setUserKs(List<UserK> userKs) {
        this.userKs = userKs;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectNumber() {
        return projectNumber;
    }

    public void setProjectNumber(String projectNumber) {
        this.projectNumber = projectNumber;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectNameShort() {
        return projectNameShort;
    }

    public void setProjectNameShort(String projectNameShort) {
        this.projectNameShort = projectNameShort;
    }

    public Integer getProjectTypeId() {
        return projectTypeId;
    }

    public void setProjectTypeId(Integer projectTypeId) {
        this.projectTypeId = projectTypeId;
    }


    public String getCustomerUnit() {
        return customerUnit;
    }

    public void setCustomerUnit(String customerUnit) {
        this.customerUnit = customerUnit;
    }

    public String getCustomerDepartment() {
        return customerDepartment;
    }

    public void setCustomerDepartment(String customerDepartment) {
        this.customerDepartment = customerDepartment;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public String getCustomerRemarks() {
        return customerRemarks;
    }

    public void setCustomerRemarks(String customerRemarks) {
        this.customerRemarks = customerRemarks;
    }

    public String getRiskRemarks() {
        return riskRemarks;
    }

    public void setRiskRemarks(String riskRemarks) {
        this.riskRemarks = riskRemarks;
    }

    public String getOtherRemarks() {
        return otherRemarks;
    }

    public void setOtherRemarks(String otherRemarks) {
        this.otherRemarks = otherRemarks;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getAllocationTime() {
        return allocationTime;
    }

    public void setAllocationTime(Date allocationTime) {
        this.allocationTime = allocationTime;
    }

    public Double getInitWorkload() {
        return initWorkload;
    }

    public void setInitWorkload(Double initWorkload) {
        this.initWorkload = initWorkload;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getbTeacherId() {
        return bTeacherId;
    }

    public void setbTeacherId(Integer bTeacherId) {
        this.bTeacherId = bTeacherId;
    }

    public Integer getcTeacherId() {
        return cTeacherId;
    }

    public void setcTeacherId(Integer cTeacherId) {
        this.cTeacherId = cTeacherId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getOrientationRemarks() {
        return orientationRemarks;
    }

    public void setOrientationRemarks(String orientationRemarks) {
        this.orientationRemarks = orientationRemarks;
    }

    public Integer getAllocationUserId() {
        return allocationUserId;
    }

    public void setAllocationUserId(Integer allocationUserId) {
        this.allocationUserId = allocationUserId;
    }

    public Integer getReviewerUserId() {
        return reviewerUserId;
    }

    public void setReviewerUserId(Integer reviewerUserId) {
        this.reviewerUserId = reviewerUserId;
    }

    public Date getReviewerTime() {
        return reviewerTime;
    }

    public void setReviewerTime(Date reviewerTime) {
        this.reviewerTime = reviewerTime;
    }

    public Integer getProjectGroupId() {
        return projectGroupId;
    }

    public void setProjectGroupId(Integer projectGroupId) {
        this.projectGroupId = projectGroupId;
    }

    public Integer getaTeacher() {
        return aTeacher;
    }

    public void setaTeacher(Integer aTeacher) {
        this.aTeacher = aTeacher;
    }

    public List<ProjectWeekAcceptance> getAcceptances() {
        return acceptances;
    }

    public void setAcceptances(List<ProjectWeekAcceptance> acceptances) {
        this.acceptances = acceptances;
    }

    public Integer getConnectStatus() {
        return connectStatus;
    }

    public void setConnectStatus(Integer connectStatus) {
        this.connectStatus = connectStatus;
    }

    public List<Acceptance> getAcceptanceList() {
        return acceptanceList;
    }

    public void setAcceptanceList(List<Acceptance> acceptanceList) {
        this.acceptanceList = acceptanceList;
    }


    public String getProjectGroupName() {
        return projectGroupName;
    }

    public void setProjectGroupName(String projectGroupName) {
        this.projectGroupName = projectGroupName;
    }

    public Date getPoAllTime() {
        return poAllTime;
    }

    public void setPoAllTime(Date poAllTime) {
        this.poAllTime = poAllTime;
    }

    public Date getPoHalfTime() {
        return poHalfTime;
    }

    public void setPoHalfTime(Date poHalfTime) {
        this.poHalfTime = poHalfTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Project project = (Project) o;

        return id.equals(project.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    /**B导师 用户ID*/
    public Integer getBTeacherId() {
        return this.bTeacherId;
    }

    /**B导师 用户ID*/
    public void setBTeacherId(Integer bTeacherId) {
        this.bTeacherId = bTeacherId;
    }

    /**C导师 用户ID*/
    public Integer getCTeacherId() {
        return this.cTeacherId;
    }

    /**C导师 用户ID*/
    public void setCTeacherId(Integer cTeacherId) {
        this.cTeacherId = cTeacherId;
    }

    /**A导师 / 分配给某部门的 部门经理 ID*/
    public Integer getATeacher() {
        return this.aTeacher;
    }

    /**A导师 / 分配给某部门的 部门经理 ID*/
    public void setATeacher(Integer aTeacher) {
        this.aTeacher = aTeacher;
    }

    /** 获取 A导师名字 */
    public String getATeacherName() {
        return this.aTeacherName;
    }

    /** 设置 A导师名字 */
    public void setATeacherName(String aTeacherName) {
        this.aTeacherName = aTeacherName;
    }

    /**B导师 姓名*/
    public String getBTeacherName() {
        return this.bTeacherName;
    }

    /**B导师 姓名*/
    public void setBTeacherName(String bTeacherName) {
        this.bTeacherName = bTeacherName;
    }

    /**C导师 姓名*/
    public String getCTeacherName() {
        return this.cTeacherName;
    }

    /**C导师 姓名*/
    public void setCTeacherName(String cTeacherName) {
        this.cTeacherName = cTeacherName;
    }

    public Integer getIsValid() {
        return this.isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }
}
