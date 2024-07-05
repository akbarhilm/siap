package ebkus.services;

import ebkus.entity.BatalTutupBkuBosMapper;
import ebkus.model.BukuKasUmum;
import ebkus.model.BatalTutupBku;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Anita
 */
@Transactional(readOnly = true)
@Service("batalTutupBkuBosServices")
public class BatalTutupBkuBosImpl implements BatalTutupBkuBosServices {

    private static final Logger log = LoggerFactory.getLogger(BatalTutupBkuBosImpl.class);

    @Autowired
    private BatalTutupBkuBosMapper tutupBkuMapper;

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
    public BatalTutupBku getBendahara(Map<String, Object> param) {
        return tutupBkuMapper.getBendahara(param);
    }

    @Override
    public BatalTutupBku getNilaiKas(Map<String, Object> param) {
        return tutupBkuMapper.getNilaiKas(param);
    }

    @Override
    public BatalTutupBku getNilaiSaldo(Map<String, Object> param) {
        return tutupBkuMapper.getNilaiSaldo(param);
    }

    @Override
    public Integer getBanyakDataBku(BatalTutupBku tutupbku) {
        return tutupBkuMapper.getBanyakDataBku(tutupbku);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertTutupBku(BatalTutupBku batalTutupBku, String kodetomboltutupbuku) {
        tutupBkuMapper.deleteTutupBku(batalTutupBku); //delete TMBKUSBOPREKAP
        //tutupBkuMapper.insertTutupBku(batalTutupBku); //insert TMBKUSBOPREKAP 
        log.debug("anitaikan kode tombol == " + kodetomboltutupbuku);
        if (kodetomboltutupbuku.equals("2")) {
            tutupBkuMapper.updateBkuPengeluaran(batalTutupBku); //update TMBKUSBOP
            log.debug("anitaikan masukkan kesini? == " + kodetomboltutupbuku);
        } 
        //tutupBkuMapper.insertBKUPengeluaran(batalTutupBku); //CALL proc_bku_ins_tibkusboptw -- insert ke tibkusboptw 
        
        tutupBkuMapper.deleteTutupBkuTI(batalTutupBku); //delete TMBKUSBOPREKAP
        
        //seharusnya: delete tibkusboptw, delete TMBKUSBOPREKAP, update TMBKUSBOP
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

}
