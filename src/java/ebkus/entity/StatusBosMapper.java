/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.entity;

import ebkus.model.StatusBku;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Racka
 */
public interface StatusBosMapper {

    Integer validateNilai(Map param);

    List<StatusBku> getListBkuBos(Map param);

    List<StatusBku> getListBosDari(Map param);

    List<StatusBku> getListBosPajak(Map param);

    List<Integer> getBanyakBosTotal(Map param);

    List<Integer> getBanyakBosTotalPajak(Map param);

    Integer getBanyakBos(Map param);

    Integer getBanyakBosPajak(Map param);

    Integer getBanyakBosDari(Map param);

    List<BigDecimal> getTotalAll(Map param);

    BigDecimal getTotal(Map param);

    BigDecimal getTotalPajak(Map param);

    BigDecimal getTotalDari(Map param);

    void updateStatusBos(Map param);

    void updateStatusBosByNoMohon(Map param);

    void updateStatusBosPajak(Map param);

    List<Integer> getStatusTutupBuku(Map param);

    List<Integer> getStatusTutupBukuPajak(Map param);

}
