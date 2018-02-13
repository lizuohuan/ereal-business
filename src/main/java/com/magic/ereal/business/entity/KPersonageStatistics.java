package com.magic.ereal.business.entity;

import java.io.Serializable;

/**
 * K 值统计(个人) -- 实体
 * @author lzh
 * @create 2017/5/17 11:10
 */
public class KPersonageStatistics implements Serializable {

    /** 部门名 */
    private String departmentName;

    /** 员工名 */
    private String name;

    /** k外 */
    private Double kw;

    /** k内 */
    private Double kn;

    /** k临时 */
    private Double kl;

    /** k常规 */
    private Double kc;

    /** 个人总k */
    private Double kt;

    /** 本月k目标 */
    private Double kmb;

    /** 个人换算系数 */
    private Double cr;

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getKw() {
        if (null == kw) {
            return 0.0;
        }
        return kw;
    }

    public void setKw(Double kw) {
        this.kw = kw;
    }

    public Double getKn() {
        if (null == kn) {
            return 0.0;
        }
        return kn;
    }

    public void setKn(Double kn) {
        this.kn = kn;
    }

    public Double getKl() {
        if (null == kl) {
            return 0.0;
        }
        return kl;
    }

    public void setKl(Double kl) {
        this.kl = kl;
    }

    public Double getKc() {
        if (null == kc) {
            return 0.0;
        }
        return kc;
    }

    public void setKc(Double kc) {
        this.kc = kc;
    }

    public Double getKt() {
        if (null == kt) {
            return 0.0;
        }
        return kt;
    }

    public void setKt(Double kt) {
        this.kt = kt;
    }

    public Double getKmb() {
        if (null == kmb) {
            return 0.0;
        }
        return kmb;
    }

    public void setKmb(Double kmb) {
        this.kmb = kmb;
    }

    public Double getCr() {
        if (null == cr) {
            return 1.0;
        }
        return cr;
    }

    public void setCr(Double cr) {
        this.cr = cr;
    }
}
