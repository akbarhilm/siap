/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.entity;

import ebkus.model.Kegiatan;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Zainab
 */
public interface KegiatanMapper {

    List<Kegiatan> getKegiatanSekolah(Map<String, Object> param);

    Integer getBanyakKegiatanSekolah(Map<String, Object> param);

    List<Kegiatan> getKegBOPperTW(Map<String, Object> param);

    Integer getBanyakKegBOPperTW(Map<String, Object> param);

    List<Kegiatan> getKegBOSperTW(Map<String, Object> param);

    Integer getBanyakKegBOSperTW(Map<String, Object> param);

}
