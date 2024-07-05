/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.services;

import ebkus.entity.TutupBkuBopMapper;
import ebkus.model.BukuKasUmum;
import ebkus.model.TutupBku;
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
@Service("tutupBkuBopServices")
public class TutupBkuBopImpl implements TutupBkuBopServices {

    private static final Logger log = LoggerFactory.getLogger(TutupBkuBopImpl.class);

    @Autowired
    private TutupBkuBopMapper tutupBkuMapper;

    @Override
    public List<BukuKasUmum> getBulanBKU(Map param) {
        return tutupBkuMapper.getBulanBKU(param);
    }

    @Override
    public Integer getBanyakListBku(Map<String, Object> param) {
        return tutupBkuMapper.getBanyakListBku(param);
    }

    @Override
    public List<BukuKasUmum> getListBku(Map<String, Object> param) {
        return tutupBkuMapper.getListBku(param);
    }

    @Override
    public TutupBku getBendahara(Map<String, Object> param) {
        return tutupBkuMapper.getBendahara(param);
    }

    @Override
    public TutupBku getNilaiKas(Map<String, Object> param) {
        return tutupBkuMapper.getNilaiKas(param);
    }

    @Override
    public TutupBku getNilaiSaldo(Map<String, Object> param) {
        return tutupBkuMapper.getNilaiSaldo(param);
    }

    @Override
    public Integer getBanyakDataBku(TutupBku tutupbku) {
        return tutupBkuMapper.getBanyakDataBku(tutupbku);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertTutupBku(TutupBku tutupbku, String kodetomboltutupbuku) {
        tutupBkuMapper.deleteTutupBku(tutupbku);
        tutupBkuMapper.insertTutupBku(tutupbku);
        //journalMapper.insertBKUPengeluaran(param);
        log.debug("kode tombol == " + kodetomboltutupbuku);
        if (kodetomboltutupbuku.equals("2")) {
            tutupBkuMapper.updateBkuPengeluaran(tutupbku);
            log.debug("masukkan kesini? == " + kodetomboltutupbuku);
        }
        //endif
        tutupBkuMapper.insertBKUPengeluaran(tutupbku);
        //System.out.println("tes");
        //log.debug("tanggalparam ===== " + tutupbku.getTglPenutupan());
    }

    @Override
    public Integer getBanyakListXlsBku(Map<String, Object> param) {
        return tutupBkuMapper.getBanyakListXlsBku(param);
    }

    @Override
    public List<Map> getListXlsBku(Map<String, Object> param) {
        return tutupBkuMapper.getListXlsBku(param);
    }

    @Override
    public List<BukuKasUmum> getTriwulanBKU(Map<String, Object> param) {
        return tutupBkuMapper.getTriwulanBKU(param);
    }

    @Override
    public TutupBku getBapKasValidasi(Map<String, Object> param) {
        return tutupBkuMapper.getBapKasValidasi(param);
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer validatePajak(Map param) {
        return tutupBkuMapper.validatePajak(param);
    }

}
