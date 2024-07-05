/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.entity;

import ebkus.model.BukuKasUmum;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Zainab
 */
public interface BatalTransferBopMapper {

    void approveBa(Map param);

    List<BukuKasUmum> getListPembatalan(Map<String, Object> param);

    Integer getBanyakListPembatalan(Map<String, Object> param);

    BukuKasUmum getTotalNilai(Map<String, Object> param);

    List<BukuKasUmum> getListPembatalanPajak(Map<String, Object> param);

    Integer getBanyakListPembatalanPajak(Map<String, Object> param);

    BukuKasUmum getTotalNilaiPajak(Map<String, Object> param);

    void pengajuanBatal(Map<String, Object> param);

    void prosesPembatalan(Map<String, Object> param);

    void prosesPembatalanPajak(Map<String, Object> param);

    void prosesPembatalanBank(Map<String, Object> param);

    Integer getPajakPengeluaran(Map<String, Object> param);

}
