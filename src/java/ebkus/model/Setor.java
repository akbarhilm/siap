/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.model;

import java.math.BigDecimal;
import java.sql.Date;

public class Setor extends BaseModel {

    private Integer idSetor;
    private String tahunAnggaran;
    private Integer idSekolah;
    private String kodeTriwulan;
    private String kodeTransaksi;
    private String ctrx;
    private Integer noSetor;
    private Date tglSetor;
    private String tahunSetor;
    private Integer noBkuReff;
    private BigDecimal nilaiSetor;
    private BigDecimal nilaiSetorSebelum;
    private Integer idBas;
    private String kodeAkun;
    private String namaAkun;
    private String uraian;
    private String noSts;
    private Sekolah sekolah;
    private String kodeSumbdana;
    private BigDecimal totalMohon;
    private BigDecimal totalRealisasi;
    private BigDecimal sisaKas;

    //tambah model buat cetak setoran
    private String nrkPA;
    private String nipPA;
    private String namaPA;
    private String pangkatPA;
    private String nrkPK;
    private String nipPK;
    private String namaPK;
    private String pangkatPK;
    private String statusCetak;

    /**
     * @return the tahunAnggaran
     */
    public String getTahunAnggaran() {
        return tahunAnggaran;
    }

    /**
     * @param tahunAnggaran the tahunAnggaran to set
     */
    public void setTahunAnggaran(String tahunAnggaran) {
        this.tahunAnggaran = tahunAnggaran;
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
     * @return the noSetor
     */
    public Integer getNoSetor() {
        return noSetor;
    }

    /**
     * @param noSetor the noSetor to set
     */
    public void setNoSetor(Integer noSetor) {
        this.noSetor = noSetor;
    }

    /**
     * @return the tglSetor
     */
    public Date getTglSetor() {
        return tglSetor;
    }

    /**
     * @param tglSetor the tglSetor to set
     */
    public void setTglSetor(Date tglSetor) {
        this.tglSetor = tglSetor;
    }

    /**
     * @return the tahunSetor
     */
    public String getTahunSetor() {
        return tahunSetor;
    }

    /**
     * @param tahunSetor the tahunSetor to set
     */
    public void setTahunSetor(String tahunSetor) {
        this.tahunSetor = tahunSetor;
    }

    /**
     * @return the noBkuReff
     */
    public Integer getNoBkuReff() {
        return noBkuReff;
    }

    /**
     * @param noBkuReff the noBkuReff to set
     */
    public void setNoBkuReff(Integer noBkuReff) {
        this.noBkuReff = noBkuReff;
    }

    /**
     * @return the nilaiSetor
     */
    public BigDecimal getNilaiSetor() {
        return nilaiSetor;
    }

    /**
     * @param nilaiSetor the nilaiSetor to set
     */
    public void setNilaiSetor(BigDecimal nilaiSetor) {
        this.nilaiSetor = nilaiSetor;
    }

    /**
     * @return the idBas
     */
    public Integer getIdBas() {
        return idBas;
    }

    /**
     * @param idBas the idBas to set
     */
    public void setIdBas(Integer idBas) {
        this.idBas = idBas;
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
     * @return the noSts
     */
    public String getNoSts() {
        return noSts;
    }

    /**
     * @param noSts the noSts to set
     */
    public void setNoSts(String noSts) {
        this.noSts = noSts;
    }

    /**
     * @return the idSetor
     */
    public Integer getIdSetor() {
        return idSetor;
    }

    /**
     * @param idSetor the idSetor to set
     */
    public void setIdSetor(Integer idSetor) {
        this.idSetor = idSetor;
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
     * @return the pangkatPA
     */
    public String getPangkatPA() {
        return pangkatPA;
    }

    /**
     * @param pangkatPA the pangkatPA to set
     */
    public void setPangkatPA(String pangkatPA) {
        this.pangkatPA = pangkatPA;
    }

    /**
     * @return the nrkPK
     */
    public String getNrkPK() {
        return nrkPK;
    }

    /**
     * @param nrkPK the nrkPK to set
     */
    public void setNrkPK(String nrkPK) {
        this.nrkPK = nrkPK;
    }

    /**
     * @return the nipPK
     */
    public String getNipPK() {
        return nipPK;
    }

    /**
     * @param nipPK the nipPK to set
     */
    public void setNipPK(String nipPK) {
        this.nipPK = nipPK;
    }

    /**
     * @return the namaPK
     */
    public String getNamaPK() {
        return namaPK;
    }

    /**
     * @param namaPK the namaPK to set
     */
    public void setNamaPK(String namaPK) {
        this.namaPK = namaPK;
    }

    /**
     * @return the statusCetak
     */
    public String getStatusCetak() {
        return statusCetak;
    }

    /**
     * @param statusCetak the statusCetak to set
     */
    public void setStatusCetak(String statusCetak) {
        this.statusCetak = statusCetak;
    }

    public String toString() {
        return getIdSetor() + "|"
                + getTahunAnggaran() + "|"
                + getIdSekolah() + "|"
                + getKodeTransaksi() + "|"
                + getNoSetor() + "|"
                + getTglSetor() + "|"
                + getTahunSetor() + "|"
                + getNoBkuReff() + "|"
                + getNilaiSetor() + "|"
                + getIdBas() + "|"
                + getUraian();
    }

    /**
     * @return the pangkatPK
     */
    public String getPangkatPK() {
        return pangkatPK;
    }

    /**
     * @param pangkatPK the pangkatPK to set
     */
    public void setPangkatPK(String pangkatPK) {
        this.pangkatPK = pangkatPK;
    }

    /**
     * @return the kodeTriwulan
     */
    public String getKodeTriwulan() {
        return kodeTriwulan;
    }

    /**
     * @param kodeTriwulan the kodeTriwulan to set
     */
    public void setKodeTriwulan(String kodeTriwulan) {
        this.kodeTriwulan = kodeTriwulan;
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
     * @return the nilaiSetorSebelum
     */
    public BigDecimal getNilaiSetorSebelum() {
        return nilaiSetorSebelum;
    }

    /**
     * @param nilaiSetorSebelum the nilaiSetorSebelum to set
     */
    public void setNilaiSetorSebelum(BigDecimal nilaiSetorSebelum) {
        this.nilaiSetorSebelum = nilaiSetorSebelum;
    }

    /**
     * @return the totalMohon
     */
    public BigDecimal getTotalMohon() {
        return totalMohon;
    }

    /**
     * @param totalMohon the totalMohon to set
     */
    public void setTotalMohon(BigDecimal totalMohon) {
        this.totalMohon = totalMohon;
    }

    /**
     * @return the totalRealisasi
     */
    public BigDecimal getTotalRealisasi() {
        return totalRealisasi;
    }

    /**
     * @param totalRealisasi the totalRealisasi to set
     */
    public void setTotalRealisasi(BigDecimal totalRealisasi) {
        this.totalRealisasi = totalRealisasi;
    }

    /**
     * @return the sisaKas
     */
    public BigDecimal getSisaKas() {
        return sisaKas;
    }

    /**
     * @param sisaKas the sisaKas to set
     */
    public void setSisaKas(BigDecimal sisaKas) {
        this.sisaKas = sisaKas;
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
     * @return the ctrx
     */
    public String getCtrx() {
        return ctrx;
    }

    /**
     * @param ctrx the ctrx to set
     */
    public void setCtrx(String ctrx) {
        this.ctrx = ctrx;
    }
}
