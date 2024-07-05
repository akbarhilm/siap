/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.ui.action;

import ebkus.model.Cutoff;
import ebkus.model.Pengguna;
import ebkus.services.CutoffServices;
import ebkus.util.BigDecimalPropertyEditor;
import ebkus.util.SqlDatePropertyEditor;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Racka
 */
@Controller
@RequestMapping("/cutoff")
public class CutoffAction {

    private static final Logger log = LoggerFactory.getLogger(CutoffAction.class);
    @Autowired
    ServletContext servletContext;
    @Autowired
    CutoffServices cutoffServices;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index(final Cutoff cutoff, final HttpServletRequest request) {

        return new ModelAndView("cutoff/indexharilibur", "refbku", cutoff);
    }

    @RequestMapping(value = "/tambahlibur", method = RequestMethod.GET)
    public ModelAndView tambah(final Cutoff cutoff, HttpServletRequest request) {

        return new ModelAndView("cutoff/addharilibur", "refbku", cutoff);
    }

    @RequestMapping(value = "/editlibur", method = RequestMethod.GET)
    public ModelAndView editlibur(final HttpServletRequest request) {
        final String id = request.getParameter("id");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("id", id);

        final Cutoff cutoff = cutoffServices.getHariLibur(param);
        return new ModelAndView("cutoff/editharilibur", "refbku", cutoff);
    }

    @RequestMapping(value = "/hapuslibur", method = RequestMethod.GET)
    public ModelAndView hapuslibur(final HttpServletRequest request) {
        final String id = request.getParameter("id");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("id", id);

        final Cutoff cutoff = cutoffServices.getHariLibur(param);
        return new ModelAndView("cutoff/deleteharilibur", "refbku", cutoff);
    }

    @RequestMapping(value = "/json/listindex", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listindex(final HttpServletRequest request) {

        final Map< String, Object> param = new HashMap<String, Object>();
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");

        final String tahun = request.getParameter("tahun");

        param.put("tahun", tahun);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        final long banyak = cutoffServices.getBanyakListHariLibur(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", cutoffServices.getListHariLibur(param));

        return mapData;
    }

    @RequestMapping(value = "/json/prosestambahlibur", method = RequestMethod.POST)
    public @ResponseBody
    String prosestambahlibur(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        Cutoff cutoff = new Cutoff();

        final String waktu = (String) mapdata.get("tanggal");
        final String keterangan = (String) mapdata.get("uraian");
        final String tahun = waktu.substring(0, 4);

        cutoff.setWaktu(waktu);
        cutoff.setKeterangan(keterangan);
        cutoff.setIdEntry(pengguna.getIdPengguna());
        cutoff.setTahun(tahun);

        cutoffServices.insertHariLibur(cutoff);

        return "Hari Libur Berhasil Disimpan";
    }

    @RequestMapping(value = "/json/prosesubahlibur", method = RequestMethod.POST)
    public @ResponseBody
    String prosesubahlibur(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        Cutoff hariLibur = new Cutoff();

        final String waktu = (String) mapdata.get("tanggal");
        final String keterangan = (String) mapdata.get("uraian");
        final String id = (String) mapdata.get("id");
        final String tahun = waktu.substring(0, 4);

        hariLibur.setWaktu(waktu);
        hariLibur.setId(Integer.parseInt(id));
        hariLibur.setKeterangan(keterangan);
        hariLibur.setIdEdit(pengguna.getIdPengguna());
        hariLibur.setTahun(tahun);

        cutoffServices.updateHariLibur(hariLibur);

        return "Hari Libur Berhasil Diubah";
    }

    @RequestMapping(value = "/json/proseshapuslibur", method = RequestMethod.POST)
    public @ResponseBody
    String proseshapuslibur(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        Cutoff hariLibur = new Cutoff();

        final String waktu = (String) mapdata.get("tanggal");
        final String keterangan = (String) mapdata.get("uraian");
        final String id = (String) mapdata.get("id");
        final String tahun = waktu.substring(0, 4);

        hariLibur.setWaktu(waktu);
        hariLibur.setId(Integer.parseInt(id));
        hariLibur.setKeterangan(keterangan);
        hariLibur.setIdEdit(pengguna.getIdPengguna());
        hariLibur.setTahun(tahun);

        cutoffServices.deleteHariLibur(hariLibur);

        return "Hari Libur Berhasil Dihapus";
    }

    @RequestMapping(value = "/json/checklibur", method = RequestMethod.GET)
    public @ResponseBody
    Integer checkpassword(final HttpServletRequest request) {

        return cutoffServices.checkLibur();
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }
}
