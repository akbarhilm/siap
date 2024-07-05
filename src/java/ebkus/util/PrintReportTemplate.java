/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ebkus.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author M.Mustakim
 */
public class PrintReportTemplate {
    
    @Autowired
    DataSource dataSource;
    
    public void printReportToPdfStream(HttpServletResponse response, Map<String, Object> parameter) throws SQLException{
        Connection con = null;
        String filename = parameter.get("filename").toString();
        String pathReport = parameter.get("pathreport").toString();
        try{
            con = dataSource.getConnection();
            JasperPrint jasperPrint = JasperFillManager.fillReport(pathReport, parameter, con);
            response.setHeader("Content-disposition", "attachment; filename=" + filename);
            ServletOutputStream output = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, output);
            output.close();
        }
        catch (Exception e) {
            e.getMessage();
        }
        finally {
            if (con != null) con.close();
        }
    };
    
}
