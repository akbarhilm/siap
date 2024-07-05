package ebkus.ui.action;

import ebkus.model.Pengguna;
import ebkus.services.ResetPasswordServices;
import ebkus.util.BigDecimalPropertyEditor;
import ebkus.util.SqlDatePropertyEditor;
import java.math.BigDecimal;
import java.security.Principal;
import java.sql.Date;
import java.util.HashMap;
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
import ebkus.util.PrintReportTemplate;
import java.sql.SQLException;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author Racka
 */
@Controller
@RequestMapping("/reset")
public class ResetPasswordAction   extends PrintReportTemplate {

    private static final Logger log = LoggerFactory.getLogger(ResetPasswordAction.class);

    @Autowired
    ServletContext servletContext;

    @Autowired
    ResetPasswordServices passwordServices;
    
    @Autowired
    DataSource dataSource;

    @RequestMapping(method = RequestMethod.GET)
    public String index(final Principal principal, final HttpServletRequest req) {
        return "admin/resetpassword";
    }

    @RequestMapping(value = "/json/getPengguna", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getPengguna(final HttpServletRequest request) {
        final String nrk = request.getParameter("nrk");
        final Map< String, Object> param = new HashMap<String, Object>();
        param.put("nrk", nrk);
        final Map<String, Object> mapData = new HashMap<String, Object>();
        mapData.put("aData", passwordServices.getPengguna(param));
        return mapData;
    }

    @RequestMapping(value = "/json/generatePassword", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> generatePassword(final HttpServletRequest request) {
        final Map<String, Object> mapData = new HashMap<String, Object>();
        mapData.put("aData", passwordServices.generatePassword());
        return mapData;
    }

    @RequestMapping(value = "/json/simpanpassword", method = RequestMethod.POST)
    public @ResponseBody
    String simpanpassword(@RequestBody Map<String, String> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final Map< String, Object> param = new HashMap<String, Object>();

        final String idpengguna = mapdata.get("idpengguna");
        final String passwordBaru = mapdata.get("sandi");
        param.put("idpengguna", idpengguna);
        param.put("passwordBaru", passwordBaru);
        param.put("idEntry", pengguna.getIdPengguna());
        passwordServices.simpan(param);
        return "Password berhasil diupdate";
    }

        @RequestMapping(value = "/json/prosescetak", method = RequestMethod.GET)
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        response.setContentType("application/pdf");

        //final String tahunAnggaran = request.getParameter("tahun");
        final String idpengguna = request.getParameter("idpengguna"); 
        final String nrk = request.getParameter("nrk"); 

        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        final Map<String, Object> map = new HashMap<String, Object>(); 
        final String pathReport = servletContext.getInitParameter("PATH_REPORT");

        map.put("SUBREPORT_DIR", pathReport);
        map.put("IDPENGGUNA", idpengguna); 

        map.put("pathreport", pathReport + "/Report_Reset_Password.jasper");
        map.put("filename", nrk + "-" + "Reset-Password-" + idpengguna + ".pdf");

        printReportToPdfStream(response, map);
    }
    
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }

}
