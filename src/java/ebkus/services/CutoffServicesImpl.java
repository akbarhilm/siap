/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.services;

import ebkus.entity.CutoffMapper;
import ebkus.entity.GenerateIdMapper;
import ebkus.model.Cutoff;
import ebkus.model.GenerateId;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service("CutoffServices")
public class CutoffServicesImpl implements CutoffServices {

    private static final Logger log = LoggerFactory.getLogger(CutoffServicesImpl.class);
    @Autowired
    private CutoffMapper cutoffMapper;
    @Autowired
    private GenerateIdMapper genMapper;

    @Override
    public void insertHariLibur(Cutoff cutoff) {
        GenerateId id = new GenerateId();
        id.setNamaTabel("TRTRANSFERLIBUR");
        genMapper.insertGetId(id);
        Integer generatedId = id.getId();
        cutoff.setId(generatedId);
        cutoffMapper.insertHariLibur(cutoff);
    }

    @Override
    public void updateHariLibur(Cutoff cutoff) {
        cutoffMapper.updateHariLibur(cutoff);
    }

    @Override
    public Integer checkLibur() {
        return cutoffMapper.checkLibur();
    }

    @Override
    public void deleteHariLibur(Cutoff cutoff) {
        cutoffMapper.deleteHariLibur(cutoff);
    }

    @Override
    public List<Cutoff> getListHariLibur(Map param) {
        return cutoffMapper.getListHariLibur(param);
    }

    @Override
    public Integer getBanyakListHariLibur(Map param) {
        return cutoffMapper.getBanyakListHariLibur(param);
    }

    @Override
    public void updateWaktuCutoff(Cutoff cutoff) {
        cutoffMapper.updateWaktuCutoff(cutoff);
    }

    @Override
    public Cutoff getWaktuCutoff(Map param) {
        return cutoffMapper.getWaktuCutoff(param);
    }

    @Override
    public Cutoff getHariLibur(Map param) {
        return cutoffMapper.getHariLibur(param);
    }

}
