package ebkus.services;

import java.util.List;
import java.util.Map;
import ebkus.model.MonitoringAkbBkuPerTriwulan;

/**
 *
 * @author idns
 */
public interface MonitoringAkbBkuPerTriwulanServices {

    List<MonitoringAkbBkuPerTriwulan> getListIndex(Map<String, Object> param);

    Integer getBanyakListIndex(Map<String, Object> param);

    //MonitoringAkbBkuPerTriwulan getNilaiIndex(Map<String, Object> param);   
    
}
