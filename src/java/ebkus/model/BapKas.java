/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ebkus.model;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author zainab
 */
public class BapKas extends BaseModel{
   
    private String tahun;
    private Sekolah sekolah; 
   // BKU BA
    
    private String kodeWilSp2d;
    private String namaHari;
    private String tglBkuBa;
    private String blnBkuBa;
    private String tglBkuPros;
    private String nrkPa;
    private String nipPa;
    private String namaPa;
    private String nrkBend;
    private String nipBend;
    private String namaBend;
    private String jabatanBend;
    private String jabatanPa;
    private String noSkGub;
    private String tglSkGub;
    private String ketBkuBa;
    private String statusBkuBa;
    private BigDecimal nilaiUangKertas;
    private BigDecimal nilaiUangLogam;
    private BigDecimal nilaiUangSp2d;
    private BigDecimal nilaiUangSaldoBank;
    private BigDecimal nilaiUangSuratBerharga;
    private BigDecimal nilaiUangTotalBkuBa;
    private BigDecimal nilaiUangSaldoBkuBa;
    private BigDecimal nilaiUangSelisihBkuBa;
    
    /*BAP KAS RINCI*/
    private BigDecimal nilaiBapKas;
    private Integer idSekolahBKUBA;
    private Integer idSekolahBKUBARinci;
    private Integer idSekolahBAPKas;
    private Integer idSekolahBAPKasRinci;
    private String namaBapKas;
    private String noBap;
    private String triwulan;
    private List<BapKasRinci> bapKasRinci;
    private String tglBkuBaformat;
   
    
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
     * @return the kodeWilSp2d
     */
    public String getKodeWilSp2d() {
        return kodeWilSp2d;
    }

    /**
     * @param kodeWilSp2d the kodeWilSp2d to set
     */
    public void setKodeWilSp2d(String kodeWilSp2d) {
        this.kodeWilSp2d = kodeWilSp2d;
    }

    /**
     * @return the namaHari
     */
    public String getNamaHari() {
        return namaHari;
    }

    /**
     * @param namaHari the namaHari to set
     */
    public void setNamaHari(String namaHari) {
        this.namaHari = namaHari;
    }

    /**
     * @return the tglBkuBa
     */
    public String getTglBkuBa() {
        return tglBkuBa;
    }

    /**
     * @param tglBkuBa the tglBkuBa to set
     */
    public void setTglBkuBa(String tglBkuBa) {
        this.tglBkuBa = tglBkuBa;
    }

    /**
     * @return the nrkPa
     */
    public String getNrkPa() {
        return nrkPa;
    }

    /**
     * @param nrkPa the nrkPa to set
     */
    public void setNrkPa(String nrkPa) {
        this.nrkPa = nrkPa;
    }

    /**
     * @return the nipPa
     */
    public String getNipPa() {
        return nipPa;
    }

    /**
     * @param nipPa the nipPa to set
     */
    public void setNipPa(String nipPa) {
        this.nipPa = nipPa;
    }

    /**
     * @return the namaPa
     */
    public String getNamaPa() {
        return namaPa;
    }

    /**
     * @param namaPa the namaPa to set
     */
    public void setNamaPa(String namaPa) {
        this.namaPa = namaPa;
    }

    /**
     * @return the nrkBend
     */
    public String getNrkBend() {
        return nrkBend;
    }

    /**
     * @param nrkBend the nrkBend to set
     */
    public void setNrkBend(String nrkBend) {
        this.nrkBend = nrkBend;
    }

    /**
     * @return the nipBend
     */
    public String getNipBend() {
        return nipBend;
    }

    /**
     * @param nipBend the nipBend to set
     */
    public void setNipBend(String nipBend) {
        this.nipBend = nipBend;
    }

    /**
     * @return the namaBend
     */
    public String getNamaBend() {
        return namaBend;
    }

    /**
     * @param namaBend the namaBend to set
     */
    public void setNamaBend(String namaBend) {
        this.namaBend = namaBend;
    }

    /**
     * @return the jabatanBend
     */
    public String getJabatanBend() {
        return jabatanBend;
    }

    /**
     * @param jabatanBend the jabatanBend to set
     */
    public void setJabatanBend(String jabatanBend) {
        this.jabatanBend = jabatanBend;
    }

    /**
     * @return the noSkGub
     */
    public String getNoSkGub() {
        return noSkGub;
    }

    /**
     * @param noSkGub the noSkGub to set
     */
    public void setNoSkGub(String noSkGub) {
        this.noSkGub = noSkGub;
    }

    /**
     * @return the tglSkGub
     */
    public String getTglSkGub() {
        return tglSkGub;
    }

    /**
     * @param tglSkGub the tglSkGub to set
     */
    public void setTglSkGub(String tglSkGub) {
        this.tglSkGub = tglSkGub;
    }

    /**
     * @return the ketBkuBa
     */
    public String getKetBkuBa() {
        return ketBkuBa;
    }

    /**
     * @param ketBkuBa the ketBkuBa to set
     */
    public void setKetBkuBa(String ketBkuBa) {
        this.ketBkuBa = ketBkuBa;
    }

    /**
     * @return the statusBkuBa
     */
    public String getStatusBkuBa() {
        return statusBkuBa;
    }

    /**
     * @param statusBkuBa the statusBkuBa to set
     */
    public void setStatusBkuBa(String statusBkuBa) {
        this.statusBkuBa = statusBkuBa;
    }

    /**
     * @return the nilaiUangKertas
     */
    public BigDecimal getNilaiUangKertas() {
        return nilaiUangKertas;
    }

    /**
     * @param nilaiUangKertas the nilaiUangKertas to set
     */
    public void setNilaiUangKertas(BigDecimal nilaiUangKertas) {
        this.nilaiUangKertas = nilaiUangKertas;
    }

    /**
     * @return the nilaiUangLogam
     */
    public BigDecimal getNilaiUangLogam() {
        return nilaiUangLogam;
    }

    /**
     * @param nilaiUangLogam the nilaiUangLogam to set
     */
    public void setNilaiUangLogam(BigDecimal nilaiUangLogam) {
        this.nilaiUangLogam = nilaiUangLogam;
    }

    /**
     * @return the nilaiUangSp2d
     */
    public BigDecimal getNilaiUangSp2d() {
        return nilaiUangSp2d;
    }

    /**
     * @param nilaiUangSp2d the nilaiUangSp2d to set
     */
    public void setNilaiUangSp2d(BigDecimal nilaiUangSp2d) {
        this.nilaiUangSp2d = nilaiUangSp2d;
    }

    /**
     * @return the nilaiUangSaldoBank
     */
    public BigDecimal getNilaiUangSaldoBank() {
        return nilaiUangSaldoBank;
    }

    /**
     * @param nilaiUangSaldoBank the nilaiUangSaldoBank to set
     */
    public void setNilaiUangSaldoBank(BigDecimal nilaiUangSaldoBank) {
        this.nilaiUangSaldoBank = nilaiUangSaldoBank;
    }

    /**
     * @return the nilaiUangSuratBerharga
     */
    public BigDecimal getNilaiUangSuratBerharga() {
        return nilaiUangSuratBerharga;
    }

    /**
     * @param nilaiUangSuratBerharga the nilaiUangSuratBerharga to set
     */
    public void setNilaiUangSuratBerharga(BigDecimal nilaiUangSuratBerharga) {
        this.nilaiUangSuratBerharga = nilaiUangSuratBerharga;
    }

    /**
     * @return the nilaiUangTotalBkuBa
     */
    public BigDecimal getNilaiUangTotalBkuBa() {
        return nilaiUangTotalBkuBa;
    }

    /**
     * @param nilaiUangTotalBkuBa the nilaiUangTotalBkuBa to set
     */
    public void setNilaiUangTotalBkuBa(BigDecimal nilaiUangTotalBkuBa) {
        this.nilaiUangTotalBkuBa = nilaiUangTotalBkuBa;
    }

    /**
     * @return the nilaiUangSaldoBkuBa
     */
    public BigDecimal getNilaiUangSaldoBkuBa() {
        return nilaiUangSaldoBkuBa;
    }

    /**
     * @param nilaiUangSaldoBkuBa the nilaiUangSaldoBkuBa to set
     */
    public void setNilaiUangSaldoBkuBa(BigDecimal nilaiUangSaldoBkuBa) {
        this.nilaiUangSaldoBkuBa = nilaiUangSaldoBkuBa;
    }

    /**
     * @return the nilaiUangSelisihBkuBa
     */
    public BigDecimal getNilaiUangSelisihBkuBa() {
        return nilaiUangSelisihBkuBa;
    }

    /**
     * @param nilaiUangSelisihBkuBa the nilaiUangSelisihBkuBa to set
     */
    public void setNilaiUangSelisihBkuBa(BigDecimal nilaiUangSelisihBkuBa) {
        this.nilaiUangSelisihBkuBa = nilaiUangSelisihBkuBa;
    }

    /**
     * @return the jabatanPa
     */
    public String getJabatanPa() {
        return jabatanPa;
    }

    /**
     * @param jabatanPa the jabatanPa to set
     */
    public void setJabatanPa(String jabatanPa) {
        this.jabatanPa = jabatanPa;
    }

    /**
     * @return the tglBkuPros
     */
    public String getTglBkuPros() {
        return tglBkuPros;
    }

    /**
     * @param tglBkuPros the tglBkuPros to set
     */
    public void setTglBkuPros(String tglBkuPros) {
        this.tglBkuPros = tglBkuPros;
    }

    /**
     * @return the blnBkuBa
     */
    public String getBlnBkuBa() {
        return blnBkuBa;
    }

    /**
     * @param blnBkuBa the blnBkuBa to set
     */
    public void setBlnBkuBa(String blnBkuBa) {
        this.blnBkuBa = blnBkuBa;
    }

    /**
     * @return the nilaiBapKas
     */
    public BigDecimal getNilaiBapKas() {
        return nilaiBapKas;
    }

    /**
     * @param nilaiBapKas the nilaiBapKas to set
     */
    public void setNilaiBapKas(BigDecimal nilaiBapKas) {
        this.nilaiBapKas = nilaiBapKas;
    }


    /**
     * @return the namaBapKas
     */
    public String getNamaBapKas() {
        return namaBapKas;
    }

    /**
     * @param namaBapKas the namaBapKas to set
     */
    public void setNamaBapKas(String namaBapKas) {
        this.namaBapKas = namaBapKas;
    }

    /**
     * @return the noBap
     */
    public String getNoBap() {
        return noBap;
    }

    /**
     * @param noBap the noBap to set
     */
    public void setNoBap(String noBap) {
        this.noBap = noBap;
    }

    /**
     * @return the bapKasRinci
     */
    public List<BapKasRinci> getBapKasRinci() {
        return bapKasRinci;
    }

    /**
     * @param bapKasRinci the bapKasRinci to set
     */
    public void setBapKasRinci(List<BapKasRinci> bapKasRinci) {
        this.bapKasRinci = bapKasRinci;
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
     * @return the idSekolahBKUBA
     */
    public Integer getIdSekolahBKUBA() {
        return idSekolahBKUBA;
    }

    /**
     * @param idSekolahBKUBA the idSekolahBKUBA to set
     */
    public void setIdSekolahBKUBA(Integer idSekolahBKUBA) {
        this.idSekolahBKUBA = idSekolahBKUBA;
    }

    /**
     * @return the idSekolahBKUBARinci
     */
    public Integer getIdSekolahBKUBARinci() {
        return idSekolahBKUBARinci;
    }

    /**
     * @param idSekolahBKUBARinci the idSekolahBKUBARinci to set
     */
    public void setIdSekolahBKUBARinci(Integer idSekolahBKUBARinci) {
        this.idSekolahBKUBARinci = idSekolahBKUBARinci;
    }

    /**
     * @return the idSekolahBAPKas
     */
    public Integer getIdSekolahBAPKas() {
        return idSekolahBAPKas;
    }

    /**
     * @param idSekolahBAPKas the idSekolahBAPKas to set
     */
    public void setIdSekolahBAPKas(Integer idSekolahBAPKas) {
        this.idSekolahBAPKas = idSekolahBAPKas;
    }

    /**
     * @return the idSekolahBAPKasRinci
     */
    public Integer getIdSekolahBAPKasRinci() {
        return idSekolahBAPKasRinci;
    }

    /**
     * @param idSekolahBAPKasRinci the idSekolahBAPKasRinci to set
     */
    public void setIdSekolahBAPKasRinci(Integer idSekolahBAPKasRinci) {
        this.idSekolahBAPKasRinci = idSekolahBAPKasRinci;
    }

    /**
     * @return the tglBkuBaformat
     */
    public String getTglBkuBaformat() {
        return tglBkuBaformat;
    }

    /**
     * @param tglBkuBaformat the tglBkuBaformat to set
     */
    public void setTglBkuBaformat(String tglBkuBaformat) {
        this.tglBkuBaformat = tglBkuBaformat;
    }


}
