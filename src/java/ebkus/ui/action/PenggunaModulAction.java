package ebkus.ui.action;

import ebkus.model.Pengguna;
import ebkus.model.PenggunaModul;
import ebkus.services.PenggunaModulServices;
import ebkus.util.BigDecimalPropertyEditor;
import ebkus.util.SipkdHelpers;
import ebkus.util.SqlDatePropertyEditor;
import java.math.BigDecimal;
import java.security.Principal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
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

/**
 *
 * @author Zainab
 */
@Controller
@RequestMapping("/modulpengguna")
public class PenggunaModulAction {

    private static final Logger log = LoggerFactory.getLogger(PenggunaModulAction.class);

    @Autowired
    ServletContext servletContext;

    @Autowired
    PenggunaModulServices modulServices;

//    @Autowired
//    ListSkpdServices skpdServices;
//
//     @Autowired
//    ListSekolahServices sekolahServices;
    @RequestMapping(method = RequestMethod.GET)
    public String index(final Principal principal, final HttpServletRequest req) {
        return "penggunamodul/index";
    }

    @RequestMapping(value = "/json/listmodul", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listskpdjson(final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String idpengguna = request.getParameter("idpengguna");
         final String kodeotor = request.getParameter("kodeotor");
  
        param.put("idpengguna", idpengguna);
        param.put("kodeotor",kodeotor);
        
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final long banyak = modulServices.getBanyakListModul(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", modulServices.getListModul(param));
        return mapData;
    }

    @RequestMapping(value = "/json/simpanpenggunamodul", method = RequestMethod.POST)
    public @ResponseBody
    String simpanskpdbas(@RequestBody List<Map<String, String>> listmapdata, final HttpServletRequest request) {
        final Timestamp tglSkrg = new Timestamp(System.currentTimeMillis());

        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        PenggunaModul pgnmdl = new PenggunaModul();
        Integer i = 0;
        //param.put(listmapdata);
        //modulServices.deletePenggunaModul(pgnmdl);
        for (Map<String, String> mapdata : listmapdata) {
            log.debug(" mapdata " + mapdata.toString());
            //PenggunaModul pgnmdl = new PenggunaModul();
            pgnmdl.setTglEntry(tglSkrg);
            pgnmdl.setIdEntry(pengguna.getIdPengguna());
            pgnmdl.setIdPengguna(SipkdHelpers.getIntFromString(mapdata.get("idPengguna")));
            pgnmdl.setIdModul(SipkdHelpers.getIntFromString(mapdata.get("idModul")));
            //pgunmod.setIdSkpd(SipkdHelpers.getIntFromString(mapdata.get("idskpd")));
            //pgunmod.setTahunAkhir("2090");
            //log.debug("TESTING == "+skpdbas.getIdSkpd());
            log.debug("IdBas == " + pgnmdl.getIdModul());
            log.debug("nama modul == " + pgnmdl.getNamaModul());
            log.debug("IdBasInduk == " + pgnmdl.getIdPengguna());
            if (i == 0) {
                modulServices.deletePenggunaModul(pgnmdl);
            }
            log.debug("iiii == " + i);
            i++;
            modulServices.insertPenggunaModul(pgnmdl);
            //skpdbas = null;
        }

        return "Data Modul Pengguna Berhasil Disimpan";
    }

//     @RequestMapping(value = "/tambahpengguna", method = RequestMethod.GET)
//    public ModelAndView tambah(final Principal principal, final HttpServletRequest req) {
//        // '0-INITIALISASI ; 88-ADMIN REFERENSI ; 99- ADMIN USERID; 1-RKA ; 2-DPA ; 3-DPPA ; 41-STS KPKD ; 42-KONSOLIDASI STS  ; 5-SPD ; 6-SPP; 7 SPM ; 8-SP2D ; 9-SPJ ; 10-LPJ SKPD PPKD; 11-LPK BPKD ;  ; 20-MONITORING'
//        req.setAttribute("listkodegrup", penggunaServices.getlistKodeGroup());
//        req.setAttribute("listkodeotor", penggunaServices.getlistKodeOtoritas());
//        final User user = new User();
//
//        return new ModelAndView("useradm/tambahuser", "progcmd", user);
//
//    }
//    @RequestMapping(value = "/simpanuser", method = RequestMethod.POST)
//    public String prosessimpan(@Valid @ModelAttribute("progcmd") User user, BindingResult result, final HttpServletRequest request, final RedirectAttributes redirectAttributes) {
//        final StringBuilder sburl = new StringBuilder("redirect:/useradm");
//        /* if (result.hasErrors()) {
//         request.setAttribute("listkodegrup", userAdministrationServices.getlistKodeGroup());
//         request.setAttribute("kodeWilayahSp2d", userAdministrationServices.getKodeSp2dProses());
//         return "/useradm/tambahuser";
//         }
//         if(listnourut.get.get("I_NOURUT".toString().equals(lampAsetTetap.getNoUrut())){
//         ObjectError objError = new ObjectError("noUrut","No Urut Sudah Ada" ;
//         result.addError(objError);
//         return "/lampiran/asettetap/updateasettetap";
//         }*/
//
//            final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
//            user.setIdEntry(pengguna.getIdPengguna());
//            user.setIdEdit(pengguna.getIdPengguna());
//            //log.debug("idd " + user.getId());
//            penggunaServices.insertUser(user);
//
//        //return "redirect:/useradm";
//        redirectAttributes.addFlashAttribute("pesan", new StringBuilder("Data ")
//                .append(" berhasil disimpan ")
//                .toString());
//        return sburl.toString();
//    }
//
//    @RequestMapping(value = "/ubahpengguna/{id}", method = RequestMethod.GET)
//    public ModelAndView ubah(@PathVariable Integer id, final HttpServletRequest req) {
//        req.setAttribute("listkodegrup", penggunaServices.getlistKodeGroup());
//        req.setAttribute("listkodeotor", penggunaServices.getlistKodeOtoritas());
//
//        final User user = penggunaServices.getPenggunaById(id);
//        final Skpd skpd = skpdServices.getSkpdById(user.getIdSkpd());
//         final Sekolah school = sekolahServices.getSekolahById(user.getIdSekolah());
//         req.setAttribute("skpd", skpd);
//          log.debug("sekolah"+school.getNpsn()+"/"+school.getNamaSekolahPendek());
//         req.setAttribute("sekolah", school);
//        // log.debug(" ################# "+user.getSkpd().getNamaSkpd());
//        return new ModelAndView("useradm/updateuser", "progcmd", user);
//
//    }
//
//    @RequestMapping(value = "/updateuser", method = RequestMethod.POST)
//    public String prosesupdate(@Valid @ModelAttribute("progcmd") User user, BindingResult result, final HttpServletRequest request, final RedirectAttributes redirectAttributes) {
//        final StringBuilder sburl = new StringBuilder("redirect:/useradm");
//        if (result.hasErrors()) {
//
//            return "/useradm/updateuser";
//        } else {
//            final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
//            user.setIdEntry(pengguna.getIdPengguna());
//            user.setIdEdit(pengguna.getIdPengguna());
//            log.debug("penggg"+pengguna.getIdPengguna());
//            penggunaServices.updateUser(user);
//        }
//        //return "redirect:/useradm";
//                redirectAttributes.addFlashAttribute("pesan", new StringBuilder("Data ")
//                .append(" berhasil disimpan ")
//                .toString());
//        return sburl.toString();
//    }
//
//    @RequestMapping(value = "/delpengguna/{id}", method = RequestMethod.GET)
//    public ModelAndView hapus(@PathVariable Integer id, final HttpServletRequest req) {
//       req.setAttribute("listkodegrup", penggunaServices.getlistKodeGroup());
//        req.setAttribute("listkodeotor", penggunaServices.getlistKodeOtoritas());
//        final User user = penggunaServices.getPenggunaById(id);
//
//        final Skpd skpd = skpdServices.getSkpdById(user.getIdSkpd());
//         final Sekolah school = sekolahServices.getSekolahById(user.getIdSekolah());
//         req.setAttribute("skpd", skpd);
//          log.debug("sekolah"+school.getNpsn()+"/"+school.getNamaSekolahPendek());
//         req.setAttribute("sekolah", school);
//        return new ModelAndView("useradm/deleteuser", "progcmd", user);
//
//    }
//
//    @RequestMapping(value = "/deleteuser", method = RequestMethod.POST)
//    public String prosesdelete(@Valid @ModelAttribute("progcmd") User user, BindingResult result, final HttpServletRequest request) {
//        penggunaServices.deleteUser(user);
//        return "redirect:/useradm";
//    }
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }

}
