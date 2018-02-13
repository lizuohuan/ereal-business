package com.magic.ereal.business.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 分公司 entity
 * Created by Eric Xie on 2017/4/20 0020.
 */
public class Company implements Serializable {

    /**主键*/
    private Integer id;

    /**分公司名称*/
    private String companyName;

    /**分公司类型 0:所属 平台 1:常规分公司 缺省值 1*/
    private Integer type;

    /**创建时间*/
    private Date createTime;

    /**分公司下所有的部门*/
    private List<Department> departments;

    /**公司下所有的用户*/
    private Set<User> users;

    /**绩效专员集合*/
    private Set<User> performanceEmployees;

    /** 是否有效 */
    private Integer isValid;

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<User> getPerformanceEmployees() {
        return performanceEmployees;
    }

    public void setPerformanceEmployees(Set<User> performanceEmployees) {
        this.performanceEmployees = performanceEmployees;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }
}
