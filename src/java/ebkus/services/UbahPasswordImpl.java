/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.services;

import ebkus.entity.UbahPasswordMapper;
import ebkus.model.LoginForm;
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
@Service("ubahpassServices")
public class UbahPasswordImpl implements UbahPasswordServices {

    private static final Logger log = LoggerFactory.getLogger(UbahPasswordImpl.class);
    @Autowired
    private UbahPasswordMapper passMapper;

    @Override
    @Transactional(readOnly = false)
    public void updatePass(LoginForm param) {
        passMapper.updatePass(param);
    }

    @Override
    public Integer checkPassword(LoginForm param) {
        return passMapper.checkPassword(param);
    }
}
