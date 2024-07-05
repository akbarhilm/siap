package ebkus.ui.action;

import ebkus.model.PaguTalangan;
import ebkus.model.Pengguna;
import ebkus.model.WSTalangan;
import ebkus.services.TalanganServices;
import ebkus.util.BigDecimalPropertyEditor;
import ebkus.util.SipkdHelpers;
import ebkus.util.SqlDatePropertyEditor;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.catalina.util.Base64;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.json.JSONObject;
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
@RequestMapping("/danatalangan")
public class TalanganAction {

    private static final Logger log = LoggerFactory.getLogger(TalanganAction.class);
    @Autowired
    ServletContext servletContext;
    @Autowired
    TalanganServices talanganServices;

    @RequestMapping(value = "/json/getpegawai", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    Map<String, String> getpegawai(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        log.debug("why a ");
        log.debug("why a ");
        log.debug("why a ");
        log.debug("why a ");
        log.debug("why a ");
        try {
            InputStream inputStream = servletContext.getResourceAsStream("/WEB-INF/txt/simpeg.txt");
            String resourceUrl = SipkdHelpers.readFromInputStream(inputStream);
            String urlParameter = "?nrk=" + mapdata.get("nrk");

            String url = (resourceUrl.split("\\|")[0] + urlParameter);
            String username = resourceUrl.split("\\|")[1];
            String password = resourceUrl.split("\\|")[2];
            log.debug("why b1 " + urlParameter + " AAAAAAA");
            HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
            log.debug("why b2 ");
            con.setDoOutput(true);
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", "Basic " + Base64.encode((username + ":" + password).getBytes()));

            log.debug("why b3 ");
            byte[] output;
            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            log.debug("why b4 ");

            String c;
            StringBuilder sb = new StringBuilder();
            log.debug("why c " + reader.readLine());
            log.debug("why c " + reader.readLine());
            log.debug("why c " + reader.readLine());
            log.debug("why c " + reader.readLine());
            log.debug("why c " + reader.readLine());
            while ((c = reader.readLine()) != null) {
                if (!c.contains("]") && !c.contains("[")) {
                    sb.append(c);
                }
            }
            JSONObject json = new JSONObject(sb.toString());
            Iterator<String> nameItr = json.keys();
            Map<String, String> mapData = new HashMap<String, String>();
            while (nameItr.hasNext()) {
                String name = nameItr.next();
                mapData.put(name, json.getString(name));
            }
//            String[] splited = sb.toString().split(":");
//            final Map<String, Object> mapData = new HashMap<String, Object>();
//            mapData.put(splited[1].split("\"")[1], splited[2].split("\"")[1]);
//            mapData.put(splited[2].split("\"")[3], splited[3].split("\"")[1]);
//            mapData.put(splited[3].split("\"")[3], splited[4].split("\"")[1]);
//            mapData.put(splited[4].split("\"")[3], splited[5].split("\"")[1]);
//            mapData.put(splited[5].split("\"")[3], splited[6].split("\"")[1]);
            reader.close();
            con.disconnect();

            return mapData;
        } catch (Exception ex) {
            log.debug("ERROR - " + ex.getMessage());
        }
        return null;
    }

    @RequestMapping(value = "/indexdanatalangan", method = RequestMethod.GET)
    public ModelAndView index(final PaguTalangan talangan, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        return new ModelAndView("talangan/indextalangan", "reftalangan", talangan);
    }

    @RequestMapping(value = "/laporandanatalangan", method = RequestMethod.GET)
    public ModelAndView laporandanatalangan(final WSTalangan talangan, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        return new ModelAndView("talangan/monitoringtalangan", "reftalangan", talangan);
    }

    @RequestMapping(value = "/adddanatalangan", method = RequestMethod.GET)
    public ModelAndView addbku(final PaguTalangan talangan, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        return new ModelAndView("talangan/addtalangan", "reftalangan", talangan);
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
        final String tahun = request.getParameter("tahun");
        final String triwulan = request.getParameter("triwulan");
        final String tipe = request.getParameter("tipe");
        log.debug("TIPE " + tipe);

        param.put("idskpd", idskpd);
        param.put("tahun", tahun);
        param.put("triwulan", triwulan);
        param.put("tipe", tipe);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        final long banyak = talanganServices.getBanyakListIndex(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", talanganServices.getListIndex(param));

        return mapData;
    }

    @RequestMapping(value = "/json/listindexmonitoring", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listindexmonitoring(final HttpServletRequest request) {

        final Map< String, Object> param = new HashMap<String, Object>();
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");

        final String tahun = request.getParameter("tahun");
        final String triwulan = request.getParameter("triwulan");
        final String kodeotoritas = request.getParameter("kodeotoritas");
        final String idskpd = request.getParameter("idskpd");
        final String npsn = request.getParameter("npsn");
        final String tipe = request.getParameter("tipe");
        log.debug("TIPE " + tipe);

        param.put("tahun", tahun);
        param.put("triwulan", triwulan);
        param.put("idskpd", idskpd);
        param.put("kodeotoritas", kodeotoritas);
        param.put("npsn", npsn);
        param.put("kodesumbdana", tipe);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        final long banyak = talanganServices.getBanyakListIndexWS(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", talanganServices.getListIndexWS(param));

        return mapData;
    }

    @RequestMapping(value = "/json/getTotal", method = RequestMethod.GET)
    public @ResponseBody
    BigDecimal getTotal(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");
        final String triwulan = request.getParameter("triwulan");
        final String tipe = (String) request.getParameter("tipe");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
        param.put("triwulan", triwulan);
        param.put("tipe", tipe);

        return talanganServices.getNilaiIndex(param);
    }

    @RequestMapping(value = "/json/getTotalWS", method = RequestMethod.GET)
    public @ResponseBody
    BigDecimal getTotalWS(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String triwulan = request.getParameter("triwulan");
        final String idskpd = request.getParameter("idskpd");
        final String kodeotoritas = request.getParameter("kodeotoritas");
        final String npsn = request.getParameter("npsn");
        final String tipe = (String) request.getParameter("tipe");

        final Map< String, Object> param = new HashMap<String, Object>();
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
        param.put("kodeotoritas", kodeotoritas);
        param.put("npsn", npsn);
        param.put("triwulan", triwulan);
        param.put("kodesumbdana", tipe);

        return talanganServices.getNilaiIndexWS(param);
    }

    @RequestMapping(value = "/editdanatalangan/", method = RequestMethod.GET)
    public ModelAndView edittalangan(final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = request.getParameter("tahun");
        final String idtalangan = request.getParameter("idtalangan");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("idtalangan", idtalangan);
        param.put("tahun", tahun);

        final PaguTalangan talangan = talanganServices.getEditPaguTalangan(param);

        return new ModelAndView("talangan/edittalangan", "reftalangan", talangan);
    }

    @RequestMapping(value = "/json/prosesupdatetalangan", method = RequestMethod.POST)
    public @ResponseBody
    String prosesupdatetalangan(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        final String bulan = (String) mapdata.get("bulan");
        final String id = (String) mapdata.get("id");
        final String idsekolah = (String) mapdata.get("idsekolah");
        final String idskpd = (String) mapdata.get("idskpd");
        final String tanggal = (String) mapdata.get("tgldok");
        final String kodesumbdana = (String) mapdata.get("kodesumbdana");
        final String triwulan = (String) mapdata.get("triwulan");
        log.debug("PLIS JALAN " + triwulan);
        final String idmcb = (String) mapdata.get("idmcb");
        final String nrk = (String) mapdata.get("nrk");
        final String namapptk = (String) mapdata.get("namapptk");
        final String nippptk = (String) mapdata.get("nippptk");
        final String uraian = (String) mapdata.get("uraian");
        final String nilai = (String) String.valueOf(mapdata.get("nilai"));
        log.debug("AAAA " + mapdata.get("nilai").getClass());
        PaguTalangan talangan = new PaguTalangan();
        talangan.setId(SipkdHelpers.getIntFromString(id));
        talangan.setTahun(tahun);
        talangan.setBulanTagihan(bulan);
        talangan.setIdsekolah(SipkdHelpers.getIntFromString(idsekolah));
        talangan.setIdskpd(SipkdHelpers.getIntFromString(idskpd));
        talangan.setTanggalMohon(SipkdHelpers.getDateFromString(tanggal));
        talangan.setKodeSumbdana(kodesumbdana);
        talangan.setNrk(nrk);
        talangan.setNamaPptk(namapptk);
        talangan.setTriwulan(triwulan);
        talangan.setNipPptk(nippptk);
        talangan.setUraian(uraian);
        talangan.setIdmcb(SipkdHelpers.getIntFromString(idmcb));
        talangan.setDanaTalangan(SipkdHelpers.getBigDecimalFromString(nilai));
        talangan.setIdEdit(pengguna.getIdPengguna());
        talangan.setTglEdit(new Timestamp(System.currentTimeMillis()));

        log.debug("TOSTRING " + talangan.toString());
        talanganServices.updatePaguTalanganById(talangan);

        return "Dana Talangan Berhasil Diubah";
    }

    @RequestMapping(value = "/hapusdanatalangan/", method = RequestMethod.GET)
    public ModelAndView hapusdanatalangan(final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = request.getParameter("tahun");
        final String idtalangan = request.getParameter("idtalangan");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("idtalangan", idtalangan);
        param.put("tahun", tahun);

        final PaguTalangan talangan = talanganServices.getEditPaguTalangan(param);

        return new ModelAndView("talangan/deletetalangan", "reftalangan", talangan);
    }

    @RequestMapping(value = "/arsipdanatalangan/", method = RequestMethod.GET)
    public ModelAndView arsipdanatalangan(final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = request.getParameter("tahun");
        final String idtalangan = request.getParameter("idtalangan");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("idtalangan", idtalangan);
        param.put("tahun", tahun);

        final PaguTalangan talangan = talanganServices.getEditPaguTalangan(param);

        return new ModelAndView("talangan/arsiptalangan", "reftalangan", talangan);
    }

    @RequestMapping(value = "/json/prosesdeletebyid", method = RequestMethod.POST)
    public @ResponseBody
    String prosesdeletebyid(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");

        final String id = (String) mapdata.get("id");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("id", id);

        talanganServices.deleteById(param);

        return "Dana Talangan Berhasil Dihapus";
    }

    @RequestMapping(value = "/json/prosessimpantalangan", method = RequestMethod.POST)
    public @ResponseBody
    String prosessimpantalangan(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        final String bulan = (String) mapdata.get("bulan");
        final String idsekolah = (String) mapdata.get("idsekolah");
        final String idskpd = (String) mapdata.get("idskpd");
        final String tanggal = (String) mapdata.get("tgldok");
        final String kodesumbdana = (String) mapdata.get("kodesumbdana");
        final String triwulan = (String) mapdata.get("triwulan");
        log.debug("PLIS JALAN " + triwulan);
        final String idmcb = (String) mapdata.get("idmcb");
        final String nrk = (String) mapdata.get("nrk");
        final String namapptk = (String) mapdata.get("namapptk");
        final String nippptk = (String) mapdata.get("nippptk");
        final String uraian = (String) mapdata.get("uraian");
        final String nilai = (String) String.valueOf(mapdata.get("nilai"));
        log.debug("AAAA " + mapdata.get("nilai").getClass());
        PaguTalangan talangan = new PaguTalangan();
        talangan.setTahun(tahun);
        talangan.setBulanTagihan(bulan);
        talangan.setIdsekolah(SipkdHelpers.getIntFromString(idsekolah));
        talangan.setIdskpd(SipkdHelpers.getIntFromString(idskpd));
        talangan.setTanggalMohon(SipkdHelpers.getDateFromString(tanggal));
        talangan.setKodeSumbdana(kodesumbdana);
        talangan.setNrk(nrk);
        talangan.setNamaPptk(namapptk);
        talangan.setTriwulan(triwulan);
        talangan.setNipPptk(nippptk);
        talangan.setUraian(uraian);
        talangan.setIdmcb(SipkdHelpers.getIntFromString(idmcb));
        talangan.setDanaTalangan(SipkdHelpers.getBigDecimalFromString(nilai));
        talangan.setIdEntry(pengguna.getIdPengguna());
        talangan.setTglEntry(new Timestamp(System.currentTimeMillis()));

        log.debug("TOSTRING " + talangan.toString());
        talanganServices.insertPaguTalangan(talangan);

        return "Dana Talangan Berhasil Disimpan";
    }

    @RequestMapping(value = "/json/getMaxTriwulan", method = RequestMethod.GET)
    public @ResponseBody
    Integer getMaxTriwulan(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");
        final String kodeotoritas = request.getParameter("kodeotoritas");
        final String npsn = request.getParameter("npsn");
        final String tipe = request.getParameter("tipe");

        final Map< String, Object> param = new HashMap<String, Object>(5);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
        param.put("kodeotoritas", kodeotoritas);
        param.put("npsn", npsn);
        param.put("tipe", tipe);

        return talanganServices.getMaxTriwulan(param);
    }

    @RequestMapping(value = "/json/getMcb", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getMcb(final HttpServletRequest request) {
        final String idsekolah = request.getParameter("idsekolah");

        final Map< String, Object> param = new HashMap<String, Object>();
        param.put("idsekolah", idsekolah);
        final Map<String, Object> mapData = new HashMap<String, Object>();
        mapData.put("aData", talanganServices.getMcb(param));
        log.debug("AM " + talanganServices.getMcb(param));
        return mapData;
    }

    @RequestMapping(value = "/json/getMaxStatus", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getMaxStatus(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idskpd = (String) request.getSession().getAttribute("idSkpd");
        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        mapData.put("aData", talanganServices.getMaxTutup(param));
        return mapData;
    }

    @RequestMapping(value = "/xls/cetaktalangan", method = RequestMethod.GET)
    public void cetaktalangan(final HttpServletRequest request, final HttpServletResponse response) throws FileNotFoundException, IOException, InvalidFormatException {
        response.setHeader("Content-disposition", "attachment; filename=data-danatalangan.xls");
        response.setContentType("application/vnd.ms-excel");
        final String tahunAnggaran = request.getParameter("tahun");
        final String idskpd = request.getParameter("idskpd");
        final String triwulan = request.getParameter("triwulan");
        final String tipe = request.getParameter("tipe");
        final Map< String, Object> param = new HashMap<String, Object>(6);
        param.put("offset", 0);

        param.put("tahun", tahunAnggaran);
        param.put("idskpd", idskpd);
        param.put("triwulan", triwulan);
        param.put("tipe", tipe);
        final int banyak = talanganServices.getBanyakListIndex(param);
        param.put("limit", banyak);
        param.put("iSortCol_0", 1);
        param.put("sSortDir_0", "ASC");

        InputStream is = servletContext.getResourceAsStream("/WEB-INF/xls/data-danatalangan.xls");
        ServletOutputStream out = response.getOutputStream();
        Workbook workbook = WorkbookFactory.create(is);
        PoiTransformer transformer = PoiTransformer.createTransformer(workbook);
        AreaBuilder areaBuilder = new XlsCommentAreaBuilder(transformer);
        List<Area> xlsAreaList = areaBuilder.build();
        Area xlsArea = xlsAreaList.get(0);
        Context context = new PoiContext();
        context.putVar("datas", talanganServices.getListIndex(param));
        xlsArea.applyAt(new CellRef("Template!A1"), context);
        xlsArea.processFormulas();
        workbook.write(out);
        is.close();
        out.close();

    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }

}
