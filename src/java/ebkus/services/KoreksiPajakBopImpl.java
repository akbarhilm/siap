/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.services;

import ebkus.entity.GenerateIdMapper;
import ebkus.entity.KoreksiPajakBopMapper;
import ebkus.entity.BkuBopMapper;
import ebkus.model.BkuRinci;
import ebkus.model.BukuKasUmum;
import ebkus.model.GenerateId;
import ebkus.util.SipkdHelpers;
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
@Service("koreksiPajakBopServices")
public class KoreksiPajakBopImpl implements KoreksiPajakBopServices {

    private static final Logger log = LoggerFactory.getLogger(KoreksiPajakBopImpl.class);
    @Autowired
    private KoreksiPajakBopMapper bkuMapper;

    @Autowired
    private GenerateIdMapper genMapper;

    @Autowired
    private BkuBopMapper bkuBopMapper;

    @Override
    public List<BukuKasUmum> getListKoreksi(final Map<String, Object> param) {
        return bkuMapper.getListKoreksi(param);
    }

    @Override
    public Integer getBanyakListKoreksi(final Map<String, Object> param) {
        return bkuMapper.getBanyakListKoreksi(param);
    }

    @Override
    public List<BukuKasUmum> getListPjKoreksi(final Map<String, Object> param) {
        return bkuMapper.getListPjKoreksi(param);
    }

    @Override
    public Integer getBanyakListPjKoreksi(final Map<String, Object> param) {
        return bkuMapper.getBanyakListPjKoreksi(param);
    }

    @Override
    public BukuKasUmum getDataPajakPn(final Map<String, Object> param) {
        return bkuMapper.getDataPajakPn(param);
    }

    @Override
    public List<BkuRinci> getListKoreksiPjPn(final Map<String, Object> param) {
        return bkuMapper.getListKoreksiPjPn(param);
    }

    @Override
    public Integer getBanyakListKoreksiPjPn(final Map<String, Object> param) {
        return bkuMapper.getBanyakListKoreksiPjPn(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertKoreksi(BukuKasUmum bku) {

        bkuMapper.updatePajakKoreksi(bku);

        // create ID BKU MASTER SPJ
        GenerateId id1 = new GenerateId();
        id1.setNamaTabel("TMBKUSBOP");
        genMapper.insertGetId(id1);
        Integer idMaster = id1.getId();

        // INSERT MASTER
        // create no bku mohon
        final Map genparam = new HashMap();
        genparam.put("tahun", bku.getTahun());
        genparam.put("idsekolah", bku.getIdsekolah());

        bku.setNoBkuMohon(bkuBopMapper.getBkuNoMohon(genparam));
        bku.setNoBKU(bkuBopMapper.getBkuNo(genparam));

        bku.setId(idMaster);
        bkuMapper.insertBkuSpjMaster(bku);

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

            bkuMapper.insertBkuSpjRinci(bkurinci);
        }

        insertKoreksiPajak(bku);

    }

    @Override
    @Transactional(readOnly = false)
    public void insertKoreksiPajak(BukuKasUmum bku) {
        // create ID BKU MASTER PAJAK
        GenerateId id1 = new GenerateId();
        id1.setNamaTabel("TMBKUSBOP");
        genMapper.insertGetId(id1);
        Integer idMaster = id1.getId();

        // INSERT MASTER
        // create no bku mohon
        final Map genparam = new HashMap();
        genparam.put("tahun", bku.getTahun());
        genparam.put("idsekolah", bku.getIdsekolah());

        bku.setNoBkuMohon(bkuBopMapper.getBkuNoMohon(genparam));
        bku.setNoBKU(bkuBopMapper.getBkuNo(genparam));

        bku.setId(idMaster);
        bkuMapper.insertBkuPjMaster(bku);

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

            bkuMapper.insertBkuPjRinci(bkurinci);
        }

    }

    @Override
    @Transactional(readOnly = false)
    public void deleteKoreksi(BukuKasUmum bku) {
        bkuMapper.deleteKoreksi(bku);
    }
}
