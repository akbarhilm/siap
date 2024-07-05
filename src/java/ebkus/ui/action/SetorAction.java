/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.*/
package ebkus.ui.action;

import ebkus.model.BukuKasUmum;
import ebkus.model.Pengguna;
import ebkus.model.Setor;
import ebkus.services.SetorServices;
import ebkus.util.BigDecimalPropertyEditor;
import ebkus.util.PrintReportTemplate;
import ebkus.util.SipkdHelpers;
import ebkus.util.SqlDatePropertyEditor;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author BKUS
 */
@Controller
@RequestMapping("/setor")
public class SetorAction extends PrintReportTemplate {

    private static final Logger log = LoggerFactory.getLogger(SetorAction.class);

    @Autowired
    SetorServices setorServices;

    @Autowired
    ServletContext servletContext;

    @Autowired
    DataSource dataSource;

    @RequestMapping(value = "/indexsetor", method = RequestMethod.GET)
    public String index(final HttpServletRequest request) {
        return "/setor/indexsetor";
    }

    @RequestMapping(value = "/tambahsetor", method = RequestMethod.GET)
    public ModelAndView tambahsetor(final Setor setor, final HttpServletRequest request) {
        return new ModelAndView("setor/tambahsetor", "formsetor", setor);
    }

    @RequestMapping(value = "/json/getnosetor", method = RequestMethod.GET)
    public @ResponseBody
    Integer getnosetor(final HttpServletRequest request) {

        final String idsekolah = request.getParameter("idsekolah");
        final String tahun = request.getParameter("tahunsetor");
        final Map< String, Object> param = new HashMap<String, Object>(2);
        param.put("tahun", tahun);
        param.put("idsekolah", idsekolah);
        Integer nomorSetor = setorServices.getNomorSetoran(param);
        return nomorSetor;
    }

    @RequestMapping(value = "/json/getnilaist", method = RequestMethod.GET)
    public @ResponseBody
    Setor getnilaist(final HttpServletRequest request) {

        final String idsekolah = request.getParameter("idsekolah");
        final String tahun = request.getParameter("tahunsetor");
        final Integer idsetor = SipkdHelpers.getIntFromString(request.getParameter("idsetor").toString());
        final Integer kodetriwulan = SipkdHelpers.getIntFromString(request.getParameter("kodetriwulan").toString());
        final String kodesumbdana = request.getParameter("kodeSumbdana");
        log.debug("INI KODE B " + kodesumbdana);

        final Map< String, Object> param = new HashMap<String, Object>();
        param.put("tahun", tahun);
        param.put("idsekolah", idsekolah);
        param.put("idsetor", idsetor);
        param.put("kodetriwulan", kodetriwulan);
        //param.put("kodesumbdana", kodesumbdana);

        //BigDecimal nilaiST = setorServices.getNilaiST(param);
        return setorServices.getNilaiST(param);
    }

    @RequestMapping(value = "/json/listrincireal", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listrincireal(final HttpServletRequest request) {

        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");

        final String idsekolah = request.getParameter("idsekolah");
        final String tahun = request.getParameter("tahunsetor");
        final String kodetriwulan = request.getParameter("kodetriwulan");
        param.put("idsekolah", idsekolah);
        param.put("tahun", tahun);
        param.put("kodetriwulan", kodetriwulan);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        final long banyak = setorServices.getBanyakListRinciReal(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", setorServices.getListRinciReal(param));

        return mapData;
    }

    @RequestMapping(value = "/listpopupbkurtpenerimaan", method = RequestMethod.GET)
    public ModelAndView listpopupbkurtpenerimaan(final BukuKasUmum bku, final HttpServletRequest request, final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return new ModelAndView("setor/popupbkurtpenerimaan", "formbkujgpn", bku);
    }

    @RequestMapping(value = "/json/listpopupbkurtpenerimaan", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> jsonlistpopupbkurtpenerimaan(final HttpServletRequest request) {

        final Map< String, Object> param = new HashMap<String, Object>();
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");

        final String idsekolah = request.getParameter("idsekolah");
        final String tahun = request.getParameter("tahun");
        final String nobkuref = request.getParameter("nobkuref");
        final String kodetriwulan = request.getParameter("kodetriwulan");
        final String kodesumbdana = request.getParameter("kodeSumbdana");
        log.debug("INI KODE C " + kodesumbdana);

        param.put("idsekolah", idsekolah);
        param.put("tahun", tahun);
        param.put("nobkuref", nobkuref);
        param.put("kodetriwulan", kodetriwulan);
        param.put("kodesumbdana", kodesumbdana);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        final long banyak = setorServices.getBanyakListBkuRtPn(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", setorServices.getListBkuRtPn(param));

        return mapData;
    }
    /*
     @RequestMapping(value = "/listpopupbkujgpenerimaan", method = RequestMethod.GET)
     public ModelAndView listpopupbkujgpenerimaan(final BukuKasUmum bku, final HttpServletRequest request, final HttpServletResponse response) {
     response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
     return new ModelAndView("setor/popupbkujgpenerimaan", "formbkujgpn", bku);
     }

     @RequestMapping(value = "/json/listpopupbkujgpenerimaan", method = RequestMethod.GET)
     public @ResponseBody
     Map<String, Object> jsonlistpopupbkujgpenerimaan(final HttpServletRequest request) {

     final Map< String, Object> param = new HashMap<String, Object>();
     final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
     final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
     final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
     final String sSortDir_0 = request.getParameter("sSortDir_0");

     final String idsekolah = request.getParameter("idsekolah");
     final String tahun = request.getParameter("tahun");
     final String nobkuref = request.getParameter("nobkuref");
     final String kodetriwulan = request.getParameter("kodetriwulan");
     final String kodesumbdana = request.getParameter("kodeSumbdana");
     log.debug("INI KODE C " + kodesumbdana);

     param.put("idsekolah", idsekolah);
     param.put("tahun", tahun);
     param.put("nobkuref", nobkuref);
     param.put("kodetriwulan", kodetriwulan);
     param.put("kodesumbdana", kodesumbdana);
     param.put("offset", offset);
     param.put("limit", limit);
     param.put("iSortCol_0", iSortCol_0);
     param.put("sSortDir_0", sSortDir_0);
     final Map<String, Object> mapData = new HashMap<String, Object>(4);

     final long banyak = setorServices.getBanyakListBkuJgPn(param);
     mapData.put("sEcho", request.getParameter("sEcho"));
     mapData.put("iTotalRecords", banyak);
     mapData.put("iTotalDisplayRecords", banyak);
     mapData.put("aaData", setorServices.getListBkuJgPn(param));

     return mapData;
     }
     */

    @RequestMapping(value = "/simpansetor", method = RequestMethod.POST)
    public String prosessimpan(@Valid @ModelAttribute("formsetor") Setor setor,
            BindingResult result, final HttpServletRequest request, final RedirectAttributes redirectAttributes) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        log.debug("TEST OI " + setor.getSekolah());
        setor.setSekolah(pengguna.getSekolah());
        log.debug("TEST OI " + setor.getSekolah());
        setor.setIdEntry(pengguna.getIdPengguna());
        String kodeTransaksi = setor.getKodeTransaksi();
        if ("ST".equals(kodeTransaksi.substring(0, 2))) {
            setor.setKodeTransaksi("ST");
        } else {
            setor.setKodeTransaksi("RT");
        }

        //       if (result.hasErrors()) {
        //           log.debug("errrrrrr ==== " + result);
        //           redirectAttributes.addFlashAttribute("pesan", new StringBuilder("Silahkan ")
        //                   .append(" pilih Jenis Transaksi terlebih dulu ")
        //                   .toString());
        //          return "redirect:/setor/tambahsetor";
        //       } else {
        setorServices.insertSetor(setor);
        //log.debug("Data Setor simpan ===== "+ setor.toString());
        return "redirect:/setor/indexsetor";
//        }
    }

    @RequestMapping(value = "/json/listindexsetor", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listindexsetor(final HttpServletRequest request) {

        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");

        final String idsekolah = request.getParameter("idsekolah");
        final String tahun = request.getParameter("tahun");
        //final String nobku = request.getParameter("nobku");

        param.put("idsekolah", idsekolah);
        param.put("tahun", tahun);
        //param.put("nobku", nobku);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        final long banyak = setorServices.getBanyakListIndexSetor(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", setorServices.getListIndexSetor(param));

        return mapData;
    }

    @RequestMapping(value = "/ubahsetor/{ctrx}/{idsetor}", method = RequestMethod.GET)
    public ModelAndView editsetor(@PathVariable String ctrx, @PathVariable Integer idsetor,
            final HttpServletRequest request) {
        final Setor setor = setorServices.getSetorById(idsetor);
        if ("JG".equals(ctrx)) {
            return new ModelAndView("setor/ubahsetorjg", "formsetor", setor);
        } else if ("RT".equals(ctrx)) {
            return new ModelAndView("setor/ubahsetorrt", "formsetor", setor);
        } else {
            return new ModelAndView("setor/ubahsetorst", "formsetor", setor);
        }
    }

    @RequestMapping(value = "/simpanubahsetor", method = RequestMethod.POST)
    public String simpanubahsetor(@Valid @ModelAttribute("formsetor") Setor setor,
            BindingResult result, final HttpServletRequest request, final RedirectAttributes redirectAttributes) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        setor.setIdEdit(pengguna.getIdPengguna());
        setor.setSekolah(pengguna.getSekolah());
//        if (result.hasErrors()) {
//            log.debug("errrrrrr ==== " + result);
//            redirectAttributes.addFlashAttribute("pesan", new StringBuilder("Silahkan ")
//                    .append(" pilih Jenis Transaksi terlebih dulu ")
//                    .toString());
//            return "redirect:/setor/ubahsetor/" + setor.getKodeTransaksi().toString() + "/" + setor.getIdSetor().toString();
//        } else {
        setorServices.updateSetor(setor);
        //log.debug("Data Setor update ===== "+ setor.toString());
        return "redirect:/setor/indexsetor";
//        }
    }

    @RequestMapping(value = "/hapussetor/{ctrx}/{idsetor}", method = RequestMethod.GET)
    public ModelAndView deletesetor(@PathVariable String ctrx, @PathVariable Integer idsetor,
            final HttpServletRequest request) {
        final Setor setor = setorServices.getSetorById(idsetor);
        return new ModelAndView("setor/hapussetor", "formsetor", setor);
    }

    @RequestMapping(value = "/simpanhapussetor", method = RequestMethod.POST)
    public String simpanhapussetor(@Valid @ModelAttribute("formsetor") Setor setor,
            BindingResult result, final HttpServletRequest request, final RedirectAttributes redirectAttributes) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        setor.setIdEdit(pengguna.getIdPengguna());
        setor.setSekolah(pengguna.getSekolah());

//        if (result.hasErrors()) {
//            log.debug("errrrrrr ==== " + result);
//            redirectAttributes.addFlashAttribute("pesan", new StringBuilder("Silahkan ")
//                    .append(" pilih Jenis Transaksi terlebih dulu ")
//                    .toString());
//            return "redirect:/setor/ubahsetor/" + setor.getKodeTransaksi().toString() + "/" + setor.getIdSetor().toString();
//        } else {
        setorServices.deleteSetor(setor);
        //log.debug("Data Setor update ===== "+ setor.toString());
        return "redirect:/setor/indexsetor";
//        }
    }

    @RequestMapping(value = "/cetaksetor", method = RequestMethod.GET)
    public String cetaksetor(final HttpServletRequest request) {
        return "/setor/cetaksetor";
    }

    @RequestMapping(value = "/json/listcetaksetor", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listcetaksetor(final HttpServletRequest request) {

        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");

        final String idsekolah = request.getParameter("idsekolah");
        final String tahun = request.getParameter("tahun");
        final String nobku = request.getParameter("nobku");

        param.put("idsekolah", idsekolah);
        param.put("tahun", tahun);
        //param.put("nobku", nobku);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        final long banyak = setorServices.getBanyakListCetakSetor(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", setorServices.getListCetakSetor(param));

        return mapData;
    }

    @RequestMapping(value = "/json/insertsetorcetak", method = RequestMethod.POST)
    public @ResponseBody
    String insertsetorcetak(@RequestBody List<Map<String, Object>> mapdata, final HttpServletRequest request) {
        setorServices.insertSetorCetak(mapdata);
        return "Simpan Data Cetak Setor Sukses";
    }

    @RequestMapping(value = "/json/hapussetorcetak", method = RequestMethod.POST)
    public @ResponseBody
    String hapussetorcetak(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        setorServices.deleteSetorCetak((Integer) mapdata.get("idsetor"));
        return "Data Setor No " + mapdata.get("nosetor") + " berhasil dibatalkan  ";
    }

    @RequestMapping(value = "/cetaksetor/{tahun}/{idsekolah}/{nosetor}/{kodetransaksi}", method = RequestMethod.GET)
    public void processRequest(HttpServletRequest request, HttpServletResponse response, @PathVariable String tahun, @PathVariable String idsekolah, @PathVariable String nosetor, @PathVariable String kodetransaksi) throws SQLException {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        response.setContentType("application/pdf");
//        try {
//            final Connection jdbcConnection = dataSource.getConnection();
        final Map<String, Object> map = new HashMap<String, Object>();
        //final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String pathReport = servletContext.getInitParameter("PATH_REPORT");
        // final String level = cetakValidasiServices.getlevel();
        System.out.println("pathReport " + pathReport);
        map.put("SUBREPORT_DIR", pathReport);
        map.put("TAHUN", tahun);
        map.put("IDSEKOLAH", idsekolah);
        map.put("ISETOR", nosetor);
        if (kodetransaksi.equals("ST")) {
            map.put("pathreport", pathReport + "/Report_Setoran_ST.jasper");
            map.put("filename", tahun + "-SETOR (Sisa Kas)-" + idsekolah + "-" + nosetor + ".pdf");
        } else if (kodetransaksi.equals("RT")) {
            map.put("pathreport", pathReport + "/Report_Setoran_RT.jasper");
            map.put("filename", tahun + "-SETOR (Pendapatan Lain-lain)-" + idsekolah + "-" + nosetor + ".pdf");
        }

        printReportToPdfStream(response, map);
        /*
         JasperPrint jasperPrint = JasperFillManager.fillReport(pathReport + "/Report_Setoran_BKUS.jasper", map, jdbcConnection);
         final String filename = tahun + "-SETOR-" + idsekolah + "-" + nosetor + ".pdf";
         response.setHeader("Content-Disposition", "attachment; filename=" + filename);
         ServletOutputStream output = response.getOutputStream();
         JasperExportManager.exportReportToPdfStream(jasperPrint, output);
         output.close();
         } catch (Exception e) {
         log.error(e.getMessage());
         e.printStackTrace();
         }
         */
    }

    @RequestMapping(value = "/approvesetor", method = RequestMethod.GET)
    public String indexapprovesetor(final HttpServletRequest request) {
        return "/setor/approvesetor";
    }

    @RequestMapping(value = "/json/listsetorapprove", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listsetorapprove(final HttpServletRequest request) {

        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");

        final String sekolah = request.getParameter("sekolah");
        final String tahun = request.getParameter("tahun");
        final String idskpd = request.getParameter("idskpd");
        log.debug("IDSKPD + " + idskpd);
        param.put("sekolah", sekolah);
        param.put("tahun", tahun);
        param.put("idskpd", idskpd);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        final long banyak = setorServices.getBanyakListSetorApprove(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", setorServices.getListSetorApprove(param));

        return mapData;
    }

    @RequestMapping(value = "/json/insertsetorapprove", method = RequestMethod.POST)
    public @ResponseBody
    String insertsetorapprove(@RequestBody List<Map<String, Object>> mapdata, final HttpServletRequest request) {
        //final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        setorServices.updateSetorApprove(mapdata);
        return "Simpan Data Approve Setoran Sukses";
    }

    @RequestMapping(value = "/json/hapussetorapprove", method = RequestMethod.POST)
    public @ResponseBody
    String hapussetorapprove(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        setorServices.deleteSetorApprove((Integer) mapdata.get("idsetor"));
        return "Data Persetujuan Setoran berhasil dibatalkan  ";
    }

    @RequestMapping(value = "/viewrincisetor/{idsekolah}/{idsetor}", method = RequestMethod.GET)
    public ModelAndView viewrincisetor(@PathVariable Integer idsekolah, @PathVariable Integer idsetor,
            final HttpServletRequest request, final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        final Setor setor = setorServices.getSetorById(idsetor);
        setor.setIdSekolah(idsekolah);

        return new ModelAndView("setor/viewrincisetorst", "formsetor", setor);

    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }

}
