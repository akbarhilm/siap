/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.services;

import ebkus.model.User;
import java.util.Map;

/**
 *
 * @author Racka
 */
public interface ResetPasswordServices {

    User getPengguna(Map<String, Object> param);

    String generatePassword();

    void simpan(Map<String, Object> param);
}
