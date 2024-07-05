/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ebkus.model;

import java.sql.Date;
import java.math.BigDecimal;

/**
 *
 * @author Galuh Ajeng Larasati
 */
public class MonitoringAkbBkuPerTriwulan extends BaseModel {
    private Integer noUrut;
    private String kodeKegiatan;
    private String namaKegiatan;
    private String kodeAkun;
    private String namaAkun;
    private BigDecimal nilaiAkb;
    private BigDecimal nilaiPengajuanBku;
    private BigDecimal selisih;
    private String tanda;
    private String triwulan;
    private String tahun;
    private Sekolah sekolah;

    /**
     * @return the noUrut
     */
    public Integer getNoUrut() {
        return noUrut;
    }

    /**
     * @param noUrut the noUrut to set
     */
    public void setNoUrut(Integer noUrut) {
        this.noUrut = noUrut;
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
     * @return the nilaiAkb
     */
    public BigDecimal getNilaiAkb() {
        return nilaiAkb;
    }

    /**
     * @param nilaiAkb the nilaiAkb to set
     */
    public void setNilaiAkb(BigDecimal nilaiAkb) {
        this.nilaiAkb = nilaiAkb;
    }

    /**
     * @return the nilaiPengajuanBku
     */
    public BigDecimal getNilaiPengajuanBku() {
        return nilaiPengajuanBku;
    }

    /**
     * @param nilaiPengajuanBku the nilaiPengajuanBku to set
     */
    public void setNilaiPengajuanBku(BigDecimal nilaiPengajuanBku) {
        this.nilaiPengajuanBku = nilaiPengajuanBku;
    }

    /**
     * @return the selisih
     */
    public BigDecimal getSelisih() {
        return selisih;
    }

    /**
     * @param selisih the selisih to set
     */
    public void setSelisih(BigDecimal selisih) {
        this.selisih = selisih;
    }

    /**
     * @return the tanda
     */
    public String getTanda() {
        return tanda;
    }

    /**
     * @param tanda the tanda to set
     */
    public void setTanda(String tanda) {
        this.tanda = tanda;
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
    
    
    
    
}
