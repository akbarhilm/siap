/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.services;

import ebkus.model.Pengguna;
import ebkus.model.Skpd;
import ebkus.model.User;
import java.util.List;
import java.util.Map;

/**
 *
 * @author zaenab
 */
public interface PenggunaServices {

     Map getMaxDepag();
    
    List<User> getListUser(Map<String, Object> map);

    Integer getBanyakListUser(Map<String, Object> map);
    
    User getPenggunaById(Integer id);
    
    Map<Integer,String> getlistKodeGroup();
    
    Map<Integer,String> getlistKodeOtoritas(Pengguna param);

    void insertUser(User user);

    void updateUser(User user);

    void deleteUser(User user);
//    
//    Map<Integer,String> getKodeSp2dProses();
//    
//    Map<Integer,String> getlistKodeGroup();
//    
//    User getUserById(Integer id);
}
