package ebkus.services;

import java.util.List;
import java.util.Map;
import ebkus.model.User;

/**
 *
 * @author Zainab
 */
public interface ListPenggunaServices {

     List<User> getAllPengguna(Map<String, Object> param);
  
     Integer getBanyakPengguna(Map<String, Object> param);
    
 //   Sekolah getSekolahById(Integer id);
}
