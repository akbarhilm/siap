/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.model;

/**
 *
 * @author User
 */
public class LoginForm {
    private String username;
    private String password;
    private String tahun;

    private String passwordBaru;
    private String passwordBaruVerify;
    private String passwordLama;
    private Integer idEntry;

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the tahun
     */
    public String getTahun() {
        return tahun;
    }

    /**
     * @param tahun the tahun to set
     */
    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    /**
     * @return the passwordBaru
     */
    public String getPasswordBaru() {
        return passwordBaru;
    }

    /**
     * @param passwordBaru the passwordBaru to set
     */
    public void setPasswordBaru(String passwordBaru) {
        this.passwordBaru = passwordBaru;
    }

    /**
     * @return the passwordBaruVerify
     */
    public String getPasswordBaruVerify() {
        return passwordBaruVerify;
    }

    /**
     * @param passwordBaruVerify the passwordBaruVerify to set
     */
    public void setPasswordBaruVerify(String passwordBaruVerify) {
        this.passwordBaruVerify = passwordBaruVerify;
    }

    /**
     * @return the passwordLama
     */
    public String getPasswordLama() {
        return passwordLama;
    }

    /**
     * @param passwordLama the passwordLama to set
     */
    public void setPasswordLama(String passwordLama) {
        this.passwordLama = passwordLama;
    }    

    /**
     * @return the idEntry
     */
    public Integer getIdEntry() {
        return idEntry;
    }

    /**
     * @param idEntry the idEntry to set
     */
    public void setIdEntry(Integer idEntry) {
        this.idEntry = idEntry;
    }
    
}
