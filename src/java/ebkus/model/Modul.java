/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.model;

import java.sql.Date;

/**
 *
 * @author Galuh Ajeng Larasati
 */
public class Modul extends BaseModel {

    private Integer id;
    private Integer idInduk;
    private String kodeDetail;
    private String kodeAktif;
    private String noModul;
    private String namaModul;
    private String namaModulLink;
    private String keterangan;
    private Integer idPgunRekam;
    private Date tglPgunRekam;
    private Integer idPgunUbah;
    private Date tglPgunUbah;
    private Integer jumlahSubModul; // jumlah anak
    private String noModulInduk; // idns , 07-12-2017 , 1.2 1.3 dst
    private String modulSuperAdmin;
    private String modulAdmin;
    private String modulPA;
    private String modulPK;
    private String modulSudin;

    public String getModulSuperAdmin() {
        return modulSuperAdmin;
    }

    public void setModulSuperAdmin(String modulSuperAdmin) {
        this.modulSuperAdmin = modulSuperAdmin;
    }

    public String getModulAdmin() {
        return modulAdmin;
    }

    public void setModulAdmin(String modulAdmin) {
        this.modulAdmin = modulAdmin;
    }

    public String getModulPA() {
        return modulPA;
    }

    public void setModulPA(String modulPA) {
        this.modulPA = modulPA;
    }

    public String getModulPK() {
        return modulPK;
    }

    public void setModulPK(String modulPK) {
        this.modulPK = modulPK;
    }

    public String getModulSudin() {
        return modulSudin;
    }

    public void setModulSudin(String modulSudin) {
        this.modulSudin = modulSudin;
    }
    
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
     * @return the idInduk
     */
    public Integer getIdInduk() {
        return idInduk;
    }

    /**
     * @param idInduk the idInduk to set
     */
    public void setIdInduk(Integer idInduk) {
        this.idInduk = idInduk;
    }

    /**
     * @return the kodeDetail
     */
    public String getKodeDetail() {
        return kodeDetail;
    }

    /**
     * @param kodeDetail the kodeDetail to set
     */
    public void setKodeDetail(String kodeDetail) {
        this.kodeDetail = kodeDetail;
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
     * @return the namaModul
     */
    public String getNamaModul() {
        return namaModul;
    }

    /**
     * @param namaModul the namaModul to set
     */
    public void setNamaModul(String namaModul) {
        this.namaModul = namaModul;
    }

    /**
     * @return the namaModulLink
     */
    public String getNamaModulLink() {
        return namaModulLink;
    }

    /**
     * @param namaModulLink the namaModulLink to set
     */
    public void setNamaModulLink(String namaModulLink) {
        this.namaModulLink = namaModulLink;
    }

    /**
     * @return the keterangan
     */
    public String getKeterangan() {
        return keterangan;
    }

    /**
     * @param keterangan the keterangan to set
     */
    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    /**
     * @return the idPgunRekam
     */
    public Integer getIdPgunRekam() {
        return idPgunRekam;
    }

    /**
     * @param idPgunRekam the idPgunRekam to set
     */
    public void setIdPgunRekam(Integer idPgunRekam) {
        this.idPgunRekam = idPgunRekam;
    }

    /**
     * @return the tglPgunRekam
     */
    public Date getTglPgunRekam() {
        return tglPgunRekam;
    }

    /**
     * @param tglPgunRekam the tglPgunRekam to set
     */
    public void setTglPgunRekam(Date tglPgunRekam) {
        this.tglPgunRekam = tglPgunRekam;
    }

    /**
     * @return the idPgunUbah
     */
    public Integer getIdPgunUbah() {
        return idPgunUbah;
    }

    /**
     * @param idPgunUbah the idPgunUbah to set
     */
    public void setIdPgunUbah(Integer idPgunUbah) {
        this.idPgunUbah = idPgunUbah;
    }

    /**
     * @return the tglPgunUbah
     */
    public Date getTglPgunUbah() {
        return tglPgunUbah;
    }

    /**
     * @param tglPgunUbah the tglPgunUbah to set
     */
    public void setTglPgunUbah(Date tglPgunUbah) {
        this.tglPgunUbah = tglPgunUbah;
    }

    /**
     * @return the jumlahSubModul
     */
    public Integer getJumlahSubModul() {
        return jumlahSubModul;
    }

    /**
     * @param jumlahSubModul the jumlahSubModul to set
     */
    public void setJumlahSubModul(Integer jumlahSubModul) {
        this.jumlahSubModul = jumlahSubModul;
    }

    /**
     * @return the noModul
     */
    public String getNoModul() {
        return noModul;
    }

    /**
     * @param noModul the noModul to set
     */
    public void setNoModul(String noModul) {
        this.noModul = noModul;
    }

    /**
     * @return the noModulInduk
     */
    public String getNoModulInduk() {
        return noModulInduk;
    }

    /**
     * @param noModulInduk the noModulInduk to set
     */
    public void setNoModulInduk(String noModulInduk) {
        this.noModulInduk = noModulInduk;
    }

}
