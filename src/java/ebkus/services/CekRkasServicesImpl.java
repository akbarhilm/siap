/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.services;

import ebkus.entity.CekRkasMapper;
import ebkus.model.CekDuplikat;
import ebkus.model.CekGiatAkb;
import ebkus.model.CekGiatRinci;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service("CekRkasServices")
public class CekRkasServicesImpl implements CekRkasServices {

    private static final Logger log = LoggerFactory.getLogger(KegiatanImpl.class);
    @Autowired
    private CekRkasMapper cekRkasMapper;

    @Override
    public List<CekDuplikat> getDuplikat(Map<String, Object> param) {
        return cekRkasMapper.getDuplikat(param);
    }

    @Override
    public List<CekGiatRinci> getGiatRinci(Map<String, Object> param) {
        return cekRkasMapper.getGiatRinci(param);
    }

    @Override
    public List<CekGiatAkb> getGiatAkb(Map<String, Object> param) {
        return cekRkasMapper.getGiatAkb(param);
    }

    @Override
    public Integer getBanyakDuplikat(Map<String, Object> param) {
        return cekRkasMapper.getBanyakDuplikat(param);
    }

    @Override
    public Integer getBanyakGiatRinci(Map<String, Object> param) {
        return cekRkasMapper.getBanyakGiatRinci(param);
    }

    @Override
    public Integer getBanyakGiatAkb(Map<String, Object> param) {
        return cekRkasMapper.getBanyakGiatAkb(param);
    }

}
