package ebkus.ui.action;

import ebkus.model.MCB;
import ebkus.model.Pengguna;
import ebkus.model.Sekolah;
import ebkus.services.ListSekolahServices;
import ebkus.services.SekolahServices;
import ebkus.util.BigDecimalPropertyEditor;
import ebkus.util.SipkdHelpers;
import ebkus.util.SqlDatePropertyEditor;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.apache.catalina.util.Base64;
import org.json.JSONObject;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Zainab
 */
@Controller
@RequestMapping("/sekolah")
public class SekolahAction {

    private static final Logger log = LoggerFactory.getLogger(SekolahAction.class);

    @Autowired
    ServletContext servletContext;

    @Autowired
    SekolahServices sekolahServices;

    @Autowired
    ListSekolahServices listServices;

//    @RequestMapping(value = "/indexsekolah", method = RequestMethod.GET)
//    public ModelAndView index(final HttpServletRequest request) {
//        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
//        //final String pengg = (String) request.getSession().getAttribute("pengguna");
//        //final List<Sekolah> listSekolah = pengguna.getSekolah();
//        final Map< String, Object> param = new HashMap<String, Object>(3);
//        param.put("npsn",pengguna.getNamaPengguna());
//        log.debug("a123"+pengguna.getNamaPengguna());
//        final List<Sekolah> bku = sekolahServices.getSekolah(param);
//
//        return new ModelAndView("sekolah/indexsekolah", "refbku", bku);
//    }
    @RequestMapping(value = "/indexsekolah", method = RequestMethod.GET)
    public ModelAndView index(final HttpServletRequest request) {

        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final Map< String, Object> param = new HashMap<String, Object>(6);
        param.put("id", String.valueOf(pengguna.getSekolah().getIdSekolah()));
        param.put("idsekolah", String.valueOf(pengguna.getSekolah().getIdSekolah()));
        param.put("idpengguna", pengguna.getIdPengguna());
        final Integer plt = listServices.getBanyakAllSekolahPlt(param);

        final Sekolah sekolah = sekolahServices.getSekolah(param);
        //buat plt
        if (plt > 1) {
            Sekolah sekolahplt = new Sekolah();
            return new ModelAndView("sekolah/indexsekolahplt", "progcmd", sekolahplt);
        }
        if (pengguna.getKodeOtoritas().equals("1") || pengguna.getKodeOtoritas().equals("2")) {

            return new ModelAndView("/sekolah/indexsekolah12", "progcmd", sekolah);
        } else {
            return new ModelAndView("/sekolah/indexsekolah", "progcmd", sekolah);
        }
    }

    @RequestMapping(value = "/json/getSekolah", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getSekolah(final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String id = request.getParameter("id");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("id", id);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        mapData.put("aData", sekolahServices.getSekolah(param));

        return mapData;
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

    @RequestMapping(value = "/indexmcb", method = RequestMethod.GET)
    public String indexmcb(final HttpServletRequest request) {

        return "/sekolah/indexmcb";

    }

    @RequestMapping(value = "/tambahmcb", method = RequestMethod.GET)
    public String tambahmcb(final HttpServletRequest request) {

        return "/sekolah/tambahmcb";

    }

    @RequestMapping(value = "/json/simpan", method = RequestMethod.POST)
    public @ResponseBody
    String prosessimpann(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final Integer idsekolah = SipkdHelpers.getIntFromString((String) mapdata.get("idsekolah"));
        final String nomcb = (String) mapdata.get("nomcb");
        final String namamcb = (String) mapdata.get("namamcb");

        final MCB mcb = new MCB();

        mcb.setIdSekolah(idsekolah);
        mcb.setNoMcb(nomcb);
        mcb.setNamaMcb(namamcb);
        mcb.setIdEntry(pengguna.getIdPengguna());
        mcb.setTglEntry(new Timestamp(System.currentTimeMillis()));

        sekolahServices.insertMcb(mcb);

        return "Dana Talangan Berhasil Disimpan";
    }

    @RequestMapping(value = "/editmcb/{id}", method = RequestMethod.GET)
    public ModelAndView editmcb(final HttpServletRequest request, @PathVariable Integer id) {
        final MCB mcb = sekolahServices.getMcbById(id);

        return new ModelAndView("/sekolah/editmcb", "progcmd", mcb);

    }

    @RequestMapping(value = "/updatemcb", method = RequestMethod.POST)
    public @ResponseBody
    String updatemcb(@Valid @ModelAttribute("progcmd") MCB mcb, BindingResult result, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        mcb.setIdEdit(pengguna.getIdPengguna());
        mcb.setTglEdit(new Timestamp(System.currentTimeMillis()));

        sekolahServices.updateMcb(mcb);

        return "Data Berhasil Diubah";
    }

    @RequestMapping(value = "/deletemcb/{id}", method = RequestMethod.GET)
    public String delmcb(final HttpServletRequest request, @PathVariable Integer id) {
        sekolahServices.deleteMcb(id);

        return "/sekolah/indexmcb";
    }

    @RequestMapping(value = "/json/getmcb", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getmcb(final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String id = request.getParameter("id");
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final Map< String, Object> param = new HashMap<String, Object>(3);
//       param.put("offset", offset);
//        param.put("limit", limit);
//        param.put("iSortCol_0", iSortCol_0);
//        param.put("sSortDir_0", sSortDir_0);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final Integer banyak = sekolahServices.getBanyakListMcb();

        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", sekolahServices.getListMcb());

        return mapData;
    }
//      @RequestMapping(value = "/json/prosesupdate", method = RequestMethod.POST)
//    public @ResponseBody
//    String prosesup(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
//        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
//        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
//
//        Sekolah sekolah = new Sekolah();
//
//        final String namakepsek = (String) mapdata.get("namakepsek");
//        final String nipkepsek = (String) mapdata.get("nipkepsek");
//        final String namabend = (String) mapdata.get("namabend");
//        final String nipbend = (String) mapdata.get("nipbend");
//        final String idsekolah = (String) mapdata.get("idsekolah");
//
//
//        sekolah.setIdSekolah(SipkdHelpers.getIntFromString(idsekolah.toString()));
//        sekolah.setNamaKepsek(namakepsek.toString());
//        sekolah.setNipKepsek(nipkepsek.toString());
//        sekolah.setNamaBendahara(namabend.toString());
//        sekolah.setNipBendahara(nipbend.toString());
//        sekolah.setIdEdit(pengguna.getIdPengguna());
//        sekolah.setTglEdit(new Timestamp(System.currentTimeMillis()));
//
//
//
//        sekolahServices.updateSekolah(sekolah);
//
//        return "Data Buku Kas Umum Berhasil Disimpan";
//    }

    @RequestMapping(value = "/json/prosesupdate", method = RequestMethod.POST)
    public @ResponseBody
    String uploadFileHandler(@RequestParam("namaLogo") String name,
            @RequestParam("file") MultipartFile file, @Valid @ModelAttribute("progcmd") Sekolah urusan, BindingResult result, final HttpServletRequest request, final RedirectAttributes redirectAttributes) {
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();

                // Creating the directory to store file
                final String pathReport = servletContext.getInitParameter("PATH_REPORT");

                // Create the file on server
                File serverFile = new File(pathReport
                        + File.separator + name);
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();

                log.debug("Server File Location="
                        + serverFile.getAbsolutePath());
            } catch (Exception e) {
                return "You failed to upload " + name + " => " + e.getMessage();
            }
        }
        final StringBuilder sburl = new StringBuilder("redirect:/");
        if ((!urusan.getNrkKepsek().isEmpty() && urusan.getNamaKepsek().isEmpty())
                || (!urusan.getNrkBendahara().isEmpty() && urusan.getNamaBendahara().isEmpty())
                || urusan.getNoNPWP().isEmpty() || urusan.getNamaNPWP().isEmpty()
                || urusan.getKotaNPWP().isEmpty()) {
            return "Please fill all field";
        } else if (result.hasErrors()) {
            log.debug("errrrrrr " + result);
            return "/";
        } else {
            final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
            //final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
            urusan.setIdEdit(pengguna.getIdPengguna());
            urusan.setTglEdit(new Timestamp(System.currentTimeMillis()));
            //urusan.setTahun(tahun);

            sekolahServices.updateSekolah1(urusan);
            sekolahServices.updateSekolahRKAS(urusan);
        }
        redirectAttributes.addFlashAttribute("pesan", new StringBuilder("Data ")
                .append(" berhasil disimpan ")
                .toString());

        return "Data Berhasil disimpan";

    }
//         String prosessimpan(@Valid @ModelAttribute("progcmd") Sekolah urusan, BindingResult result, final HttpServletRequest request, final RedirectAttributes redirectAttributes) {
//
//
//    }
//

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }

}
