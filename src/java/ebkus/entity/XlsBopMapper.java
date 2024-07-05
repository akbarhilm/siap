/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */   

package ebkus.entity;
import java.util.List;
import java.util.Map;
import ebkus.model.RkasBkus;

/**
 *
 * @author Zainab
 */
public interface XlsBopMapper {
   
  List<RkasBkus> getListRkasBkus(Map<String, Object> param);

  Integer getBanyakRkasBkus(Map<String, Object> param);
  
  
}
