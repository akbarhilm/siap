package ebkus.services;

import java.util.List;
import java.util.Map;
import ebkus.model.MonitoringBkuBopHarian;

/**
 *
 * @author idns
 */
public interface MonitoringBkuBopHarianServices {

    List<MonitoringBkuBopHarian> getListIndex(Map<String, Object> param);

    Integer getBanyakListIndex(Map<String, Object> param);

    //MonitoringBkuBopHarian getNilaiIndex(Map<String, Object> param);   
    
}
