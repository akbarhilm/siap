/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.*/
package ebkus.ui.action;

import ebkus.model.Pengguna;
import ebkus.services.ListPenggunaServices;
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
@RequestMapping("/penggunapopup")
public class ListPenggunaAction {

    private static final Logger log = LoggerFactory.getLogger(ListPenggunaAction.class);

    @Autowired
    ListPenggunaServices listServices;

    @RequestMapping(value = "/listpengguna", method = RequestMethod.GET)
    public String indexlistskpd(final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return "listpengguna/listpengguna";
    }

    @RequestMapping(value = "/json/listpenggunajson", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listskpdjson(final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String namapengguna = request.getParameter("namapengguna");
        final String nrk = request.getParameter("nrk");
        String idskpd = "";
        if (pengguna.getKodeOtoritas().equals("0")) {
            idskpd = String.valueOf(pengguna.getIdSkpd());
        }
        param.put("idskpd", idskpd);
        param.put("namapengguna", namapengguna);
        param.put("nrk", nrk);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final long banyak = listServices.getBanyakPengguna(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", listServices.getAllPengguna(param));
        return mapData;
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }

}
