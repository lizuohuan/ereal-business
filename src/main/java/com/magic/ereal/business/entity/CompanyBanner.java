package com.magic.ereal.business.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 公司banner
 * Created by Eric Xie on 2017/8/21 0021.
 */
public class CompanyBanner implements Serializable {

    private Integer id;

    /**  */
    private String title;

    private String context;

    private String imgUrl;

    private Integer companyId;

    private String companyName;

    /** 类型 0：首页banner  4:统计banner */
    private Integer type;

    private Date createTime;

    private Integer createUserId;

    private String createUserName;


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContext() {
        return this.context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getImgUrl() {
        return this.imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getCompanyId() {
        return this.companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /** 获取 类型 0：首页banner  4:统计banner */
    public Integer getType() {
        return this.type;
    }

    /** 设置 类型 0：首页banner  4:统计banner */
    public void setType(Integer type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateUserId() {
        return this.createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUserName() {
        return this.createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }
}
