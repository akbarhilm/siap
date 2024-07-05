/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.services;

import ebkus.entity.GenerateIdMapper;
import ebkus.entity.SekolahMapper;
import ebkus.model.GenerateId;
import ebkus.model.MCB;
import ebkus.model.Rkas;
import ebkus.model.Sekolah;
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
@Service("sekolahServices")
public class SekolahImpl implements SekolahServices {

    private static final Logger log = LoggerFactory.getLogger(SekolahImpl.class);
    @Autowired
    private SekolahMapper sekolahMapper;
    
     @Autowired
    private GenerateIdMapper genIdMapper;

    @Override
    public Sekolah getSekolah(Map param) {
        return sekolahMapper.getSekolah(param);
    }

    @Override
    public void updateSekolah1(Sekolah param) {
        sekolahMapper.updateSekolah1(param);
        
    }
    
    @Override
    public void updateSekolahRKAS(Sekolah param) {
        sekolahMapper.updateSekolahRKAS(param);
    }

    @Override
    public void updateSekolah2(Rkas param) {
        sekolahMapper.updateSekolah2(param);
    }
    
    @Override
    public void insertMcb(MCB mcb) {
        GenerateId gen = new GenerateId();
            gen.setNamaTabel("TRPLNMCB");
            genIdMapper.insertGetId(gen);
            mcb.setIdMcb(gen.getId());
        sekolahMapper.insertMcb(mcb);
    }
    
    @Override
    public  List<MCB> getListMcb() {
      return  sekolahMapper.getListMcb();
    }
    
    @Override
    public  Integer getBanyakListMcb() {
      return  sekolahMapper.getBanyakListMcb();
    }
    
    @Override
    public  MCB getMcbById(Integer id) {
      return  sekolahMapper.getMcbById(id);
    }
   
    @Override
    public  void deleteMcb(Integer id) {
        sekolahMapper.deleteMcb(id);
    }
    
    @Override
    public  void updateMcb(MCB mcb) {
        sekolahMapper.updateMcb(mcb);
    }
}
