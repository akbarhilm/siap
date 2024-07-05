/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.entity;

import ebkus.model.BkuRinci;
import ebkus.model.BkuTransfer;
import ebkus.model.BukuKasUmum;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Zainab
 */
public interface BkuTransferBosMapper {

    List<BkuTransfer> getListTransfer(Map<String, Object> param);

    Integer getBanyakListTransfer(Map<String, Object> param);

    BukuKasUmum getDataTf(Map<String, Object> param);

    void updateBkuById(BukuKasUmum param);

    List<BkuRinci> getListBkuRinci(Map<String, Object> param);

    Integer getBanyakListBkuRinci(Map<String, Object> param);

    BkuTransfer getDataBankTujuan(Map<String, Object> param);

    BkuTransfer getDataBankBOS(Map<String, Object> param);

    BkuTransfer getDataNpwp(Map<String, Object> param);

    BukuKasUmum getSaldoKas(Map<String, Object> param);

    List<BkuRinci> getListPajak(Map<String, Object> param);

    Integer getBanyakListPajak(Map<String, Object> param);

    Integer getBkuNo(Map<String, Object> param);

    Integer getBkuNoMohon(Map<String, Object> param);

    void deletePajakMaster(BukuKasUmum param);

    void deletePajakRinci(BukuKasUmum param);

    void insertBkuPajakMaster(BukuKasUmum param);

    void insertBkuPajakRinci(BkuRinci param);

    void updateNilaiSpj(Map param);

    void updateNilaiSpjNol(Map param);

    BukuKasUmum getDataPajakSpj(Map<String, Object> param);

    BukuKasUmum getNilaiSpjNetto(Map<String, Object> param);

    BkuTransfer getKodeStan(Map param);

    void insertBkuBank(BkuTransfer param);

    void updateBkuBank(BkuTransfer param);

    List<BukuKasUmum> getListPajakPnBos(Map<String, Object> param);

    void updatePajakPnById(BukuKasUmum param);

    void insertBkuBankError(BkuTransfer param);

}
