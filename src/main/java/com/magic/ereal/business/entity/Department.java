package com.magic.ereal.business.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *  部门 entity
 * Created by Eric Xie on 2017/4/20 0020.
 */
public class Department implements Serializable {

    /**主键*/
    private Integer id;

    /**部门名称*/
    private String departmentName;

    /**所属分公司ID*/
    private Integer companyId;
    private String companyName;

    /** 部门类型  0：平台部门   1：分公司部门 */
    private Integer type;

    /**创建时间*/
    private Date createTime;

    /**部分下所有的员工*/
    private List<User> users;

    /** 是否是职能部门或是项目部门 */
    private Integer isProjectDep;


    /** 部门是否有效  0：无效 1：有效 */
    private Integer isValid;

    /** 团队下的 内部项目列表 导出用 */
    private List<ExcelProjectInterior> interiors;

    /** 该部门下 所有团队中 最大的项目组 团队人数 */
    private int perpons;

    /** 是否是项目部  0:不是项目部 1:是项目部 */
    private Integer isProjectDepartment;


    public int getPerpons() {
        return perpons;
    }

    public void setPerpons(int perpons) {
        this.perpons = perpons;
    }

    public List<ExcelProjectInterior> getInteriors() {
        return interiors;
    }

    public void setInteriors(List<ExcelProjectInterior> interiors) {
        this.interiors = interiors;
    }

    public Integer getIsValid() {
        return isValid;
    }
    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getIsProjectDep() {
        return isProjectDep;
    }

    public void setIsProjectDep(Integer isProjectDep) {
        this.isProjectDep = isProjectDep;
    }

    /** 获取 是否是项目部  0:不是项目部 1:是项目部 */
    public Integer getIsProjectDepartment() {
        return this.isProjectDepartment;
    }

    /** 设置 是否是项目部  0:不是项目部 1:是项目部 */
    public void setIsProjectDepartment(Integer isProjectDepartment) {
        this.isProjectDepartment = isProjectDepartment;
    }
}
