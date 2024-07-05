/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.services;

import ebkus.model.BapKas;
import java.util.List;
import java.util.Map;

/**
 *
 * @author zainab
 */
public interface BapKasBosServices {

    List<Integer> getBapTriwulan(Map param);

    void updateApproval(Map param);

    BapKas getBapKasByTriwulan(Map param);

    Integer getCountTglBkuProses(Map param);

    List<BapKas> getBapKas(Map<String, Object> param);

    Integer getCountBapKas(Map param);

    Integer isTutupBuku(Map param);

    List<Map> getTriwulan(Map param);

    void insertBapKasAll(BapKas bapKas);

    BapKas getBapKasById(Map param);

    List<BapKas> getAllBAPKAS(Map<String, Object> param);

    Integer getBanyakAllBAPKAS(Map<String, Object> param);

    void updateBapKasAll(BapKas bapkas);

    void deleteBapKas(Map param);

    List<Map> getnilaiparam(Map param);

    BapKas getNilaiKas(Map param);

}
