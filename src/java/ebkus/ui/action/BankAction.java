package ebkus.ui.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ebkus.model.Kegiatan;
import ebkus.services.BankServices;
import ebkus.util.SipkdHelpers;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Zainab
 */
@Controller
@RequestMapping("/bank")
public class BankAction {

    private static final Logger log = LoggerFactory.getLogger(BankAction.class);

    @Autowired
    BankServices bankServices;
    
    
    @RequestMapping(value = "/listbank", method = RequestMethod.GET)
    public ModelAndView listakunbukubesar(final Kegiatan kegiatan, final HttpServletRequest request, final HttpServletResponse response) {
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        return new ModelAndView("bank/listbank", "refkegiatan", kegiatan);
    }
    
    @RequestMapping(value = "/json/listbankpfk", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> listbankpfk(final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>(6);
        final Integer offset = request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")) : 0;
        final Integer limit = request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")) : 0;
        final Integer iSortCol_0 = request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")) : 0;
        final String sSortDir_0 = request.getParameter("sSortDir_0");
        
        final String tahunAnggaran = (String) request.getSession().getAttribute("tahunAnggaran");
        final String kode = request.getParameter("kode");
        final String nama = request.getParameter("nama");
        
        param.put("tahun", tahunAnggaran);
        param.put("kode", kode);
        param.put("nama", nama);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        final long banyak = bankServices.getBanyakListBank(param);
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);
        mapData.put("aaData", bankServices.getListBank(param));
        return mapData;
    }

}
