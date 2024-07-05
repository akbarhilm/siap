/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.services;

import ebkus.entity.StatusBopMapper;
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
@Service("statusBopServices")
public class StatusBopImpl implements StatusBopServices {

    private static final Logger log = LoggerFactory.getLogger(SekolahImpl.class);
    @Autowired
    private StatusBopMapper statusBopMapper;

    @Override
    public List<StatusBku> getListBkuBop(Map param) {
        return statusBopMapper.getListBkuBop(param);
    }

    @Override
    public List<StatusBku> getListBopDari(Map param) {
        return statusBopMapper.getListBopDari(param);
    }

    @Override
    public List<Integer> getBanyakBopTotal(Map param) {
        return statusBopMapper.getBanyakBopTotal(param);
    }

    @Override
    public Integer getBanyakBop(Map param) {
        return statusBopMapper.getBanyakBop(param);
    }

    @Override
    public Integer getBanyakBopDari(Map param) {
        return statusBopMapper.getBanyakBopDari(param);
    }

    @Override
    public BigDecimal getTotal(Map param) {
        return statusBopMapper.getTotal(param);
    }

    @Override
    public BigDecimal getTotalDari(Map param) {
        return statusBopMapper.getTotalDari(param);
    }

    @Override
    public void updateStatusBop(Map param) {
        statusBopMapper.updateStatusBop(param);
    }

    @Override
    public List<BigDecimal> getTotalAll(Map param) {
        return statusBopMapper.getTotalAll(param);
    }

    @Override
    public List<StatusBku> getListBopPajak(Map param) {
        return statusBopMapper.getListBopPajak(param);
    }

    @Override
    public List<Integer> getBanyakBopTotalPajak(Map param) {
        return statusBopMapper.getBanyakBopTotalPajak(param);
    }

    @Override
    public Integer getBanyakBopPajak(Map param) {
        return statusBopMapper.getBanyakBopPajak(param);
    }

    @Override
    public BigDecimal getTotalPajak(Map param) {
        return statusBopMapper.getTotalPajak(param);
    }

    @Override
    public void updateStatusBopPajak(Map param) {
        statusBopMapper.updateStatusBopPajak(param);
    }

    @Override
    public List<Integer> getStatusTutupBuku(Map param) {
        return statusBopMapper.getStatusTutupBuku(param);
    }

    @Override
    public List<Integer> getStatusTutupBukuPajak(Map param) {
        return statusBopMapper.getStatusTutupBukuPajak(param);
    }

    @Override
    public void updateStatusBopByNoMohon(Map param) {
        statusBopMapper.updateStatusBopByNoMohon(param);
    }

    @Override
    public void deleteSisa(Map param) {
        List<StatusBku> listbku = statusBopMapper.getListSisa(param);
        for (StatusBku bku : listbku) {
            statusBopMapper.deleteRinciSisa(bku.getId());
        }
        statusBopMapper.deleteSisa(param);
    }

    @Override
    public Integer validateNilai(Map param) {
        return statusBopMapper.validateNilai(param);
    }
}
