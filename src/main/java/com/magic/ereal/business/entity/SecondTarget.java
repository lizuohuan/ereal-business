package com.magic.ereal.business.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 二维数据统计  目标指标配置
 * Created by Eric Xie on 2017/5/22 0022.
 */
public class SecondTarget implements Serializable {

    /**主键ID*/
    private Integer id;

    /**部门ID*/
    private Integer departmentId;

    /** 部门名称 */
    private String departmentName;

    /** 公司名称 */
    private String companyName;

    /**指标名称*/
    private String targetName;

    /**指标权重*/
    private Double targetWeight;

    /**指标值 描述*/
    private String targetMsg;

    /**创建人*/
    private Integer createUserId;

    /**创建人 名字*/
    private String userName;

    /**创建时间*/
    private Date createTime;

    /**针对月份*/
    private  Date targetTime;

    /**针对月份 时间戳*/
    private  Long targetTimeL;

    /**当项目ID 是 项目团队时，应有此字段 可为空 缺省：1*/
    private Double targetNum;

    /**是否 通过本月考核   0：没有通过 1：通过  缺省值 0*/
    private Integer isApproved;

    /**是否是 项目部 0不是  1是*/
    private Integer isProjectDepartment;

    /**计算的公式  如果为1 则不用评价分 为2时 需要用 评分*/
    private Integer method;

    /**开始时间*/
    private Date startTime;

    /** 结束时间 */
    private Date endTime;

    /**总经理评分*/
    private Double managerGrade;

    /**值总评分*/
    private Double dutyGrade;

    /** 第二维 最新打分 */
    private Double score;

    /** 公司ID */
    private Integer companyId;

    /** 通过时间 */
    protected Date approvedTime;

    /** 打分时间 */
    private Date scoreTime;

    public Date getScoreTime() {
        return scoreTime;
    }

    public void setScoreTime(Date scoreTime) {
        this.scoreTime = scoreTime;
    }

    public Date getApprovedTime() {
        return approvedTime;
    }

    public void setApprovedTime(Date approvedTime) {
        this.approvedTime = approvedTime;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Double getManagerGrade() {
        return managerGrade;
    }

    public void setManagerGrade(Double managerGrade) {
        this.managerGrade = managerGrade;
    }

    public Double getDutyGrade() {
        return dutyGrade;
    }

    public void setDutyGrade(Double dutyGrade) {
        this.dutyGrade = dutyGrade;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
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

    public Integer getMethod() {
        return method;
    }

    public void setMethod(Integer method) {
        this.method = method;
    }

    public Integer getIsProjectDepartment() {
        return isProjectDepartment;
    }

    public void setIsProjectDepartment(Integer isProjectDepartment) {
        this.isProjectDepartment = isProjectDepartment;
    }

    public Integer getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(Integer isApproved) {
        this.isApproved = isApproved;
    }

    public Double getTargetNum() {
        return targetNum;
    }

    public void setTargetNum(Double targetNum) {
        this.targetNum = targetNum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public Double getTargetWeight() {
        return targetWeight;
    }

    public void setTargetWeight(Double targetWeight) {
        this.targetWeight = targetWeight;
    }

    public String getTargetMsg() {
        return targetMsg;
    }

    public void setTargetMsg(String targetMsg) {
        this.targetMsg = targetMsg;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getTargetTime() {
        return targetTime;
    }

    public void setTargetTime(Date targetTime) {
        this.targetTime = targetTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getTargetTimeL() {
        return targetTimeL;
    }

    public void setTargetTimeL(Long targetTimeL) {
        this.targetTimeL = targetTimeL;
    }
}
