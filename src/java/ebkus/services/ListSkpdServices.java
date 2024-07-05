package ebkus.services;

import java.util.List;
import java.util.Map;
import ebkus.model.Skpd;

/**
 *
 * @author Zainab
 */
public interface ListSkpdServices {

    List<Skpd> getSkpd(Map<String, Object> param);
    
    Integer getBanyakSkpd(Map<String, Object> param);
    
    Skpd getSkpdById(Integer id);
    
 
}
