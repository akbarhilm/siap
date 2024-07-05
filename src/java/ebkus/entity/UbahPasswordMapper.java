/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.entity;

import ebkus.model.LoginForm;

/**
 *
 * @author Zainab
 */
public interface UbahPasswordMapper {

    void updatePass(LoginForm param);

    Integer checkPassword(LoginForm param);

}
