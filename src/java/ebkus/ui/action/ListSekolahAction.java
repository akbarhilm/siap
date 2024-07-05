/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.*/
package ebkus.ui.action;

import ebkus.model.Pengguna;
import ebkus.services.ListSekolahServices;
import ebkus.util.BigDecimalPropertyEditor;
import ebkus.util.SqlDatePropertyEditor;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Zainab
 */
@Controller
@RequestMapping("/sekolahpopup")
public class ListSekolahAction {

    private static final Logger log = LoggerFactory.getLogger(ListSekolahAction.class);

    @Autowired
    ListSekolahServices skpdServices;

    @RequestMapping(value = "/listsekolah", method = RequestMethod.GET)
    public String indexlistskpd(final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return "listsekolah/listsekolah";
    }

    @RequestMapping(value = "/listsekolahpa", method = RequestMethod.GET)
    public String indexlistsekolah(final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return "listsekolah/listsekolahpa";
    }

    @RequestMapping(value = "/listsekolahplt", method = RequestMethod.GET)
    public String indexlistsekolahplt(final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return "listsekolah/listsekolahplt";
    }

    @RequestMapping(value = "/json/listsekolahjson", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listskpdjson(final HttpServletRequest request) {
        Pengguna pgn = (Pengguna) request.getSession().getAttribute("pengguna");

        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String namasekolah = request.getParameter("namasekolah");
        final String npsn = request.getParameter("npsn");
        String idskpd = "";
        log.debug("idskpd " + pgn.getIdSkpd());
        if (pgn.getKodeOtoritas().equals("0") || (pgn.getKodeOtoritas().equals("7") && !(String.valueOf(pgn.getIdSkpd())).equals("0"))) {
            idskpd = String.valueOf(pgn.getIdSkpd());
        }
        param.put("idskpd", idskpd);
        param.put("namasekolah", namasekolah);
        param.put("npsn", npsn);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final long banyak = skpdServices.getBanyakAllSekolah(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", skpdServices.getAllSekolah(param));
        return mapData;
    }

    @RequestMapping(value = "/listsekolahbyidskpd", method = RequestMethod.GET) /* idns , 21-12-2017 */

    public String indexlistsekolahbyidskpd(final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return "listsekolah/listsekolahskpd";
    }

    @RequestMapping(value = "/json/listsekolahbyidskpdjson", method = RequestMethod.GET)    /* idns , 21-12-2017 */

    public @ResponseBody
    Map<String, Object> listsekolahbyidskpdjson(final HttpServletRequest request) {

        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String namasekolah = request.getParameter("namasekolah");
        final String npsn = request.getParameter("npsn");
        final String kodesumbdana = request.getParameter("kodesumbdana");
        Pengguna pgn = (Pengguna) request.getSession().getAttribute("pengguna");
        String kodegrup = pgn.getKodeGrup();
        Integer idskpd = pgn.getIdSkpd();
        /*
        switch (kodesumbdana) {
            case "0":
            case "1":
                if (pgn.getIdSkpd() != 12782) {
                    idskpd = pgn.getIdSkpd();
                }
                break;
            default:
                idskpd = pgn.getIdSkpd();
                break;
        }*/
        param.put("kodegrup", kodegrup);
        param.put("kodesumbdana", kodesumbdana);
        param.put("idskpd", idskpd);
        param.put("namasekolah", namasekolah);
        param.put("npsn", npsn);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final long banyak = skpdServices.getBanyakSekolahByKodeGrup(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", skpdServices.getSekolahByKodeGrup(param));
        return mapData;
    }
    
    @RequestMapping(value = "/json/listsekolahbyidskpdjson_lama", method = RequestMethod.GET)    /* idns , 21-12-2017 */

    public @ResponseBody
    Map<String, Object> listsekolahbyidskpdjson_lama(final HttpServletRequest request) {

        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String namasekolah = request.getParameter("namasekolah");
        final String npsn = request.getParameter("npsn");
        final String kodesumbdana = request.getParameter("kodesumbdana");
        Pengguna pgn = (Pengguna) request.getSession().getAttribute("pengguna");
        Integer idskpd = null;
        switch (kodesumbdana) {
            case "0":
                if (pgn.getIdSkpd() != 12782) {
                    idskpd = pgn.getIdSkpd();
                }
                break;
            case "1":
                if (pgn.getIdSkpd() != 12782) {
                    idskpd = pgn.getIdSkpd();
                }
                break;
            case "2":
                idskpd = pgn.getIdSkpd();
                break;
            default:
                idskpd = pgn.getIdSkpd();
                break;
        }
        param.put("idskpd", idskpd);
        param.put("namasekolah", namasekolah);
        param.put("npsn", npsn);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final long banyak = skpdServices.getBanyakAllSekolah(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", skpdServices.getAllSekolah(param));
        return mapData;
    }

    @RequestMapping(value = "/json/listsekolahplt", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listsekolahplt(final HttpServletRequest request) {

        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String namasekolah = request.getParameter("namasekolah");
        final String npsn = request.getParameter("npsn");
        Pengguna pgn = (Pengguna) request.getSession().getAttribute("pengguna");
        Integer idpengguna = pgn.getIdPengguna();
        Integer idsekolah = pgn.getSekolah().getIdSekolah();
        param.put("idpengguna", idpengguna);
        param.put("idsekolah", idsekolah);
        param.put("namasekolah", namasekolah);
        param.put("npsn", npsn);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final long banyak = skpdServices.getBanyakAllSekolahPlt(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", skpdServices.getAllSekolahPlt(param));
        return mapData;
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }

}
