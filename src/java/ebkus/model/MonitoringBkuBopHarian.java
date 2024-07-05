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
public class MonitoringBkuBopHarian extends BaseModel {
    private Integer noUrut;
    private Integer noBku;
    private String namaTrx;
    private String noBuktiDoc;  // I_DOC_BUKTI
    private String tglBuktiDoc; // I_DOC_BUKTI
    private String keterangan;
    private BigDecimal perimaan;
    private BigDecimal pengeluaran;
    private BigDecimal saldo;
    private String tglPosting;
    private String tahun;
    private Sekolah sekolah;

    /**
     * @return the noBku
     */
    public Integer getNoBku() {
        return noBku;
    }

    /**
     * @param noBku the noBku to set
     */
    public void setNoBku(Integer noBku) {
        this.noBku = noBku;
    }

    /**
     * @return the namaTrx
     */
    public String getNamaTrx() {
        return namaTrx;
    }

    /**
     * @param namaTrx the namaTrx to set
     */
    public void setNamaTrx(String namaTrx) {
        this.namaTrx = namaTrx;
    }

    /**
     * @return the keterangan
     */
    public String getKeterangan() {
        return keterangan;
    }

    /**
     * @param keterangan the keterangan to set
     */
    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    /**
     * @return the perimaan
     */
    public BigDecimal getPerimaan() {
        return perimaan;
    }

    /**
     * @param perimaan the perimaan to set
     */
    public void setPerimaan(BigDecimal perimaan) {
        this.perimaan = perimaan;
    }

    /**
     * @return the pengeluaran
     */
    public BigDecimal getPengeluaran() {
        return pengeluaran;
    }

    /**
     * @param pengeluaran the pengeluaran to set
     */
    public void setPengeluaran(BigDecimal pengeluaran) {
        this.pengeluaran = pengeluaran;
    }

    /**
     * @return the saldo
     */
    public BigDecimal getSaldo() {
        return saldo;
    }

    /**
     * @param saldo the saldo to set
     */
    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    /**
     * @return the noBuktiDoc
     */
    public String getNoBuktiDoc() {
        return noBuktiDoc;
    }

    /**
     * @param noBuktiDoc the noBuktiDoc to set
     */
    public void setNoBuktiDoc(String noBuktiDoc) {
        this.noBuktiDoc = noBuktiDoc;
    }

    /**
     * @return the tglBuktiDoc
     */
    public String getTglBuktiDoc() {
        return tglBuktiDoc;
    }

    /**
     * @param tglBuktiDoc the tglBuktiDoc to set
     */
    public void setTglBuktiDoc(String tglBuktiDoc) {
        this.tglBuktiDoc = tglBuktiDoc;
    }

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
     * @return the tglPosting
     */
    public String getTglPosting() {
        return tglPosting;
    }

    /**
     * @param tglPosting the tglPosting to set
     */
    public void setTglPosting(String tglPosting) {
        this.tglPosting = tglPosting;
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
