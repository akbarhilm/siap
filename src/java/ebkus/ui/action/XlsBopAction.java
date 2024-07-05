package ebkus.ui.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ebkus.model.Kegiatan;
import ebkus.model.Pengguna;
import ebkus.services.BankServices;
import ebkus.services.XlsBopServices;
import ebkus.util.SipkdHelpers;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.jxls.area.Area;
import org.jxls.builder.AreaBuilder;
import org.jxls.builder.xls.XlsCommentAreaBuilder;
import org.jxls.common.CellRef;
import org.jxls.common.Context;
import org.jxls.transform.poi.PoiContext;
import org.jxls.transform.poi.PoiTransformer;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Zainab
 */
@Controller
@RequestMapping("/rkasbkus")
public class XlsBopAction {

    private static final Logger log = LoggerFactory.getLogger(XlsBopAction.class);

    @Autowired
    XlsBopServices xlsServices;
    
        @Autowired
    ServletContext servletContext;
        
    @RequestMapping(value = "/rkasvsbop", method = RequestMethod.GET)
    public String rkasbop( final HttpServletRequest request, final HttpServletResponse response) {
    
        return "downloadxls/xlsrkasbop";
    }
     @RequestMapping(value = "/rkasvsbos", method = RequestMethod.GET)
    public String rkasbos( final HttpServletRequest request, final HttpServletResponse response) {
    
        return "downloadxls/xlsrkasbos";
    }
    
    @RequestMapping(value = "/json/listbankpfk", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listbankpfk(final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String npsn = request.getParameter("npsn");
        //final String nama = request.getParameter("nama");
        
       // param.put("tahun", tahunAnggaran);
       // param.put("kode", kode);
        param.put("npsn", npsn);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final long banyak = xlsServices.getBanyakRkasBkus(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", xlsServices.getListRkasBkus(param));
        return mapData;
    }
    
    @RequestMapping(value = "/xls/bkuxls", method = RequestMethod.GET)
    public void xlsbku(final HttpServletRequest request, final HttpServletResponse response) throws FileNotFoundException, IOException, InvalidFormatException {
        //final String pathXls = servletContext.getInitParameter("PATH_XLS");
         String tahunAnggaran = request.getParameter("tahun");
        String npsn = request.getParameter("npsn");
        response.setHeader("Content-disposition", "attachment; filename=RKASVSBOP-"+npsn+".xls");
        response.setContentType("application/vnd.ms-excel");
       
        //final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        //log.debug(" =============== idskpd ============= " + idskpd);
       // String triwulan = request.getParameter("triwulan");
        HashMap<String, Object> param = new HashMap<String, Object>(9);
        param.put("offset", 0);
        //param.put("kodeakun", kodeakun);
        //param.put("namaakun", namaakun);
        param.put("tahun", tahunAnggaran);
       //param.put("triwulan", triwulan);
        //param.put("kodewilproses", kodewilproses);
        param.put("npsn", npsn);
        int banyak = xlsServices.getBanyakRkasBkus(param);
        param.put("limit", banyak);
        param.put("iSortCol_0", 1);
        param.put("sSortDir_0", "ASC");
        //InputStream is = servletContext.getResourceAsStream(pathXls+"/bku.xls");
        InputStream is = servletContext.getResourceAsStream("/WEB-INF/xls/rkasvsbop.xls");
        //log.debug(" =============== tes path ============= "+is.read());
        ServletOutputStream out = response.getOutputStream();
        Workbook workbook = WorkbookFactory.create(is);
        PoiTransformer transformer = PoiTransformer.createTransformer(workbook);
        log.debug(" =============== " + transformer.getWorkbook().getSheetName(0));
// Transformer transformer = TransformerFactory.createTransformer(is, out);
        // XlsArea xlsArea = new XlsArea("Template!A1", transformer);
        AreaBuilder areaBuilder = new XlsCommentAreaBuilder(transformer);
        log.debug(" =============== " + areaBuilder.build());
        List<Area> xlsAreaList = areaBuilder.build();
// getting the main area from the list
        Area xlsArea = xlsAreaList.get(0);
// creating a new PoiContext and setting our sample employees data into it under "employees" key
        Context context = new PoiContext();
        context.putVar("datas", xlsServices.getListRkasBkus(param));
        xlsArea.applyAt(new CellRef("Template!A1"), context);
        xlsArea.processFormulas();
        workbook.write(out);
        is.close();
        out.close();
    }

}
