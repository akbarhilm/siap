/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.entity;

import ebkus.model.CekDuplikat;
import ebkus.model.CekGiatAkb;
import ebkus.model.CekGiatRinci;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Racka
 */
public interface CekRkasMapper {

    List<CekDuplikat> getDuplikat(Map<String, Object> param);

    List<CekGiatRinci> getGiatRinci(Map<String, Object> param);

    List<CekGiatAkb> getGiatAkb(Map<String, Object> param);

    Integer getBanyakDuplikat(Map<String, Object> param);

    Integer getBanyakGiatRinci(Map<String, Object> param);

    Integer getBanyakGiatAkb(Map<String, Object> param);
}
