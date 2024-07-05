/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.services;

import java.util.List;
import java.util.Map;
import ebkus.entity.ListPenggunaMapper;
import ebkus.model.User;
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
@Service("listPenggunaServices")
public class ListPenggunaImpl implements ListPenggunaServices {

    private static final Logger log = LoggerFactory.getLogger(ListPenggunaImpl.class);
    @Autowired
    private ListPenggunaMapper listMapper;
    
    
    @Override
    public Integer getBanyakPengguna(final Map<String, Object> param) {
        return listMapper.getBanyakPengguna(param);
    }
    
    @Override
    public List<User> getAllPengguna(final Map<String, Object> param) {
        return listMapper.getAllPengguna(param);
    }
    
//     @Override
//    public Sekolah getSekolahById(Integer id) {
//        return journalMapper.getSekolahById(id);
//    }
    
  
    
    
}

  