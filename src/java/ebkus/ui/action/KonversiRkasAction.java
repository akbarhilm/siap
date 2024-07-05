/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.ui.action;

import ebkus.model.Konversi;
import ebkus.model.Pengguna;
import ebkus.services.KonversiRkasServices;
import ebkus.util.BigDecimalPropertyEditor;
import ebkus.util.SqlDatePropertyEditor;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Racka
 */
@Controller
@RequestMapping("/bku/indexkonversirkas")
public class KonversiRkasAction {

    private static final Logger log = LoggerFactory.getLogger(KonversiRkasAction.class);
    @Autowired
    KonversiRkasServices konversiRkasServices;
    @Autowired
    ServletContext servletContext;
    @Autowired
    DataSource dataSource;

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "/konversi/konversiperubahan";

    }

    @RequestMapping(value = "/json/getBudget", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getBudget(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String kode = request.getParameter("kode");
        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        switch (kode) {
            case "kgt":
                log.debug("Masuk Kegiatan");
                mapData.put("aData", konversiRkasServices.getBudgetKegiatan(param));
                break;
            case "kgtk":
                log.debug("Masuk Kegiatan Rinci");
                mapData.put("aData", konversiRkasServices.getBudgetKegiatanRinci(param));
                break;
            case "kgta":
                log.debug("Masuk Kegiatan AKB");
                mapData.put("aData", konversiRkasServices.getBudgetKegiatanAkb(param));
                break;
            case "dsa":
                log.debug("Masuk Data Sekolah Anggaran");
                mapData.put("aData", konversiRkasServices.getBudgetDataSekolah(param));
                break;
        }
        return mapData;
    }

    @RequestMapping(value = "/json/cekDuplicate", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> cekDuplicate(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        mapData.put("aData", konversiRkasServices.cekDuplicate(param));

        return mapData;
    }

    @RequestMapping(value = "/json/cekGiat", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> cekGiat(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        mapData.put("aData", konversiRkasServices.cekGiat(param));

        return mapData;
    }

    @RequestMapping(value = "/json/cekGiatRinci", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> cekGiatRinci(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        mapData.put("aData", konversiRkasServices.cekGiatRinci(param));

        return mapData;
    }

    @RequestMapping(value = "/json/prosesKonversi", method = RequestMethod.POST)
    public @ResponseBody
    void prosesKonversi(@RequestBody Map<String, String> mapdata, final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        log.debug("Tahun ===== " + tahunAnggaran);
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        Konversi konversi = new Konversi();
        konversi.setTahun(tahunAnggaran);
        konversi.setIdEntry(pengguna.getIdPengguna());
        log.debug("Id Pgun ===== " + pengguna.getIdPengguna());

        final String kode = mapdata.get("kode");
        log.debug("kode proses" + kode);
        switch (kode) {
            case "kgt":
                log.debug("Masuk Kegiatan 2");
                konversiRkasServices.prosesKegiatan(konversi);
                break;
            case "kgtk":
                konversiRkasServices.prosesKegiatanRinci(konversi);
                break;
            case "kgta":
                konversiRkasServices.prosesKegiatanAkb(konversi);
                break;
            case "dsa":
                konversiRkasServices.prosesDataSekolah(konversi);
                break;
        }

    }

    @RequestMapping(value = "/json/prosesKonversiPerubahan", method = RequestMethod.POST)
    public @ResponseBody
    void prosesKonversiPerubahan(@RequestBody Map<String, String> mapdata, final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        log.debug("Tahun ===== " + tahunAnggaran);
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        Konversi konversi = new Konversi();
        konversi.setTahun(tahunAnggaran);
        konversi.setIdEntry(pengguna.getIdPengguna());
        log.debug("Id Pgun ===== " + pengguna.getIdPengguna());

        final String kode = mapdata.get("kode");
        log.debug("kode proses" + kode);
        switch (kode) {
            case "kgt":
                log.debug("Masuk Kegiatan 2");
                konversiRkasServices.prosesKegiatanPerubahan(konversi);
                break;
            case "kgtk":
                konversiRkasServices.prosesKegiatanRinciPerubahan(konversi);
                break;
            case "kgta":
                konversiRkasServices.prosesKegiatanAkbPerubahan(konversi);
                break;
            case "dsa":
                konversiRkasServices.prosesDataSekolahPerubahan(konversi);
                break;
        }

    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }

}
