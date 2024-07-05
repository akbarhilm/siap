package ebkus.model;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author zainab
 */
public class BapKasRinci extends BaseModel {

    private String tahun;
    private Sekolah sekolah;

    /*BAP KAS RINCI*/
    private BigDecimal nilaiBapKas;
    private String namaBapKas;
    private Integer idSekolahBAPKas;
    private Integer idSekolahBAPKasRinci;
    private String noBap;
    private String noUrut;
    

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
     * @return the nilaiBapKas
     */
    public BigDecimal getNilaiBapKas() {
        return nilaiBapKas;
    }

    /**
     * @param nilaiBapKas the nilaiBapKas to set
     */
    public void setNilaiBapKas(BigDecimal nilaiBapKas) {
        this.nilaiBapKas = nilaiBapKas;
    }

    /**
     * @return the namaBapKas
     */
    public String getNamaBapKas() {
        return namaBapKas;
    }

    /**
     * @param namaBapKas the namaBapKas to set
     */
    public void setNamaBapKas(String namaBapKas) {
        this.namaBapKas = namaBapKas;
    }

    /**
     * @return the noBap
     */
    public String getNoBap() {
        return noBap;
    }

    /**
     * @param noBap the noBap to set
     */
    public void setNoBap(String noBap) {
        this.noBap = noBap;
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
     * @return the idSekolahBAPKas
     */
    public Integer getIdSekolahBAPKas() {
        return idSekolahBAPKas;
    }

    /**
     * @param idSekolahBAPKas the idSekolahBAPKas to set
     */
    public void setIdSekolahBAPKas(Integer idSekolahBAPKas) {
        this.idSekolahBAPKas = idSekolahBAPKas;
    }

    /**
     * @return the idSekolahBAPKasRinci
     */
    public Integer getIdSekolahBAPKasRinci() {
        return idSekolahBAPKasRinci;
    }

    /**
     * @param idSekolahBAPKasRinci the idSekolahBAPKasRinci to set
     */
    public void setIdSekolahBAPKasRinci(Integer idSekolahBAPKasRinci) {
        this.idSekolahBAPKasRinci = idSekolahBAPKasRinci;
    }

}
