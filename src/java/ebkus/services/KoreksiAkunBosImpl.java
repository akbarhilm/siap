/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.services;

import ebkus.entity.BkuBosMapper;
import ebkus.entity.GenerateIdMapper;
import java.util.List;
import java.util.Map;
import ebkus.model.BukuKasUmum;
import ebkus.model.BkuRinci;
import ebkus.entity.KoreksiAkunBosMapper;
import ebkus.model.GenerateId;
import java.util.HashMap;
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
@Service("koreksiAkunBosServices")
public class KoreksiAkunBosImpl implements KoreksiAkunBosServices {

    private static final Logger log = LoggerFactory.getLogger(KoreksiAkunBosImpl.class);
    @Autowired
    private KoreksiAkunBosMapper koreksiMapper;
    
    @Autowired
    private GenerateIdMapper genMapper;
    
    @Autowired
    private BkuBosMapper bkuMapper;

    

    @Override
    public Integer getMaxTriwulanByRekap(final Map<String, Object> param) {
        return koreksiMapper.getMaxTriwulanByRekap(param);
    }

    @Override
    public Integer getTriwulanByRekap(final Map<String, Object> param) {
        return koreksiMapper.getTriwulanByRekap(param);
    }

    @Override
    public List<BukuKasUmum> getListIndex(final Map<String, Object> param) {
        return koreksiMapper.getListIndex(param);
    }

    @Override
    public Integer getBanyakListIndex(final Map<String, Object> param) {
        return koreksiMapper.getBanyakListIndex(param);
    }

    @Override
    public List<BukuKasUmum> getListSpj(final Map<String, Object> param) {
        return koreksiMapper.getListSpj(param);
    }

    @Override
    public Integer getBanyakListSpj(final Map<String, Object> param) {
        return koreksiMapper.getBanyakListSpj(param);
    }

    @Override
    public List<BkuRinci> getListSpjKoreksi(final Map<String, Object> param) {
        return koreksiMapper.getListSpjKoreksi(param);
    }

    @Override
    public Integer getBanyakListSpjKoreksi(final Map<String, Object> param) {
        return koreksiMapper.getBanyakListSpjKoreksi(param);
    }

    @Override
    public BukuKasUmum getDataSekolah(final Map<String, Object> param) {
        return koreksiMapper.getDataSekolah(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateBkuSpj(BukuKasUmum bku) {

        // UPDATE MASTER
        koreksiMapper.updateSpjMasterById(bku);
        Integer idMaster = bku.getIdBku();

        // INSERT RINCI
        final List<BkuRinci> list = bku.getListBkuRinci();
        // create ID BKU RINCI
        GenerateId id2 = new GenerateId();
        Integer idRinci;

        for (BkuRinci bkurinci : list) {
            Integer idrinci = bkurinci.getIdBkuRinci();
            
            if (idrinci > 0) { // DATA LAMA
                bkuMapper.updateSpjRinciById(bkurinci);

            } else { // DATA BARU
                id2.setNamaTabel("TMBKUSBOSRINCI");
                genMapper.insertGetId(id2);
                idRinci = id2.getId();
                bkurinci.setIdBku(idMaster);
                bkurinci.setId(idRinci);

                bkuMapper.insertBkuSpjRinci(bkurinci);

            }
        }
    }

    @Override
    public BukuKasUmum getDataEdit(final Map<String, Object> param) {
        return koreksiMapper.getDataEdit(param);
    }

    
}
