package ebkus.services;

import ebkus.model.Bank;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Zainab
 */
public interface BankServices {

  List<Bank> getListBank(Map<String, Object> param);

  Integer getBanyakListBank(Map<String, Object> param);
   
  
}
