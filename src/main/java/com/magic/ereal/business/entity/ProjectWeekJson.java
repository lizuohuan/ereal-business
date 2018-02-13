package com.magic.ereal.business.entity;

/**
 * 外部项目周验收 各阶段进度
 * @author lzh
 * @create 2017/5/3 18:08
 */
public class ProjectWeekJson {

    private Integer sectionId;

    /** 阶段一 进度 */
    private Double schedule;

    /** 阶段一 质量 */
    private Double quality;

    /** 阶段一 增量k值 */
    private Double k;

    /** 阶段一 k值 */
    private Double sk;

    /**阶段值  第一阶段 ... 值： 1 2 3 4*/
    private Integer sectionNum;


    public Double getSk() {
        return sk;
    }

    public void setSk(Double sk) {
        this.sk = sk;
    }

    public Integer getSectionNum() {
        return sectionNum;
    }

    public void setSectionNum(Integer sectionNum) {
        this.sectionNum = sectionNum;
    }

    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    public Double getSchedule() {
        return schedule;
    }

    public void setSchedule(Double schedule) {
        this.schedule = schedule;
    }

    public Double getQuality() {
        return quality;
    }

    public void setQuality(Double quality) {
        this.quality = quality;
    }

    public Double getK() {
        return k;
    }

    public void setK(Double k) {
        this.k = k;
    }
}
