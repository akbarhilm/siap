/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.services;

import ebkus.entity.StatusBosMapper;
import ebkus.model.StatusBku;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service("statusBosServices")
public class StatusBosImpl implements StatusBosServices {

    private static final Logger log = LoggerFactory.getLogger(SekolahImpl.class);
    @Autowired
    private StatusBosMapper statusBosMapper;

    @Override
    public List<StatusBku> getListBkuBos(Map param) {
        return statusBosMapper.getListBkuBos(param);
    }

    @Override
    public List<StatusBku> getListBosDari(Map param) {
        return statusBosMapper.getListBosDari(param);
    }

    @Override
    public List<Integer> getBanyakBosTotal(Map param) {
        return statusBosMapper.getBanyakBosTotal(param);
    }

    @Override
    public Integer getBanyakBos(Map param) {
        return statusBosMapper.getBanyakBos(param);
    }

    @Override
    public Integer getBanyakBosDari(Map param) {
        return statusBosMapper.getBanyakBosDari(param);
    }

    @Override
    public BigDecimal getTotal(Map param) {
        return statusBosMapper.getTotal(param);
    }

    @Override
    public BigDecimal getTotalDari(Map param) {
        return statusBosMapper.getTotalDari(param);
    }

    @Override
    public void updateStatusBos(Map param) {
        statusBosMapper.updateStatusBos(param);
    }

    @Override
    public List<BigDecimal> getTotalAll(Map param) {
        return statusBosMapper.getTotalAll(param);
    }

    @Override
    public List<StatusBku> getListBosPajak(Map param) {
        return statusBosMapper.getListBosPajak(param);
    }

    @Override
    public List<Integer> getBanyakBosTotalPajak(Map param) {
        return statusBosMapper.getBanyakBosTotalPajak(param);
    }

    @Override
    public Integer getBanyakBosPajak(Map param) {
        return statusBosMapper.getBanyakBosPajak(param);
    }

    @Override
    public BigDecimal getTotalPajak(Map param) {
        return statusBosMapper.getTotalPajak(param);
    }

    @Override
    public void updateStatusBosPajak(Map param) {
        statusBosMapper.updateStatusBosPajak(param);
    }

    @Override
    public List<Integer> getStatusTutupBuku(Map param) {
        return statusBosMapper.getStatusTutupBuku(param);
    }

    @Override
    public List<Integer> getStatusTutupBukuPajak(Map param) {
        return statusBosMapper.getStatusTutupBukuPajak(param);
    }

    @Override
    public void updateStatusBosByNoMohon(Map param) {
        statusBosMapper.updateStatusBosByNoMohon(param);
    }

    @Override
    public Integer validateNilai(Map param) {
        return statusBosMapper.validateNilai(param);
    }
}
