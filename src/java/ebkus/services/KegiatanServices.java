package ebkus.services;

import ebkus.model.Kegiatan;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Zainab
 */
public interface KegiatanServices {

    List<Kegiatan> getKegiatanSekolah(Map<String, Object> param);

    Integer getBanyakKegiatanSekolah(Map<String, Object> param);

    List<Kegiatan> getKegBOPperTW(Map<String, Object> param);

    Integer getBanyakKegBOPperTW(Map<String, Object> param);

    List<Kegiatan> getKegBOSperTW(Map<String, Object> param);

    Integer getBanyakKegBOSperTW(Map<String, Object> param);

}
