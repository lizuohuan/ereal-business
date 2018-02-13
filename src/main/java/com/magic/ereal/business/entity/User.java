package com.magic.ereal.business.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 实体 -- 用户
 * @author lzh
 * @create 2017/4/17 11:14
 */
public class User implements Serializable {

    private Integer id;

    /** 账号 */
    private String account;

    /** 密码 */
    private String password;

    /** 姓名 */
    private String name;

    /** 转正时间 */
    private Date positiveTime;

    /** 工资 */
    private Double salary;

    /** 角色id */
    private Integer roleId;

    /**是否是 经理级别角色*/
    private Integer isManager;

    /** 是否是 C导师  0:否  1:是 */
    private Integer isCTeacher;

    /** 头像 */
    private String avatar;

    /** 年龄 */
    private Integer age;

    /** 性别 0：男 1：女 */
    private Integer sex;

    /** 爱好 */
    private String hobby;

    /** 身份属性(在职状态)（0实习，1磨合期，2正式，3离职） */
    private Integer incumbency;

    /** 创建时间 */
    private Date createTime;

    /** 更新时间 */
    private Date updateTime;

    /** 邮箱 */
    private String email;

    /** 手机号 */
    private String phone;

    /**请求令牌*/
    private String token;

    /**设备类型 0:android 1:ios*/
    private Integer deviceType;

    /**极光推送的token*/
    private String deviceToken;

    /**最后登录时间*/
    private Date lastLoginTime;

    /** 生日 */
    private Date birthday;

    /** 入职时间 */
    private Date entryTime;

    /** 部门id */
    private Integer departmentId;

    /** 部门名 */
    private String departmentName;

    /** 角色名 */
    private String roleName;

    /** 公司名 */
    private String companyName;

    /** 公司id */
    private Integer companyId;

    /** 用户类型 0：总公司用户  1：分公司用户*/
    private Integer type;

    /**是否是 第一次登录  0：不是  1：是*/
    private Integer isFirst;

    /** 实习期工资 */
    private Double practiceSalary;

    /** 离职时间 */
    private Date resignTime;

    /**用户对应的角色集合 - 后台登录时用*/
    private List<Role> roles;

    /** 多角色ID集合 */
    private String roleIds;

    /** 生日消息 Banner  在接口 getInfo中使用 */
    private Banner banner;

    /** 个人K可比 */
    private Double kkb;

    /** 统计月度K王用 */
    private Double totalK;

    /** 所在的部门 是否是 项目部门  0：不是  1：是 */
    private Integer isProjectDepartment;

    /** 员工信息状态  0:合作  1:股东 */
    private Integer infoStatus;

    /** 离职时间 */
    private Date dimissionTime;

    public Date getDimissionTime() {
        return dimissionTime;
    }

    public User setDimissionTime(Date dimissionTime) {
        this.dimissionTime = dimissionTime;
        return this;
    }

    public Integer getInfoStatus() {
        return infoStatus;
    }

    public User setInfoStatus(Integer infoStatus) {
        this.infoStatus = infoStatus;
        return this;
    }

    public Integer getIsProjectDepartment() {
        return isProjectDepartment;
    }

    public void setIsProjectDepartment(Integer isProjectDepartment) {
        this.isProjectDepartment = isProjectDepartment;
    }

    public Double getTotalK() {
        return totalK;
    }

    public void setTotalK(Double totalK) {
        this.totalK = totalK;
    }

    public Double getKkb() {
        return kkb;
    }
    public void setKkb(Double kkb) {
        this.kkb = kkb;
    }

    public Banner getBanner() {
        return banner;
    }

    public void setBanner(Banner banner) {
        this.banner = banner;
    }

    public Integer getIsCTeacher() {
        return isCTeacher;
    }

    public void setIsCTeacher(Integer isCTeacher) {
        this.isCTeacher = isCTeacher;
    }

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Integer getIsFirst() {
        return isFirst;
    }

    public void setIsFirst(Integer isFirst) {
        this.isFirst = isFirst;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getPositiveTime() {
        return positiveTime;
    }

    public void setPositiveTime(Date positiveTime) {
        this.positiveTime = positiveTime;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public Integer getIncumbency() {
        return incumbency;
    }

    public void setIncumbency(Integer incumbency) {
        this.incumbency = incumbency;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
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

    public Integer getIsManager() {
        return isManager;
    }

    public void setIsManager(Integer isManager) {
        this.isManager = isManager;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Double getPracticeSalary() {
        return practiceSalary;
    }

    public void setPracticeSalary(Double practiceSalary) {
        this.practiceSalary = practiceSalary;
    }

    public Date getResignTime() {
        return resignTime;
    }

    public void setResignTime(Date resignTime) {
        this.resignTime = resignTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return id.equals(user.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}

