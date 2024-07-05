/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */   

package ebkus.entity;
import java.util.List;
import java.util.Map;
import ebkus.model.Bank;

/**
 *
 * @author Zainab
 */
public interface BankMapper {
   
  List<Bank> getListBank(Map<String, Object> param);

  Integer getBanyakListBank(Map<String, Object> param);
  
  
}
