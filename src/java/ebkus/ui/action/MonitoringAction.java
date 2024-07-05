package ebkus.ui.action;

import ebkus.services.MonitoringServices;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import sp2d.services.RekonBankDkiServices;
import ebkus.util.SipkdHelpers;
import ebkus.util.SqlDatePropertyEditor;
import java.sql.Connection;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import ebkus.util.PrintReportTemplate;
import java.sql.SQLException;

/**
 *
 * @author Zainab
 */
@Controller
@RequestMapping("/monitoring")
public class MonitoringAction  extends PrintReportTemplate{

    private static final Logger log = LoggerFactory.getLogger(MonitoringAction.class);
    @Autowired
    MonitoringServices monitoringServices;
    @Autowired
    ServletContext servletContext;
    @Autowired
    DataSource dataSource;

    @RequestMapping(value = "/bkubendaharapengeluaran", method = RequestMethod.GET)
    public String BKU(final HttpServletRequest request) {
        return "monitoring/BKUbendaharapengeluaran";
    }
    
     @RequestMapping(value = "/kartukendalisekolah", method = RequestMethod.GET)
    public String BRO(final HttpServletRequest request) {
        return "monitoring/kartukendalisekolah";
    }
     @RequestMapping(value = "/bukurinciobjek", method = RequestMethod.GET)
    public String KKS(final HttpServletRequest request) {
        return "monitoring/BukuRincianObjekBelanja";
    }
    
    @RequestMapping(value = "/json/prosescetak", method = RequestMethod.GET)
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        response.setContentType("application/pdf"); 
        final String idsekolah = request.getParameter("idsekolah");
        final String kodlap = request.getParameter("kodlap");
        final String bulan = request.getParameter("bulan");
        final String idkegiatan = request.getParameter("idkeg");
        final String nama = request.getParameter("nama");
        
        final Map<String, Object> map = new HashMap<String, Object>();
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String pathReport = servletContext.getInitParameter("PATH_REPORT");

        map.put("SUBREPORT_DIR", pathReport);
            map.put("TAHUN",tahunAnggaran);
            map.put("BULAN",bulan);
            map.put("IDSEKOLAH",idsekolah);
            map.put("ID_SEKOLAH",idsekolah);
            map.put("KODELAPORAN",kodlap);
            map.put("IDKEGIATAN",idkegiatan);
                       
         if("01".equals(kodlap)){ 
            map.put("pathreport", pathReport + "/Report_BKU-Sekolah.jasper");
            map.put("filename", tahunAnggaran+"-"+bulan+"-"+nama+" BKU Sekolah"+".pdf"); 
        } else if("02".equals(kodlap)) {
            map.put("pathreport", pathReport + "/Report_BukuObjekBelanja-Sekolah.jasper");
            map.put("filename", tahunAnggaran+"-"+bulan+"-"+nama+" Buku Rincian Objek Belanja Sekolah"+".pdf"); 
        } else if("03".equals(kodlap)) { // PAJAK (PJK) 
            map.put("pathreport", pathReport + "/Report_KK_Kegiatan-Sekolah.jasper");
            map.put("filename", tahunAnggaran+"-"+bulan+"-"+nama+" Kartu Kendali KegiatanSekolah"+".pdf"); 
                
        } else { // Kartu Kendali 
        }
        printReportToPdfStream(response, map);
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        //webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }

}
