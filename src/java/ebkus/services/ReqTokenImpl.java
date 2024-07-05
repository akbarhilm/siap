/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.services;

import ebkus.entity.ReqTokenMapper;
import ebkus.model.Token;
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
@Service("reqTokenServices")
public class ReqTokenImpl implements ReqTokenServices {

    private static final Logger log = LoggerFactory.getLogger(ReqTokenImpl.class);
    @Autowired
    private ReqTokenMapper tknMapper;

    @Override
    @Transactional(readOnly = false)
    public void insertToken(Token tkn) {
        tknMapper.insertToken(tkn);
    }

    @Override
    public List<Token> getToken(Map param) {
        if (param.get("kodeSumbdana").equals("1")) {
            return tknMapper.getTokenBos(param);
        } else {
            return tknMapper.getTokenBop(param);
        }
    }

    @Override
    public void deleteToken(Map param) {
        tknMapper.deleteToken(param);
    }

    @Override
    public void deleteTokenMohon(Map param) {
        tknMapper.deleteTokenMohon(param);
    }

    @Override
    public void deleteTokenSemua(Map param) {
        tknMapper.deleteTokenSemua(param);
    }

    @Override
    public Token getTokenByBkuNo(Map param) {
        return tknMapper.getTokenByBkuNo(param);
    }

    @Override
    public Integer getBanyakToken(Map param) {
        if (param.get("kodeSumbdana").equals("1")) {
            return tknMapper.getBanyakTokenBos(param);
        } else {
            return tknMapper.getBanyakTokenBop(param);
        }
    }

    @Override
    public Token getDate() {
        return tknMapper.getDate();
    }

    @Override
    public Token getTimeLimitBop(Map param) {
        return tknMapper.getTimeLimitBop(param);
    }

    @Override
    public Token getTimeLimitBos(Map param) {
        return tknMapper.getTimeLimitBos(param);
    }

    @Override
    public Token getTimeLimitOn() {
        return tknMapper.getTimeLimitOn();
    }
}
