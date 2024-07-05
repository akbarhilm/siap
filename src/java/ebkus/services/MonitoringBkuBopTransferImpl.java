/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.services;

import ebkus.entity.MonitoringBkuBopTransferMapper;
import ebkus.model.BukuKasUmum;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Racka
 */
@Transactional(readOnly = true)
@Service("monitoringBkuBopTransferServices")
public class MonitoringBkuBopTransferImpl implements MonitoringBkuBopTransferServices {

    //private static final Logger log = LoggerFactory.getLogger(MonitoringBkuBopHarianImpl.class);
    @Autowired
    private MonitoringBkuBopTransferMapper monitoringBkuBopTransferMapper;

    @Override
    public List<BukuKasUmum> getListIndex(final Map<String, Object> param) {
        return monitoringBkuBopTransferMapper.getListIndex(param);
    }

    @Override
    public Integer getBanyakListIndex(final Map<String, Object> param) {
        return monitoringBkuBopTransferMapper.getBanyakListIndex(param);
    }

    @Override
    public BukuKasUmum getNilaiIndex(final Map<String, Object> param) {
        return monitoringBkuBopTransferMapper.getNilaiIndex(param);
    }

    @Override
    public BukuKasUmum getNilaiSaldo(final Map<String, Object> param) {
        return monitoringBkuBopTransferMapper.getNilaiSaldo(param);
    }
}
