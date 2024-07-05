package ebkus.ui.action;

import ebkus.model.Kegiatan;
import ebkus.services.KegiatanServices;
import ebkus.services.ListSekolahServices;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Admin
 */
@Controller
@RequestMapping("/common")
public class CommonAction {

    private static final Logger log = LoggerFactory.getLogger(CommonAction.class);

    @Autowired
    KegiatanServices commonServices;

    @Autowired
    ListSekolahServices sekolahService;

    @RequestMapping(value = "/listkegiatansekolah", method = RequestMethod.GET)
    public ModelAndView listakunbukubesar(final Kegiatan kegiatan, final HttpServletRequest request, final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return new ModelAndView("kegiatan/listkegiatan", "refkegiatan", kegiatan);
    }

    @RequestMapping(value = "/json/listkegiatansekolah", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listskpdjsonbantuan(final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");

        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String kode = request.getParameter("kode");
        final String nama = request.getParameter("nama");
        final String idsekolah = request.getParameter("idsekolah");

        param.put("tahun", tahunAnggaran);
        param.put("idsekolah", idsekolah);
        param.put("kode", kode);
        param.put("nama", nama);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final long banyak = commonServices.getBanyakKegiatanSekolah(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", commonServices.getKegiatanSekolah(param));
        return mapData;
    }

    @RequestMapping(value = "/listsekolah", method = RequestMethod.GET)
    public String indexlistskpd(final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return "listsekolah/listsekolah";
    }

    @RequestMapping(value = "/json/listsekolahjson", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listskpdjson(final HttpServletRequest request) {

        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        final String namasekolah = request.getParameter("namasekolah");
        final String npsn = request.getParameter("npsn");
        param.put("namasekolah", namasekolah);
        param.put("npsn", npsn);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final long banyak = sekolahService.getBanyakAllSekolah(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", sekolahService.getAllSekolah(param));
        return mapData;
    }

    @RequestMapping(value = "/listkegiatanbop", method = RequestMethod.GET)
    public ModelAndView listkegiatanbop(final Kegiatan kegiatan, final HttpServletRequest request, final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return new ModelAndView("kegiatan/listkegiatanboptw", "refkegiatan", kegiatan);
    }

    @RequestMapping(value = "/json/listkegboppertw", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listkegboppertw(final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");

        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String kode = request.getParameter("kode");
        final String nama = request.getParameter("nama");
        final String idsekolah = request.getParameter("idsekolah");
        final String triwulan = request.getParameter("triwulan");

        param.put("tahun", tahunAnggaran);
        param.put("triwulan", triwulan);
        param.put("idsekolah", idsekolah);
        param.put("kode", kode);
        param.put("nama", nama);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final long banyak = commonServices.getBanyakKegBOPperTW(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", commonServices.getKegBOPperTW(param));
        return mapData;
    }

    @RequestMapping(value = "/listkegiatanbos", method = RequestMethod.GET)
    public ModelAndView listkegiatanbos(final Kegiatan kegiatan, final HttpServletRequest request, final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return new ModelAndView("kegiatan/listkegiatanbostw", "refkegiatan", kegiatan);
    }

    @RequestMapping(value = "/json/listkegbospertw", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listkegbospertw(final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");

        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String kode = request.getParameter("kode");
        final String nama = request.getParameter("nama");
        final String idsekolah = request.getParameter("idsekolah");
        final String triwulan = request.getParameter("triwulan");

        param.put("tahun", tahunAnggaran);
        param.put("triwulan", triwulan);
        param.put("idsekolah", idsekolah);
        param.put("kode", kode);
        param.put("nama", nama);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final long banyak = commonServices.getBanyakKegBOSperTW(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", commonServices.getKegBOSperTW(param));
        return mapData;
    }
}
