package ebkus.ui.action;

import ebkus.model.BkuRinci;
import ebkus.model.BkuTransfer;
import ebkus.model.BukuKasUmum;
import ebkus.model.Pengguna;
import ebkus.services.BkuTransferBopServices;
import ebkus.services.BkuTransferBosServices;
import ebkus.util.BigDecimalPropertyEditor;
import ebkus.util.SipkdHelpers;
import ebkus.util.SqlDatePropertyEditor;
import java.io.IOException;
import java.io.InputStream;
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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Zainab
 */
@Controller
@RequestMapping("/bkutf")
public class BkuTransferAction {

    private static final Logger log = LoggerFactory.getLogger(BkuTransferAction.class);

    @Autowired
    ServletContext servletContext;
    @Autowired
    BkuTransferBopServices bkuBopServices;
    @Autowired
    BkuTransferBosServices bkuBosServices;

    private final RestTemplate rest;
    private final HttpHeaders headers;
    private HttpStatus status;

    public BkuTransferAction() {
        this.rest = new RestTemplate();
        this.headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "*/*");
    }

    @RequestMapping(value = "/json/transfersp2d", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    Map<String, Object> transfersp2d(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) throws IOException {
        HttpEntity<Map<String, Object>> requestPostData = new HttpEntity<Map<String, Object>>(mapdata);
        InputStream inputStream = servletContext.getResourceAsStream("/WEB-INF/txt/transfer.txt");
        String url = SipkdHelpers.readFromInputStream(inputStream);
        final Map< String, Object> param = new HashMap<String, Object>(3);
        ResponseEntity<Map> response = rest.exchange(url, HttpMethod.POST, requestPostData, Map.class);
//        if (response.hasBody()) {
//            param.put("idbku", mapdata.get("idbku"));
//            param.put("response", response.getBody());
//            param.put("kodesumbdana", mapdata.get("kodesumbdana"));
//            bkuBopServices.insertResponse(param);
//        }
        return response.getBody();
    }

    @RequestMapping(value = "/indexbkubop", method = RequestMethod.GET)
    public ModelAndView indexbop(final BukuKasUmum bku, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        return new ModelAndView("transfer/indextransferbop", "refbku", bku);
    }

    @RequestMapping(value = "/indexbkubos", method = RequestMethod.GET)
    public ModelAndView indexbos(final BukuKasUmum bku, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        return new ModelAndView("transfer/indextransferbos", "refbku", bku);
    }

    @RequestMapping(value = "/tfbop", method = RequestMethod.GET)
    public ModelAndView tfbop(final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = request.getParameter("tahun");
        final String idbku = request.getParameter("idbku");
        final String idskpd = request.getParameter("idskpd");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("idbku", idbku);
        param.put("tahun", tahun);

        BukuKasUmum bku = new BukuKasUmum();

        bku = bkuBopServices.getDataTf(param);
        bku.setIdskpd(SipkdHelpers.getIntFromString(idskpd));

        return new ModelAndView("transfer/transferbop", "refbku", bku);
    }

    @RequestMapping(value = "/tfbopva", method = RequestMethod.GET)
    public ModelAndView tfbopva(final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = request.getParameter("tahun");
        final String idbku = request.getParameter("idbku");
        final String idskpd = request.getParameter("idskpd");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("idbku", idbku);
        param.put("tahun", tahun);

        BukuKasUmum bku = new BukuKasUmum();

        bku = bkuBopServices.getDataTf(param);
        bku.setIdskpd(SipkdHelpers.getIntFromString(idskpd));

        return new ModelAndView("transfer/transfervabop", "refbku", bku);
    }

    @RequestMapping(value = "/json/listindexbop", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listindexbop(final HttpServletRequest request) {

        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");

        final String idsekolah = request.getParameter("idsekolah");
        final String tahun = request.getParameter("tahun");
        final String triwulan = request.getParameter("triwulan");
        final String nobku = request.getParameter("nobku");
        final String nobukti = request.getParameter("nobukti");

        param.put("idsekolah", idsekolah);
        param.put("tahun", tahun);
        param.put("triwulan", triwulan);
        param.put("nobku", nobku);
        param.put("nobukti", nobukti);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        final long banyak = bkuBopServices.getBanyakListTransfer(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", bkuBopServices.getListTransfer(param));

        return mapData;
    }

    @RequestMapping(value = "/json/getDataBankTujuan", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getDataBankTujuan(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idskpd = request.getParameter("idskpd");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("idskpd", idskpd);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        mapData.put("aData", bkuBopServices.getDataBankTujuan(param));
        return mapData;
    }

    @RequestMapping(value = "/json/listbkubop", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listbkubop(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");

        final String idbku = request.getParameter("idbku");

        param.put("idbku", idbku);
        param.put("tahun", tahunAnggaran);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        final long banyak = bkuBopServices.getBanyakListBkuRinci(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", bkuBopServices.getListBkuRinci(param));

        return mapData;
    }

    @RequestMapping(value = "/json/getDataBankBOP", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getDataBankBOP(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idsekolah = request.getParameter("idsekolah");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("idsekolah", idsekolah);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        mapData.put("aData", bkuBopServices.getDataBankBOP(param));
        return mapData;
    }

    @RequestMapping(value = "/json/getDataNpwp", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getDataNpwp(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idsekolah = request.getParameter("idsekolah");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("idsekolah", idsekolah);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        mapData.put("aData", bkuBopServices.getDataNpwp(param));
        return mapData;
    }

    @RequestMapping(value = "/json/getSaldoKasBop", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getSaldoKasBop(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idsekolah = request.getParameter("idsekolah");
        final String triwulan = request.getParameter("triwulan");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("idsekolah", idsekolah);
        param.put("tahun", tahunAnggaran);
        param.put("triwulan", triwulan);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        mapData.put("aData", bkuBopServices.getSaldoKas(param));
        return mapData;
    }

    @RequestMapping(value = "/json/bayarbkubop", method = RequestMethod.POST)
    public @ResponseBody
    String bayarbkubop(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        List<BkuRinci> listRinci = new ArrayList<>();

        BukuKasUmum bukukas = new BukuKasUmum();

        final String kodetransaksi = (String) mapdata.get("kodetransaksi");
        final String tglposting = (String) mapdata.get("tglposting");
        final String namarekan = (String) mapdata.get("namarekan");
        final String norek = (String) mapdata.get("norek");
        final String kodebank = (String) mapdata.get("kodebank");
        final String kodebanktf = (String) mapdata.get("kodebanktf");
        final String namabank = (String) mapdata.get("namabank");
        final String idbku = (String) mapdata.get("idbku");
        final String idsekolah = (String) mapdata.get("idsekolah");
        final String banyakpajak = (String) mapdata.get("banyakpajak");
        final String bit11 = (String) mapdata.get("bit11");

        if (kodetransaksi.substring(0, 1).equals("P")) {
            final String masapajak = (String) mapdata.get("masapajak");
            final String tahunpajak = (String) mapdata.get("tahunpajak");
            bukukas.setMasaPajak(masapajak);
            bukukas.setTahunPajak(tahunpajak);
        } else if (kodetransaksi.equals("JJ")) {
            List<Map<String, Object>> listpajak = (List) mapdata.get("listpajak");
            for (Map<String, Object> mapnilailist : listpajak) {

                BkuRinci bkurinci = new BkuRinci();

                Object idbkupajak = mapnilailist.get("idbku");
                Object kodetrx = mapnilailist.get("kodetrx");

                bkurinci.setIdBku(SipkdHelpers.getIntFromString(idbkupajak.toString()));
                bkurinci.setKodeTransaksi(kodetrx.toString());
                listRinci.add(bkurinci);
                bukukas.setListBkuRinci(listRinci);

            }
        }
        bukukas.setTahun(tahun);
        bukukas.setIdEntry(pengguna.getIdPengguna());
        bukukas.setKodeTransaksi(kodetransaksi);
        bukukas.setIdBku(SipkdHelpers.getIntFromString(idbku));
        bukukas.setIdsekolah(SipkdHelpers.getIntFromString(idsekolah));
        bukukas.setTglPosting(tglposting);
        bukukas.setNamaRekan(namarekan);
        bukukas.setNorekBank(norek);
        bukukas.setKodeBank(kodebank);
        bukukas.setKodeBankTf(kodebanktf);
        bukukas.setNamaBank(namabank);
        bukukas.setBanyakPajak(SipkdHelpers.getIntFromString(banyakpajak));

        bkuBopServices.updateBkuById(bukukas);

        return "Transaksi BKU Berhasil Dibayarkan";
    }

    @RequestMapping(value = "/listpajakbop", method = RequestMethod.GET)
    public ModelAndView listsetoranbop(final BukuKasUmum bku, final HttpServletRequest request, final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return new ModelAndView("transfer/listpajakbop", "refkegiatan", bku);
    }

    @RequestMapping(value = "/json/listpajakspjbop", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listpajakspjbop(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");

        final String idblrinci = request.getParameter("idblrinci");
        final String nobkuref = request.getParameter("nobkuref");
        final String idsekolah = request.getParameter("idsekolah");

        param.put("idsekolah", idsekolah);
        param.put("nobkuref", nobkuref);
        param.put("idblrinci", idblrinci);
        param.put("tahun", tahunAnggaran);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        final long banyak = bkuBopServices.getBanyakListPajak(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", bkuBopServices.getListPajak(param));

        return mapData;
    }

    @RequestMapping(value = "/json/prosessimpanpajakbop", method = RequestMethod.POST)
    public @ResponseBody
    String prosessimpanpajakbop(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        List<Map<String, Object>> nilailist = (List) mapdata.get("nilailist");
        List<BkuRinci> listRinci = new ArrayList<>();
        BukuKasUmum bukukas = new BukuKasUmum();

        final String idsekolah = (String) mapdata.get("idsekolah");
        final String nobukti = (String) mapdata.get("nobukti");
        final String tgldok = (mapdata.get("tgldok")).toString();
        final String jenis = (String) mapdata.get("jenis");
        final String beban = (String) mapdata.get("beban");
        final String idkegiatan = (String) mapdata.get("idkegiatan");
        final String kodekegiatan = (String) mapdata.get("kodekeg");
        final String filling = (String) mapdata.get("fileinbox");
        final String carabayar = (String) mapdata.get("carabayar");
        final String nipPPTK = (String) mapdata.get("nippptk");
        final String namaPPTK = (String) mapdata.get("namapptk");
        final String nrk = (String) mapdata.get("nrk");
        final String namarekan = (String) mapdata.get("namarekan");
        final String npwp = (String) mapdata.get("npwp");
        final String masapajak = (String) mapdata.get("masapajak");
        final String tahunpajak = (String) mapdata.get("tahunpajak");
        final String triwulan = (String) mapdata.get("triwulan");
        final String nobkuref = (String) mapdata.get("nobkuref");
        final String bkututup = (String) mapdata.get("bkututup");
        final String bkustatus = (String) mapdata.get("bkustatus");
        final String tglposting = (String) mapdata.get("tglposting");
        final String idspj = (String) mapdata.get("idspj");
        final String idblrinci = (String) mapdata.get("idblrinci");

        bukukas.setIdTransaksi(idspj);
        bukukas.setTahun(tahun);
        bukukas.setTglPosting(tglposting);
        bukukas.setIdsekolah(SipkdHelpers.getIntFromString(idsekolah.toString()));
        bukukas.setIdEntry(pengguna.getIdPengguna());
        bukukas.setNoDok(nobukti);
        bukukas.setTglDok(SipkdHelpers.getDateFromString(tgldok));
        bukukas.setJenis(jenis);
        bukukas.setBeban(beban);
        bukukas.setIdKegiatan(SipkdHelpers.getIntFromString(idkegiatan));
        bukukas.setKodeKeg(kodekegiatan);
        bukukas.setInboxFile(filling);
        bukukas.setKodeWilayah(pengguna.getKodeProses());
        bukukas.setKodePembayaran(carabayar);
        bukukas.setKodeUangPersediaan(carabayar);
        bukukas.setNipPptk(nipPPTK);
        bukukas.setNamaPptk(namaPPTK);
        bukukas.setNrkPptk(nrk);
        bukukas.setNamaRekan(namarekan);
        bukukas.setNpwp(npwp);
        bukukas.setMasaPajak(masapajak);
        bukukas.setTahunPajak(tahunpajak);
        bukukas.setTriwulan(triwulan);
        bukukas.setNoBkuRef(nobkuref);
        bukukas.setKodeKoreksi("0");
        bukukas.setBkuStatus(bkustatus);
        bukukas.setKodeTutup(bkututup);
        bukukas.setIdBl(SipkdHelpers.getIntFromString(idblrinci.toString()));

        for (Map<String, Object> mapnilailist : nilailist) {

            BkuRinci bkurinci = new BkuRinci();

            Object nilaimasuk = mapnilailist.get("nilaimasuk");
            Object nilaikeluar = mapnilailist.get("nilaikeluar");

            Object kodetransaksi = mapnilailist.get("kodetransaksi");
            Object uraian = mapnilailist.get("uraian");

            final String idbas = (String) mapdata.get("idbas");
            final String kodeakun = (String) mapdata.get("kodeakun");
            final String idkomponen = (String) mapdata.get("idkomponen");

            //bukukas.setKodeTransaksi(kodetransaksi.toString());
            //bukukas.setUraian(uraian.toString());
            //bukukas.setUraianBukti(uraian.toString());
            bkurinci.setUraian(uraian.toString());
            bkurinci.setKeterangan(kodetransaksi.toString());
            bkurinci.setNilaiMasuk(SipkdHelpers.getBigDecimalFromString(nilaimasuk.toString()));
            bkurinci.setNilaiKeluar(SipkdHelpers.getBigDecimalFromString(nilaikeluar.toString()));
            bkurinci.setIdBas(SipkdHelpers.getIntFromString(idbas.toString()));
            bkurinci.setKodeakun(kodeakun.toString());
            bkurinci.setIdEntry(pengguna.getIdPengguna());
            bkurinci.setIdKomponen(SipkdHelpers.getIntFromString(idkomponen.toString()));
            bkurinci.setIdBlRinci(SipkdHelpers.getIntFromString(idblrinci.toString()));

            listRinci.add(bkurinci);

        }

        bukukas.setListBkuRinci(listRinci);
        bkuBopServices.insertBkuPajak(bukukas);

        return "Data Buku Kas Umum Berhasil Disimpan";
    }

    @RequestMapping(value = "/json/getDataPajakSpjBop", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getDataPajakSpjBop(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idsekolah = request.getParameter("idsekolah");
        final String idblrinci = request.getParameter("idblrinci");
        final String nobkuref = request.getParameter("nobkuref");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("idsekolah", idsekolah);
        param.put("tahun", tahunAnggaran);
        param.put("nobkuref", nobkuref);
        param.put("idblrinci", idblrinci);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        mapData.put("aData", bkuBopServices.getDataPajakSpj(param));
        return mapData;
    }

    @RequestMapping(value = "/json/getNilaiSpjNettoBop", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getNilaiSpjNettoBop(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idsekolah = request.getParameter("idsekolah");
        final String idbku = request.getParameter("idbku");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("idsekolah", idsekolah);
        param.put("tahun", tahunAnggaran);
        param.put("idbku", idbku);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        mapData.put("aData", bkuBopServices.getNilaiSpjNetto(param));
        return mapData;
    }

    @RequestMapping(value = "/json/getKodeStan", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getKodeStan(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        mapData.put("aData", bkuBopServices.getKodeStan(param));
        return mapData;
    }

    @RequestMapping(value = "/json/simpanbkubank", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> simpanbkubank(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        final String idbku = (String) mapdata.get("idbku");
        final String nilaibayar = (String) mapdata.get("nilaibayar");
        final String msgkirim = (String) mapdata.get("msgkirim");
        final String tahun = (String) mapdata.get("tahun");
        final String bit4 = (String) mapdata.get("bit4");
        final String bit11 = (String) mapdata.get("bit11");
        final String bit12 = (String) mapdata.get("bit12");
        final String bit13 = (String) mapdata.get("bit13");
        final String bit37 = (String) mapdata.get("bit37");
        final String kodebank = (String) mapdata.get("kodebank");
        final String norektujuan = (String) mapdata.get("norektujuan");
        final String namatujuan = (String) mapdata.get("namatujuan");
        final String norekpengirim = (String) mapdata.get("norekpengirim");
        final String namapengirim = (String) mapdata.get("namapengirim");
        final String kodewil = (String) mapdata.get("kodewil");
        final String keterangan = (String) mapdata.get("keterangan");
        final String idsekolah = (String) mapdata.get("idsekolah");
        final String kodeakun = (String) mapdata.get("kodeakun");
        final String npsn = (String) mapdata.get("npsn");
        final String namasekolah = (String) mapdata.get("namasekolah");
        final String kodekomponen = (String) mapdata.get("kodekomponen");
        final String token = (String) mapdata.get("token");
        final String kodesumbdana = (String) mapdata.get("kodesumbdana");
        final String bit62 = (String) mapdata.get("bit62");

        BkuTransfer bku = new BkuTransfer();

        bku.setIdEntry(pengguna.getIdPengguna());
        bku.setTahun(tahun);
        bku.setNilaiTransfer(SipkdHelpers.getBigDecimalFromString(nilaibayar));
        bku.setIdBku(SipkdHelpers.getIntFromString(idbku.toString()));
        bku.setMsgKirim(msgkirim);
        bku.setBit4(bit4);
        bku.setBit11(bit11);
        bku.setBit12(bit12);
        bku.setBit13(bit13);
        bku.setBit37(bit37);
        bku.setKodeBank(kodebank);
        bku.setNorekTujuan(norektujuan);
        bku.setNamaTujuan(namatujuan);
        bku.setNorekPengirim(norekpengirim);
        bku.setNamaPengirim(namapengirim);
        bku.setWilayah(kodewil);
        bku.setUraian(keterangan);
        bku.setIdsekolah(SipkdHelpers.getIntFromString(idsekolah.toString()));
        bku.setKodeAkun(kodeakun);
        bku.setKodeKomponen(kodekomponen);
        bku.setNamaSekolah(namasekolah);
        bku.setNpsn(npsn);
        bku.setToken(token);
        bku.setKodeSumbdana(kodesumbdana);
        bku.setBit62(bit62);

        String pesan, kode;
        try {
            bkuBopServices.insertBkuBank(bku);
            kode = "1";
            pesan = "berhasil";
        } catch (Exception e) {
            //log.error("BKU TRANSFER =============== "+e.getMessage());
            e.printStackTrace();
            kode = "9";
            pesan = e.getMessage().toString();
            bku.setPesanError(pesan);
            bku.setKodeAction("INS - BANK");
            bkuBopServices.insertBkuBankError(bku);
        }

        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        mapData.put("kode", kode);
        mapData.put("pesan", pesan);

        return mapData;
    }

    @RequestMapping(value = "/json/updatebkubank", method = RequestMethod.POST)
    public @ResponseBody
    String updatebkubank(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        final String idbku = (String) mapdata.get("idbku");
        final String nilaibayarbank = (String) mapdata.get("nilaibayarbank");
        final String msgterimabank = (String) mapdata.get("msgterimabank");
        final String tahun = (String) mapdata.get("tahun");
        final String idsekolah = (String) mapdata.get("idsekolah");
        final String bit11 = (String) mapdata.get("bit11");
        final String kodesumbdana = (String) mapdata.get("kodesumbdana");
        final String trxterimabank = (String) mapdata.get("trxterimabank");
        final String tglprosesbank = (String) mapdata.get("tglprosesbank");
        final String statusbank = (String) mapdata.get("statusbank");

        BkuTransfer bku = new BkuTransfer();

        bku.setIdEntry(pengguna.getIdPengguna());
        bku.setTahun(tahun);
        bku.setNilaiTransfer(SipkdHelpers.getBigDecimalFromString(nilaibayarbank));
        bku.setIdBku(SipkdHelpers.getIntFromString(idbku.toString()));
        bku.setMsgTerimaBank(msgterimabank);
        bku.setTrxTerimaBank(trxterimabank);
        bku.setTglProses(tglprosesbank);
        bku.setStatusBank(statusbank);
        bku.setBit11(bit11);
        bku.setKodeSumbdana(kodesumbdana);
        bku.setIdsekolah(SipkdHelpers.getIntFromString(idsekolah.toString()));

        try {
            bkuBopServices.updateBkuBank(bku);
        } catch (Exception e) {
            //log.error("BKU TRANSFER =============== "+e.getMessage());
            e.printStackTrace();
            bku.setPesanError(e.getMessage().toString());
            bku.setKodeAction("UPD - BANK");
            bkuBopServices.insertBkuBankError(bku);
        }

        return "Data BKU Bank Berhasil Diupdate";
    }

    @RequestMapping(value = "/json/updatebkubankbos", method = RequestMethod.POST)
    public @ResponseBody
    String updatebkubankbos(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        final String idbku = (String) mapdata.get("idbku");
        final String nilaibayarbank = (String) mapdata.get("nilaibayarbank");
        final String msgterimabank = (String) mapdata.get("msgterimabank");
        final String tahun = (String) mapdata.get("tahun");
        final String idsekolah = (String) mapdata.get("idsekolah");
        final String bit11 = (String) mapdata.get("bit11");
        final String kodesumbdana = (String) mapdata.get("kodesumbdana");
        final String trxterimabank = (String) mapdata.get("trxterimabank");
        final String tglprosesbank = (String) mapdata.get("tglprosesbank");
        final String statusbank = (String) mapdata.get("statusbank");

        BkuTransfer bku = new BkuTransfer();

        bku.setIdEntry(pengguna.getIdPengguna());
        bku.setTahun(tahun);
        bku.setNilaiTransfer(SipkdHelpers.getBigDecimalFromString(nilaibayarbank));
        bku.setIdBku(SipkdHelpers.getIntFromString(idbku.toString()));
        bku.setMsgTerimaBank(msgterimabank);
        bku.setTrxTerimaBank(trxterimabank);
        bku.setTglProses(tglprosesbank);
        bku.setStatusBank(statusbank);
        bku.setBit11(bit11);
        bku.setKodeSumbdana(kodesumbdana);
        bku.setIdsekolah(SipkdHelpers.getIntFromString(idsekolah.toString()));

        try {
            bkuBosServices.updateBkuBank(bku);
        } catch (Exception e) {
            //log.error("BKU TRANSFER =============== "+e.getMessage());
            e.printStackTrace();
            bku.setPesanError(e.getMessage().toString());
            bku.setKodeAction("UPD - BANK");
            bkuBosServices.insertBkuBankError(bku);
        }

        return "Data BKU Bank Berhasil Diupdate";
    }

    @RequestMapping(value = "/tfbos", method = RequestMethod.GET)
    public ModelAndView tfbos(final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = request.getParameter("tahun");
        final String idbku = request.getParameter("idbku");
        final String idskpd = request.getParameter("idskpd");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("idbku", idbku);
        param.put("tahun", tahun);

        BukuKasUmum bku = new BukuKasUmum();

        bku = bkuBosServices.getDataTf(param);
        bku.setIdskpd(SipkdHelpers.getIntFromString(idskpd));

        return new ModelAndView("transfer/transferbos", "refbku", bku);
    }

    @RequestMapping(value = "/tfbosva", method = RequestMethod.GET)
    public ModelAndView tfbosva(final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = request.getParameter("tahun");
        final String idbku = request.getParameter("idbku");
        final String idskpd = request.getParameter("idskpd");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("idbku", idbku);
        param.put("tahun", tahun);

        BukuKasUmum bku = new BukuKasUmum();

        bku = bkuBosServices.getDataTf(param);
        bku.setIdskpd(SipkdHelpers.getIntFromString(idskpd));

        return new ModelAndView("transfer/transfervabos", "refbku", bku);
    }

    @RequestMapping(value = "/json/listindexbos", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listindexbos(final HttpServletRequest request) {

        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");

        final String idsekolah = request.getParameter("idsekolah");
        final String tahun = request.getParameter("tahun");
        final String triwulan = request.getParameter("triwulan");
        final String nobku = request.getParameter("nobku");
        final String nobukti = request.getParameter("nobukti");

        param.put("idsekolah", idsekolah);
        param.put("tahun", tahun);
        param.put("triwulan", triwulan);
        param.put("nobku", nobku);
        param.put("nobukti", nobukti);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        final long banyak = bkuBosServices.getBanyakListTransfer(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", bkuBosServices.getListTransfer(param));

        return mapData;
    }

    @RequestMapping(value = "/json/listbkubos", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listbkubos(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");

        final String idbku = request.getParameter("idbku");

        param.put("idbku", idbku);
        param.put("tahun", tahunAnggaran);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        final long banyak = bkuBosServices.getBanyakListBkuRinci(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", bkuBosServices.getListBkuRinci(param));

        return mapData;
    }

    @RequestMapping(value = "/json/getDataBankBOS", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getDataBankBOS(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idsekolah = request.getParameter("idsekolah");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("idsekolah", idsekolah);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        mapData.put("aData", bkuBosServices.getDataBankBOS(param));
        return mapData;
    }

    @RequestMapping(value = "/json/getSaldoKasBos", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getSaldoKasBos(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idsekolah = request.getParameter("idsekolah");
        final String triwulan = request.getParameter("triwulan");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("idsekolah", idsekolah);
        param.put("tahun", tahunAnggaran);
        param.put("triwulan", triwulan);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        mapData.put("aData", bkuBosServices.getSaldoKas(param));
        return mapData;
    }

    @RequestMapping(value = "/json/bayarbkubos", method = RequestMethod.POST)
    public @ResponseBody
    String bayarbkubos(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        List<BkuRinci> listRinci = new ArrayList<>();

        BukuKasUmum bukukas = new BukuKasUmum();

        final String kodetransaksi = (String) mapdata.get("kodetransaksi");
        final String tglposting = (String) mapdata.get("tglposting");
        final String namarekan = (String) mapdata.get("namarekan");
        final String norek = (String) mapdata.get("norek");
        final String kodebank = (String) mapdata.get("kodebank");
        final String kodebanktf = (String) mapdata.get("kodebanktf");
        final String namabank = (String) mapdata.get("namabank");
        final String idbku = (String) mapdata.get("idbku");
        final String idsekolah = (String) mapdata.get("idsekolah");
        final String banyakpajak = (String) mapdata.get("banyakpajak");

        if (kodetransaksi.substring(0, 1).equals("P")) {
            final String masapajak = (String) mapdata.get("masapajak");
            final String tahunpajak = (String) mapdata.get("tahunpajak");
            bukukas.setMasaPajak(masapajak);
            bukukas.setTahunPajak(tahunpajak);
        } else if (kodetransaksi.equals("JJ")) {
            List<Map<String, Object>> listpajak = (List) mapdata.get("listpajak");

            for (Map<String, Object> mapnilailist : listpajak) {

                BkuRinci bkurinci = new BkuRinci();

                Object idbkupajak = mapnilailist.get("idbku");
                Object kodetrx = mapnilailist.get("kodetrx");

                bkurinci.setIdBku(SipkdHelpers.getIntFromString(idbkupajak.toString()));
                bkurinci.setKodeTransaksi(kodetrx.toString());
                listRinci.add(bkurinci);
                bukukas.setListBkuRinci(listRinci);
            }
        }
        bukukas.setTahun(tahun);
        bukukas.setIdEntry(pengguna.getIdPengguna());
        bukukas.setKodeTransaksi(kodetransaksi);
        bukukas.setIdBku(SipkdHelpers.getIntFromString(idbku));
        bukukas.setIdsekolah(SipkdHelpers.getIntFromString(idsekolah));
        bukukas.setTglPosting(tglposting);
        bukukas.setNamaRekan(namarekan);
        bukukas.setNorekBank(norek);
        bukukas.setKodeBank(kodebank);
        bukukas.setKodeBankTf(kodebanktf);
        bukukas.setNamaBank(namabank);
        bukukas.setBanyakPajak(SipkdHelpers.getIntFromString(banyakpajak));

        bkuBosServices.updateBkuById(bukukas);

        return "Transaksi BKU Berhasil Dibayarkan";
    }

    @RequestMapping(value = "/listpajakbos", method = RequestMethod.GET)
    public ModelAndView listsetoranbos(final BukuKasUmum bku, final HttpServletRequest request, final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return new ModelAndView("transfer/listpajakbos", "refkegiatan", bku);
    }

    @RequestMapping(value = "/json/listpajakspjbos", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listpajakspjbos(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");

        final String idblrinci = request.getParameter("idblrinci");
        final String nobkuref = request.getParameter("nobkuref");
        final String idsekolah = request.getParameter("idsekolah");

        param.put("idsekolah", idsekolah);
        param.put("nobkuref", nobkuref);
        param.put("idblrinci", idblrinci);
        param.put("tahun", tahunAnggaran);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        final long banyak = bkuBosServices.getBanyakListPajak(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", bkuBosServices.getListPajak(param));

        return mapData;
    }

    @RequestMapping(value = "/json/prosessimpanpajakbos", method = RequestMethod.POST)
    public @ResponseBody
    String prosessimpanpajakbos(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        List<Map<String, Object>> nilailist = (List) mapdata.get("nilailist");
        List<BkuRinci> listRinci = new ArrayList<>();
        BukuKasUmum bukukas = new BukuKasUmum();

        final String idsekolah = (String) mapdata.get("idsekolah");
        final String nobukti = (String) mapdata.get("nobukti");
        final String tgldok = (mapdata.get("tgldok")).toString();
        final String jenis = (String) mapdata.get("jenis");
        final String beban = (String) mapdata.get("beban");
        final String idkegiatan = (String) mapdata.get("idkegiatan");
        final String kodekegiatan = (String) mapdata.get("kodekeg");
        final String filling = (String) mapdata.get("fileinbox");
        final String carabayar = (String) mapdata.get("carabayar");
        final String nipPPTK = (String) mapdata.get("nippptk");
        final String namaPPTK = (String) mapdata.get("namapptk");
        final String nrk = (String) mapdata.get("nrk");
        final String namarekan = (String) mapdata.get("namarekan");
        final String npwp = (String) mapdata.get("npwp");
        final String masapajak = (String) mapdata.get("masapajak");
        final String tahunpajak = (String) mapdata.get("tahunpajak");
        final String triwulan = (String) mapdata.get("triwulan");
        final String nobkuref = (String) mapdata.get("nobkuref");
        final String bkututup = (String) mapdata.get("bkututup");
        final String bkustatus = (String) mapdata.get("bkustatus");
        final String tglposting = (String) mapdata.get("tglposting");
        final String idspj = (String) mapdata.get("idspj");
        final String idblrinci = (String) mapdata.get("idblrinci");

        bukukas.setIdTransaksi(idspj);
        bukukas.setTahun(tahun);
        bukukas.setTglPosting(tglposting);
        bukukas.setIdsekolah(SipkdHelpers.getIntFromString(idsekolah.toString()));
        bukukas.setIdEntry(pengguna.getIdPengguna());
        bukukas.setNoDok(nobukti);
        bukukas.setTglDok(SipkdHelpers.getDateFromString(tgldok));
        bukukas.setJenis(jenis);
        bukukas.setBeban(beban);
        bukukas.setIdKegiatan(SipkdHelpers.getIntFromString(idkegiatan));
        bukukas.setKodeKeg(kodekegiatan);
        bukukas.setInboxFile(filling);
        bukukas.setKodeWilayah(pengguna.getKodeProses());
        bukukas.setKodePembayaran(carabayar);
        bukukas.setKodeUangPersediaan(carabayar);
        bukukas.setNipPptk(nipPPTK);
        bukukas.setNamaPptk(namaPPTK);
        bukukas.setNrkPptk(nrk);
        bukukas.setNamaRekan(namarekan);
        bukukas.setNpwp(npwp);
        bukukas.setMasaPajak(masapajak);
        bukukas.setTahunPajak(tahunpajak);
        bukukas.setTriwulan(triwulan);
        bukukas.setNoBkuRef(nobkuref);
        bukukas.setKodeKoreksi("0");
        bukukas.setBkuStatus(bkustatus);
        bukukas.setKodeTutup(bkututup);
        bukukas.setIdBl(SipkdHelpers.getIntFromString(idblrinci.toString()));

        for (Map<String, Object> mapnilailist : nilailist) {

            BkuRinci bkurinci = new BkuRinci();

            Object nilaimasuk = mapnilailist.get("nilaimasuk");
            Object nilaikeluar = mapnilailist.get("nilaikeluar");

            Object kodetransaksi = mapnilailist.get("kodetransaksi");
            Object uraian = mapnilailist.get("uraian");

            final String idbas = (String) mapdata.get("idbas");
            final String kodeakun = (String) mapdata.get("kodeakun");
            final String idkomponen = (String) mapdata.get("idkomponen");

            //bukukas.setKodeTransaksi(kodetransaksi.toString());
            //bukukas.setUraian(uraian.toString());
            //bukukas.setUraianBukti(uraian.toString());
            bkurinci.setUraian(uraian.toString());
            bkurinci.setKeterangan(kodetransaksi.toString());
            bkurinci.setNilaiMasuk(SipkdHelpers.getBigDecimalFromString(nilaimasuk.toString()));
            bkurinci.setNilaiKeluar(SipkdHelpers.getBigDecimalFromString(nilaikeluar.toString()));
            bkurinci.setIdBas(SipkdHelpers.getIntFromString(idbas.toString()));
            bkurinci.setKodeakun(kodeakun.toString());
            bkurinci.setIdEntry(pengguna.getIdPengguna());
            bkurinci.setIdKomponen(SipkdHelpers.getIntFromString(idkomponen.toString()));
            bkurinci.setIdBlRinci(SipkdHelpers.getIntFromString(idblrinci.toString()));

            listRinci.add(bkurinci);

        }

        bukukas.setListBkuRinci(listRinci);
        bkuBosServices.insertBkuPajak(bukukas);

        return "Data Buku Kas Umum Berhasil Disimpan";
    }

    @RequestMapping(value = "/json/getDataPajakSpjBos", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getDataPajakSpjBos(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idsekolah = request.getParameter("idsekolah");
        final String idblrinci = request.getParameter("idblrinci");
        final String nobkuref = request.getParameter("nobkuref");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("idsekolah", idsekolah);
        param.put("tahun", tahunAnggaran);
        param.put("nobkuref", nobkuref);
        param.put("idblrinci", idblrinci);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        mapData.put("aData", bkuBosServices.getDataPajakSpj(param));
        return mapData;
    }

    @RequestMapping(value = "/json/getNilaiSpjNettoBos", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getNilaiSpjNettoBos(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idsekolah = request.getParameter("idsekolah");
        final String idbku = request.getParameter("idbku");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("idsekolah", idsekolah);
        param.put("tahun", tahunAnggaran);
        param.put("idbku", idbku);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        mapData.put("aData", bkuBosServices.getNilaiSpjNetto(param));
        return mapData;
    }

    @RequestMapping(value = "/json/getListPajakPnBos", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getListPajakPnBos(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idsekolah = request.getParameter("idsekolah");
        final String nobkuref = request.getParameter("nobkuref");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("idsekolah", idsekolah);
        param.put("nobkuref", nobkuref);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        mapData.put("aData", bkuBosServices.getListPajakPnBos(param));
        return mapData;
    }

    @RequestMapping(value = "/json/getListPajakPnBop", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getListPajakPnBop(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idsekolah = request.getParameter("idsekolah");
        final String nobkuref = request.getParameter("nobkuref");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("idsekolah", idsekolah);
        param.put("nobkuref", nobkuref);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        mapData.put("aData", bkuBopServices.getListPajakPnBop(param));
        return mapData;
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }

}
