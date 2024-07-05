/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.services;

import java.util.List;
import java.util.Map;
import ebkus.entity.BankMapper;
import ebkus.entity.XlsBopMapper;
import ebkus.model.Bank;
import ebkus.model.RkasBkus;
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
@Service("xlsBopServices")
public class XlsBopImpl implements XlsBopServices {

    private static final Logger log = LoggerFactory.getLogger(XlsBopImpl.class);
    @Autowired
    private XlsBopMapper bankMapper;
    
    
    @Override
    public List<RkasBkus> getListRkasBkus(final Map<String, Object> param) {
        
        return bankMapper.getListRkasBkus(param);
    }
    
    @Override
    public Integer getBanyakRkasBkus(final Map<String, Object> param) {
        return bankMapper.getBanyakRkasBkus(param);
    }

}
