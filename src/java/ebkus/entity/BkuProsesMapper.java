/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.entity;

import java.util.List;
import java.util.Map;
import ebkus.model.BkuProses;
import ebkus.model.Skpd;

/**
 *
 * @author Zainab
 */
public interface BkuProsesMapper {

    List<BkuProses> getListIndex(Map<String, Object> param);

    Integer getBanyakListIndex(Map<String, Object> param);

    Skpd getSkpd(Map<String, Object> param);

    void insertBatasWaktu(BkuProses param);

    void updateBatasWaktu(BkuProses param);

    BkuProses getEdit(Map<String, Object> param);


}
