/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.services;

import ebkus.model.Pengguna;

/**
 *
 * @author idns , 07-11-2017
 */
public interface LoginServices {

    Pengguna loadUserByUsername(String usernam, String pass);

    Pengguna loadPenggunaByUsername(String usernam);

    void updateSalahPassword(Pengguna param);

    void updateStatusLock(Pengguna param);

    Integer getSisaHari(Pengguna param);

    void loginSuccess(Pengguna param);

    void setLoginStatus(Pengguna param);

    Integer getLoginStatus(Pengguna param);

    void deleteLoginStatus(Pengguna param);

}
