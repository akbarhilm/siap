package ebkus.ui.action;

import ebkus.model.FormBku;
import ebkus.model.FormBkuBos;
import ebkus.model.Pengguna;
import ebkus.services.FormBkuServices;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Anita
 */
@Controller
@RequestMapping("/formbku")
public class FormBkuAction extends PrintReportTemplate {

    private static final Logger log = LoggerFactory.getLogger(FormBkuAction.class);
    @Autowired
    FormBkuServices cetakService;

    @Autowired
    ServletContext servletContext;

    //@Autowired
    //DataSource dataSource;
    @RequestMapping(value = "/indexlaporan101", method = RequestMethod.GET)
    public ModelAndView add(final FormBku bku, final HttpServletRequest request, Model model) {
        return new ModelAndView("formbku/formbku10", "refcetak", bku);
    }

    @RequestMapping(value = "/indexlaporanmonitoringbop", method = RequestMethod.GET)
    public ModelAndView index(final FormBkuBos bku, final HttpServletRequest request, Model model) {
        return new ModelAndView("formbku/laporanmonitoringbop", "refcetak", bku);
    }

    @RequestMapping(value = "/indexlaporan10", method = RequestMethod.GET)
    public ModelAndView indexlaporan10(final FormBku bku, final HttpServletRequest request, Model model) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        return new ModelAndView("formbku/formbku10", "refcetak", bku);
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
        final String idkegiatan = request.getParameter("idkegiatan");
        final String nosurat = request.getParameter("nosurat");
        final String bulan = request.getParameter("bulan");
        final String semester = request.getParameter("semester");
        final Map<String, Object> map = new HashMap<String, Object>();
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String pathReport = servletContext.getInitParameter("PATH_REPORT");

        map.put("SUBREPORT_DIR", pathReport);
        map.put("TAHUN", tahunAnggaran);
        map.put("IDSEKOLAH", idsekolah);
        map.put("IDKEGIATAN", idkegiatan);
        map.put("TRIWULAN", triwulan);
        map.put("TANGGAL", tanggal);
        map.put("NO_SURAT", nosurat);
        map.put("KODEPAJAK", jenislaporan);
        map.put("KODEAKUN", akun);
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
        if ("8".equals(jenislaporan)) {
            map.put("pathreport", pathReport + "/Report_BukuKasBank.jasper");
            map.put("filename", tahunAnggaran + "-BKU Laporan Bulanan Kas Bank BOP.pdf");
        } else if ("22".equals(jenislaporan)) {
            map.put("pathreport", pathReport + "/Report_BKU-Sekolah_BOP.jasper");
            map.put("filename", tahunAnggaran + "-Laporan BKU Pengeluaran BOP.pdf");
        } else if ("PJK".equals(jenislaporan)) { // PAJAK (PJK)
            map.put("pathreport", pathReport + "/Report_Daftar_Realisasi_PP_Pajak.jasper");
            map.put("filename", tahunAnggaran + "-BKU-Laporan-Realisasi-Bulanan-Pajak BOP.pdf");
        } else if ("KKK".equals(jenislaporan)) { // Saldo Kas
            map.put("pathreport", pathReport + "/Report_KK_Kegiatan-Sekolah-BOP_Komponen.jasper");
            map.put("filename", tahunAnggaran + "-Laporan Kartu Kendali Kegiatan Sekolah Komponen BOP.pdf");
        } else if ("SPT".equals(jenislaporan)) { // Surat Pertanggungjawaban
            map.put("pathreport", pathReport + "/Report_SuratPertanggungJawaban-BOP.jasper");
            map.put("filename", tahunAnggaran + "-Report_SuratPertanggungJawaban-BOP.pdf");
        } else if ("KK".equals(jenislaporan)) {  // Kartu Kendali
            map.put("pathreport", pathReport + "/Report_KK_Kegiatan-Sekolah.jasper");
            map.put("filename", tahunAnggaran + "-Laporan Kartu Kendali Kegiatan Sekolah BOP.pdf");
        } else if ("MT".equals(jenislaporan)) {  // Monitoring Transfer
            map.put("pathreport", pathReport + "/Report_MonitoringTransfer-BOP-Sekolah.jasper");
            map.put("filename", tahunAnggaran + "-Report_MonitoringTransfer-BOP-Sekolah.pdf");
        } else if ("MT9".equals(jenislaporan)) {
            map.put("pathreport", pathReport + "/Report_MonitoringPengeluaranTriwulan-BOP.jasper");
            map.put("filename", tahunAnggaran + "-Report_MonitoringPengeluaranTriwulan-BOP_Sekolah.pdf");
        } else if ("11".equals(jenislaporan)) {
            map.put("pathreport", pathReport + "/Report_DaftarKegiatanSekolah-BOP.jasper");
            map.put("filename", tahunAnggaran + "-Report_DaftarKegiatanSekolah-BOP.pdf");
        } else if ("12".equals(jenislaporan)) {
            map.put("pathreport", pathReport + "/Report_PengajuanDanaSekolah-BOP.jasper");
            map.put("filename", tahunAnggaran + "-Report_PengajuanDanaSekolah-BOP.pdf");
        } else if ("13".equals(jenislaporan)) {
            map.put("pathreport", pathReport + "/Report_RealisasiPenerimaanPenyetoran_Pajak-BOP.jasper");
            map.put("filename", tahunAnggaran + "-Report_RealisasiPenerimaanPenyetoran_Pajak-BOP.pdf");
        } else { // Pembantu Pajak P1-P6
            map.put("pathreport", pathReport + "/Report_BukuPembantuPajak_BOP.jasper");
            map.put("filename", tahunAnggaran + "-Laporan Buku pembantu Pajak-BOP.pdf");
        }
        printReportToPdfStream(response, map);
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
        final String akunbelanja = request.getParameter("akunbelanja");
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
        map.put("KODEREK", akunbelanja);
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
        } else if ("10".equals(jenislaporan)) {
            map.put("pathreport", pathReport + "/Report_RealisasiKegiatan_SKPD-BOP.jasper");
            map.put("filename", tahunAnggaran + "-" + triwulan + "-Report_RealisasiKegiatan_SKPD-BOP.pdf");
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

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }

}
