/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.services;

import ebkus.entity.StatusTransferMapper;
import ebkus.model.BkuTransfer;
import ebkus.model.BukuKasUmum;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service("statusTransferBopServices")
public class StatusTransferImpl implements StatusTransferServices {

    private static final Logger log = LoggerFactory.getLogger(SekolahImpl.class);
    @Autowired
    private StatusTransferMapper statusBopMapper;

    @Override
    public List<BukuKasUmum> getListSpjBop(Map param) {
        return statusBopMapper.getListSpjBop(param);
    }

    @Override
    public Integer getBanyakListSpjBop(Map param) {
        return statusBopMapper.getBanyakListSpjBop(param);
    }

    @Override
    public List<BukuKasUmum> getListSpjBos(Map param) {
        return statusBopMapper.getListSpjBos(param);
    }

    @Override
    public Integer getBanyakListSpjBos(Map param) {
        return statusBopMapper.getBanyakListSpjBos(param);
    }

    @Override
    public List<BkuTransfer> getListBank(Map param) {
        return statusBopMapper.getListBank(param);
    }

    @Override
    public Integer getBanyakListBank(Map param) {
        return statusBopMapper.getBanyakListBank(param);
    }

    @Override
    public void updateDataBank(Map param) {
        statusBopMapper.updateDataBank(param);
    }

    @Override
    public void updateDataBkuBop(BukuKasUmum bku) {
        statusBopMapper.updateDataBkuBop(bku);
    }

    @Override
    public void updateDataBkuBos(BukuKasUmum bku) {
        statusBopMapper.updateDataBkuBos(bku);
    }

    @Override
    public Integer getBanyakBankBerhasil(Map param) {
        return statusBopMapper.getBanyakBankBerhasil(param);
    }

}
