package com.magic.ereal.business.entity;

import java.io.Serializable;

/**
 *
 * Created by Eric Xie on 2017/5/18 0018.
 */
public class KStatistics implements Serializable {


    /**K常规*/
    private Double kGeneral;

    /**K临时*/
    private Double kTemp;

    /**K内*/
    private Double kInterior;

    /**K外*/
    private Double kExterior;

    /**K完成率*/
    private Double kFinishRate;


    public Double getkGeneral() {
        return kGeneral;
    }

    public void setkGeneral(Double kGeneral) {
        this.kGeneral = kGeneral;
    }

    public Double getkTemp() {
        return kTemp;
    }

    public void setkTemp(Double kTemp) {
        this.kTemp = kTemp;
    }

    public Double getkInterior() {
        return kInterior;
    }

    public void setkInterior(Double kInterior) {
        this.kInterior = kInterior;
    }

    public Double getkExterior() {
        return kExterior;
    }

    public void setkExterior(Double kExterior) {
        this.kExterior = kExterior;
    }

    public Double getkFinishRate() {
        return kFinishRate;
    }

    public void setkFinishRate(Double kFinishRate) {
        this.kFinishRate = kFinishRate;
    }
}
