/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.entity;

import ebkus.model.BapKas;
import ebkus.model.BapKasRinci;
import java.util.List;
import java.util.Map;

/**
 *
 * @author zainab
 */
public interface BapKasBosMapper {

    List<Integer> getBapTriwulan(Map param);

    void updateApproval(Map param);

    BapKas getBapKasByTriwulan(Map param);

    Integer getCountTglBkuProses(Map param);

    List<BapKas> getBapKas(Map<String, Object> param);

    Integer getCountBapKas(Map param);

    Integer isTutupBuku(Map param);

    List<Map> getTriwulan(Map param);

    void insertBapKas(BapKas param);

    public void insertBapKasRinci(BapKasRinci bapKasRinci);

    BapKas getBapKasById(Map param);

    List<BapKas> getAllBAPKAS(Map<String, Object> param);

    Integer getBanyakAllBAPKAS(Map<String, Object> param);

    void updateBapKas(BapKas param);

    void updateBapKasRinci(BapKasRinci param);

    void deleteBapKas(Map param);

    void deleteBapKasRinci(Map param);

    List<Map> getnilaiparam(Map parameter);

    BapKas getNilaiKas(Map param);

}
