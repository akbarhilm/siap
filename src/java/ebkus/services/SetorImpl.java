/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.services;

import ebkus.entity.GenerateIdMapper;
import ebkus.entity.SetorMapper;
import ebkus.model.BukuKasUmum;
import ebkus.model.GenerateId;
import ebkus.model.Setor;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author M.Mustakim
 */
@Transactional(readOnly = true)
@Service("setorServices")
public class SetorImpl implements SetorServices {

    private static final Logger log = LoggerFactory.getLogger(SetorImpl.class);
    @Autowired
    private GenerateIdMapper genIdMapper;

    @Autowired
    private SetorMapper setorMapper;

    @Override
    public Integer getNomorSetoran(Map<String, Object> param) {
        return setorMapper.getNomorSetoran(param);
    }

    @Override
    public Setor getNilaiST(Map<String, Object> param) {
        //String kodeSumbdana = (String) param.get("kodesumbdana");
        //log.debug("PARAM = " + param.toString());
        //if (kodeSumbdana.equals("1")) {
        //    return setorMapper.getNilaiSTBos(param);
        //} else if (kodeSumbdana.equals("2")) {
            return setorMapper.getNilaiSTBop(param);
        //}
        //return new BigDecimal(0);
    }

    @Override
    public long getBanyakListBkuJgPn(Map<String, Object> param) {
        String kodeSumbdana = (String) param.get("kodesumbdana");
        log.debug("PARAM2 = " + param.toString());
        if (kodeSumbdana.equals("1")) {
            return setorMapper.getBanyakListBkuJgPnBos(param);
        } else if (kodeSumbdana.equals("2")) {
            return setorMapper.getBanyakListBkuJgPnBop(param);
        }
        return 0;
    }

    @Override
    public List<BukuKasUmum> getListBkuJgPn(Map<String, Object> param) {
        String kodeSumbdana = (String) param.get("kodesumbdana");
        if (kodeSumbdana.equals("1")) {
            return setorMapper.getListBkuJgPnBos(param);
        } else if (kodeSumbdana.equals("2")) {
            return setorMapper.getListBkuJgPnBop(param);
        }
        return null;
    }

    @Override
    @Transactional(readOnly = false)
    public void insertSetor(Setor setor) {
        if (setor.getNilaiSetor().compareTo(BigDecimal.ZERO) == 1) {
            GenerateId gen = new GenerateId();
            gen.setNamaTabel("TMSETOR");
            genIdMapper.insertGetId(gen);
            setor.setIdSetor(gen.getId());
            setorMapper.insertSetor(setor);
        }
    }

    @Override
    public long getBanyakListIndexSetor(Map<String, Object> param) {
        return setorMapper.getBanyakListIndexSetor(param);
    }

    @Override
    public List<Setor> getListIndexSetor(Map<String, Object> param) {
        return setorMapper.getListIndexSetor(param);
    }

    @Override
    public Setor getSetorById(Integer idsetor) {
        return setorMapper.getSetorById(idsetor);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateSetor(Setor setor) {
        setorMapper.updateSetor(setor);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteSetor(Setor setor) {
        setorMapper.deleteSetor(setor);
    }

    @Override
    public long getBanyakListCetakSetor(Map<String, Object> param) {
        return setorMapper.getBanyakListCetakSetor(param);
    }

    @Override
    public List<Setor> getListCetakSetor(Map<String, Object> param) {
        return setorMapper.getListCetakSetor(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertSetorCetak(List<Map<String, Object>> mapdata) {
        for (Map<String, Object> mapdetil : mapdata) {
            setorMapper.insertSetorCetak(mapdetil);
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteSetorCetak(Integer idsetor) {
        setorMapper.deleteSetorCetak(idsetor);
    }

    @Override
    public Integer getBanyakListSetorApprove(Map<String, Object> param) {
        return setorMapper.getBanyakListSetorApprove(param);
    }

    @Override
    public List<Setor> getListSetorApprove(Map<String, Object> param) {
        return setorMapper.getListSetorApprove(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateSetorApprove(List<Map<String, Object>> mapdata) {
        for (Map<String, Object> mapdetil : mapdata) {
            setorMapper.updateSetorApprove(mapdetil);
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteSetorApprove(Integer idsetor) {
        setorMapper.deleteSetorApprove(idsetor);
    }

    @Override
    public Integer getBanyakListRinciReal(Map<String, Object> param) {
        return setorMapper.getBanyakListRinciReal(param);
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Setor> getListRinciReal(Map<String, Object> param) {
        return setorMapper.getListRinciReal(param);
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Integer getBanyakListBkuRtPn(Map<String, Object> param) {
        String kodeSumbdana = (String) param.get("kodesumbdana");
        log.debug("PARAM2 = " + param.toString());
        if (kodeSumbdana.equals("1")) {
            return setorMapper.getBanyakListBkuRtPnBos(param);
        } else if (kodeSumbdana.equals("2")) {
            return setorMapper.getBanyakListBkuRtPnBop(param);
        }
        return 0;
    }

    @Override
    public List<BukuKasUmum> getListBkuRtPn(Map<String, Object> param) {
        String kodeSumbdana = (String) param.get("kodesumbdana");
        if (kodeSumbdana.equals("1")) {
            return setorMapper.getListBkuRtPnBos(param);
        } else if (kodeSumbdana.equals("2")) {
            return setorMapper.getListBkuRtPnBop(param);
        }
        return null;
    }
}
