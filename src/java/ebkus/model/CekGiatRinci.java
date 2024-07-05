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
public class CekGiatRinci {

    private int no;
    private String idGiat;
    private String tahun;
    private String kodeUnit;
    private BigDecimal paguGiat;
    private BigDecimal sumGiatRinci;
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

    public BigDecimal getSumGiatRinci() {
        return sumGiatRinci;
    }

    public void setSumGiatRinci(BigDecimal sumGiatRinci) {
        this.sumGiatRinci = sumGiatRinci;
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

}
