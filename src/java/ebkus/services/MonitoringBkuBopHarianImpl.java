/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.services;

import java.util.List;
import java.util.Map;
import ebkus.entity.MonitoringBkuBopHarianMapper;
import ebkus.model.MonitoringBkuBopHarian;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author idns
 */
@Transactional(readOnly = true)
@Service("monitoringBkuBopHarianServices")
public class MonitoringBkuBopHarianImpl implements MonitoringBkuBopHarianServices {

    //private static final Logger log = LoggerFactory.getLogger(MonitoringBkuBopHarianImpl.class);
    @Autowired
    private MonitoringBkuBopHarianMapper monitoringBkuBopHarianMapper;

    @Override
    public List<MonitoringBkuBopHarian> getListIndex(final Map<String, Object> param) {
        return monitoringBkuBopHarianMapper.getListIndex(param);
    }

    @Override
    public Integer getBanyakListIndex(final Map<String, Object> param) {
        return monitoringBkuBopHarianMapper.getBanyakListIndex(param);
    }

    /*@Override
    public BukuKasUmum getNilaiIndex(final Map<String, Object> param) {
        return bkuMapper.getNilaiIndex(param);
    }*/

}
