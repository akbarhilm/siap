package ebkus.entity;

import ebkus.model.PASekolah;
import java.util.List;
import java.util.Map;

/**
 *
 * @author zaenab
 */
public interface PASekolahMapper {

    List<PASekolah> getListPASekolah(Map<String, Object> map);

    Integer getBanyakListPASekolah(Map<String, Object> map);

    void insertPASekolah(Map param);

    void nonAktifPa(Map param);

    void nonAktifPaPlt(Map param);
//    
//    User getPenggunaById(Integer id);
//    
//    void insertUser(User user);
//
//    void updateUser(User user);
//
//    void deleteUser(User user);
//
//    User getUserById(Integer id);
}
