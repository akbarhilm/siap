/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.services;

import ebkus.model.BaBatal;
import java.util.Map;

/**
 *
 * @author Racka
 */
public interface BaBatalServices {

    BaBatal getBaBatal(Map param);

    void insertBaBatal(BaBatal batal);

    void updateBaBatal(BaBatal batal);

    void deleteBaBatal(BaBatal batal);

    void deleteBaBatalByMohon(Map param);

    String getFormattedNoBa(BaBatal batal);

}
