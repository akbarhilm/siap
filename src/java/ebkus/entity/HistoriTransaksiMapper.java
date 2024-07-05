/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.entity;

import ebkus.model.HistoriTransaksi;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Zainab
 */
public interface HistoriTransaksiMapper {

    List<HistoriTransaksi> getTransaksi(Map<String, Object> param);

    List<HistoriTransaksi> getTransaksiall(Map<String, Object> param);

    List<Map> getSaldoAkhir(Map<String, Object> param);

    List<Map> getSaldoAkhirall(Map<String, Object> param);

    void getMutasiAll(Map<String, Object> param);

    Integer getBanyakTransaksi(Map<String, Object> param);

    Integer getBanyakTransaksiall(Map<String, Object> param);

    void insertTransaksi(HistoriTransaksi ht);

    public void deleteTransaksi(Map<String, Object> param);

}
