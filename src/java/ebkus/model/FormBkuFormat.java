package ebkus.model;

import java.math.BigDecimal;
import javax.validation.Valid;

/**
 *
 * @author Anita
 */
public class FormBkuFormat extends BaseModel {

    private Integer id;

    @Valid
    private Pengguna pengguna;
    private Skpd skpd;
    private Integer idskpd;
    private String tahun;

    //trjourumumskpd
    private Integer tahunAngg;
    private String tglPosting;
    private String tglPostingAwal;
    private Integer idBas;
    private String kodeskpd;
    private String namaskpd;

    private String bulan;
    private String perubahan;
    private String kodeBulan;
    private String ketBulan;

    // tambahan untuk akun
    private String namaakun;
    private String kodeakun;
    private String ketskpd;

    //tambahan untuk kegiatan
    private String idKegiatan;
    private String namaKegiatan;

    //tambahan untuk get kecamatan
    private Integer idWilayah;
    private String kodeWilayah;
    private String namaWilayah;
    private String nipSisdik;
    private String namaSisdik;

    @Valid
    private Sekolah sekolah;
    private Integer idsekolah;
    private BigDecimal saldoawal;

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
     * @return the ketskpd
     */
    public String getKetskpd() {
        return ketskpd;
    }

    /**
     * @param ketskpd the ketskpd to set
     */
    public void setKetskpd(String ketskpd) {
        this.ketskpd = ketskpd;
    }

    /**
     * @return the tglPostingAwal
     */
    public String getTglPostingAwal() {
        return tglPostingAwal;
    }

    /**
     * @param tglPostingAwal the tglPostingAwal to set
     */
    public void setTglPostingAwal(String tglPostingAwal) {
        this.tglPostingAwal = tglPostingAwal;
    }

    /**
     * @return the kodeskpd
     */
    public String getKodeskpd() {
        return kodeskpd;
    }

    /**
     * @param kodeskpd the kodeskpd to set
     */
    public void setKodeskpd(String kodeskpd) {
        this.kodeskpd = kodeskpd;
    }

    /**
     * @return the namaskpd
     */
    public String getNamaskpd() {
        return namaskpd;
    }

    /**
     * @param namaskpd the namaskpd to set
     */
    public void setNamaskpd(String namaskpd) {
        this.namaskpd = namaskpd;
    }

    /**
     * @return the pengguna
     */
    public Pengguna getPengguna() {
        return pengguna;
    }

    /**
     * @param pengguna the pengguna to set
     */
    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
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
     * @return the perubahan
     */
    public String getPerubahan() {
        return perubahan;
    }

    /**
     * @param perubahan the perubahan to set
     */
    public void setPerubahan(String perubahan) {
        this.perubahan = perubahan;
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
     * @return the ketBulan
     */
    public String getKetBulan() {
        return ketBulan;
    }

    /**
     * @param ketBulan the ketBulan to set
     */
    public void setKetBulan(String ketBulan) {
        this.ketBulan = ketBulan;
    }

    /**
     * @return the saldoawal
     */
    public BigDecimal getSaldoawal() {
        return saldoawal;
    }

    /**
     * @param saldoawal the saldoawal to set
     */
    public void setSaldoawal(BigDecimal saldoawal) {
        this.saldoawal = saldoawal;
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
     * @return the idKegiatan
     */
    public String getIdKegiatan() {
        return idKegiatan;
    }

    /**
     * @param idKegiatan the idKegiatan to set
     */
    public void setIdKegiatan(String idKegiatan) {
        this.idKegiatan = idKegiatan;
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
     * @return the idWilayah
     */
    public Integer getIdWilayah() {
        return idWilayah;
    }

    /**
     * @param idWilayah the idWilayah to set
     */
    public void setIdWilayah(Integer idWilayah) {
        this.idWilayah = idWilayah;
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
     * @return the namaWilayah
     */
    public String getNamaWilayah() {
        return namaWilayah;
    }

    /**
     * @param namaWilayah the namaWilayah to set
     */
    public void setNamaWilayah(String namaWilayah) {
        this.namaWilayah = namaWilayah;
    }

    /**
     * @return the nipSisdik
     */
    public String getNipSisdik() {
        return nipSisdik;
    }

    /**
     * @param nipSisdik the nipSisdik to set
     */
    public void setNipSisdik(String nipSisdik) {
        this.nipSisdik = nipSisdik;
    }

    /**
     * @return the namaSisdik
     */
    public String getNamaSisdik() {
        return namaSisdik;
    }

    /**
     * @param namaSisdik the namaSisdik to set
     */
    public void setNamaSisdik(String namaSisdik) {
        this.namaSisdik = namaSisdik;
    }

}
