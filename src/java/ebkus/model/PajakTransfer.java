/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.model;

import java.math.BigDecimal;
import java.sql.Date;
import javax.validation.Valid;

/**
 *
 * @author Zainab
 */
public class PajakTransfer extends BaseModel {

    private Integer id;

    @Valid
    private Sekolah sekolah;
    private BukuKasUmum bku;
    private Integer idskpd;
    private Integer idsekolah;
    private String tahun;
    private Integer idBku;
    private String npwpSekolah;
    private String namaWpSekolah;
    private String alamatWpSekolah;
    private String kotaWpSekolah;
    private String noBkuMohon;
    private String kodeTransaksi;
    private BigDecimal nilaiPajak;
    private String uraian;
    private String npwpRekanan;
    private String namaWp;
    private String alamatWp;
    private String kotaWp;
    private String norekBank;
    private String namaPengirimBank;
    private String kodeJenisSetor;
    private String akunPajak;
    private String masaPajak;
    private String tahunPajak;
    private String noSk;
    private String kodeBilling;
    private Date tglBillExp;
    private String tglBuku;
    private String statusBpn;
    private String npsn;
    private String namaSekolah;
    private Date tglBayar;
    private String ntb;
    private String ntpn;
    private String persenPot;
    private String bulkIdRequest;
    private String idRequest;
    private String idChannel;
    private String idResponse;
    private String namaKJS;
    private String namaMAP;
    private String nop;
    private String noIdentitas;
    private String kodeProses;
    private String kodeResponse;
    private String uraianResponse;
    private String kodeStan;
    private String kodeRequest;
    private String kodeApp;
    private Date tglTransmisi;
    private String idApp;
    private String tglBayarString;
    private String tglBillExpString;
    private String tglTransmisiString;

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
     * @return the npwpSekolah
     */
    public String getNpwpSekolah() {
        return npwpSekolah;
    }

    /**
     * @param npwpSekolah the npwpSekolah to set
     */
    public void setNpwpSekolah(String npwpSekolah) {
        this.npwpSekolah = npwpSekolah;
    }

    /**
     * @return the namaWpSekolah
     */
    public String getNamaWpSekolah() {
        return namaWpSekolah;
    }

    /**
     * @param namaWpSekolah the namaWpSekolah to set
     */
    public void setNamaWpSekolah(String namaWpSekolah) {
        this.namaWpSekolah = namaWpSekolah;
    }

    /**
     * @return the alamatWpSekolah
     */
    public String getAlamatWpSekolah() {
        return alamatWpSekolah;
    }

    /**
     * @param alamatWpSekolah the alamatWpSekolah to set
     */
    public void setAlamatWpSekolah(String alamatWpSekolah) {
        this.alamatWpSekolah = alamatWpSekolah;
    }

    /**
     * @return the kotaWpSekolah
     */
    public String getKotaWpSekolah() {
        return kotaWpSekolah;
    }

    /**
     * @param kotaWpSekolah the kotaWpSekolah to set
     */
    public void setKotaWpSekolah(String kotaWpSekolah) {
        this.kotaWpSekolah = kotaWpSekolah;
    }

    /**
     * @return the noBkuMohon
     */
    public String getNoBkuMohon() {
        return noBkuMohon;
    }

    /**
     * @param noBkuMohon the noBkuMohon to set
     */
    public void setNoBkuMohon(String noBkuMohon) {
        this.noBkuMohon = noBkuMohon;
    }

    /**
     * @return the nilaiPajak
     */
    public BigDecimal getNilaiPajak() {
        return nilaiPajak;
    }

    /**
     * @param nilaiPajak the nilaiPajak to set
     */
    public void setNilaiPajak(BigDecimal nilaiPajak) {
        this.nilaiPajak = nilaiPajak;
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
     * @return the npwpRekanan
     */
    public String getNpwpRekanan() {
        return npwpRekanan;
    }

    /**
     * @param npwpRekanan the npwpRekanan to set
     */
    public void setNpwpRekanan(String npwpRekanan) {
        this.npwpRekanan = npwpRekanan;
    }

    /**
     * @return the namaWp
     */
    public String getNamaWp() {
        return namaWp;
    }

    /**
     * @param namaWp the namaWp to set
     */
    public void setNamaWp(String namaWp) {
        this.namaWp = namaWp;
    }

    /**
     * @return the alamatWp
     */
    public String getAlamatWp() {
        return alamatWp;
    }

    /**
     * @param alamatWp the alamatWp to set
     */
    public void setAlamatWp(String alamatWp) {
        this.alamatWp = alamatWp;
    }

    /**
     * @return the kotaWp
     */
    public String getKotaWp() {
        return kotaWp;
    }

    /**
     * @param kotaWp the kotaWp to set
     */
    public void setKotaWp(String kotaWp) {
        this.kotaWp = kotaWp;
    }

    /**
     * @return the norekBank
     */
    public String getNorekBank() {
        return norekBank;
    }

    /**
     * @param norekBank the norekBank to set
     */
    public void setNorekBank(String norekBank) {
        this.norekBank = norekBank;
    }

    /**
     * @return the namaPengirimBank
     */
    public String getNamaPengirimBank() {
        return namaPengirimBank;
    }

    /**
     * @param namaPengirimBank the namaPengirimBank to set
     */
    public void setNamaPengirimBank(String namaPengirimBank) {
        this.namaPengirimBank = namaPengirimBank;
    }

    /**
     * @return the kodeJenisSetor
     */
    public String getKodeJenisSetor() {
        return kodeJenisSetor;
    }

    /**
     * @param kodeJenisSetor the kodeJenisSetor to set
     */
    public void setKodeJenisSetor(String kodeJenisSetor) {
        this.kodeJenisSetor = kodeJenisSetor;
    }

    /**
     * @return the akunPajak
     */
    public String getAkunPajak() {
        return akunPajak;
    }

    /**
     * @param akunPajak the akunPajak to set
     */
    public void setAkunPajak(String akunPajak) {
        this.akunPajak = akunPajak;
    }

    /**
     * @return the masaPajak
     */
    public String getMasaPajak() {
        return masaPajak;
    }

    /**
     * @param masaPajak the masaPajak to set
     */
    public void setMasaPajak(String masaPajak) {
        this.masaPajak = masaPajak;
    }

    /**
     * @return the tahunPajak
     */
    public String getTahunPajak() {
        return tahunPajak;
    }

    /**
     * @param tahunPajak the tahunPajak to set
     */
    public void setTahunPajak(String tahunPajak) {
        this.tahunPajak = tahunPajak;
    }

    /**
     * @return the noSk
     */
    public String getNoSk() {
        return noSk;
    }

    /**
     * @param noSk the noSk to set
     */
    public void setNoSk(String noSk) {
        this.noSk = noSk;
    }

    /**
     * @return the kodeBilling
     */
    public String getKodeBilling() {
        return kodeBilling;
    }

    /**
     * @param kodeBilling the kodeBilling to set
     */
    public void setKodeBilling(String kodeBilling) {
        this.kodeBilling = kodeBilling;
    }

    /**
     * @return the tglBillExp
     */
    public Date getTglBillExp() {
        return tglBillExp;
    }

    /**
     * @param tglBillExp the tglBillExp to set
     */
    public void setTglBillExp(Date tglBillExp) {
        this.tglBillExp = tglBillExp;
    }

    /**
     * @return the tglBuku
     */
    public String getTglBuku() {
        return tglBuku;
    }

    /**
     * @param tglBuku the tglBuku to set
     */
    public void setTglBuku(String tglBuku) {
        this.tglBuku = tglBuku;
    }

    /**
     * @return the statusBpn
     */
    public String getStatusBpn() {
        return statusBpn;
    }

    /**
     * @param statusBpn the statusBpn to set
     */
    public void setStatusBpn(String statusBpn) {
        this.statusBpn = statusBpn;
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
     * @return the tglBayar
     */
    public Date getTglBayar() {
        return tglBayar;
    }

    /**
     * @param tglBayar the tglBayar to set
     */
    public void setTglBayar(Date tglBayar) {
        this.tglBayar = tglBayar;
    }

    /**
     * @return the ntb
     */
    public String getNtb() {
        return ntb;
    }

    /**
     * @param ntb the ntb to set
     */
    public void setNtb(String ntb) {
        this.ntb = ntb;
    }

    /**
     * @return the ntpn
     */
    public String getNtpn() {
        return ntpn;
    }

    /**
     * @param ntpn the ntpn to set
     */
    public void setNtpn(String ntpn) {
        this.ntpn = ntpn;
    }

    /**
     * @return the bku
     */
    public BukuKasUmum getBku() {
        return bku;
    }

    /**
     * @param bku the bku to set
     */
    public void setBku(BukuKasUmum bku) {
        this.bku = bku;
    }

    /**
     * @return the persenPot
     */
    public String getPersenPot() {
        return persenPot;
    }

    /**
     * @param persenPot the persenPot to set
     */
    public void setPersenPot(String persenPot) {
        this.persenPot = persenPot;
    }

    /**
     * @return the bulkIdRequest
     */
    public String getBulkIdRequest() {
        return bulkIdRequest;
    }

    /**
     * @param bulkIdRequest the bulkIdRequest to set
     */
    public void setBulkIdRequest(String bulkIdRequest) {
        this.bulkIdRequest = bulkIdRequest;
    }

    /**
     * @return the idRequest
     */
    public String getIdRequest() {
        return idRequest;
    }

    /**
     * @param idRequest the idRequest to set
     */
    public void setIdRequest(String idRequest) {
        this.idRequest = idRequest;
    }

    /**
     * @return the idChannel
     */
    public String getIdChannel() {
        return idChannel;
    }

    /**
     * @param idChannel the idChannel to set
     */
    public void setIdChannel(String idChannel) {
        this.idChannel = idChannel;
    }

    /**
     * @return the idResponse
     */
    public String getIdResponse() {
        return idResponse;
    }

    /**
     * @param idResponse the idResponse to set
     */
    public void setIdResponse(String idResponse) {
        this.idResponse = idResponse;
    }

    /**
     * @return the namaKJS
     */
    public String getNamaKJS() {
        return namaKJS;
    }

    /**
     * @param namaKJS the namaKJS to set
     */
    public void setNamaKJS(String namaKJS) {
        this.namaKJS = namaKJS;
    }

    /**
     * @return the namaMAP
     */
    public String getNamaMAP() {
        return namaMAP;
    }

    /**
     * @param namaMAP the namaMAP to set
     */
    public void setNamaMAP(String namaMAP) {
        this.namaMAP = namaMAP;
    }

    /**
     * @return the nop
     */
    public String getNop() {
        return nop;
    }

    /**
     * @param nop the nop to set
     */
    public void setNop(String nop) {
        this.nop = nop;
    }

    /**
     * @return the noIdentitas
     */
    public String getNoIdentitas() {
        return noIdentitas;
    }

    /**
     * @param noIdentitas the noIdentitas to set
     */
    public void setNoIdentitas(String noIdentitas) {
        this.noIdentitas = noIdentitas;
    }

    /**
     * @return the kodeProses
     */
    public String getKodeProses() {
        return kodeProses;
    }

    /**
     * @param kodeProses the kodeProses to set
     */
    public void setKodeProses(String kodeProses) {
        this.kodeProses = kodeProses;
    }

    /**
     * @return the kodeResponse
     */
    public String getKodeResponse() {
        return kodeResponse;
    }

    /**
     * @param kodeResponse the kodeResponse to set
     */
    public void setKodeResponse(String kodeResponse) {
        this.kodeResponse = kodeResponse;
    }

    /**
     * @return the uraianResponse
     */
    public String getUraianResponse() {
        return uraianResponse;
    }

    /**
     * @param uraianResponse the uraianResponse to set
     */
    public void setUraianResponse(String uraianResponse) {
        this.uraianResponse = uraianResponse;
    }

    /**
     * @return the kodeStan
     */
    public String getKodeStan() {
        return kodeStan;
    }

    /**
     * @param kodeStan the kodeStan to set
     */
    public void setKodeStan(String kodeStan) {
        this.kodeStan = kodeStan;
    }

    /**
     * @return the kodeRequest
     */
    public String getKodeRequest() {
        return kodeRequest;
    }

    /**
     * @param kodeRequest the kodeRequest to set
     */
    public void setKodeRequest(String kodeRequest) {
        this.kodeRequest = kodeRequest;
    }

    /**
     * @return the kodeApp
     */
    public String getKodeApp() {
        return kodeApp;
    }

    /**
     * @param kodeApp the kodeApp to set
     */
    public void setKodeApp(String kodeApp) {
        this.kodeApp = kodeApp;
    }

    /**
     * @return the tglTransmisi
     */
    public Date getTglTransmisi() {
        return tglTransmisi;
    }

    /**
     * @param tglTransmisi the tglTransmisi to set
     */
    public void setTglTransmisi(Date tglTransmisi) {
        this.tglTransmisi = tglTransmisi;
    }

    /**
     * @return the idApp
     */
    public String getIdApp() {
        return idApp;
    }

    /**
     * @param idApp the idApp to set
     */
    public void setIdApp(String idApp) {
        this.idApp = idApp;
    }

    /**
     * @return the tglBayarString
     */
    public String getTglBayarString() {
        return tglBayarString;
    }

    /**
     * @param tglBayarString the tglBayarString to set
     */
    public void setTglBayarString(String tglBayarString) {
        this.tglBayarString = tglBayarString;
    }

    /**
     * @return the tglBillExpString
     */
    public String getTglBillExpString() {
        return tglBillExpString;
    }

    /**
     * @param tglBillExpString the tglBillExpString to set
     */
    public void setTglBillExpString(String tglBillExpString) {
        this.tglBillExpString = tglBillExpString;
    }

    /**
     * @return the tglTransmisiString
     */
    public String getTglTransmisiString() {
        return tglTransmisiString;
    }

    /**
     * @param tglTransmisiString the tglTransmisiString to set
     */
    public void setTglTransmisiString(String tglTransmisiString) {
        this.tglTransmisiString = tglTransmisiString;
    }

    @Override
    public String toString() {
        return "PajakTransfer{" + "id=" + id + ", sekolah=" + sekolah + ", bku=" + bku + ", idskpd=" + idskpd + ", idsekolah=" + idsekolah + ", tahun=" + tahun + ", idBku=" + idBku + ", npwpSekolah=" + npwpSekolah + ", namaWpSekolah=" + namaWpSekolah + ", alamatWpSekolah=" + alamatWpSekolah + ", kotaWpSekolah=" + kotaWpSekolah + ", noBkuMohon=" + noBkuMohon + ", kodeTransaksi=" + kodeTransaksi + ", nilaiPajak=" + nilaiPajak + ", uraian=" + uraian + ", npwpRekanan=" + npwpRekanan + ", namaWp=" + namaWp + ", alamatWp=" + alamatWp + ", kotaWp=" + kotaWp + ", norekBank=" + norekBank + ", namaPengirimBank=" + namaPengirimBank + ", kodeJenisSetor=" + kodeJenisSetor + ", akunPajak=" + akunPajak + ", masaPajak=" + masaPajak + ", tahunPajak=" + tahunPajak + ", noSk=" + noSk + ", kodeBilling=" + kodeBilling + ", tglBillExp=" + tglBillExp + ", tglBuku=" + tglBuku + ", statusBpn=" + statusBpn + ", npsn=" + npsn + ", namaSekolah=" + namaSekolah + ", tglBayar=" + tglBayar + ", ntb=" + ntb + ", ntpn=" + ntpn + ", persenPot=" + persenPot + ", bulkIdRequest=" + bulkIdRequest + ", idRequest=" + idRequest + ", idChannel=" + idChannel + ", idResponse=" + idResponse + ", namaKJS=" + namaKJS + ", namaMAP=" + namaMAP + ", nop=" + nop + ", noIdentitas=" + noIdentitas + ", kodeProses=" + kodeProses + ", kodeResponse=" + kodeResponse + ", uraianResponse=" + uraianResponse + ", kodeStan=" + kodeStan + ", kodeRequest=" + kodeRequest + ", kodeApp=" + kodeApp + ", tglTransmisi=" + tglTransmisi + ", idApp=" + idApp + ", tglBayarString=" + tglBayarString + ", tglBillExpString=" + tglBillExpString + ", tglTransmisiString=" + tglTransmisiString + '}';
    }

}
