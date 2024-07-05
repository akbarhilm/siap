package ebkus.services;

import ebkus.model.BkuRinci;
import ebkus.model.BukuKasUmum;
import ebkus.model.Kegiatan;
import ebkus.model.Setor;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Zainab
 */
public interface KoreksiPajakBopServices {

    List<BukuKasUmum> getListKoreksi(Map<String, Object> param);

    Integer getBanyakListKoreksi(Map<String, Object> param);

    List<BukuKasUmum> getListPjKoreksi(Map<String, Object> param);

    Integer getBanyakListPjKoreksi(Map<String, Object> param);

    BukuKasUmum getDataPajakPn(Map<String, Object> param);

    List<BkuRinci> getListKoreksiPjPn(Map<String, Object> param);

    Integer getBanyakListKoreksiPjPn(Map<String, Object> param);

    void insertKoreksi(BukuKasUmum param);

    void insertKoreksiPajak(BukuKasUmum param);

    void deleteKoreksi(BukuKasUmum param);

}
