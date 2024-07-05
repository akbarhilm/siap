/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.entity;

import ebkus.model.Token;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Zainab
 */
public interface ReqTokenMapper {

    Token getTimeLimitBop(Map param);

    Token getTimeLimitBos(Map param);

    Token getTimeLimitOn();

    void insertToken(Token tkn);

    List<Token> getTokenBop(Map param);

    List<Token> getTokenBos(Map param);

    Token getTokenByBkuNo(Map param);

    Integer getBanyakTokenBop(Map param);

    Integer getBanyakTokenBos(Map param);

    void deleteToken(Map param);

    void deleteTokenMohon(Map param);

    void deleteTokenSemua(Map param);

    Token getDate();
}
