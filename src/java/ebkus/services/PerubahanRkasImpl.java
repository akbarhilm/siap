/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.services;

import ebkus.entity.PerubahanRkasMapper;
import ebkus.model.BkuRinci;
import ebkus.model.Kegiatan;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("perubahanRkasServices")
@Transactional(readOnly = true)

public class PerubahanRkasImpl implements PerubahanRkasServices {

    @Autowired
    private PerubahanRkasMapper perubahanRkasMapper;

    @Override
    public List<BkuRinci> getListAkun(Map param) {
        return perubahanRkasMapper.getListAkun(param);
    }

    @Override
    public List<BkuRinci> getListKomponen(Map param) {
        return perubahanRkasMapper.getListKomponen(param);
    }

    @Override
    public Integer getBanyakListKomponen(Map param) {
        return perubahanRkasMapper.getBanyakListKomponen(param);
    }

    @Override
    public void getUpdateStatusKomponen(Map param) {
        perubahanRkasMapper.getUpdateStatusKomponen(param);
    }

    @Override
    public List<Kegiatan> getListKegiatan(Map param) {
        return perubahanRkasMapper.getListKegiatan(param);
    }

}
