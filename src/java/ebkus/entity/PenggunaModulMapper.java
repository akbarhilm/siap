package ebkus.entity;

import ebkus.model.PenggunaModul;
import java.util.List;
import java.util.Map;

/**
 *
 * @author zaenab
 */
public interface PenggunaModulMapper {

    List<PenggunaModul> getListModul(Map<String, Object> map);

    Integer getBanyakListModul(Map<String, Object> map);
    
   
    void insertPenggunaModul(PenggunaModul pgnmdl);

    void deletePenggunaModul(PenggunaModul pgnmdl);


}
