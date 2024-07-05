/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */   

package ebkus.entity;
import java.util.List;
import java.util.Map;
import ebkus.model.Skpd;

/**
 *
 * @author Zainab
 */
public interface ListSkpdMapper {
   
  List<Skpd> getSkpd(Map<String, Object> param);
  
  Integer getBanyakSkpd(Map<String, Object> param);
  
  Skpd getSkpdById(Integer id);
  

}
