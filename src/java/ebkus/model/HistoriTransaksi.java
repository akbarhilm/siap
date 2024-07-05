package ebkus.model;

import java.math.BigDecimal;

/**
 *
 * @author User
 */
public class HistoriTransaksi implements java.io.Serializable {

    private String tglTrx;
    private String jamTrx;
    private String keterangan;
    private BigDecimal terima;
    private BigDecimal keluar;
    private String generateTime;
    private String ketBku;
    private String namaTujuan;
    private String rekening;
    private BigDecimal nilai;
    private String jurnal;
    private String keteranganBank;
    private BigDecimal saldo;

    @Override
    public String toString() {
        return "HistoriTransaksi{" + "tglTrx=" + tglTrx + ", jamTrx=" + jamTrx + ", keterangan=" + keterangan + ", terima=" + terima + ", keluar=" + keluar + ", generateTime=" + generateTime + ", ketBku=" + ketBku + ", namaTujuan=" + namaTujuan + ", rekening=" + rekening + ", nilai=" + nilai + ", jurnal=" + jurnal + ", keteranganBank=" + keteranganBank + ", saldo=" + saldo + '}';
    }

    /**
     * @return the rekening
     */
    public String getRekening() {
        return rekening;
    }

    /**
     * @param rekening the rekening to set
     */
    public void setRekening(String rekening) {
        this.rekening = rekening;
    }

    /**
     * @return the nilai
     */
    public BigDecimal getNilai() {
        return nilai;
    }

    /**
     * @param nilai the nilai to set
     */
    public void setNilai(BigDecimal nilai) {
        this.nilai = nilai;
    }

    /**
     * @return the jurnal
     */
    public String getJurnal() {
        return jurnal;
    }

    /**
     * @param jurnal the jurnal to set
     */
    public void setJurnal(String jurnal) {
        this.jurnal = jurnal;
    }

    /**
     * @return the keteranganBank
     */
    public String getKeteranganBank() {
        return keteranganBank;
    }

    /**
     * @param keteranganBank the keteranganBank to set
     */
    public void setKeteranganBank(String keteranganBank) {
        this.keteranganBank = keteranganBank;
    }

    public String getNamaTujuan() {
        return namaTujuan;
    }

    public void setNamaTujuan(String namaTujuan) {
        this.namaTujuan = namaTujuan;
    }

    public String getKetBku() {
        return ketBku;
    }

    public void setKetBku(String ketBku) {
        this.ketBku = ketBku;
    }

    public String getGenerateTime() {
        return generateTime;
    }

    public void setGenerateTime(String generateTime) {
        this.generateTime = generateTime;
    }

    public String getTglTrx() {
        return tglTrx;
    }

    public void setTglTrx(String tglTrx) {
        this.tglTrx = tglTrx;
    }

    public String getJamTrx() {
        return jamTrx;
    }

    public void setJamTrx(String jamTrx) {
        this.jamTrx = jamTrx;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public BigDecimal getTerima() {
        return terima;
    }

    public void setTerima(BigDecimal terima) {
        this.terima = terima;
    }

    public BigDecimal getKeluar() {
        return keluar;
    }

    public void setKeluar(BigDecimal keluar) {
        this.keluar = keluar;
    }

    /**
     * @return the saldo
     */
    public BigDecimal getSaldo() {
        return saldo;
    }

    /**
     * @param saldo the saldo to set
     */
    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

}
