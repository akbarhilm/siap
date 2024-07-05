/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ebkus.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.validation.Valid;

/**
 *
 * @author M.Mustakim
 */
public class TutupBku extends BaseModel {
    private Integer id;
    @Valid
    private Skpd skpd;
    private Sekolah sekolah;
    private Integer idskpd;
    private Integer idsekolah;
    private String tahun;
    private Integer tahunAngg;
    
    private String triwulan;
    private String bulan;
    private String namaBulan;
    private String nipPA;
    private String nrkPA;
    private String namaPA;
    private String nipBendahara;
    private String nrkBendahara;
    private String namaBendahara;
    private String tglPenutupan;
    private String bulanPenutupan;
    private BigDecimal kasTerimaLalu;
    private BigDecimal kasKeluarLalu;
    private BigDecimal kasTerima;
    private BigDecimal kasKeluar;
    private BigDecimal kasSaatIni;
    private BigDecimal saldoBank;
    private BigDecimal saldoLain;
    private BigDecimal saldoTunai;
    //buat bandingin dengan bap kas
    private String statusBA;
    private BigDecimal totalBA;
    private BigDecimal saldoBA;
    private BigDecimal selisihBA;
    
    private Timestamp bkuProses;
    private String kodeWilProses;
    private String kodeNihil;
    private String kdTombolTutupBuku;

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
     * @return the skpd
     */
    public Skpd getSkpd() {
        return skpd;
    }

    /**
     * @param skpd the skpd to set
     */
    public void setSkpd(Skpd skpd) {
        this.skpd = skpd;
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
     * @return the idskpd
     */
    public Integer getIdskpd() {
        return idskpd;
    }

    /**
     * @param idskpd the idskpd to set
     */
    public void setIdskpd(Integer idskpd) {
        this.idskpd = idskpd;
    }

    /**
     * @return the idsekolah
     */
    public Integer getIdsekolah() {
        return idsekolah;
    }

    /**
     * @param idsekolah the idsekolah to set
     */
    public void setIdsekolah(Integer idsekolah) {
        this.idsekolah = idsekolah;
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
     * @return the tahunAngg
     */
    public Integer getTahunAngg() {
        return tahunAngg;
    }

    /**
     * @param tahunAngg the tahunAngg to set
     */
    public void setTahunAngg(Integer tahunAngg) {
        this.tahunAngg = tahunAngg;
    }

    /**
     * @return the bulan
     */
    public String getBulan() {
        return bulan;
    }

    /**
     * @param bulan the bulan to set
     */
    public void setBulan(String bulan) {
        this.bulan = bulan;
    }

    /**
     * @return the namaBulan
     */
    public String getNamaBulan() {
        return namaBulan;
    }

    /**
     * @param namaBulan the namaBulan to set
     */
    public void setNamaBulan(String namaBulan) {
        this.namaBulan = namaBulan;
    }

    /**
     * @return the nipPA
     */
    public String getNipPA() {
        return nipPA;
    }

    /**
     * @param nipPA the nipPA to set
     */
    public void setNipPA(String nipPA) {
        this.nipPA = nipPA;
    }

    /**
     * @return the nrkPA
     */
    public String getNrkPA() {
        return nrkPA;
    }

    /**
     * @param nrkPA the nrkPA to set
     */
    public void setNrkPA(String nrkPA) {
        this.nrkPA = nrkPA;
    }

    /**
     * @return the namaPA
     */
    public String getNamaPA() {
        return namaPA;
    }

    /**
     * @param namaPA the namaPA to set
     */
    public void setNamaPA(String namaPA) {
        this.namaPA = namaPA;
    }

    /**
     * @return the nipBendahara
     */
    public String getNipBendahara() {
        return nipBendahara;
    }

    /**
     * @param nipBendahara the nipBendahara to set
     */
    public void setNipBendahara(String nipBendahara) {
        this.nipBendahara = nipBendahara;
    }

    /**
     * @return the nrkBendahara
     */
    public String getNrkBendahara() {
        return nrkBendahara;
    }

    /**
     * @param nrkBendahara the nrkBendahara to set
     */
    public void setNrkBendahara(String nrkBendahara) {
        this.nrkBendahara = nrkBendahara;
    }

    /**
     * @return the namaBendahara
     */
    public String getNamaBendahara() {
        return namaBendahara;
    }

    /**
     * @param namaBendahara the namaBendahara to set
     */
    public void setNamaBendahara(String namaBendahara) {
        this.namaBendahara = namaBendahara;
    }

    /**
     * @return the tglPenutupan
     */
    public String getTglPenutupan() {
        return tglPenutupan;
    }

    /**
     * @param tglPenutupan the tglPenutupan to set
     */
    public void setTglPenutupan(String tglPenutupan) {
        this.tglPenutupan = tglPenutupan;
    }

    /**
     * @return the bulanPenutupan
     */
    public String getBulanPenutupan() {
        return bulanPenutupan;
    }

    /**
     * @param bulanPenutupan the bulanPenutupan to set
     */
    public void setBulanPenutupan(String bulanPenutupan) {
        this.bulanPenutupan = bulanPenutupan;
    }

    /**
     * @return the kasTerimaLalu
     */
    public BigDecimal getKasTerimaLalu() {
        return kasTerimaLalu;
    }

    /**
     * @param kasTerimaLalu the kasTerimaLalu to set
     */
    public void setKasTerimaLalu(BigDecimal kasTerimaLalu) {
        this.kasTerimaLalu = kasTerimaLalu;
    }

    /**
     * @return the kasKeluarLalu
     */
    public BigDecimal getKasKeluarLalu() {
        return kasKeluarLalu;
    }

    /**
     * @param kasKeluarLalu the kasKeluarLalu to set
     */
    public void setKasKeluarLalu(BigDecimal kasKeluarLalu) {
        this.kasKeluarLalu = kasKeluarLalu;
    }

    /**
     * @return the kasTerima
     */
    public BigDecimal getKasTerima() {
        return kasTerima;
    }

    /**
     * @param kasTerima the kasTerima to set
     */
    public void setKasTerima(BigDecimal kasTerima) {
        this.kasTerima = kasTerima;
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
     * @return the kasSaatIni
     */
    public BigDecimal getKasSaatIni() {
        return kasSaatIni;
    }

    /**
     * @param kasSaatIni the kasSaatIni to set
     */
    public void setKasSaatIni(BigDecimal kasSaatIni) {
        this.kasSaatIni = kasSaatIni;
    }

    /**
     * @return the saldoBank
     */
    public BigDecimal getSaldoBank() {
        return saldoBank;
    }

    /**
     * @param saldoBank the saldoBank to set
     */
    public void setSaldoBank(BigDecimal saldoBank) {
        this.saldoBank = saldoBank;
    }

    /**
     * @return the saldoLain
     */
    public BigDecimal getSaldoLain() {
        return saldoLain;
    }

    /**
     * @param saldoLain the saldoLain to set
     */
    public void setSaldoLain(BigDecimal saldoLain) {
        this.saldoLain = saldoLain;
    }

    /**
     * @return the saldoTunai
     */
    public BigDecimal getSaldoTunai() {
        return saldoTunai;
    }

    /**
     * @param saldoTunai the saldoTunai to set
     */
    public void setSaldoTunai(BigDecimal saldoTunai) {
        this.saldoTunai = saldoTunai;
    }

    /**
     * @return the bkuProses
     */
    public Timestamp getBkuProses() {
        return bkuProses;
    }

    /**
     * @param bkuProses the bkuProses to set
     */
    public void setBkuProses(Timestamp bkuProses) {
        this.bkuProses = bkuProses;
    }

    /**
     * @return the kodeWilProses
     */
    public String getKodeWilProses() {
        return kodeWilProses;
    }

    /**
     * @param kodeWilProses the kodeWilProses to set
     */
    public void setKodeWilProses(String kodeWilProses) {
        this.kodeWilProses = kodeWilProses;
    }

    /**
     * @return the kodeNihil
     */
    public String getKodeNihil() {
        return kodeNihil;
    }

    /**
     * @param kodeNihil the kodeNihil to set
     */
    public void setKodeNihil(String kodeNihil) {
        this.kodeNihil = kodeNihil;
    }

    /**
     * @return the kdTombolTutupBuku
     */
    public String getKdTombolTutupBuku() {
        return kdTombolTutupBuku;
    }

    /**
     * @param kdTombolTutupBuku the kdTombolTutupBuku to set
     */
    public void setKdTombolTutupBuku(String kdTombolTutupBuku) {
        this.kdTombolTutupBuku = kdTombolTutupBuku;
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
     * @return the totalBA
     */
    public BigDecimal getTotalBA() {
        return totalBA;
    }

    /**
     * @param totalBA the totalBA to set
     */
    public void setTotalBA(BigDecimal totalBA) {
        this.totalBA = totalBA;
    }

    /**
     * @return the saldoBA
     */
    public BigDecimal getSaldoBA() {
        return saldoBA;
    }

    /**
     * @param saldoBA the saldoBA to set
     */
    public void setSaldoBA(BigDecimal saldoBA) {
        this.saldoBA = saldoBA;
    }

    /**
     * @return the selisihBA
     */
    public BigDecimal getSelisihBA() {
        return selisihBA;
    }

    /**
     * @param selisihBA the selisihBA to set
     */
    public void setSelisihBA(BigDecimal selisihBA) {
        this.selisihBA = selisihBA;
    }

    /**
     * @return the statusBA
     */
    public String getStatusBA() {
        return statusBA;
    }

    /**
     * @param statusBA the statusBA to set
     */
    public void setStatusBA(String statusBA) {
        this.statusBA = statusBA;
    }
}
