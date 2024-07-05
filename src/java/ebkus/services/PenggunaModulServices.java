/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.services;

import ebkus.model.Skpd;
import ebkus.model.PenggunaModul;
import java.util.List;
import java.util.Map;

/**
 *
 * @author zaenab
 */
public interface PenggunaModulServices {

    List<PenggunaModul> getListModul(Map<String, Object> map);

    Integer getBanyakListModul(Map<String, Object> map);
    
    void insertPenggunaModul(PenggunaModul pgnmdl);

    void deletePenggunaModul(PenggunaModul pgnmdl);
}