package ebkus.ui.action;

import ebkus.model.BaBatal;
import ebkus.model.Pengguna;
import ebkus.services.BaBatalServices;
import ebkus.services.FormBkuServices;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Zainab
 */
@Controller
@RequestMapping("/babtl")
public class BaBatalTransferAction extends PrintReportTemplate {

    private static final Logger log = LoggerFactory.getLogger(BaBatalTransferAction.class);

    @Autowired
    ServletContext servletContext;
    @Autowired
    BaBatalServices baBatalServices;
    @Autowired
    FormBkuServices cetakService;

    private final RestTemplate rest;
    private final HttpHeaders headers;
    private HttpStatus status;

    public BaBatalTransferAction() {
        this.rest = new RestTemplate();
        this.headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "*/*");
    }

    @RequestMapping(value = "/listtambahba", method = RequestMethod.GET)
    public String tambahba(final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return "babatal/tambahba";
    }

    @RequestMapping(value = "/listeditba", method = RequestMethod.GET)
    public ModelAndView editba(final HttpServletRequest request, final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        final String tahun = request.getParameter("tahun");
        final String nomohon = request.getParameter("nomohon");
        final String idsekolah = request.getParameter("idsekolah");
        final String kodesumbdana = request.getParameter("kodesumbdana");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("nomohon", nomohon);
        param.put("tahun", tahun);
        param.put("idsekolah", idsekolah);
        param.put("kodesumbdana", kodesumbdana);

        final BaBatal baBatal = baBatalServices.getBaBatal(param);

        return new ModelAndView("babatal/editba", "formbatal", baBatal);
    }

    @RequestMapping(value = "/listhapusba", method = RequestMethod.GET)
    public ModelAndView hapusba(final HttpServletRequest request, final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        final String tahun = request.getParameter("tahun");
        final String nomohon = request.getParameter("nomohon");
        final String idsekolah = request.getParameter("idsekolah");
        final String kodesumbdana = request.getParameter("kodesumbdana");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("nomohon", nomohon);
        param.put("tahun", tahun);
        param.put("idsekolah", idsekolah);
        param.put("kodesumbdana", kodesumbdana);

        final BaBatal baBatal = baBatalServices.getBaBatal(param);

        return new ModelAndView("babatal/hapusba", "formbatal", baBatal);
    }

    @RequestMapping(value = "/listarsipba", method = RequestMethod.GET)
    public ModelAndView arsipba(final HttpServletRequest request, final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        final String tahun = request.getParameter("tahun");
        final String nomohon = request.getParameter("nomohon");
        final String idsekolah = request.getParameter("idsekolah");
        final String kodesumbdana = request.getParameter("kodesumbdana");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("nomohon", nomohon);
        param.put("tahun", tahun);
        param.put("idsekolah", idsekolah);
        param.put("kodesumbdana", kodesumbdana);

        final BaBatal baBatal = baBatalServices.getBaBatal(param);

        return new ModelAndView("babatal/arsipba", "formbatal", baBatal);
    }

    @RequestMapping(value = "/json/prosessimpan", method = RequestMethod.POST)
    public @ResponseBody
    String prosessimpan(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        BaBatal baBatal = new BaBatal();
        final String idsekolah = (String) mapdata.get("idsekolah");
        final String kodetransaksi = (String) mapdata.get("kodetransaksi");
        final String nomohon = (String) mapdata.get("nomohon");
        final String noBeritaAcara = (String) mapdata.get("noBeritaAcara");
        final String noBeritaAcaraFormatted = (String) mapdata.get("noBeritaAcaraFormatted");
        final String nilai = (String) mapdata.get("nilai");
        final String uraian = (String) mapdata.get("uraian");
        final String triwulan = (String) mapdata.get("triwulan");
        final String tahun = (String) mapdata.get("tahun");
        final String kodesumbdana = (String) mapdata.get("kodesumbdana");
        final String tanggal = (mapdata.get("tanggal")).toString();

        baBatal.setIdSekolah(SipkdHelpers.getIntFromString(idsekolah));
        baBatal.setKodeSumbdana(kodesumbdana);
        baBatal.setKodeTransaksi(kodetransaksi);
        baBatal.setTanggal(SipkdHelpers.getDateFromString(tanggal));
        baBatal.setNilai(SipkdHelpers.getBigDecimalFromString(nilai));
        baBatal.setNoBa(noBeritaAcara);
        baBatal.setNoBaDok(noBeritaAcaraFormatted);
        baBatal.setNoBkuMohon(SipkdHelpers.getIntFromString(nomohon));
        baBatal.setTriwulan(triwulan);
        baBatal.setTahun(tahun);
        baBatal.setUraian(uraian);
        baBatal.setIdEntry(pengguna.getIdPengguna());

        baBatalServices.insertBaBatal(baBatal);
        return "Data Berita Acara Pembatalan Berhasil Disimpan";
    }

    @RequestMapping(value = "/json/prosesedit", method = RequestMethod.POST)
    public @ResponseBody
    String prosesedit(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        BaBatal baBatal = new BaBatal();
        final String uraian = (String) mapdata.get("uraian");
        final String idba = (String) mapdata.get("idba");
        final String tanggal = (mapdata.get("tanggal")).toString();
        final String noBeritaAcaraFormatted = (mapdata.get("noBeritaAcaraFormatted")).toString();

        baBatal.setTanggal(SipkdHelpers.getDateFromString(tanggal));
        baBatal.setIdBa(SipkdHelpers.getIntFromString(idba));
        baBatal.setUraian(uraian);
        baBatal.setNoBaDok(noBeritaAcaraFormatted);
        baBatal.setIdEdit(pengguna.getIdPengguna());

        baBatalServices.updateBaBatal(baBatal);
        return "Data Berita Acara Pembatalan Berhasil Disimpan";
    }

    @RequestMapping(value = "/json/proseshapus", method = RequestMethod.POST)
    public @ResponseBody
    String proseshapus(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        BaBatal baBatal = new BaBatal();
        final String idba = (String) mapdata.get("idba");

        baBatal.setIdBa(SipkdHelpers.getIntFromString(idba));

        baBatalServices.deleteBaBatal(baBatal);
        return "Data Berita Acara Pembatalan Berhasil Dihapus";
    }

    @RequestMapping(value = "/json/proseshapusbymohon", method = RequestMethod.POST)
    public @ResponseBody
    String proseshapusbymohon(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        Map param = new HashMap();
        param.put("idsekolah", mapdata.get("idsekolah"));
        param.put("tahun", mapdata.get("tahun"));
        param.put("triwulan", mapdata.get("triwulan"));
        param.put("nomohon", mapdata.get("nomohon"));
        param.put("sumbdana", mapdata.get("sumbdana"));

        baBatalServices.deleteBaBatalByMohon(param);
        return "Data Berita Acara Pembatalan Berhasil Dihapus";
    }

    @RequestMapping(value = "/json/getformattednoba", method = RequestMethod.GET)
    public @ResponseBody
    String getformattednoba(final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");

        BaBatal baBatal = new BaBatal();
        final String idsekolah = request.getParameter("idsekolah");
        final String kodesumbdana = request.getParameter("kodesumbdana");
        final String tanggal = request.getParameter("tanggal");

        baBatal.setIdSekolah(SipkdHelpers.getIntFromString(idsekolah));
        baBatal.setKodeSumbdana(kodesumbdana);
        baBatal.setTanggal(SipkdHelpers.getDateFromString(tanggal));
        baBatal.setTahun(tahun);
        return baBatalServices.getFormattedNoBa(baBatal);
    }

    @RequestMapping(value = "/json/prosescetak", method = RequestMethod.GET)
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        response.setContentType("application/pdf");
        final String idsekolah = request.getParameter("idsekolah");
        final String idBa = request.getParameter("idba");
        final String kodesumbdana = request.getParameter("kodesumbdana");
        final String npsn = request.getParameter("npsn");
        final Map<String, Object> map = new HashMap<String, Object>();
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        final String pathReport = servletContext.getInitParameter("PATH_REPORT");

        map.put("SUBREPORT_DIR", pathReport);
        map.put("TAHUN", tahun);
        map.put("IDSEKOLAH", idsekolah);
        map.put("IDBATAL", idBa);
        List<Map> listhasil = cetakService.getnilaiparam(map);
        map.put("NAMA_DAERAH", listhasil.get(0).get("N_DAERAH_JUDUL"));
        map.put("NAMA_DAERAH_LOW", listhasil.get(0).get("N_DAERAH"));
        map.put("NO_PERDA", listhasil.get(0).get("I_PERDA_NO"));
        map.put("THN_PERDA", listhasil.get(0).get("C_PERDA_TAHUN"));
        map.put("TGL_PERDA", listhasil.get(0).get("C_PERDA_TGL"));
        map.put("NAMA_KOTA", listhasil.get(0).get("N_KOTA"));
        map.put("PERATURAN_1", listhasil.get(0).get("E_PERATURAN_SPD1"));
        map.put("PERATURAN_2", listhasil.get(0).get("E_PERATURAN_SPD2"));
        map.put("PERATURAN_3", listhasil.get(0).get("E_PERATURAN_SPD3"));
        map.put("PERATURAN_4", listhasil.get(0).get("E_PERATURAN_SPD4"));
        map.put("PERATURAN_5", listhasil.get(0).get("E_PERATURAN_SPD5"));
        map.put("PERATURAN_6", listhasil.get(0).get("E_PERATURAN_SPD6"));
        map.put("PERATURAN_7", listhasil.get(0).get("E_PERATURAN_SPD7"));
        if (kodesumbdana.equals("1")) {
            map.put("pathreport", pathReport + "/Report_BA_Batal_Transfer_BOS.jasper");
            map.put("filename", "Laporan-Berita-Acara-Batal-BOS-" + tahun + "_" + npsn + "_" + idBa + ".pdf");
        } else {
            map.put("pathreport", pathReport + "/Report_BA_Batal_Transfer_BOP.jasper");
            map.put("filename", "Laporan-Berita-Acara-Batal-BOP-" + tahun + "_" + npsn + "_" + idBa + ".pdf");
        }

        printReportToPdfStream(response, map);
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }

}
