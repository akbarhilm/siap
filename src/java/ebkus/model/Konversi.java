/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.model;

/**
 *
 * @author Racka
 */
public class Konversi extends BaseModel {

    private String tahun;
    private Integer banyak;
    private String pagu;

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public Integer getBanyak() {
        return banyak;
    }

    public void setBanyak(Integer banyak) {
        this.banyak = banyak;
    }

    public String getPagu() {
        return pagu;
    }

    public void setPagu(String pagu) {
        this.pagu = pagu;
    }
}
