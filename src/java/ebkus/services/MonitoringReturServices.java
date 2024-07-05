package ebkus.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import ebkus.model.Retur;



/**
 *
 * @author Sakadaek
 */
public interface MonitoringReturServices {

    
     List<Retur> getReturBOP(Map<String, Object> param);

    Integer getBanyakReturBOP(Map<String, Object> param);
    
        
     List<Retur> getReturBOS(Map<String, Object> param);

    Integer getBanyakReturBOS(Map<String, Object> param);

   
}
