/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.services;

import ebkus.model.StatusBku;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Racka
 */
public interface StatusBopServices {

    Integer validateNilai(Map param);

    List<StatusBku> getListBkuBop(Map param);

    List<StatusBku> getListBopDari(Map param);

    List<StatusBku> getListBopPajak(Map param);

    List<Integer> getBanyakBopTotal(Map param);

    List<Integer> getBanyakBopTotalPajak(Map param);

    Integer getBanyakBop(Map param);

    Integer getBanyakBopPajak(Map param);

    Integer getBanyakBopDari(Map param);

    List<BigDecimal> getTotalAll(Map param);

    BigDecimal getTotal(Map param);

    BigDecimal getTotalPajak(Map param);

    BigDecimal getTotalDari(Map param);

    void updateStatusBop(Map param);

    void updateStatusBopByNoMohon(Map param);

    void updateStatusBopPajak(Map param);

    List<Integer> getStatusTutupBuku(Map param);

    List<Integer> getStatusTutupBukuPajak(Map param);

    void deleteSisa(Map param);

}
