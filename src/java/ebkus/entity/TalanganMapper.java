/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.entity;

import ebkus.model.MCB;
import ebkus.model.PaguTalangan;
import ebkus.model.WSTalangan;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Yuuhuu
 */
public interface TalanganMapper {

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

    Integer getMaxTriwulanBop(Map<String, Object> param);

    Integer getMaxTriwulanBos(Map<String, Object> param);

    void deleteMcbById(Map param);

    void insertMcb(MCB param);

    List<MCB> getMcb(Map<String, Object> param);

    void updateMcbById(MCB param);

}
