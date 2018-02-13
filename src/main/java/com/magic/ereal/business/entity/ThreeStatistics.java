package com.magic.ereal.business.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 三维统计  -- 列表
 * @author lzh
 * @create 2017/6/1 14:51
 */
public class ThreeStatistics implements Serializable {


    /** 员工名 */
    private String userName;

    /** 部门名 */
    private String departmentName;

    /** 公司名 */
    private String companyName;

    /** 员工ID */
    private String userId;

    /** 部门ID */
    private String departmentId;

    /** 公司ID */
    private String companyId;

    /** 各个指标 */
    private List<ThreeVeidooSta> list;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public List<ThreeVeidooSta> getList() {
        return list;
    }

    public void setList(List<ThreeVeidooSta> list) {
        this.list = list;
    }
}
