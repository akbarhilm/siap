/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.services;

import ebkus.model.MCB;
import ebkus.model.PaguTalangan;
import ebkus.model.WSTalangan;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 *
 * @author HP pavilion
 */
public interface TalanganServices {

    List<PaguTalangan> getListIndex(Map<String, Object> param);

    Integer getBanyakListIndex(Map<String, Object> param);

    List<WSTalangan> getListIndexWS(Map<String, Object> param);

    Integer getBanyakListIndexWS(Map<String, Object> param);

    BigDecimal getNilaiIndex(Map<String, Object> param);

    BigDecimal getNilaiIndexWS(Map<String, Object> param);

    Integer getMaxTutup(Map<String, Object> param);

    void deleteById(Map param);

    void insertPaguTalangan(PaguTalangan param);

    PaguTalangan getEditPaguTalangan(Map<String, Object> param);

    void updatePaguTalanganById(PaguTalangan param);

    List<MCB> getMcb(Map<String, Object> param);

    Integer getMaxTriwulan(Map<String, Object> param);
}
