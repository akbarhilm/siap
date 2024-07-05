/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.ui.action;

import ebkus.model.Pengguna;
import ebkus.model.Rkas;
import ebkus.model.Sekolah;
import ebkus.services.RkasServices;
import ebkus.services.SekolahServices;
import ebkus.util.SipkdHelpers;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import javax.validation.Valid;
import org.apache.catalina.util.Base64;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Racka
 */
@Controller
@RequestMapping("admin/indexrkas")
public class RkasAction {

    private static final Logger log = LoggerFactory.getLogger(RkasAction.class);
    @Autowired
    RkasServices rkasServices;
    @Autowired
    SekolahServices sekolahServices;
    @Autowired
    ServletContext servletContext;
    @Autowired
    DataSource dataSource;

    private HttpHeaders headers;
    private RestTemplate rest;

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "/admin/listrkas";
    }

    @RequestMapping(value = "/json/listrkas", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listRkas(final HttpServletRequest request) {
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        final Map< String, Object> param = new HashMap<String, Object>();
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String npsnFilter = request.getParameter("npsnFilter");
        final String namaSekolahFilter = request.getParameter("namaSekolahFilter");
        final String namaPAFilter = request.getParameter("namaPAFilter");
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        param.put("tahun", tahun);
        param.put("npsnFilter", npsnFilter);
        param.put("namaSekolahFilter", namaSekolahFilter);
        param.put("namaPAFilter", namaPAFilter);
        param.put("nrk", pengguna.getNamaPengguna());
        param.put("kodeotor", pengguna.getKodeOtoritas());

        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final int banyak = rkasServices.getBanyakRkas(param);
        log.debug(banyak + " AAAAAAAAAAA");
        log.debug(rkasServices.getListRkas(param).size() + " bbbbbb");
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", rkasServices.getListRkas(param));
        return mapData;
    }

    @RequestMapping(value = "/updaterkas/{id}", method = RequestMethod.GET)
    public ModelAndView update(@PathVariable String id, final HttpServletRequest request) {
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        final Map< String, Object> param = new HashMap<String, Object>();
        param.put("tahun", tahun);
        param.put("id", id);
//        final Rkas rkas = rkasServices.getRkas(param);
//        log.debug(rkas.getNamaSekolah() + " ABCDEF");
//        log.debug(rkas.getNrkPA() + " ABCDEF");
        final Sekolah sekolah = sekolahServices.getSekolah(param);
        return new ModelAndView("/sekolah/indexsekolah", "progcmd", sekolah);

    }

    @RequestMapping(value = "/json/getpegawai", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    Map<String, String> getpegawai(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        try {
            InputStream inputStream = servletContext.getResourceAsStream("/WEB-INF/txt/simpeg.txt");
            String resourceUrl = SipkdHelpers.readFromInputStream(inputStream);
            String urlParameter = "?nrk=" + mapdata.get("nrk");

            String url = (resourceUrl.split("\\|")[0] + urlParameter);
            String username = resourceUrl.split("\\|")[1];
            String password = resourceUrl.split("\\|")[2];
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

    @RequestMapping(value = "/prosesupdate", method = RequestMethod.POST)
    public String prosesupdate(@Valid @ModelAttribute("penggunaMaster") Rkas rkas, BindingResult result, final HttpServletRequest request, final RedirectAttributes redirectAttributes) {
        System.out.println(" result.hasErrors() = " + result.hasErrors());
        final StringBuilder sburl = new StringBuilder("redirect:/admin/indexrkas");
        if (result.hasErrors()) {
            return "/admin/updaterkas";
        } else {
            final Pengguna user = (Pengguna) request.getSession().getAttribute("pengguna");
            rkas.setIdEdit(user.getIdPengguna());
            rkas.setTglEdit(new Timestamp(System.currentTimeMillis()));
            rkasServices.updateRkas(rkas);
            sekolahServices.updateSekolah2(rkas);
        }
        redirectAttributes.addFlashAttribute("pesan", "Data berhasil diupdate. ");
        return sburl.toString();
    }
}
