package ebkus.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import javax.validation.Valid;

public class Monitoring extends BaseModel {

    private Integer id;
    @Valid
    private Skpd skpd;
    private String tahun;
    private String kodeLap;
    private String kodeakses;
    private String kodeTapd;
    private Integer idPgun;
    

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
     * @return the kodeakses
     */
    public String getKodeakses() {
        return kodeakses;
    }

    /**
     * @param kodeakses the kodeakses to set
     */
    public void setKodeakses(String kodeakses) {
        this.kodeakses = kodeakses;
    }

    /**
     * @return the kodeLap
     */
    public String getKodeLap() {
        return kodeLap;
    }

    /**
     * @param kodeLap the kodeLap to set
     */
    public void setKodeLap(String kodeLap) {
        this.kodeLap = kodeLap;
    }

    /**
     * @return the kodeTapd
     */
    public String getKodeTapd() {
        return kodeTapd;
    }

    /**
     * @param kodeTapd the kodeTapd to set
     */
    public void setKodeTapd(String kodeTapd) {
        this.kodeTapd = kodeTapd;
    }

    /**
     * @return the idPgun
     */
    public Integer getIdPgun() {
        return idPgun;
    }

    /**
     * @param idPgun the idPgun to set
     */
    public void setIdPgun(Integer idPgun) {
        this.idPgun = idPgun;
    }

}
