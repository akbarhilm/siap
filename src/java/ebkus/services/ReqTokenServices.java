package ebkus.services;

import ebkus.model.Token;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Zainab
 */
public interface ReqTokenServices {

    Token getTimeLimitOn();

    void insertToken(Token tkn);

    List<Token> getToken(Map param);

    Integer getBanyakToken(Map param);

    Token getTokenByBkuNo(Map param);

    void deleteToken(Map param);

    void deleteTokenMohon(Map param);

    void deleteTokenSemua(Map param);

    Token getDate();

    Token getTimeLimitBop(Map param);

    Token getTimeLimitBos(Map param);
}
