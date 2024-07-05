/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.services;

import ebkus.model.BkuRinci;
import ebkus.model.Kegiatan;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Racka
 */
public interface PerubahanRkasServices {

    List<Kegiatan> getListKegiatan(Map param);

    List<BkuRinci> getListAkun(Map param);

    List<BkuRinci> getListKomponen(Map param);

    Integer getBanyakListKomponen(Map param);

    void getUpdateStatusKomponen(Map param);
}
