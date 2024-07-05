/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.entity;

import ebkus.model.BkuRinci;
import ebkus.model.BkuTransfer;
import ebkus.model.BukuKasUmum;
import ebkus.model.PajakTransfer;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Zainab
 */
public interface PajakTransferBopMapper {

    PajakTransfer getDataPajak(Map<String, Object> param);

    List<BkuRinci> getListBkuRinci(Map<String, Object> param);

    Integer getBanyakListBkuRinci(Map<String, Object> param);

    Integer getBkuNo(Map<String, Object> param);

    void updateBkuById(BukuKasUmum param);

    BkuTransfer getDataSekolah(Map<String, Object> param);

    BukuKasUmum getSaldoKas(Map<String, Object> param);

    void insertBkuBankError(BkuTransfer param);

    void updateRinciPotPajak(PajakTransfer param);

    void insertRinciPotPajak(PajakTransfer param);

    void updateInquiryMasterPot(PajakTransfer param);

    void updateInquiryPot(PajakTransfer param);

    void updateCetakCount(Map<String, Object> param);

}
