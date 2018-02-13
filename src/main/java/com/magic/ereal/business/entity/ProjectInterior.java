package com.magic.ereal.business.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 内部项目 -- 实体
 * @author lzh
 * @create 2017/4/27 17:10
 */
public class ProjectInterior implements Serializable {

    private Integer id;
    /** 项目编号 */
    private String projectNumber;
    /** 项目名 */
    private String projectName;
    /** 项目简称 */
    private String shortName;
    /** 专业id */
    private Integer projectMajorId;

    /** 初始工作量 暂定单位 天 */
    private Double initWorkload;

    /** 项目组ID */
    private Integer projectGroupId;
    /** 分配到的部门/团队ID */
    private Integer departmentId;
    /** 项目启动时间 */
    private Date startTime;
    /** 截止时间 */
    private Date endTime;
    /** 审核人（值总） */
    private Integer reviewerUserId;
    /** 被推送人（团队长）ID */
    private Integer allocationUserId;
    /** 审核时间 */
    private Date reviewerTime;
    /** 项目分配时间 */
    private Date allocationTime;
    /** 创建者用户（绩效专员）ID */
    private Integer createUserId;
    /** 是否对内 0：对内  1：对外 */
    private Integer atHome;
    /**创建时间*/
    private Date createTime;
    /**最后更新时间*/
    private Date updateTime;
    /** 项目状态 0 : 审核中（绩效专员选择团队）  1：审核通过（通过后团队长分配项目组）  2 ： 审核失败 */
    private Integer status;
    /** 备注 */
    private String remark;
    /** 直接汇报人id */
    private Integer directReportPersonUserId;
    //业务
    /** 项目专业名 */
    private String projectMajorName;
    /** 项目组名 */
    private String projectGroupName;
    /** 部门对象 */
    private Department department;
    /** 审核人（值总）对象 */
    private User reviewerUser;
    /** 创建者用户（绩效专员）对象 */
    private User createUser;
    /** 被推送人（团队长）ID 对象*/
    private User allocationUser;

    /** 直接汇报人 */
    private String directReportPersonUserName;
    /**项目周验收 数据集合*/
    private List<ProjectInteriorWeekAcceptance> acceptances;
    /**全项目组人员集合 包括项目经理，可通过 项目经理ID 确认项目经理*/
    private List<User> members;

    /**总用时*/
    private Double useTime;

    /**项目经理 名称*/
    private String projectManagerName;

    /**效率参数 初始化为 0*/
    private Double efficiency;

    /**团队名称*/
    private String departmentName;

    /**参与该项目用户 用户K值集合*/
    private List<UserK> userKs;

    /**C导师名字*/
    private String cTeacherName;

    /**项目是否结项 0:没有结项 1:已经结项*/
    private Integer projectStatus;
    /**结项时间*/
    private Date overTime;

    /** 公司ID */
    private Integer companyId;

    /** 是否终止项目  0:否  1:是  缺省值  0 */
    private Integer isTerminate;
    private Integer isValid;

    public Integer getIsValid() {
        return isValid;
    }

    public ProjectInterior setIsValid(Integer isValid) {
        this.isValid = isValid;
        return this;
    }

    public Integer getIsTerminate() {
        return isTerminate;
    }

    public ProjectInterior setIsTerminate(Integer isTerminate) {
        this.isTerminate = isTerminate;
        return this;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Date getOverTime() {
        return overTime;
    }

    public void setOverTime(Date overTime) {
        this.overTime = overTime;
    }

    public Integer getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(Integer projectStatus) {
        this.projectStatus = projectStatus;
    }

    public String getcTeacherName() {
        return cTeacherName;
    }

    public void setcTeacherName(String cTeacherName) {
        this.cTeacherName = cTeacherName;
    }

    public List<UserK> getUserKs() {
        return userKs;
    }

    public void setUserKs(List<UserK> userKs) {
        this.userKs = userKs;
    }

    public Double getEfficiency() {
        return null == efficiency ? 0.0 : efficiency;
    }

    public void setEfficiency(Double efficiency) {
        this.efficiency = efficiency;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getProjectManagerName() {
        return projectManagerName;
    }

    public void setProjectManagerName(String projectManagerName) {
        this.projectManagerName = projectManagerName;
    }

    public Double getUseTime() {
        return useTime;
    }

    public void setUseTime(Double useTime) {
        this.useTime = useTime;
    }

    public List<ProjectInteriorWeekAcceptance> getAcceptances() {
        return acceptances;
    }

    public void setAcceptances(List<ProjectInteriorWeekAcceptance> acceptances) {
        this.acceptances = acceptances;
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

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Integer getProjectMajorId() {
        return projectMajorId;
    }

    public void setProjectMajorId(Integer projectMajorId) {
        this.projectMajorId = projectMajorId;
    }

    public Double getInitWorkload() {
        return initWorkload;
    }

    public void setInitWorkload(Double initWorkload) {
        this.initWorkload = initWorkload;
    }

    public Integer getProjectGroupId() {
        return projectGroupId;
    }

    public void setProjectGroupId(Integer projectGroupId) {
        this.projectGroupId = projectGroupId;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getReviewerUserId() {
        return reviewerUserId;
    }

    public void setReviewerUserId(Integer reviewerUserId) {
        this.reviewerUserId = reviewerUserId;
    }

    public Integer getAllocationUserId() {
        return allocationUserId;
    }

    public void setAllocationUserId(Integer allocationUserId) {
        this.allocationUserId = allocationUserId;
    }

    public Date getReviewerTime() {
        return reviewerTime;
    }

    public void setReviewerTime(Date reviewerTime) {
        this.reviewerTime = reviewerTime;
    }

    public Date getAllocationTime() {
        return allocationTime;
    }

    public void setAllocationTime(Date allocationTime) {
        this.allocationTime = allocationTime;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Integer getAtHome() {
        return atHome;
    }

    public void setAtHome(Integer atHome) {
        this.atHome = atHome;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getProjectMajorName() {
        return projectMajorName;
    }

    public void setProjectMajorName(String projectMajorName) {
        this.projectMajorName = projectMajorName;
    }

    public String getProjectGroupName() {
        return projectGroupName;
    }

    public void setProjectGroupName(String projectGroupName) {
        this.projectGroupName = projectGroupName;
    }

    public Department getDepartment() {
        return department;
    }

    public User getReviewerUser() {
        return reviewerUser;
    }

    public void setReviewerUser(User reviewerUser) {
        this.reviewerUser = reviewerUser;
    }

    public User getCreateUser() {
        return createUser;
    }

    public void setCreateUser(User createUser) {
        this.createUser = createUser;
    }

    public User getAllocationUser() {
        return allocationUser;
    }

    public void setAllocationUser(User allocationUser) {
        this.allocationUser = allocationUser;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getDirectReportPersonUserId() {
        return directReportPersonUserId;
    }

    public void setDirectReportPersonUserId(Integer directReportPersonUserId) {
        this.directReportPersonUserId = directReportPersonUserId;
    }

    public String getDirectReportPersonUserName() {
        return directReportPersonUserName;
    }

    public void setDirectReportPersonUserName(String directReportPersonUserName) {
        this.directReportPersonUserName = directReportPersonUserName;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProjectInterior that = (ProjectInterior) o;

        return id.equals(that.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}