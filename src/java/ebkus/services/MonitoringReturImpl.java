/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.services;


import ebkus.entity.MonitoringReturMapper;
import ebkus.model.Retur;

import java.util.List;
import java.util.Map;
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
@Service("monitoringReturServices")
public class MonitoringReturImpl implements MonitoringReturServices {

    private static final Logger log = LoggerFactory.getLogger(MonitoringReturImpl.class);
    @Autowired
    private MonitoringReturMapper retMapper;

    @Override
    public Integer getBanyakReturBOP(final Map<String, Object> param) {
        return retMapper.getBanyakReturBOP(param);
    }

    @Override
    public List<Retur> getReturBOP(final Map<String, Object> param) {
        return retMapper.getReturBOP(param);
    }
    
    @Override
    public Integer getBanyakReturBOS(final Map<String, Object> param) {
        return retMapper.getBanyakReturBOS(param);
    }

    @Override
    public List<Retur> getReturBOS(final Map<String, Object> param) {
        return retMapper.getReturBOS(param);
    }

    

}
