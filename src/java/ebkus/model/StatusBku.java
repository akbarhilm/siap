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
public class StatusBku extends BaseModel {

    private Integer id;
    private String tahun;
    private String triwulan;
    private Integer noMohon;
    private String kodeKegiatan;
    private String namaKegiatan;
    private String kodeAkun;
    private String namaAkun;
    private String kodeKomponen;
    private String namaKomponen;
    private BigDecimal kasKeluar;
    private String status;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the tahun
     */
    public String getTahun() {
        return tahun;
    }

    /**
     * @param tahun the tahun to set
     */
    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    /**
     * @return the triwulan
     */
    public String getTriwulan() {
        return triwulan;
    }

    /**
     * @param triwulan the triwulan to set
     */
    public void setTriwulan(String triwulan) {
        this.triwulan = triwulan;
    }

    /**
     * @return the noMohon
     */
    public Integer getNoMohon() {
        return noMohon;
    }

    /**
     * @param noMohon the noMohon to set
     */
    public void setNoMohon(Integer noMohon) {
        this.noMohon = noMohon;
    }

    /**
     * @return the kodeKegiatan
     */
    public String getKodeKegiatan() {
        return kodeKegiatan;
    }

    /**
     * @param kodeKegiatan the kodeKegiatan to set
     */
    public void setKodeKegiatan(String kodeKegiatan) {
        this.kodeKegiatan = kodeKegiatan;
    }

    /**
     * @return the namaKegiatan
     */
    public String getNamaKegiatan() {
        return namaKegiatan;
    }

    /**
     * @param namaKegiatan the namaKegiatan to set
     */
    public void setNamaKegiatan(String namaKegiatan) {
        this.namaKegiatan = namaKegiatan;
    }

    /**
     * @return the kodeAkun
     */
    public String getKodeAkun() {
        return kodeAkun;
    }

    /**
     * @param kodeAkun the kodeAkun to set
     */
    public void setKodeAkun(String kodeAkun) {
        this.kodeAkun = kodeAkun;
    }

    /**
     * @return the namaAkun
     */
    public String getNamaAkun() {
        return namaAkun;
    }

    /**
     * @param namaAkun the namaAkun to set
     */
    public void setNamaAkun(String namaAkun) {
        this.namaAkun = namaAkun;
    }

    /**
     * @return the kodeKomponen
     */
    public String getKodeKomponen() {
        return kodeKomponen;
    }

    /**
     * @param kodeKomponen the kodeKomponen to set
     */
    public void setKodeKomponen(String kodeKomponen) {
        this.kodeKomponen = kodeKomponen;
    }

    /**
     * @return the namaKomponen
     */
    public String getNamaKomponen() {
        return namaKomponen;
    }

    /**
     * @param namaKomponen the namaKomponen to set
     */
    public void setNamaKomponen(String namaKomponen) {
        this.namaKomponen = namaKomponen;
    }

    /**
     * @return the kasKeluar
     */
    public BigDecimal getKasKeluar() {
        return kasKeluar;
    }

    /**
     * @param kasKeluar the kasKeluar to set
     */
    public void setKasKeluar(BigDecimal kasKeluar) {
        this.kasKeluar = kasKeluar;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
}
