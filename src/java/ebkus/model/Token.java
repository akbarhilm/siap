package ebkus.model;

import java.math.BigDecimal;

/**
 *
 * @author User
 */
public class Token extends BaseModel {

    private String tahun;
    private Integer idSekolah;
    private String token;
    private String nrk;
    private String dMohon;
    private String dMohonDki;
    private String dBerlaku;
    private String noBkuMohon;
    private String namaRekan;
    private String ketKegiatan;
    private BigDecimal nilaiBku;
    private BigDecimal nilaiNetto;
    private String uraian;
    private String kodeTransaksi;
    private String kodeSumbdana;
    private String kodeUs;
    private String dPayment;

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
     * @return the dMohon
     */
    public String getdMohon() {
        return dMohon;
    }

    /**
     * @param dMohon the dMohon to set
     */
    public void setdMohon(String dMohon) {
        this.dMohon = dMohon;
    }

    /**
     * @return the dBerlaku
     */
    public String getdBerlaku() {
        return dBerlaku;
    }

    /**
     * @param dBerlaku the dBerlaku to set
     */
    public void setdBerlaku(String dBerlaku) {
        this.dBerlaku = dBerlaku;
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
     * @return the dMohonDki
     */
    public String getdMohonDki() {
        return dMohonDki;
    }

    /**
     * @param dMohonDki the dMohonDki to set
     */
    public void setdMohonDki(String dMohonDki) {
        this.dMohonDki = dMohonDki;
    }

    /**
     * @return the nilaiNetto
     */
    public BigDecimal getNilaiNetto() {
        return nilaiNetto;
    }

    /**
     * @param nilaiNetto the nilaiNetto to set
     */
    public void setNilaiNetto(BigDecimal nilaiNetto) {
        this.nilaiNetto = nilaiNetto;
    }

    /**
     * @return the kodeUs
     */
    public String getKodeUs() {
        return kodeUs;
    }

    /**
     * @param kodeUs the kodeUs to set
     */
    public void setKodeUs(String kodeUs) {
        this.kodeUs = kodeUs;
    }

    /**
     * @return the dPayment
     */
    public String getdPayment() {
        return dPayment;
    }

    /**
     * @param dPayment the dPayment to set
     */
    public void setdPayment(String dPayment) {
        this.dPayment = dPayment;
    }

}
