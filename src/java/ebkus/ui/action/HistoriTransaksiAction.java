package ebkus.ui.action;

import ebkus.model.Pengguna;
import ebkus.model.Sekolah;
import ebkus.services.FormBkuServices;
import ebkus.services.HistoriTransaksiServices;
import ebkus.services.ListSekolahServices;
import ebkus.services.SekolahServices;
import ebkus.util.BigDecimalPropertyEditor;
import ebkus.util.PrintReportTemplate;
import ebkus.util.SipkdHelpers;
import ebkus.util.SqlDatePropertyEditor;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.Principal;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.catalina.util.Base64;
import org.json.JSONObject;
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
@RequestMapping("/bkustrans")
public class HistoriTransaksiAction extends PrintReportTemplate {

    private static final Logger log = LoggerFactory.getLogger(HistoriTransaksiAction.class);

    @Autowired
    ServletContext servletContext;

    @Autowired
    SekolahServices skulServices;

    @Autowired
    ListSekolahServices listServices;
    @Autowired

    FormBkuServices cetakService;
    @Autowired
    HistoriTransaksiServices histrxServices;

    private final RestTemplate rest;
    private final HttpHeaders headers;
    private HttpStatus status;

    public HistoriTransaksiAction() {
        this.rest = new RestTemplate();
        this.headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "*/*");
        // headers.add("Authorization","Basic c2lhcGRldjpzaWFwZGV2");
    }

    @RequestMapping(value = "/bopmutasiall", method = RequestMethod.GET)
    public ModelAndView bopmutasiall(final Principal principal, final HttpServletRequest req) {
        // public String indexbop(final Principal principal, final HttpServletRequest req) {

        final Pengguna pengguna = (Pengguna) req.getSession().getAttribute("pengguna");
        Sekolah sekolah = new Sekolah();
        final Map< String, Object> param = new HashMap<String, Object>(3);
        if (pengguna.getKodeOtoritas().equals("0")) {
            req.setAttribute("rek", "bop");
            req.setAttribute("plt", 0);
        } else {
            param.put("id", String.valueOf(pengguna.getSekolah().getIdSekolah()));
            param.put("idsekolah", String.valueOf(pengguna.getSekolah().getIdSekolah()));
            param.put("idpengguna", pengguna.getIdPengguna());

            sekolah = skulServices.getSekolah(param);
            final Integer plt = listServices.getBanyakAllSekolahPlt(param);

            req.setAttribute("plt", plt);
            req.setAttribute("rek", "bop");
            //buat plt
        }
        //return "historitransaksi/indexbop";
        return new ModelAndView("historitransaksi/mutasitransall", "progcmd", sekolah);

    }

    @RequestMapping(value = "/bosmutasiall", method = RequestMethod.GET)
    public ModelAndView bosmutasiall(final Principal principal, final HttpServletRequest req) {
        // public String indexbop(final Principal principal, final HttpServletRequest req) {

        final Pengguna pengguna = (Pengguna) req.getSession().getAttribute("pengguna");
        Sekolah sekolah = new Sekolah();
        final Map< String, Object> param = new HashMap<String, Object>(3);
        if (pengguna.getKodeOtoritas().equals("0")) {
            req.setAttribute("rek", "bos");
            req.setAttribute("plt", 0);
        } else {
            param.put("id", String.valueOf(pengguna.getSekolah().getIdSekolah()));
            param.put("idsekolah", String.valueOf(pengguna.getSekolah().getIdSekolah()));
            param.put("idpengguna", pengguna.getIdPengguna());

            sekolah = skulServices.getSekolah(param);
            final Integer plt = listServices.getBanyakAllSekolahPlt(param);

            req.setAttribute("plt", plt);
            req.setAttribute("rek", "bos");
            //buat plt
        }
        //return "historitransaksi/indexbop";
        return new ModelAndView("historitransaksi/mutasitransall", "progcmd", sekolah);

    }

    @RequestMapping(value = "/transbop", method = RequestMethod.GET)
    public ModelAndView indexbop(final Principal principal, final HttpServletRequest req) {
        // public String indexbop(final Principal principal, final HttpServletRequest req) {

        final Pengguna pengguna = (Pengguna) req.getSession().getAttribute("pengguna");
        Sekolah sekolah = new Sekolah();

        final Map< String, Object> param = new HashMap<String, Object>(3);
        if (pengguna.getKodeOtoritas().equals("0")) {
            req.setAttribute("rek", "bop");
            req.setAttribute("plt", 0);
        } else {
            param.put("id", String.valueOf(pengguna.getSekolah().getIdSekolah()));
            param.put("idsekolah", String.valueOf(pengguna.getSekolah().getIdSekolah()));
            param.put("idpengguna", pengguna.getIdPengguna());

            sekolah = skulServices.getSekolah(param);
            final Integer plt = listServices.getBanyakAllSekolahPlt(param);

            req.setAttribute("plt", plt);
            req.setAttribute("rek", "bop");
            //buat plt
        }
        //return "historitransaksi/indexbop";
        return new ModelAndView("historitransaksi/indextrans", "progcmd", sekolah);

    }

    @RequestMapping(value = "/transbos", method = RequestMethod.GET)
    public ModelAndView indexbos(final Principal principal, final HttpServletRequest req) {
        // public String indexbop(final Principal principal, final HttpServletRequest req) {

        final Pengguna pengguna = (Pengguna) req.getSession().getAttribute("pengguna");
        Sekolah sekolah = new Sekolah();
        final Map< String, Object> param = new HashMap<String, Object>(3);
        if (pengguna.getKodeOtoritas().equals("0")) {
            req.setAttribute("rek", "bos");
            req.setAttribute("plt", 0);
        } else {
            param.put("id", String.valueOf(pengguna.getSekolah().getIdSekolah()));
            param.put("idsekolah", String.valueOf(pengguna.getSekolah().getIdSekolah()));
            param.put("idpengguna", pengguna.getIdPengguna());

            sekolah = skulServices.getSekolah(param);
            final Integer plt = listServices.getBanyakAllSekolahPlt(param);

            req.setAttribute("plt", plt);
            req.setAttribute("rek", "bos");
            //buat plt
        }
        //return "historitransaksi/indexbop";
        return new ModelAndView("historitransaksi/indextrans", "progcmd", sekolah);

    }

    @RequestMapping(value = "/json/listtrans", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listtrx(final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>(6);

        final String rekbos = request.getParameter("rekeningbos");
        final String rekbop = request.getParameter("rekeningbop");
        final String tipe = request.getParameter("tipe");

        if (tipe.equals("BOP")) {
            param.put("rekening", rekbop);
        } else {
            param.put("rekening", rekbos);
        }

        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final long banyak = histrxServices.getBanyakTransaksi(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", histrxServices.getTransaksi(param));

        return mapData;
    }

    @RequestMapping(value = "/json/listtransall", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listtrxall(final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String rekbos = request.getParameter("rekeningbos");
        final String rekbop = request.getParameter("rekeningbop");
        final String tipe = request.getParameter("tipe");

        if (tipe.equals("BOP")) {
            param.put("rekening", rekbop);
        } else {
            param.put("rekening", rekbos);
        }

        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final long banyak = histrxServices.getBanyakTransaksiall(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", histrxServices.getTransaksiall(param));

        return mapData;
    }

    @RequestMapping(value = "/json/saldoakhir", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> saldoakhir(final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>(6);

        final String rekbos = request.getParameter("rekeningbos");
        final String rekbop = request.getParameter("rekeningbop");
        final String tipe = request.getParameter("tipe");
        param.put("tipe", tipe);
        if (tipe.equals("BOP")) {
            param.put("rekening", rekbop);
        } else {
            param.put("rekening", rekbos);
        }

        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        mapData.put("aData", histrxServices.getSaldoAkhir(param));

        return mapData;
    }

    @RequestMapping(value = "/json/saldoakhirall", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> saldoakhirall(final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>(6);

        final String rekbos = request.getParameter("rekeningbos");
        final String rekbop = request.getParameter("rekeningbop");
        final String tipe = request.getParameter("tipe");
        param.put("tipe", tipe);
        if (tipe.equals("BOP")) {
            param.put("rekening", rekbop);
        } else {
            param.put("rekening", rekbos);
        }

        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        mapData.put("aData", histrxServices.getSaldoAkhirall(param));

        return mapData;
    }

    @RequestMapping(value = "/json/getmutasiall", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    Map<String, String> getmutasiall(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>(6);

        String account = (String) mapdata.get("account");
        String date = (String) mapdata.get("date");
        //final String tipe = request.getParameter("tipe");
        param.put("account", account);
        param.put("date", date);

        log.debug("accb" + account);
        histrxServices.getMutasiAll(param);

        return null;
    }

    @RequestMapping(value = "/json/prosescetak", method = RequestMethod.GET)
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        response.setContentType("application/pdf");
        final String idsekolah = request.getParameter("idsekolah");
        final String tipe = request.getParameter("tipe");
        final String rek = request.getParameter("rekening");
        //log.debug("rekening ===== "+ rek);
        final Map<String, Object> map = new HashMap<String, Object>();
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String pathReport = servletContext.getInitParameter("PATH_REPORT");

        map.put("SUBREPORT_DIR", pathReport);
        map.put("TAHUN", tahunAnggaran);
        map.put("NOREKENING", rek);
        map.put("IDSEKOLAH", idsekolah);
        if (tipe.equals("BOS")) {
            map.put("SUMBDANA", "1");
        }
        if (tipe.equals("BOP")) {
            map.put("SUMBDANA", "2");
        }
        //map.put("TIPE", tipe);

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

        map.put("pathreport", pathReport + "/Report_MutasiRekeningKoran.jasper");

        map.put("filename", tahunAnggaran + "- Mutasi Rekening  " + tipe + ".pdf");

        printReportToPdfStream(response, map);
    }

    @RequestMapping(value = "/json/prosescetakall", method = RequestMethod.GET)
    public void processRequestall(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        response.setContentType("application/pdf");
        final String idsekolah = request.getParameter("idsekolah");
        final String tipe = request.getParameter("tipe");
        final String rek = request.getParameter("rekening");
        final Map<String, Object> map = new HashMap<String, Object>();
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String pathReport = servletContext.getInitParameter("PATH_REPORT");

        map.put("SUBREPORT_DIR", pathReport);
        map.put("NOREKENING", rek);
        map.put("IDSEKOLAH", idsekolah);
        if (tipe.equals("BOS")) {
            map.put("SUMBDANA", "1");
        }
        if (tipe.equals("BOP")) {
            map.put("SUMBDANA", "2");
        }
        //map.put("TIPE", tipe);

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

        map.put("pathreport", pathReport + "/Report_Trx_HistoriBank.jasper");

        map.put("filename", tahunAnggaran + "- Transaksi Histori Rekening Bank " + tipe + ".pdf");

        printReportToPdfStream(response, map);
    }

//    @RequestMapping(value = "/json/rekeningkoran", method = RequestMethod.POST, consumes = "application/json")
//    public @ResponseBody
//    Map<String, Object> ceknorek(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) throws IOException {
//
//        HttpEntity<Map<String, Object>> requestPostData = new HttpEntity<Map<String, Object>>(mapdata);
//        InputStream inputStream = servletContext.getResourceAsStream("/WEB-INF/txt/rekeningkoran.txt");
//        String url = SipkdHelpers.readFromInputStream(inputStream);
//        log.debug("AAAAAAAAAA____ " + url);
//        ResponseEntity<Map> response = rest.exchange(url, HttpMethod.POST, requestPostData, Map.class);
//
//        log.debug("KIRIM POST DATA -- response.getBody() ------------> " + response.getBody());
//
//        return response.getBody();
//    }
    @RequestMapping(value = "/json/rekeningkoran", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    Map<String, String> getrekor(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        // final String tipe = mapdata.get("tipe")
//        final Map<String, Object> mapped = new HashMap<String, Object>();
//        mapped.put("gagal", "gagal");

        try {
            InputStream inputStream = servletContext.getResourceAsStream("/WEB-INF/txt/rekeningkoran.txt");
            String resourceUrl = SipkdHelpers.readFromInputStream(inputStream);
            String urlParameter = "?account=" + mapdata.get("account");

            String url = (resourceUrl.split("\\|")[0] + urlParameter);
            String username = resourceUrl.split("\\|")[1];
            String password = resourceUrl.split("\\|")[2];
            log.debug("urlll " + url);
            HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
            con.setDoOutput(true);
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", "Basic " + Base64.encode((username + ":" + password).getBytes()));

            byte[] output;
            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            String c;
            StringBuilder sb = new StringBuilder();
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
//            log.debug("hasil..." + sb.toString());
//            String[] splited = sb.toString().split(":");
//
//            final Map<String, Object> mapData = new HashMap<String, Object>();
//            if (splited.length == 4) {
//                mapData.put(splited[0].split("\"")[1], splited[1].split("\"")[1]);
//                mapData.put(splited[1].split("\"")[3], splited[2].split("\"")[1]);
//                mapData.put(splited[2].split("\"")[3], splited[3].split("\"")[1]);
//            }
//            if (splited.length == 3) {
//                mapData.put(splited[0].split("\"")[1], splited[1].split("\"")[1]);
//                mapData.put(splited[1].split("\"")[3], splited[2].split("\"")[1]);
//
//            }
            reader.close();
            con.disconnect();

            return mapData;
        } catch (Exception ex) {
            log.debug("ERROR - " + ex.getMessage());
        }
        return null;
    }

    @RequestMapping(value = "/json/newrekeningkoran", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    String getnewrekeningkoran(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        try {
            InputStream inputStream = servletContext.getResourceAsStream("/WEB-INF/txt/rekeningkoran.txt");
            String resourceUrl = SipkdHelpers.readFromInputStream(inputStream);
            String urlParameter = "?account=" + mapdata.get("account");

            String url = (resourceUrl.split("\\|")[0] + urlParameter);
            String username = resourceUrl.split("\\|")[1];
            String password = resourceUrl.split("\\|")[2];
            log.debug("urlll " + url);
            HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
            con.setDoOutput(true);
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", "Basic " + Base64.encode((username + ":" + password).getBytes()));

            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            String c;
            StringBuilder sb = new StringBuilder();
            while ((c = reader.readLine()) != null) {
                sb.append(c);
            }
            log.debug("SB = " + sb.toString());
            JSONObject json = new JSONObject(sb.toString());
            int count = json.getJSONArray("mutasiList").length();
            histrxServices.insertTransaksi(json, (String) mapdata.get("account"));
            log.debug("Beres");
            reader.close();
            con.disconnect();

            return "Jumlah Transaksi = " + count;
        } catch (Exception ex) {
            log.debug("ERROR - " + ex.getMessage());
        }
        return null;
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }

}
