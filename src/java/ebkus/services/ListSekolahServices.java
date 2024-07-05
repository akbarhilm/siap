package ebkus.services;

import ebkus.model.Sekolah;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Zainab
 */
public interface ListSekolahServices {

    List<Sekolah> getAllSekolah(Map<String, Object> param);

    Integer getBanyakAllSekolah(Map<String, Object> param);

    List<Sekolah> getAllSekolahPlt(Map<String, Object> param);

    Integer getBanyakAllSekolahPlt(Map<String, Object> param);

    Sekolah getSekolahById(Integer id);

    Integer getBanyakSekolahByKodeGrup(Map<String, Object> param);

    List<Sekolah> getSekolahByKodeGrup(Map<String, Object> param);
}
