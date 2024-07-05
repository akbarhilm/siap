/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.model;

import java.math.BigDecimal;
//import java.sql.Date;
import javax.validation.Valid;

/**
 *
 * @author Zainab
 */
public class BkuRinci extends BaseModel {

    private Integer id;

    @Valid
    private Integer idBkuRinci;
    private Integer idBku;
    private Integer idBkuRef;
    private String tahun;
    private Integer idKegiatan;
    private String ketKegiatan;
    private String kodeKeg;
    private String namaKeg;
    private Integer idBas;
    private String namaakun;
    private String kodeakun;
    private String ketakun;
    private Integer idKomponen;
    private String kodeKomponen;
    private String namaKomponen;
    private BigDecimal nilaiMasuk;
    private BigDecimal nilaiKeluar;
    private BigDecimal nilaiPajakSpj;
    private BigDecimal nilaiMohon;
    private BigDecimal nilaiNettoSpj;
    private String kodeTransaksi;

    private BigDecimal nilaiSisa;
    private BigDecimal nilaiAnggaran;
    private BigDecimal nilaiBkuSebelum;
    private BigDecimal nilaiBkuSebelumTw;
    private BigDecimal nilaiBkuInput;
    private BigDecimal nilaiSpj;
    private BigDecimal saldoKomponen;
    private Integer idBlRinci;
    private String noUrut;
    private String namaSubKegiatan;
    private String keteranganRinci;
    private Integer volume;
    private Integer komponenPajak;
    private BigDecimal hargaSatuan;
    private String keterangan;
    private String uraian;
    private String statusSpj;
    private String spekKomponen;
    private BigDecimal paguAkun;
    
    /**
     * @return the idBkuRinci
     */
    public Integer getIdBkuRinci() {
        return idBkuRinci;
    }

    /**
     * @param idBkuRinci the idBkuRinci to set
     */
    public void setIdBkuRinci(Integer idBkuRinci) {
        this.idBkuRinci = idBkuRinci;
    }

    /**
     * @return the idBku
     */
    public Integer getIdBku() {
        return idBku;
    }

    /**
     * @param idBku the idBku to set
     */
    public void setIdBku(Integer idBku) {
        this.idBku = idBku;
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
     * @return the idKegiatan
     */
    public Integer getIdKegiatan() {
        return idKegiatan;
    }

    /**
     * @param idKegiatan the idKegiatan to set
     */
    public void setIdKegiatan(Integer idKegiatan) {
        this.idKegiatan = idKegiatan;
    }

    /**
     * @return the ketKegiatan
     */
    public String getKetKegiatan() {
        return ketKegiatan;
    }

    /**
     * @param ketKegiatan the ketKegiatan to set
     */
    public void setKetKegiatan(String ketKegiatan) {
        this.ketKegiatan = ketKegiatan;
    }

    /**
     * @return the kodeKeg
     */
    public String getKodeKeg() {
        return kodeKeg;
    }

    /**
     * @param kodeKeg the kodeKeg to set
     */
    public void setKodeKeg(String kodeKeg) {
        this.kodeKeg = kodeKeg;
    }

    /**
     * @return the namaKeg
     */
    public String getNamaKeg() {
        return namaKeg;
    }

    /**
     * @param namaKeg the namaKeg to set
     */
    public void setNamaKeg(String namaKeg) {
        this.namaKeg = namaKeg;
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
     * @return the namaakun
     */
    public String getNamaakun() {
        return namaakun;
    }

    /**
     * @param namaakun the namaakun to set
     */
    public void setNamaakun(String namaakun) {
        this.namaakun = namaakun;
    }

    /**
     * @return the kodeakun
     */
    public String getKodeakun() {
        return kodeakun;
    }

    /**
     * @param kodeakun the kodeakun to set
     */
    public void setKodeakun(String kodeakun) {
        this.kodeakun = kodeakun;
    }

    /**
     * @return the ketakun
     */
    public String getKetakun() {
        return ketakun;
    }

    /**
     * @param ketakun the ketakun to set
     */
    public void setKetakun(String ketakun) {
        this.ketakun = ketakun;
    }

    /**
     * @return the idKomponen
     */
    public Integer getIdKomponen() {
        return idKomponen;
    }

    /**
     * @param idKomponen the idKomponen to set
     */
    public void setIdKomponen(Integer idKomponen) {
        this.idKomponen = idKomponen;
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
     * @return the nilaiMasuk
     */
    public BigDecimal getNilaiMasuk() {
        return nilaiMasuk;
    }

    /**
     * @param nilaiMasuk the nilaiMasuk to set
     */
    public void setNilaiMasuk(BigDecimal nilaiMasuk) {
        this.nilaiMasuk = nilaiMasuk;
    }

    /**
     * @return the nilaiKeluar
     */
    public BigDecimal getNilaiKeluar() {
        return nilaiKeluar;
    }

    /**
     * @param nilaiKeluar the nilaiKeluar to set
     */
    public void setNilaiKeluar(BigDecimal nilaiKeluar) {
        this.nilaiKeluar = nilaiKeluar;
    }

    /**
     * @return the nilaiPajakSpj
     */
    public BigDecimal getNilaiPajakSpj() {
        return nilaiPajakSpj;
    }

    /**
     * @param nilaiPajakSpj the nilaiPajakSpj to set
     */
    public void setNilaiPajakSpj(BigDecimal nilaiPajakSpj) {
        this.nilaiPajakSpj = nilaiPajakSpj;
    }

    /**
     * @return the nilaiNettoSpj
     */
    public BigDecimal getNilaiNettoSpj() {
        return nilaiNettoSpj;
    }

    /**
     * @param nilaiNettoSpj the nilaiNettoSpj to set
     */
    public void setNilaiNettoSpj(BigDecimal nilaiNettoSpj) {
        this.nilaiNettoSpj = nilaiNettoSpj;
    }

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
     * @return the nilaiSisa
     */
    public BigDecimal getNilaiSisa() {
        return nilaiSisa;
    }

    /**
     * @param nilaiSisa the nilaiSisa to set
     */
    public void setNilaiSisa(BigDecimal nilaiSisa) {
        this.nilaiSisa = nilaiSisa;
    }

    /**
     * @return the nilaiAnggaran
     */
    public BigDecimal getNilaiAnggaran() {
        return nilaiAnggaran;
    }

    /**
     * @param nilaiAnggaran the nilaiAnggaran to set
     */
    public void setNilaiAnggaran(BigDecimal nilaiAnggaran) {
        this.nilaiAnggaran = nilaiAnggaran;
    }

    /**
     * @return the nilaiBkuSebelum
     */
    public BigDecimal getNilaiBkuSebelum() {
        return nilaiBkuSebelum;
    }

    /**
     * @param nilaiBkuSebelum the nilaiBkuSebelum to set
     */
    public void setNilaiBkuSebelum(BigDecimal nilaiBkuSebelum) {
        this.nilaiBkuSebelum = nilaiBkuSebelum;
    }

    /**
     * @return the nilaiBkuInput
     */
    public BigDecimal getNilaiBkuInput() {
        return nilaiBkuInput;
    }

    /**
     * @param nilaiBkuInput the nilaiBkuInput to set
     */
    public void setNilaiBkuInput(BigDecimal nilaiBkuInput) {
        this.nilaiBkuInput = nilaiBkuInput;
    }

    /**
     * @return the nilaiSpj
     */
    public BigDecimal getNilaiSpj() {
        return nilaiSpj;
    }

    /**
     * @param nilaiSpj the nilaiSpj to set
     */
    public void setNilaiSpj(BigDecimal nilaiSpj) {
        this.nilaiSpj = nilaiSpj;
    }

    /**
     * @return the saldoKomponen
     */
    public BigDecimal getSaldoKomponen() {
        return saldoKomponen;
    }

    /**
     * @param saldoKomponen the saldoKomponen to set
     */
    public void setSaldoKomponen(BigDecimal saldoKomponen) {
        this.saldoKomponen = saldoKomponen;
    }

    /**
     * @return the idBlRinci
     */
    public Integer getIdBlRinci() {
        return idBlRinci;
    }

    /**
     * @param idBlRinci the idBlRinci to set
     */
    public void setIdBlRinci(Integer idBlRinci) {
        this.idBlRinci = idBlRinci;
    }

    /**
     * @return the noUrut
     */
    public String getNoUrut() {
        return noUrut;
    }

    /**
     * @param noUrut the noUrut to set
     */
    public void setNoUrut(String noUrut) {
        this.noUrut = noUrut;
    }

    /**
     * @return the namaSubKegiatan
     */
    public String getNamaSubKegiatan() {
        return namaSubKegiatan;
    }

    /**
     * @param namaSubKegiatan the namaSubKegiatan to set
     */
    public void setNamaSubKegiatan(String namaSubKegiatan) {
        this.namaSubKegiatan = namaSubKegiatan;
    }

    /**
     * @return the keteranganRinci
     */
    public String getKeteranganRinci() {
        return keteranganRinci;
    }

    /**
     * @param keteranganRinci the keteranganRinci to set
     */
    public void setKeteranganRinci(String keteranganRinci) {
        this.keteranganRinci = keteranganRinci;
    }

    /**
     * @return the volume
     */
    public Integer getVolume() {
        return volume;
    }

    /**
     * @param volume the volume to set
     */
    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    /**
     * @return the komponenPajak
     */
    public Integer getKomponenPajak() {
        return komponenPajak;
    }

    /**
     * @param komponenPajak the komponenPajak to set
     */
    public void setKomponenPajak(Integer komponenPajak) {
        this.komponenPajak = komponenPajak;
    }

    /**
     * @return the hargaSatuan
     */
    public BigDecimal getHargaSatuan() {
        return hargaSatuan;
    }

    /**
     * @param hargaSatuan the hargaSatuan to set
     */
    public void setHargaSatuan(BigDecimal hargaSatuan) {
        this.hargaSatuan = hargaSatuan;
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
     * @return the nilaiMohon
     */
    public BigDecimal getNilaiMohon() {
        return nilaiMohon;
    }

    /**
     * @param nilaiMohon the nilaiMohon to set
     */
    public void setNilaiMohon(BigDecimal nilaiMohon) {
        this.nilaiMohon = nilaiMohon;
    }

    /**
     * @return the statusSpj
     */
    public String getStatusSpj() {
        return statusSpj;
    }

    /**
     * @param statusSpj the statusSpj to set
     */
    public void setStatusSpj(String statusSpj) {
        this.statusSpj = statusSpj;
    }

    /**
     * @return the idBkuRef
     */
    public Integer getIdBkuRef() {
        return idBkuRef;
    }

    /**
     * @param idBkuRef the idBkuRef to set
     */
    public void setIdBkuRef(Integer idBkuRef) {
        this.idBkuRef = idBkuRef;
    }

    /**
     * @return the spekKomponen
     */
    public String getSpekKomponen() {
        return spekKomponen;
    }

    /**
     * @param spekKomponen the spekKomponen to set
     */
    public void setSpekKomponen(String spekKomponen) {
        this.spekKomponen = spekKomponen;
    }

    /**
     * @return the paguAkun
     */
    public BigDecimal getPaguAkun() {
        return paguAkun;
    }

    /**
     * @param paguAkun the paguAkun to set
     */
    public void setPaguAkun(BigDecimal paguAkun) {
        this.paguAkun = paguAkun;
    }

    /**
     * @return the nilaiBkuSebelumTw
     */
    public BigDecimal getNilaiBkuSebelumTw() {
        return nilaiBkuSebelumTw;
    }

    /**
     * @param nilaiBkuSebelumTw the nilaiBkuSebelumTw to set
     */
    public void setNilaiBkuSebelumTw(BigDecimal nilaiBkuSebelumTw) {
        this.nilaiBkuSebelumTw = nilaiBkuSebelumTw;
    }

}
