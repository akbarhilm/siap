/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.services;

import ebkus.model.BukuKasUmum;
import ebkus.model.Setor;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 *
 * @author M.Mustakim
 */
public interface SetorServices {

    public Integer getNomorSetoran(Map<String, Object> param);

    public Setor getNilaiST(Map<String, Object> param);

    public long getBanyakListBkuJgPn(Map<String, Object> param);

    public List<BukuKasUmum> getListBkuJgPn(Map<String, Object> param);

    public void insertSetor(Setor setor);

    public long getBanyakListIndexSetor(Map<String, Object> param);

    public List<Setor> getListIndexSetor(Map<String, Object> param);

    public Setor getSetorById(Integer idsetor);

    public void updateSetor(Setor setor);

    public void deleteSetor(Setor setor);

    public long getBanyakListCetakSetor(Map<String, Object> param);

    public List<Setor> getListCetakSetor(Map<String, Object> param);

    public void insertSetorCetak(List<Map<String, Object>> mapdata);

    public void deleteSetorCetak(Integer integer);

    public Integer getBanyakListSetorApprove(Map<String, Object> param);

    public List<Setor> getListSetorApprove(Map<String, Object> param);

    public void updateSetorApprove(List<Map<String, Object>> mapdata);

    public void deleteSetorApprove(Integer integer);

    public Integer getBanyakListRinciReal(Map<String, Object> param);

    public List<Setor> getListRinciReal(Map<String, Object> param);

    public Integer getBanyakListBkuRtPn(Map<String, Object> param);

    public List<BukuKasUmum> getListBkuRtPn(Map<String, Object> param);

}
