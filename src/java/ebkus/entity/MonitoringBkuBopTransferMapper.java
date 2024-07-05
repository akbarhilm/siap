/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.entity;

import ebkus.model.BukuKasUmum;
import java.util.List;
import java.util.Map;

/**
 *
 * @author idns
 */
public interface MonitoringBkuBopTransferMapper {

    List<BukuKasUmum> getListIndex(Map<String, Object> param);

    Integer getBanyakListIndex(Map<String, Object> param);

    BukuKasUmum getNilaiIndex(Map<String, Object> param);

    BukuKasUmum getNilaiSaldo(Map<String, Object> param);
}
