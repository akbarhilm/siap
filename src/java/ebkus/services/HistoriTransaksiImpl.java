package ebkus.services;

import ebkus.entity.HistoriTransaksiMapper;
import ebkus.entity.ReqTokenMapper;
import ebkus.model.HistoriTransaksi;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author zaenab
 */
@Service("historiTransaksiServices")
@Transactional(readOnly = true)
public class HistoriTransaksiImpl implements HistoriTransaksiServices {

    private static final Logger log = LoggerFactory.getLogger(HistoriTransaksiImpl.class);

    @Autowired
    private HistoriTransaksiMapper histrxMapper;

    @Autowired
    private ReqTokenMapper reqTokenMapper;

    @Override
    public List<HistoriTransaksi> getTransaksi(Map<String, Object> param) {
        return histrxMapper.getTransaksi(param);
    }

    @Override
    public List<HistoriTransaksi> getTransaksiall(Map<String, Object> param) {
        return histrxMapper.getTransaksiall(param);
    }

    @Override
    public List<Map> getSaldoAkhir(Map<String, Object> param) {
        return histrxMapper.getSaldoAkhir(param);
    }

    @Override
    public List<Map> getSaldoAkhirall(Map<String, Object> param) {
        return histrxMapper.getSaldoAkhirall(param);
    }

    @Override
    public void getMutasiAll(Map<String, Object> param) {
        histrxMapper.getMutasiAll(param);
    }

    @Override
    public Integer getBanyakTransaksi(Map<String, Object> param) {
        return histrxMapper.getBanyakTransaksi(param);
    }

    @Override
    public Integer getBanyakTransaksiall(Map<String, Object> param) {
        return histrxMapper.getBanyakTransaksiall(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertTransaksi(JSONObject json, String account) {
        int total = json.getJSONArray("mutasiList").length();
        int success = 0;
        int error = 0;
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("account", account);
        histrxMapper.deleteTransaksi(param);
        JSONArray dataArray = json.getJSONArray("mutasiList");
        Iterator<String> nameItr;
        Object data = null;
        for (int i = 0; i < dataArray.length(); i++) {
            log.debug("ISI KE " + i + " ADALAH : " + ((JSONObject) dataArray.get(i)).toString());
            HistoriTransaksi ht = new HistoriTransaksi();
            ht.setGenerateTime(reqTokenMapper.getDate().getdMohon());
            ht.setRekening(account);
            ht.setKeteranganBank(json.getString("rc"));
            ht.setSaldo(json.getBigDecimal("balance"));
            nameItr = ((JSONObject) dataArray.get(i)).keys();
            while (nameItr.hasNext()) {
                String name = nameItr.next();
                log.debug("INI NAME " + name);
                if (((JSONObject) dataArray.get(i)).get(name).equals(null)) {
                    data = '-';
                } else {
                    data = ((JSONObject) dataArray.get(i)).getString(name);
                }
                switch (name) {
                    case "tanggaltrx":
                        ht.setTglTrx((String) data);
                        break;
                    case "jamtrx":
                        ht.setJamTrx((String) data);
                        break;
                    case "description":
                        ht.setKeterangan((String) data);
                        break;
                    case "nominal":
                        ht.setNilai(new BigDecimal((String) data));
                        break;
                    case "jurnal":
                        ht.setJurnal((String) data);
                        break;
                }
                log.debug("HT KE " + i + " ADALAH : " + ht.toString());

            }
            histrxMapper.insertTransaksi(ht);
        }
    }

    @Override
    public void deleteTransaksi(Map<String, Object> param) {
        histrxMapper.deleteTransaksi(param);
    }

}
