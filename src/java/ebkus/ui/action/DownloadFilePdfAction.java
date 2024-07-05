package ebkus.ui.action;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import sp2d.services.RekonBankDkiServices;
import ebkus.util.SipkdHelpers;
import ebkus.util.SqlDatePropertyEditor;

/**
 *
 * @author Zainab
 */
@Controller
@RequestMapping("/downloadfile")
public class DownloadFilePdfAction {

    private static final Logger log = LoggerFactory.getLogger(DownloadFilePdfAction.class);

    @Autowired
    ServletContext servletContext;
    @Autowired
    DataSource dataSource;

    @RequestMapping(value = "/indexdownload", method = RequestMethod.GET)
    public String indexdownload(final HttpServletRequest request) {
        return "downloadfile/indexdownload";
    }

    @RequestMapping(value = "/json/getfilepdf", method = RequestMethod.GET)
    public void getfilepdf(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String pathReport = servletContext.getInitParameter("PATH_PDF");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        try {
            response.setContentType("APPLICATION/OCTET-STREAM");
            final String filename = "File.pdf";
            response.setHeader("Content-disposition", "attachment; filename=" + filename);
            response.setContentType("application/pdf");
            FileInputStream fileInputStream = new FileInputStream("D:/[[OFFICE_PROJECT]]/SIPKD/outputreport/pdf/2016/SP2D/0_Balai_Kota/2016-SP2D-BL-LS-0-271-1.03.013.pdf");
            int i;
            while ((i = fileInputStream.read()) != -1) {
                out.write(i);
            }
            fileInputStream.close();
            out.close();
                    
        } catch (Exception e) {
            //e.printStackTrace();
            e.getMessage();
        }
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        //webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }

}
