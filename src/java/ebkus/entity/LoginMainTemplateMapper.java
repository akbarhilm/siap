/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.entity;

import ebkus.model.Modul;
import ebkus.model.Pengguna;
import ebkus.model.Sekolah;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Galuh Ajeng Larasati
 */
public interface LoginMainTemplateMapper {

    Pengguna loadUserByUsername(Map<String, Object> map);

    Pengguna loadUserOnlyByUsername(Map<String, Object> map);

    Pengguna loadPenggunaByUsername(String id);

    List<Modul> getListModulDgnIdIndukSatu(Map<String, Object> map);

    List<Modul> getListModulDgnIdIndukSatuByUsername(String id);

    Integer getBanyakListModulByIdInduk(Integer id);

    void setLoginStatus(Pengguna param);

    Integer getLoginStatus(Pengguna param);

    void deleteLoginStatus(Pengguna param);

    List<Modul> getListModulByIdInduk(Map<String, Object> map);

    List<Sekolah> getListSekolah(String id);

    Sekolah getSekolah(String id);

    Integer getSisaHari(Pengguna param);

    void updateSalahPassword(Pengguna param);

    void updateStatusLock(Pengguna param);

    void loginSuccess(Pengguna param);
}
