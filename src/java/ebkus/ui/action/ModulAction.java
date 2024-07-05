package ebkus.ui.action;

import ebkus.model.Modul;
import ebkus.model.Pengguna;
import ebkus.services.ModulAdministrationServices;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author idns
 */
@Controller
//@RequestMapping("/moduladm")
@RequestMapping("/bku/indexadmmodul")
public class ModulAction {

    private static final Logger log = LoggerFactory.getLogger(ModulAction.class);
    @Autowired
    ModulAdministrationServices modulAdministrationServices;
    @Autowired
    ServletContext servletContext;
    @Autowired
    DataSource dataSource;

    @RequestMapping(method = RequestMethod.GET)
    public String index(final Principal principal, final HttpServletRequest req) {
        return "admin/indexmodul";
    }

    /*
     @RequestMapping(value = "/indexadmmodul", method = RequestMethod.GET)
     public ModelAndView index(final Modul login, final HttpServletRequest request) {

     return new ModelAndView("moduladm/index", "progcmd", login);
     }
     */
    @RequestMapping(value = "/json/listmodul", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listmoduljson(final HttpServletRequest request, final RedirectAttributes redirectAttributes) {
        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String namaModul = request.getParameter("namaModul");
        param.put("namaModul", namaModul);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final long banyak = modulAdministrationServices.getBanyakListModul(param);
        log.debug("NNNNN " + banyak);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", modulAdministrationServices.getListModul(param));
        return mapData;
    }

    //@RequestMapping(value = "/tambahmodul", method = RequestMethod.GET)
    //public ModelAndView tambah(final Principal principal, final HttpServletRequest req) {
    @RequestMapping(value = "/tambahmodul/{idInduk}/{noModul}", method = RequestMethod.GET)
    public ModelAndView tambah(@PathVariable Integer idInduk, @PathVariable String noModul, final HttpServletRequest req) {
        final Modul modul = new Modul();
        modul.setIdInduk(idInduk);
        modul.setNoModulInduk(noModul);

        return new ModelAndView("admin/tambahmodul", "progcmd", modul);

    }

    @RequestMapping(value = "/simpanmodul", method = RequestMethod.POST)
    public String prosessimpan(@Valid @ModelAttribute("progcmd") Modul modul, BindingResult result, final HttpServletRequest request, final RedirectAttributes redirectAttributes) {
        final StringBuilder sburl = new StringBuilder("redirect:/bku/indexadmmodul");
        //return "redirect:/useradm";
        if (result.hasErrors()) {
            return "/admin/tambahmodul";
        } else {
            final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
            modul.setIdPgunRekam(pengguna.getIdPengguna());
            modul.setKodeDetail(modul.getNamaModulLink() == null || "".equals(modul.getNamaModulLink().trim()) ? "0" : "1");
            modul = modulAdministrationServices.insertModul(modul);
        }
        String msg = " berhasil disimpan. ";
        if (modul == null) {
            msg = " gagal disimpan. ";
        }
        redirectAttributes.addFlashAttribute("pesan", new StringBuilder("Data ")
                .append(msg)
                .toString());
        return sburl.toString();
    }

    @RequestMapping(value = "/ubahmodul/{id}", method = RequestMethod.GET)
    public ModelAndView ubah(@PathVariable Integer id, final HttpServletRequest req) {
        final Modul modul = modulAdministrationServices.getModulById(id);
        return new ModelAndView("admin/updatemodul", "progcmd", modul);
    }

    @RequestMapping(value = "/updatemodul", method = RequestMethod.POST)
    public String prosesupdate(@Valid @ModelAttribute("progcmd") Modul modul, BindingResult result, final HttpServletRequest request, final RedirectAttributes redirectAttributes) {
        final StringBuilder sburl = new StringBuilder("redirect:/bku/indexadmmodul");
        if (result.hasErrors()) {

            return "/admin/updatemodul";
        } else {
            final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
            modul.setIdPgunUbah(pengguna.getIdPengguna());
            modul.setKodeDetail(modul.getNamaModulLink() == null || "".equals(modul.getNamaModulLink().trim()) ? "0" : "1");
            modulAdministrationServices.updateModul(modul);
        }
        //return "redirect:/useradm";
        redirectAttributes.addFlashAttribute("pesan", "Data berhasil diupdate. ");
        return sburl.toString();
    }

    @RequestMapping(value = "/delmodul/{id}", method = RequestMethod.GET)
    public ModelAndView hapus(@PathVariable Integer id, final HttpServletRequest req) {

        final Modul modul = modulAdministrationServices.getModulById(id);
        return new ModelAndView("admin/deletemodul", "progcmd", modul);
    }

    @RequestMapping(value = "/deletemodul/{id}", method = RequestMethod.POST)
    //public String prosesdelete(@Valid @ModelAttribute("progcmd") Modul modul, BindingResult result, final HttpServletRequest request) {
    public String prosesdelete(@PathVariable Integer id) {
        modulAdministrationServices.deleteModul(id);
        //return "redirect:/bku/indexadmmodul";
        return null;
    }
}
