/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.entity;

import ebkus.model.BkuRinci;
import ebkus.model.BukuKasUmum;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Zainab
 */
public interface KoreksiPajakBopMapper {

    List<BukuKasUmum> getListKoreksi(Map<String, Object> param);

    Integer getBanyakListKoreksi(Map<String, Object> param);

    List<BukuKasUmum> getListPjKoreksi(Map<String, Object> param);

    Integer getBanyakListPjKoreksi(Map<String, Object> param);

    BukuKasUmum getDataPajakPn(Map<String, Object> param);

    List<BkuRinci> getListKoreksiPjPn(Map<String, Object> param);

    Integer getBanyakListKoreksiPjPn(Map<String, Object> param);

    void insertBkuSpjMaster(BukuKasUmum param);

    void insertBkuSpjRinci(BkuRinci param);

    void insertBkuPjMaster(BukuKasUmum param);

    void insertBkuPjRinci(BkuRinci param);

    void updatePajakKoreksi(BukuKasUmum param);

    void deleteKoreksi(BukuKasUmum param);

}
