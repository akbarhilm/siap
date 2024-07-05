package ebkus.ui.action;

import ebkus.model.Akun;
import ebkus.model.BapKas;
import ebkus.model.BapKasRinci;
import ebkus.model.Pengguna;
import ebkus.model.Sekolah;
import ebkus.services.BapKasBosServices;
import ebkus.util.BigDecimalPropertyEditor;
import ebkus.util.PrintReportTemplate;
import ebkus.util.SipkdHelpers;
import ebkus.util.SqlDatePropertyEditor;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author zainab
 */
@Controller
@RequestMapping("/bapkasbos")
public class BapKasBosAction extends PrintReportTemplate {

    private static final Logger log = LoggerFactory.getLogger(BapKasBosAction.class);

    @Autowired
    BapKasBosServices bapKasServices;

    @Autowired
    ServletContext servletContext;

    @Autowired
    DataSource dataSource;

    @RequestMapping(value = "/indexbapkas", method = RequestMethod.GET)
    public String index(final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");

        int thn = Integer.valueOf(tahunAnggaran);
        int th = thn + 1;
        final List<Sekolah> listSekolah = pengguna.getListSekolah();
        final Map< String, Object> param = new HashMap<String, Object>(2);

        param.put("tahun", th);
        param.put("idsekolah", listSekolah.get(0).getIdSekolah());

        final int tglBkuPros = bapKasServices.getCountTglBkuProses(param);

        request.setAttribute("tglBkuPros", tglBkuPros);

        return "bapkasbos/indexbapkasbos";

    }

    @RequestMapping(value = "/json/getlistbapkas", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getlistspjbl(final HttpServletRequest request) {
        final String idsekolah = request.getParameter("idsekolah");
        final String tahunAnggaran = request.getParameter("tahun");

        final Map< String, Object> param = new HashMap<String, Object>(2);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");

        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        param.put("tahun", tahunAnggaran);
        param.put("idsekolah", idsekolah);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final int banyak = bapKasServices.getCountBapKas(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", bapKasServices.getBapKas(param));
        return mapData;
    }

    @RequestMapping(value = "/addbapkas", method = RequestMethod.GET)
    public ModelAndView addbapkas(final BapKas bapkas, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        return new ModelAndView("bapkasbos/addbapkasbos", "refsppup", bapkas);
    }

    @RequestMapping(value = "/json/setTriwulan", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> setTriwulan(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idsekolah = request.getParameter("idsekolah");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("idsekolah", idsekolah);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        mapData.put("aData", bapKasServices.getTriwulan(param));

        return mapData;
    }

    @RequestMapping(value = "/prosessimpan", method = RequestMethod.POST)
    public String prosessimpan2(@Valid @ModelAttribute("refsppup") BapKas bapkas,
            final BindingResult result,
            final RedirectAttributes redirectAttributes,
            final Model model,
            final HttpServletRequest request) {
        bapkas.setTahun((String) request.getSession().getAttribute("tahunAnggaran"));

        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        String t = bapkas.getTglBkuBa();
        String tgl = SipkdHelpers.getStringDateFormatFromString(t, "yyyyMMdd", "dd/MM/yyyy");
        bapkas.setTglBkuBa(tgl);
        bapkas.setKodeWilSp2d("-");

        bapkas.setIdEntry(pengguna.getIdPengguna());
        bapkas.setTglEntry(new Timestamp(System.currentTimeMillis()));

        final int banyakrinci = SipkdHelpers.getIntFromString(request.getParameter("banyakrinci"));
        final List<BapKasRinci> listBapKasRinci = new ArrayList<BapKasRinci>(banyakrinci);

        for (int i = 0; i < banyakrinci; i++) {
            int indeks2 = i + 1;

            final BapKasRinci bapKasRinci = new BapKasRinci();
            final Akun akun = new Akun();
            akun.setIdAkun(SipkdHelpers.getIntFromString(request.getParameter("idakun" + indeks2)));
            bapKasRinci.setIdEntry(pengguna.getIdPengguna());
            bapKasRinci.setTglEntry(new Timestamp(System.currentTimeMillis()));
            bapKasRinci.setNamaBapKas(request.getParameter("namaBapKas" + indeks2));
            bapKasRinci.setNilaiBapKas(SipkdHelpers.getBigDecimalFromString(request.getParameter("nilaiBapKas" + indeks2)));

            listBapKasRinci.add(bapKasRinci);

        }

        bapkas.setBapKasRinci(listBapKasRinci);
        bapKasServices.insertBapKasAll(bapkas);

        redirectAttributes.addFlashAttribute("pesan", new StringBuilder("Data BAP Kas")
                .append(" Berhasil Disimpan ")
                .toString());

        return "redirect:/bapkasbos/indexbapkas";

    }

    @RequestMapping(value = "/editbapkas/{id}/{idsekolah}", method = RequestMethod.GET)
    public ModelAndView editspjbl(@PathVariable String id, @PathVariable String idsekolah, BapKas bapkas, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        final Map< String, Object> param = new HashMap<String, Object>(3);

        param.put("id", id);
        param.put("idsekolah", idsekolah);
        bapkas = bapKasServices.getBapKasById(param);
        return new ModelAndView("bapkasbos/editbapkasbos", "refsppup", bapkas);
    }

    @RequestMapping(value = "/arsipbapkas/{id}/{idsekolah}", method = RequestMethod.GET)
    public ModelAndView arsipbapkas(@PathVariable String id, @PathVariable String idsekolah, BapKas bapkas, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        final Map< String, Object> param = new HashMap<String, Object>(3);

        param.put("id", id);
        param.put("idsekolah", idsekolah);
        bapkas = bapKasServices.getBapKasById(param);
        return new ModelAndView("bapkasbos/arsipbapkasbos", "refsppup", bapkas);
    }

    @RequestMapping(value = "/json/istutupbuku", method = RequestMethod.GET)
    public @ResponseBody
    Integer istutupbuku(final HttpServletRequest request) {
        final String idsekolah = request.getParameter("idsekolah");
        final String triwulan = request.getParameter("triwulan");
        final String tahun = request.getParameter("tahun");
        final Map< String, Object> param = new HashMap<String, Object>(6);

        param.put("idsekolah", idsekolah);
        param.put("triwulan", triwulan);
        param.put("tahun", tahun);
        return bapKasServices.isTutupBuku(param);
    }

    @RequestMapping(value = "/json/getlistbapkasrinci", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listaset(final HttpServletRequest request) {
        final String idSekolahBAPKas = request.getParameter("idSekolahBAPKas");
        final Map< String, Object> param = new HashMap<String, Object>(9);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");

        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        param.put("idSekolahBAPKas", idSekolahBAPKas);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final int banyak = bapKasServices.getBanyakAllBAPKAS(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", bapKasServices.getAllBAPKAS(param));
        return mapData;
    }

    @RequestMapping(value = "/json/getbanyakrincibapkas ", method = RequestMethod.GET)
    public @ResponseBody
    Integer getBanyakBAPKasRinci(final HttpServletRequest request) {
        final String idSekolahBAPKas = request.getParameter("idSekolahBAPKas");
        final Map< String, Object> param = new HashMap<String, Object>(6);

        param.put("idSekolahBAPKas", idSekolahBAPKas);
        return bapKasServices.getBanyakAllBAPKAS(param);
    }

    @RequestMapping(value = "/prosesubah", method = RequestMethod.POST)
    public String prosesubah2(@Valid @ModelAttribute("refsppup") BapKas bapkas,
            final BindingResult result,
            final RedirectAttributes redirectAttributes,
            final Model model,
            final HttpServletRequest request) {
        bapkas.setTahun((String) request.getSession().getAttribute("tahunAnggaran"));

        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final List<Sekolah> listSekolah = pengguna.getListSekolah();

        String t = bapkas.getTglBkuBa();
        String tgl = SipkdHelpers.getStringDateFormatFromString(t, "yyyyMMdd", "dd/MM/yyyy");
        bapkas.setTglBkuBa(tgl);
        bapkas.setKodeWilSp2d("-");

        bapkas.setIdEntry(pengguna.getIdPengguna());
        bapkas.setTglEntry(new Timestamp(System.currentTimeMillis()));

        final int banyakrinci = SipkdHelpers.getIntFromString(request.getParameter("banyakrinci"));
        final int idrowbaru = SipkdHelpers.getIntFromString(request.getParameter("idrowbaru"));

        final int banyakrincibaru = banyakrinci - idrowbaru;
        final List<BapKasRinci> listBapKasRinci = new ArrayList<BapKasRinci>(banyakrinci);

        for (int i = 0; i < banyakrinci; i++) {
            int indeks2 = i + 1;

            final String nilaiBapKas = request.getParameter("nilaiBapKas" + indeks2);
            final String idSkpdBAPKas = request.getParameter("idSkpdBAPKas" + indeks2);
            final String idSkpdBAPKasRinci = request.getParameter("idSkpdBAPKasRinci" + indeks2);

            final BapKasRinci bapKasRinci = new BapKasRinci();
            final Akun akun = new Akun();
            akun.setIdAkun(SipkdHelpers.getIntFromString(request.getParameter("idakun" + indeks2)));

            bapKasRinci.setIdEntry(pengguna.getIdPengguna());
            bapKasRinci.setTglEntry(new Timestamp(System.currentTimeMillis()));
            bapKasRinci.setNamaBapKas(request.getParameter("namaBapKas" + indeks2));

            bapKasRinci.setNilaiBapKas(SipkdHelpers.getBigDecimalFromString(nilaiBapKas));
            bapKasRinci.setIdSekolahBAPKas(SipkdHelpers.getIntFromString(idSkpdBAPKas));
            bapKasRinci.setIdSekolahBAPKasRinci(SipkdHelpers.getIntFromString(idSkpdBAPKasRinci));

            listBapKasRinci.add(bapKasRinci);

        }

        bapkas.setBapKasRinci(listBapKasRinci);
        bapKasServices.updateBapKasAll(bapkas);

        redirectAttributes.addFlashAttribute("pesan", new StringBuilder("Data BAP Kas")
                .append(" berhasil diubah")
                .toString());

        return "redirect:/bapkasbos/editbapkas/" + bapkas.getIdSekolahBAPKas() + "/" + listSekolah.get(0).getIdSekolah();

    }

    @RequestMapping(value = "/deletebapkas/{id}/{idsekolah}", method = RequestMethod.GET)
    public ModelAndView deletebapkas(@PathVariable String id, @PathVariable String idsekolah, BapKas bapkas, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        final Map< String, Object> param = new HashMap<String, Object>(3);

        param.put("id", id);
        param.put("idsekolah", idsekolah);
        bapkas = bapKasServices.getBapKasById(param);
        return new ModelAndView("bapkasbos/deletebapkasbos", "refsppup", bapkas);
    }

    @RequestMapping(value = "/prosesdelete", method = RequestMethod.POST)
    public String prosesdelete(@Valid @ModelAttribute("refsppup") BapKas bapkas,
            final BindingResult result,
            final RedirectAttributes redirectAttributes,
            final Model model,
            final HttpServletRequest request) {

        final Map< String, Object> param = new HashMap<String, Object>(3);
        //String idba = bapkas.getIdSekolahBAPKas(); //request.getParameter("idSekolahBAPKas");
        param.put("idba", bapkas.getIdSekolahBAPKas());

        bapKasServices.deleteBapKas(param);

        redirectAttributes.addFlashAttribute("pesan", new StringBuilder("Data BAP Kas")
                .append(" berhasil dihapus")
                .toString());

        return "redirect:/bapkasbos/indexbapkas";
    }

    @RequestMapping(value = "/viewbapkas/{id}/{idsekolah}", method = RequestMethod.GET)
    public ModelAndView viewbapkas(@PathVariable String id, @PathVariable String idsekolah, BapKas bapkas, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        final Map< String, Object> param = new HashMap<String, Object>(3);

        param.put("id", id);
        param.put("idsekolah", idsekolah);
        bapkas = bapKasServices.getBapKasById(param);
        return new ModelAndView("bapkasbos/viewbapkasbos", "refsppup", bapkas);
    }

    @RequestMapping(value = "/json/prosescetak", method = RequestMethod.GET)
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        response.setContentType("application/pdf");
        final String idsekolah = request.getParameter("idsekolah");
        final String tahun = request.getParameter("tahun");
        final String triwulan = request.getParameter("triwulan");
        final Map<String, Object> map = new HashMap<String, Object>();
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String pathReport = servletContext.getInitParameter("PATH_REPORT");

        map.put("SUBREPORT_DIR", pathReport);
        map.put("TAHUN", tahunAnggaran);
        map.put("IDSEKOLAH", idsekolah);
        map.put("TRIWULAN", triwulan);
        List<Map> listhasil = bapKasServices.getnilaiparam(map);
        map.put("NAMA_DAERAH", listhasil.get(0).get("N_DAERAH_JUDUL"));
        map.put("NAMA_DAERAH_LOW", listhasil.get(0).get("N_DAERAH"));
        map.put("NO_PERDA", listhasil.get(0).get("I_PERDA_NO"));
        map.put("THN_PERDA", listhasil.get(0).get("C_PERDA_TAHUN"));
        map.put("TGL_PERDA", listhasil.get(0).get("C_PERDA_TGL"));
        map.put("NAMA_KOTA", listhasil.get(0).get("N_KOTA"));
        map.put("PERATURAN_1", listhasil.get(0).get("E_PERATURAN_SPD1"));
        map.put("PERATURAN_2", listhasil.get(0).get("E_PERATURAN_SPD2"));
        map.put("PERATURAN_3", listhasil.get(0).get("E_PERATURAN_SPD3"));
        map.put("PERATURAN_4", listhasil.get(0).get("E_PERATURAN_SPD4"));
        map.put("PERATURAN_5", listhasil.get(0).get("E_PERATURAN_SPD5"));
        map.put("PERATURAN_6", listhasil.get(0).get("E_PERATURAN_SPD6"));
        map.put("PERATURAN_7", listhasil.get(0).get("E_PERATURAN_SPD7"));

        map.put("pathreport", pathReport + "/Report_BAP_KasBos.jasper");
        map.put("filename", "Laporan-Berita-Acara-Pemeriksaan-Kas-" + "." + tahunAnggaran + "." + idsekolah + ".pdf");

        printReportToPdfStream(response, map);
    }

    @RequestMapping(value = "/json/getTriwulan", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getTriwulan(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idsekolah = request.getParameter("idsekolah");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("idsekolah", idsekolah);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        mapData.put("aData", bapKasServices.getBapTriwulan(param));

        return mapData;
    }

    @RequestMapping(value = "/persetujuanbapkas", method = RequestMethod.GET)
    public ModelAndView persetujuanbapkas(final BapKas bapkas, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        return new ModelAndView("bapkasbos/persetujuanbapkasbos", "refsppup", bapkas);
    }

    @RequestMapping(value = "/json/getbapkas", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getbapkas(final HttpServletRequest request) {
        final String idsekolah = request.getParameter("idsekolah");
        final String tahunAnggaran = request.getParameter("tahun");
        final String triwulan = request.getParameter("triwulan");

        final Map< String, Object> param = new HashMap<String, Object>(2);

        param.put("tahun", tahunAnggaran);
        param.put("idsekolah", idsekolah);
        param.put("triwulan", triwulan);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        mapData.put("aData", bapKasServices.getBapKasByTriwulan(param));
        return mapData;
    }

    @RequestMapping(value = "/json/prosesupdate", method = RequestMethod.POST)
    public @ResponseBody
    void prosesUpdate(@RequestBody Map<String, String> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final Map< String, Object> param = new HashMap<String, Object>();

        param.put("idSekolahBAPKas", mapdata.get("id"));
        param.put("ke", mapdata.get("ke"));
        param.put("idPengguna", pengguna.getIdPengguna());
        bapKasServices.updateApproval(param);
    }

    @RequestMapping(value = "/json/getNilaiKas", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getNilaiKas(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idsekolah = request.getParameter("idsekolah");
        final String triwulan = request.getParameter("triwulan");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("idsekolah", idsekolah);
        param.put("triwulan", triwulan);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        mapData.put("aData", bapKasServices.getNilaiKas(param));
        return mapData;
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }

}
