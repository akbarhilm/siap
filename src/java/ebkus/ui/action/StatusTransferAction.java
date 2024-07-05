package ebkus.ui.action;

import ebkus.model.BukuKasUmum;
import ebkus.model.Pengguna;
import ebkus.services.StatusTransferServices;
import ebkus.util.BigDecimalPropertyEditor;
import ebkus.util.PrintReportTemplate;
import ebkus.util.SipkdHelpers;
import ebkus.util.SqlDatePropertyEditor;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
 * @author Zainab
 */
@Controller
@RequestMapping("/statustransfer")
public class StatusTransferAction extends PrintReportTemplate {

    private static final Logger log = LoggerFactory.getLogger(StatusTransferAction.class);

    @Autowired
    StatusTransferServices bkuServices;

    @Autowired
    ServletContext servletContext;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index(final BukuKasUmum bku, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        return new ModelAndView("statustransfer/statustransfer", "refbku", bku);
    }

    @RequestMapping(value = "/json/listkegiatan", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listkegiatan(final HttpServletRequest request) {

        final Map< String, Object> param = new HashMap<String, Object>();
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");

        final String idsekolah = request.getParameter("idsekolah");
        final String tahun = request.getParameter("tahun");
        final String triwulan = request.getParameter("triwulan");
        final String nomohon = request.getParameter("nomohon");
        final String kodetrans = request.getParameter("kodetrans");
        final String kodesumb = request.getParameter("kodesumb");

        param.put("kodetrans", kodetrans);
        param.put("idsekolah", idsekolah);
        param.put("tahun", tahun);
        param.put("triwulan", triwulan);
        param.put("nomohon", nomohon);
        param.put("kodesumb", kodesumb);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);

        Map<String, Object> mapData = new HashMap<String, Object>(4);
        long banyak;

        if ("BOP".equals(kodesumb)) {
            banyak = bkuServices.getBanyakListSpjBop(param);
            mapData.put("sEcho", request.getParameter("sEcho"));
            mapData.put("iTotalRecords", banyak);
            mapData.put("iTotalDisplayRecords", banyak);
            mapData.put("aaData", bkuServices.getListSpjBop(param));

        } else {
            banyak = bkuServices.getBanyakListSpjBos(param);
            mapData.put("sEcho", request.getParameter("sEcho"));
            mapData.put("iTotalRecords", banyak);
            mapData.put("iTotalDisplayRecords", banyak);
            mapData.put("aaData", bkuServices.getListSpjBos(param));
        }

        return mapData;
    }

    @RequestMapping(value = "/json/getBanyakBankBerhasil", method = RequestMethod.GET)
    public @ResponseBody
    Integer getBanyakBankBerhasil(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idbku = request.getParameter("idbku");
        final String kodesumb = request.getParameter("kodesumb");

        final Map< String, Object> param = new HashMap<String, Object>(5);
        param.put("tahun", tahunAnggaran);
        param.put("idbku", SipkdHelpers.getIntFromString(idbku));
        param.put("kodesumb", kodesumb);

        return bkuServices.getBanyakBankBerhasil(param);
    }

    @RequestMapping(value = "/listdok", method = RequestMethod.GET)
    public ModelAndView listdok(final BukuKasUmum bku, final HttpServletRequest request, final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return new ModelAndView("statustransfer/listdok", "refkegiatan", bku);
    }

    @RequestMapping(value = "/json/listdatabank", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listdatabank(final HttpServletRequest request) {

        final Map< String, Object> param = new HashMap<String, Object>();
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");

        final String idbku = request.getParameter("idbku");
        final String tahun = request.getParameter("tahun");
        final String berhasil = request.getParameter("berhasil");
        final String kodesumb = request.getParameter("kodesumb");

        param.put("kodesumb", kodesumb);
        param.put("idbku", SipkdHelpers.getIntFromString(idbku));
        param.put("tahun", tahun);
        param.put("berhasil", berhasil);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        final long banyak = bkuServices.getBanyakListBank(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", bkuServices.getListBank(param));

        return mapData;
    }

    @RequestMapping(value = "/json/prosesupdatedatabank", method = RequestMethod.POST)
    public @ResponseBody
    String prosessimpankoreksi(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");

        final String idbank = (String) mapdata.get("idbank");
        final String idbku = (String) mapdata.get("idbku");
        final String kodesumb = request.getParameter("kodesumb");

        final Map< String, Object> param = new HashMap<String, Object>();

        param.put("kodesumb", kodesumb);
        param.put("idbku", SipkdHelpers.getIntFromString(idbku));
        param.put("tahun", tahun);
        param.put("id", SipkdHelpers.getIntFromString(idbank));

        bkuServices.updateDataBank(param);

        return "Data Buku Kas Umum Berhasil Disimpan";
    }

    @RequestMapping(value = "/json/prosesupdatebku", method = RequestMethod.POST)
    public @ResponseBody
    String prosesupdatebku(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        BukuKasUmum bukukas = new BukuKasUmum();

        final String idsekolah = (String) mapdata.get("idsekolah");
        final String nomohon = (String) mapdata.get("nomohon");
        final String kodesumb = (String) mapdata.get("kodesumb");

        bukukas.setTahun(tahun);
        bukukas.setIdsekolah(SipkdHelpers.getIntFromString(idsekolah.toString()));
        bukukas.setIdEntry(pengguna.getIdPengguna());
        bukukas.setNoBkuMohon(SipkdHelpers.getIntFromString(nomohon));

        if ("BOS".equals(kodesumb)) {
            bkuServices.updateDataBkuBos(bukukas);
        } else {
            bkuServices.updateDataBkuBop(bukukas);
        }

        return "Data Buku Kas Umum Berhasil Disimpan";
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }

}
