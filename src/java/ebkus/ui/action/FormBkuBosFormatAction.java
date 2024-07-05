package ebkus.ui.action;

import ebkus.model.FormBkuBos;
import ebkus.model.FormBkuBosFormat;
import ebkus.model.Pengguna;
import ebkus.services.FormBkuBosFormatServices;
import ebkus.util.BigDecimalPropertyEditor;
import ebkus.util.PrintReportTemplate;
import ebkus.util.SipkdHelpers;
import ebkus.util.BigDecimalPropertyEditor;
import java.math.BigDecimal;
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
@RequestMapping("/formbkubosformat")
public class FormBkuBosFormatAction extends PrintReportTemplate {

    private static final Logger log = LoggerFactory.getLogger(FormBkuBosFormatAction.class);
    @Autowired
    FormBkuBosFormatServices cetakService;

    @Autowired
    ServletContext servletContext;

    @RequestMapping(value = "/indexlaporanmonitoringbos", method = RequestMethod.GET)
    public ModelAndView index(final FormBkuBos bku, final HttpServletRequest request, Model model) {
        return new ModelAndView("formbkuformat/laporanmonitoringbos", "refcetak", bku);
    }

    @RequestMapping(value = "/indexlaporanformat", method = RequestMethod.GET)
    public ModelAndView indexlaporanformat(final FormBkuBosFormat bku, final HttpServletRequest request, Model model) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        return new ModelAndView("formbkuformat/formbkuformatbos", "refcetak", bku);
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
        final String nosurat = request.getParameter("nosurat");
        final String bulan = request.getParameter("bulan");
        final String semester = request.getParameter("semester");
        final Map<String, Object> map = new HashMap<String, Object>();
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String pathReport = servletContext.getInitParameter("PATH_REPORT");

        map.put("SUBREPORT_DIR", pathReport);
        map.put("TAHUN", tahunAnggaran);
        map.put("IDSEKOLAH", idsekolah);
        map.put("TRIWULAN", triwulan);
        map.put("TANGGAL", tanggal);
        map.put("NO_SURAT", nosurat);
        map.put("KODEPAJAK", jenislaporan);
        map.put("KODEREK", akun);
        map.put("BULAN", bulan);
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
            map.put("pathreport", pathReport + "/Report_BOS_Format-K1.jasper");
            map.put("filename", tahunAnggaran + "-Report_BOS_Format-K1.pdf");
        } else if ("2".equals(jenislaporan)) {
            log.debug("jenis laporan = 2");
            map.put("pathreport", pathReport + "/Report_BOS_Format-K2.jasper");
            map.put("filename", tahunAnggaran + "-Report_BOS_Format-K2.pdf");
        } else if ("3".equals(jenislaporan)) {
            log.debug("jenis laporan = 3");
            map.put("pathreport", pathReport + "/Report_BOS_Format-K3.jasper");
            map.put("filename", tahunAnggaran + "-Report_BOS_Format-K3.pdf");
        } else if ("4".equals(jenislaporan)) {
            log.debug("jenis laporan = 4");
            map.put("pathreport", pathReport + "/Report_BOS_Format-K4.jasper");
            map.put("filename", tahunAnggaran + "-Report_BOS_Format-K4.pdf");
        } else if ("5".equals(jenislaporan)) {
            log.debug("jenis laporan = 5");
            map.put("pathreport", pathReport + "/Report_BOS_Format-K5.jasper");
            map.put("filename", tahunAnggaran + "-Report_BOS_Format-K5.pdf");
        } else if ("6".equals(jenislaporan)) {
            log.debug("jenis laporan = 6");
            map.put("pathreport", pathReport + "/Report_BOS_Format-K6.jasper");
            map.put("filename", tahunAnggaran + "-Report_BOS_Format-K6.pdf");
        } else {
            log.debug("jenis laporan = 8");
            map.put("pathreport", pathReport + "/Report_BOS_Format-K7A.jasper");
            map.put("filename", tahunAnggaran + "-Report_BOS_Format-K7A.pdf");
        }
        printReportToPdfStream(response, map);
        log.debug("berhasil cetak report");
    }

    @RequestMapping(value = "/json/prosescetak2", method = RequestMethod.GET)
    public void processRequest2(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        response.setContentType("application/pdf");
        final String idsekolah = request.getParameter("idsekolah");
        final String jenislaporan = request.getParameter("jenislaporan");
        final String triwulan = request.getParameter("triwulan");
        final String tanggal = request.getParameter("tanggal");
        final String akun = request.getParameter("akun");
        final String nosurat = request.getParameter("nosurat");
        final String bulan = request.getParameter("bulan");
        final String semester = request.getParameter("semester");
        final BigDecimal kastunai = new BigDecimal(request.getParameter("kastunai"));
        final Map<String, Object> map = new HashMap<String, Object>();
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String pathReport = servletContext.getInitParameter("PATH_REPORT");

        map.put("SUBREPORT_DIR", pathReport);
        map.put("TAHUN", tahunAnggaran);
        map.put("IDSEKOLAH", idsekolah);
        map.put("TRIWULAN", triwulan);
        map.put("TANGGAL", tanggal);
        map.put("NO_SURAT", nosurat);
        map.put("KODEPAJAK", jenislaporan);
        map.put("KODEREK", akun);
        map.put("BULAN", bulan);
        map.put("SEMESTER", semester);
        map.put("KAS_TUNAI", kastunai);
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
        if ("7".equals(jenislaporan)) {
            log.debug("jenis laporan = 8");
            map.put("pathreport", pathReport + "/Report_BOS_Format-K7.jasper");
            map.put("filename", tahunAnggaran + "-Report_BOS_Format-K7.pdf");
        } else {
        }
        printReportToPdfStream(response, map);
        log.debug("berhasil cetak report");
    }

    @RequestMapping(value = "/json/prosescetakmon", method = RequestMethod.GET)
    public void processRequestMon(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        response.setContentType("application/pdf");
        final String idsekolah = request.getParameter("idsekolah");
        final String jenislaporan = request.getParameter("jenislaporan");
        final String triwulan = request.getParameter("triwulan");
        final String tanggal = request.getParameter("tanggal");
        final String bulan = request.getParameter("bulan");
        final String jenjang = request.getParameter("jenjang");
        final String idskpd = request.getParameter("idskpd");
        final Map<String, Object> map = new HashMap<String, Object>();
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String pathReport = servletContext.getInitParameter("PATH_REPORT");

        map.put("SUBREPORT_DIR", pathReport);
        map.put("TAHUN", tahunAnggaran);
        map.put("IDSEKOLAH", idsekolah);
        map.put("TANGGAL", tanggal);
        map.put("TRIWULAN", triwulan);
        map.put("BULAN", bulan);
        map.put("JENJANG", jenjang);
        map.put("IDSKPD", idskpd);
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
            map.put("pathreport", pathReport + "/Report_PengajuanPembayaran-BOP.jasper");
            map.put("filename", tahunAnggaran + "-Report_PengajuanPembayaran-BOP.pdf");
        } else if ("2".equals(jenislaporan)) {
            map.put("pathreport", pathReport + "/Report_BukuPembantuRincianObjekBelanja_BOP.jasper");
            map.put("filename", tahunAnggaran + "-Report_BukuPembantuRincianObjekBelanja_BOP.pdf");
        } else if ("3".equals(jenislaporan)) {
            map.put("pathreport", pathReport + "/Report_Laporan_Tahunan_Triwulan_BOP.jasper");
            map.put("filename", tahunAnggaran + "-Report_Per_Triwulan_BOP.pdf");
        } else if ("4".equals(jenislaporan)) {
            map.put("pathreport", pathReport + "/Report_Rekap_BOP.jasper");
            map.put("filename", tahunAnggaran + "-Report_Rekap_BOP.pdf");
        } else if ("5".equals(jenislaporan)) {
            map.put("pathreport", pathReport + "/Report_Monitoring_TransaksiBelanja-BOP.jasper");
            map.put("filename", tahunAnggaran + "-Report_Monitoring_Transaksi_Belanja-BOP.pdf");
        } else if ("6".equals(jenislaporan)) {
            map.put("pathreport", pathReport + "/Report_MonitoringTransfer-BOP.jasper");
            map.put("filename", tahunAnggaran + "-Report_Monitoring_Transfer-BOP.pdf");
        } else if ("7".equals(jenislaporan)) {
            map.put("pathreport", pathReport + "/Report_DaftarSekolah_TutupBKU-BOP.jasper");
            map.put("filename", tahunAnggaran + "-Report_DaftarSekolah_TutupBKU-BOP.pdf");
        } else if ("8".equals(jenislaporan)) {
            map.put("pathreport", pathReport + "/Report_Setoran_BKUS.jasper");
            map.put("filename", tahunAnggaran + "-Report_Setoran_BKUS_BOP.pdf");
        } else if ("9".equals(jenislaporan)) {
            map.put("pathreport", pathReport + "/Report_MonitoringPengeluaranTriwulan-BOP.jasper");
            map.put("filename", tahunAnggaran + "-Report_MonitoringPengeluaranTriwulan-BOP.pdf");
        }
        printReportToPdfStream(response, map);
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

    @RequestMapping(value = "/json/setComboBulan", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> setComboBulan(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idsekolah = request.getParameter("idsekolah");
        final Map<String, Object> mapData = new HashMap<String, Object>(1);
        final Map< String, Object> param = new HashMap<String, Object>(2);
        param.put("tahun", tahunAnggaran);
        param.put("idsekolah", idsekolah);
        mapData.put("aData", cetakService.getBulanBku(param));
        return mapData;
    }

    @RequestMapping(value = "/json/prosesupdateTIBulan", method = RequestMethod.POST)
    public @ResponseBody
    String prosesupdateTIBulan(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        FormBkuBosFormat formBkuBosFormat = new FormBkuBosFormat();

        final String idsekolah = (String) mapdata.get("idsekolah");
        final String bulan = (String) mapdata.get("bulan");

        formBkuBosFormat.setTahun(tahun);
        formBkuBosFormat.setBulan(bulan);
        formBkuBosFormat.setIdsekolah(SipkdHelpers.getIntFromString(idsekolah.toString()));
        formBkuBosFormat.setIdEntry(pengguna.getIdPengguna());
        cetakService.insertTIBKUBulan(formBkuBosFormat);

        return "Data Buku Kas Umum Berhasil Disimpan";
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }

}
