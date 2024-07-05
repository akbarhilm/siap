package ebkus.model;

import java.math.BigDecimal;
import javax.validation.constraints.NotNull;

/**
 *
 * @author User
 */
public class Bank extends BaseModel {

    private Integer idBank;
    private String kodeBank;
    private String namaBank;
    private String kodeBankTf;
    private String namaBankTf;

    
    
    /**
     * @return the idBank
     */
    public Integer getIdBank() {
        return idBank;
    }

    /**
     * @param idBank the idBank to set
     */
    public void setIdBank(Integer idBank) {
        this.idBank = idBank;
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
     * @return the namaBankTf
     */
    public String getNamaBankTf() {
        return namaBankTf;
    }

    /**
     * @param namaBankTf the namaBankTf to set
     */
    public void setNamaBankTf(String namaBankTf) {
        this.namaBankTf = namaBankTf;
    }
    
    
    
}
