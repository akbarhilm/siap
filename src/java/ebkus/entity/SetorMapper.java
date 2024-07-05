/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.entity;

import ebkus.model.BukuKasUmum;
import ebkus.model.Setor;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 *
 * @author M.Mustakim
 */
public interface SetorMapper {

    public Integer getNomorSetoran(Map<String, Object> param);

    public Setor getNilaiSTBop(Map<String, Object> param);

    public BigDecimal getNilaiSTBos(Map<String, Object> param);

    public Integer getBanyakListBkuJgPnBop(Map<String, Object> param);

    public List<BukuKasUmum> getListBkuJgPnBop(Map<String, Object> param);

    public Integer getBanyakListBkuJgPnBos(Map<String, Object> param);

    public List<BukuKasUmum> getListBkuJgPnBos(Map<String, Object> param);

    public void insertSetor(Setor setor);

    public Integer getBanyakListIndexSetor(Map<String, Object> param);

    public List<Setor> getListIndexSetor(Map<String, Object> param);

    public Setor getSetorById(Integer idsetor);

    public void updateSetor(Setor setor);

    public void deleteSetor(Setor setor);

    public Integer getBanyakListCetakSetor(Map<String, Object> param);

    public List<Setor> getListCetakSetor(Map<String, Object> param);

    public void insertSetorCetak(Map<String, Object> mapdetil);

    public void deleteSetorCetak(Integer idsetor);

    public Integer getBanyakListSetorApprove(Map<String, Object> param);

    public List<Setor> getListSetorApprove(Map<String, Object> param);

    public void updateSetorApprove(Map<String, Object> mapdetil);

    public void deleteSetorApprove(Integer idsetor);

    public Integer getBanyakListRinciReal(Map<String, Object> param);

    public List<Setor> getListRinciReal(Map<String, Object> param);

    public Integer getBanyakListBkuRtPnBos(Map<String, Object> param);

    public Integer getBanyakListBkuRtPnBop(Map<String, Object> param);

    public List<BukuKasUmum> getListBkuRtPnBos(Map<String, Object> param);

    public List<BukuKasUmum> getListBkuRtPnBop(Map<String, Object> param);
}
