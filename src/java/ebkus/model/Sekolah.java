package ebkus.model;

import javax.validation.constraints.NotNull;

/**
 *
 * @author User
 */
public class Sekolah extends BaseModel {

    @NotNull(message = "Sekolah Wajib di isi")
    private String tahun;
    private Integer idSekolah;
    private String npsn;
    private Integer idSkpd;
    private String namaSekolah;
    private String namaSekolahPendek;
    private String alamatSekolah;
    private String nrkKepsek;
    private String nipKepsek;
    private String namaKepsek;
    private String pangkatKepsek;
    private String nrkBendahara;
    private String nipBendahara;
    private String namaBendahara;
    private String noTelpon;
    private String noFax;
    private String website;
    private String email;
    private String namaLogo;
    private String kodeWilayah;
    private String kodeJenjang;
    private String kodeNegeri;
    private String kodeLokasi;
    private String nss;
    private Integer jumlahSiswa;
    private Integer jumlahRomBel;
    private Integer idLokasi;
    private String aktif;
    private Sekolah sekolah;
    private String sekolahGabung;
    private String noNPWP;
    private String namaNPWP;
    private String alamatNPWP;
    private String kotaNPWP;
    private String noBOP;
    private String namaBOP;
    private String noBOS;
    private String namaBOS;
    private String kodeSkpd;
    private String namaSkpd;
    private String skpd;

    public String getSkpd() {
        return skpd;
    }

    public void setSkpd(String skpd) {
        this.skpd = skpd;
    }

    public String getKodeSkpd() {
        return kodeSkpd;
    }

    public void setKodeSkpd(String kodeSkpd) {
        this.kodeSkpd = kodeSkpd;
    }

    public String getNamaSkpd() {
        return namaSkpd;
    }

    public void setNamaSkpd(String namaSkpd) {
        this.namaSkpd = namaSkpd;
    }

    public String getTahun() {
        return tahun;
    }

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
     * @return the npsn
     */
    public String getNpsn() {
        return npsn;
    }

    /**
     * @param npsn the npsn to set
     */
    public void setNpsn(String npsn) {
        this.npsn = npsn;
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
     * @return the namaSekolah
     */
    public String getNamaSekolah() {
        return namaSekolah;
    }

    /**
     * @param namaSekolah the namaSekolah to set
     */
    public void setNamaSekolah(String namaSekolah) {
        this.namaSekolah = namaSekolah;
    }

    /**
     * @return the namaSekolahPendek
     */
    public String getNamaSekolahPendek() {
        return namaSekolahPendek;
    }

    /**
     * @param namaSekolahPendek the namaSekolahPendek to set
     */
    public void setNamaSekolahPendek(String namaSekolahPendek) {
        this.namaSekolahPendek = namaSekolahPendek;
    }

    /**
     * @return the alamatSekolah
     */
    public String getAlamatSekolah() {
        return alamatSekolah;
    }

    /**
     * @param alamatSekolah the alamatSekolah to set
     */
    public void setAlamatSekolah(String alamatSekolah) {
        this.alamatSekolah = alamatSekolah;
    }

    /**
     * @return the nrkKepsek
     */
    public String getNrkKepsek() {
        return nrkKepsek;
    }

    /**
     * @param nrkKepsek the nrkKepsek to set
     */
    public void setNrkKepsek(String nrkKepsek) {
        this.nrkKepsek = nrkKepsek;
    }

    /**
     * @return the nipKepsek
     */
    public String getNipKepsek() {
        return nipKepsek;
    }

    /**
     * @param nipKepsek the nipKepsek to set
     */
    public void setNipKepsek(String nipKepsek) {
        this.nipKepsek = nipKepsek;
    }

    /**
     * @return the namaKepsek
     */
    public String getNamaKepsek() {
        return namaKepsek;
    }

    /**
     * @param namaKepsek the namaKepsek to set
     */
    public void setNamaKepsek(String namaKepsek) {
        this.namaKepsek = namaKepsek;
    }

    /**
     * @return the pangkatKepsek
     */
    public String getPangkatKepsek() {
        return pangkatKepsek;
    }

    /**
     * @param pangkatKepsek the pangkatKepsek to set
     */
    public void setPangkatKepsek(String pangkatKepsek) {
        this.pangkatKepsek = pangkatKepsek;
    }

    /**
     * @return the nrkBendahara
     */
    public String getNrkBendahara() {
        return nrkBendahara;
    }

    /**
     * @param nrkBendahara the nrkBendahara to set
     */
    public void setNrkBendahara(String nrkBendahara) {
        this.nrkBendahara = nrkBendahara;
    }

    /**
     * @return the nipBendahara
     */
    public String getNipBendahara() {
        return nipBendahara;
    }

    /**
     * @param nipBendahara the nipBendahara to set
     */
    public void setNipBendahara(String nipBendahara) {
        this.nipBendahara = nipBendahara;
    }

    /**
     * @return the namaBendahara
     */
    public String getNamaBendahara() {
        return namaBendahara;
    }

    /**
     * @param namaBendahara the namaBendahara to set
     */
    public void setNamaBendahara(String namaBendahara) {
        this.namaBendahara = namaBendahara;
    }

    /**
     * @return the noTelpon
     */
    public String getNoTelpon() {
        return noTelpon;
    }

    /**
     * @param noTelpon the noTelpon to set
     */
    public void setNoTelpon(String noTelpon) {
        this.noTelpon = noTelpon;
    }

    /**
     * @return the noFax
     */
    public String getNoFax() {
        return noFax;
    }

    /**
     * @param noFax the noFax to set
     */
    public void setNoFax(String noFax) {
        this.noFax = noFax;
    }

    /**
     * @return the website
     */
    public String getWebsite() {
        return website;
    }

    /**
     * @param website the website to set
     */
    public void setWebsite(String website) {
        this.website = website;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the namaLogo
     */
    public String getNamaLogo() {
        return namaLogo;
    }

    /**
     * @param namaLogo the namaLogo to set
     */
    public void setNamaLogo(String namaLogo) {
        this.namaLogo = namaLogo;
    }

    /**
     * @return the kodeWilayah
     */
    public String getKodeWilayah() {
        return kodeWilayah;
    }

    /**
     * @param kodeWilayah the kodeWilayah to set
     */
    public void setKodeWilayah(String kodeWilayah) {
        this.kodeWilayah = kodeWilayah;
    }

    /**
     * @return the kodeJenjang
     */
    public String getKodeJenjang() {
        return kodeJenjang;
    }

    /**
     * @param kodeJenjang the kodeJenjang to set
     */
    public void setKodeJenjang(String kodeJenjang) {
        this.kodeJenjang = kodeJenjang;
    }

    /**
     * @return the kodeNegeri
     */
    public String getKodeNegeri() {
        return kodeNegeri;
    }

    /**
     * @param kodeNegeri the kodeNegeri to set
     */
    public void setKodeNegeri(String kodeNegeri) {
        this.kodeNegeri = kodeNegeri;
    }

    /**
     * @return the kodeLokasi
     */
    public String getKodeLokasi() {
        return kodeLokasi;
    }

    /**
     * @param kodeLokasi the kodeLokasi to set
     */
    public void setKodeLokasi(String kodeLokasi) {
        this.kodeLokasi = kodeLokasi;
    }

    /**
     * @return the nss
     */
    public String getNss() {
        return nss;
    }

    /**
     * @param nss the to set
     */
    public void setNss(String nss) {
        this.nss = nss;
    }

    /**
     * @return the jumlahSiswa
     */
    public Integer getJumlahSiswa() {
        return jumlahSiswa;
    }

    /**
     * @param jumlahSiswa the jumlahSiswa to set
     */
    public void setJumlahSiswa(Integer jumlahSiswa) {
        this.jumlahSiswa = jumlahSiswa;
    }

    /**
     * @return the jumlahRomBel
     */
    public Integer getJumlahRomBel() {
        return jumlahRomBel;
    }

    /**
     * @param jumlahRomBel the jumlahRomBel to set
     */
    public void setJumlahRomBel(Integer jumlahRomBel) {
        this.jumlahRomBel = jumlahRomBel;
    }

    /**
     * @return the idLokasi
     */
    public Integer getIdLokasi() {
        return idLokasi;
    }

    /**
     * @param idLokasi the idLokasi to set
     */
    public void setIdLokasi(Integer idLokasi) {
        this.idLokasi = idLokasi;
    }

    /**
     * @return the aktif
     */
    public String getAktif() {
        return aktif;
    }

    /**
     * @param aktif the aktif to set
     */
    public void setAktif(String aktif) {
        this.aktif = aktif;
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
     * @return the sekolahGabung
     */
    public String getSekolahGabung() {
        return sekolahGabung;
    }

    /**
     * @param sekolahGabung the sekolahGabung to set
     */
    public void setSekolahGabung(String sekolahGabung) {
        this.sekolahGabung = sekolahGabung;
    }

    /**
     * @return the noNPWP
     */
    public String getNoNPWP() {
        return noNPWP;
    }

    /**
     * @param noNPWP the noNPWP to set
     */
    public void setNoNPWP(String noNPWP) {
        this.noNPWP = noNPWP;
    }

    /**
     * @return the namaNPWP
     */
    public String getNamaNPWP() {
        return namaNPWP;
    }

    /**
     * @param namaNPWP the namaNPWP to set
     */
    public void setNamaNPWP(String namaNPWP) {
        this.namaNPWP = namaNPWP;
    }

    /**
     * @return the kotaNPWP
     */
    public String getKotaNPWP() {
        return kotaNPWP;
    }

    /**
     * @param kotaNPWP the kotaNPWP to set
     */
    public void setKotaNPWP(String kotaNPWP) {
        this.kotaNPWP = kotaNPWP;
    }

    /**
     * @return the noBOP
     */
    public String getNoBOP() {
        return noBOP;
    }

    /**
     * @param noBOP the noBOP to set
     */
    public void setNoBOP(String noBOP) {
        this.noBOP = noBOP;
    }

    /**
     * @return the namaBOP
     */
    public String getNamaBOP() {
        return namaBOP;
    }

    /**
     * @param namaBOP the namaBOP to set
     */
    public void setNamaBOP(String namaBOP) {
        this.namaBOP = namaBOP;
    }

    /**
     * @return the noBOS
     */
    public String getNoBOS() {
        return noBOS;
    }

    /**
     * @param noBOS the noBOS to set
     */
    public void setNoBOS(String noBOS) {
        this.noBOS = noBOS;
    }

    /**
     * @return the namaBOS
     */
    public String getNamaBOS() {
        return namaBOS;
    }

    /**
     * @param namaBOS the namaBOS to set
     */
    public void setNamaBOS(String namaBOS) {
        this.namaBOS = namaBOS;
    }

    /**
     * @return the alamatNPWP
     */
    public String getAlamatNPWP() {
        return alamatNPWP;
    }

    /**
     * @param alamatNPWP the alamatNPWP to set
     */
    public void setAlamatNPWP(String alamatNPWP) {
        this.alamatNPWP = alamatNPWP;
    }

}
