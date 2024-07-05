package ebkus.services;

import ebkus.model.BkuRinci;
import ebkus.model.BukuKasUmum;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Zainab
 */
public interface KoreksiNilaiSpjBopServices {

    public List<BukuKasUmum> getListTriwulanByRekap(Map<String, Object> param);

    public List<BukuKasUmum> getListIndex(Map<String, Object> param);

    public Integer getBanyakListIndex(Map<String, Object> param);

    public List<BukuKasUmum> getListSpj(Map<String, Object> param);

    public Integer getBanyakListSpj(Map<String, Object> param);

    public BukuKasUmum getDataSekolah(Map<String, Object> param);

    public List<BkuRinci> getListSpjKoreksi(Map<String, Object> param);

    public Integer getBanyakListSpjKoreksi(Map<String, Object> param);

    void insertKoreksi(BukuKasUmum param);

    public BukuKasUmum getDataEdit(Map<String, Object> param);

    void updateKoreksi(BukuKasUmum param);

    void deleteBkuById(Map param);

    public BukuKasUmum getPaguEdit(Map<String, Object> param);

}
