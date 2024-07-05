/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.services;

import java.util.List;
import java.util.Map;
import ebkus.entity.ListSkpdMapper;
import ebkus.model.Skpd;
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
@Service("listSkpdServices")
public class ListSkpdImpl implements ListSkpdServices {

    private static final Logger log = LoggerFactory.getLogger(ListSkpdImpl.class);
    @Autowired
    private ListSkpdMapper skpdMapper;
    
    
    @Override
    public Integer getBanyakSkpd(final Map<String, Object> param) {
        return skpdMapper.getBanyakSkpd(param);
    }
    
    @Override
    public List<Skpd> getSkpd(final Map<String, Object> param) {
        return skpdMapper.getSkpd(param);
    }
    
     @Override
    public Skpd getSkpdById(Integer id) {
        return skpdMapper.getSkpdById(id);
    }
  
    
    
}

  