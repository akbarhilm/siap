package ebkus.entity;

import ebkus.model.BukuKasUmum;
import ebkus.model.BatalTutupBku;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Anita
 */
public interface BatalTutupBkuBopMapper {

    List<BukuKasUmum> getBulanBKU(Map param);

    Integer getBanyakListBku(Map<String, Object> param);

    List<BukuKasUmum> getListBku(Map<String, Object> param);

    BatalTutupBku getBendahara(Map<String, Object> param);

    BatalTutupBku getNilaiKas(Map<String, Object> param);

    BatalTutupBku getNilaiSaldo(Map<String, Object> param);

    Integer getBanyakDataBku(BatalTutupBku tutupbku);

    void deleteTutupBku(BatalTutupBku tutupbku);
    
    void deleteTutupBkuTI(BatalTutupBku tutupbku);

    void insertTutupBku(BatalTutupBku tutupbku);

    void updateBkuPengeluaran(BatalTutupBku tutupbku);

    void insertBKUPengeluaran(BatalTutupBku tutupbku);

    Integer getBanyakListXlsBku(Map<String, Object> param);

    List<Map> getListXlsBku(Map<String, Object> param);

    public List<BukuKasUmum> getTriwulanBKU(Map<String, Object> param);
    
}
