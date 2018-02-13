package com.magic.ereal.business.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 首页banner消息 -- 实体
 * @author lzh
 * @create 2017/5/24 14:52
 */
public class Banner implements Serializable {

    private Integer id;

    /** 图片地址 */
    private String imgUrl;

    /** 备注 */
    private String remarks;

    /** 内容 */
    private String context;

    /** 是否显示： 0：不显示  1：显示 */
    private Integer isShow;

    /** 排序 */
    private Integer order;

    /** 标题 */
    private String  title;

    /** 创建时间 */
    private Date createTime;

    /** 更新时间 */
    private Date updateTime;

    /** 创建人id */
    private Integer createUserId;

    /** 创建人姓名 */
    private String name;

    /** banner类型  1：普通  2：审核  3:生日消息 4:统计页面的banner*/
    private Integer type;

    /** type:2 时 0：待审核 1:已发布 2:不通过*/
    private Integer status;

    /** type:2 status：1,2  审核人id */
    private Integer auditUserId;
    /** 审核人姓名 */
    private String auditUserName;

    /** type:2 时间类型 0：周 1：月 */
    private Integer timeType;

    /** 审核维度类型 0：一维  1:二维 2：三维 */
    private Integer dimensionType;

    /** type:2  所属公司id */
    private Integer companyId;

    /** type:2  所属公司名 */
    private String companyName;

    /** 三维发布数据的ID集合 用户更新关联的三维数据  以ID 数据集合传输 */
    private String userStatisticsIds;

    /** 来源 source 0:源  1:公司Banner */
    private Integer source;

    public String getUserStatisticsIds() {
        return userStatisticsIds;
    }

    public void setUserStatisticsIds(String userStatisticsIds) {
        this.userStatisticsIds = userStatisticsIds;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getAuditUserId() {
        return auditUserId;
    }

    public void setAuditUserId(Integer auditUserId) {
        this.auditUserId = auditUserId;
    }

    public String getAuditUserName() {
        return auditUserName;
    }

    public void setAuditUserName(String auditUserName) {
        this.auditUserName = auditUserName;
    }

    public Integer getTimeType() {
        return timeType;
    }

    public void setTimeType(Integer timeType) {
        this.timeType = timeType;
    }

    public Integer getDimensionType() {
        return dimensionType;
    }

    public void setDimensionType(Integer dimensionType) {
        this.dimensionType = dimensionType;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /** 获取 来源 source 0:源  1:公司Banner */
    public Integer getSource() {
        return this.source;
    }

    /** 设置 来源 source 0:源  1:公司Banner */
    public void setSource(Integer source) {
        this.source = source;
    }
}
