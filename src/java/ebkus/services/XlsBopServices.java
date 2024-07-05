package ebkus.services;

import ebkus.model.RkasBkus;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Zainab
 */
public interface XlsBopServices {

  List<RkasBkus> getListRkasBkus(Map<String, Object> param);

  Integer getBanyakRkasBkus(Map<String, Object> param);
   
  
}
