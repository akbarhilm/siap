package ebkus.services;

import ebkus.model.BukuKasUmum;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Zainab
 */
public interface BatalTransferBosServices {

    List<BukuKasUmum> getListPembatalan(Map<String, Object> param);

    Integer getBanyakListPembatalan(Map<String, Object> param);

    void approveBa(Map param);

    BukuKasUmum getTotalNilai(Map<String, Object> param);

    void pengajuanBatal(Map<String, Object> param);

    void prosesPembatalan(Map<String, Object> param);

    Integer getPajakPengeluaran(Map<String, Object> param);

}
