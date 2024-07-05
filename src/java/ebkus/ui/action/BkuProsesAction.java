package ebkus.ui.action;

import ebkus.model.BkuProses;
import ebkus.model.BukuKasUmum;
import ebkus.model.Pengguna;
import ebkus.services.BkuProsesServices;
import ebkus.util.BigDecimalPropertyEditor;
import ebkus.util.SipkdHelpers;
import ebkus.util.SqlDatePropertyEditor;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
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
@RequestMapping("/bkuproses")
public class BkuProsesAction {

    private static final Logger log = LoggerFactory.getLogger(BkuProsesAction.class);
    
    @Autowired
    BkuProsesServices bkuServices;

    @RequestMapping(value = "/indexbkuproses", method = RequestMethod.GET)
    public ModelAndView index(final BkuProses bku, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        return new ModelAndView("bkuproses/indexbkuproses", "refbku", bku);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addbku(final BkuProses bku, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        return new ModelAndView("bkuproses/addbkuproses", "refbku", bku);
    }

    @RequestMapping(value = "/json/listindex", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listindex(final HttpServletRequest request) {

        final Map< String, Object> param = new HashMap<String, Object>();
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");

        final String idskpd = request.getParameter("idskpd");
        
        param.put("idskpd", idskpd);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        final long banyak = bkuServices.getBanyakListIndex(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", bkuServices.getListIndex(param));

        return mapData;
    }

    @RequestMapping(value = "/json/getSkpd", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getSkpd(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");
        
        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
        
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        mapData.put("aData", bkuServices.getSkpd(param));
        return mapData;
    }
    
    @RequestMapping(value = "/json/prosessimpan", method = RequestMethod.POST)
    public @ResponseBody
    String prosessimpanbku(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");

        BkuProses bukukas = new BkuProses();

        final String idskpd = (String) mapdata.get("idskpd");
        final String triwulan = (String) mapdata.get("triwulan");
        final String sumbdana = (String) mapdata.get("sumbdana");
        final String bataswaktu = (mapdata.get("bataswaktu")).toString();
                  
        bukukas.setKodeSumbdana(sumbdana);
        bukukas.setIdskpd(SipkdHelpers.getIntFromString(idskpd.toString()));
        bukukas.setTriwulan(triwulan);
        bukukas.setBatasWaktu(bataswaktu);
        bukukas.setIdEntry(pengguna.getIdPengguna());
        
        bkuServices.insertBatasWaktu(bukukas);

        return "Data Buku Kas Umum Berhasil Disimpan";
    }
    
    @RequestMapping(value = "/edit/", method = RequestMethod.GET)
    public ModelAndView edit(final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String id = request.getParameter("id");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("id", id);
        
        final BkuProses bku = bkuServices.getEdit(param);

        return new ModelAndView("bkuproses/editbkuproses", "refbku", bku);
    }
    
    @RequestMapping(value = "/json/prosesupdate", method = RequestMethod.POST)
    public @ResponseBody
    String prosesupdate(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");

        BkuProses bukukas = new BkuProses();

        final String idskpd = (String) mapdata.get("idskpd");
        final String triwulan = (String) mapdata.get("triwulan");
        final String sumbdana = (String) mapdata.get("sumbdana");
        final String bataswaktu = (mapdata.get("bataswaktu")).toString();
        final String id = (mapdata.get("id")).toString();
                  
        bukukas.setKodeSumbdana(sumbdana);
        bukukas.setIdskpd(SipkdHelpers.getIntFromString(idskpd.toString()));
        bukukas.setId(SipkdHelpers.getIntFromString(id.toString()));
        bukukas.setTriwulan(triwulan);
        bukukas.setBatasWaktu(bataswaktu);
        bukukas.setIdEntry(pengguna.getIdPengguna());
        
        bkuServices.updateBatasWaktu(bukukas);

        return "Data Buku Kas Umum Berhasil Diubah";
    }
    
/*
    

    @RequestMapping(value = "/json/prosesupdatec12", method = RequestMethod.POST)
    public @ResponseBody
    String prosesupdatec12(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");

        BukuKasUmum bukukas = new BukuKasUmum();

        final String idbku = (String) mapdata.get("idbku");
        final String idsekolah = (String) mapdata.get("idsekolah");
        final String kodetrans = (String) mapdata.get("kodetransaksi");
        final String nobukti = (String) mapdata.get("nobukti");
        final String tgldok = (mapdata.get("tgldok")).toString();
        final String nilai = (String) mapdata.get("nilai");
        final String filling = (String) mapdata.get("fileinbox");
        final String nrk = (String) mapdata.get("nrk");
        final String namapptk = (String) mapdata.get("namapptk");
        final String nippptk = (String) mapdata.get("nippptk");
        final String uraian = (String) mapdata.get("uraian");
        final String nobkumohon = (String) mapdata.get("nobkumohon");
        final String triwulan = (String) mapdata.get("triwulan");

        bukukas.setTahun(tahun);
        bukukas.setTriwulan(triwulan);
        bukukas.setIdsekolah(SipkdHelpers.getIntFromString(idsekolah.toString()));
        bukukas.setIdEntry(pengguna.getIdPengguna());
        log.debug("Ini id entry " + bukukas.getIdEntry());
        bukukas.setKodeTransaksi(kodetrans);
        bukukas.setNoDok(nobukti);
        bukukas.setTglDok(SipkdHelpers.getDateFromString(tgldok));
        bukukas.setNilaiMasuk(SipkdHelpers.getBigDecimalFromString(nilai));
        bukukas.setNilaiKeluar(SipkdHelpers.getBigDecimalFromString(nilai));
        bukukas.setInboxFile(filling);
        bukukas.setNamaPptk(namapptk);
        bukukas.setNrkPptk(nrk);
        bukukas.setNipPptk(nippptk);
        bukukas.setUraian(uraian);
        bukukas.setUraianBukti(uraian);
        bukukas.setKodeWilayah(pengguna.getKodeProses());
        bukukas.setIdBku(SipkdHelpers.getIntFromString(idbku));
        bukukas.setKodeKoreksi("0");

        bkuServices.updateBkuC12(bukukas);

        return "Data Buku Kas Umum Berhasil Diubah";
    }

    */
    
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }

}
