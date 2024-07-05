/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import javax.validation.Valid;

/**
 *
 * @author Zainab
 */
public class BukuKasUmum extends BaseModel {

    /**
     * @return the persen
     */
    public BigDecimal getPersen() {
        return persen;
    }

    /**
     * @param persen the persen to set
     */
    public void setPersen(BigDecimal persen) {
        this.persen = persen;
    }

    private Integer id;

    @Valid
    private Skpd skpd;
    private Sekolah sekolah;
    private Integer idskpd;
    private Integer idsekolah;
    private Integer idBaBatal;
    private String tahun;

    private Integer tahunAngg;
    private String tglPosting;
    private String noDok;
    private Date tglDok;
    private String noBukti;
    private String noBuktiDok;
    private String uraianBukti;

    private Integer idBku;
    private Integer idBas;
    private Integer idKegiatan;
    private String ketKegiatan;
    private String kodeKeg;
    private String namaKeg;
    private String bulan;
    private String kodeBulan;
    private String bulanPosting;
    private String kodeTransaksi;
    private String kodeSaldoAwal;
    private String idTransaksi;
    private String bkuStatus;
    private BigDecimal nilaiMasuk;
    private BigDecimal nilaiKeluar;
    private BigDecimal saldoKas;
    private BigDecimal nilaiSisa;
    private BigDecimal nilaiAnggaran;
    private BigDecimal nilaiBkuSebelum;
    private BigDecimal nilaiBkuInput;
    private BigDecimal nilaiSpj;

    private String namaakun;
    private String kodeakun;
    private String ketakun;
    private String jenis;
    private String beban;

    private String inboxFile;
    private String namaPptk;
    private String nipPptk;
    private Integer noBKU;
    private String uraian;
    private String kodeWilayah;
    private String ketWilayah;
    private String kodePembayaran;
    private String jenisPembayaran;
    private String kodeUangPersediaan;

    private BigDecimal nilaiBku;
    private String kodeTglTutup;
    private String kodeKoreksi;
    private BigDecimal sisaPajak;
    private BigDecimal saldoAwalPajak;

    private BigDecimal nilaiSetor;
    private String tglValidasi;
    private BigDecimal nilaiPajak;
    private BigDecimal nilaiSpjNetto;
    private Integer noBkuSpj;
    private String noBkuPj;
    private Integer idBkuRef;
    private String noBkuRef;
    private String kodeSumbdana;

    private Kegiatan kegiatan;
    private String nrkPptk;
    private Integer noBkuMohon;
    private String kodeBank;
    private String kodeBankTf;
    private String namaBank;
    private String norekBank;
    private String alamatBank;
    private String npwp;
    private String namaNpwp;
    private String alamatNpwp;
    private String kotaNpwp;
    private String namaRekan;
    private String kodeApprove;

    private List<BkuRinci> listBkuRinci;
    private BkuRinci bkuRinci;
    private Integer idRinci;
    private String kodeSetor;
    private Integer idBl;
    private BigDecimal paguAkb;
    private String kodeVA;
    private String kodeTalangan;
    private String bulanTagihan;
    private String idMcb;
    private String mcb;
    private String masaPajak;
    private String tahunPajak;
    private String kitas;
    private String triwulan;
    private String triwulanKoreksi;
    private String kodePajak;
    private String kodeBkuPajak;
    private String kodeTutup;
    private String statusSpj;
    private String statusBank;
    private Integer banyakPajak;
    private String kodeRetur;
    private String kodeBatalTf;
    private String statusBpn;
    private String kodeKjs;
    private String kodeMap;
    private String kodePNS;
    private String kodeSK;
    private String kodePKP;
    private String kodePeriode;
    private String kodeBelanja;
    private String kodePegawai;
    private String kodePTKP;
    private BigDecimal persen;
    private Integer idParam;

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
     * @return the noBukti
     */
    public String getNoBukti() {
        return noBukti;
    }

    /**
     * @param noBukti the noBukti to set
     */
    public void setNoBukti(String noBukti) {
        this.noBukti = noBukti;
    }

    /**
     * @return the noBuktiDok
     */
    public String getNoBuktiDok() {
        return noBuktiDok;
    }

    /**
     * @param noBuktiDok the noBuktiDok to set
     */
    public void setNoBuktiDok(String noBuktiDok) {
        this.noBuktiDok = noBuktiDok;
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
     * @return the jenis
     */
    public String getJenis() {
        return jenis;
    }

    /**
     * @param jenis the jenis to set
     */
    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    /**
     * @return the beban
     */
    public String getBeban() {
        return beban;
    }

    /**
     * @param beban the beban to set
     */
    public void setBeban(String beban) {
        this.beban = beban;
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
     * @return the uraianBukti
     */
    public String getUraianBukti() {
        return uraianBukti;
    }

    /**
     * @param uraianBukti the uraianBukti to set
     */
    public void setUraianBukti(String uraianBukti) {
        this.uraianBukti = uraianBukti;
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
     * @return the idTransaksi
     */
    public String getIdTransaksi() {
        return idTransaksi;
    }

    /**
     * @param idTransaksi the idTransaksi to set
     */
    public void setIdTransaksi(String idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    /**
     * @return the tglDok
     */
    public Date getTglDok() {
        return tglDok;
    }

    /**
     * @param tglDok the tglDok to set
     */
    public void setTglDok(Date tglDok) {
        this.tglDok = tglDok;
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
     * @return the bkuStatus
     */
    public String getBkuStatus() {
        return bkuStatus;
    }

    /**
     * @param bkuStatus the bkuStatus to set
     */
    public void setBkuStatus(String bkuStatus) {
        this.bkuStatus = bkuStatus;
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
     * @return the saldoKas
     */
    public BigDecimal getSaldoKas() {
        return saldoKas;
    }

    /**
     * @param saldoKas the saldoKas to set
     */
    public void setSaldoKas(BigDecimal saldoKas) {
        this.saldoKas = saldoKas;
    }

    /**
     * @return the kodeBulan
     */
    public String getKodeBulan() {
        return kodeBulan;
    }

    /**
     * @param kodeBulan the kodeBulan to set
     */
    public void setKodeBulan(String kodeBulan) {
        this.kodeBulan = kodeBulan;
    }

    /**
     * @return the bulanPosting
     */
    public String getBulanPosting() {
        return bulanPosting;
    }

    /**
     * @param bulanPosting the bulanPosting to set
     */
    public void setBulanPosting(String bulanPosting) {
        this.bulanPosting = bulanPosting;
    }

    /**
     * @return the inboxFile
     */
    public String getInboxFile() {
        return inboxFile;
    }

    /**
     * @param inboxFile the inboxFile to set
     */
    public void setInboxFile(String inboxFile) {
        this.inboxFile = inboxFile;
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
     * @return the noBKU
     */
    public Integer getNoBKU() {
        return noBKU;
    }

    /**
     * @param noBKU the noBKU to set
     */
    public void setNoBKU(Integer noBKU) {
        this.noBKU = noBKU;
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
     * @return the kodeWilayah
     */
    public String getKodeWilayah() {
        return kodeWilayah;
    }

    /**
     * @param kodeWilayah the kodeWilayah to set
     */
    public void setKodeWilayah(String kodeWilayah) {
        this.kodeWilayah = kodeWilayah;
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
     * @return the kodePembayaran
     */
    public String getKodePembayaran() {
        return kodePembayaran;
    }

    /**
     * @param kodePembayaran the kodePembayaran to set
     */
    public void setKodePembayaran(String kodePembayaran) {
        this.kodePembayaran = kodePembayaran;
    }

    /**
     * @return the jenisPembayaran
     */
    public String getJenisPembayaran() {
        return jenisPembayaran;
    }

    /**
     * @param jenisPembayaran the jenisPembayaran to set
     */
    public void setJenisPembayaran(String jenisPembayaran) {
        this.jenisPembayaran = jenisPembayaran;
    }

    /**
     * @return the kodeUangPersediaan
     */
    public String getKodeUangPersediaan() {
        return kodeUangPersediaan;
    }

    /**
     * @param kodeUangPersediaan the kodeUangPersediaan to set
     */
    public void setKodeUangPersediaan(String kodeUangPersediaan) {
        this.kodeUangPersediaan = kodeUangPersediaan;
    }

    /**
     * @return the nilaiBku
     */
    public BigDecimal getNilaiBku() {
        return nilaiBku;
    }

    /**
     * @param nilaiBku the nilaiBku to set
     */
    public void setNilaiBku(BigDecimal nilaiBku) {
        this.nilaiBku = nilaiBku;
    }

    /**
     * @return the kodeTglTutup
     */
    public String getKodeTglTutup() {
        return kodeTglTutup;
    }

    /**
     * @param kodeTglTutup the kodeTglTutup to set
     */
    public void setKodeTglTutup(String kodeTglTutup) {
        this.kodeTglTutup = kodeTglTutup;
    }

    /**
     * @return the kodeKoreksi
     */
    public String getKodeKoreksi() {
        return kodeKoreksi;
    }

    /**
     * @param kodeKoreksi the kodeKoreksi to set
     */
    public void setKodeKoreksi(String kodeKoreksi) {
        this.kodeKoreksi = kodeKoreksi;
    }

    /**
     * @return the ketWilayah
     */
    public String getKetWilayah() {
        return ketWilayah;
    }

    /**
     * @param ketWilayah the ketWilayah to set
     */
    public void setKetWilayah(String ketWilayah) {
        this.ketWilayah = ketWilayah;
    }

    /**
     * @return the sisaPajak
     */
    public BigDecimal getSisaPajak() {
        return sisaPajak;
    }

    /**
     * @param sisaPajak the sisaPajak to set
     */
    public void setSisaPajak(BigDecimal sisaPajak) {
        this.sisaPajak = sisaPajak;
    }

    /**
     * @return the saldoAwalPajak
     */
    public BigDecimal getSaldoAwalPajak() {
        return saldoAwalPajak;
    }

    /**
     * @param saldoAwalPajak the saldoAwalPajak to set
     */
    public void setSaldoAwalPajak(BigDecimal saldoAwalPajak) {
        this.saldoAwalPajak = saldoAwalPajak;
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
     * @return the tglValidasi
     */
    public String getTglValidasi() {
        return tglValidasi;
    }

    /**
     * @param tglValidasi the tglValidasi to set
     */
    public void setTglValidasi(String tglValidasi) {
        this.tglValidasi = tglValidasi;
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
     * @return the nilaiSpjNetto
     */
    public BigDecimal getNilaiSpjNetto() {
        return nilaiSpjNetto;
    }

    /**
     * @param nilaiSpjNetto the nilaiSpjNetto to set
     */
    public void setNilaiSpjNetto(BigDecimal nilaiSpjNetto) {
        this.nilaiSpjNetto = nilaiSpjNetto;
    }

    /**
     * @return the noBkuSpj
     */
    public Integer getNoBkuSpj() {
        return noBkuSpj;
    }

    /**
     * @param noBkuSpj the noBkuSpj to set
     */
    public void setNoBkuSpj(Integer noBkuSpj) {
        this.noBkuSpj = noBkuSpj;
    }

    /**
     * @return the noBkuPj
     */
    public String getNoBkuPj() {
        return noBkuPj;
    }

    /**
     * @param noBkuPj the noBkuPj to set
     */
    public void setNoBkuPj(String noBkuPj) {
        this.noBkuPj = noBkuPj;
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
     * @return the noBkuRef
     */
    public String getNoBkuRef() {
        return noBkuRef;
    }

    /**
     * @param noBkuRef the noBkuRef to set
     */
    public void setNoBkuRef(String noBkuRef) {
        this.noBkuRef = noBkuRef;
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
     * @return the kegiatan
     */
    public Kegiatan getKegiatan() {
        return kegiatan;
    }

    /**
     * @param kegiatan the kegiatan to set
     */
    public void setKegiatan(Kegiatan kegiatan) {
        this.kegiatan = kegiatan;
    }

    /**
     * @return the nrkPptk
     */
    public String getNrkPptk() {
        return nrkPptk;
    }

    /**
     * @param nrkPptk the nrkPptk to set
     */
    public void setNrkPptk(String nrkPptk) {
        this.nrkPptk = nrkPptk;
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
     * @return the alamatBank
     */
    public String getAlamatBank() {
        return alamatBank;
    }

    /**
     * @param alamatBank the alamatBank to set
     */
    public void setAlamatBank(String alamatBank) {
        this.alamatBank = alamatBank;
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
     * @return the namaRekan
     */
    public String getNamaRekan() {
        return namaRekan;
    }

    /**
     * @param namaRekan the namaRekan to set
     */
    public void setNamaRekan(String namaRekan) {
        this.namaRekan = namaRekan;
    }

    /**
     * @return the kodeApprove
     */
    public String getKodeApprove() {
        return kodeApprove;
    }

    /**
     * @param kodeApprove the kodeApprove to set
     */
    public void setKodeApprove(String kodeApprove) {
        this.kodeApprove = kodeApprove;
    }

    /**
     * @return the idRinci
     */
    public Integer getIdRinci() {
        return idRinci;
    }

    /**
     * @param idRinci the idRinci to set
     */
    public void setIdRinci(Integer idRinci) {
        this.idRinci = idRinci;
    }

    /**
     * @return the kodeSetor
     */
    public String getKodeSetor() {
        return kodeSetor;
    }

    /**
     * @param kodeSetor the kodeSetor to set
     */
    public void setKodeSetor(String kodeSetor) {
        this.kodeSetor = kodeSetor;
    }

    /**
     * @return the idBl
     */
    public Integer getIdBl() {
        return idBl;
    }

    /**
     * @param idBl the idBl to set
     */
    public void setIdBl(Integer idBl) {
        this.idBl = idBl;
    }

    /**
     * @return the paguAkb
     */
    public BigDecimal getPaguAkb() {
        return paguAkb;
    }

    /**
     * @param paguAkb the paguAkb to set
     */
    public void setPaguAkb(BigDecimal paguAkb) {
        this.paguAkb = paguAkb;
    }

    /**
     * @return the kodeBankTf
     */
    public String getKodeBankTf() {
        return kodeBankTf;
    }

    /**
     * @param kodeBankTf the kodeBankTf to set
     */
    public void setKodeBankTf(String kodeBankTf) {
        this.kodeBankTf = kodeBankTf;
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
     * @return the listBkuRinci
     */
    public List<BkuRinci> getListBkuRinci() {
        return listBkuRinci;
    }

    /**
     * @param listBkuRinci the listBkuRinci to set
     */
    public void setListBkuRinci(List<BkuRinci> listBkuRinci) {
        this.listBkuRinci = listBkuRinci;
    }

    /**
     * @return the bkuRinci
     */
    public BkuRinci getBkuRinci() {
        return bkuRinci;
    }

    /**
     * @param bkuRinci the bkuRinci to set
     */
    public void setBkuRinci(BkuRinci bkuRinci) {
        this.bkuRinci = bkuRinci;
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
     * @return the kitas
     */
    public String getKitas() {
        return kitas;
    }

    /**
     * @param kitas the kitas to set
     */
    public void setKitas(String kitas) {
        this.kitas = kitas;
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
     * @return the kodePajak
     */
    public String getKodePajak() {
        return kodePajak;
    }

    /**
     * @param kodePajak the kodePajak to set
     */
    public void setKodePajak(String kodePajak) {
        this.kodePajak = kodePajak;
    }

    /**
     * @return the kodeTutup
     */
    public String getKodeTutup() {
        return kodeTutup;
    }

    /**
     * @param kodeTutup the kodeTutup to set
     */
    public void setKodeTutup(String kodeTutup) {
        this.kodeTutup = kodeTutup;
    }

    /**
     * @return the kodeBkuPajak
     */
    public String getKodeBkuPajak() {
        return kodeBkuPajak;
    }

    /**
     * @param kodeBkuPajak the kodeBkuPajak to set
     */
    public void setKodeBkuPajak(String kodeBkuPajak) {
        this.kodeBkuPajak = kodeBkuPajak;
    }

    /**
     * @return the kodeTalangan
     */
    public String getKodeTalangan() {
        return kodeTalangan;
    }

    /**
     * @param kodeTalangan the kodeTalangan to set
     */
    public void setKodeTalangan(String kodeTalangan) {
        this.kodeTalangan = kodeTalangan;
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
     * @return the idMcb
     */
    public String getIdMcb() {
        return idMcb;
    }

    /**
     * @param idMcb the idMcb to set
     */
    public void setIdMcb(String idMcb) {
        this.idMcb = idMcb;
    }

    /**
     * @return the mcb
     */
    public String getMcb() {
        return mcb;
    }

    /**
     * @param mcb the mcb to set
     */
    public void setMcb(String mcb) {
        this.mcb = mcb;
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
     * @return the banyakPajak
     */
    public Integer getBanyakPajak() {
        return banyakPajak;
    }

    /**
     * @param banyakPajak the banyakPajak to set
     */
    public void setBanyakPajak(Integer banyakPajak) {
        this.banyakPajak = banyakPajak;
    }

    /**
     * @return the kodeRetur
     */
    public String getKodeRetur() {
        return kodeRetur;
    }

    /**
     * @param kodeRetur the kodeRetur to set
     */
    public void setKodeRetur(String kodeRetur) {
        this.kodeRetur = kodeRetur;
    }

    /**
     * @return the kodeSaldoAwal
     */
    public String getKodeSaldoAwal() {
        return kodeSaldoAwal;
    }

    /**
     * @param kodeSaldoAwal the kodeSaldoAwal to set
     */
    public void setKodeSaldoAwal(String kodeSaldoAwal) {
        this.kodeSaldoAwal = kodeSaldoAwal;
    }

    /**
     * @return the idBaBatal
     */
    public Integer getIdBaBatal() {
        return idBaBatal;
    }

    /**
     * @param idBaBatal the idBaBatal to set
     */
    public void setIdBaBatal(Integer idBaBatal) {
        this.idBaBatal = idBaBatal;
    }

    /**
     * @return the namaNpwp
     */
    public String getNamaNpwp() {
        return namaNpwp;
    }

    /**
     * @param namaNpwp the namaNpwp to set
     */
    public void setNamaNpwp(String namaNpwp) {
        this.namaNpwp = namaNpwp;
    }

    /**
     * @return the alamatNpwp
     */
    public String getAlamatNpwp() {
        return alamatNpwp;
    }

    /**
     * @param alamatNpwp the alamatNpwp to set
     */
    public void setAlamatNpwp(String alamatNpwp) {
        this.alamatNpwp = alamatNpwp;
    }

    /**
     * @return the kotaNpwp
     */
    public String getKotaNpwp() {
        return kotaNpwp;
    }

    /**
     * @param kotaNpwp the kotaNpwp to set
     */
    public void setKotaNpwp(String kotaNpwp) {
        this.kotaNpwp = kotaNpwp;
    }

    /**
     * @return the triwulanKoreksi
     */
    public String getTriwulanKoreksi() {
        return triwulanKoreksi;
    }

    /**
     * @param triwulanKoreksi the triwulanKoreksi to set
     */
    public void setTriwulanKoreksi(String triwulanKoreksi) {
        this.triwulanKoreksi = triwulanKoreksi;
    }

    /**
     * @return the kodeBatalTf
     */
    public String getKodeBatalTf() {
        return kodeBatalTf;
    }

    /**
     * @param kodeBatalTf the kodeBatalTf to set
     */
    public void setKodeBatalTf(String kodeBatalTf) {
        this.kodeBatalTf = kodeBatalTf;
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
     * @return the kodeKjs
     */
    public String getKodeKjs() {
        return kodeKjs;
    }

    /**
     * @param kodeKjs the kodeKjs to set
     */
    public void setKodeKjs(String kodeKjs) {
        this.kodeKjs = kodeKjs;
    }

    /**
     * @return the kodeMap
     */
    public String getKodeMap() {
        return kodeMap;
    }

    /**
     * @param kodeMap the kodeMap to set
     */
    public void setKodeMap(String kodeMap) {
        this.kodeMap = kodeMap;
    }

    /**
     * @return the kodePNS
     */
    public String getKodePNS() {
        return kodePNS;
    }

    /**
     * @param kodePNS the kodePNS to set
     */
    public void setKodePNS(String kodePNS) {
        this.kodePNS = kodePNS;
    }

    /**
     * @return the kodeSK
     */
    public String getKodeSK() {
        return kodeSK;
    }

    /**
     * @param kodeSK the kodeSK to set
     */
    public void setKodeSK(String kodeSK) {
        this.kodeSK = kodeSK;
    }

    /**
     * @return the kodePKP
     */
    public String getKodePKP() {
        return kodePKP;
    }

    /**
     * @param kodePKP the kodePKP to set
     */
    public void setKodePKP(String kodePKP) {
        this.kodePKP = kodePKP;
    }

    /**
     * @return the kodeBelanja
     */
    public String getKodeBelanja() {
        return kodeBelanja;
    }

    /**
     * @param kodeBelanja the kodeBelanja to set
     */
    public void setKodeBelanja(String kodeBelanja) {
        this.kodeBelanja = kodeBelanja;
    }

    /**
     * @return the kodePegawai
     */
    public String getKodePegawai() {
        return kodePegawai;
    }

    /**
     * @param kodePegawai the kodePegawai to set
     */
    public void setKodePegawai(String kodePegawai) {
        this.kodePegawai = kodePegawai;
    }

    /**
     * @return the idParam
     */
    public Integer getIdParam() {
        return idParam;
    }

    /**
     * @param idParam the idParam to set
     */
    public void setIdParam(Integer idParam) {
        this.idParam = idParam;
    }

    /**
     * @return the kodePTKP
     */
    public String getKodePTKP() {
        return kodePTKP;
    }

    /**
     * @param kodePTKP the kodePTKP to set
     */
    public void setKodePTKP(String kodePTKP) {
        this.kodePTKP = kodePTKP;
    }

    /**
     * @return the kodePeriode
     */
    public String getKodePeriode() {
        return kodePeriode;
    }

    /**
     * @param kodePeriode the kodePeriode to set
     */
    public void setKodePeriode(String kodePeriode) {
        this.kodePeriode = kodePeriode;
    }
}
