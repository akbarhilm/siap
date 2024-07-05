package ebkus.ui.action;

import ebkus.model.Pengguna;
import ebkus.model.Token;
import ebkus.services.ReqTokenServices;
import ebkus.util.BigDecimalPropertyEditor;
import ebkus.util.SipkdHelpers;
import ebkus.util.SqlDatePropertyEditor;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.security.Principal;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/token")
public class ReqTokenAction {

    private static final Logger log = LoggerFactory.getLogger(ReqTokenAction.class);

    @Autowired
    ServletContext servletContext;

    @Autowired
    ReqTokenServices tknServices;

    private final RestTemplate rest;
    private final HttpHeaders headers;
    private HttpStatus status;

    public ReqTokenAction() {
        this.rest = new RestTemplate();
        this.headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "*/*");
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index(final Principal principal, final HttpServletRequest req) {
        return "reqtoken/token";
    }

    @RequestMapping(value = "/tokenbop", method = RequestMethod.GET)
    public ModelAndView indexbop(final Token token, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        return new ModelAndView("reqtoken/tokenbop", "refbku", token);
    }

    @RequestMapping(value = "/tokenbos", method = RequestMethod.GET)
    public ModelAndView indexbos(final Token token, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        return new ModelAndView("reqtoken/tokenbos", "refbku", token);
    }

    @RequestMapping(value = "/json/token", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    Map<String, Object> ceknorek(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) throws IOException {

        HttpEntity<Map<String, Object>> requestPostData = new HttpEntity<Map<String, Object>>(mapdata);
        InputStream inputStream = servletContext.getResourceAsStream("/WEB-INF/txt/token.txt");
        String url = SipkdHelpers.readFromInputStream(inputStream);
        log.debug("AAAAAAAAAA____ " + url);
        ResponseEntity<Map> response = rest.exchange(url, HttpMethod.POST, requestPostData, Map.class);

        log.debug("KIRIM POST DATA -- response.getBody() ------------> " + response.getBody());

        return response.getBody();
    }

    @RequestMapping(value = "/json/listToken", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listToken(final HttpServletRequest request) {

        final Map< String, Object> param = new HashMap<String, Object>();
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");

        final String idsekolah = request.getParameter("idsekolah");
        final String tahun = request.getParameter("tahun");
        final String triwulan = request.getParameter("triwulan");
        final String kodeSumbdana = request.getParameter("kodeSumbdana");

        param.put("idsekolah", idsekolah);
        param.put("tahun", tahun);
        param.put("triwulan", triwulan);
        param.put("kodeSumbdana", kodeSumbdana);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        final long banyak = tknServices.getBanyakToken(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", tknServices.getToken(param));

        return mapData;
    }

    @RequestMapping(value = "/json/getToken", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getToken(final HttpServletRequest request) {
        final String idsekolah = request.getParameter("idsekolah");
        final String noBkuMohon = request.getParameter("noBkuMohon");
        final String kodesumbdana = request.getParameter("kodesumbdana");
        log.debug("TEST 1 " + idsekolah);

        final Map< String, Object> param = new HashMap<String, Object>();
        param.put("idsekolah", idsekolah);
        param.put("noBkuMohon", noBkuMohon);
        param.put("kodesumbdana", kodesumbdana);
        log.debug("TEST 2 " + param.get("idsekolah"));
        final Map<String, Object> mapData = new HashMap<String, Object>();
        mapData.put("aData", tknServices.getTokenByBkuNo(param));
        return mapData;
    }

    @RequestMapping(value = "/json/getDate", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getDate(final HttpServletRequest request) {
        final Map<String, Object> mapData = new HashMap<String, Object>();
        mapData.put("aData", tknServices.getDate());
        return mapData;
    }

    @RequestMapping(value = "/json/getTimeLimit", method = RequestMethod.GET)
    public @ResponseBody
    Token getTimeLimit(final HttpServletRequest request) {
        final String kodesumbdana = request.getParameter("kodesumbdana");
        final String nomohon = request.getParameter("nomohon");
        final String idsekolah = request.getParameter("idsekolah");
        final String tahun = request.getParameter("tahun");
        final Map< String, Object> param = new HashMap<String, Object>(3);
        Token token = new Token();
        param.put("nomohon", nomohon);
        param.put("idsekolah", idsekolah);
        param.put("tahun", tahun);
        if (kodesumbdana.equals("1")) {
            token = tknServices.getTimeLimitBos(param);
        } else {
            token = tknServices.getTimeLimitBop(param);
        }
        if (token == null) {
            token = tknServices.getTimeLimitOn();
        }
        return token;
    }

    @RequestMapping(value = "/json/prosessimpan", method = RequestMethod.POST)
    public @ResponseBody
    String prosessimpan(@RequestBody Map<String, String> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");

        Token tkn = new Token();

        final String idsekolah = mapdata.get("idsekolah");
        final String token = mapdata.get("token");
        final String nrk = mapdata.get("nrk");
        final String requesttime = mapdata.get("requesttime");
        final String requesttimedki = mapdata.get("requesttimedki");
        final String nobkumohon = mapdata.get("nobkumohon");
        final String kodesumbdana = mapdata.get("kodesumbdana");

        tkn.setTahun(tahun);
        tkn.setIdSekolah(Integer.parseInt(idsekolah));
        tkn.setIdEntry(pengguna.getIdPengguna());
        tkn.setNrk(nrk);
        tkn.setToken(token);
        tkn.setdMohon(requesttime);
        tkn.setdMohonDki(requesttimedki);
        tkn.setNoBkuMohon(nobkumohon);
        tkn.setKodeSumbdana(kodesumbdana);

        tknServices.insertToken(tkn);

        return "Token Berhasil Disimpan";
    }

    @RequestMapping(value = "/json/deletetoken", method = RequestMethod.POST)
    public @ResponseBody
    String deletetoken(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idsekolah = (String) mapdata.get("idsekolah");
        final String token = (String) mapdata.get("token");
        final String dposting = (String) mapdata.get("dposting");
        final String noMohon = (String) mapdata.get("noMohon");
        final String kodesumbdana = (String) mapdata.get("kodesumbdana");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("token", token);
        param.put("tahun", tahun);
        param.put("dposting", dposting);
        param.put("idsekolah", idsekolah);
        param.put("kodesumbdana", kodesumbdana);
        param.put("nomohon", noMohon);
        tknServices.deleteToken(param);

        return "Token Berhasil Dihapus";
    }

    @RequestMapping(value = "/json/deletetokenmohon", method = RequestMethod.POST)
    public @ResponseBody
    void deletetokenmohon(@RequestBody List<Map<String, String>> listmapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final Map< String, Object> param = new HashMap<String, Object>();
        for (Map<String, String> mapdata : listmapdata) {
            param.put("tahun", mapdata.get("tahun"));
            param.put("idsekolah", mapdata.get("idSekolah"));
            param.put("kodesumbdana", mapdata.get("kodesumbdana"));
            param.put("nomohon", mapdata.get("noMohon"));
            tknServices.deleteTokenMohon(param);
        }
    }

    @RequestMapping(value = "/json/deletetokensemua", method = RequestMethod.POST)
    public @ResponseBody
    void deletetokensemua(@RequestBody Map<String, String> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final Map< String, Object> param = new HashMap<String, Object>();
        param.put("tahun", mapdata.get("tahun"));
        param.put("idsekolah", mapdata.get("idSekolah"));
        param.put("kodesumbdana", mapdata.get("kodesumbdana"));
        tknServices.deleteTokenSemua(param);
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }

}
