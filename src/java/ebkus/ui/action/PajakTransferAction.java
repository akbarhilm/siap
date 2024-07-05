package ebkus.ui.action;

import ebkus.model.BukuKasUmum;
import ebkus.model.PajakTransfer;
import ebkus.model.Pengguna;
import ebkus.services.PajakTransferBopServices;
import ebkus.services.PajakTransferBosServices;
import ebkus.util.BigDecimalPropertyEditor;
import ebkus.util.SipkdHelpers;
import ebkus.util.SqlDatePropertyEditor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.catalina.util.Base64;
import org.json.JSONArray;
import org.json.JSONObject;
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
@RequestMapping("/pajaktf")
public class PajakTransferAction {

    private static final Logger log = LoggerFactory.getLogger(PajakTransferAction.class);

    @Autowired
    ServletContext servletContext;
    @Autowired
    PajakTransferBopServices bkuBopServices;
    @Autowired
    PajakTransferBosServices bkuBosServices;

    private final RestTemplate rest;
    private final HttpHeaders headers;
    private HttpStatus status;

    public PajakTransferAction() {
        this.rest = new RestTemplate();
        this.headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "*/*");
    }

    @RequestMapping(value = "/json/transferpajak", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    Map<String, Object> transfersp2d(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) throws IOException {
        HttpEntity<Map<String, Object>> requestPostData = new HttpEntity<Map<String, Object>>(mapdata);
        InputStream inputStream = servletContext.getResourceAsStream("/WEB-INF/txt/transfer.txt");
        String url = SipkdHelpers.readFromInputStream(inputStream);

        ResponseEntity<Map> response = rest.exchange(url, HttpMethod.POST, requestPostData, Map.class);

        return response.getBody();
    }

    @RequestMapping(value = "/tfpajakbos", method = RequestMethod.GET)
    public ModelAndView tfpajakbos(final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idbku = request.getParameter("idbku");
        final String idsekolah = request.getParameter("idsekolah");
        final String kodetrx = request.getParameter("kodetrx");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("idbku", idbku);
        param.put("tahun", tahunAnggaran);
        param.put("idsekolah", idsekolah);
        param.put("kodetrx", kodetrx);

        PajakTransfer pajak = new PajakTransfer();

        pajak = bkuBosServices.getDataPajak(param);

        return new ModelAndView("transferpajak/transferbos", "refbku", pajak);
    }

    @RequestMapping(value = "/tfpajakbop", method = RequestMethod.GET)
    public ModelAndView tfpajakbop(final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idbku = request.getParameter("idbku");
        final String idsekolah = request.getParameter("idsekolah");
        final String kodetrx = request.getParameter("kodetrx");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("idbku", idbku);
        param.put("tahun", tahunAnggaran);
        param.put("idsekolah", idsekolah);
        param.put("kodetrx", kodetrx);

        PajakTransfer pajak = new PajakTransfer();

        pajak = bkuBopServices.getDataPajak(param);

        return new ModelAndView("transferpajak/transferbop", "refbku", pajak);
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

    @RequestMapping(value = "/json/getDataSekolahBos", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getDataSekolahBos(final HttpServletRequest request) {
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String idsekolah = request.getParameter("idsekolah");

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("idsekolah", idsekolah);

        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        mapData.put("aData", bkuBosServices.getDataSekolah(param));
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

    @RequestMapping(value = "/json/create", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    Object ssp(@RequestBody Map<String, String> mapdata, final HttpServletRequest request) {
        Object returnObject;
        try {
            String[] link = SipkdHelpers.getDjpService(servletContext).get(1).split("\\|");
            String url = link[0];
            String username = link[1];
            String password = link[2];

            JSONObject data = new JSONObject();
            JSONObject header = new JSONObject();
            JSONArray body = new JSONArray();
            JSONObject bodyElement = new JSONObject();
//            HEADER
            final String idchannel = "10";
            final String bulkidrequest = mapdata.get("bulkid");

//            BODY
            final String idrequest = mapdata.get("idrequest");
            final String taxid = mapdata.get("npwprekanan");
            final String taxpayername = mapdata.get("namawp");
            final String debitaccountno = mapdata.get("norek");
            final String taxpayeraddress = mapdata.get("alamatwp");
            final String taxpayercity = mapdata.get("kotawp");
            final String typeoftax = mapdata.get("akupajak");
            final String subtypeoftax = mapdata.get("kjs");
            final String taxperiod = mapdata.get("masapajak");
            final String numberofprovisionletter = mapdata.get("nosk");
            final String numberoftaxobject = mapdata.get("nop");
            final String identitynumber = mapdata.get("noidentitas");
            final String paymentamount = mapdata.get("nilaipajak");
            final String taxpayerid = mapdata.get("npwppenyetor");
            final String taxpayeridname = mapdata.get("namapenyetor");
            final String paymentdescription = mapdata.get("uraian");
            final String idapp = mapdata.get("idapp");
            final String kodeapp = mapdata.get("kodeapp");

            header.put("idchannel", idchannel);
            header.put("bulkidrequest", bulkidrequest);

            bodyElement.put("idrequest", idrequest);
            bodyElement.put("taxid", taxid);
            bodyElement.put("taxpayername", taxpayername);
            bodyElement.put("taxpayeraddress", taxpayeraddress);
            bodyElement.put("taxpayercity", taxpayercity);
            bodyElement.put("typeoftax", typeoftax);
            bodyElement.put("subtypeoftax", subtypeoftax);
            bodyElement.put("taxperiod", taxperiod);
            bodyElement.put("numberofprovisionletter", numberofprovisionletter);
            bodyElement.put("numberoftaxobject", numberoftaxobject);
            bodyElement.put("identitynumber", identitynumber);
            bodyElement.put("paymentamount", paymentamount);
            bodyElement.put("taxpayerid", taxpayerid);
            bodyElement.put("taxpayeridname", taxpayeridname);
            bodyElement.put("paymentdescription", paymentdescription);
            bodyElement.put("debitaccountno", debitaccountno);
            bodyElement.put("idapp", idapp);
            bodyElement.put("kodeapp", kodeapp);

            body.put(0, bodyElement);
            data.put("header", header);
            data.put("data", body);
            log.debug("Data - " + data.toString());
            HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Authorization", "Basic " + Base64.encode((username + ":" + password).getBytes()));

            OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
            wr.write(data.toString());

            wr.flush();
            wr.close();

            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            String c;
            StringBuilder sb = new StringBuilder();
            while ((c = reader.readLine()) != null) {
                sb.append(c);
            }
//
            log.debug("Response - " + sb.toString());
            //Response
//            {"header":{"idchannel":"10","bulkidrequest":"201808300000001"},"data":[]}

            JSONObject json = new JSONObject(sb.toString());
            JSONArray dataArray = json.getJSONArray("data");
            JSONObject dataArray2 = json.getJSONObject("header");
            Map<Object, Object> returnData = new HashMap<Object, Object>();
//            Tinggal dilooping berapa datanya
//            contoh:
            int count = 0;
            //for (Object o : dataArray) {
            Map<String, String> dataElement = new HashMap<String, String>();
            Iterator<String> nameItr2 = dataArray2.keys();

            while (nameItr2.hasNext()) {
                String name2 = nameItr2.next();
                dataElement.put(name2, dataArray2.getString(name2));
            }

            Iterator<String> nameItr = ((JSONObject) dataArray.get(0)).keys();
            while (nameItr.hasNext()) {
                String name = nameItr.next();
                if (((JSONObject) dataArray.get(0)).get(name).equals(null)) {
                    dataElement.put(name, "-");
                } else {
                    dataElement.put(name, ((JSONObject) dataArray.get(0)).getString(name));
                }

            }
            //}
            reader.close();
            con.disconnect();
            log.debug("Response JSON - " + json.toString());

            returnObject = dataElement; //sb.toString();

        } catch (Exception ex) {
            returnObject = ex.getMessage().toString();
            log.debug("ERROR - " + ex.getMessage());
            log.debug("ERROR - " + ex.getStackTrace());
        }
        return returnObject;
    }

    @RequestMapping(value = "/json/billing", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    Object billing(@RequestBody Map<String, String> mapdata, final HttpServletRequest request) {
        Object returnObject;
        try {
            String[] link = SipkdHelpers.getDjpService(servletContext).get(2).split("\\|");
            String url = link[0];
            String username = link[1];
            String password = link[2];
            JSONObject data = new JSONObject();
            JSONObject header = new JSONObject();
            JSONArray body = new JSONArray();
            JSONObject bodyElement = new JSONObject();
//            HEADER
            final String idchannel = "10";
            final String bulkidrequest = mapdata.get("bulkid");

//            BODY
            final String idrequest = mapdata.get("idrequest");
            final String debitaccountno = mapdata.get("norek");
            final String billingcode = mapdata.get("kodebilling");
            final String taxpayerid = mapdata.get("npwppenyetor");
            final String taxpayeridname = mapdata.get("namapenyetor");
            final String paymentdescription = mapdata.get("uraian");
            final String idapp = mapdata.get("idapp");
            final String kodeapp = mapdata.get("kodeapp");

            header.put("idchannel", idchannel);
            header.put("bulkidrequest", bulkidrequest);

            bodyElement.put("idrequest", idrequest);
            bodyElement.put("debitaccountno", debitaccountno);
            bodyElement.put("billingcode", billingcode);
            bodyElement.put("taxpayerid", taxpayerid);
            bodyElement.put("taxpayeridname", taxpayeridname);
            bodyElement.put("paymentdescription", paymentdescription);
            bodyElement.put("idapp", idapp);
            bodyElement.put("kodeapp", kodeapp);

            body.put(0, bodyElement);
            data.put("header", header);
            data.put("data", body);
            log.debug("Data - " + data.toString());
            HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Authorization", "Basic " + Base64.encode((username + ":" + password).getBytes()));

            OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
            wr.write(data.toString());
//
            wr.flush();
            wr.close();
            log.debug("Output : " + con.getOutputStream());
            log.debug("Response Code : " + con.getResponseCode());
            log.debug("Response Message : " + con.getResponseMessage());
            log.debug("Error Stream : " + con.getErrorStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            String c;
            StringBuilder sb = new StringBuilder();
            while ((c = reader.readLine()) != null) {
                sb.append(c);
            }
//
            log.debug("Response - " + sb.toString());
            //RESPONSE
//            {"header":{"idchannel":"10","bulkidrequest":"201808300000001"},"data":[{"idrequest":"201808300000002","idresponse":"000000000000015","debitaccountno":null,"taxid":null,"taxpayername":null,"taxpayeraddress":null,"typeoftax":null,"subtypeoftax":null,"typeoftaxdescription":null,"subtypeoftaxdescription":null,"taxperiod":null,"numberofprovisionletter":null,"numberoftaxobject":null,"paymentamount":null,"taxpayerid":null,"taxpayeridname":null,"paymentdescription":null,"billingccy":null,"branchcode":null,"processingcode":null,"responsecode":"01","responsecodedescription":"Tagihan Tidak Tersedia","billingcode":"810000000000003","stan":null,"datetimeofpayment":null,"datetimeoftransmission":null,"settlementdate":null,"ntbnumber":null,"ntpnnumber":null,"bpnstatus":null}]}

            JSONObject json = new JSONObject(sb.toString());
            JSONArray dataArray = json.getJSONArray("data");
            JSONObject dataArray2 = json.getJSONObject("header");
            Map<Object, Object> returnData = new HashMap<Object, Object>();

            Map<String, String> dataElement = new HashMap<String, String>();
            Iterator<String> nameItr2 = dataArray2.keys();

            while (nameItr2.hasNext()) {
                String name2 = nameItr2.next();
                dataElement.put(name2, dataArray2.getString(name2));
            }

            Iterator<String> nameItr = ((JSONObject) dataArray.get(0)).keys();
            while (nameItr.hasNext()) {
                String name = nameItr.next();
                if (((JSONObject) dataArray.get(0)).get(name).equals(null)) {
                    dataElement.put(name, "-");
                } else {
                    dataElement.put(name, ((JSONObject) dataArray.get(0)).getString(name));
                }
            }

            reader.close();
            con.disconnect();
            log.debug("Response JSON - " + json.toString());

            returnObject = dataElement;

        } catch (Exception ex) {
            returnObject = ex.getMessage().toString();
            log.debug("ERROR - " + ex.getMessage());
            log.debug("ERROR - " + ex.getStackTrace());
        }
        return returnObject;
    }

    @RequestMapping(value = "/json/reinquirybilling", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    Object reinquirybilling(@RequestBody Map<String, String> mapdata, final HttpServletRequest request) {
        Object returnObject;
        try {
            String[] link = SipkdHelpers.getDjpService(servletContext).get(4).split("\\|");
            String url = link[0];
            String username = link[1];
            String password = link[2];
            JSONObject data = new JSONObject();
            JSONObject header = new JSONObject();
            JSONArray body = new JSONArray();
            JSONObject bodyElement = new JSONObject();
//            HEADER
            final String idchannel = "10";
            final String bulkidrequest = mapdata.get("bulkid");

//            BODY
            final String idrequest = mapdata.get("idrequest");
            final String billingcode = mapdata.get("kodebilling");
            final String ntbnumber = mapdata.get("ntb");
            final String idapp = mapdata.get("idapp");
            final String kodeapp = mapdata.get("kodeapp");

            header.put("idchannel", idchannel);
            header.put("bulkidrequest", bulkidrequest);

            bodyElement.put("idrequest", idrequest);
            bodyElement.put("billingcode", billingcode);
            bodyElement.put("ntbnumber", ntbnumber);
            bodyElement.put("idapp", idapp);
            bodyElement.put("kodeapp", kodeapp);

            body.put(0, bodyElement);
            data.put("header", header);
            data.put("data", body);
            log.debug("Data - " + data.toString());
            HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Authorization", "Basic " + Base64.encode((username + ":" + password).getBytes()));

            OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
            wr.write(data.toString());
//
            wr.flush();
            wr.close();
            log.debug("Output : " + con.getOutputStream());
            log.debug("Response Code : " + con.getResponseCode());
            log.debug("Response Message : " + con.getResponseMessage());
            log.debug("Error Stream : " + con.getErrorStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            String c;
            StringBuilder sb = new StringBuilder();
            while ((c = reader.readLine()) != null) {
                sb.append(c);
            }

            JSONObject json = new JSONObject(sb.toString());
            JSONArray dataArray = json.getJSONArray("data");
            JSONObject dataArray2 = json.getJSONObject("header");
            Map<Object, Object> returnData = new HashMap<Object, Object>();

            Map<String, String> dataElement = new HashMap<String, String>();
            Iterator<String> nameItr2 = dataArray2.keys();

            while (nameItr2.hasNext()) {
                String name2 = nameItr2.next();
                dataElement.put(name2, dataArray2.getString(name2));
            }

            Iterator<String> nameItr = ((JSONObject) dataArray.get(0)).keys();
            while (nameItr.hasNext()) {
                String name = nameItr.next();
                if (((JSONObject) dataArray.get(0)).get(name).equals(null)) {
                    dataElement.put(name, "-");
                } else {
                    dataElement.put(name, ((JSONObject) dataArray.get(0)).getString(name));
                }
            }

            reader.close();
            con.disconnect();

            returnObject = dataElement;

        } catch (Exception ex) {
            returnObject = ex.getMessage().toString();
            log.debug("ERROR - " + ex.getMessage());
            log.debug("ERROR - " + ex.getStackTrace());
        }
        return returnObject;
    }

    @RequestMapping(value = "/json/inquirybilling", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    Object inquirybilling(@RequestBody Map<String, String> mapdata, final HttpServletRequest request) {
        Object returnObject;
        try {
            String[] link = SipkdHelpers.getDjpService(servletContext).get(3).split("\\|");
            String url = link[0];
            String username = link[1];
            String password = link[2];
            JSONObject data = new JSONObject();
            JSONObject header = new JSONObject();
            JSONArray body = new JSONArray();
            JSONObject bodyElement = new JSONObject();
//            HEADER
            final String idchannel = "10";
            final String bulkidrequest = bkuBosServices.getGeneratedIdChar("BULKIDREQUEST");

//            BODY
            final String idrequest = bkuBosServices.getGeneratedIdChar("IDREQUEST");
            final String billingcode = mapdata.get("billingcode");
            final String idapp = mapdata.get("idapp");
            final String kodeapp = mapdata.get("kodeapp");

            header.put("idchannel", idchannel);
            header.put("bulkidrequest", bulkidrequest);

            bodyElement.put("idrequest", idrequest);
            bodyElement.put("billingcode", billingcode);
            bodyElement.put("idapp", idapp);
            bodyElement.put("kodeapp", kodeapp);

            body.put(0, bodyElement);
            data.put("header", header);
            data.put("data", body);
            log.debug("Data - " + data.toString());
            HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Authorization", "Basic " + Base64.encode((username + ":" + password).getBytes()));

            OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
            wr.write(data.toString());
//
            wr.flush();
            wr.close();
            log.debug("Output : " + con.getOutputStream());
            log.debug("Response Code : " + con.getResponseCode());
            log.debug("Response Message : " + con.getResponseMessage());
            log.debug("Error Stream : " + con.getErrorStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            String c;
            StringBuilder sb = new StringBuilder();
            while ((c = reader.readLine()) != null) {
                sb.append(c);
            }
//
            log.debug("Response - " + sb.toString());
            //RESPONSE
//            {"header":{"idchannel":"10","bulkidrequest":"201810230000001"},"data":[{"idrequest":"201810230000002","idresponse":"380405036149140","paymentamount":null,"responsecode":"EC","responsecodedescription":"data tagihan sudah terdapat di database bank","billingcode":"199999999017411","idapp":"000000000530994","kodeapp":"001","taxid":null,"taxpayername":null,"taxpayeraddress":null,"typeoftax":null,"subtypeoftax":null,"typeoftaxdescription":null,"subtypeoftaxdescription":null,"taxperiod":null,"numberofprovisionletter ":null,"numberoftaxobject":null}]}

            JSONObject json = new JSONObject(sb.toString());
            JSONArray dataArray = json.getJSONArray("data");
            JSONObject dataArray2 = json.getJSONObject("header");
            Map<Object, Object> returnData = new HashMap<Object, Object>();

            Map<String, String> dataElement = new HashMap<String, String>();
            Iterator<String> nameItr2 = dataArray2.keys();

            while (nameItr2.hasNext()) {
                String name2 = nameItr2.next();
                dataElement.put(name2, dataArray2.getString(name2));
            }

            Iterator<String> nameItr = ((JSONObject) dataArray.get(0)).keys();
            while (nameItr.hasNext()) {
                String name = nameItr.next();
                if (((JSONObject) dataArray.get(0)).get(name).equals(null)) {
                    dataElement.put(name, "-");
                } else {
                    dataElement.put(name, ((JSONObject) dataArray.get(0)).getString(name));
                }
            }

            reader.close();
            con.disconnect();
            log.debug("Response JSON - " + json.toString());

            returnObject = dataElement;
        } catch (Exception ex) {
            log.debug("ERROR - " + ex.getMessage());
            log.debug("ERROR - " + ex.getStackTrace());
            returnObject = ex.getMessage().toString();
        }
        return returnObject;
    }

    @RequestMapping(value = "/json/simpandjpcreate", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> simpandjpcreate(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        final String tahun = (String) mapdata.get("tahun");
        final String idbku = (String) mapdata.get("idbku");
        final String kodetrx = (String) mapdata.get("kodetrx");
        final String norek = (String) mapdata.get("norek");
        final String npwprekanan = (String) mapdata.get("npwprekanan");
        final String namawp = (String) mapdata.get("namawp");
        final String alamatwp = (String) mapdata.get("alamatwp");
        final String kotawp = (String) mapdata.get("kotawp");
        final String kjs = (String) mapdata.get("kjs");
        final String akupajak = (String) mapdata.get("akupajak");
        final String masapajak = (String) mapdata.get("masapajak");
        final String tahunpajak = (String) mapdata.get("tahunpajak");
        final String nosk = (String) mapdata.get("nosk");
        final String nop = (String) mapdata.get("nop");
        final String noidentitas = (String) mapdata.get("noidentitas");
        final String nilaipajak = (String) mapdata.get("nilaipajak");
        final String npwppenyetor = (String) mapdata.get("npwppenyetor");
        final String namapenyetor = (String) mapdata.get("namapenyetor");
        final String uraian = (String) mapdata.get("uraian");
        final String koderequest = (String) mapdata.get("koderequest");
        final String kodeapp = (String) mapdata.get("kodeapp");
        final String idsekolah = (String) mapdata.get("idsekolah");

        String idrequest;
        String bulkid;

        if ("002".equals(kodeapp)) { // BOS
            idrequest = bkuBosServices.getGeneratedIdChar("IDREQUEST");
            bulkid = bkuBosServices.getGeneratedIdChar("BULKIDREQUEST");
        } else { // 003 - BOP
            idrequest = bkuBopServices.getGeneratedIdChar("IDREQUEST");
            bulkid = bkuBopServices.getGeneratedIdChar("BULKIDREQUEST");
        }

        PajakTransfer pajak = new PajakTransfer();

        pajak.setTahun(tahun);
        pajak.setKodeTransaksi(kodetrx);
        pajak.setIdBku(SipkdHelpers.getIntFromString(idbku));
        pajak.setIdsekolah(SipkdHelpers.getIntFromString(idsekolah));
        pajak.setNorekBank(norek);
        pajak.setNpwpRekanan(npwprekanan);
        pajak.setNamaWp(namawp);
        pajak.setAlamatWp(alamatwp);
        pajak.setKotaWp(kotawp);
        pajak.setKodeJenisSetor(kjs);
        pajak.setAkunPajak(akupajak);
        pajak.setMasaPajak(masapajak);
        pajak.setTahunPajak(tahunpajak);
        pajak.setNoSk(nosk);
        pajak.setNop(nop);
        pajak.setNoIdentitas(noidentitas);
        pajak.setNilaiPajak(SipkdHelpers.getBigDecimalFromString(nilaipajak));
        pajak.setNpwpSekolah(npwppenyetor);
        pajak.setNamaWpSekolah(namapenyetor);
        pajak.setUraian(uraian);
        pajak.setKodeRequest(koderequest);
        pajak.setKodeApp(kodeapp);
        pajak.setBulkIdRequest(bulkid);
        pajak.setIdRequest(idrequest);
        pajak.setIdChannel("10");
        pajak.setIdEntry(pengguna.getIdPengguna());

        bkuBosServices.insertDjpCreate(pajak);
        final Map<String, Object> data = new HashMap<String, Object>(4);

        data.put("idrequest", idrequest);
        data.put("bulkid", bulkid);

        return data;
    }

    @RequestMapping(value = "/json/updatedjpcreate", method = RequestMethod.POST)
    public @ResponseBody
    String updatedjpcreate(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        final String idresponse = (String) mapdata.get("idresponse");
        final String namakjs = (String) mapdata.get("namakjs");
        final String namamap = (String) mapdata.get("namamap");
        final String kodeproses = (String) mapdata.get("kodeproses");
        final String koderesponse = (String) mapdata.get("koderesponse");
        final String uraianresponse = (String) mapdata.get("uraianresponse");
        final String kodebilling = (String) mapdata.get("kodebilling");
        final String tglexp = (String) mapdata.get("tglexp");
        final String kodestan = (String) mapdata.get("kodestan");
        final String tglbayar = (String) mapdata.get("tglbayar");
        final String tgltransmisi = (String) mapdata.get("tgltransmisi");
        final String tglbuku = (String) mapdata.get("tglbuku");
        final String ntb = (String) mapdata.get("ntb");
        final String ntpn = (String) mapdata.get("ntpn");
        final String statusbpn = (String) mapdata.get("statusbpn");
        final String idbku = (String) mapdata.get("idbku");
        final String bulkidreq = (String) mapdata.get("bulkidreq");
        final String idsekolah = (String) mapdata.get("idsekolah");

        PajakTransfer pajak = new PajakTransfer();

        pajak.setIdBku(SipkdHelpers.getIntFromString(idbku));
        pajak.setIdResponse(idresponse);
        pajak.setNamaKJS(namakjs);
        pajak.setNamaMAP(namamap);
        pajak.setKodeProses(kodeproses);
        pajak.setKodeResponse(koderesponse);
        pajak.setUraianResponse(uraianresponse);
        pajak.setKodeBilling(kodebilling);
        pajak.setTglBillExp(SipkdHelpers.getDateFromString(tglexp));
        pajak.setKodeStan(kodestan);
        pajak.setTglBillExpString(tglexp);
        pajak.setTglBayarString(tglbayar);
        pajak.setTglTransmisiString(tgltransmisi);
        pajak.setTglBuku(tglbuku);
        pajak.setNtb(ntb);
        pajak.setNtpn(ntpn);
        pajak.setStatusBpn(statusbpn);
        pajak.setBulkIdRequest(bulkidreq);
        pajak.setIdsekolah(SipkdHelpers.getIntFromString(idsekolah));
        pajak.setIdEntry(pengguna.getIdPengguna());

        bkuBosServices.updateDjpCreate(pajak);

        return "Data Pembuatan Kode Billing Pajak Berhasil Diupdate";
    }

    @RequestMapping(value = "/json/updatepotrincibos", method = RequestMethod.POST)
    public @ResponseBody
    String updatepotrincibos(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        final String bulkidreq = (String) mapdata.get("bulkidreq");
        final String idrequest = (String) mapdata.get("idrequest");
        final String kodebilling = (String) mapdata.get("kodebilling");
        final String tglexp = (String) mapdata.get("tglexp");
        final String tglbuku = (String) mapdata.get("tglbuku");
        final String statusbpn = (String) mapdata.get("statusbpn");
        final String ntb = (String) mapdata.get("ntb");
        final String ntpn = (String) mapdata.get("ntpn");
        final String tglbayar = (String) mapdata.get("tglbayar");
        final String idbku = (String) mapdata.get("idbku");
        final String kodereq = (String) mapdata.get("kodereq");
        final String masapajak = (String) mapdata.get("masapajak");
        final String kodestan = (String) mapdata.get("kodestan");

        PajakTransfer pajak = new PajakTransfer();

        pajak.setIdBku(SipkdHelpers.getIntFromString(idbku));
        pajak.setIdRequest(idrequest);
        pajak.setKodeRequest(kodereq);
        pajak.setKodeBilling(kodebilling);
        pajak.setTglBillExpString(tglexp);
        pajak.setTglBayarString(tglbayar);
        pajak.setTglBuku(tglbuku);
        pajak.setNtb(ntb);
        pajak.setNtpn(ntpn);
        pajak.setMasaPajak(masapajak);
        pajak.setKodeStan(kodestan);
        pajak.setStatusBpn(statusbpn);
        pajak.setBulkIdRequest(bulkidreq);
        pajak.setIdEntry(pengguna.getIdPengguna());

        bkuBosServices.updateRinciPotPajak(pajak);

        return "Data Rinci Potongan Pajak Berhasil Diupdate";
    }

    @RequestMapping(value = "/json/updatepotrincibop", method = RequestMethod.POST)
    public @ResponseBody
    String updatepotrincibop(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        final String bulkidreq = (String) mapdata.get("bulkidreq");
        final String idrequest = (String) mapdata.get("idrequest");
        final String kodebilling = (String) mapdata.get("kodebilling");
        final String tglexp = (String) mapdata.get("tglexp");
        final String tglbuku = (String) mapdata.get("tglbuku");
        final String statusbpn = (String) mapdata.get("statusbpn");
        final String ntb = (String) mapdata.get("ntb");
        final String ntpn = (String) mapdata.get("ntpn");
        final String tglbayar = (String) mapdata.get("tglbayar");
        final String idbku = (String) mapdata.get("idbku");
        final String kodereq = (String) mapdata.get("kodereq");
        final String masapajak = (String) mapdata.get("masapajak");
        final String kodestan = (String) mapdata.get("kodestan");

        PajakTransfer pajak = new PajakTransfer();

        pajak.setIdBku(SipkdHelpers.getIntFromString(idbku));
        pajak.setIdRequest(idrequest);
        pajak.setKodeRequest(kodereq);
        pajak.setKodeBilling(kodebilling);
        pajak.setTglBillExpString(tglexp);
        pajak.setTglBayarString(tglbayar);
        pajak.setTglBuku(tglbuku);
        pajak.setNtb(ntb);
        pajak.setNtpn(ntpn);
        pajak.setStatusBpn(statusbpn);
        pajak.setMasaPajak(masapajak);
        pajak.setKodeStan(kodestan);
        pajak.setBulkIdRequest(bulkidreq);
        pajak.setIdEntry(pengguna.getIdPengguna());

        bkuBopServices.updateRinciPotPajak(pajak);

        return "Data Rinci Potongan Pajak Berhasil Diupdate";
    }

    @RequestMapping(value = "/json/updateBkuByIdBos", method = RequestMethod.POST)
    public @ResponseBody
    String updateBkuByIdBos(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");

        BukuKasUmum bukukas = new BukuKasUmum();

        final String kodetransaksi = (String) mapdata.get("kodetransaksi");
        final String tglposting = (String) mapdata.get("tglposting");
        final String idbku = (String) mapdata.get("idbku");
        final String idsekolah = (String) mapdata.get("idsekolah");

        bukukas.setTahun(tahun);
        bukukas.setIdEntry(pengguna.getIdPengguna());
        bukukas.setKodeTransaksi(kodetransaksi);
        bukukas.setIdBku(SipkdHelpers.getIntFromString(idbku));
        bukukas.setIdsekolah(SipkdHelpers.getIntFromString(idsekolah));
        bukukas.setTglPosting(tglposting);
        bukukas.setIdEntry(pengguna.getIdPengguna());

        bkuBosServices.updateBkuById(bukukas);

        return "Transaksi BKU Berhasil Dibayarkan";
    }

    @RequestMapping(value = "/json/updateBkuByIdBop", method = RequestMethod.POST)
    public @ResponseBody
    String updateBkuByIdBop(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");

        BukuKasUmum bukukas = new BukuKasUmum();

        final String kodetransaksi = (String) mapdata.get("kodetransaksi");
        final String tglposting = (String) mapdata.get("tglposting");
        final String idbku = (String) mapdata.get("idbku");
        final String idsekolah = (String) mapdata.get("idsekolah");

        bukukas.setTahun(tahun);
        bukukas.setIdEntry(pengguna.getIdPengguna());
        bukukas.setKodeTransaksi(kodetransaksi);
        bukukas.setIdBku(SipkdHelpers.getIntFromString(idbku));
        bukukas.setIdsekolah(SipkdHelpers.getIntFromString(idsekolah));
        bukukas.setTglPosting(tglposting);
        bukukas.setIdEntry(pengguna.getIdPengguna());

        bkuBopServices.updateBkuById(bukukas);

        return "Transaksi BKU Berhasil Dibayarkan";
    }

    @RequestMapping(value = "/json/simpandjpbilling", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> simpandjpbilling(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        final String tahun = (String) mapdata.get("tahun");
        final String idbku = (String) mapdata.get("idbku");
        final String kodetrx = (String) mapdata.get("kodetrx");
        final String koderequest = (String) mapdata.get("koderequest");
        final String kodeapp = (String) mapdata.get("kodeapp");
        final String norek = (String) mapdata.get("norek");
        final String tahunpajak = (String) mapdata.get("tahunpajak");
        final String npwppenyetor = (String) mapdata.get("npwppenyetor");
        final String namapenyetor = (String) mapdata.get("namapenyetor");
        final String uraian = (String) mapdata.get("uraian");
        final String kodebilling = (String) mapdata.get("kodebilling");
        final String idsekolah = (String) mapdata.get("idsekolah");

        String idrequest;
        String bulkid;

        if ("002".equals(kodeapp)) { // BOS
            idrequest = bkuBosServices.getGeneratedIdChar("IDREQUEST");
            bulkid = bkuBosServices.getGeneratedIdChar("BULKIDREQUEST");
        } else { // 003 - BOP
            idrequest = bkuBopServices.getGeneratedIdChar("IDREQUEST");
            bulkid = bkuBopServices.getGeneratedIdChar("BULKIDREQUEST");
        }

        PajakTransfer pajak = new PajakTransfer();

        pajak.setTahun(tahun);
        pajak.setKodeTransaksi(kodetrx);
        pajak.setIdBku(SipkdHelpers.getIntFromString(idbku));
        pajak.setNorekBank(norek);
        pajak.setTahunPajak(tahunpajak);
        pajak.setNpwpSekolah(npwppenyetor);
        pajak.setNamaWpSekolah(namapenyetor);
        pajak.setUraian(uraian);
        pajak.setKodeRequest(koderequest);
        pajak.setKodeApp(kodeapp);
        pajak.setBulkIdRequest(bulkid);
        pajak.setKodeBilling(kodebilling);
        pajak.setIdRequest(idrequest);
        pajak.setIdChannel("10");
        pajak.setIdsekolah(SipkdHelpers.getIntFromString(idsekolah));
        pajak.setIdEntry(pengguna.getIdPengguna());

        bkuBosServices.insertDjpBilling(pajak);
        final Map<String, Object> data = new HashMap<String, Object>(4);

        data.put("idrequest", idrequest);
        data.put("bulkid", bulkid);

        return data;
    }

    @RequestMapping(value = "/json/updatedjpbilling", method = RequestMethod.POST)
    public @ResponseBody
    String updatedjpbilling(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        final String idresponse = (String) mapdata.get("idresponse");
        final String npwprekanan = (String) mapdata.get("npwprekanan");
        final String namawp = (String) mapdata.get("namawp");
        final String alamatwp = (String) mapdata.get("alamatwp");
        final String kotawp = (String) mapdata.get("kotawp");
        final String kjs = (String) mapdata.get("kjs");
        final String akupajak = (String) mapdata.get("akupajak");
        final String namakjs = (String) mapdata.get("namakjs");
        final String namamap = (String) mapdata.get("namamap");
        final String masapajak = (String) mapdata.get("masapajak");
        final String nosk = (String) mapdata.get("nosk");
        final String nop = (String) mapdata.get("nop");
        final String noidentitas = (String) mapdata.get("noidentitas");
        final String nilaipajak = (String) mapdata.get("nilaipajak");
        final String kodeproses = (String) mapdata.get("kodeproses");
        final String koderesponse = (String) mapdata.get("koderesponse");
        final String uraianresponse = (String) mapdata.get("uraianresponse");
        final String tglexp = (String) mapdata.get("tglexp");
        final String kodestan = (String) mapdata.get("kodestan");
        final String tglbayar = (String) mapdata.get("tglbayar");
        final String tgltransmisi = (String) mapdata.get("tgltransmisi");
        final String tglbuku = (String) mapdata.get("tglbuku");
        final String ntb = (String) mapdata.get("ntb");
        final String ntpn = (String) mapdata.get("ntpn");
        final String statusbpn = (String) mapdata.get("statusbpn");
        final String idbku = (String) mapdata.get("idbku");
        final String bulkidreq = (String) mapdata.get("bulkidreq");
        final String idsekolah = (String) mapdata.get("idsekolah");

        PajakTransfer pajak = new PajakTransfer();

        pajak.setIdBku(SipkdHelpers.getIntFromString(idbku));
        pajak.setIdResponse(idresponse);
        pajak.setNamaKJS(namakjs);
        pajak.setNamaMAP(namamap);
        pajak.setKodeProses(kodeproses);
        pajak.setKodeResponse(koderesponse);
        pajak.setUraianResponse(uraianresponse);
        pajak.setTglBillExpString(tglexp);
        pajak.setKodeStan(kodestan);
        pajak.setTglBayarString(tglbayar);
        pajak.setTglTransmisiString(tgltransmisi);
        pajak.setTglBuku(tglbuku);
        pajak.setNtb(ntb);
        pajak.setNtpn(ntpn);
        pajak.setStatusBpn(statusbpn);
        pajak.setBulkIdRequest(bulkidreq);
        pajak.setNpwpRekanan(npwprekanan);
        pajak.setNamaWp(namawp);
        pajak.setAlamatWp(alamatwp);
        pajak.setKotaWp(kotawp);
        pajak.setKodeJenisSetor(kjs);
        pajak.setAkunPajak(akupajak);
        pajak.setMasaPajak(masapajak);
        pajak.setNoSk(nosk);
        pajak.setNop(nop);
        pajak.setNoIdentitas(noidentitas);
        pajak.setNilaiPajak(SipkdHelpers.getBigDecimalFromString(nilaipajak));
        pajak.setIdsekolah(SipkdHelpers.getIntFromString(idsekolah));
        pajak.setIdEntry(pengguna.getIdPengguna());

        bkuBosServices.updateDjpBilling(pajak);

        return "Data Pembayaran Pajak Berhasil Diupdate";
    }

    @RequestMapping(value = "/json/simpandjpreinquiry", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> simpandjpreinquiry(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        final String tahun = (String) mapdata.get("tahun");
        final String idbku = (String) mapdata.get("idbku");
        final String kodetrx = (String) mapdata.get("kodetrx");
        final String koderequest = (String) mapdata.get("koderequest");
        final String kodeapp = (String) mapdata.get("kodeapp");
        final String ntb = (String) mapdata.get("ntb");
        final String kodebilling = (String) mapdata.get("kodebilling");
        final String norekbank = (String) mapdata.get("norekbank");
        final String tahunpajak = (String) mapdata.get("tahunpajak");
        final String idsekolah = (String) mapdata.get("idsekolah");

        String idrequest;
        String bulkid;

        if ("002".equals(kodeapp)) { // BOS
            idrequest = bkuBosServices.getGeneratedIdChar("IDREQUEST");
            bulkid = bkuBosServices.getGeneratedIdChar("BULKIDREQUEST");
        } else { // 003 - BOP
            idrequest = bkuBopServices.getGeneratedIdChar("IDREQUEST");
            bulkid = bkuBopServices.getGeneratedIdChar("BULKIDREQUEST");
        }

        PajakTransfer pajak = new PajakTransfer();

        pajak.setTahun(tahun);
        pajak.setKodeTransaksi(kodetrx);
        pajak.setIdBku(SipkdHelpers.getIntFromString(idbku));
        pajak.setNtb(ntb);
        pajak.setKodeRequest(koderequest);
        pajak.setKodeApp(kodeapp);
        pajak.setBulkIdRequest(bulkid);
        pajak.setKodeBilling(kodebilling);
        pajak.setIdRequest(idrequest);
        pajak.setNorekBank(norekbank);
        pajak.setTahunPajak(tahunpajak);
        pajak.setIdChannel("10");
        pajak.setIdsekolah(SipkdHelpers.getIntFromString(idsekolah));
        pajak.setIdEntry(pengguna.getIdPengguna());

        bkuBosServices.insertDjpReinquiry(pajak);
        final Map<String, Object> data = new HashMap<String, Object>(4);

        data.put("idrequest", idrequest);
        data.put("bulkid", bulkid);

        return data;
    }

    @RequestMapping(value = "/json/updatedjpreinquiry", method = RequestMethod.POST)
    public @ResponseBody
    String updatedjpreinquiry(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        final String idresponse = (String) mapdata.get("idresponse");
        final String npwprekanan = (String) mapdata.get("npwprekanan");
        final String namawp = (String) mapdata.get("namawp");
        final String alamatwp = (String) mapdata.get("alamatwp");
        final String kjs = (String) mapdata.get("kjs");
        final String akupajak = (String) mapdata.get("akupajak");
        final String masapajak = (String) mapdata.get("masapajak");
        final String nosk = (String) mapdata.get("nosk");
        final String nop = (String) mapdata.get("nop");
        final String noidentitas = (String) mapdata.get("noidentitas");
        final String nilaipajak = (String) mapdata.get("nilaipajak");
        final String npwppenyetor = (String) mapdata.get("npwppenyetor");
        final String namapenyetor = (String) mapdata.get("namapenyetor");
        final String uraian = (String) mapdata.get("uraian");
        final String kodeproses = (String) mapdata.get("kodeproses");
        final String koderesponse = (String) mapdata.get("koderesponse");
        final String uraianresponse = (String) mapdata.get("uraianresponse");
        final String tglexp = (String) mapdata.get("tglexp");
        final String kodestan = (String) mapdata.get("kodestan");
        final String tglbayar = (String) mapdata.get("tglbayar");
        final String tgltransmisi = (String) mapdata.get("tgltransmisi");
        final String tglbuku = (String) mapdata.get("tglbuku");
        final String ntpn = (String) mapdata.get("ntpn");
        final String statusbpn = (String) mapdata.get("statusbpn");
        final String idbku = (String) mapdata.get("idbku");
        final String bulkidreq = (String) mapdata.get("bulkidreq");
        final String idsekolah = (String) mapdata.get("idsekolah");

        PajakTransfer pajak = new PajakTransfer();

        pajak.setIdBku(SipkdHelpers.getIntFromString(idbku));
        pajak.setIdsekolah(SipkdHelpers.getIntFromString(idsekolah));
        pajak.setIdResponse(idresponse);
        pajak.setKodeProses(kodeproses);
        pajak.setKodeResponse(koderesponse);
        pajak.setUraianResponse(uraianresponse);
        pajak.setTglBillExp(SipkdHelpers.getDateFromString(tglexp));
        pajak.setKodeStan(kodestan);
        pajak.setTglBayar(SipkdHelpers.getDateFromString(tglbayar));
        pajak.setTglTransmisi(SipkdHelpers.getDateFromString(tgltransmisi));
        pajak.setTglBuku(tglbuku);
        pajak.setNtpn(ntpn);
        pajak.setStatusBpn(statusbpn);
        pajak.setBulkIdRequest(bulkidreq);
        pajak.setNpwpRekanan(npwprekanan);
        pajak.setNamaWp(namawp);
        pajak.setAlamatWp(alamatwp);
        pajak.setKodeJenisSetor(kjs);
        pajak.setAkunPajak(akupajak);
        pajak.setMasaPajak(masapajak);
        pajak.setNoSk(nosk);
        pajak.setNop(nop);
        pajak.setNoIdentitas(noidentitas);
        pajak.setNilaiPajak(SipkdHelpers.getBigDecimalFromString(nilaipajak));
        pajak.setNpwpSekolah(npwppenyetor);
        pajak.setNamaWpSekolah(namapenyetor);
        pajak.setUraian(uraian);
        pajak.setIdEntry(pengguna.getIdPengguna());

        bkuBosServices.updateDjpReinquiry(pajak);

        return "Data Reinsquiry Pajak Berhasil Diupdate";
    }

    @RequestMapping(value = "/json/simpaninquiry", method = RequestMethod.POST)
    public @ResponseBody
    String simpaninquiry(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final String idbku = (String) mapdata.get("idbku");
        final String npwprekanan = (String) mapdata.get("npwprekanan");
        final String namawp = (String) mapdata.get("namawp");
        final String alamatwp = (String) mapdata.get("alamatwp");
        final String nilaipajak = (String) mapdata.get("nilaipajak");
        final String kodebilling = (String) mapdata.get("kodebilling");
        final String kodeapp = (String) mapdata.get("kodeapp");

        PajakTransfer pajak = new PajakTransfer();

        pajak.setIdBku(SipkdHelpers.getIntFromString(idbku));
        pajak.setNpwpRekanan(npwprekanan);
        pajak.setNamaWp(namawp);
        pajak.setAlamatWp(alamatwp);
        pajak.setNilaiPajak(SipkdHelpers.getBigDecimalFromString(nilaipajak));
        pajak.setKodeBilling(kodebilling);
        pajak.setIdEntry(pengguna.getIdPengguna());
        log.info("ISI PAJAK SIMPAN INQUIRY" + pajak.toString());
        if (kodeapp.equals("002")) {
            bkuBosServices.updateInquiryPot(pajak);
        } else {
            bkuBopServices.updateInquiryPot(pajak);
        }

        return "Data Inquiry Pajak Berhasil Disimpan";
    }

    @RequestMapping(value = "/json/simpanpotrincibos", method = RequestMethod.POST)
    public @ResponseBody
    String simpanpotrincibos(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        final String idbku = (String) mapdata.get("idbku");
        final String bulkidreq = (String) mapdata.get("bulkidreq");
        final String idrequest = (String) mapdata.get("idrequest");
        final String kodebilling = (String) mapdata.get("kodebilling");
        final String tglexp = (String) mapdata.get("tglexp");
        final String kjs = (String) mapdata.get("kjs");
        final String kodemap = (String) mapdata.get("kodemap");
        final String idsekolah = (String) mapdata.get("idsekolah");
        final String tahun = (String) mapdata.get("tahun");

        PajakTransfer pajak = new PajakTransfer();

        pajak.setIdBku(SipkdHelpers.getIntFromString(idbku));
        pajak.setIdsekolah(SipkdHelpers.getIntFromString(idsekolah));
        pajak.setIdRequest(idrequest);
        pajak.setKodeJenisSetor(kjs);
        pajak.setKodeBilling(kodebilling);
        pajak.setTglBillExpString(tglexp);
        pajak.setAkunPajak(kodemap);
        pajak.setTahun(tahun);
        pajak.setBulkIdRequest(bulkidreq);
        pajak.setIdEntry(pengguna.getIdPengguna());

        bkuBosServices.insertRinciPotPajak(pajak);

        return "Data Rinci Potongan Pajak Berhasil Disimpan";
    }

    @RequestMapping(value = "/json/simpanpotrincibop", method = RequestMethod.POST)
    public @ResponseBody
    String simpanpotrincibop(@RequestBody Map<String, Object> mapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        final String idbku = (String) mapdata.get("idbku");
        final String bulkidreq = (String) mapdata.get("bulkidreq");
        final String idrequest = (String) mapdata.get("idrequest");
        final String kodebilling = (String) mapdata.get("kodebilling");
        final String tglexp = (String) mapdata.get("tglexp");
        final String kjs = (String) mapdata.get("kjs");
        final String kodemap = (String) mapdata.get("kodemap");
        final String idsekolah = (String) mapdata.get("idsekolah");
        final String tahun = (String) mapdata.get("tahun");

        PajakTransfer pajak = new PajakTransfer();

        pajak.setIdBku(SipkdHelpers.getIntFromString(idbku));
        pajak.setIdsekolah(SipkdHelpers.getIntFromString(idsekolah));
        pajak.setIdRequest(idrequest);
        pajak.setKodeJenisSetor(kjs);
        pajak.setKodeBilling(kodebilling);
        pajak.setTglBillExpString(tglexp);
        pajak.setAkunPajak(kodemap);
        pajak.setTahun(tahun);
        pajak.setBulkIdRequest(bulkidreq);
        pajak.setIdEntry(pengguna.getIdPengguna());

        bkuBopServices.insertRinciPotPajak(pajak);

        return "Data Rinci Potongan Pajak Berhasil Disimpan";
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }

}
