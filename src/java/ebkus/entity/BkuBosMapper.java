/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.entity;

import ebkus.model.BkuRinci;
import ebkus.model.BukuKasUmum;
import ebkus.model.Kegiatan;
import ebkus.model.Setor;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Zainab
 */
public interface BkuBosMapper {

    List<BukuKasUmum> getListIndexSemua(Map<String, Object> param);

    List<BukuKasUmum> getListIndexTransfer(Map<String, Object> param);

    List<BukuKasUmum> getListIndexBelum(Map<String, Object> param);

    Integer getBanyakListIndexSemua(Map<String, Object> param);

    Integer getBanyakListIndexTransfer(Map<String, Object> param);

    Integer getBanyakListIndexBelum(Map<String, Object> param);

    BukuKasUmum getNilaiIndex(Map<String, Object> param);

    void insertBkuJoMaster(BukuKasUmum param);

    void insertBkuC12Master(BukuKasUmum param);

    void insertBkuC12Rinci(BkuRinci param);

    List<BukuKasUmum> getBkuEditC12(Map<String, Object> param);

    List<BkuRinci> getBkuEditC12Rinci(Integer param);

    void updateC12MasterById(BukuKasUmum param);

    void updateC12RinciById(BkuRinci param);

    Integer getBkuNo(Map<String, Object> param);

    Integer getBkuNoMohon(Map<String, Object> param);

    Integer getCountSaldoAwal(Map<String, Object> param);

    void insertBkuJoRinci(BkuRinci param);

    BukuKasUmum getBkuEditJo(Map<String, Object> param);

    BukuKasUmum getSaldoKasJO(Map<String, Object> param);

    void updateJoMasterById(BukuKasUmum param);

    void updateJoRinciById(BukuKasUmum param);

    void deleteById(Map param);

    void deleteRinciById(Map param);

    List<BukuKasUmum> getKodeSetor(Map<String, Object> param);

    List<Setor> getListSetoran(Map<String, Object> param);

    Integer getBanyakListSetoran(Map<String, Object> param);

    BukuKasUmum getSisaKas(Map<String, Object> param);

    List<BkuRinci> getListSpj(Map<String, Object> param);

    Integer getBanyakListSpj(Map<String, Object> param);

    void insertBkuSpjMaster(BukuKasUmum param);

    void insertBkuSpjRinci(BkuRinci param);

    BukuKasUmum getBkuEditSpj(Map<String, Object> param);

    Kegiatan getDataKegiatan(Map<String, Object> param);

    void updateSpjMasterById(BukuKasUmum param);

    void updateSpjMasterByIdBukti(BukuKasUmum param);

    void updateSpjRinciById(BkuRinci param);

    List<BukuKasUmum> getListSpjRinci(Map<String, Object> param);

    Integer getBanyakListSpjRinci(Map<String, Object> param);

    List<BukuKasUmum> getListPajakPn(Map<String, Object> param);

    Integer getBanyakListPajakPn(Map<String, Object> param);

    List<BkuRinci> getListPajakSpj(Map<String, Object> param);

    Integer getBanyakListPajakSpj(Map<String, Object> param);

    List<BkuRinci> getListPajakPg(Map<String, Object> param);

    Integer getBanyakListPajakPg(Map<String, Object> param);

    BukuKasUmum getDataWP(Map<String, Object> param);

    void insertBkuPajakMaster(BukuKasUmum param);

    void insertBkuPajakRinci(BkuRinci param);

    BukuKasUmum getBkuEditPajak(Map<String, Object> param);

    void updatePajakMasterById(BukuKasUmum param);

    void updatePajakRinciByIdbkubos(BkuRinci param);

    Integer getBanyakDataPjPn(Map<String, Object> param);

    Integer getStatusSpj(Map<String, Object> param);

    Integer getTriwulanByRekap(Map<String, Object> param);

    Integer getMaxTriwulan(Map<String, Object> param);

    BukuKasUmum getPkBlj(Map<String, Object> param);

    List<BukuKasUmum> getListPnJg(Map<String, Object> param);

    Integer getBanyakListPnJg(Map<String, Object> param);

    BigDecimal getSaldoAwal(Map<String, Object> param);

    List<BkuRinci> getListSpjEdit(Map<String, Object> param);

    Integer getBanyakListSpjEdit(Map<String, Object> param);

    void updateSpjNetto(BkuRinci param);

    void updateSpjNettoEdit(BkuRinci param);

    void deletePajakSpj(Map param);

    void deleteRinciPajakSpj(Map param);

    void procUpdateSpjPjNetto(BukuKasUmum param);

    void insertParamPajak(BukuKasUmum param);

    void updateParamPajak(BukuKasUmum param);

    void deleteParamPajak(Map param);

    BukuKasUmum getDataParam(Map param);

    Map validateP2P3(Map param);

    String getWpPjPg(Map param);

}
