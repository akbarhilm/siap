/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.services;

import ebkus.model.Modul;
import java.util.List;
import java.util.Map;

/**
 *
 * @author idns
 */
public interface ModulAdministrationServices {

    List<Modul> getListModul(Map<String, Object> map);

    Integer getBanyakListModul(Map<String, Object> map);
    
    Modul insertModul(Modul modul);

    void updateModul(Modul modul);

    void deleteModul(Integer id);
        
    Modul getModulById(Integer id);
}
