/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.services;

import java.util.List;
import java.util.Map;
import ebkus.entity.BankMapper;
import ebkus.model.Bank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; 

/**
 *
 * @author Zainab
 */

@Transactional(readOnly = true)
@Service("bankServices")
public class BankImpl implements BankServices {

    private static final Logger log = LoggerFactory.getLogger(BankImpl.class);
    @Autowired
    private BankMapper bankMapper;
    
    
    @Override
    public List<Bank> getListBank(final Map<String, Object> param) {
        log.debug("data ============== "+bankMapper.getListBank(param));
        return bankMapper.getListBank(param);
    }
    
    @Override
    public Integer getBanyakListBank(final Map<String, Object> param) {
        return bankMapper.getBanyakListBank(param);
    }

}
