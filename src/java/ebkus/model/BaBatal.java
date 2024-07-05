/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.model;

import java.math.BigDecimal;
import java.sql.Date;

/**
 *
 * @author Racka
 */
public class BaBatal extends BaseModel {

    private Integer idBa;
    private Integer idSekolah;
    private Integer noBkuMohon;
    private String noBa;
    private String noBaDok;
    private String kodeSumbdana;
    private String tahun;
    private String triwulan;
    private BigDecimal nilai;
    private String kodeTransaksi;
    private String uraian;
    private String kodeAktif;
    private Date tanggal;

    /**
     * @return the idBa
     */
    public Integer getIdBa() {
        return idBa;
    }

    /**
     * @param idBa the idBa to set
     */
    public void setIdBa(Integer idBa) {
        this.idBa = idBa;
    }

    /**
     * @return the idSekolah
     */
    public Integer getIdSekolah() {
        return idSekolah;
    }

    /**
     * @param idSekolah the idSekolah to set
     */
    public void setIdSekolah(Integer idSekolah) {
        this.idSekolah = idSekolah;
    }

    /**
     * @return the noBa
     */
    public String getNoBa() {
        return noBa;
    }

    /**
     * @param noBa the noBa to set
     */
    public void setNoBa(String noBa) {
        this.noBa = noBa;
    }

    /**
     * @return the kodeSumbdana
     */
    public String getKodeSumbdana() {
        return kodeSumbdana;
    }

    /**
     * @param kodeSumbdana the kodeSumbdana to set
     */
    public void setKodeSumbdana(String kodeSumbdana) {
        this.kodeSumbdana = kodeSumbdana;
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
     * @return the kodeTriwulan
     */
    /**
     * @return the nilai
     */
    public BigDecimal getNilai() {
        return nilai;
    }

    /**
     * @param nilai the nilai to set
     */
    public void setNilai(BigDecimal nilai) {
        this.nilai = nilai;
    }

    /**
     * @return the kodeTransaksi
     */
    public String getKodeTransaksi() {
        return kodeTransaksi;
    }

    /**
     * @param kodeTransaksi the kodeTransaksi to set
     */
    public void setKodeTransaksi(String kodeTransaksi) {
        this.kodeTransaksi = kodeTransaksi;
    }

    /**
     * @return the uraian
     */
    public String getUraian() {
        return uraian;
    }

    /**
     * @param uraian the uraian to set
     */
    public void setUraian(String uraian) {
        this.uraian = uraian;
    }

    /**
     * @return the kodeAktif
     */
    public String getKodeAktif() {
        return kodeAktif;
    }

    /**
     * @param kodeAktif the kodeAktif to set
     */
    public void setKodeAktif(String kodeAktif) {
        this.kodeAktif = kodeAktif;
    }

    /**
     * @return the tanggal
     */
    public Date getTanggal() {
        return tanggal;
    }

    /**
     * @param tanggal the tanggal to set
     */
    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    /**
     * @return the noBkuMohon
     */
    public Integer getNoBkuMohon() {
        return noBkuMohon;
    }

    /**
     * @param noBkuMohon the noBkuMohon to set
     */
    public void setNoBkuMohon(Integer noBkuMohon) {
        this.noBkuMohon = noBkuMohon;
    }

    /**
     * @return the noBaDok
     */
    public String getNoBaDok() {
        return noBaDok;
    }

    /**
     * @param noBaDok the noBaDok to set
     */
    public void setNoBaDok(String noBaDok) {
        this.noBaDok = noBaDok;
    }
}
