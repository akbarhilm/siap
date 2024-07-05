/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.entity;

import ebkus.model.Rkas;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Racka
 */
public interface RkasMapper {

    List<Rkas> getListRkas(Map param);

    Integer getBanyakRkas(Map param);

    Rkas getRkas(Map param);

    void updateRkas(Rkas param);
}
