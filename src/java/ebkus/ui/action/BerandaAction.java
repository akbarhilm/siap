package ebkus.ui.action;

import ebkus.model.Pengguna;
import ebkus.model.Sekolah;
import ebkus.services.LoginServices;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author User
 */
@Controller
@RequestMapping("/beranda")
public class BerandaAction {

    private static final Logger log = LoggerFactory.getLogger(BerandaAction.class);
    @Autowired
    LoginServices loginServices;

    /* private static final Properties properties = new Properties();

     static {
     try {
     properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("sso.properties"));
     } catch (IOException ex) {
     ex.printStackTrace();
     }
     }*/
    @RequestMapping(method = RequestMethod.GET)
    public String index(final Principal principal, final HttpServletRequest req) {
        //  log.debug(" namaaaaa  " + new StringBuilder(principal.getName()).append(" tahunAnggaran ").append(req.getSession().getAttribute("tahunAnggaran")).toString());
        final Pengguna pengguna = loginServices.loadPenggunaByUsername(principal.getName());
        req.getSession().setAttribute("pengguna", pengguna);
        req.getSession().setAttribute("tahunAnggaran", req.getSession().getAttribute("tahunAnggaran")/*SipkdHelpers.splitString(principal.getName(), "|", 1) */);

        final List<Sekolah> listSkpd = pengguna.getListSekolah();
        log.debug("******* LIST SEKOLAH BERANDA === " + listSkpd.toString());

        return "dashboard/index";
    }

    @RequestMapping(value = "/json/sisahari", method = RequestMethod.GET)
    public @ResponseBody
    Integer sisahari(final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        return loginServices.getSisaHari(pengguna);
    }

    @RequestMapping(value = "/json/deletesession", method = RequestMethod.POST)
    public @ResponseBody
    String deleteSession(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");

        loginServices.deleteLoginStatus(pengguna);

        return "Data Buku Kas Umum Berhasil Dihapus";
    }
}
