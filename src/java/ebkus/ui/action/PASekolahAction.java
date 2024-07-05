package ebkus.ui.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ebkus.model.Pengguna;
import ebkus.model.PenggunaModul;
import ebkus.services.PASekolahServices;
import ebkus.util.BigDecimalPropertyEditor;
import ebkus.util.SipkdHelpers;
import ebkus.util.SqlDatePropertyEditor;
import java.math.BigDecimal;
import java.security.Principal;
import java.sql.Date;
import java.sql.Timestamp;
import javax.servlet.ServletContext;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import ebkus.util.SipkdHelpers;
import org.springframework.web.bind.annotation.PathVariable;
/**
 *
 * @author Zainab
 */
@Controller
@RequestMapping("/pasekolah")
public class PASekolahAction {

    private static final Logger log = LoggerFactory.getLogger(PASekolahAction.class);

    @Autowired
    ServletContext servletContext;

    @Autowired
    PASekolahServices pasServices;

//    @Autowired
//    ListSkpdServices skpdServices;
//
//     @Autowired
//    ListSekolahServices sekolahServices;
    @RequestMapping(method = RequestMethod.GET)
    public String index(final Principal principal, final HttpServletRequest req) {
        return "pasekolah/index";
    }

    @RequestMapping(value = "/json/listpasekolah", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listskpdjson(final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>(6);
 
        final String idpengguna = request.getParameter("idpengguna");
         
  
        param.put("idpengguna", idpengguna);
      
        
      
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final long banyak = pasServices.getBanyakListPASekolah(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", pasServices.getListPASekolah(param));
        return mapData;
    }

    @RequestMapping(value = "/nonaktif/{idsekolah}/{idpgn}", method = RequestMethod.GET)
     public @ResponseBody
   String nonaktif(@PathVariable String idsekolah,@PathVariable String idpgn, final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("idpengguna",idpgn);
        param.put("idsekolah", idsekolah);
        pasServices.nonAktifPaPlt(param);
        return "Kepala Sekolah Berhasil Dihapus";
    }
   @RequestMapping(value = "/json/prosessimpan", method = RequestMethod.POST)
    public @ResponseBody
    String prosessimpan(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        //final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");

        final String idpa=  (String) mapdata.get("idpengguna");
        final Integer idsekolah = (Integer) mapdata.get("idsekolah");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("idpa", idpa);
        param.put("idsekolah", idsekolah);
        param.put("pengguna", pengguna.getIdPengguna());

        pasServices.insertPASekolah(param);

        return "Data Buku Kas Umum Berhasil Dihapus";
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
