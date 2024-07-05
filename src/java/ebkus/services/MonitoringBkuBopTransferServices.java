package ebkus.services;

import ebkus.model.BukuKasUmum;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Racka
 */
public interface MonitoringBkuBopTransferServices {

    List<BukuKasUmum> getListIndex(Map<String, Object> param);

    Integer getBanyakListIndex(Map<String, Object> param);

    BukuKasUmum getNilaiIndex(Map<String, Object> param);

    BukuKasUmum getNilaiSaldo(Map<String, Object> param);
}
