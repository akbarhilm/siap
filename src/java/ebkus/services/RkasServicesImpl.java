/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.services;

import ebkus.entity.RkasMapper;
import ebkus.model.Rkas;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service("rkasServices")
public class RkasServicesImpl implements RkasServices {

    private static final Logger log = LoggerFactory.getLogger(SekolahImpl.class);
    @Autowired
    private RkasMapper rkasMapper;

    @Override
    public List<Rkas> getListRkas(Map param) {
        return rkasMapper.getListRkas(param);
    }

    @Override
    public Rkas getRkas(Map param) {
        return rkasMapper.getRkas(param);
    }

    @Override
    public void updateRkas(Rkas param) {
        rkasMapper.updateRkas(param);
    }

    @Override
    public Integer getBanyakRkas(Map param) {
        return rkasMapper.getBanyakRkas(param);
    }

}
