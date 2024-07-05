/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.services;

import ebkus.entity.KegiatanMapper;
import ebkus.model.Kegiatan;
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
@Service("kegiatanServices")
public class KegiatanImpl implements KegiatanServices {

    private static final Logger log = LoggerFactory.getLogger(KegiatanImpl.class);
    @Autowired
    private KegiatanMapper kegMapper;

    @Override
    public List<Kegiatan> getKegiatanSekolah(final Map<String, Object> param) {
        return kegMapper.getKegiatanSekolah(param);
    }

    @Override
    public Integer getBanyakKegiatanSekolah(final Map<String, Object> param) {
        return kegMapper.getBanyakKegiatanSekolah(param);
    }

    @Override
    public List<Kegiatan> getKegBOPperTW(final Map<String, Object> param) {
        return kegMapper.getKegBOPperTW(param);
    }

    @Override
    public Integer getBanyakKegBOPperTW(final Map<String, Object> param) {
        return kegMapper.getBanyakKegBOPperTW(param);
    }

    @Override
    public List<Kegiatan> getKegBOSperTW(final Map<String, Object> param) {
        return kegMapper.getKegBOSperTW(param);
    }

    @Override
    public Integer getBanyakKegBOSperTW(final Map<String, Object> param) {
        return kegMapper.getBanyakKegBOSperTW(param);
    }

}
