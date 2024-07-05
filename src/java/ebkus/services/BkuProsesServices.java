package ebkus.services;

import ebkus.model.BkuProses;
import ebkus.model.Skpd;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Zainab
 */
public interface BkuProsesServices {

    List<BkuProses> getListIndex(Map<String, Object> param);

    Integer getBanyakListIndex(Map<String, Object> param);

    Skpd getSkpd(Map<String, Object> param);

    void insertBatasWaktu(BkuProses param);

    void updateBatasWaktu(BkuProses param);

    BkuProses getEdit(Map<String, Object> param);

    
}
