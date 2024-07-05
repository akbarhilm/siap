package ebkus.ui.action;

import ebkus.model.BkuRinci;
import ebkus.model.BukuKasUmum;
import ebkus.model.Pengguna;
import ebkus.services.KoreksiNilaiSpjBosServices;
import ebkus.util.BigDecimalPropertyEditor;
import ebkus.util.PrintReportTemplate;
import ebkus.util.SipkdHelpers;
import ebkus.util.SqlDatePropertyEditor;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Zainab
 */
@Controller
@RequestMapping("/koreksinilaispjbos")
public class KoreksiNilaiSpjBosAction extends PrintReportTemplate {

    private static final Logger log = LoggerFactory.getLogger(KoreksiNilaiSpjBosAction.class);

    @Autowired
    KoreksiNilaiSpjBosServices bkuServices;

    @Autowired
    ServletContext servletContext;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index(final BukuKasUmum bku, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        return new ModelAndView("koreksinilaispjbos/indexkoreksibos", "refbku", bku);
    }

    @RequestMapping(value = "/addkoreksi/{idsekolah}", method = RequestMethod.GET)
    public ModelAndView addkoreksi(BukuKasUmum bku, @PathVariable String idsekolah, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        final Map< String, Object> param = new HashMap<String, Object>();
        param.put("idsekolah", idsekolah);
        bku = bkuServices.getDataSekolah(param);
        return new ModelAndView("koreksinilaispjbos/addkoreksibos", "refbku", bku);
    }

    @RequestMapping(value = "/json/listindex", method = RequestMethod.GET)
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

        param.put("idsekolah", idsekolah);
        param.put("tahun", tahun);
        param.put("triwulan", triwulan);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        final long banyak = bkuServices.getBanyakListIndex(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", bkuServices.getListIndex(param));

        return mapData;
    }

    @RequestMapping(value = "/json/getListTriwulanByRekap", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getSaldoKasJO(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idsekolah = request.getParameter("idsekolah");

        final Map< String, Object> param = new HashMap<String, Object>(5);
        param.put("tahun", tahunAnggaran);
        param.put("idsekolah", idsekolah);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        mapData.put("aData", bkuServices.getListTriwulanByRekap(param));
        return mapData;
    }

    @RequestMapping(value = "/listpj", method = RequestMethod.GET)
    public ModelAndView listsetoran(final BukuKasUmum bku, final HttpServletRequest request, final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return new ModelAndView("koreksinilaispjbos/listspj", "refkegiatan", bku);
    }

    @RequestMapping(value = "/json/listspj", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listspj(final HttpServletRequest request) {

        final Map< String, Object> param = new HashMap<String, Object>();
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");

        final String idsekolah = request.getParameter("idsekolah");
        final String tahun = request.getParameter("tahun");
        final String triwulan = request.getParameter("triwulan");
        final String nomohon = request.getParameter("nobku");
        final String nobukti = request.getParameter("nobukti");

        param.put("idsekolah", idsekolah);
        param.put("tahun", tahun);
        param.put("triwulan", triwulan);
        param.put("nomohon", nomohon);
        param.put("nobukti", nobukti);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        final long banyak = bkuServices.getBanyakListSpj(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", bkuServices.getListSpj(param));

        return mapData;
    }

    @RequestMapping(value = "/json/listspjkoreksi", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listspjkoreksi(final HttpServletRequest request) {

        final Map< String, Object> param = new HashMap<String, Object>();
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");

        final String idsekolah = request.getParameter("idsekolah");
        final String tahun = request.getParameter("tahun");
        final String triwulan = request.getParameter("triwulan");
        final String idkegiatan = request.getParameter("idkegiatan");
        final String nomohonref = request.getParameter("nomohonref");
        final String nobkumohon = request.getParameter("nobkumohon");

        param.put("idsekolah", idsekolah);
        param.put("tahun", tahun);
        param.put("triwulan", triwulan);
        param.put("idkegiatan", idkegiatan);
        param.put("nomohonref", nomohonref);
        param.put("nomohon", nobkumohon);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        final long banyak = bkuServices.getBanyakListSpjKoreksi(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", bkuServices.getListSpjKoreksi(param));

        return mapData;
    }

    @RequestMapping(value = "/json/prosessimpankoreksi", method = RequestMethod.POST)
    public @ResponseBody
    String prosessimpankoreksi(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        List<Map<String, Object>> nilailist = (List) mapdata.get("nilailist");
        List<BkuRinci> listRinci = new ArrayList<>();
        BukuKasUmum bukukas = new BukuKasUmum();

        final String idsekolah = (String) mapdata.get("idsekolah");
        final String kodetransaksi = (String) mapdata.get("kodetransaksi");
        final String nobukti = (String) mapdata.get("nobukti");
        final String tgldok = (mapdata.get("tgldok")).toString();
        final String uraian = (String) mapdata.get("uraian");
        final String nrk = (String) mapdata.get("nrk");
        final String idbku = (String) mapdata.get("idbku");
        final String nipPPTK = (String) mapdata.get("nippptk");
        final String namaPPTK = (String) mapdata.get("namapptk");
        final String idkegiatan = (String) mapdata.get("idkegiatan");
        final String kodekegiatan = (String) mapdata.get("kodekeg");
        final String triwulan = (String) mapdata.get("triwulan");
        final String nobkuref = (String) mapdata.get("nobkuref");
        final String triwulankoreksi = (String) mapdata.get("triwulankoreksi");
        final String tglposting = (String) mapdata.get("tglposting");

        bukukas.setTahun(tahun);
        bukukas.setIdsekolah(SipkdHelpers.getIntFromString(idsekolah.toString()));
        bukukas.setIdBku(SipkdHelpers.getIntFromString(idbku.toString()));
        bukukas.setIdEntry(pengguna.getIdPengguna());
        bukukas.setKodeTransaksi(kodetransaksi);
        bukukas.setNoDok(nobukti);
        bukukas.setTglDok(SipkdHelpers.getDateFromString(tgldok));
        bukukas.setUraian(uraian);
        bukukas.setUraianBukti(uraian);
        bukukas.setKodeWilayah(pengguna.getKodeProses());
        bukukas.setNrkPptk(nrk);
        bukukas.setNipPptk(nipPPTK);
        bukukas.setNamaPptk(namaPPTK);
        bukukas.setNoBkuRef(nobkuref);
        bukukas.setIdKegiatan(SipkdHelpers.getIntFromString(idkegiatan));
        bukukas.setKodeKeg(kodekegiatan);
        bukukas.setTriwulan(triwulan);
        bukukas.setTriwulanKoreksi(triwulankoreksi);
        bukukas.setTglPosting(tglposting);

        for (Map<String, Object> mapnilailist : nilailist) {

            BkuRinci bkurinci = new BkuRinci();

            Object nilaimasuk = mapnilailist.get("nilaimasuk");
            Object nilaikeluar = mapnilailist.get("nilaikeluar");
            Object nilaipajak = mapnilailist.get("nilaipajak");
            Object idbas = mapnilailist.get("idbas");
            Object kodeakun = mapnilailist.get("kodeakun");
            Object idkomponen = mapnilailist.get("idkomponen");
            Object idblrinci = mapnilailist.get("idblrinci");
            Object nourut = mapnilailist.get("nourut");
            Object namasubkeg = mapnilailist.get("namasubkeg");
            Object komponenpajak = mapnilailist.get("komponenpajak");
            Object volume = mapnilailist.get("volume");
            Object hargasatuan = mapnilailist.get("hargasatuan");
            Object ketrinci = mapnilailist.get("ketrinci");
            Object idrinci = mapnilailist.get("idrinci");

            bkurinci.setIdBkuRinci(SipkdHelpers.getIntFromString(idrinci.toString()));
            bkurinci.setNilaiMasuk(SipkdHelpers.getBigDecimalFromString(nilaimasuk.toString()));
            bkurinci.setNilaiKeluar(SipkdHelpers.getBigDecimalFromString(nilaikeluar.toString()));
            bkurinci.setNilaiPajakSpj(SipkdHelpers.getBigDecimalFromString(nilaipajak.toString()));
            bkurinci.setIdBas(SipkdHelpers.getIntFromString(idbas.toString()));
            bkurinci.setKodeakun(kodeakun.toString());
            bkurinci.setIdEntry(pengguna.getIdPengguna());
            bkurinci.setIdKomponen(SipkdHelpers.getIntFromString(idkomponen.toString()));
            bkurinci.setIdBlRinci(SipkdHelpers.getIntFromString(idblrinci.toString()));
            bkurinci.setNoUrut(nourut.toString());
            bkurinci.setNamaSubKegiatan(namasubkeg.toString());
            bkurinci.setKomponenPajak(SipkdHelpers.getIntFromString(komponenpajak.toString()));
            bkurinci.setVolume(SipkdHelpers.getIntFromString(volume.toString()));
            bkurinci.setKeteranganRinci(ketrinci.toString());
            bkurinci.setHargaSatuan(SipkdHelpers.getBigDecimalFromString(hargasatuan.toString()));
            bkurinci.setKodeTransaksi(kodetransaksi);

            listRinci.add(bkurinci);

        }

        bukukas.setListBkuRinci(listRinci);
        bkuServices.insertKoreksi(bukukas);

        return "Data Buku Kas Umum Berhasil Disimpan";
    }

    @RequestMapping(value = "/editkoreksi/", method = RequestMethod.GET)
    public ModelAndView editbkuspj(final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");

        final String idbku = request.getParameter("idbku");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("idbku", idbku);
        param.put("tahun", tahunAnggaran);

        final BukuKasUmum bku = bkuServices.getDataEdit(param);

        return new ModelAndView("koreksinilaispjbos/editkoreksibos", "refbku", bku);
    }

    @RequestMapping(value = "/json/prosesupdatekoreksi", method = RequestMethod.POST)
    public @ResponseBody
    String prosesupdatekoreksi(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        List<Map<String, Object>> nilailist = (List) mapdata.get("nilailist");
        List<BkuRinci> listRinci = new ArrayList<>();
        BukuKasUmum bukukas = new BukuKasUmum();

        final String idsekolah = (String) mapdata.get("idsekolah");
        final String kodetransaksi = (String) mapdata.get("kodetransaksi");
        final String nobukti = (String) mapdata.get("nobukti");
        final String tgldok = (mapdata.get("tgldok")).toString();
        final String uraian = (String) mapdata.get("uraian");
        final String nrk = (String) mapdata.get("nrk");
        final String idbku = (String) mapdata.get("idbku");
        final String nipPPTK = (String) mapdata.get("nippptk");
        final String namaPPTK = (String) mapdata.get("namapptk");
        final String idkegiatan = (String) mapdata.get("idkegiatan");
        final String kodekegiatan = (String) mapdata.get("kodekeg");
        final String triwulan = (String) mapdata.get("triwulan");
        final String nobkuref = (String) mapdata.get("nobkuref");
        final String triwulankoreksi = (String) mapdata.get("triwulankoreksi");
        final String tglposting = (String) mapdata.get("tglposting");

        bukukas.setTahun(tahun);
        bukukas.setIdsekolah(SipkdHelpers.getIntFromString(idsekolah.toString()));
        bukukas.setIdBku(SipkdHelpers.getIntFromString(idbku.toString()));
        bukukas.setIdEntry(pengguna.getIdPengguna());
        bukukas.setKodeTransaksi(kodetransaksi);
        bukukas.setNoDok(nobukti);
        bukukas.setTglDok(SipkdHelpers.getDateFromString(tgldok));
        bukukas.setUraian(uraian);
        bukukas.setUraianBukti(uraian);
        bukukas.setKodeWilayah(pengguna.getKodeProses());
        bukukas.setNrkPptk(nrk);
        bukukas.setNipPptk(nipPPTK);
        bukukas.setNamaPptk(namaPPTK);
        bukukas.setNoBkuRef(nobkuref);
        bukukas.setIdKegiatan(SipkdHelpers.getIntFromString(idkegiatan));
        bukukas.setKodeKeg(kodekegiatan);
        bukukas.setTriwulan(triwulan);
        bukukas.setTriwulanKoreksi(triwulankoreksi);
        bukukas.setTglPosting(tglposting);

        for (Map<String, Object> mapnilailist : nilailist) {

            BkuRinci bkurinci = new BkuRinci();

            Object nilaimasuk = mapnilailist.get("nilaimasuk");
            Object nilaikeluar = mapnilailist.get("nilaikeluar");
            Object idbas = mapnilailist.get("idbas");
            Object kodeakun = mapnilailist.get("kodeakun");
            Object idkomponen = mapnilailist.get("idkomponen");
            Object idblrinci = mapnilailist.get("idblrinci");
            Object nourut = mapnilailist.get("nourut");
            Object namasubkeg = mapnilailist.get("namasubkeg");
            Object komponenpajak = mapnilailist.get("komponenpajak");
            Object volume = mapnilailist.get("volume");
            Object hargasatuan = mapnilailist.get("hargasatuan");
            Object ketrinci = mapnilailist.get("ketrinci");
            Object idrinci = mapnilailist.get("idrinci");

            bkurinci.setIdBkuRinci(SipkdHelpers.getIntFromString(idrinci.toString()));
            bkurinci.setNilaiMasuk(SipkdHelpers.getBigDecimalFromString(nilaimasuk.toString()));
            bkurinci.setNilaiKeluar(SipkdHelpers.getBigDecimalFromString(nilaikeluar.toString()));
            bkurinci.setIdBas(SipkdHelpers.getIntFromString(idbas.toString()));
            bkurinci.setKodeakun(kodeakun.toString());
            bkurinci.setIdEntry(pengguna.getIdPengguna());
            bkurinci.setIdKomponen(SipkdHelpers.getIntFromString(idkomponen.toString()));
            bkurinci.setIdBlRinci(SipkdHelpers.getIntFromString(idblrinci.toString()));
            bkurinci.setNoUrut(nourut.toString());
            bkurinci.setNamaSubKegiatan(namasubkeg.toString());
            bkurinci.setKomponenPajak(SipkdHelpers.getIntFromString(komponenpajak.toString()));
            bkurinci.setVolume(SipkdHelpers.getIntFromString(volume.toString()));
            bkurinci.setKeteranganRinci(ketrinci.toString());
            bkurinci.setHargaSatuan(SipkdHelpers.getBigDecimalFromString(hargasatuan.toString()));
            bkurinci.setKodeTransaksi(kodetransaksi);

            listRinci.add(bkurinci);

        }

        bukukas.setListBkuRinci(listRinci);
        bkuServices.updateKoreksi(bukukas);

        return "Data Buku Kas Umum Berhasil Disimpan";
    }

    @RequestMapping(value = "/deletekoreksi/", method = RequestMethod.GET)
    public ModelAndView deletekoreksi(final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");

        final String idbku = request.getParameter("idbku");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("idbku", idbku);
        param.put("tahun", tahunAnggaran);

        final BukuKasUmum bku = bkuServices.getDataEdit(param);

        return new ModelAndView("koreksinilaispjbos/deletekoreksibos", "refbku", bku);
    }

    @RequestMapping(value = "/json/prosesdeletebyid", method = RequestMethod.POST)
    public @ResponseBody
    String prosesdeletebyid(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");

        final String idbku = (String) mapdata.get("idbku");
        final String kodetrans = "JJ";
        final String idsekolah = (String) mapdata.get("idsekolah");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahun);
        param.put("kodeTransaksi", kodetrans);
        param.put("idbku", idbku);
        param.put("idsekolah", idsekolah);

        bkuServices.deleteBkuById(param);

        return "Data Buku Kas Umum Berhasil Dihapus";
    }

    @RequestMapping(value = "/arsipkoreksi/", method = RequestMethod.GET)
    public ModelAndView arsipkoreksi(final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");

        final String idbku = request.getParameter("idbku");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("idbku", idbku);
        param.put("tahun", tahunAnggaran);

        final BukuKasUmum bku = bkuServices.getDataEdit(param);

        return new ModelAndView("koreksinilaispjbos/arsipkoreksibos", "refbku", bku);
    }

    @RequestMapping(value = "/json/getPaguEdit", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getPaguEdit(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idsekolah = request.getParameter("idsekolah");
        final String nomohon = request.getParameter("nomohon");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("idsekolah", idsekolah);
        param.put("nomohon", nomohon);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        mapData.put("aData", bkuServices.getPaguEdit(param));
        return mapData;
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }

}
