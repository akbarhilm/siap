/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.model;

import java.math.BigDecimal;
import java.sql.Date;
//import java.sql.Date;
import javax.validation.Valid;

/**
 *
 * @author Zainab
 */
public class GenerateId extends BaseModel {

    private Integer id;
    private String namaTabel;
    private String idFormat;
    private String kodeApp;

    
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
     * @return the namaTabel
     */
    public String getNamaTabel() {
        return namaTabel;
    }

    /**
     * @param namaTabel the namaTabel to set
     */
    public void setNamaTabel(String namaTabel) {
        this.namaTabel = namaTabel;
    }

    /**
     * @return the idFormat
     */
    public String getIdFormat() {
        return idFormat;
    }

    /**
     * @param idFormat the idFormat to set
     */
    public void setIdFormat(String idFormat) {
        this.idFormat = idFormat;
    }

    /**
     * @return the kodeApp
     */
    public String getKodeApp() {
        return kodeApp;
    }

    /**
     * @param kodeApp the kodeApp to set
     */
    public void setKodeApp(String kodeApp) {
        this.kodeApp = kodeApp;
    }
    
    

}
