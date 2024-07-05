package ebkus.ui.action;

import ebkus.model.BukuKasUmum;
import ebkus.model.Pengguna;
import ebkus.services.MonitoringBkuBopTransferServices;
import ebkus.util.BigDecimalPropertyEditor;
import ebkus.util.SqlDatePropertyEditor;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ebkus.util.PrintReportTemplate;
import java.sql.SQLException;

/**
 *
 * @author Racka
 */
@Controller
@RequestMapping("/monbop")
public class MonitoringBkuBopTransferAction extends PrintReportTemplate {

    private static final Logger log = LoggerFactory.getLogger(BkuBopAction.class);
    @Autowired
    ServletContext servletContext;

    @Autowired
    DataSource dataSource;

    @Autowired
    MonitoringBkuBopTransferServices bkuBopTransferServices;

    @RequestMapping(value = "/indexmontransfer", method = RequestMethod.GET)
    public ModelAndView index(final BukuKasUmum bku, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        return new ModelAndView("monitoring/indexmonitoringbkuboptransfer", "refmonbkubop", bku);
    }

    @RequestMapping(value = "/json/listindexmontransfer", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listindex(final HttpServletRequest request) {

        final Map< String, Object> param = new HashMap<String, Object>();
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");

        final String idsekolah = request.getParameter("idsekolah");
        final String tahun = request.getParameter("tahun");
        final String triwulan = request.getParameter("triwulan");

        param.put("idsekolah", Integer.parseInt(idsekolah));
        param.put("tahun", tahun);
        param.put("triwulan", triwulan);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        final long banyak = bkuBopTransferServices.getBanyakListIndex(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", bkuBopTransferServices.getListIndex(param));

        return mapData;
    }

    @RequestMapping(value = "/json/getTotal", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getTotal(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idsekolah = request.getParameter("idsekolah");
        final String triwulan = request.getParameter("triwulan");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("idsekolah", idsekolah);

        param.put("triwulan", triwulan);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        mapData.put("aData", bkuBopTransferServices.getNilaiIndex(param));
        return mapData;
    }

    @RequestMapping(value = "/json/getSaldo", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getSaldo(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idsekolah = request.getParameter("idsekolah");
        final String triwulan = request.getParameter("triwulan");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("idsekolah", idsekolah);

        param.put("triwulan", triwulan);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        mapData.put("aData", bkuBopTransferServices.getNilaiSaldo(param));
        return mapData;
    }

    @RequestMapping(value = "/json/prosescetakmontransfer", method = RequestMethod.GET)
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        response.setContentType("application/pdf");
        final String idsekolah = request.getParameter("idsekolah");
        final String triwulan = request.getParameter("triwulan");
        String tw = "";
        if ("1".equals(triwulan)) {
            tw = "I";
        } else if ("2".equals(triwulan)) {
            tw = "II";
        } else if ("3".equals(triwulan)) {
            tw = "III";
        } else if ("4".equals(triwulan)) {
            tw = "IV";
        }
        final String kodeKegiatanFilter = request.getParameter("kodeKegiatanFilter");
        final String namaKegiatanFilter = request.getParameter("namaKegiatanFilter");
        final String kodeAkunFilter = request.getParameter("kodeAkunFilter");
        final String namaAkunFilter = request.getParameter("namaAkunFilter");
        final String selisihFilter = request.getParameter("selisihFilter");

        final Map<String, Object> map = new HashMap<String, Object>();
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String pathReport = servletContext.getInitParameter("PATH_REPORT");
        JasperPrint jasperPrint = null;
        String filename = "";

        map.put("SUBREPORT_DIR", pathReport);
        map.put("TAHUN", tahunAnggaran);
        map.put("IDSEKOLAH", Integer.parseInt(idsekolah));
        map.put("TRIWULAN", triwulan);
        map.put("kodeKegiatanFilter", kodeKegiatanFilter);
        map.put("namaKegiatanFilter", namaKegiatanFilter);
        map.put("kodeAkunFilter", kodeAkunFilter);
        map.put("namaAkunFilter", namaAkunFilter);
        map.put("selisihFilter", selisihFilter);

        map.put("pathreport", pathReport + "/Report_Monitoring_Transfer_Keg_Bku_Bop_Triwulan.jasper");
        map.put("filename", tahunAnggaran + "-" + "Laporan_Monitoring_Transfer_Keg_Bku_Bop_Triwulan_" + tw + ".pdf");
        printReportToPdfStream(response, map);
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }

}
