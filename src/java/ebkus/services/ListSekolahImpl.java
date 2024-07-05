/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.services;

import ebkus.entity.ListSekolahMapper;
import ebkus.model.Sekolah;
import java.util.HashMap;
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
@Service("listSekolahServices")
public class ListSekolahImpl implements ListSekolahServices {

    private static final Logger log = LoggerFactory.getLogger(ListSekolahImpl.class);
    @Autowired
    private ListSekolahMapper journalMapper;

    @Override
    public Integer getBanyakAllSekolah(final Map<String, Object> param) {
        return journalMapper.getBanyakAllSekolah(param);
    }

    @Override
    public List<Sekolah> getAllSekolah(final Map<String, Object> param) {
        return journalMapper.getAllSekolah(param);
    }

    @Override
    public Integer getBanyakAllSekolahPlt(final Map<String, Object> param) {
        return journalMapper.getBanyakAllSekolahPlt(param);
    }

    @Override
    public List<Sekolah> getAllSekolahPlt(final Map<String, Object> param) {
        return journalMapper.getAllSekolahPlt(param);
    }

    @Override
    public Sekolah getSekolahById(Integer id) {
        return journalMapper.getSekolahById(id);
    }

    @Override
    public Integer getBanyakSekolahByKodeGrup(Map<String, Object> param) {
        String kodegrup = param.get("kodegrup").toString();
        String kodesumbdana = param.get("kodesumbdana").toString();
        if("3".equals(kodegrup) && "1".equals(kodesumbdana)){
            param.put("idskpd", -99);
        } else if (("1".equals(kodegrup) && "2".equals(kodesumbdana)) || ("2".equals(kodegrup) && "2".equals(kodesumbdana))){
            param.put("idskpd", 12782);
        } else if (("2".equals(kodegrup) && "1".equals(kodesumbdana)) || ("4".equals(kodegrup))){
            param.put("idskpd", null);
        } else {
            
        }
        return journalMapper.getBanyakAllSekolah(param);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Sekolah> getSekolahByKodeGrup(Map<String, Object> param) {
        log.debug("======= MASUK GET SEKOLAH BY KODE GRUP ========");
        String kodegrup = param.get("kodegrup").toString();
        String kodesumbdana = param.get("kodesumbdana").toString();
        log.debug("======= kodegrup = "+ kodegrup);
        log.debug("======= kodesumbdana = "+ kodesumbdana);
        
        if("3".equals(kodegrup) && "1".equals(kodesumbdana)){
            log.debug("======= kodegrup == \"3\" && kodesumbdana == \"1\" ========");
            param.put("idskpd", -99);
        } else if (("1".equals(kodegrup) && "2".equals(kodesumbdana)) || ("2".equals(kodegrup) && "2".equals(kodesumbdana))){
            log.debug("======= (kodegrup == \"1\" && kodesumbdana == \"2\") || (kodegrup == \"2\" && kodesumbdana == \"2\") ========");
            param.put("idskpd", 12782);
        } else if (("2".equals(kodegrup) && "1".equals(kodesumbdana)) || ("4".equals(kodegrup))){
            log.debug("======= (kodegrup == \"2\" && kodesumbdana == \"1\") || (kodegrup == \"4\") ========");
            param.put("idskpd", null);
        } else {
            log.debug("======= ELSE ========");
        }
        return journalMapper.getAllSekolah(param);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
