/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.services;

import ebkus.model.BkuRinci;
import ebkus.model.BukuKasUmum;
import ebkus.model.Kegiatan;
import ebkus.model.Setor;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Racka
 */
public interface BkuBosServices {

    List<BukuKasUmum> getListIndex(Map<String, Object> param);

    Integer getBanyakListIndex(Map<String, Object> param);

    BukuKasUmum getNilaiIndex(Map<String, Object> param);

    void insertBkuJo(BukuKasUmum param);

    List<BukuKasUmum> getBkuEditC12(Map<String, Object> param);

    void updateBkuC12(BukuKasUmum bku);

    BukuKasUmum getBkuEditJo(Map<String, Object> param);

    BukuKasUmum getSaldoKasJO(Map<String, Object> param);

    void updateBkuJo(BukuKasUmum param);

    void deleteBkuById(Map param);

    Integer getCountSaldoAwal(Map<String, Object> param);

    List<BukuKasUmum> getKodeSetor(Map<String, Object> param);

    List<Setor> getListSetoran(Map<String, Object> param);

    Integer getBanyakListSetoran(Map<String, Object> param);

    BukuKasUmum getSisaKas(Map<String, Object> param);

    List<BkuRinci> getListSpj(Map<String, Object> param);

    Integer getBanyakListSpj(Map<String, Object> param);

    void insertBkuSpj(BukuKasUmum param);

    BukuKasUmum getBkuEditSpj(Map<String, Object> param);

    Kegiatan getDataKegiatan(Map<String, Object> param);

    void updateBkuSpj(BukuKasUmum param);

    void updateBkuSpjBukti(BukuKasUmum param);

    List<BukuKasUmum> getListSpjRinci(Map<String, Object> param);

    Integer getBanyakListSpjRinci(Map<String, Object> param);

    List<BukuKasUmum> getListPajakPn(Map<String, Object> param);

    Integer getBanyakListPajakPn(Map<String, Object> param);

    List<BkuRinci> getListPajakSpj(Map<String, Object> param);

    Integer getBanyakListPajakSpj(Map<String, Object> param);

    List<BkuRinci> getListPajakPg(Map<String, Object> param);

    Integer getBanyakListPajakPg(Map<String, Object> param);

    BukuKasUmum getDataWP(Map<String, Object> param);

    void insertBkuPajak(BukuKasUmum param);

    BukuKasUmum getBkuEditPajak(Map<String, Object> param);

    void updateBkuPajak(BukuKasUmum param);

    Integer getBanyakDataPjPn(Map<String, Object> param);

    Integer getStatusSpj(Map<String, Object> param);

    Integer getTriwulanByRekap(Map<String, Object> param);

    Integer getMaxTriwulan(Map<String, Object> param);

    BukuKasUmum getPkBlj(Map<String, Object> param);

    List<BukuKasUmum> getListPnJg(Map<String, Object> param);

    Integer getBanyakListPnJg(Map<String, Object> param);

    BigDecimal getSaldoAwal(Map<String, Object> param);

    void insertBkuC12(List<BukuKasUmum> param);

    List<BkuRinci> getListSpjEdit(Map<String, Object> param);

    Integer getBanyakListSpjEdit(Map<String, Object> param);

    void deletePajakPn(BukuKasUmum param);

    BukuKasUmum getDataParam(Map param);

    Map validateP2P3(Map param);

    String getWpPjPg(Map param);

}
