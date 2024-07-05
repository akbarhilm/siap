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
 * @author HP pavilion
 */
public class WSTalangan extends BaseModel {

    private Integer id;

    private Sekolah sekolah;
    private Integer tahunAnggaran;
    private String namaSekolah;
    private String npsn;
    private String tahun;
    private Date tanggalTerima;
    private String bulanTagihan;
    private String kodeSumbdana;
    private String triwulan;
    private Date tanggalBayar;
    private String kodeBayar;
    private BigDecimal nilaiBayar;
    private String iMcb;
    private String namaMcb;

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
     * @return the sekolah
     */
    public Sekolah getSekolah() {
        return sekolah;
    }

    /**
     * @param sekolah the sekolah to set
     */
    public void setSekolah(Sekolah sekolah) {
        this.sekolah = sekolah;
    }

    /**
     * @return the tahunAnggaran
     */
    public Integer getTahunAnggaran() {
        return tahunAnggaran;
    }

    /**
     * @param tahunAnggaran the tahunAnggaran to set
     */
    public void setTahunAnggaran(Integer tahunAnggaran) {
        this.tahunAnggaran = tahunAnggaran;
    }

    /**
     * @return the namaSekolah
     */
    public String getNamaSekolah() {
        return namaSekolah;
    }

    /**
     * @param namaSekolah the namaSekolah to set
     */
    public void setNamaSekolah(String namaSekolah) {
        this.namaSekolah = namaSekolah;
    }

    /**
     * @return the npsn
     */
    public String getNpsn() {
        return npsn;
    }

    /**
     * @param npsn the npsn to set
     */
    public void setNpsn(String npsn) {
        this.npsn = npsn;
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
     * @return the tanggalTerima
     */
    public Date getTanggalTerima() {
        return tanggalTerima;
    }

    /**
     * @param tanggalTerima the tanggalTerima to set
     */
    public void setTanggalTerima(Date tanggalTerima) {
        this.tanggalTerima = tanggalTerima;
    }

    /**
     * @return the bulanTagihan
     */
    public String getBulanTagihan() {
        return bulanTagihan;
    }

    /**
     * @param bulanTagihan the bulanTagihan to set
     */
    public void setBulanTagihan(String bulanTagihan) {
        this.bulanTagihan = bulanTagihan;
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
     * @return the tanggalBayar
     */
    public Date getTanggalBayar() {
        return tanggalBayar;
    }

    /**
     * @param tanggalBayar the tanggalBayar to set
     */
    public void setTanggalBayar(Date tanggalBayar) {
        this.tanggalBayar = tanggalBayar;
    }

    /**
     * @return the kodeBayar
     */
    public String getKodeBayar() {
        return kodeBayar;
    }

    /**
     * @param kodeBayar the kodeBayar to set
     */
    public void setKodeBayar(String kodeBayar) {
        this.kodeBayar = kodeBayar;
    }

    /**
     * @return the nilaiBayar
     */
    public BigDecimal getNilaiBayar() {
        return nilaiBayar;
    }

    /**
     * @param nilaiBayar the nilaiBayar to set
     */
    public void setNilaiBayar(BigDecimal nilaiBayar) {
        this.nilaiBayar = nilaiBayar;
    }

    /**
     * @return the iMcb
     */
    public String getiMcb() {
        return iMcb;
    }

    /**
     * @param iMcb the iMcb to set
     */
    public void setiMcb(String iMcb) {
        this.iMcb = iMcb;
    }

    /**
     * @return the namaMcb
     */
    public String getNamaMcb() {
        return namaMcb;
    }

    /**
     * @param namaMcb the namaMcb to set
     */
    public void setNamaMcb(String namaMcb) {
        this.namaMcb = namaMcb;
    }
}
