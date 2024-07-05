/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.services;

import ebkus.entity.BapKasBopMapper;
import ebkus.entity.GenerateIdMapper;
import ebkus.model.BapKas;
import ebkus.model.BapKasRinci;
import ebkus.model.GenerateId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service("bapKasBopServices")
public class BapKasBopImpl implements BapKasBopServices {

    private static final Logger log = LoggerFactory.getLogger(BapKasBopImpl.class);

    @Autowired
    private BapKasBopMapper bapKasMapper;

    @Autowired
    private GenerateIdMapper genMapper;

    @Override
    public Integer getCountTglBkuProses(Map param) {
        return bapKasMapper.getCountTglBkuProses(param);
    }

    @Override
    public List<BapKas> getBapKas(Map<String, Object> param) {
        return bapKasMapper.getBapKas(param);
    }

    @Override
    public Integer getCountBapKas(Map param) {
        return bapKasMapper.getCountBapKas(param);
    }

    @Override
    public List<Map> getTriwulan(Map param) {
        return bapKasMapper.getTriwulan(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertBapKasAll(BapKas bapKas) {
        GenerateId id1 = new GenerateId();
        id1.setNamaTabel("TMBKUSBOPBA");
        genMapper.insertGetId(id1);
        Integer idMaster = id1.getId();
        bapKas.setIdSekolahBAPKas(idMaster);

        bapKasMapper.insertBapKas(bapKas);

        final List<BapKasRinci> list = bapKas.getBapKasRinci();
        for (BapKasRinci bapKasRinci : list) {
            bapKasRinci.setIdSekolahBAPKas(idMaster);

            // generate id rinci
            GenerateId id2 = new GenerateId();
            id2.setNamaTabel("TMBKUSBOPBARINCI");
            genMapper.insertGetId(id2);
            Integer idRinci = id2.getId();
            bapKasRinci.setIdSekolahBAPKasRinci(idRinci);

            bapKasMapper.insertBapKasRinci(bapKasRinci);
        }
    }

    @Override
    public BapKas getBapKasById(Map param) {
        return bapKasMapper.getBapKasById(param);
    }

    @Override
    public List<BapKas> getAllBAPKAS(Map<String, Object> param) {
        return bapKasMapper.getAllBAPKAS(param);
    }

    @Override
    public Integer getBanyakAllBAPKAS(Map<String, Object> param) {
        return bapKasMapper.getBanyakAllBAPKAS(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateBapKasAll(BapKas bapKas) {
        final Map param = new HashMap();
        bapKasMapper.updateBapKas(bapKas);

        final List<BapKasRinci> list = bapKas.getBapKasRinci();

        for (BapKasRinci bapKasRinci : list) {

            if (bapKasRinci.getIdSekolahBAPKasRinci() != 0) {
                bapKasMapper.updateBapKasRinci(bapKasRinci);
            } else {
                GenerateId id2 = new GenerateId();
                id2.setNamaTabel("TMBKUSBOPBARINCI");
                genMapper.insertGetId(id2);
                Integer idRinci = id2.getId();
                bapKasRinci.setIdSekolahBAPKasRinci(idRinci);
                bapKasRinci.setIdSekolahBAPKas(bapKas.getIdSekolahBAPKas());

                bapKasMapper.insertBapKasRinci(bapKasRinci);
            }
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteBapKas(Map param) {

        bapKasMapper.deleteBapKas(param);
        bapKasMapper.deleteBapKasRinci(param);

    }

    @Override
    public List<Map> getnilaiparam(Map param) {
        return bapKasMapper.getnilaiparam(param);
    }

    @Override
    public List<Integer> getBapTriwulan(Map param) {
        return bapKasMapper.getBapTriwulan(param);
    }

    @Override
    public void updateApproval(Map param) {
        bapKasMapper.updateApproval(param);
    }

    @Override
    public BapKas getBapKasByTriwulan(Map param) {
        return bapKasMapper.getBapKasByTriwulan(param);
    }

    @Override
    public BapKas getNilaiKas(Map param) {
        return bapKasMapper.getNilaiKas(param);
    }

    @Override
    public Integer isTutupBuku(Map param) {
        return bapKasMapper.isTutupBuku(param);
    }

}
