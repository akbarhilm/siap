package ebkus.model;

import java.math.BigDecimal;
import javax.validation.constraints.NotNull;

/**
 *
 * @author User
 */
public class Kegiatan extends BaseModel {

    @NotNull(message = "Kegiatan Wajib di isi")
    
    private Integer idKegiatan;
    private String kodeKegiatan;
    private String namaKegiatan;
    private Integer idBidang;
    private String kodeBidang;
    private String ketBidang;
    private Integer idSnp;
    private String kodeSnp;
    private String ketSnp;
    private String kodeSumbdana;
    private String ketSumbdana;
    private String tahun;
    private Integer idBl;
    private BigDecimal paguAkb;
    
    private Sekolah sekolah;

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
     * @return the kodeKegiatan
     */
    public String getKodeKegiatan() {
        return kodeKegiatan;
    }

    /**
     * @param kodeKegiatan the kodeKegiatan to set
     */
    public void setKodeKegiatan(String kodeKegiatan) {
        this.kodeKegiatan = kodeKegiatan;
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
     * @return the idBidang
     */
    public Integer getIdBidang() {
        return idBidang;
    }

    /**
     * @param idBidang the idBidang to set
     */
    public void setIdBidang(Integer idBidang) {
        this.idBidang = idBidang;
    }

    /**
     * @return the kodeBidang
     */
    public String getKodeBidang() {
        return kodeBidang;
    }

    /**
     * @param kodeBidang the kodeBidang to set
     */
    public void setKodeBidang(String kodeBidang) {
        this.kodeBidang = kodeBidang;
    }

    /**
     * @return the ketBidang
     */
    public String getKetBidang() {
        return ketBidang;
    }

    /**
     * @param ketBidang the ketBidang to set
     */
    public void setKetBidang(String ketBidang) {
        this.ketBidang = ketBidang;
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
     * @return the ketSumbdana
     */
    public String getKetSumbdana() {
        return ketSumbdana;
    }

    /**
     * @param ketSumbdana the ketSumbdana to set
     */
    public void setKetSumbdana(String ketSumbdana) {
        this.ketSumbdana = ketSumbdana;
    }

    /**
     * @return the idSnp
     */
    public Integer getIdSnp() {
        return idSnp;
    }

    /**
     * @param idSnp the idSnp to set
     */
    public void setIdSnp(Integer idSnp) {
        this.idSnp = idSnp;
    }

    /**
     * @return the kodeSnp
     */
    public String getKodeSnp() {
        return kodeSnp;
    }

    /**
     * @param kodeSnp the kodeSnp to set
     */
    public void setKodeSnp(String kodeSnp) {
        this.kodeSnp = kodeSnp;
    }

    /**
     * @return the ketSnp
     */
    public String getKetSnp() {
        return ketSnp;
    }

    /**
     * @param ketSnp the ketSnp to set
     */
    public void setKetSnp(String ketSnp) {
        this.ketSnp = ketSnp;
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
    
      
  

   

}
