package ebkus.ui.action;

import ebkus.config.DefaultTrustManager;
import ebkus.util.SipkdHelpers;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.catalina.util.Base64;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author hari
 */
@Controller
@RequestMapping("/postnorek")
public class PostNorekAction {

    private static final Logger log = LoggerFactory.getLogger(PostNorekAction.class);

    @Autowired
    ServletContext servletContext;

    private final RestTemplate rest;
    private final HttpHeaders headers;
    private HttpStatus status;

    public PostNorekAction() {
        this.rest = new RestTemplate();
        this.headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "*/*");
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "index";

    }

    @RequestMapping(value = "/json/ceknorek", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    Map<String, String> ceknorek(@RequestBody Map<String, String> mapdata, final HttpServletRequest request) throws IOException {
        try {
            InputStream inputStreamOnUs = servletContext.getResourceAsStream("/WEB-INF/txt/norek.txt");
            InputStream inputStreamOffUs = servletContext.getResourceAsStream("/WEB-INF/txt/norekoffus.txt");
            String on = SipkdHelpers.readFromInputStream(inputStreamOnUs);
            String off = SipkdHelpers.readFromInputStream(inputStreamOffUs);
            SSLContext sslctx = SSLContext.getInstance("SSL");
            sslctx.init(null, new X509TrustManager[]{new DefaultTrustManager()
            }, null);
            HttpsURLConnection.setDefaultSSLSocketFactory(sslctx.getSocketFactory()
            );
            JSONObject data = new JSONObject();
            final String norek = mapdata.get("norek");
            final String kodebank = mapdata.get("kodebank");
            String url = null, username = null, password = null;
            if (kodebank.equals("111")) {
                data.put("kodebank", kodebank);
                data.put("norek", norek);
                url = on.split("\\|")[0];
                username = on.split("\\|")[1];
                password = on.split("\\|")[2];
            } else {
                data.put("code", kodebank);
                data.put("account", norek);
                url = off.split("\\|")[0];
                username = off.split("\\|")[1];
                password = off.split("\\|")[2];
            }

            HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("Authorization", "Basic " + Base64.encode((username + ":" + password).getBytes()));
            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

            OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
            wr.write(data.toString());
            wr.flush();
            wr.close();

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
            Map<String, String> outMap = new HashMap<String, String>();
            while (nameItr.hasNext()) {
                String name = nameItr.next();
                outMap.put(name, json.getString(name));

            }
            reader.close();
            con.disconnect();
            return outMap;
        } catch (Exception ex) {
            log.debug("ERROR - " + ex.getMessage());
        }
        return null;

        //LOCAL
//        HttpEntity<Map<String, Object>> requestPostData = new HttpEntity<Map<String, Object>>(mapdata);
//
//        InputStream inputStream = servletContext.getResourceAsStream("/WEB-INF/txt/norek.txt");
//        String url = SipkdHelpers.readFromInputStream(inputStream);
//
//        ResponseEntity<Map> response = rest.exchange(url, HttpMethod.POST, requestPostData, Map.class);
//
//        log.debug("KIRIM POST DATA -- response.getBody() ------------> " + response.getBody());
//
//        return response.getBody();
    }

}
