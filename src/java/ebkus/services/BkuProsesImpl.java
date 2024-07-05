/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.services;

import java.util.List;
import java.util.Map;
import ebkus.entity.BkuProsesMapper;
import ebkus.entity.GenerateIdMapper;
import ebkus.model.BkuProses;
import ebkus.model.GenerateId;
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
@Service("bkuProsesServices")
public class BkuProsesImpl implements BkuProsesServices {

    private static final Logger log = LoggerFactory.getLogger(BkuProsesImpl.class);
    @Autowired
    private BkuProsesMapper bkuMapper;
    
    @Autowired
    private GenerateIdMapper genMapper;

    
    @Override
    public List<BkuProses> getListIndex(final Map<String, Object> param) {
        return bkuMapper.getListIndex(param);
    }
    
    @Override
    public Integer getBanyakListIndex(final Map<String, Object> param) {
        return bkuMapper.getBanyakListIndex(param);
    }

    @Override
    public Skpd getSkpd(final Map<String, Object> param) {
        return bkuMapper.getSkpd(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertBatasWaktu(BkuProses bku) {
        GenerateId id1 = new GenerateId();
        id1.setNamaTabel("TRBKUSPROSES");
        genMapper.insertGetId(id1);
        Integer idMaster = id1.getId();

       bku.setId(idMaster);
        bkuMapper.insertBatasWaktu(bku);
    }
    
    @Override
    @Transactional(readOnly = false)
    public void updateBatasWaktu(BkuProses bku) {
        bkuMapper.updateBatasWaktu(bku);
    }
 
    @Override
    public BkuProses getEdit(final Map<String, Object> param) {
        return bkuMapper.getEdit(param);
    }

    
}
