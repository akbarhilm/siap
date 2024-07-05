/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.entity;

import ebkus.model.GenerateId;

/**
 *
 * @author Zainab
 */
public interface GenerateIdMapper {

    void insertGetId(GenerateId param);

    void insertGetIdChar(GenerateId param);

}
