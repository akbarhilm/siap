/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.entity;

import ebkus.model.BkuTransfer;
import ebkus.model.BukuKasUmum;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Zainab
 */
public interface StatusTransferMapper {

    List<BukuKasUmum> getListSpjBop(Map param);

    Integer getBanyakListSpjBop(Map param);

    List<BukuKasUmum> getListSpjBos(Map param);

    Integer getBanyakListSpjBos(Map param);

    List<BkuTransfer> getListBank(Map param);

    Integer getBanyakListBank(Map param);

    void updateDataBank(Map param);

    void updateDataBkuBop(BukuKasUmum bku);

    void updateDataBkuBos(BukuKasUmum bku);

    Integer getBanyakBankBerhasil(Map param);

}
