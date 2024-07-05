package ebkus.entity;

import ebkus.model.Modul;
import java.util.List;
import java.util.Map;

/**
 *
 * @author idns
 */
public interface ModulMapper {

    List<Modul> getListModul(Map<String, Object> map);

    Integer getBanyakListModul(Map<String, Object> map);

    void insertModul(Modul modul);

    void updateModul(Modul modul);

    void deleteModul(Integer id);

    Modul getModulById(Integer id);
    
    Integer getBanyakListModulByIdInduk(Integer id);
    
}
