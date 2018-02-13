package com.magic.ereal.business.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * 内部项目 台帐 导出
 * Created by Eric Xie on 2017/7/11 0011.
 */
public class ExcelProjectInterior implements Serializable {

    /** 项目ID */
    private Integer projectId;

    /** 项目编号 */
    private String projectNumber;

    /** 启动时间 */
    private Date startTime;

    /** 项目名称 */
    private String projectName;

    /** 初始工作 */
    private int initWorkload;

    /** 项目经理名字 */
    private String projectManagerName;

    /** 直接汇报人 */
    private String directReportName;

    /** 最新的 项目阶段验收结果 */
    private ExcelProjectInteriorNewReport newReport;

    /** 验收次数 */
    private List<ProjectInteriorWeekAcceptance> weekAcceptances;


    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectNumber() {
        return projectNumber;
    }

    public void setProjectNumber(String projectNumber) {
        this.projectNumber = projectNumber;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getInitWorkload() {
        return initWorkload;
    }

    public void setInitWorkload(int initWorkload) {
        this.initWorkload = initWorkload;
    }

    public String getProjectManagerName() {
        return projectManagerName;
    }

    public void setProjectManagerName(String projectManagerName) {
        this.projectManagerName = projectManagerName;
    }

    public String getDirectReportName() {
        return directReportName;
    }

    public void setDirectReportName(String directReportName) {
        this.directReportName = directReportName;
    }

    public ExcelProjectInteriorNewReport getNewReport() {
        return newReport;
    }

    public void setNewReport(ExcelProjectInteriorNewReport newReport) {
        this.newReport = newReport;
    }

    public List<ProjectInteriorWeekAcceptance> getWeekAcceptances() {
        return weekAcceptances;
    }

    public void setWeekAcceptances(List<ProjectInteriorWeekAcceptance> weekAcceptances) {
        this.weekAcceptances = weekAcceptances;
    }
}
