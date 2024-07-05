/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Admin
 */
public class SipkdPref {

    public String getPrinter(final String user, final String basePath) throws IOException {
        final File file = new File(basePath + user + "-print.json");
        if (file.exists()) {
            final ObjectMapper mapper = new ObjectMapper();
            final Map<String, String> dataprinter = mapper.readValue(file, Map.class);
            return dataprinter.get("printer");
        } else {
            return null;
        }

    }

    public void setPrinter(final String user, final String basePath, final String namaPrinter) throws IOException {
        final File file = new File(basePath + user + "-print.json");
        final ObjectMapper mapper = new ObjectMapper();
        final Map<String, String> mapData = new HashMap<String, String>();
        mapData.put("printer", namaPrinter);
        mapper.writeValue(file, mapData);

    }
}
