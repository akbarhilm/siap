package ebkus.ui.action;

import ebkus.model.Pengguna;
import ebkus.services.ListSekolahServices;
import ebkus.services.MonitoringReturServices;
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
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Zainab
 */
@Controller
@RequestMapping("/monretur")
public class MonitoringReturAction  extends PrintReportTemplate{

    private static final Logger log = LoggerFactory.getLogger(MonitoringReturAction.class);
    @Autowired
    MonitoringReturServices retServices;
    @Autowired
    ServletContext servletContext;
    @Autowired
    DataSource dataSource;
     @Autowired
    ListSekolahServices listServices;

    @RequestMapping(value = "/returbop", method = RequestMethod.GET)
    public String retbop(final HttpServletRequest request) {
         final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("idsekolah",String.valueOf(pengguna.getSekolah().getIdSekolah()));
        param.put("idpengguna",pengguna.getIdPengguna());
         final Integer plt = listServices.getBanyakAllSekolahPlt(param);
         
         request.setAttribute("plt",plt);
         request.setAttribute("rek","bop");
        
        return "monitoring/retur";
    }
    
     @RequestMapping(value = "/returbos", method = RequestMethod.GET)
    public String retbos(final HttpServletRequest request) {
         final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("idsekolah",String.valueOf(pengguna.getSekolah().getIdSekolah()));
        param.put("idpengguna",pengguna.getIdPengguna());
         final Integer plt = listServices.getBanyakAllSekolahPlt(param);
         
         request.setAttribute("plt",plt);
         request.setAttribute("rek","bos");
        return "monitoring/retur";
    }
    
    @RequestMapping(value = "/json/listretur", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listret(final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>(6);
 
       
        final String tipe = request.getParameter("tipe");
        final Integer tw = SipkdHelpers.getIntFromString(request.getParameter("tw"))  ;
         final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final Integer idsekolah = SipkdHelpers.getIntFromString(request.getParameter("idsekolah"));
        if(tipe.equals("bop")){
             param.put("tipe", 2);
        }else{
            param.put("tipe", 1);
        }
       
        param.put("tw",tw);
        param.put("tahun",tahunAnggaran);
        param.put("idsekolah",idsekolah);
        
      
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
         if(tipe.equals("bop")){
        final long banyak = retServices.getBanyakReturBOP(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", retServices.getReturBOP(param));
         }else{
             final long banyak = retServices.getBanyakReturBOS(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", retServices.getReturBOS(param));
         }
        return mapData;
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
