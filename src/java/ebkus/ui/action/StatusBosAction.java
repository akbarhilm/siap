/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.ui.action;

import ebkus.model.Pengguna;
import ebkus.services.SekolahServices;
import ebkus.services.StatusBosServices;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
@RequestMapping("/statusbos")
public class StatusBosAction {

    private static final Logger log = LoggerFactory.getLogger(StatusBosAction.class);
    @Autowired
    StatusBosServices bosServices;
    @Autowired
    SekolahServices sekolahServices;
    @Autowired
    ServletContext servletContext;
    @Autowired
    DataSource dataSource;

    @RequestMapping(value = "/indexbljpengajuan", method = RequestMethod.GET)
    public ModelAndView indexPengajuan(final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        return new ModelAndView("/bkubos/pengajuanbos", "pengajuanbos", pengguna);
    }

    @RequestMapping(value = "/indexpengajuansudin", method = RequestMethod.GET)
    public ModelAndView indexPengirimanSudin(final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        return new ModelAndView("/bkubos/pengirimanbos", "pengirimanbos", pengguna);
    }

    @RequestMapping(value = "/indexbljpersetujuan", method = RequestMethod.GET)
    public ModelAndView indexPersetujuan(final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        return new ModelAndView("/bkubos/persetujuanbos", "persetujuanbos", pengguna);
    }

    @RequestMapping(value = "/indexbkupengajuan", method = RequestMethod.GET)
    public ModelAndView indexPersetujuanLain(final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        return new ModelAndView("/bkubos/persetujuanboslain", "persetujuanboslain", pengguna);
    }

    @RequestMapping(value = "/json/listpengajuan", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getPengajuan(final HttpServletRequest request) {
        final String tahun = request.getParameter("tahun");
        final Integer idSekolah = Integer.valueOf(request.getParameter("idSekolah"));
        final String triwulan = request.getParameter("triwulan");
        final String kodeTrx = request.getParameter("kodeTrx");
        final String dari = request.getParameter("dari");
        final String hingga = request.getParameter("hingga");
        final Map< String, Object> param = new HashMap<String, Object>();
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String kodeKegiatanFilter = request.getParameter("kodeKegiatanFilter");
        final String namaKegiatanFilter = request.getParameter("namaKegiatanFilter");
        final String kodeAkunFilter = request.getParameter("kodeAkunFilter");
        final String namaAkunFilter = request.getParameter("namaAkunFilter");
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        param.put("tahun", tahun);
        param.put("idSekolah", idSekolah);
        param.put("triwulan", triwulan);
        param.put("kodeTrx", kodeTrx);
        param.put("dari", dari);
        param.put("hingga", hingga);
        param.put("kodeKegiatanFilter", kodeKegiatanFilter);
        param.put("namaKegiatanFilter", namaKegiatanFilter);
        param.put("kodeAkunFilter", kodeAkunFilter);
        param.put("namaAkunFilter", namaAkunFilter);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final int banyak = bosServices.getBanyakBosDari(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", bosServices.getListBosDari(param));
        return mapData;
    }

    @RequestMapping(value = "/json/listpersetujuan", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getPersetujuan(final HttpServletRequest request) {
        final String tahun = request.getParameter("tahun");
        final Integer idSekolah = Integer.valueOf(request.getParameter("idSekolah"));
        final String triwulan = request.getParameter("triwulan");
        final String kodeTrx = request.getParameter("kodeTrx");
        final String dari = request.getParameter("dari");
        final String hingga = request.getParameter("hingga");
        final Map< String, Object> param = new HashMap<String, Object>();
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String kodeKegiatanFilter = request.getParameter("kodeKegiatanFilter");
        final String namaKegiatanFilter = request.getParameter("namaKegiatanFilter");
        final String kodeAkunFilter = request.getParameter("kodeAkunFilter");
        final String namaAkunFilter = request.getParameter("namaAkunFilter");
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        param.put("tahun", tahun);
        param.put("idSekolah", idSekolah);
        param.put("triwulan", triwulan);
        param.put("kodeTrx", kodeTrx);
        param.put("dari", dari);
        param.put("hingga", hingga);
        param.put("kodeKegiatanFilter", kodeKegiatanFilter);
        param.put("namaKegiatanFilter", namaKegiatanFilter);
        param.put("kodeAkunFilter", kodeAkunFilter);
        param.put("namaAkunFilter", namaAkunFilter);
        int banyak;
        log.debug("JJJJJJJ");

        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        if (!kodeTrx.equals("P")) {
            banyak = bosServices.getBanyakBosDari(param);
            log.debug("UYEAH " + banyak);
            mapData.put("aaData", bosServices.getListBosDari(param));
        } else {
            log.debug("PAJAAKXX");
            banyak = bosServices.getBanyakBosPajak(param);
            mapData.put("aaData", bosServices.getListBosPajak(param));
        }
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        return mapData;
    }

    @RequestMapping(value = "/json/listapproval", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getPembayaran(final HttpServletRequest request) {
        final String tahun = request.getParameter("tahun");
        final Integer idSekolah = Integer.valueOf(request.getParameter("idSekolah"));
        final String triwulan = request.getParameter("triwulan");
        final String kodeTrx = request.getParameter("kodeTrx");
        final String dari = request.getParameter("dari");
        final String hingga = request.getParameter("hingga");
        final Map< String, Object> param = new HashMap<String, Object>();
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String kodeKegiatanFilter = request.getParameter("kodeKegiatanFilter");
        final String namaKegiatanFilter = request.getParameter("namaKegiatanFilter");
        final String kodeAkunFilter = request.getParameter("kodeAkunFilter");
        final String namaAkunFilter = request.getParameter("namaAkunFilter");
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        param.put("tahun", tahun);
        param.put("idSekolah", idSekolah);
        param.put("triwulan", triwulan);
        param.put("kodeTrx", kodeTrx);
        param.put("dari", dari);
        param.put("hingga", hingga);
        param.put("kodeKegiatanFilter", kodeKegiatanFilter);
        param.put("namaKegiatanFilter", namaKegiatanFilter);
        param.put("kodeAkunFilter", kodeAkunFilter);
        param.put("namaAkunFilter", namaAkunFilter);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final int banyak = bosServices.getBanyakBosDari(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", bosServices.getListBosDari(param));
        return mapData;
    }

    @RequestMapping(value = "/json/getTotalDari", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getTotalDari(final HttpServletRequest request) {
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idSekolah = request.getParameter("idSekolah");
        final String triwulan = request.getParameter("triwulan");
        final String kodeTrx = request.getParameter("kodeTrx");
        final String dari = request.getParameter("dari");
        final String hingga = request.getParameter("hingga");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahun);
        param.put("idSekolah", idSekolah);
        param.put("triwulan", triwulan);
        param.put("kodeTrx", kodeTrx);
        param.put("dari", dari);
        param.put("hingga", hingga);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        if (!kodeTrx.equals("P")) {
            mapData.put("aData", bosServices.getTotalDari(param));
        } else {
            mapData.put("aData", bosServices.getTotalPajak(param));
        }
        return mapData;
    }

    @RequestMapping(value = "/json/getTotal", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getTotal(final HttpServletRequest request) {
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idSekolah = request.getParameter("idSekolah");
        final String triwulan = request.getParameter("triwulan");
        final String kodeTrx = request.getParameter("kodeTrx");
        final String status = request.getParameter("status");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahun);
        param.put("idSekolah", idSekolah);
        param.put("triwulan", triwulan);
        param.put("kodeTrx", kodeTrx);
        param.put("status", status);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        mapData.put("aData", bosServices.getTotal(param));
        return mapData;
    }

    @RequestMapping(value = "/json/getBanyak", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getBanyak(final HttpServletRequest request) {
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idSekolah = request.getParameter("idSekolah");
        final String triwulan = request.getParameter("triwulan");
        final String kodeTrx = request.getParameter("kodeTrx");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahun);
        param.put("idSekolah", idSekolah);
        param.put("triwulan", triwulan);
        param.put("kodeTrx", kodeTrx);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        if (!kodeTrx.equals("P")) {
            mapData.put("aData", bosServices.getBanyakBosTotal(param));
        } else {
            log.debug("PAJAAK2");
            mapData.put("aData", bosServices.getBanyakBosTotalPajak(param));
        }
        return mapData;
    }

    @RequestMapping(value = "/json/getTutupBuku", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getTutupBuku(final HttpServletRequest request) {
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idSekolah = request.getParameter("idSekolah");
        final String kodeTrx = request.getParameter("kodeTrx");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahun);
        param.put("idSekolah", idSekolah);
        param.put("kodeTrx", kodeTrx);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        if (!kodeTrx.equals("P")) {
            mapData.put("aData", bosServices.getStatusTutupBuku(param));
        } else {
            log.debug("PAJAAK2");
            mapData.put("aData", bosServices.getStatusTutupBukuPajak(param));
        }
        return mapData;
    }

    @RequestMapping(value = "/json/prosesupdate", method = RequestMethod.POST)
    public @ResponseBody
    void prosesUpdate(@RequestBody Map<String, String> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final Map< String, Object> param = new HashMap<String, Object>();
        log.debug("UPDATE ====== " + mapdata.get("tahun"));
        log.debug("UPDATE ====== " + mapdata.get("idSekolah"));
        log.debug("UPDATE ====== " + mapdata.get("statusSebelum"));
        log.debug("UPDATE ====== " + mapdata.get("statusSesudah"));
        param.put("tahun", mapdata.get("tahun"));
        param.put("idSekolah", mapdata.get("idSekolah"));
        param.put("triwulan", mapdata.get("triwulan"));
        param.put("statusSebelum", mapdata.get("statusSebelum"));
        param.put("statusSesudah", mapdata.get("statusSesudah"));
        param.put("kodeTrx", mapdata.get("kodeTrx"));
        param.put("id", pengguna.getIdPengguna());
        param.put("tanggal", new Timestamp(System.currentTimeMillis()));
        if (!mapdata.get("kodeTrx").equals("P")) {
            bosServices.updateStatusBos(param);
            log.debug("UPDATE123");
        } else {
            log.debug("456");
            bosServices.updateStatusBosPajak(param);
        }
    }

    @RequestMapping(value = "/json/prosesupdatemohon", method = RequestMethod.POST)
    public @ResponseBody
    void prosesUpdateMohon(@RequestBody List<Map<String, String>> listmapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final Map< String, Object> param = new HashMap<String, Object>();
        for (Map<String, String> mapdata : listmapdata) {
            log.debug(" mapdata " + mapdata.toString());
            log.debug("UPDATE ====== " + mapdata.get("tahun"));
            log.debug("UPDATE ====== " + mapdata.get("idSekolah"));
            log.debug("UPDATE ====== " + mapdata.get("statusSebelum"));
            log.debug("UPDATE ====== " + mapdata.get("statusSesudah"));
            param.put("tahun", mapdata.get("tahun"));
            param.put("idSekolah", mapdata.get("idSekolah"));
            param.put("triwulan", mapdata.get("triwulan"));
            param.put("statusSebelum", mapdata.get("statusSebelum"));
            param.put("statusSesudah", mapdata.get("statusSesudah"));
            param.put("kodeTrx", mapdata.get("kodeTrx"));
            param.put("id", pengguna.getIdPengguna());
            param.put("tanggal", new Timestamp(System.currentTimeMillis()));
            param.put("noMohon", mapdata.get("noMohon"));
            bosServices.updateStatusBosByNoMohon(param);
        }
    }

    @RequestMapping(value = "/json/validateNilai", method = RequestMethod.GET)
    public @ResponseBody
    Integer validateNilai(final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>(3);
        final String idsekolah = request.getParameter("idSekolah");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        final String triwulan = request.getParameter("triwulan");
        final String nomohon = request.getParameter("noBkuMohon");

        param.put("idsekolah", idsekolah);
        param.put("tahun", tahun);
        param.put("triwulan", triwulan);
        param.put("nomohon", nomohon);

        return bosServices.validateNilai(param);
    }
}
