/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.entity;

import ebkus.model.Sekolah;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Zainab
 */
public interface ListSekolahMapper {

    List<Sekolah> getAllSekolah(Map<String, Object> param);

    Integer getBanyakAllSekolah(Map<String, Object> param);

    List<Sekolah> getAllSekolahPlt(Map<String, Object> param);

    Integer getBanyakAllSekolahPlt(Map<String, Object> param);

    Sekolah getSekolahById(Integer id);

}
