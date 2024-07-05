/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.entity;

import ebkus.model.BukuKasUmum;
import ebkus.model.TutupBku;
import java.util.List;
import java.util.Map;

/**
 *
 * @author M.Mustakim
 */
public interface TutupBkuBopMapper {

    Integer validatePajak(Map param);

    List<BukuKasUmum> getBulanBKU(Map param);

    Integer getBanyakListBku(Map<String, Object> param);

    List<BukuKasUmum> getListBku(Map<String, Object> param);

    TutupBku getBendahara(Map<String, Object> param);

    TutupBku getNilaiKas(Map<String, Object> param);

    TutupBku getNilaiSaldo(Map<String, Object> param);

    Integer getBanyakDataBku(TutupBku tutupbku);

    void deleteTutupBku(TutupBku tutupbku);

    void insertTutupBku(TutupBku tutupbku);

    void updateBkuPengeluaran(TutupBku tutupbku);

    void insertBKUPengeluaran(TutupBku tutupbku);

    Integer getBanyakListXlsBku(Map<String, Object> param);

    List<Map> getListXlsBku(Map<String, Object> param);

    List<BukuKasUmum> getTriwulanBKU(Map<String, Object> param);

    TutupBku getBapKasValidasi(Map<String, Object> param);

}
