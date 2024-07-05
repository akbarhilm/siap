package ebkus.model;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author zaenab
 */
public class User extends BaseModel {

    private Integer idPengguna;
    private String kodeGroup;
    private String kodeOtoritas;
    private String idNrk;
    private String idSandi;
    private String ipAddress;
    private Integer idSekolah;
    private String namaEmail;
    private String noHp;
    private String kodeHpaktif;
    private String nipPengguna;
    private String namaPengguna;
    private String jabatanPengguna;
    private String kodeAktif;
    private Integer idSkpd;
    private String namaSekolahPendek;

    public String getNamaSekolahPendek() {
        return namaSekolahPendek;
    }

    public void setNamaSekolahPendek(String namaSekolahPendek) {
        this.namaSekolahPendek = namaSekolahPendek;
    }

   
    
    
    
    public Integer getIdPengguna() {
        return idPengguna;
    }

    public void setIdPengguna(Integer idPengguna) {
        this.idPengguna = idPengguna;
    }

    public String getKodeGroup() {
        return kodeGroup;
    }

    public void setKodeGroup(String kodeGroup) {
        this.kodeGroup = kodeGroup;
    }

    public String getKodeOtoritas() {
        return kodeOtoritas;
    }

    public void setKodeOtoritas(String kodeOtoritas) {
        this.kodeOtoritas = kodeOtoritas;
    }

    public String getIdNrk() {
        return idNrk;
    }

    public void setIdNrk(String idNrk) {
        this.idNrk = idNrk;
    }

    public String getIdSandi() {
        return idSandi;
    }

    public void setIdSandi(String idSandi) {
        this.idSandi = idSandi;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Integer getIdSekolah() {
        return idSekolah;
    }

    public void setIdSekolah(Integer idSekolah) {
        this.idSekolah = idSekolah;
    }

    public String getNamaEmail() {
        return namaEmail;
    }

    public void setNamaEmail(String namaEmail) {
        this.namaEmail = namaEmail;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public String getKodeHpaktif() {
        return kodeHpaktif;
    }

    public void setKodeHpaktif(String kodeHpaktif) {
        this.kodeHpaktif = kodeHpaktif;
    }

    public String getNipPengguna() {
        return nipPengguna;
    }

    public void setNipPengguna(String nipPengguna) {
        this.nipPengguna = nipPengguna;
    }

    public String getNamaPengguna() {
        return namaPengguna;
    }

    public void setNamaPengguna(String namaPengguna) {
        this.namaPengguna = namaPengguna;
    }

    public String getJabatanPengguna() {
        return jabatanPengguna;
    }

    public void setJabatanPengguna(String jabatanPengguna) {
        this.jabatanPengguna = jabatanPengguna;
    }

    public String getKodeAktif() {
        return kodeAktif;
    }

    public void setKodeAktif(String kodeAktif) {
        this.kodeAktif = kodeAktif;
    }

    public Integer getIdSkpd() {
        return idSkpd;
    }

    public void setIdSkpd(Integer idSkpd) {
        this.idSkpd = idSkpd;
    }

 
    
}
