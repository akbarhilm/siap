package ebkus.services;

import ebkus.model.BukuKasUmum;
import ebkus.model.BatalTutupBku;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Anita
 */
public interface BatalTutupBkuBopServices {

    List<BukuKasUmum> getBulanBKU(Map param);

    Integer getBanyakListBku(Map<String, Object> param);

    List<BukuKasUmum> getListBku(Map<String, Object> param);

    BatalTutupBku getBendahara(Map<String, Object> param);

    BatalTutupBku getNilaiKas(Map<String, Object> param);

    BatalTutupBku getNilaiSaldo(Map<String, Object> param);

    Integer getBanyakDataBku(BatalTutupBku tutupbku);

    void insertTutupBku(BatalTutupBku tutupbku, String kodetomboltutupbuku);

    Integer getBanyakListXlsBku(Map<String, Object> param);

    List<Map> getListXlsBku(Map<String, Object> param);

    List<BukuKasUmum> getTriwulanBKU(Map<String, Object> param);
    
}
