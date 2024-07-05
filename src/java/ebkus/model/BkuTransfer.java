/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.model;

import java.math.BigDecimal;
import javax.validation.Valid;

/**
 *
 * @author Zainab
 */
public class BkuTransfer extends BaseModel {

    private Integer id;

    @Valid
    private Integer idBku;
    private Integer idskpd;
    private Integer idsekolah;
    private Integer noBku;
    private Integer noBkuMohon;
    private String tahun;
    private String tglPosting;
    private String kodeTransaksi;
    private String noDok;
    private BigDecimal nilaiTransfer;
    private String norekPengirim;
    private String namaPengirim;
    private String norekTujuan;
    private String namaTujuan;
    private String kodeBank;
    private String namaBank;
    private String kodeVA;
    private String npwp;
    private String namaWp;
    private String alamatWp;
    private String kotaWp;
    private String akunPajak;
    private String setorPajak;
    private String masaPajak;
    private String tahunPajak;
    private String tglProses;
    private String nomorRef;
    private String kodeStan;
    private String wilayah;
    private String msgKirim;
    private String bit4;
    private String bit11;
    private String bit12;
    private String bit13;
    private String bit37;
    private String namaSekolah;
    private String kodeAkun;
    private String kodeKomponen;
    private String uraian;
    private String npsn;
    private String statusBank;
    private String msgTerimaBank;
    private String trxTerimaBank;
    private String token;
    private String kodeSumbdana;
    private String pesanError;
    private String kodeAction;
    private String bit62;

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
     * @return the noDok
     */
    public String getNoDok() {
        return noDok;
    }

    /**
     * @param noDok the noDok to set
     */
    public void setNoDok(String noDok) {
        this.noDok = noDok;
    }

    /**
     * @return the nilaiTransfer
     */
    public BigDecimal getNilaiTransfer() {
        return nilaiTransfer;
    }

    /**
     * @param nilaiTransfer the nilaiTransfer to set
     */
    public void setNilaiTransfer(BigDecimal nilaiTransfer) {
        this.nilaiTransfer = nilaiTransfer;
    }

    /**
     * @return the norekPengirim
     */
    public String getNorekPengirim() {
        return norekPengirim;
    }

    /**
     * @param norekPengirim the norekPengirim to set
     */
    public void setNorekPengirim(String norekPengirim) {
        this.norekPengirim = norekPengirim;
    }

    /**
     * @return the namaPengirim
     */
    public String getNamaPengirim() {
        return namaPengirim;
    }

    /**
     * @param namaPengirim the namaPengirim to set
     */
    public void setNamaPengirim(String namaPengirim) {
        this.namaPengirim = namaPengirim;
    }

    /**
     * @return the norekTujuan
     */
    public String getNorekTujuan() {
        return norekTujuan;
    }

    /**
     * @param norekTujuan the norekTujuan to set
     */
    public void setNorekTujuan(String norekTujuan) {
        this.norekTujuan = norekTujuan;
    }

    /**
     * @return the namaTujuan
     */
    public String getNamaTujuan() {
        return namaTujuan;
    }

    /**
     * @param namaTujuan the namaTujuan to set
     */
    public void setNamaTujuan(String namaTujuan) {
        this.namaTujuan = namaTujuan;
    }

    /**
     * @return the kodeBank
     */
    public String getKodeBank() {
        return kodeBank;
    }

    /**
     * @param kodeBank the kodeBank to set
     */
    public void setKodeBank(String kodeBank) {
        this.kodeBank = kodeBank;
    }

    /**
     * @return the namaBank
     */
    public String getNamaBank() {
        return namaBank;
    }

    /**
     * @param namaBank the namaBank to set
     */
    public void setNamaBank(String namaBank) {
        this.namaBank = namaBank;
    }

    /**
     * @return the kodeVA
     */
    public String getKodeVA() {
        return kodeVA;
    }

    /**
     * @param kodeVA the kodeVA to set
     */
    public void setKodeVA(String kodeVA) {
        this.kodeVA = kodeVA;
    }

    /**
     * @return the npwp
     */
    public String getNpwp() {
        return npwp;
    }

    /**
     * @param npwp the npwp to set
     */
    public void setNpwp(String npwp) {
        this.npwp = npwp;
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
     * @return the setorPajak
     */
    public String getSetorPajak() {
        return setorPajak;
    }

    /**
     * @param setorPajak the setorPajak to set
     */
    public void setSetorPajak(String setorPajak) {
        this.setorPajak = setorPajak;
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
     * @return the tglProses
     */
    public String getTglProses() {
        return tglProses;
    }

    /**
     * @param tglProses the tglProses to set
     */
    public void setTglProses(String tglProses) {
        this.tglProses = tglProses;
    }

    /**
     * @return the nomorRef
     */
    public String getNomorRef() {
        return nomorRef;
    }

    /**
     * @param nomorRef the nomorRef to set
     */
    public void setNomorRef(String nomorRef) {
        this.nomorRef = nomorRef;
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
     * @return the wilayah
     */
    public String getWilayah() {
        return wilayah;
    }

    /**
     * @param wilayah the wilayah to set
     */
    public void setWilayah(String wilayah) {
        this.wilayah = wilayah;
    }

    /**
     * @return the msgKirim
     */
    public String getMsgKirim() {
        return msgKirim;
    }

    /**
     * @param msgKirim the msgKirim to set
     */
    public void setMsgKirim(String msgKirim) {
        this.msgKirim = msgKirim;
    }

    /**
     * @return the bit4
     */
    public String getBit4() {
        return bit4;
    }

    /**
     * @param bit4 the bit4 to set
     */
    public void setBit4(String bit4) {
        this.bit4 = bit4;
    }

    /**
     * @return the bit11
     */
    public String getBit11() {
        return bit11;
    }

    /**
     * @param bit11 the bit11 to set
     */
    public void setBit11(String bit11) {
        this.bit11 = bit11;
    }

    /**
     * @return the bit12
     */
    public String getBit12() {
        return bit12;
    }

    /**
     * @param bit12 the bit12 to set
     */
    public void setBit12(String bit12) {
        this.bit12 = bit12;
    }

    /**
     * @return the bit13
     */
    public String getBit13() {
        return bit13;
    }

    /**
     * @param bit13 the bit13 to set
     */
    public void setBit13(String bit13) {
        this.bit13 = bit13;
    }

    /**
     * @return the bit37
     */
    public String getBit37() {
        return bit37;
    }

    /**
     * @param bit37 the bit37 to set
     */
    public void setBit37(String bit37) {
        this.bit37 = bit37;
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
     * @return the statusBank
     */
    public String getStatusBank() {
        return statusBank;
    }

    /**
     * @param statusBank the statusBank to set
     */
    public void setStatusBank(String statusBank) {
        this.statusBank = statusBank;
    }

    /**
     * @return the msgTerimaBank
     */
    public String getMsgTerimaBank() {
        return msgTerimaBank;
    }

    /**
     * @param msgTerimaBank the msgTerimaBank to set
     */
    public void setMsgTerimaBank(String msgTerimaBank) {
        this.msgTerimaBank = msgTerimaBank;
    }

    /**
     * @return the trxTerimaBank
     */
    public String getTrxTerimaBank() {
        return trxTerimaBank;
    }

    /**
     * @param trxTerimaBank the trxTerimaBank to set
     */
    public void setTrxTerimaBank(String trxTerimaBank) {
        this.trxTerimaBank = trxTerimaBank;
    }

    /**
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token the token to set
     */
    public void setToken(String token) {
        this.token = token;
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
     * @return the pesanError
     */
    public String getPesanError() {
        return pesanError;
    }

    /**
     * @param pesanError the pesanError to set
     */
    public void setPesanError(String pesanError) {
        this.pesanError = pesanError;
    }

    /**
     * @return the kodeAction
     */
    public String getKodeAction() {
        return kodeAction;
    }

    /**
     * @param kodeAction the kodeAction to set
     */
    public void setKodeAction(String kodeAction) {
        this.kodeAction = kodeAction;
    }

    /**
     * @return the bit62
     */
    public String getBit62() {
        return bit62;
    }

    /**
     * @param bit62 the bit62 to set
     */
    public void setBit62(String bit62) {
        this.bit62 = bit62;
    }

}
