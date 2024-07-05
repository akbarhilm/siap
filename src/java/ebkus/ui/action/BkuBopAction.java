package ebkus.ui.action;

import ebkus.config.DefaultTrustManager;
import ebkus.model.BkuRinci;
import ebkus.model.BukuKasUmum;
import ebkus.model.PajakTransfer;
import ebkus.model.Pengguna;
import ebkus.services.BkuBopServices;
import ebkus.services.PajakTransferBopServices;
import ebkus.util.BigDecimalPropertyEditor;
import ebkus.util.PrintReportTemplate;
import ebkus.util.SipkdHelpers;
import ebkus.util.SqlDatePropertyEditor;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.catalina.util.Base64;
import org.json.JSONObject;
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
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Zainab
 */
@Controller
@RequestMapping("/bkubop")
public class BkuBopAction extends PrintReportTemplate {

    private static final Logger log = LoggerFactory.getLogger(BkuBopAction.class);

    @Autowired
    BkuBopServices bkuServices;
    @Autowired
    PajakTransferBopServices pajakServices;

    @Autowired
    ServletContext servletContext;

    @RequestMapping(value = "/indexbkubop", method = RequestMethod.GET)
    public ModelAndView index(final BukuKasUmum bku, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        return new ModelAndView("bkubop/indexbkubop", "refbku", bku);
    }

    @RequestMapping(value = "/sudinbkubop", method = RequestMethod.GET)
    public ModelAndView indexsudin(final BukuKasUmum bku, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        return new ModelAndView("bkubop/sudinbkubop", "refbku", bku);
    }

    @RequestMapping(value = "/json/inquirynpwp", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    Object inquirynpwp(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        try {
            final String npwp = (String) mapdata.get("npwp");
            InputStream inputStream = servletContext.getResourceAsStream("/WEB-INF/txt/npwp.txt");
            String resourceUrl = SipkdHelpers.readFromInputStream(inputStream);
            SSLContext sslctx = SSLContext.getInstance("SSL");
            sslctx.init(null, new X509TrustManager[]{new DefaultTrustManager()
            }, null);
            HttpsURLConnection.setDefaultSSLSocketFactory(sslctx.getSocketFactory()
            );

            String urlParameter = "?npwp=" + npwp;
            String url = (resourceUrl.split("\\|")[0] + urlParameter);
            String username = resourceUrl.split("\\|")[1];
            String password = resourceUrl.split("\\|")[2];
            HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
            con.setDoOutput(true);
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", "Basic " + Base64.encode((username + ":" + password).getBytes()));

            byte[] output;
            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            String c;
            StringBuilder sb = new StringBuilder();
            while ((c = reader.readLine()) != null) {
                sb.append(c);
            }
            Map<String, String> mapData = new HashMap<String, String>();
            Object returnObject;
            JSONObject jsonData = new JSONObject(sb.toString());
            if (jsonData.getJSONObject("RespHeader").getString("respCode").equals("1")) {
                Iterator<String> nameItr = jsonData.getJSONObject("RespBody").keys();
                while (nameItr.hasNext()) {
                    String name = nameItr.next();
                    if (name.equals("NPWP") || name.equals("NAMA") || name.equals("ALAMAT")
                            || name.equals("KABKOT") || name.equals("STATUS_PKP")) {
                        if (name.equals("NAMA") && jsonData.getJSONObject("RespBody").getString(name).length() > 75) {
                            mapData.put(name, jsonData.getJSONObject("RespBody").getString(name).substring(0, 75));
                        } else if (name.equals("ALAMAT") && jsonData.getJSONObject("RespBody").getString(name).length() > 100) {
                            mapData.put(name, jsonData.getJSONObject("RespBody").getString(name).substring(0, 100));
                        } else {
                            mapData.put(name, jsonData.getJSONObject("RespBody").getString(name));
                        }
                    }
                }
                returnObject = mapData;
            } else {
                mapData.put("error", jsonData.getJSONObject("RespHeader").getString("respDesc"));
                returnObject = mapData;
            }
            reader.close();
            con.disconnect();

            return returnObject;
        } catch (Exception ex) {
            log.debug("ERROR - " + ex.getMessage());
        }
        return "ERROR";
    }

    @RequestMapping(value = "/json/getpegawai", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    Map<String, String> getpegawai(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        try {
            InputStream inputStream = servletContext.getResourceAsStream("/WEB-INF/txt/simpeg.txt");
            String resourceUrl = SipkdHelpers.readFromInputStream(inputStream);
            String urlParameter = "?nrk=" + mapdata.get("nrk");

            String url = (resourceUrl.split("\\|")[0] + urlParameter);
            String username = resourceUrl.split("\\|")[1];
            String password = resourceUrl.split("\\|")[2];
            HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
            con.setDoOutput(true);
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", "Basic " + Base64.encode((username + ":" + password).getBytes()));

            byte[] output;
            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            String c;
            StringBuilder sb = new StringBuilder();
            while ((c = reader.readLine()) != null) {
                if (!c.contains("]") && !c.contains("[")) {
                    sb.append(c);
                }
            }
            JSONObject json = new JSONObject(sb.toString());
            Iterator<String> nameItr = json.keys();
            Map<String, String> mapData = new HashMap<String, String>();
            while (nameItr.hasNext()) {
                String name = nameItr.next();
                mapData.put(name, json.getString(name));
            }
//            String[] splited = sb.toString().split(":");
//            final Map<String, Object> mapData = new HashMap<String, Object>();
//            mapData.put(splited[1].split("\"")[1], splited[2].split("\"")[1]);
//            mapData.put(splited[2].split("\"")[3], splited[3].split("\"")[1]);
//            mapData.put(splited[3].split("\"")[3], splited[4].split("\"")[1]);
//            mapData.put(splited[4].split("\"")[3], splited[5].split("\"")[1]);
//            mapData.put(splited[5].split("\"")[3], splited[6].split("\"")[1]);
            reader.close();
            con.disconnect();
            return mapData;
        } catch (Exception ex) {
            log.debug("ERROR - " + ex.getMessage());
        }
        return null;
    }

    @RequestMapping(value = "/addbkubop", method = RequestMethod.GET)
    public ModelAndView addbku(final BukuKasUmum bku, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        return new ModelAndView("bkubop/addbkubop", "refbku", bku);
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
        final String tipe = request.getParameter("tipe");

        param.put("idsekolah", idsekolah);
        param.put("tahun", tahun);
        param.put("triwulan", triwulan);
        param.put("tipe", tipe);
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

    @RequestMapping(value = "/json/getTotal", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getTotal(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String kodetotal = request.getParameter("kodetotal");
        final String idsekolah = request.getParameter("idsekolah");
        final String triwulan = request.getParameter("triwulan");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("idsekolah", idsekolah);
        param.put("triwulan", triwulan);
        param.put("kodetotal", kodetotal);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        mapData.put("aData", bkuServices.getNilaiIndex(param));
        return mapData;
    }

    @RequestMapping(value = "/json/getNpwp", method = RequestMethod.GET)
    public @ResponseBody
    String getNpwp(final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        return pengguna.getSekolah().getNoNPWP();
    }

    @RequestMapping(value = "/json/prosessimpanc12", method = RequestMethod.POST)
    public @ResponseBody
    String prosessimpanc12(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");

        final String idsekolah = (String) mapdata.get("idsekolah");
        final String kodetrans = (String) mapdata.get("kodetransaksi");
        final String nobukti = (String) mapdata.get("nobukti");
        final String tgldok = (mapdata.get("tgldok")).toString();
        final String jenis = (String) mapdata.get("jenis");
        final String beban = (String) mapdata.get("beban");
        final String nilai = (String) mapdata.get("nilai");
        final String filling = (String) mapdata.get("fileinbox");
        final String nrk = (String) mapdata.get("nrk");
        final String namapptk = (String) mapdata.get("namapptk");
        final String nippptk = (String) mapdata.get("nippptk");
        final String uraian = (String) mapdata.get("uraian");
        final String idbas1 = (String) mapdata.get("idbas1");
        final String idbas2 = (String) mapdata.get("idbas2");
        final String kodeakun1 = (String) mapdata.get("kodeakun1");
        final String kodeakun2 = (String) mapdata.get("kodeakun2");
        final String triwulan = (String) mapdata.get("triwulan");
        final String bkututup = (String) mapdata.get("bkututup");
        final String bkustatus = (String) mapdata.get("bkustatus");

        List<BukuKasUmum> bkus = new ArrayList<>();
        BukuKasUmum bukukas1 = new BukuKasUmum();
        BukuKasUmum bukukas2 = new BukuKasUmum();

        //BKU 1
        bukukas1.setTahun(tahun);
        bukukas1.setIdsekolah(SipkdHelpers.getIntFromString(idsekolah.toString()));
        bukukas1.setIdEntry(pengguna.getIdPengguna());
        bukukas1.setKodeTransaksi(kodetrans);
        bukukas1.setNoDok(nobukti);
        bukukas1.setTglDok(SipkdHelpers.getDateFromString(tgldok));
        bukukas1.setJenis(jenis);
        bukukas1.setBeban(beban);

        bukukas1.setInboxFile(filling);
        bukukas1.setNrkPptk(nrk);
        bukukas1.setNamaPptk(namapptk);
        bukukas1.setNipPptk(nippptk);
        bukukas1.setUraian(uraian);
        bukukas1.setUraianBukti(uraian);
        //bukukas.setKodeWilayah(pengguna.getKodeProses());
        //bukukas.setKodePembayaran(carabayar);
        bukukas1.setKodeKoreksi("0");
        bukukas1.setTriwulan(triwulan);
        bukukas1.setBkuStatus(bkustatus);
        bukukas1.setKodeTutup(bkututup);

        //BKU 2
        bukukas2.setTahun(tahun);
        bukukas2.setIdsekolah(SipkdHelpers.getIntFromString(idsekolah.toString()));
        bukukas2.setIdEntry(pengguna.getIdPengguna());
        bukukas2.setKodeTransaksi(kodetrans);
        bukukas2.setNoDok(nobukti);
        bukukas2.setTglDok(SipkdHelpers.getDateFromString(tgldok));
        bukukas2.setJenis(jenis);
        bukukas2.setBeban(beban);
        bukukas2.setInboxFile(filling);
        bukukas2.setNrkPptk(nrk);
        bukukas2.setNamaPptk(namapptk);
        bukukas2.setNipPptk(nippptk);
        bukukas2.setUraian(uraian);
        bukukas2.setUraianBukti(uraian);
        //bukukas.setKodeWilayah(pengguna.getKodeProses());
        //bukukas.setKodePembayaran(carabayar);
        bukukas2.setKodeKoreksi("0");
        bukukas2.setTriwulan(triwulan);
        bukukas2.setBkuStatus(bkustatus);
        bukukas2.setKodeTutup(bkututup);

        if (kodetrans.equals("C1")) {
            bukukas1.setKodeUangPersediaan("1");
            bukukas1.setNilaiMasuk(SipkdHelpers.getBigDecimalFromString(nilai));
            bukukas1.setNilaiKeluar(SipkdHelpers.getBigDecimalFromString("0"));
            bukukas1.setIdBas(SipkdHelpers.getIntFromString(idbas1.toString()));
            bukukas1.setKodeakun(kodeakun1);
            bukukas1.setJenisPembayaran("PN");

            bukukas2.setKodeUangPersediaan("2");
            bukukas2.setNilaiMasuk(SipkdHelpers.getBigDecimalFromString("0"));
            bukukas2.setNilaiKeluar(SipkdHelpers.getBigDecimalFromString(nilai));
            bukukas2.setIdBas(SipkdHelpers.getIntFromString(idbas2.toString()));
            bukukas2.setKodeakun(kodeakun2);
            bukukas2.setJenisPembayaran("PG");
        } else {
            bukukas2.setKodeUangPersediaan("1");
            bukukas2.setNilaiMasuk(SipkdHelpers.getBigDecimalFromString(nilai));
            bukukas2.setNilaiKeluar(SipkdHelpers.getBigDecimalFromString("0"));
            bukukas2.setIdBas(SipkdHelpers.getIntFromString(idbas2.toString()));
            bukukas2.setKodeakun(kodeakun2);
            bukukas2.setJenisPembayaran("PN");

            bukukas1.setKodeUangPersediaan("2");
            bukukas1.setNilaiMasuk(SipkdHelpers.getBigDecimalFromString("0"));
            bukukas1.setNilaiKeluar(SipkdHelpers.getBigDecimalFromString(nilai));
            bukukas1.setIdBas(SipkdHelpers.getIntFromString(idbas1.toString()));
            bukukas1.setKodeakun(kodeakun1);
            bukukas1.setJenisPembayaran("PG");
        }
        bkus.add(bukukas1);
        bkus.add(bukukas2);
        bkuServices.insertBkuC12(bkus);

        return "Data Buku Kas Umum Berhasil Disimpan";
    }

    @RequestMapping(value = "/json/prosessimpanjo", method = RequestMethod.POST)
    public @ResponseBody
    String prosessimpanbku(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");

        BukuKasUmum bukukas = new BukuKasUmum();

        final String idsekolah = (String) mapdata.get("idsekolah");
        final String kodetrans = (String) mapdata.get("kodetransaksi");
        final String nobukti = (String) mapdata.get("nobukti");
        final String tgldok = (mapdata.get("tgldok")).toString();
        final String jenis = (String) mapdata.get("jenis");
        final String beban = (String) mapdata.get("beban");
        final String nilaikeluar = (String) mapdata.get("nilaikeluar");
        final String nilaimasuk = (String) mapdata.get("nilaimasuk");
        final String filling = (String) mapdata.get("fileinbox");
        final String nrk = (String) mapdata.get("nrk");
        final String namapptk = (String) mapdata.get("namapptk");
        final String nippptk = (String) mapdata.get("nippptk");
        final String uraian = (String) mapdata.get("uraian");
        final String carabayar = (String) mapdata.get("carabayar");
        final String idbas = (String) mapdata.get("idbas");
        final String kodeakun = (String) mapdata.get("kodeakun");
        final String triwulan = (String) mapdata.get("triwulan");
        final String bkututup = (String) mapdata.get("bkututup");
        final String bkustatus = (String) mapdata.get("bkustatus");
        final String jenisbayar = (String) mapdata.get("jenisbayar");
        final String nobkuref = (String) mapdata.get("nobkuref");

        bukukas.setTahun(tahun);
        bukukas.setNoBkuRef(nobkuref);
        bukukas.setIdsekolah(SipkdHelpers.getIntFromString(idsekolah.toString()));
        bukukas.setIdEntry(pengguna.getIdPengguna());
        bukukas.setKodeTransaksi(kodetrans);
        bukukas.setNoDok(nobukti);
        bukukas.setTglDok(SipkdHelpers.getDateFromString(tgldok));
        bukukas.setJenis(jenis);
        bukukas.setBeban(beban);
        bukukas.setNilaiMasuk(SipkdHelpers.getBigDecimalFromString(nilaimasuk));
        bukukas.setNilaiKeluar(SipkdHelpers.getBigDecimalFromString(nilaikeluar));
        bukukas.setInboxFile(filling);
        bukukas.setNrkPptk(nrk);
        bukukas.setNamaPptk(namapptk);
        bukukas.setNipPptk(nippptk);
        bukukas.setUraian(uraian);
        bukukas.setUraianBukti(uraian);
        //bukukas.setKodeWilayah(pengguna.getKodeProses());
        //bukukas.setKodePembayaran(carabayar);
        bukukas.setKodeUangPersediaan(carabayar);
        bukukas.setKodeKoreksi("0");
        bukukas.setIdBas(SipkdHelpers.getIntFromString(idbas.toString()));
        bukukas.setKodeakun(kodeakun);
        bukukas.setTriwulan(triwulan);
        bukukas.setBkuStatus(bkustatus);
        bukukas.setJenisPembayaran(jenisbayar);
        bukukas.setKodeTutup(bkututup);

        bkuServices.insertBkuJo(bukukas);

        return "Data Buku Kas Umum Berhasil Disimpan";
    }

    @RequestMapping(value = "/editbkuc12/", method = RequestMethod.GET)
    public ModelAndView editbkuc12(final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = request.getParameter("tahun");
        final String idbku = request.getParameter("idbku");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("idbku", idbku);
        param.put("tahun", tahun);

        final BukuKasUmum bku = bkuServices.getBkuEditC12(param).get(0);

        return new ModelAndView("bkubop/editbkubopc12", "refbku", bku);
    }

    @RequestMapping(value = "/json/getNilaiC12", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getNilaiC12(final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = request.getParameter("tahun");
        final String idbku = request.getParameter("idbku");
        final String trx = request.getParameter("trx");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("idbku", idbku);
        param.put("tahun", tahun);

        final BukuKasUmum bku = bkuServices.getBkuEditC12(param).get(0);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        if (trx.equals("C1")) {
            mapData.put("aData", bku.getNilaiMasuk());
        } else {
            mapData.put("aData", bku.getNilaiKeluar());
        }
        log.debug("NILAI 1 " + bku.getNilaiMasuk());
        log.debug("NILAI 2 " + bku.getNilaiKeluar());
        return mapData;
    }

    @RequestMapping(value = "/json/prosesupdatec12", method = RequestMethod.POST)
    public @ResponseBody
    String prosesupdatec12(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");

        BukuKasUmum bukukas = new BukuKasUmum();

        final String idbku = (String) mapdata.get("idbku");
        final String idsekolah = (String) mapdata.get("idsekolah");
        final String kodetrans = (String) mapdata.get("kodetransaksi");
        final String nobukti = (String) mapdata.get("nobukti");
        final String tgldok = (mapdata.get("tgldok")).toString();
        final String nilai = (String) mapdata.get("nilai");
        final String filling = (String) mapdata.get("fileinbox");
        final String nrk = (String) mapdata.get("nrk");
        final String namapptk = (String) mapdata.get("namapptk");
        final String nippptk = (String) mapdata.get("nippptk");
        final String uraian = (String) mapdata.get("uraian");
        final String nobkumohon = (String) mapdata.get("nobkumohon");
        final String triwulan = (String) mapdata.get("triwulan");

        bukukas.setTahun(tahun);
        bukukas.setTriwulan(triwulan);
        bukukas.setIdsekolah(SipkdHelpers.getIntFromString(idsekolah.toString()));
        bukukas.setIdEntry(pengguna.getIdPengguna());
        log.debug("Ini id entry " + bukukas.getIdEntry());
        bukukas.setKodeTransaksi(kodetrans);
        bukukas.setNoDok(nobukti);
        bukukas.setTglDok(SipkdHelpers.getDateFromString(tgldok));
        bukukas.setNilaiMasuk(SipkdHelpers.getBigDecimalFromString(nilai));
        bukukas.setNilaiKeluar(SipkdHelpers.getBigDecimalFromString(nilai));
        bukukas.setInboxFile(filling);
        bukukas.setNamaPptk(namapptk);
        bukukas.setNrkPptk(nrk);
        bukukas.setNipPptk(nippptk);
        bukukas.setUraian(uraian);
        bukukas.setUraianBukti(uraian);
        bukukas.setKodeWilayah(pengguna.getKodeProses());
        bukukas.setIdBku(SipkdHelpers.getIntFromString(idbku));
        bukukas.setKodeKoreksi("0");

        bkuServices.updateBkuC12(bukukas);

        return "Data Buku Kas Umum Berhasil Diubah";
    }

    @RequestMapping(value = "/hapusbkuc12/", method = RequestMethod.GET)
    public ModelAndView hapusbkuc12(final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = request.getParameter("tahun");
        final String idbku = request.getParameter("idbku");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("idbku", idbku);
        param.put("tahun", tahun);

        final BukuKasUmum bku = bkuServices.getBkuEditC12(param).get(0);

        return new ModelAndView("bkubop/deletebkubopc12", "refbku", bku);
    }

    @RequestMapping(value = "/editbkujo/", method = RequestMethod.GET)
    public ModelAndView editbkujo(final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = request.getParameter("tahun");
        final String idbku = request.getParameter("idbku");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("idbku", idbku);
        param.put("tahun", tahun);

        final BukuKasUmum bku = bkuServices.getBkuEditJo(param);

        return new ModelAndView("bkubop/editbkubopjo", "refbku", bku);
    }

    @RequestMapping(value = "/editbkurt/", method = RequestMethod.GET)
    public ModelAndView editbkurt(final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = request.getParameter("tahun");
        final String idbku = request.getParameter("idbku");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("idbku", idbku);
        param.put("tahun", tahun);

        final BukuKasUmum bku = bkuServices.getBkuEditJo(param);

        return new ModelAndView("bkubop/editbkuboprt", "refbku", bku);
    }

    @RequestMapping(value = "/editbkujg/", method = RequestMethod.GET)
    public ModelAndView editbkujg(final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = request.getParameter("tahun");
        final String idbku = request.getParameter("idbku");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("idbku", idbku);
        param.put("tahun", tahun);

        final BukuKasUmum bku = bkuServices.getBkuEditJo(param);

        return new ModelAndView("bkubop/editbkubopjg", "refbku", bku);
    }

    @RequestMapping(value = "/editbkuspj/", method = RequestMethod.GET)
    public ModelAndView editbkuspj(final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = request.getParameter("tahun");
        final String idbku = request.getParameter("idbku");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("idbku", idbku);
        param.put("tahun", tahun);

        final BukuKasUmum bku = bkuServices.getBkuEditSpj(param);

        return new ModelAndView("bkubop/editbkubopspj", "refbku", bku);
    }

    @RequestMapping(value = "/editbkuspjbukti/", method = RequestMethod.GET)
    public ModelAndView editbkuspjbukti(final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = request.getParameter("tahun");
        final String idbku = request.getParameter("idbku");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("idbku", idbku);
        param.put("tahun", tahun);

        final BukuKasUmum bku = bkuServices.getBkuEditSpj(param);

        return new ModelAndView("bkubop/editbkubopspjbukti", "refbku", bku);
    }

    @RequestMapping(value = "/editbkupajak/", method = RequestMethod.GET)
    public ModelAndView editbkupajak(final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = request.getParameter("tahun");
        final String idbku = request.getParameter("idbku");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("idbku", idbku);
        param.put("tahun", tahun);

        final BukuKasUmum bku = bkuServices.getBkuEditPajak(param);

        return new ModelAndView("bkubop/editbkuboppajak", "refbku", bku);
    }

    @RequestMapping(value = "/editbkusetor/", method = RequestMethod.GET)
    public ModelAndView editbkusetor(final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = request.getParameter("tahun");
        final String idbku = request.getParameter("idbku");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("idbku", idbku);
        param.put("tahun", tahun);

        final BukuKasUmum bku = bkuServices.getBkuEditJo(param);

        return new ModelAndView("bkubop/editbkubopst", "refbku", bku);
    }

    @RequestMapping(value = "/json/getSaldoKasJO", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getSaldoKasJO(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idbku = request.getParameter("idbku");
        final String idsekolah = request.getParameter("idsekolah");
        final String triwulan = request.getParameter("triwulan");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("idsekolah", idsekolah);
        param.put("triwulan", triwulan);
        param.put("idbku", idbku);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        mapData.put("aData", bkuServices.getSaldoKasJO(param));
        return mapData;
    }

    @RequestMapping(value = "/json/prosesupdatebku", method = RequestMethod.POST)
    public @ResponseBody
    String prosesupdatebku(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        String jenisbayar;

        BukuKasUmum bukukas = new BukuKasUmum();

        final String idbku = (String) mapdata.get("idbku");
        final String idrinci = (String) mapdata.get("idrinci");
        final String idsekolah = (String) mapdata.get("idsekolah");
        final String tglpost = (String) mapdata.get("tglposting");
        final String kodetrans = (String) mapdata.get("kodetransaksi");
        final String nobukti = (String) mapdata.get("nobukti");
        final String tgldok = (mapdata.get("tgldok")).toString();
        final String nilaikeluar = (String) mapdata.get("nilaikeluar");
        final String nilaimasuk = (String) mapdata.get("nilaimasuk");
        final String filling = (String) mapdata.get("fileinbox");
        final String nrk = (String) mapdata.get("nrk");
        final String namapptk = (String) mapdata.get("namapptk");
        final String nippptk = (String) mapdata.get("nippptk");
        final String uraian = (String) mapdata.get("uraian");
        final String carabayar = (String) mapdata.get("carabayar");
        final String nobkuref = (String) mapdata.get("nobkuref");
        final String nobkumohon = (String) mapdata.get("nobkumohon");
        final String triwulan = (String) mapdata.get("triwulan");
        String jenispembayaran = (String) mapdata.get("jenispembayaran");

        if ("JG".equals(kodetrans) || "RT".equals(kodetrans)) {
            jenisbayar = jenispembayaran;
        } else {
            jenisbayar = "";
        }
        bukukas.setTahun(tahun);
        bukukas.setJenisPembayaran(jenisbayar);
        bukukas.setTriwulan(triwulan);
        bukukas.setIdsekolah(SipkdHelpers.getIntFromString(idsekolah.toString()));
        bukukas.setIdEntry(pengguna.getIdPengguna());
        bukukas.setTglPosting(tglpost);
        bukukas.setKodeTransaksi(kodetrans);
        bukukas.setNoDok(nobukti);
        bukukas.setTglDok(SipkdHelpers.getDateFromString(tgldok));
        bukukas.setNilaiMasuk(SipkdHelpers.getBigDecimalFromString(nilaimasuk));
        bukukas.setNilaiKeluar(SipkdHelpers.getBigDecimalFromString(nilaikeluar));
        bukukas.setInboxFile(filling);
        bukukas.setNamaPptk(namapptk);
        bukukas.setNrkPptk(nrk);
        bukukas.setNipPptk(nippptk);
        bukukas.setUraian(uraian);
        bukukas.setUraianBukti(uraian);
        bukukas.setKodeWilayah(pengguna.getKodeProses());
        bukukas.setKodePembayaran(carabayar);
        bukukas.setKodeUangPersediaan(carabayar);
        bukukas.setNoBkuRef(nobkuref);
        bukukas.setIdBku(SipkdHelpers.getIntFromString(idbku));
        bukukas.setIdRinci(SipkdHelpers.getIntFromString(idrinci));
        bukukas.setKodeKoreksi("0");

        bkuServices.updateBkuJo(bukukas);

        return "Data Buku Kas Umum Berhasil Diubah";
    }

    @RequestMapping(value = "/hapusbkujo/", method = RequestMethod.GET)
    public ModelAndView hapusbkujo(final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = request.getParameter("tahun");
        final String idbku = request.getParameter("idbku");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("idbku", idbku);
        param.put("tahun", tahun);

        final BukuKasUmum bku = bkuServices.getBkuEditJo(param);

        return new ModelAndView("bkubop/deletebkubopjo", "refbku", bku);
    }

    @RequestMapping(value = "/hapusbkurt/", method = RequestMethod.GET)
    public ModelAndView hapusbkurt(final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = request.getParameter("tahun");
        final String idbku = request.getParameter("idbku");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("idbku", idbku);
        param.put("tahun", tahun);

        final BukuKasUmum bku = bkuServices.getBkuEditJo(param);

        return new ModelAndView("bkubop/deletebkuboprt", "refbku", bku);
    }

    @RequestMapping(value = "/hapusbkujg/", method = RequestMethod.GET)
    public ModelAndView hapusbkujg(final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = request.getParameter("tahun");
        final String idbku = request.getParameter("idbku");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("idbku", idbku);
        param.put("tahun", tahun);

        final BukuKasUmum bku = bkuServices.getBkuEditJo(param);

        return new ModelAndView("bkubop/deletebkubopjg", "refbku", bku);
    }

    @RequestMapping(value = "/hapusbkusetor/", method = RequestMethod.GET)
    public ModelAndView hapusbkusetor(final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = request.getParameter("tahun");
        final String idbku = request.getParameter("idbku");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("idbku", idbku);
        param.put("tahun", tahun);

        final BukuKasUmum bku = bkuServices.getBkuEditJo(param);

        return new ModelAndView("bkubop/deletebkubopst", "refbku", bku);
    }

    @RequestMapping(value = "/hapusbkuspj/", method = RequestMethod.GET)
    public ModelAndView hapusbkuspj(final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = request.getParameter("tahun");
        final String idbku = request.getParameter("idbku");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("idbku", idbku);
        param.put("tahun", tahun);

        final BukuKasUmum bku = bkuServices.getBkuEditSpj(param);

        return new ModelAndView("bkubop/deletebkubopspj", "refbku", bku);
    }

    @RequestMapping(value = "/hapusbkupajak/", method = RequestMethod.GET)
    public ModelAndView hapusbkupajak(final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = request.getParameter("tahun");
        final String idbku = request.getParameter("idbku");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("idbku", idbku);
        param.put("tahun", tahun);

        final BukuKasUmum bku = bkuServices.getBkuEditPajak(param);

        return new ModelAndView("bkubop/deletebkuboppajak", "refbku", bku);
    }

    @RequestMapping(value = "/json/prosesdeletebyid", method = RequestMethod.POST)
    public @ResponseBody
    String prosesdeletebyid(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");

        final String idbku = (String) mapdata.get("idbku");
        final String idparam = (String) mapdata.get("idparam");
        final String kodetrans = (String) mapdata.get("kodetransaksi");
        final String idsekolah = (String) mapdata.get("idsekolah");
        final String nobkuref = (String) mapdata.get("nobkuref");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahun);
        param.put("kodeTransaksi", kodetrans);
        param.put("idbku", idbku);
        param.put("idparam", idparam);
        param.put("idsekolah", idsekolah);
        param.put("nobkuref", nobkuref);

        bkuServices.deleteBkuById(param);

        return "Data Buku Kas Umum Berhasil Dihapus";
    }

    @RequestMapping(value = "/json/getDataParam", method = RequestMethod.GET)
    public @ResponseBody
    BukuKasUmum getDataParam(final HttpServletRequest request) {
        final String idbku = request.getParameter("idbku");
        //final String kodetransaksi = request.getParameter("kodetransaksi");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("idbku", idbku);

        return bkuServices.getDataParam(param);
    }

    @RequestMapping(value = "/json/validateP2P3", method = RequestMethod.GET)
    public @ResponseBody
    Map validateP2P3(final HttpServletRequest request) {
        final String idsekolah = request.getParameter("idsekolah");
        final String idbku = request.getParameter("idbku");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("idsekolah", idsekolah);
        param.put("idbku", idbku);
        param.put("tahun", tahun);

        return bkuServices.validateP2P3(param);
    }

    @RequestMapping(value = "/json/getKodeSetor", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getKodeSetor(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String triwulan = request.getParameter("triwulan");
        final String idsekolah = request.getParameter("idsekolah");
        //final String kodetransaksi = request.getParameter("kodetransaksi");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("idsekolah", idsekolah);
        param.put("triwulan", triwulan);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        mapData.put("aData", bkuServices.getKodeSetor(param));
        return mapData;
    }

    @RequestMapping(value = "/listsetoran", method = RequestMethod.GET)
    public ModelAndView listsetoran(final BukuKasUmum bku, final HttpServletRequest request, final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return new ModelAndView("bkubop/listsetoran", "refkegiatan", bku);
    }

    @RequestMapping(value = "/json/listsetor", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listsetor(final HttpServletRequest request) {

        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");

        final String idsekolah = request.getParameter("idsekolah");
        final String tahun = request.getParameter("tahun");
        final String kodetransaksi = request.getParameter("kodetransaksi");
        final String nobkumohon = request.getParameter("nobkumohon");
        final String triwulan = request.getParameter("triwulan");

        param.put("idsekolah", idsekolah);
        param.put("tahun", tahun);
        param.put("kodetransaksi", kodetransaksi);
        param.put("nobkumohon", nobkumohon);
        param.put("triwulan", triwulan);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        final long banyak = bkuServices.getBanyakListSetoran(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", bkuServices.getListSetoran(param));

        return mapData;
    }

    @RequestMapping(value = "/json/getSisaKas", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getSisaKas(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String nobku = request.getParameter("nobku");
        final String idsekolah = request.getParameter("idsekolah");
        final String idkegiatan = request.getParameter("idkegiatan");
        final String triwulan = request.getParameter("triwulan");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("idsekolah", idsekolah);
        param.put("nobku", nobku);
        param.put("idkegiatan", idkegiatan);
        param.put("triwulan", triwulan);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        mapData.put("aData", bkuServices.getSisaKas(param));
        return mapData;
    }

    @RequestMapping(value = "/json/listspj", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listspj(final HttpServletRequest request) {

        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");

        final String idsekolah = request.getParameter("idsekolah");
        final String tahun = request.getParameter("tahun");
        final String idkegiatan = request.getParameter("idkegiatan");
        final String nobkumohon = request.getParameter("nobkumohon");
        final String triwulan = request.getParameter("triwulan");

        param.put("idsekolah", idsekolah);
        param.put("tahun", tahun);
        param.put("idkegiatan", idkegiatan);
        param.put("nobkumohon", nobkumohon);
        param.put("triwulan", triwulan);
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

    @RequestMapping(value = "/json/prosessimpanspj", method = RequestMethod.POST)
    public @ResponseBody
    String prosessimpanspj(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        List<Map<String, Object>> nilailist = (List) mapdata.get("nilailist");
        List<BkuRinci> listRinci = new ArrayList<>();
        BukuKasUmum bukukas = new BukuKasUmum();

        final String idsekolah = (String) mapdata.get("idsekolah");
        final String kodetransaksi = (String) mapdata.get("kodetransaksi");
        final String nobukti = (String) mapdata.get("nobukti");
        final String tgldok = (mapdata.get("tgldok")).toString();
        final String jenis = (String) mapdata.get("jenis");
        final String beban = (String) mapdata.get("beban");
        final String idkegiatan = (String) mapdata.get("idkegiatan");
        final String kodekegiatan = (String) mapdata.get("kodekeg");
        final String filling = (String) mapdata.get("fileinbox");
        final String uraian = (String) mapdata.get("uraian");
        final String carabayar = (String) mapdata.get("carabayar");
        final String nipPPTK = (String) mapdata.get("nippptk");
        final String namaPPTK = (String) mapdata.get("namapptk");
        final String nrk = (String) mapdata.get("nrk");
        final String norek = (String) mapdata.get("norek");
        final String kodeva = (String) mapdata.get("kodeva");
        final String namarekan = (String) mapdata.get("namarekan");
        final String kodebank = (String) mapdata.get("kodebank");
        final String kodebanktf = (String) mapdata.get("kodebanktf");
        final String namabanktf = (String) mapdata.get("namabanktf");
        final String triwulan = (String) mapdata.get("triwulan");
        final String kodetalangan = (String) mapdata.get("kodetalangan");
        final String statusspj = (String) mapdata.get("statusspj");
        final String bulan = (String) mapdata.get("bulan");
        final String imcb = (String) mapdata.get("imcb");
        final String kodesk = (String) mapdata.get("kodesk");
        final String pkp = (String) mapdata.get("pkp");
        final String pns = (String) mapdata.get("pns");
        final String ptkp = (String) mapdata.get("ptkp");
        final String pegawai = (String) mapdata.get("pegawai");
        final String belanja = (String) mapdata.get("belanja");
        final String npwp = (String) mapdata.get("npwp");
        final String periode = (String) mapdata.get("periode");
        final String namanpwp = (String) mapdata.get("namanpwp");
        final String alamatnpwp = (String) mapdata.get("alamatnpwp");
        final String kotanpwp = (String) mapdata.get("kotanpwp");

        bukukas.setTahun(tahun);
        bukukas.setStatusSpj(statusspj);
        bukukas.setIdsekolah(SipkdHelpers.getIntFromString(idsekolah.toString()));
        bukukas.setIdEntry(pengguna.getIdPengguna());
        bukukas.setKodeTransaksi(kodetransaksi);
        bukukas.setNoDok(nobukti);
        bukukas.setTglDok(SipkdHelpers.getDateFromString(tgldok));
        bukukas.setJenis(jenis);
        bukukas.setBeban(beban);
        bukukas.setIdKegiatan(SipkdHelpers.getIntFromString(idkegiatan));
        bukukas.setKodeKeg(kodekegiatan);
        bukukas.setInboxFile(filling);
        bukukas.setUraian(uraian);
        bukukas.setUraianBukti(uraian);
        bukukas.setKodeWilayah(pengguna.getKodeProses());
        bukukas.setKodePembayaran(carabayar);
        bukukas.setKodeUangPersediaan(carabayar);
        bukukas.setNipPptk(nipPPTK);
        bukukas.setNamaPptk(namaPPTK);
        bukukas.setNrkPptk(nrk);
        bukukas.setNorekBank(norek);
        bukukas.setNamaRekan(namarekan);
        bukukas.setKodeBank(kodebank);
        bukukas.setKodeBankTf(kodebanktf);
        bukukas.setNamaBank(namabanktf);
        bukukas.setKodeVA(kodeva);
        bukukas.setKodeTalangan(kodetalangan);
        bukukas.setTriwulan(triwulan);
        bukukas.setKodeKoreksi("0");
        bukukas.setKodeSK(kodesk);
        bukukas.setKodePKP(pkp);
        bukukas.setKodePNS(pns);
        bukukas.setKodePegawai(pegawai);
        bukukas.setKodeBelanja(belanja);
        bukukas.setNpwp(npwp);
        bukukas.setNamaNpwp(namanpwp);
        bukukas.setAlamatNpwp(alamatnpwp);
        bukukas.setKotaNpwp(kotanpwp);
        bukukas.setKodePTKP(ptkp);
        bukukas.setKodePeriode(periode);

        bukukas.setBulanTagihan(bulan);
        bukukas.setIdMcb(imcb);

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
            bkurinci.setKodeTransaksi("JJ");
            bkurinci.setStatusSpj(statusspj);

            listRinci.add(bkurinci);

        }

        bukukas.setListBkuRinci(listRinci);
        bkuServices.insertBkuSpj(bukukas);

        return "Data Buku Kas Umum Berhasil Disimpan";
    }

    @RequestMapping(value = "/json/getDataKegiatan", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getDataKegiatan(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idkegiatan = request.getParameter("idkegiatan");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("idkegiatan", idkegiatan);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        mapData.put("aData", bkuServices.getDataKegiatan(param));
        return mapData;
    }

    @RequestMapping(value = "/json/getMaxStatus", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getMaxStatus(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idSekolah = request.getParameter("idsekolah");
        final String triwulan = request.getParameter("triwulan");
        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("idsekolah", idSekolah);
        param.put("triwulan", triwulan);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        mapData.put("aData", bkuServices.getMaxStatus(param));
        return mapData;
    }

    @RequestMapping(value = "/json/prosesupdatebkuspj", method = RequestMethod.POST)
    public @ResponseBody
    String prosesupdatebkuspj(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        List<Map<String, Object>> nilailist = (List) mapdata.get("nilailist");
        List<BkuRinci> listRinci = new ArrayList<>();
        BukuKasUmum bukukas = new BukuKasUmum();

        final String idsekolah = (String) mapdata.get("idsekolah");
        final String kodetransaksi = (String) mapdata.get("kodetransaksi");
        final String nobukti = (String) mapdata.get("nobukti");
        final String tgldok = (mapdata.get("tgldok")).toString();
        final String jenis = (String) mapdata.get("jenis");
        final String beban = (String) mapdata.get("beban");
        final String idkegiatan = (String) mapdata.get("idkegiatan");
        final String kodekegiatan = (String) mapdata.get("kodekeg");
        final String filling = (String) mapdata.get("fileinbox");
        final String uraian = (String) mapdata.get("uraian");
        final String carabayar = (String) mapdata.get("carabayar");
        final String nipPPTK = (String) mapdata.get("nippptk");
        final String namaPPTK = (String) mapdata.get("namapptk");
        final String nrk = (String) mapdata.get("nrk");
        final String norek = (String) mapdata.get("norek");
        final String kodeva = (String) mapdata.get("kodeva");
        final String kodetalangan = (String) mapdata.get("kodetalangan");
        final String bulan = (String) mapdata.get("bulan");
        final String imcb = (String) mapdata.get("imcb");
        final String namarekan = (String) mapdata.get("namarekan");
        final String kodebank = (String) mapdata.get("kodebank");
        final String kodebanktf = (String) mapdata.get("kodebanktf");
        final String namabanktf = (String) mapdata.get("namabanktf");
        final String idbku = (String) mapdata.get("idbku");
        final String idparam = (String) mapdata.get("idparam");
        final String nobkumohon = (String) mapdata.get("nobkumohon");
        //final String statusspj = (String) mapdata.get("statusspj");
        final String kodesk = (String) mapdata.get("kodesk");
        final String pkp = (String) mapdata.get("pkp");
        final String pns = (String) mapdata.get("pns");
        final String ptkp = (String) mapdata.get("ptkp");
        final String pegawai = (String) mapdata.get("pegawai");
        final String belanja = (String) mapdata.get("belanja");
        final String npwp = (String) mapdata.get("npwp");
        final String periode = (String) mapdata.get("periode");
        final String namanpwp = (String) mapdata.get("namanpwp");
        final String alamatnpwp = (String) mapdata.get("alamatnpwp");
        final String kotanpwp = (String) mapdata.get("kotanpwp");

        bukukas.setTahun(tahun);
        bukukas.setIdBku(SipkdHelpers.getIntFromString(idbku.toString()));
        bukukas.setIdParam(SipkdHelpers.getIntFromString(idparam.toString()));
        bukukas.setNoBkuMohon(SipkdHelpers.getIntFromString(nobkumohon.toString()));
        bukukas.setIdsekolah(SipkdHelpers.getIntFromString(idsekolah.toString()));
        bukukas.setIdEntry(pengguna.getIdPengguna());
        bukukas.setKodeTransaksi(kodetransaksi);
        bukukas.setNoDok(nobukti);
        bukukas.setTglDok(SipkdHelpers.getDateFromString(tgldok));
        bukukas.setJenis(jenis);
        bukukas.setBeban(beban);
        bukukas.setIdKegiatan(SipkdHelpers.getIntFromString(idkegiatan));
        bukukas.setKodeKeg(kodekegiatan);
        bukukas.setInboxFile(filling);
        bukukas.setUraian(uraian);
        bukukas.setUraianBukti(uraian);
        bukukas.setKodeWilayah(pengguna.getKodeProses());
        bukukas.setKodePembayaran(carabayar);
        bukukas.setKodeUangPersediaan(carabayar);
        bukukas.setNipPptk(nipPPTK);
        bukukas.setNamaPptk(namaPPTK);
        bukukas.setNrkPptk(nrk);
        bukukas.setNorekBank(norek);
        bukukas.setNamaRekan(namarekan);
        bukukas.setKodeBank(kodebank);
        bukukas.setKodeBankTf(kodebanktf);
        bukukas.setNamaBank(namabanktf);
        bukukas.setKodeVA(kodeva);
        bukukas.setKodeTalangan(kodetalangan);
        bukukas.setBulanTagihan(bulan);
        bukukas.setIdMcb(imcb);
        bukukas.setKodeKoreksi("0");
        bukukas.setKodeSK(kodesk);
        bukukas.setKodePKP(pkp);
        bukukas.setKodePNS(pns);
        bukukas.setKodePegawai(pegawai);
        bukukas.setKodeBelanja(belanja);
        bukukas.setNpwp(npwp);
        bukukas.setNamaNpwp(namanpwp);
        bukukas.setAlamatNpwp(alamatnpwp);
        bukukas.setKotaNpwp(kotanpwp);
        bukukas.setKodePTKP(ptkp);
        bukukas.setKodePeriode(periode);

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
            //bkurinci.setStatusSpj(statusspj);
            bkurinci.setNamaSubKegiatan(namasubkeg.toString());
            bkurinci.setKomponenPajak(SipkdHelpers.getIntFromString(komponenpajak.toString()));
            bkurinci.setVolume(SipkdHelpers.getIntFromString(volume.toString()));
            bkurinci.setKeteranganRinci(ketrinci.toString());
            bkurinci.setHargaSatuan(SipkdHelpers.getBigDecimalFromString(hargasatuan.toString()));
            bkurinci.setKodeTransaksi(kodetransaksi);

            listRinci.add(bkurinci);

        }

        bukukas.setListBkuRinci(listRinci);
        bkuServices.updateBkuSpj(bukukas);

        return "Data Buku Kas Umum Berhasil Disimpan";
    }

    @RequestMapping(value = "/json/prosesupdatebkuspjbukti", method = RequestMethod.POST)
    public @ResponseBody
    String prosesupdatebkuspjbukti(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        BukuKasUmum bukukas = new BukuKasUmum();

        final String idsekolah = (String) mapdata.get("idsekolah");
        final String kodetransaksi = (String) mapdata.get("kodetransaksi");
        final String nobukti = (String) mapdata.get("nobukti");
        final String idbku = (String) mapdata.get("idbku");

        bukukas.setTahun(tahun);
        bukukas.setIdBku(SipkdHelpers.getIntFromString(idbku.toString()));
        bukukas.setIdsekolah(SipkdHelpers.getIntFromString(idsekolah.toString()));
        bukukas.setIdEntry(pengguna.getIdPengguna());
        bukukas.setKodeTransaksi(kodetransaksi);
        bukukas.setNoDok(nobukti);
        log.debug("WHYYYY " + nobukti);
        bkuServices.updateBkuSpjBukti(bukukas);

        return "Data Buku Kas Umum Berhasil Disimpan";
    }

    @RequestMapping(value = "/listpjpn", method = RequestMethod.GET)
    public ModelAndView listpjpn(final BukuKasUmum bku, final HttpServletRequest request, final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return new ModelAndView("bkubop/listspjrinci", "refkegiatan", bku);
    }

    @RequestMapping(value = "/listpjpg", method = RequestMethod.GET)
    public ModelAndView listpjpg(final BukuKasUmum bku, final HttpServletRequest request, final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return new ModelAndView("bkubop/listspnpajak", "refkegiatan", bku);
    }

    @RequestMapping(value = "/json/listspjrinci", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listspjrinci(final HttpServletRequest request) {

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
        final String jenisbayarpajak = request.getParameter("jenisbayarpajak");
        final String kodetransaksi = request.getParameter("kodetransaksi");

        param.put("idsekolah", idsekolah);
        param.put("tahun", tahun);
        param.put("triwulan", triwulan);
        param.put("nobku", nobku);
        param.put("nobukti", nobukti);
        param.put("jenisbayarpajak", jenisbayarpajak);
        param.put("kodetransaksi", kodetransaksi);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        final long banyak = bkuServices.getBanyakListSpjRinci(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", bkuServices.getListSpjRinci(param));

        return mapData;
    }

    @RequestMapping(value = "/json/listpajakpn", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listpajakpn(final HttpServletRequest request) {

        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");

        final String idsekolah = request.getParameter("idsekolah");
        final String tahun = request.getParameter("tahun");
        final String nobkumohon = request.getParameter("nobkumohon");
        final String jenispajak = request.getParameter("jenispajak");
        final String triwulan = request.getParameter("triwulan");

        param.put("idsekolah", idsekolah);
        param.put("tahun", tahun);
        param.put("nobkumohon", nobkumohon);
        param.put("jenispajak", jenispajak);
        param.put("triwulan", triwulan);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        final long banyak = bkuServices.getBanyakListPajakPn(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", bkuServices.getListPajakPn(param));

        return mapData;
    }

    @RequestMapping(value = "/json/listpajakspj", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listpajakspj(final HttpServletRequest request) {

        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");

        final String idsekolah = request.getParameter("idsekolah");
        final String tahun = request.getParameter("tahun");
        final String nobkumohon = request.getParameter("nobkumohon");
        final String jenispajak = request.getParameter("jenispajak");
        final String nobkuref = request.getParameter("nobkuref");
        final String idbku = request.getParameter("idbku");
        final String triwulan = request.getParameter("triwulan");

        param.put("idsekolah", idsekolah);
        param.put("tahun", tahun);
        param.put("nobkumohon", nobkumohon);
        param.put("jenispajak", jenispajak);
        param.put("nobkuref", nobkuref);
        param.put("idbku", idbku);
        param.put("triwulan", triwulan);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        final long banyak = bkuServices.getBanyakListPajakSpj(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", bkuServices.getListPajakSpj(param));

        return mapData;
    }

    @RequestMapping(value = "/json/listpajakpg", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listpajakpg(final HttpServletRequest request) {

        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");

        final String idsekolah = request.getParameter("idsekolah");
        final String tahun = request.getParameter("tahun");
        final String nobkumohon = request.getParameter("nobkumohon");
        final String jenispajak = request.getParameter("jenispajak");

        param.put("idsekolah", idsekolah);
        param.put("tahun", tahun);
        param.put("nobkumohon", nobkumohon);
        param.put("jenispajak", jenispajak);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        final long banyak = bkuServices.getBanyakListPajakPg(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", bkuServices.getListPajakPg(param));

        return mapData;
    }

    @RequestMapping(value = "/json/getDataWP", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getDataWP(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idsekolah = request.getParameter("idsekolah");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("idsekolah", idsekolah);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        mapData.put("aData", bkuServices.getDataWP(param));
        return mapData;
    }

    @RequestMapping(value = "/json/prosessimpanpajak", method = RequestMethod.POST)
    public @ResponseBody
    String prosessimpanpajak(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        List<Map<String, Object>> nilailist = (List) mapdata.get("nilailist");
        List<BkuRinci> listRinci = new ArrayList<>();
        BukuKasUmum bukukas = new BukuKasUmum();

        final String idsekolah = (String) mapdata.get("idsekolah");
        final String kodetransaksi = (String) mapdata.get("kodetransaksi");
        final String nobukti = (String) mapdata.get("nobukti");
        final String tgldok = (mapdata.get("tgldok")).toString();
        final String jenis = (String) mapdata.get("jenis");
        final String beban = (String) mapdata.get("beban");
        final String idkegiatan = (String) mapdata.get("idkegiatan");
        final String kodekegiatan = (String) mapdata.get("kodekeg");
        final String filling = (String) mapdata.get("fileinbox");
        final String uraian = (String) mapdata.get("uraian");
        final String carabayar = (String) mapdata.get("carabayar");
        final String nipPPTK = (String) mapdata.get("nippptk");
        final String namaPPTK = (String) mapdata.get("namapptk");
        final String nrk = (String) mapdata.get("nrk");
        final String namarekan = (String) mapdata.get("namarekan");
        final String npwp = (String) mapdata.get("npwp");
        final String namanpwp = (String) mapdata.get("namanpwp");
        final String alamatnpwp = (String) mapdata.get("alamatnpwp");
        final String kotanpwp = (String) mapdata.get("kotanpwp");
        final String masapajak = (String) mapdata.get("masapajak");
        final String tahunpajak = (String) mapdata.get("tahunpajak");
        final String triwulan = (String) mapdata.get("triwulan");
        final String nobkuref = (String) mapdata.get("nobkuref");
        final String bkututup = (String) mapdata.get("bkututup");
        log.debug("INI LOG 1 " + bkututup);
        final String bkustatus = (String) mapdata.get("bkustatus");
        final String jenisbayar = (String) mapdata.get("jenisbayar");
        final String kodekjs = (String) mapdata.get("kodekjs");
        final String kodemap = (String) mapdata.get("kodemap");
        final String idbkuref = (String) mapdata.get("idbkuref");
        final String jenisbayarpajak = (String) mapdata.get("jenisbayarpajak");
        final String persen = (String) mapdata.get("persen");

        bukukas.setTahun(tahun);
        bukukas.setIdsekolah(SipkdHelpers.getIntFromString(idsekolah.toString()));
        bukukas.setIdEntry(pengguna.getIdPengguna());
        bukukas.setKodeTransaksi(kodetransaksi);
        bukukas.setNoDok(nobukti);
        bukukas.setTglDok(SipkdHelpers.getDateFromString(tgldok));
        bukukas.setJenis(jenis);
        bukukas.setBeban(beban);
        bukukas.setIdKegiatan(SipkdHelpers.getIntFromString(idkegiatan));
        bukukas.setKodeKeg(kodekegiatan);
        bukukas.setInboxFile(filling);
        bukukas.setUraian(uraian);
        bukukas.setUraianBukti(uraian);
        bukukas.setKodeWilayah(pengguna.getKodeProses());
        bukukas.setKodePembayaran(carabayar);
        bukukas.setKodeUangPersediaan(carabayar);
        bukukas.setNipPptk(nipPPTK);
        bukukas.setNamaPptk(namaPPTK);
        bukukas.setNrkPptk(nrk);
        bukukas.setNamaRekan(namarekan);
        bukukas.setNpwp(npwp);
        bukukas.setNamaNpwp(namanpwp);
        bukukas.setAlamatNpwp(alamatnpwp);
        bukukas.setKotaNpwp(kotanpwp);
        bukukas.setMasaPajak(masapajak);
        bukukas.setTahunPajak(tahunpajak);
        bukukas.setTriwulan(triwulan);
        bukukas.setNoBkuRef(nobkuref);
        bukukas.setKodeKoreksi("0");
        bukukas.setBkuStatus(bkustatus);
        bukukas.setJenisPembayaran(jenisbayar);
        bukukas.setKodeKjs(kodekjs);
        bukukas.setKodeMap(kodemap);
        bukukas.setPersen(new BigDecimal(persen));
        bukukas.setKodeTutup(bkututup);
        log.debug("INI LOG 2 " + bukukas.getKodeTutup());
        bukukas.setKodePajak(jenisbayarpajak);
        bukukas.setIdBkuRef(SipkdHelpers.getIntFromString(idbkuref.toString()));

        for (Map<String, Object> mapnilailist : nilailist) {

            BkuRinci bkurinci = new BkuRinci();

            Object nilaimasuk = mapnilailist.get("nilaimasuk");
            Object nilaikeluar = mapnilailist.get("nilaikeluar");
            Object idbas = mapnilailist.get("idbas");
            Object kodeakun = mapnilailist.get("kodeakun");
            Object idkomponen = mapnilailist.get("idkomponen");
            Object idblrinci = mapnilailist.get("idblrinci");
            Object idbkurinci = mapnilailist.get("idbkurinci");

            bkurinci.setNilaiMasuk(SipkdHelpers.getBigDecimalFromString(nilaimasuk.toString()));
            bkurinci.setNilaiKeluar(SipkdHelpers.getBigDecimalFromString(nilaikeluar.toString()));
            bkurinci.setIdBas(SipkdHelpers.getIntFromString(idbas.toString()));
            bkurinci.setKodeakun(kodeakun.toString());
            bkurinci.setIdEntry(pengguna.getIdPengguna());
            bkurinci.setIdKomponen(SipkdHelpers.getIntFromString(idkomponen.toString()));
            bkurinci.setIdBlRinci(SipkdHelpers.getIntFromString(idblrinci.toString()));
            bkurinci.setIdBkuRinci(SipkdHelpers.getIntFromString(idbkurinci.toString()));
            bkurinci.setIdBkuRef(SipkdHelpers.getIntFromString(idbkuref.toString()));
            listRinci.add(bkurinci);

        }

        bukukas.setListBkuRinci(listRinci);
        bkuServices.insertBkuPajak(bukukas);

        String status = "1";

        if ("PN".equals(jenisbayar) && "0".equals(jenisbayarpajak)) {
            status = bukukas.getBanyakPajak().toString();
        }

        return status;
    }

    @RequestMapping(value = "/json/prosesupdatepajak", method = RequestMethod.POST)
    public @ResponseBody
    String prosesupdatepajak(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        List<Map<String, Object>> nilailist = (List) mapdata.get("nilailist");
        List<BkuRinci> listRinci = new ArrayList<>();
        BukuKasUmum bukukas = new BukuKasUmum();

        final String idbku = (String) mapdata.get("idbku");
        final String nobkumohon = (String) mapdata.get("nobkumohon");
        final String idsekolah = (String) mapdata.get("idsekolah");
        final String kodetransaksi = (String) mapdata.get("kodetransaksi");
        final String nobukti = (String) mapdata.get("nobukti");
        final String tgldok = (mapdata.get("tgldok")).toString();
        final String idkegiatan = (String) mapdata.get("idkegiatan");
        final String kodekegiatan = (String) mapdata.get("kodekeg");
        final String filling = (String) mapdata.get("fileinbox");
        final String uraian = (String) mapdata.get("uraian");
        final String carabayar = (String) mapdata.get("carabayar");
        final String nipPPTK = (String) mapdata.get("nippptk");
        final String namaPPTK = (String) mapdata.get("namapptk");
        final String nrk = (String) mapdata.get("nrk");
        final String namarekan = (String) mapdata.get("namarekan");
        final String npwp = (String) mapdata.get("npwp");
        final String namanpwp = (String) mapdata.get("namanpwp");
        final String alamatnpwp = (String) mapdata.get("alamatnpwp");
        final String kotanpwp = (String) mapdata.get("kotanpwp");
        final String masapajak = (String) mapdata.get("masapajak");
        final String tahunpajak = (String) mapdata.get("tahunpajak");
        final String nobkuref = (String) mapdata.get("nobkuref");
        final String idbkuref = (String) mapdata.get("idbkuref");
        final String jenispembayaran = (String) mapdata.get("jenispembayaran");
        final String jenisbayarpajak = (String) mapdata.get("jenisbayarpajak");
        final String kodekjs = (String) mapdata.get("kodekjs");
        final String kodemap = (String) mapdata.get("kodemap");
        final String persen = (String) mapdata.get("persen");

        bukukas.setTahun(tahun);
        bukukas.setIdBku(SipkdHelpers.getIntFromString(idbku.toString()));
        bukukas.setNoBkuMohon(SipkdHelpers.getIntFromString(nobkumohon.toString()));
        bukukas.setIdsekolah(SipkdHelpers.getIntFromString(idsekolah.toString()));
        bukukas.setIdEntry(pengguna.getIdPengguna());
        bukukas.setKodeTransaksi(kodetransaksi);
        bukukas.setNoDok(nobukti);
        bukukas.setTglDok(SipkdHelpers.getDateFromString(tgldok));
        bukukas.setIdKegiatan(SipkdHelpers.getIntFromString(idkegiatan));
        bukukas.setKodeKeg(kodekegiatan);
        bukukas.setInboxFile(filling);
        bukukas.setUraian(uraian);
        bukukas.setUraianBukti(uraian);
        bukukas.setKodeWilayah(pengguna.getKodeProses());
        bukukas.setKodePembayaran(carabayar);
        bukukas.setKodeUangPersediaan(carabayar);
        bukukas.setNipPptk(nipPPTK);
        bukukas.setNamaPptk(namaPPTK);
        bukukas.setNrkPptk(nrk);
        bukukas.setNamaRekan(namarekan);
        bukukas.setNpwp(npwp);
        bukukas.setNamaNpwp(namanpwp);
        bukukas.setAlamatNpwp(alamatnpwp);
        bukukas.setKotaNpwp(kotanpwp);
        bukukas.setMasaPajak(masapajak);
        bukukas.setTahunPajak(tahunpajak);
        bukukas.setNoBkuRef(nobkuref);
        bukukas.setKodeKoreksi("0");
        bukukas.setKodeKjs(kodekjs);
        bukukas.setKodeMap(kodemap);
        bukukas.setJenisPembayaran(jenispembayaran);
        bukukas.setKodePajak(jenisbayarpajak);
        bukukas.setPersen(new BigDecimal(persen));
        bukukas.setIdBkuRef(SipkdHelpers.getIntFromString(idbkuref.toString()));

        for (Map<String, Object> mapnilailist : nilailist) {

            BkuRinci bkurinci = new BkuRinci();

            Object nilaimasuk = mapnilailist.get("nilaimasuk");
            Object nilaikeluar = mapnilailist.get("nilaikeluar");

            Object idbkurinci = mapnilailist.get("idbkurinci");
            Object idbas = mapnilailist.get("idbas");
            Object idkomponen = mapnilailist.get("idkomponen");
            Object idblrinci = mapnilailist.get("idblrinci");
            Object kodeakun = mapnilailist.get("kodeakun");
            Object nilaipajakspj = mapnilailist.get("nilaipajakspj");

            bkurinci.setNilaiPajakSpj(SipkdHelpers.getBigDecimalFromString(nilaipajakspj.toString()));
            bkurinci.setNilaiMasuk(SipkdHelpers.getBigDecimalFromString(nilaimasuk.toString()));
            bkurinci.setNilaiKeluar(SipkdHelpers.getBigDecimalFromString(nilaikeluar.toString()));
            bkurinci.setIdBku(SipkdHelpers.getIntFromString(idbku.toString()));
            bkurinci.setIdEntry(pengguna.getIdPengguna());

            bkurinci.setIdBas(SipkdHelpers.getIntFromString(idbas.toString()));
            bkurinci.setKodeakun(kodeakun.toString());
            bkurinci.setIdKomponen(SipkdHelpers.getIntFromString(idkomponen.toString()));
            bkurinci.setIdBlRinci(SipkdHelpers.getIntFromString(idblrinci.toString()));
            bkurinci.setIdBkuRinci(SipkdHelpers.getIntFromString(idbkurinci.toString()));
            bkurinci.setIdBkuRef(SipkdHelpers.getIntFromString(idbkuref.toString()));

            listRinci.add(bkurinci);

        }

        bukukas.setListBkuRinci(listRinci);
        bkuServices.updateBkuPajak(bukukas);

        //return "Data Buku Kas Umum Berhasil Diubah";
        String status = "1";

        if ("PN".equals(jenispembayaran) && "0".equals(jenisbayarpajak)) {
            status = bukukas.getBanyakPajak().toString();
        }

        return status;
    }

    @RequestMapping(value = "/json/getBanyakDataPjPn", method = RequestMethod.GET)
    public @ResponseBody
    Integer getBanyakDataPjPn(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idsekolah = request.getParameter("idsekolah");
        final String jenispajak = request.getParameter("jenispajak");
        final String nobkumohon = request.getParameter("nobkumohon");

        final Map< String, Object> param = new HashMap<String, Object>(5);
        param.put("tahun", tahunAnggaran);
        param.put("idsekolah", idsekolah);
        param.put("jenispajak", jenispajak);
        param.put("nobkumohon", nobkumohon);

        return bkuServices.getBanyakDataPjPn(param);
    }

    @RequestMapping(value = "/json/getWpPjPg", method = RequestMethod.GET)
    public @ResponseBody
    String getWpPjPg(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idbku = request.getParameter("idbku");

        final Map< String, Object> param = new HashMap<String, Object>(5);
        param.put("tahun", tahunAnggaran);
        param.put("idbku", idbku);

        return bkuServices.getWpPjPg(param);
    }

    @RequestMapping(value = "/json/getStatusSpj", method = RequestMethod.GET)
    public @ResponseBody
    Integer getStatusSpj(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idsekolah = request.getParameter("idsekolah");
        final String triwulan = request.getParameter("triwulan");

        final Map< String, Object> param = new HashMap<String, Object>(5);
        param.put("tahun", tahunAnggaran);
        param.put("idsekolah", idsekolah);
        param.put("triwulan", triwulan);

        return bkuServices.getStatusSpj(param);
    }

    @RequestMapping(value = "/arsipbkujo/", method = RequestMethod.GET)
    public ModelAndView arsipbkujo(final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = request.getParameter("tahun");
        final String idbku = request.getParameter("idbku");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("idbku", idbku);
        param.put("tahun", tahun);

        final BukuKasUmum bku = bkuServices.getBkuEditJo(param);

        return new ModelAndView("arsipbkubop/arsipbkubopjo", "refbku", bku);
    }

    @RequestMapping(value = "/arsipbkurt/", method = RequestMethod.GET)
    public ModelAndView arsipbkurt(final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = request.getParameter("tahun");
        final String idbku = request.getParameter("idbku");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("idbku", idbku);
        param.put("tahun", tahun);

        final BukuKasUmum bku = bkuServices.getBkuEditJo(param);

        return new ModelAndView("arsipbkubop/arsipbkuboprt", "refbku", bku);
    }

    @RequestMapping(value = "/arsipbkujg/", method = RequestMethod.GET)
    public ModelAndView arsipbkujg(final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = request.getParameter("tahun");
        final String idbku = request.getParameter("idbku");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("idbku", idbku);
        param.put("tahun", tahun);

        final BukuKasUmum bku = bkuServices.getBkuEditJo(param);

        return new ModelAndView("arsipbkubop/arsipbkubopjg", "refbku", bku);
    }

    @RequestMapping(value = "/arsipbkuspj/", method = RequestMethod.GET)
    public ModelAndView arsipbkuspj(final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = request.getParameter("tahun");
        final String idbku = request.getParameter("idbku");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("idbku", idbku);
        param.put("tahun", tahun);

        final BukuKasUmum bku = bkuServices.getBkuEditSpj(param);

        return new ModelAndView("arsipbkubop/arsipbkubopspj", "refbku", bku);
    }

    @RequestMapping(value = "/arsipbkupajak/", method = RequestMethod.GET)
    public ModelAndView arsipbkupajak(final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = request.getParameter("tahun");
        final String idbku = request.getParameter("idbku");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("idbku", idbku);
        param.put("tahun", tahun);

        final BukuKasUmum bku = bkuServices.getBkuEditPajak(param);

        return new ModelAndView("arsipbkubop/arsipbkuboppajak", "refbku", bku);
    }

    @RequestMapping(value = "/arsipbkust/", method = RequestMethod.GET)
    public ModelAndView arsipbkust(final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = request.getParameter("tahun");
        final String idbku = request.getParameter("idbku");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("idbku", idbku);
        param.put("tahun", tahun);

        final BukuKasUmum bku = bkuServices.getBkuEditJo(param);

        return new ModelAndView("arsipbkubop/arsipbkubopst", "refbku", bku);
    }

    @RequestMapping(value = "/json/getTriwulanByRekap", method = RequestMethod.GET)
    public @ResponseBody
    Integer getTriwulanByRekap(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idsekolah = request.getParameter("idsekolah");

        final Map< String, Object> param = new HashMap<String, Object>(5);
        param.put("tahun", tahunAnggaran);
        param.put("idsekolah", idsekolah);

        return bkuServices.getTriwulanByRekap(param);
    }

    @RequestMapping(value = "/json/getMaxTriwulan", method = RequestMethod.GET)
    public @ResponseBody
    Integer getMaxTriwulan(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idsekolah = request.getParameter("idsekolah");

        final Map< String, Object> param = new HashMap<String, Object>(5);
        param.put("tahun", tahunAnggaran);
        param.put("idsekolah", idsekolah);

        return bkuServices.getMaxTriwulan(param);
    }

    @RequestMapping(value = "/json/getPkBlj", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getPkBlj(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idsekolah = request.getParameter("idsekolah");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("idsekolah", idsekolah);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        mapData.put("aData", bkuServices.getPkBlj(param));
        return mapData;
    }

    @RequestMapping(value = "/listjgpn", method = RequestMethod.GET)
    public ModelAndView listjgpn(final BukuKasUmum bku, final HttpServletRequest request, final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return new ModelAndView("bkubop/listpnjasagiro", "refkegiatan", bku);
    }

    @RequestMapping(value = "/json/getListPnJg", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getListPnJg(final HttpServletRequest request) {

        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");

        final String idsekolah = request.getParameter("idsekolah");
        final String tahun = request.getParameter("tahun");
        final String triwulan = request.getParameter("triwulan");
        final String nobkumohon = request.getParameter("nobkumohon");

        param.put("idsekolah", idsekolah);
        param.put("tahun", tahun);
        param.put("triwulan", triwulan);
        param.put("nobkumohon", nobkumohon);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        final long banyak = bkuServices.getBanyakListPnJg(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", bkuServices.getListPnJg(param));

        return mapData;
    }

    @RequestMapping(value = "/json/getSisaKasSpj", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getSisaKasSpj(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String nobku = request.getParameter("nobku");
        final String idsekolah = request.getParameter("idsekolah");
        final String idkegiatan = request.getParameter("idkegiatan");
        final String triwulan = request.getParameter("triwulan");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", tahunAnggaran);
        param.put("idsekolah", idsekolah);
        param.put("nobku", nobku);
        param.put("triwulan", triwulan);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        mapData.put("aData", bkuServices.getSisaKasSpj(param));
        return mapData;
    }

    @RequestMapping(value = "/json/listspjedit", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listspjedit(final HttpServletRequest request) {

        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");

        final String idsekolah = request.getParameter("idsekolah");
        final String tahun = request.getParameter("tahun");
        final String idkegiatan = request.getParameter("idkegiatan");
        final String nobkumohon = request.getParameter("nobkumohon");
        final String triwulan = request.getParameter("triwulan");

        param.put("idsekolah", idsekolah);
        param.put("tahun", tahun);
        param.put("idkegiatan", idkegiatan);
        param.put("nobkumohon", nobkumohon);
        param.put("triwulan", triwulan);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);

        final long banyak = bkuServices.getBanyakListSpjEdit(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", bkuServices.getListSpjEdit(param));

        return mapData;
    }

    @RequestMapping(value = "/json/prosesdeletepjpn", method = RequestMethod.POST)
    public @ResponseBody
    String prosesdeletepjpn(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        List<Map<String, Object>> nilailist = (List) mapdata.get("nilailist");
        List<BkuRinci> listRinci = new ArrayList<>();
        BukuKasUmum bukukas = new BukuKasUmum();

        final String idbku = (String) mapdata.get("idbku");
        final String kodetransaksi = (String) mapdata.get("kodetransaksi");
        final String idbkuref = (String) mapdata.get("idbkuref");
        final String jenisbayarpajak = (String) mapdata.get("jenisbayarpajak");
        final String jenispembayaran = (String) mapdata.get("jenispembayaran");
        final String nobkuref = (String) mapdata.get("nobkuref");
        final String idsekolah = (String) mapdata.get("idsekolah");

        bukukas.setTahun(tahun);
        bukukas.setIdBku(SipkdHelpers.getIntFromString(idbku.toString()));
        bukukas.setIdEntry(pengguna.getIdPengguna());
        bukukas.setKodeTransaksi(kodetransaksi);
        bukukas.setKodeWilayah(pengguna.getKodeProses());
        bukukas.setKodeKoreksi("0");
        bukukas.setKodePajak(jenisbayarpajak);
        bukukas.setJenisPembayaran(jenispembayaran);
        bukukas.setIdBkuRef(SipkdHelpers.getIntFromString(idbkuref.toString()));
        bukukas.setIdsekolah(SipkdHelpers.getIntFromString(idsekolah.toString()));
        bukukas.setNoBkuRef(nobkuref);

        for (Map<String, Object> mapnilailist : nilailist) {

            BkuRinci bkurinci = new BkuRinci();

            Object idblrinci = mapnilailist.get("idblrinci");
            Object nilaipajakspj = mapnilailist.get("nilaipajakspj");

            bkurinci.setNilaiPajakSpj(SipkdHelpers.getBigDecimalFromString(nilaipajakspj.toString()));
            bkurinci.setIdBku(SipkdHelpers.getIntFromString(idbku.toString()));
            bkurinci.setIdBlRinci(SipkdHelpers.getIntFromString(idblrinci.toString()));
            bkurinci.setIdBkuRef(SipkdHelpers.getIntFromString(idbkuref.toString()));
            bkurinci.setIdEntry(pengguna.getIdPengguna());
            listRinci.add(bkurinci);

        }

        bukukas.setListBkuRinci(listRinci);
        bkuServices.deletePajakPn(bukukas);

        return "Data Buku Kas Umum Berhasil Diubah";
    }

    @RequestMapping(value = "/json/prosescetakbku/", method = RequestMethod.GET)
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        response.setContentType("application/pdf");

        final String idbku = request.getParameter("idbku");
        final String idsekolah = request.getParameter("idsekolah");
        //final Integer idbku = SipkdHelpers.getIntFromString(request.getParameter("idbku"));
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        final Map<String, Object> map = new HashMap<String, Object>();
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String pathReport = servletContext.getInitParameter("PATH_REPORT");

        map.put("SUBREPORT_DIR", pathReport);
        map.put("IDBKU", idbku);
        map.put("IDSEKOLAH", idsekolah);

        map.put("pathreport", pathReport + "/Report_Bukti_Transaksi.jasper");
        map.put("filename", tahunAnggaran + "-" + "Bukti-Transaksi-" + idbku + ".pdf");

        printReportToPdfStream(response, map);
    }

    @RequestMapping(value = "/json/prosescetakbpn/", method = RequestMethod.GET)
    public void prosescetakbpn(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        response.setContentType("application/pdf");

        final String idbku = request.getParameter("idbku");
        final String idsekolah = request.getParameter("idsekolah");
        final String kodetrx = request.getParameter("kodetrx");
        final String tahun = request.getParameter("tahun");
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");

        final Map<String, Object> data = new HashMap<String, Object>();
        final Map<String, Object> map = new HashMap<String, Object>();

        data.put("idsekolah", idsekolah);
        data.put("kodetrx", kodetrx);
        data.put("idbku", idbku);
        data.put("tahun", tahun);
        final PajakTransfer pajakTransfer = pajakServices.getDataPajak(data);
        final String bulkidrequest = pajakTransfer.getBulkIdRequest();
        final String pathReport = servletContext.getInitParameter("PATH_REPORT");
        pajakServices.updateCetakCount(data);

        map.put("SUBREPORT_DIR", pathReport);
        map.put("IDBKUPOT", idbku);
        String filename = "BuktiPenerimaanNegaraBOP_" + tahun + "_" + idsekolah + "_" + idbku + "_" + kodetrx + ".pdf";

        map.put("pathreport", pathReport + "/Report_BuktiPenerimaanNegara_BOP.jasper");
        map.put("filename", filename);

        printReportToPdfStream(response, map);
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }

}
