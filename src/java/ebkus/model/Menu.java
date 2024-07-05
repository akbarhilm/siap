/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ebkus.model;

import java.io.Serializable;
/**
 *
 * @author Galuh Ajeng Larasati
 */
public class Menu implements Serializable {
    private Integer idNumber;
    private String nama;
    private String link;
    private String kodeAdaSubMenu;
    private java.util.ArrayList<Menu> menu; // menu level di bawah nya

    /**
     * @return the nama
     */
    public String getNama() {
        return nama;
    }

    /**
     * @param nama the nama to set
     */
    public void setNama(String nama) {
        this.nama = nama;
    }

    /**
     * @return the link
     */
    public String getLink() {
        return link;
    }

    /**
     * @param link the link to set
     */
    public void setLink(String link) {
        this.link = link;
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
     * @return the idNumber
     */
    public Integer getIdNumber() {
        return idNumber;
    }

    /**
     * @param idNumber the idNumber to set
     */
    public void setIdNumber(Integer idNumber) {
        this.idNumber = idNumber;
    }

    /**
     * @return the kodeAdaSubMenu
     */
    public String getKodeAdaSubMenu() {
        return kodeAdaSubMenu;
    }

    /**
     * @param kodeAdaSubMenu the kodeAdaSubMenu to set
     */
    public void setKodeAdaSubMenu(String kodeAdaSubMenu) {
        this.kodeAdaSubMenu = kodeAdaSubMenu;
    }


}
