/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.services;

import ebkus.entity.BkuBopMapper;
import ebkus.entity.GenerateIdMapper;
import ebkus.entity.KoreksiNilaiSpjBopMapper;
import ebkus.model.BkuRinci;
import ebkus.model.BukuKasUmum;
import ebkus.model.GenerateId;
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
@Service("koreksiNilaiSpjBopServices")
public class KoreksiNilaiSpjBopImpl implements KoreksiNilaiSpjBopServices {

    private static final Logger log = LoggerFactory.getLogger(KoreksiNilaiSpjBopImpl.class);
    @Autowired
    private KoreksiNilaiSpjBopMapper koreksiMapper;

    @Autowired
    private GenerateIdMapper genMapper;

    @Autowired
    private BkuBopMapper bkuMapper;

    @Override
    public List<BukuKasUmum> getListTriwulanByRekap(final Map<String, Object> param) {
        return koreksiMapper.getListTriwulanByRekap(param);
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
    public BukuKasUmum getDataSekolah(final Map<String, Object> param) {
        return koreksiMapper.getDataSekolah(param);
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
    @Transactional(readOnly = false)
    public void insertKoreksi(BukuKasUmum bku) {
        // create ID BKU MASTER
        GenerateId id1 = new GenerateId();
        id1.setNamaTabel("TMBKUSBOP");
        genMapper.insertGetId(id1);
        Integer idMaster = id1.getId();

        // INSERT MASTER
        // create no bku mohon
        final Map genparam = new HashMap();
        genparam.put("tahun", bku.getTahun());
        genparam.put("idsekolah", bku.getIdsekolah());
        genparam.put("triwulan", bku.getTriwulan());
        bku.setNoBkuMohon(bkuMapper.getBkuNoMohon(genparam));
        bku.setNoBKU(bkuMapper.getBkuNo(genparam));
        bku.setId(idMaster);
        koreksiMapper.insertBkuSpjMaster(bku);

        // INSERT RINCI
        final List<BkuRinci> list = bku.getListBkuRinci();
        // create ID BKU RINCI
        GenerateId id2 = new GenerateId();
        Integer idRinci;

        for (BkuRinci bkurinci : list) {
            id2.setNamaTabel("TMBKUSBOPRINCI");
            genMapper.insertGetId(id2);
            idRinci = id2.getId();

            bkurinci.setId(idRinci);
            bkurinci.setIdBku(idMaster);

            koreksiMapper.insertBkuSpjRinci(bkurinci);
        }

    }

    @Override
    public BukuKasUmum getDataEdit(final Map<String, Object> param) {
        return koreksiMapper.getDataEdit(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateKoreksi(BukuKasUmum bku) {

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
                koreksiMapper.updateSpjRinciById(bkurinci);

            } else { // DATA BARU

                id2.setNamaTabel("TMBKUSBOPRINCI");
                genMapper.insertGetId(id2);
                idRinci = id2.getId();
                bkurinci.setIdBku(idMaster);
                bkurinci.setId(idRinci);

                koreksiMapper.insertBkuSpjRinci(bkurinci);

            }
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteBkuById(Map param) {
        bkuMapper.deleteRinciById(param);
        bkuMapper.deleteById(param);

    }

    @Override
    public BukuKasUmum getPaguEdit(final Map<String, Object> param) {
        return koreksiMapper.getPaguEdit(param);
    }

}
