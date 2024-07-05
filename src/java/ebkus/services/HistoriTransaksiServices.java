/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.services;

import ebkus.model.HistoriTransaksi;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/**
 *
 * @author zaenab
 */
public interface HistoriTransaksiServices {

    List<HistoriTransaksi> getTransaksi(Map<String, Object> param);

    List<HistoriTransaksi> getTransaksiall(Map<String, Object> param);

    List<Map> getSaldoAkhir(Map<String, Object> param);

    List<Map> getSaldoAkhirall(Map<String, Object> param);

    void insertTransaksi(JSONObject json, String account);

    void getMutasiAll(Map<String, Object> param);

    Integer getBanyakTransaksi(Map<String, Object> param);

    Integer getBanyakTransaksiall(Map<String, Object> param);

    void deleteTransaksi(Map<String, Object> param);

}
