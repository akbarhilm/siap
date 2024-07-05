package ebkus.ui.action;

import ebkus.model.Pengguna;
import ebkus.model.Sekolah;
import ebkus.model.Skpd;
import ebkus.model.User;
import ebkus.services.ListSekolahServices;
import ebkus.services.ListSkpdServices;
import ebkus.services.PenggunaServices;
import ebkus.util.BigDecimalPropertyEditor;
import ebkus.util.SqlDatePropertyEditor;
import java.math.BigDecimal;
import java.security.Principal;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Zainab
 */
@Controller
@RequestMapping("/useradm")
public class PenggunaAction {

    private static final Logger log = LoggerFactory.getLogger(PenggunaAction.class);

    @Autowired
    ServletContext servletContext;

    @Autowired
    PenggunaServices penggunaServices;

    @Autowired
    ListSkpdServices skpdServices;

    @Autowired
    ListSekolahServices sekolahServices;

    @RequestMapping(method = RequestMethod.GET)
    public String index(final Principal principal, final HttpServletRequest req) {
        return "useradm/index";
    }

    @RequestMapping(value = "/json/listpengguna", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listskpdjson(final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>();
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String userName = request.getParameter("userName");
        final String kodeotoritas = request.getParameter("kodeotoritas");
        final String idskpd = String.valueOf(pengguna.getIdSkpd());
        String idsekolah = "";
                try{
                    if(pengguna.getSekolah().getIdSekolah()!=null){
                       idsekolah = String.valueOf(pengguna.getSekolah().getIdSekolah());
                    }
                            
                }catch(NullPointerException e){
                    
                }
        log.debug(">>>>>>>>>>>>>>>>> " + idskpd + " " + idsekolah);
        param.put("kodeotoritas", kodeotoritas);
        param.put("idsekolah", idsekolah);
        param.put("idskpd", idskpd);
        param.put("userName", userName);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final long banyak = penggunaServices.getBanyakListUser(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", penggunaServices.getListUser(param));
        return mapData;
    }

    
     @RequestMapping(value = "/json/maxdepag", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> depagmak(final HttpServletRequest request) {
       
        
        
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
       
        mapData.put("aaData", penggunaServices.getMaxDepag());
        return mapData;
    }
    
    @RequestMapping(value = "/tambahpengguna", method = RequestMethod.GET)
    public ModelAndView tambah(final Principal principal, final HttpServletRequest req) {
        final Pengguna pengguna = (Pengguna) req.getSession().getAttribute("pengguna");
        // '0-INITIALISASI ; 88-ADMIN REFERENSI ; 99- ADMIN USERID; 1-RKA ; 2-DPA ; 3-DPPA ; 41-STS KPKD ; 42-KONSOLIDASI STS  ; 5-SPD ; 6-SPP; 7 SPM ; 8-SP2D ; 9-SPJ ; 10-LPJ SKPD PPKD; 11-LPK BPKD ;  ; 20-MONITORING'
        req.setAttribute("listkodegrup", penggunaServices.getlistKodeGroup());
        req.setAttribute("listkodeotor", penggunaServices.getlistKodeOtoritas(pengguna));
        req.setAttribute("kodeotor", pengguna.getKodeOtoritas());
        final User user = new User();

        return new ModelAndView("useradm/tambahuser", "progcmd", user);

    }

    @RequestMapping(value = "/simpanuser", method = RequestMethod.POST)
    public String prosessimpan(@Valid @ModelAttribute("progcmd") User user, BindingResult result, final HttpServletRequest request, final RedirectAttributes redirectAttributes) {
        final StringBuilder sburl = new StringBuilder("redirect:/useradm");
        /* if (result.hasErrors()) {
         request.setAttribute("listkodegrup", userAdministrationServices.getlistKodeGroup());
         request.setAttribute("kodeWilayahSp2d", userAdministrationServices.getKodeSp2dProses());
         return "/useradm/tambahuser";
         }
         if(listnourut.get.get("I_NOURUT".toString().equals(lampAsetTetap.getNoUrut())){
         ObjectError objError = new ObjectError("noUrut","No Urut Sudah Ada" ;
         result.addError(objError);
         return "/lampiran/asettetap/updateasettetap";
         }*/

        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        user.setIdEntry(pengguna.getIdPengguna());
        user.setIdEdit(pengguna.getIdPengguna());
        //log.debug("idd " + user.getId());
        penggunaServices.insertUser(user);

        //return "redirect:/useradm";
        redirectAttributes.addFlashAttribute("pesan", new StringBuilder("Data ")
                .append(" berhasil disimpan ")
                .toString());
        return sburl.toString();
    }

    @RequestMapping(value = "/ubahpengguna/{id}", method = RequestMethod.GET)
    public ModelAndView ubah(@PathVariable Integer id, final HttpServletRequest req) {
        final Pengguna pengguna = (Pengguna) req.getSession().getAttribute("pengguna");
        req.setAttribute("listkodegrup", penggunaServices.getlistKodeGroup());
        req.setAttribute("listkodeotor", penggunaServices.getlistKodeOtoritas(pengguna));

        final User user = penggunaServices.getPenggunaById(id);
        final Skpd skpd = skpdServices.getSkpdById(user.getIdSkpd());
        final Sekolah school = sekolahServices.getSekolahById(user.getIdSekolah());
        req.setAttribute("skpd", skpd);
      //  log.debug("sekolah" + school.getNpsn() + "/" + school.getNamaSekolahPendek());
        req.setAttribute("sekolah", school);
        // log.debug(" ################# "+user.getSkpd().getNamaSkpd());
        return new ModelAndView("useradm/updateuser", "progcmd", user);

    }

    @RequestMapping(value = "/updateuser", method = RequestMethod.POST)
    public String prosesupdate(@Valid @ModelAttribute("progcmd") User user, BindingResult result, final HttpServletRequest request, final RedirectAttributes redirectAttributes) {
        final StringBuilder sburl = new StringBuilder("redirect:/useradm");
        if (result.hasErrors()) {

            return "/useradm/updateuser";
        } else {
            final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
            user.setIdEntry(pengguna.getIdPengguna());
            user.setIdEdit(pengguna.getIdPengguna());
            log.debug("penggg" + pengguna.getIdPengguna());
            penggunaServices.updateUser(user);
        }
        //return "redirect:/useradm";
        redirectAttributes.addFlashAttribute("pesan", new StringBuilder("Data ")
                .append(" berhasil disimpan ")
                .toString());
        return sburl.toString();
    }

    @RequestMapping(value = "/delpengguna/{id}", method = RequestMethod.GET)
    public ModelAndView hapus(@PathVariable Integer id, final HttpServletRequest req) {
        final Pengguna pengguna = (Pengguna) req.getSession().getAttribute("pengguna");
        req.setAttribute("listkodegrup", penggunaServices.getlistKodeGroup());
        req.setAttribute("listkodeotor", penggunaServices.getlistKodeOtoritas(pengguna));
        final User user = penggunaServices.getPenggunaById(id);

        final Skpd skpd = skpdServices.getSkpdById(user.getIdSkpd());
        final Sekolah school = sekolahServices.getSekolahById(user.getIdSekolah());
        req.setAttribute("skpd", skpd);
       // log.debug("sekolah" + school.getNpsn() + "/" + school.getNamaSekolahPendek());
        req.setAttribute("sekolah", school);
        return new ModelAndView("useradm/deleteuser", "progcmd", user);

    }

    @RequestMapping(value = "/deleteuser", method = RequestMethod.POST)
    public String prosesdelete(@Valid @ModelAttribute("progcmd") User user, BindingResult result, final HttpServletRequest request) {
        penggunaServices.deleteUser(user);
        return "redirect:/useradm";
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }

}
