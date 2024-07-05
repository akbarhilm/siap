package ebkus.entity;

import ebkus.model.User;
import java.util.List;
import java.util.Map;

/**
 *
 * @author zaenab
 */
public interface PenggunaMapper {

    List<User> getListUser(Map<String, Object> map);
    
    Map getMaxDepag();

    Integer getBanyakListUser(Map<String, Object> map);
    
    User getPenggunaById(Integer id);
    
    void insertUser(User user);

    void updateUser(User user);

    void deleteUser(User user);
//
//    User getUserById(Integer id);
}
