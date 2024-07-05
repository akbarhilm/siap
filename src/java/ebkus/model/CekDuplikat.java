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
public class CekDuplikat {

    private int no;
    private int idGiat;
    private String tahun;
    private String kodeUnit;
    private String noGiat;
    private String kodeUrusan;
    private String kodeReferensi;
    private String kodeBidang;
    private String kodeGiat;
    private String namaGiat;
    private BigDecimal pagu;

    public int getIdGiat() {
        return idGiat;
    }

    public void setIdGiat(int idGiat) {
        this.idGiat = idGiat;
    }

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

    public String getNoGiat() {
        return noGiat;
    }

    public void setNoGiat(String noGiat) {
        this.noGiat = noGiat;
    }

    public String getKodeUrusan() {
        return kodeUrusan;
    }

    public void setKodeUrusan(String kodeUrusan) {
        this.kodeUrusan = kodeUrusan;
    }

    public String getKodeReferensi() {
        return kodeReferensi;
    }

    public void setKodeReferensi(String kodeReferensi) {
        this.kodeReferensi = kodeReferensi;
    }

    public String getKodeBidang() {
        return kodeBidang;
    }

    public void setKodeBidang(String kodeBidang) {
        this.kodeBidang = kodeBidang;
    }

    public String getKodeGiat() {
        return kodeGiat;
    }

    public void setKodeGiat(String kodeGiat) {
        this.kodeGiat = kodeGiat;
    }

    public String getNamaGiat() {
        return namaGiat;
    }

    public void setNamaGiat(String namaGiat) {
        this.namaGiat = namaGiat;
    }

    public BigDecimal getPagu() {
        return pagu;
    }

    public void setPagu(BigDecimal pagu) {
        this.pagu = pagu;
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
