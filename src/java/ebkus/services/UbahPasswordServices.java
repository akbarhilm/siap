package ebkus.services;

import ebkus.model.LoginForm;

/**
 *
 * @author Zainab
 */
public interface UbahPasswordServices {

    void updatePass(LoginForm param);

    Integer checkPassword(LoginForm param);

}
