/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.ui.action;

import ebkus.services.CekRkasServices;
import ebkus.util.BigDecimalPropertyEditor;
import ebkus.util.SqlDatePropertyEditor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Racka
 */
@Controller
@RequestMapping("bku/indexcekrkas")
public class CekRkasAction {

    private static final Logger log = LoggerFactory.getLogger(KonversiRkasAction.class);
    @Autowired
    CekRkasServices cekRkasServices;
    @Autowired
    ServletContext servletContext;
    @Autowired
    DataSource dataSource;

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "/konversi/cekrkas";

    }

    @RequestMapping(value = "/json/listduplikat", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> cekDuplikat(final HttpServletRequest request) {
        final String tahun = request.getParameter("tahun");

        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        param.put("tahun", tahun);

        log.debug("tahun = " + tahun);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final int banyak = cekRkasServices.getBanyakDuplikat(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", cekRkasServices.getDuplikat(param));
        return mapData;
    }

    @RequestMapping(value = "/json/listrinci", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> cekGiatRinci(final HttpServletRequest request) {
        final String tahun = request.getParameter("tahun");

        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        param.put("tahun", tahun);

        log.debug("tahun = " + tahun);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final int banyak = cekRkasServices.getBanyakGiatRinci(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", cekRkasServices.getGiatRinci(param));
        return mapData;
    }

    @RequestMapping(value = "/json/listakb", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> cekGiatAkb(final HttpServletRequest request) {
        final String tahun = request.getParameter("tahun");

        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        param.put("tahun", tahun);

        log.debug("tahun = " + tahun);
        log.debug("JUMLAH ===== " + cekRkasServices.getGiatAkb(param).size());

        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final int banyak = cekRkasServices.getBanyakGiatAkb(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", cekRkasServices.getGiatAkb(param));
        return mapData;
    }

    @RequestMapping(value = "/xls/xlsduplikat", method = RequestMethod.GET)
    public void xlsDuplikat(final HttpServletRequest request, final HttpServletResponse response) throws FileNotFoundException, IOException, InvalidFormatException {
        response.setHeader("Content-disposition", "attachment; filename=data-duplikat.xls");
        response.setContentType("application/vnd.ms-excel");
        final String tahunAnggaran = request.getParameter("tahun");
        final Map< String, Object> param = new HashMap<String, Object>(6);
        param.put("offset", 0);

        param.put("tahun", tahunAnggaran);
        final int banyak = cekRkasServices.getBanyakDuplikat(param);
        param.put("limit", banyak);
        param.put("iSortCol_0", 1);
        param.put("sSortDir_0", "ASC");

        InputStream is = servletContext.getResourceAsStream("/WEB-INF/xls/cekduplikat.xls");
        ServletOutputStream out = response.getOutputStream();
        Workbook workbook = WorkbookFactory.create(is);
        PoiTransformer transformer = PoiTransformer.createTransformer(workbook);
        AreaBuilder areaBuilder = new XlsCommentAreaBuilder(transformer);
        List<Area> xlsAreaList = areaBuilder.build();
        Area xlsArea = xlsAreaList.get(0);
        Context context = new PoiContext();
        context.putVar("datas", cekRkasServices.getDuplikat(param));
        xlsArea.applyAt(new CellRef("Template!A1"), context);
        xlsArea.processFormulas();
        workbook.write(out);
        is.close();
        out.close();

    }

    @RequestMapping(value = "/xls/xlsrinci", method = RequestMethod.GET)
    public void xlsRinci(final HttpServletRequest request, final HttpServletResponse response) throws FileNotFoundException, IOException, InvalidFormatException {
        response.setHeader("Content-disposition", "attachment; filename=data-kegiatan-rinci.xls");
        response.setContentType("application/vnd.ms-excel");
        final String tahunAnggaran = request.getParameter("tahun");
        final Map< String, Object> param = new HashMap<String, Object>(6);
        param.put("offset", 0);

        param.put("tahun", tahunAnggaran);
        final int banyak = cekRkasServices.getBanyakGiatRinci(param);

        param.put("limit", banyak);
        param.put("iSortCol_0", 1);
        param.put("sSortDir_0", "ASC");

        InputStream is = servletContext.getResourceAsStream("/WEB-INF/xls/cekrinci.xls");
        ServletOutputStream out = response.getOutputStream();
        Workbook workbook = WorkbookFactory.create(is);
        PoiTransformer transformer = PoiTransformer.createTransformer(workbook);
        AreaBuilder areaBuilder = new XlsCommentAreaBuilder(transformer);
        List<Area> xlsAreaList = areaBuilder.build();
        Area xlsArea = xlsAreaList.get(0);
        Context context = new PoiContext();
        context.putVar("datas", cekRkasServices.getGiatRinci(param));
        xlsArea.applyAt(new CellRef("Template!A1"), context);
        xlsArea.processFormulas();
        workbook.write(out);
        is.close();
        out.close();

    }

    @RequestMapping(value = "/xls/xlsakb", method = RequestMethod.GET)
    public void xlsAkb(final HttpServletRequest request, final HttpServletResponse response) throws FileNotFoundException, IOException, InvalidFormatException {
        response.setHeader("Content-disposition", "attachment; filename=data-kegiatan-akb.xls");
        response.setContentType("application/vnd.ms-excel");
        final String tahunAnggaran = request.getParameter("tahun");
        final Map< String, Object> param = new HashMap<String, Object>(6);
        param.put("offset", 0);

        param.put("tahun", tahunAnggaran);
        final int banyak = cekRkasServices.getBanyakGiatAkb(param);

        param.put("limit", banyak);
        param.put("iSortCol_0", 1);
        param.put("sSortDir_0", "ASC");

        InputStream is = servletContext.getResourceAsStream("/WEB-INF/xls/cekakb.xls");
        ServletOutputStream out = response.getOutputStream();
        Workbook workbook = WorkbookFactory.create(is);
        PoiTransformer transformer = PoiTransformer.createTransformer(workbook);
        AreaBuilder areaBuilder = new XlsCommentAreaBuilder(transformer);
        List<Area> xlsAreaList = areaBuilder.build();
        Area xlsArea = xlsAreaList.get(0);
        Context context = new PoiContext();
        context.putVar("datas", cekRkasServices.getGiatAkb(param));
        xlsArea.applyAt(new CellRef("Template!A1"), context);
        xlsArea.processFormulas();
        workbook.write(out);
        is.close();
        out.close();
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }
}
