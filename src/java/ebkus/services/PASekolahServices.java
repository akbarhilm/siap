/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.services;

import ebkus.model.PASekolah;
import java.util.List;
import java.util.Map;

/**
 *
 * @author zaenab
 */
public interface PASekolahServices {

    List<PASekolah> getListPASekolah(Map<String, Object> map);

    Integer getBanyakListPASekolah(Map<String, Object> map);
    
     void insertPASekolah(Map param);
     
     void nonAktifPaPlt(Map param);
     
     
//    
//    User getPenggunaById(Integer id);
//    
//    Map<Integer,String> getlistKodeGroup();
//    
//    Map<Integer,String> getlistKodeOtoritas(Pengguna param);
//
//    void insertUser(User user);
//
//    void updateUser(User user);
//
//    void deleteUser(User user);
//    
//    Map<Integer,String> getKodeSp2dProses();
//    
//    Map<Integer,String> getlistKodeGroup();
//    
//    User getUserById(Integer id);
}
