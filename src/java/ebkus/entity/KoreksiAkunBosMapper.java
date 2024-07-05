package ebkus.entity;

import ebkus.model.BukuKasUmum;
import ebkus.model.BkuRinci;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Anita
 */
public interface KoreksiAkunBosMapper {

    public Integer getMaxTriwulanByRekap(Map<String, Object> param);

    public Integer getTriwulanByRekap(Map<String, Object> param);

    public List<BukuKasUmum> getListIndex(Map<String, Object> param);

    public Integer getBanyakListIndex(Map<String, Object> param);

    public List<BukuKasUmum> getListSpj(Map<String, Object> param);

    public Integer getBanyakListSpj(Map<String, Object> param);

    public List<BkuRinci> getListSpjKoreksi(Map<String, Object> param);

    public Integer getBanyakListSpjKoreksi(Map<String, Object> param);

    public BukuKasUmum getDataSekolah(Map<String, Object> param);
    
    void updateSpjMasterById(BukuKasUmum param);

    public BukuKasUmum getDataEdit(Map<String, Object> param);
    
    
}
