/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.entity;

import ebkus.model.Cutoff;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Racka
 */
public interface CutoffMapper {

    void insertHariLibur(Cutoff cutoff);

    void updateHariLibur(Cutoff cutoff);

    void deleteHariLibur(Cutoff cutoff);

    Cutoff getHariLibur(Map param);

    Integer checkLibur();

    List<Cutoff> getListHariLibur(Map param);

    Integer getBanyakListHariLibur(Map param);

    void updateWaktuCutoff(Cutoff cutoff);

    Cutoff getWaktuCutoff(Map param);
}
