/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.services;

import ebkus.entity.ResetPasswordMapper;
import ebkus.model.User;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("resetPasswordServices")
@Transactional(readOnly = true)
public class ResetPasswordImpl implements ResetPasswordServices {

    private static final Logger log = LoggerFactory.getLogger(ResetPasswordImpl.class);
    @Autowired
    private ResetPasswordMapper passwordMapper;

    @Override
    public User getPengguna(Map<String, Object> param) {
        return passwordMapper.getPengguna(param);
    }

    @Override
    public String generatePassword() {
        return passwordMapper.generatePassword();
    }

    @Override
    public void simpan(Map<String, Object> param) {
        passwordMapper.simpan(param);
    }

}
