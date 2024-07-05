package ebkus.model;

import java.util.List;

/**
 *
 * @author User
 */
public class Pengguna implements java.io.Serializable {

    private Integer idPengguna;
    private String namaPengguna;
    private boolean isAktif;
    private String passPengguna;
    private String nip;
    private String ipAddress;
    private String kodeGrup;
    private String nrk;
    private String kodeProses;
    private List<Sekolah> listSekolah;
    private java.util.ArrayList<Menu> menu;
    private String kodeOtoritas;
    private Sekolah sekolah;
    private String kodeAktif;
    private Integer idSkpd; /* idns , 20-12-2017 */

    private Integer countSalah;
    private String kodeKunci;

    /**
     * @return the idPengguna
     */
    public Integer getIdPengguna() {
        return idPengguna;
    }

    /**
     * @param idPengguna the idPengguna to set
     */
    public void setIdPengguna(Integer idPengguna) {
        this.idPengguna = idPengguna;
    }

    /**
     * @return the namaPengguna
     */
    public String getNamaPengguna() {
        return namaPengguna;
    }

    /**
     * @param namaPengguna the namaPengguna to set
     */
    public void setNamaPengguna(String namaPengguna) {
        this.namaPengguna = namaPengguna;
    }

    /**
     * @return the nip
     */
    public String getNip() {
        return nip;
    }

    /**
     * @param nip the nip to set
     */
    public void setNip(String nip) {
        this.nip = nip;
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
     * @return the ipAddress
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * @param ipAddress the ipAddress to set
     */
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    /**
     * @return the kodeProses
     */
    public String getKodeProses() {
        return kodeProses;
    }

    /**
     * @param kodeProses the kodeProses to set
     */
    public void setKodeProses(String kodeProses) {
        this.kodeProses = kodeProses;
    }

    /**
     * @return the kodeGrup
     */
    public String getKodeGrup() {
        return kodeGrup;
    }

    /**
     * @param kodeGrup the kodeGrup to set
     */
    public void setKodeGrup(String kodeGrup) {
        this.kodeGrup = kodeGrup;
    }

    /**
     * @return the passPengguna
     */
    public String getPassPengguna() {
        return passPengguna;
    }

    /**
     * @param passPengguna the passPengguna to set
     */
    public void setPassPengguna(String passPengguna) {
        this.passPengguna = passPengguna;
    }

    /**
     * @return the isAktif
     */
    public boolean isIsAktif() {
        return isAktif;
    }

    /**
     * @param isAktif the isAktif to set
     */
    public void setIsAktif(boolean isAktif) {
        this.isAktif = isAktif;
    }

    /**
     * @return the sekolah
     */
    public List<Sekolah> getListSekolah() {
        return listSekolah;
    }

    /**
     * @param listSekolah the listSekolah to set
     */
    public void setListSekolah(List<Sekolah> listSekolah) {
        this.listSekolah = listSekolah;
    }

    /**
     * @return the menu
     */
    public java.util.ArrayList<Menu> getMenu() {
        return menu;
    }

    /**
     * @param menu the menu to set
     */
    public void setMenu(java.util.ArrayList<Menu> menu) {
        this.menu = menu;
    }

    /**
     * @return the kodeOtoritas
     */
    public String getKodeOtoritas() {
        return kodeOtoritas;
    }

    /**
     * @param kodeOtoritas the kodeOtoritas to set
     */
    public void setKodeOtoritas(String kodeOtoritas) {
        this.kodeOtoritas = kodeOtoritas;
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
     * @return the kodeAktif
     */
    public String getKodeAktif() {
        return kodeAktif;
    }

    /**
     * @param kodeAktif the kodeAktif to set
     */
    public void setKodeAktif(String kodeAktif) {
        this.kodeAktif = kodeAktif;
    }

    /**
     * @return the idSkpd
     */
    public Integer getIdSkpd() {
        return idSkpd;
    }

    /**
     * @param idSkpd the idSkpd to set
     */
    public void setIdSkpd(Integer idSkpd) {
        this.idSkpd = idSkpd;
    }

    /**
     * @return the kodeKunci
     */
    public String getKodeKunci() {
        return kodeKunci;
    }

    /**
     * @param kodeKunci the kodeKunci to set
     */
    public void setKodeKunci(String kodeKunci) {
        this.kodeKunci = kodeKunci;
    }

    /**
     * @return the countSalah
     */
    public Integer getCountSalah() {
        return countSalah;
    }

    /**
     * @param countSalah the countSalah to set
     */
    public void setCountSalah(Integer countSalah) {
        this.countSalah = countSalah;
    }

}
