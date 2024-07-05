package ebkus.model;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author zaenab
 */
public class PenggunaModul extends BaseModel {

    private Integer idModul;
    private Integer idPengguna;
    private Integer kodeDetail;
    private String noModul;
    private String namaModul;
    private String uraianModul;
    private String kodeOtoritas;

    public Integer getIdPengguna() {
        return idPengguna;
    }

    public void setIdPengguna(Integer idPengguna) {
        this.idPengguna = idPengguna;
    }

    
    
    public String getKodeOtoritas() {
        return kodeOtoritas;
    }

    public void setKodeOtoritas(String kodeOtoritas) {
        this.kodeOtoritas = kodeOtoritas;
    }
    
    

    public Integer getIdModul() {
        return idModul;
    }

    public void setIdModul(Integer idModul) {
        this.idModul = idModul;
    }

    public Integer getKodeDetail() {
        return kodeDetail;
    }

    public void setKodeDetail(Integer kodeDetail) {
        this.kodeDetail = kodeDetail;
    }

    public String getNoModul() {
        return noModul;
    }

    public void setNoModul(String noModul) {
        this.noModul = noModul;
    }

    public String getNamaModul() {
        return namaModul;
    }

    public void setNamaModul(String namaModul) {
        this.namaModul = namaModul;
    }

    public String getUraianModul() {
        return uraianModul;
    }

    public void setUraianModul(String uraianModul) {
        this.uraianModul = uraianModul;
    }
    
    
    
}
