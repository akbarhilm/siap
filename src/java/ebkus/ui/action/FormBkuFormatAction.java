package ebkus.ui.action;

import ebkus.model.FormBkuBos;
import ebkus.model.FormBkuFormat;
import ebkus.model.Pengguna;
import ebkus.services.FormBkuFormatServices;
import ebkus.util.BigDecimalPropertyEditor;
import ebkus.util.PrintReportTemplate;
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Anita
 */
@Controller
@RequestMapping("/formbkuformat")
public class FormBkuFormatAction extends PrintReportTemplate {

    private static final Logger log = LoggerFactory.getLogger(FormBkuFormatAction.class);
    @Autowired
    FormBkuFormatServices cetakService;

    @Autowired
    ServletContext servletContext;

    @RequestMapping(value = "/indexlaporanmonitoringbop", method = RequestMethod.GET)
    public ModelAndView index(final FormBkuBos bku, final HttpServletRequest request, Model model) {
        return new ModelAndView("formbkuformat/laporanmonitoringbop", "refcetak", bku);
    }

    @RequestMapping(value = "/indexlaporanformat", method = RequestMethod.GET)
    public ModelAndView indexlaporanformat(final FormBkuFormat bku, final HttpServletRequest request, Model model) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        return new ModelAndView("formbkuformat/formbkuformatbop", "refcetak", bku);
    }

    @RequestMapping(value = "/indexlaporanformatsudin", method = RequestMethod.GET)
    public ModelAndView indexlaporanformatsudin(final FormBkuFormat bku, final HttpServletRequest request, Model model) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        return new ModelAndView("formbkuformat/formbkuformatbopsudin", "refcetak", bku);
    }

    @RequestMapping(value = "/listskpd", method = RequestMethod.GET)
    public String indexlist(final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return "cetak/listskpd";
    }

    @RequestMapping(value = "/json/prosescetak", method = RequestMethod.GET)
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        response.setContentType("application/pdf");
        final String idsekolah = request.getParameter("idsekolah");
        final String jenislaporan = request.getParameter("jenislaporan");
        final String triwulan = request.getParameter("triwulan");
        final String tanggal = request.getParameter("tanggal");
        final String akun = request.getParameter("akun");
        //final String idkegiatan = request.getParameter("idkegiatan");
        final String nosurat = request.getParameter("nosurat");
        //final String bulan = request.getParameter("bulan");
        final String semester = request.getParameter("semester");
        final Map<String, Object> map = new HashMap<String, Object>();
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String pathReport = servletContext.getInitParameter("PATH_REPORT");

        map.put("SUBREPORT_DIR", pathReport);
        map.put("TAHUN", tahunAnggaran);
        map.put("IDSEKOLAH", idsekolah);
        //map.put("IDKEGIATAN", idkegiatan);
        map.put("TRIWULAN", triwulan);
        map.put("TANGGAL", tanggal);
        map.put("NO_SURAT", nosurat);
        map.put("KODEPAJAK", jenislaporan);
        map.put("KODEREK", akun);
        //map.put("BULAN", bulan);
        map.put("SEMESTER", semester);
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
        if ("1".equals(jenislaporan)) {
            log.debug("jenis laporan = 1");
            map.put("pathreport", pathReport + "/Report_BOP_Format1A.jasper");
            map.put("filename", tahunAnggaran + "-Report_BOP_Format1A.pdf");
        } else if ("2".equals(jenislaporan)) {
            log.debug("jenis laporan = 2");
            map.put("pathreport", pathReport + "/Report_BOP_Format1B.jasper");
            map.put("filename", tahunAnggaran + "-Report_BOP_Format1B.pdf");
        } else if ("3".equals(jenislaporan)) { // PAJAK (PJK)
            log.debug("jenis laporan = 3");
            map.put("pathreport", pathReport + "/Report_BOP_Format1C.jasper");
            map.put("filename", tahunAnggaran + "-Report_BOP_Format1C.pdf");
        } else if ("4".equals(jenislaporan)) { // Saldo Kas
            log.debug("jenis laporan = 4");
            map.put("pathreport", pathReport + "/Report_BOP_Format1D.jasper");
            map.put("filename", tahunAnggaran + "-Report_BOP_Format1D.pdf");
        } else {// if ("5".equals(jenislaporan)) { // Surat Pertanggungjawaban
            log.debug("jenis laporan = 5");
            map.put("pathreport", pathReport + "/Report_BOP_Format1E.jasper");
            map.put("filename", tahunAnggaran + "-Report_BOP_Format1E.pdf");
        }
        printReportToPdfStream(response, map);
        log.debug("berhasil cetak");
    }

    @RequestMapping(value = "/json/prosescetaksudin", method = RequestMethod.GET)
    public void processRequestMon(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        response.setContentType("application/pdf");
        final String idsekolah = request.getParameter("idsekolah");
        final String jenislaporan = request.getParameter("jenislaporan");
        final String triwulan = request.getParameter("triwulan");
        final String tanggal = request.getParameter("tanggal");
        final String bulan = request.getParameter("bulan");
        final String akunbelanja = request.getParameter("akunbelanja");
        final String jenjang = request.getParameter("jenjang");
        final String idskpd = request.getParameter("idskpd");
        final String kodewil = request.getParameter("kodewil");
        final String nipkasi = request.getParameter("nipSisdik");
        final String namakasi = request.getParameter("namaSisdik");
        final Map<String, Object> map = new HashMap<String, Object>();
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String pathReport = servletContext.getInitParameter("PATH_REPORT");

        map.put("SUBREPORT_DIR", pathReport);
        map.put("TAHUN", tahunAnggaran);
        map.put("IDSEKOLAH", idsekolah);
        map.put("TANGGAL", tanggal);
        map.put("TRIWULAN", triwulan);
        map.put("BULAN", bulan);
        map.put("KODEREK", akunbelanja);
        map.put("JENJANG", jenjang);
        map.put("IDSKPD", idskpd);
        map.put("KECAMATAN", kodewil);
        map.put("NIP_KASI", nipkasi);
        map.put("NAMA_KASI", namakasi);
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
        if ("2".equals(jenislaporan)) {
            log.debug("jenis laporan = sudin 2");
            map.put("pathreport", pathReport + "/Report_BOP_Format2.jasper");
            map.put("filename", tahunAnggaran + "-Report_BOP_Format2.pdf");
        } else if ("3".equals(jenislaporan)) {
            log.debug("jenis laporan = sudin 3");
            map.put("pathreport", pathReport + "/Report_BOP_Format3.jasper");
            map.put("filename", tahunAnggaran + "-Report_BOP_Format3.pdf");
        } else if ("4".equals(jenislaporan)) {
            log.debug("jenis laporan = sudin 4");
            map.put("pathreport", pathReport + "/Report_BOP_Format4.jasper");
            map.put("filename", tahunAnggaran + "-Report_BOP_Format4.pdf");
        }
        printReportToPdfStream(response, map);
        log.debug("berhasil cetak report sudin");
    }

    @RequestMapping(value = "/json/getSaldoAwal", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getSaldoAwal(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String bulan = request.getParameter("bulan");
        final String idsekolah = request.getParameter("idsekolah");
        final String jenis = request.getParameter("jenis");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("bulan", bulan);
        param.put("idsekolah", idsekolah);
        param.put("jenis", jenis);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        if ("7".equals(jenis)) { // Panjar
            mapData.put("aData", cetakService.getSaldoAwalPanjar(param));
        } else if ("8".equals(jenis)) { // Bank
            mapData.put("aData", cetakService.getSaldoAwalBank(param));
        } else if ("9".equals(jenis)) { // Tunai
            mapData.put("aData", cetakService.getSaldoAwalTunai(param));
        } else { // jenis pajak P1-P6
            mapData.put("aData", cetakService.getSaldoAwalPajak(param));
        }

        return mapData;
    }

    @RequestMapping(value = "/json/getAkunBelanja", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getAkunBelanja(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idsekolah = request.getParameter("idsekolah");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("idsekolah", idsekolah);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        mapData.put("aData", cetakService.getAkunBelanja(param));

        return mapData;
    }

    @RequestMapping(value = "/json/getKegiatan", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getKegiatan(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idsekolah = request.getParameter("idsekolah");
        final String triwulan = request.getParameter("triwulan");

        final Map< String, Object> param = new HashMap<String, Object>(4);
        param.put("tahun", tahunAnggaran);
        param.put("idsekolah", idsekolah);
        param.put("triwulan", triwulan);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        mapData.put("aData", cetakService.getKegiatan(param));

        return mapData;
    }

    @RequestMapping(value = "/json/getWilayah", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getWilayah(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        mapData.put("aData", cetakService.getWilayah(param));

        return mapData;
    }

    @RequestMapping(value = "/json/setTriwulan", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> triwulan(final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        //final String kodewilayah = pengguna.getKodeProses();

        final String idsekolah = request.getParameter("idsekolah");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        //param.put("wilayah", kodewilayah);
        param.put("idsekolah", idsekolah);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        mapData.put("aData", cetakService.getTriwulan(param));
        return mapData;
    }

    @RequestMapping(value = "/json/cekRekap", method = RequestMethod.GET)
    public @ResponseBody
    Integer cekRekap(final HttpServletRequest request) {
        final String idsekolah = request.getParameter("idsekolah");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        final Map< String, Object> param = new HashMap<String, Object>(2);
        param.put("idsekolah", idsekolah);
        param.put("tahun", tahun);
        return cetakService.cekRekap(param);
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

        final long banyak = cetakService.getBanyakListCetakSetor(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", cetakService.getListCetakSetor(param));

        return mapData;
    }

    @RequestMapping(value = "/json/getIdSkpd", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getIdSkpd(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String nrkPptk = request.getParameter("nrkPptk");
        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("nrkPptk", nrkPptk);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        mapData.put("aData", cetakService.getIdSkpd(param));
        return mapData;
    }

    @RequestMapping(value = "/json/getKecamatan", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getKecamatan(final HttpServletRequest request) {
        final String idskpd = request.getParameter("idskpd");
        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("idskpd", idskpd);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        mapData.put("aData", cetakService.getKecamatan(param));
        return mapData;
    }

    @RequestMapping(value = "/json/getSisdik", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getSisdik(final HttpServletRequest request) {
        final String kecamatan = request.getParameter("kecamatan");
        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("kecamatan", kecamatan);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        mapData.put("aData", cetakService.getSisdik(param));
        return mapData;
    }

    @RequestMapping(value = "/json/updateSisdik", method = RequestMethod.POST)
    public @ResponseBody
    String updateSisdik(@RequestBody Map<String, String> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final Map< String, Object> param = new HashMap<String, Object>();
        final String idpengguna = mapdata.get("idpengguna");
        final String nipSisdik = mapdata.get("nipSisdik");
        final String namaSisdik = mapdata.get("namaSisdik");
        final String kecamatan = mapdata.get("kecamatan");
        param.put("idpengguna", idpengguna);
        param.put("nipSisdik", nipSisdik);
        param.put("namaSisdik", namaSisdik);
        param.put("kecamatan", kecamatan);
        //param.put("idEntry", pengguna.getIdPengguna());
        cetakService.updateSisdik(param);
        return "Data Sisdik berhasil disimpan";
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }

}
