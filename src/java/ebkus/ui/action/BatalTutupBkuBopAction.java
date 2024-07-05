package ebkus.ui.action;

import ebkus.model.BukuKasUmum;
import ebkus.model.Pengguna;
import ebkus.model.BatalTutupBku;
import ebkus.services.BatalTutupBkuBopServices;
import ebkus.util.BigDecimalPropertyEditor;
import ebkus.util.SipkdHelpers;
import ebkus.util.SqlDatePropertyEditor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.jxls.area.Area;
import org.jxls.builder.AreaBuilder;
import org.jxls.builder.xls.XlsCommentAreaBuilder;
import org.jxls.common.CellRef;
import org.jxls.common.Context;
import org.jxls.transform.poi.PoiContext;
import org.jxls.transform.poi.PoiTransformer;
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
@RequestMapping("/bataltutupbkubop")
public class BatalTutupBkuBopAction {

    private static final Logger log = LoggerFactory.getLogger(BatalTutupBkuBopAction.class);

    @Autowired
    BatalTutupBkuBopServices tutupBkuServices;

    @Autowired
    ServletContext servletContext;

    @Autowired
    DataSource dataSource;

    @RequestMapping(value = "/indextutupbku", method = RequestMethod.GET)
    public ModelAndView addbku(final BukuKasUmum bku, final HttpServletRequest request, Model model) {

        return new ModelAndView("bataltutupbku/indextutupbkubop", "refcetak", bku);
    }

    @RequestMapping(value = "/json/setComboTriwulan", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> setComboBulan(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idsekolah = request.getParameter("idsekolah");
        //final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna"); 
        final Map<String, Object> mapData = new HashMap<String, Object>(1);
        final Map< String, Object> param = new HashMap<String, Object>(2);
        param.put("tahun", tahunAnggaran);
        param.put("idsekolah", idsekolah);
        //param.put("kodewilproses", kodewilproses);

        mapData.put("aData", tutupBkuServices.getTriwulanBKU(param));

        return mapData;
    }

    @RequestMapping(value = "/json/gridbku", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> gridbku(final HttpServletRequest request) {
        final String idsekolah = request.getParameter("idsekolah");
        final String tahunAnggaran = request.getParameter("tahun");
        final String triwulan = request.getParameter("triwulan");
        //final String idkegiatan = request.getParameter("idkegiatan");
        //final String jenislaporan = request.getParameter("jenislaporan");
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        /*
         String kodewilproses = "0";
         if (idskpd.equals("761")) {
         if (pengguna.getKodeGrup().equals("11")) {
         kodewilproses = request.getParameter("kodewilproses");
         } else {
         kodewilproses = pengguna.getKodeProses();
         }
         }
         */
        final Map< String, Object> param = new HashMap<String, Object>(2);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");

        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        param.put("tahun", tahunAnggaran);
        param.put("triwulan", triwulan);
        //param.put("idkegiatan", idkegiatan);
        param.put("idsekolah", idsekolah);
        //param.put("kodewilproses", kodewilproses);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        final int banyak = tutupBkuServices.getBanyakListBku(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", tutupBkuServices.getListBku(param));
        /*
         if ("1".equals(jenislaporan)) { // BKU Pengeluaran
         final int banyak = laporancetakbkuService.getCountListBkuPengeluaran(param);
         mapData.put("sEcho", request.getParameter("sEcho"));
         mapData.put("iTotalRecords", banyak);
         mapData.put("iTotalDisplayRecords", banyak);
         mapData.put("aaData", laporancetakbkuService.getListBkuPengeluaran(param));

         } else if ("2".equals(jenislaporan)) { // BKU Per Kegiatan
         final int banyak = laporancetakbkuService.getCountListBkuPerKegiatan(param);
         mapData.put("sEcho", request.getParameter("sEcho"));
         mapData.put("iTotalRecords", banyak);
         mapData.put("iTotalDisplayRecords", banyak);
         mapData.put("aaData", laporancetakbkuService.getListBkuPerKegiatan(param));
         }*/

        return mapData;
    }

    @RequestMapping(value = "/panelsimpanpop", method = RequestMethod.GET)
    public ModelAndView tutupbkupop(final HttpServletResponse response, final BatalTutupBku tutupbku, final HttpServletRequest request, Model model) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");

        return new ModelAndView("bataltutupbku/panelsimpanpopbop", "refbku", tutupbku);
    }

    @RequestMapping(value = "/json/getBendahara", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getBendahara(final HttpServletRequest request) {
        final String idsekolah = request.getParameter("idsekolah");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");

        final Map< String, Object> param = new HashMap<String, Object>(2);
        param.put("idsekolah", idsekolah);
        param.put("tahun", tahun);

        final Map<String, Object> mapData = new HashMap<String, Object>(2);
        mapData.put("aData", tutupBkuServices.getBendahara(param));
        return mapData;

    }

    @RequestMapping(value = "/json/getNilaiKas ", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getvalidasi(final HttpServletRequest request) {
        final String idsekolah = request.getParameter("idsekolah");
        final String triwulan = request.getParameter("triwulan");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        //final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        //String kodewilproses="0";

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("idsekolah", idsekolah);
        param.put("tahun", tahun);
        param.put("triwulan", triwulan);
        //param.put("kodeWilProses", kodewilproses);

        final Map<String, Object> mapData = new HashMap<String, Object>(1);
        mapData.put("aData", tutupBkuServices.getNilaiKas(param));
        return mapData;

    }

    @RequestMapping(value = "/json/getNilaiSaldo ", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getsaldo(final HttpServletRequest request) {
        final String idsekolah = request.getParameter("idsekolah");
        final String triwulan = request.getParameter("triwulan");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        //final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        //String kodewilproses="0";

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("idsekolah", idsekolah);
        param.put("tahun", tahun);
        param.put("triwulan", triwulan);
        //param.put("kodeWilProses", kodewilproses);

        final Map<String, Object> mapData = new HashMap<String, Object>(2);
        mapData.put("aData", tutupBkuServices.getNilaiSaldo(param));
        return mapData;

    }

    @RequestMapping(value = "/json/simpan", method = RequestMethod.POST)
    public @ResponseBody
    String prosessimpan2(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {

        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");

        final String idsekolah = (String) mapdata.get("idsekolah");

        final String kodetomboltutupbuku = (String) mapdata.get("kodetombol");
        log.debug("anitaikan kode tombol == "+kodetomboltutupbuku);

        final String triwulan = (String) mapdata.get("triwulan");
        //final String tglpenutupan = (String) mapdata.get("tglpenutupan");
        final String nrkpa = (String) mapdata.get("nrkpa");
        final String nippa = (String) mapdata.get("nippa");
        final String namapa = (String) mapdata.get("namapa");
        final String nrkbend = (String) mapdata.get("nrkbend");
        final String nipbend = (String) mapdata.get("nipbend");
        final String namabend = (String) mapdata.get("namabend");
        final String kasterimalalu = mapdata.get("kasterimalalu").toString();
        final String kaskeluarlalu = mapdata.get("kaskeluarlalu").toString();
        final String kasterima = mapdata.get("kasterima").toString();
        final String kaskeluar = mapdata.get("kaskeluar").toString();
        final String kassaatini = mapdata.get("kassaatini").toString();
        final String saldotunai = mapdata.get("saldotunai").toString();
        final String saldobank = mapdata.get("saldobank").toString();
        final String saldolain = mapdata.get("saldolain").toString();

        //log.debug("kasterima int===== "+mapdata.get("kasterima"));
        //log.debug("kasterima string===== "+kasterima);
        final BatalTutupBku batalTutupBku = new BatalTutupBku();

        batalTutupBku.setIdEntry(pengguna.getIdPengguna());
        batalTutupBku.setTahun(tahun);
        batalTutupBku.setIdsekolah(SipkdHelpers.getIntFromString(idsekolah));
        batalTutupBku.setTriwulan(triwulan);
        //batalTutupBku.setTglPenutupan(tglpenutupan);
        batalTutupBku.setNrkPA(nrkpa);
        batalTutupBku.setNipPA(nippa);
        batalTutupBku.setNamaPA(namapa);
        batalTutupBku.setNrkBendahara(nrkbend);
        batalTutupBku.setNipBendahara(nipbend);
        batalTutupBku.setNamaBendahara(namabend);
        batalTutupBku.setKasTerimaLalu(SipkdHelpers.getBigDecimalFromString(kasterimalalu));
        batalTutupBku.setKasKeluarLalu(SipkdHelpers.getBigDecimalFromString(kaskeluarlalu));
        batalTutupBku.setKasTerima(SipkdHelpers.getBigDecimalFromString(kasterima));
        batalTutupBku.setKasKeluar(SipkdHelpers.getBigDecimalFromString(kaskeluar));
        batalTutupBku.setKasSaatIni(SipkdHelpers.getBigDecimalFromString(kassaatini));
        batalTutupBku.setSaldoTunai(SipkdHelpers.getBigDecimalFromString(saldotunai));
        batalTutupBku.setSaldoBank(SipkdHelpers.getBigDecimalFromString(saldobank));
        batalTutupBku.setSaldoLain(SipkdHelpers.getBigDecimalFromString(saldolain));

        //tutupbku.setKodeWilProses(kodewilproses);
        if (tutupBkuServices.getBanyakDataBku(batalTutupBku) == 0) { // dicek jika di bku pengeluaran tidak ada data dengan parameter idskpd, tahun, bulan
            batalTutupBku.setKodeNihil("1");//kode nihil 1
        } else {
            batalTutupBku.setKodeNihil("0");//kode nihil 0
        }

        batalTutupBku.setKdTombolTutupBuku(kodetomboltutupbuku);

        tutupBkuServices.insertTutupBku(batalTutupBku, kodetomboltutupbuku);

        return "Data Batal Tutup BKU Pengeluaran Berhasil Disimpan";
    }

    @RequestMapping(value = "/xls/bkuxls", method = RequestMethod.GET)
    public void xlsbku(final HttpServletRequest request, final HttpServletResponse response) throws FileNotFoundException, IOException, InvalidFormatException {
        //final String pathXls = servletContext.getInitParameter("PATH_XLS");
        response.setHeader("Content-disposition", "attachment; filename=BKU_SEKOLAH.xls");
        response.setContentType("application/vnd.ms-excel");
        String tahunAnggaran = request.getParameter("tahun");
        String idsekolah = request.getParameter("idsekolah");
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        //log.debug(" =============== idskpd ============= " + idskpd);
        String triwulan = request.getParameter("triwulan");
        HashMap<String, Object> param = new HashMap<String, Object>(9);
        param.put("offset", 0);
        //param.put("kodeakun", kodeakun);
        //param.put("namaakun", namaakun);
        param.put("tahun", tahunAnggaran);
        param.put("triwulan", triwulan);
        //param.put("kodewilproses", kodewilproses);
        param.put("idsekolah", SipkdHelpers.getIntFromString(idsekolah));
        int banyak = tutupBkuServices.getBanyakListXlsBku(param);
        param.put("limit", banyak);
        param.put("iSortCol_0", 1);
        param.put("sSortDir_0", "ASC");
        //InputStream is = servletContext.getResourceAsStream(pathXls+"/bku.xls");
        InputStream is = servletContext.getResourceAsStream("/WEB-INF/xls/lap_bku_pengeluaran.xls");
        //log.debug(" =============== tes path ============= "+is.read());
        ServletOutputStream out = response.getOutputStream();
        Workbook workbook = WorkbookFactory.create(is);
        PoiTransformer transformer = PoiTransformer.createTransformer(workbook);
        log.debug(" =============== " + transformer.getWorkbook().getSheetName(0));
// Transformer transformer = TransformerFactory.createTransformer(is, out);
        // XlsArea xlsArea = new XlsArea("Template!A1", transformer);
        AreaBuilder areaBuilder = new XlsCommentAreaBuilder(transformer);
        log.debug(" =============== " + areaBuilder.build());
        List<Area> xlsAreaList = areaBuilder.build();
// getting the main area from the list
        Area xlsArea = xlsAreaList.get(0);
// creating a new PoiContext and setting our sample employees data into it under "employees" key
        Context context = new PoiContext();
        context.putVar("datas", tutupBkuServices.getListXlsBku(param));
        xlsArea.applyAt(new CellRef("Template!A1"), context);
        xlsArea.processFormulas();
        workbook.write(out);
        is.close();
        out.close();
    }

    @RequestMapping(value = "/json/prosescetak", method = RequestMethod.GET)
    public void prosescetak(HttpServletRequest request, HttpServletResponse response) throws JRException, IOException, SQLException {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        response.setContentType("application/pdf");
        //final String jenislaporan = request.getParameter("jenislaporan");
        final String tahunAnggaran = request.getParameter("tahun");
        final String idsekolah = request.getParameter("idsekolah");
        //final String tgl1 = request.getParameter("tgl1");
        //final String tgl2 = request.getParameter("tgl2");
        //final String idkegiatan = request.getParameter("idkegiatan");
        final String triwulan = request.getParameter("triwulan");

        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
//        String kodewilproses = "0";
//        if (idskpd.equals("761")) {
//            if (pengguna.getKodeGrup().equals("11")) {
//                kodewilproses = request.getParameter("kodewilproses");
//            } else {
//                kodewilproses = pengguna.getKodeProses();
//            }
//        }

        try {
            final Connection jdbcConnection = dataSource.getConnection();
            final Map<String, Object> map = new HashMap<String, Object>();

            final String pathReport = servletContext.getInitParameter("PATH_REPORT");

            map.put("SUBREPORT_DIR", pathReport);
            map.put("TAHUN", tahunAnggaran);
            map.put("TRIWULAN", triwulan);
            map.put("IDSEKOLAH", idsekolah);
//            map.put("ID_SKPD", idskpd);
//            map.put("TGL_AKHIR", tgl2);
//            map.put("IDKEGIATAN", idkegiatan);
//            map.put("BULAN", bulan);
//            map.put("KODE_WIL_SP2DPROSES", kodewilproses);
//
//            List<Map> listhasil = laporancetakbkuService.getnilaiparam(map);
//            map.put("NAMA_DAERAH", listhasil.get(0).get("N_DAERAH_JUDUL"));
//            map.put("NAMA_DAERAH_LOW", listhasil.get(0).get("N_DAERAH"));
//            map.put("NO_PERDA", listhasil.get(0).get("I_PERDA_NO"));
//            map.put("THN_PERDA", listhasil.get(0).get("C_PERDA_TAHUN"));
//            map.put("TGL_PERDA", listhasil.get(0).get("C_PERDA_TGL"));
//            map.put("NAMA_KOTA", listhasil.get(0).get("N_KOTA"));
//
//            log.debug("jenis laporan === " + jenislaporan);

//            if ("1".equals(jenislaporan)) { // Bku Pengeluaran
//                log.debug("jenis " + jenislaporan);
//                JasperPrint jasperPrint;
//                if (idskpd.equals("761")) {
            JasperPrint jasperPrint = JasperFillManager.fillReport(pathReport + "/Report_BKU-Sekolah_BOP.jasper", map, jdbcConnection);
//                } else {
//                    jasperPrint = JasperFillManager.fillReport(pathReport + "/Report_BKU-Pengeluaran.jasper", map, jdbcConnection);
//                }

            final String filename = tahunAnggaran + "-" + "BKU-Pengeluaran-" + triwulan + ".pdf";
            response.setHeader("Content-disposition", "attachment; filename=" + filename);
            response.setContentType("application/pdf");
            ServletOutputStream output = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, output);
            output.close();

//            } else if ("2".equals(jenislaporan)) { // BKU Per Kegiatan (untuk sekarang tidak pernah dipanggil karena per kegiatan tidak bisa cetak report)
//
//                JasperPrint jasperPrint = JasperFillManager.fillReport(pathReport + "/Report_Realisasi_BKU_PerKegiatan.jasper", map, jdbcConnection);
//                final String filename = tahunAnggaran + "-" + "REALISASI_BKU_PERKEGIATAN" + "-" + idkegiatan + ".pdf";
//                response.setHeader("Content-disposition", "attachment; filename=" + filename);
//                response.setContentType("application/pdf");
//                ServletOutputStream output = response.getOutputStream();
//                JasperExportManager.exportReportToPdfStream(jasperPrint, output);
//                output.close();
//
//            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }

}
