package ebkus.model;

import java.util.List;

/**
 *
 * @author User
 */
public class PASekolah implements java.io.Serializable {

    private String idSekolah;
    private String idPengguna;
    private String npsn;
    private String namaSekolah;
    private String alamatSekolah;
    private String c_asal;

    public String getC_asal() {
        return c_asal;
    }

    public void setC_asal(String c_asal) {
        this.c_asal = c_asal;
    }
    
    

    public String getNpsn() {
        return npsn;
    }

    public void setNpsn(String npsn) {
        this.npsn = npsn;
    }

    public String getIdSekolah() {
        return idSekolah;
    }

    public void setIdSekolah(String idSekolah) {
        this.idSekolah = idSekolah;
    }

    public String getIdPengguna() {
        return idPengguna;
    }

    public void setIdPengguna(String idPengguna) {
        this.idPengguna = idPengguna;
    }

    public String getNamaSekolah() {
        return namaSekolah;
    }

    public void setNamaSekolah(String namaSekolah) {
        this.namaSekolah = namaSekolah;
    }

    public String getAlamatSekolah() {
        return alamatSekolah;
    }

    public void setAlamatSekolah(String alamatSekolah) {
        this.alamatSekolah = alamatSekolah;
    }
    

  

}
