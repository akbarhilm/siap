package ebkus.services;

import ebkus.model.BkuRinci;
import ebkus.model.BkuTransfer;
import ebkus.model.BukuKasUmum;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Zainab
 */
public interface BkuTransferBopServices {

    List<BkuTransfer> getListTransfer(Map<String, Object> param);

    Integer getBanyakListTransfer(Map<String, Object> param);

    BukuKasUmum getDataTf(Map<String, Object> param);

    void updateBkuById(BukuKasUmum param);

    List<BkuRinci> getListBkuRinci(Map<String, Object> param);

    Integer getBanyakListBkuRinci(Map<String, Object> param);

    BkuTransfer getDataBankTujuan(Map<String, Object> param);

    BkuTransfer getDataBankBOP(Map<String, Object> param);

    BkuTransfer getDataNpwp(Map<String, Object> param);

    BukuKasUmum getSaldoKas(Map<String, Object> param);

    List<BkuRinci> getListPajak(Map<String, Object> param);

    Integer getBanyakListPajak(Map<String, Object> param);

    void insertBkuPajak(BukuKasUmum param);

    BukuKasUmum getDataPajakSpj(Map<String, Object> param);

    BukuKasUmum getNilaiSpjNetto(Map<String, Object> param);

    BkuTransfer getKodeStan(Map param);

    void insertBkuBank(BkuTransfer param);

    void updateBkuBank(BkuTransfer param);

    List<BukuKasUmum> getListPajakPnBop(Map<String, Object> param);

    void insertBkuBankError(BkuTransfer param);

    void insertResponse(Map param);

}
