/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.services;

import ebkus.entity.BatalTransferBosMapper;
import ebkus.model.BukuKasUmum;
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
@Service("batalTfBosServices")
public class BatalTransferBosImpl implements BatalTransferBosServices {

    private static final Logger log = LoggerFactory.getLogger(BatalTransferBosImpl.class);
    @Autowired
    private BatalTransferBosMapper bkuMapper;

    @Override
    public List<BukuKasUmum> getListPembatalan(Map<String, Object> param) {
        if (param.get("kodetransaksi").equals("JJ") || param.get("kodetransaksi").equals("ST") || param.get("kodetransaksi").equals("RT")) {
            return bkuMapper.getListPembatalan(param);
        } else {
            return bkuMapper.getListPembatalanPajak(param);
        }
    }

    @Override
    public Integer getBanyakListPembatalan(Map<String, Object> param) {
        if (param.get("kodetransaksi").equals("JJ") || param.get("kodetransaksi").equals("ST") || param.get("kodetransaksi").equals("RT")) {
            return bkuMapper.getBanyakListPembatalan(param);
        } else {
            return bkuMapper.getBanyakListPembatalanPajak(param);
        }
    }

    @Override
    public BukuKasUmum getTotalNilai(Map<String, Object> param) {
        if (param.get("kodetransaksi").equals("JJ") || param.get("kodetransaksi").equals("ST") || param.get("kodetransaksi").equals("RT")) {
            return bkuMapper.getTotalNilai(param);
        } else {
            return bkuMapper.getTotalNilaiPajak(param);
        }
    }

    @Override
    public void pengajuanBatal(Map<String, Object> param) {
        bkuMapper.pengajuanBatal(param);
    }

    @Override
    public void prosesPembatalan(Map<String, Object> param) {
        bkuMapper.prosesPembatalanPajak(param);
        if (!param.containsKey("pajakPengeluaran")) {
            bkuMapper.prosesPembatalan(param);
            bkuMapper.prosesPembatalanBank(param);
        }
        if (param.get("kodeotor").equals("8") || param.get("kodeotor").equals("0")) {
            approveBa(param);
        }
    }

    @Override
    public Integer getPajakPengeluaran(Map<String, Object> param) {
        return bkuMapper.getPajakPengeluaran(param);
    }

    @Override
    public void approveBa(Map param) {
        bkuMapper.approveBa(param);
    }

}
