/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.entity;

import ebkus.model.Retur;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Zainab
 */
public interface MonitoringReturMapper {

    List<Retur> getReturBOP(Map<String, Object> param);

    Integer getBanyakReturBOP(Map<String, Object> param);
    
     List<Retur> getReturBOS(Map<String, Object> param);

    Integer getBanyakReturBOS(Map<String, Object> param);

    
}
