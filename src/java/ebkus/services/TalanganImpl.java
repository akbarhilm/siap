/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.services;

import ebkus.entity.GenerateIdMapper;
import ebkus.entity.TalanganMapper;
import ebkus.model.GenerateId;
import ebkus.model.MCB;
import ebkus.model.PaguTalangan;
import ebkus.model.WSTalangan;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service("talanganBopServices")
public class TalanganImpl implements TalanganServices {

    private static final Logger log = LoggerFactory.getLogger(ReqTokenImpl.class);
    @Autowired
    private TalanganMapper talanganMapper;

    @Autowired
    private GenerateIdMapper genMapper;

    @Override
    public List<PaguTalangan> getListIndex(Map<String, Object> param) {
        return talanganMapper.getListIndex(param);
    }

    @Override
    public Integer getBanyakListIndex(Map<String, Object> param) {
        return talanganMapper.getBanyakListIndex(param);
    }

    @Override
    public BigDecimal getNilaiIndex(Map<String, Object> param) {
        return talanganMapper.getNilaiIndex(param);
    }

    @Override
    public BigDecimal getNilaiIndexWS(Map<String, Object> param) {
        return talanganMapper.getNilaiIndexWS(param);
    }

    @Override
    public Integer getMaxTutup(Map<String, Object> param) {
        return talanganMapper.getMaxTutup(param);
    }

    @Override
    public void deleteById(Map param) {
        talanganMapper.deleteById(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertPaguTalangan(PaguTalangan param) {

        GenerateId id1 = new GenerateId();
        id1.setNamaTabel("TMBKUSPAGUTALANGAN");
        genMapper.insertGetId(id1);
        Integer idtalangan = id1.getId();

        PaguTalangan talang = param;
        talang.setId(idtalangan);

        talanganMapper.insertPaguTalangan(talang);
    }

    @Override
    public PaguTalangan getEditPaguTalangan(Map<String, Object> param) {
        return talanganMapper.getEditPaguTalangan(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void updatePaguTalanganById(PaguTalangan param) {
        talanganMapper.updatePaguTalanganById(param);
    }

    @Override
    public Integer getMaxTriwulan(Map<String, Object> param) {
        if (param.get("tipe").equals("2")) {
            return talanganMapper.getMaxTriwulanBos(param);
        } else {
            return talanganMapper.getMaxTriwulanBop(param);
        }
    }

    @Override
    public List<MCB> getMcb(Map<String, Object> param) {
        return talanganMapper.getMcb(param);
    }

    @Override
    public List<WSTalangan> getListIndexWS(Map<String, Object> param) {
        return talanganMapper.getListIndexWS(param);
    }

    @Override
    public Integer getBanyakListIndexWS(Map<String, Object> param) {
        return talanganMapper.getBanyakListIndexWS(param);
    }

}
