/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.model;

import java.math.BigDecimal;

/**
 *
 * @author Racka
 */
public class CekGiatAkb {

    /**
     * @return the idGiat
     */
    public String getIdGiat() {
        return idGiat;
    }

    /**
     * @param idGiat the idGiat to set
     */
    public void setIdGiat(String idGiat) {
        this.idGiat = idGiat;
    }

    private int no;
    private String idGiat;
    private String tahun;
    private String kodeUnit;
    private BigDecimal paguGiat;
    private BigDecimal sumGiatAkb;
    private BigDecimal selisih;

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getKodeUnit() {
        return kodeUnit;
    }

    public void setKodeUnit(String kodeUnit) {
        this.kodeUnit = kodeUnit;
    }

    public BigDecimal getPaguGiat() {
        return paguGiat;
    }

    public void setPaguGiat(BigDecimal paguGiat) {
        this.paguGiat = paguGiat;
    }

    public BigDecimal getSumGiatAkb() {
        return sumGiatAkb;
    }

    public void setSumGiatAkb(BigDecimal sumGiatAkb) {
        this.sumGiatAkb = sumGiatAkb;
    }

    public BigDecimal getSelisih() {
        return selisih;
    }

    public void setSelisih(BigDecimal selisih) {
        this.selisih = selisih;
    }

    /**
     * @return the no
     */
    public int getNo() {
        return no;
    }

    /**
     * @param no the no to set
     */
    public void setNo(int no) {
        this.no = no;
    }

}
