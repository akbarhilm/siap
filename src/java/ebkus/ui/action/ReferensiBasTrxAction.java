package ebkus.ui.action;

import ebkus.services.RefBasTrxServices;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;







@Controller
@RequestMapping("/bku")
public class ReferensiBasTrxAction
{
  private static final Logger log = LoggerFactory.getLogger(ReferensiBasTrxAction.class);
    @Autowired
  ServletContext servletContext;
  @Autowired
  DataSource dataSource;
  @Autowired
  RefBasTrxServices refBasTrxServices;
  
  
  
  @RequestMapping(value="/indexcekbastrx", method = RequestMethod.GET)
  public String index(Principal principal, HttpServletRequest req)
  {
    return "konversi/bastrx";
  }
  
  @RequestMapping(value="/json/listbastrx", method=RequestMethod.GET)
  @ResponseBody
  public Map<String, Object> listbastrx(HttpServletRequest request) {
    Map<String, Object> param = new HashMap(6);
   
    
    String tahun = request.getParameter("tahun");
    String ketfilter = request.getParameter("ketfilter");
    String koderekfilter = request.getParameter("koderekfilter");
    param.put("tahun", tahun);
    param.put("ketfilter", ketfilter);
    param.put("koderekfilter", koderekfilter);
    
   
    Map<String, Object> mapData = new HashMap(4);
    int banyak = refBasTrxServices.getBanyakListBasTrx(param);
    mapData.put("sEcho", request.getParameter("sEcho"));
    mapData.put("iTotalRecords", banyak);
    mapData.put("iTotalDisplayRecords", banyak);
    mapData.put("aaData", refBasTrxServices.getListBasTrx(param));
    return mapData;
  }
  

}
