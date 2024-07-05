/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.services;

import ebkus.entity.BaBatalMapper;
import ebkus.entity.GenerateIdMapper;
import ebkus.model.BaBatal;
import ebkus.model.GenerateId;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service("baBatalServices")
public class BaBatalServicesImpl implements BaBatalServices {

    private static final Logger log = LoggerFactory.getLogger(BaBatalServicesImpl.class);
    @Autowired
    private BaBatalMapper baBatalMapper;
    @Autowired
    private GenerateIdMapper genMapper;

    @Override
    public BaBatal getBaBatal(Map param) {
        return baBatalMapper.getBaBatal(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertBaBatal(BaBatal batal) {
        // create ID
        GenerateId id = new GenerateId();
        id.setNamaTabel("TMBKUSBABATAL");
        genMapper.insertGetId(id);
        Integer idMaster = id.getId();
        batal.setIdBa(idMaster);

        baBatalMapper.insertBaBatal(batal);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateBaBatal(BaBatal batal) {
        baBatalMapper.updateBaBatal(batal);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteBaBatal(BaBatal batal) {
        baBatalMapper.deleteBaBatal(batal);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteBaBatalByMohon(Map param) {
        baBatalMapper.deleteBaBatalByMohon(param);
    }

    @Override
    public String getFormattedNoBa(BaBatal batal) {
        return baBatalMapper.getFormattedNoBa(batal);
    }

}
