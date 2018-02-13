package com.magic.ereal.business.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 最新一次内部项目 数据
 * Created by Eric Xie on 2017/7/11 0011.
 */
public class ExcelProjectInteriorNewReport implements Serializable {

    /** 进度 */
    private double progress;

    /** P值 */
    private double p;

    /** A值 */
    private double a;

    /** N值 */
    private double n;

    /** E值 */
    private double e;

    /** L值 */
    private double l;

    /** 类型 */
    private String type;

    /** 质量系数 */
    private double quality;

    /** 用户阶段 K值 用时 */
    private List<ExcelUserK> excelUserKs;

    public double getProgress() {
        return progress;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }

    public double getP() {
        return p;
    }

    public void setP(double p) {
        this.p = p;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getN() {
        return n;
    }

    public void setN(double n) {
        this.n = n;
    }

    public double getE() {
        return e;
    }

    public void setE(double e) {
        this.e = e;
    }

    public double getL() {
        return l;
    }

    public void setL(double l) {
        this.l = l;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getQuality() {
        return quality;
    }

    public void setQuality(double quality) {
        this.quality = quality;
    }

    public List<ExcelUserK> getExcelUserKs() {
        return excelUserKs;
    }

    public void setExcelUserKs(List<ExcelUserK> excelUserKs) {
        this.excelUserKs = excelUserKs;
    }
}
