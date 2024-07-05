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
public class PaguTalangan extends BaseModel {

    private Integer id;
    private String no;
    private Integer idskpd;
    private Integer idsekolah;
    private Integer idmcb;
    private Sekolah sekolah;
    private Integer tahunAnggaran;
    private String namaSekolah;
    private String tahun;
    private Date tanggalMohon;
    private String bulanTagihan;
    private String uraian;
    private String kodeSumbdana;
    private String nrk;
    private String namaPptk;
    private String nipPptk;
    private String triwulan;
    private BigDecimal danaTalangan;
    private String iMcb;
    private String namaMcb;
    private String kodeRekening;

    private MCB mcb;

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
     * @return the idmcb
     */
    public Integer getIdmcb() {
        return idmcb;
    }

    /**
     * @param idmcb the idmcb to set
     */
    public void setIdmcb(Integer idmcb) {
        this.idmcb = idmcb;
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
     * @return the tanggalMohon
     */
    public Date getTanggalMohon() {
        return tanggalMohon;
    }

    /**
     * @param tanggalMohon the tanggalMohon to set
     */
    public void setTanggalMohon(Date tanggalMohon) {
        this.tanggalMohon = tanggalMohon;
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
     * @return the nrk
     */
    public String getNrk() {
        return nrk;
    }

    /**
     * @param nrk the nrk to set
     */
    public void setNrk(String nrk) {
        this.nrk = nrk;
    }

    /**
     * @return the namaPptk
     */
    public String getNamaPptk() {
        return namaPptk;
    }

    /**
     * @param namaPptk the namaPptk to set
     */
    public void setNamaPptk(String namaPptk) {
        this.namaPptk = namaPptk;
    }

    /**
     * @return the nipPptk
     */
    public String getNipPptk() {
        return nipPptk;
    }

    /**
     * @param nipPptk the nipPptk to set
     */
    public void setNipPptk(String nipPptk) {
        this.nipPptk = nipPptk;
    }

    /**
     * @return the danaTalangan
     */
    public BigDecimal getDanaTalangan() {
        return danaTalangan;
    }

    /**
     * @param danaTalangan the danaTalangan to set
     */
    public void setDanaTalangan(BigDecimal danaTalangan) {
        this.danaTalangan = danaTalangan;
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
     * @return the mcb
     */
    public MCB getMcb() {
        return mcb;
    }

    /**
     * @param mcb the mcb to set
     */
    public void setMcb(MCB mcb) {
        this.mcb = mcb;
    }

    @Override
    public String toString() {
        return "PaguTalangan{" + "id=" + id + ", idskpd=" + idskpd + ", idsekolah=" + idsekolah + ", idmcb=" + idmcb + ", sekolah=" + sekolah + ", tahunAnggaran=" + tahunAnggaran + ", namaSekolah=" + namaSekolah + ", tahun=" + tahun + ", tanggalMohon=" + tanggalMohon + ", bulanTagihan=" + bulanTagihan + ", uraian=" + uraian + ", kodeSumbdana=" + kodeSumbdana + ", nrk=" + nrk + ", namaPptk=" + namaPptk + ", nipPptk=" + nipPptk + ", triwulan=" + triwulan + ", danaTalangan=" + danaTalangan + ", iMcb=" + iMcb + ", namaMcb=" + namaMcb + ", mcb=" + mcb + '}';
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

    /**
     * @return the kodeRekening
     */
    public String getKodeRekening() {
        return kodeRekening;
    }

    /**
     * @param kodeRekening the kodeRekening to set
     */
    public void setKodeRekening(String kodeRekening) {
        this.kodeRekening = kodeRekening;
    }

    /**
     * @return the no
     */
    public String getNo() {
        return no;
    }

    /**
     * @param no the no to set
     */
    public void setNo(String no) {
        this.no = no;
    }

}
