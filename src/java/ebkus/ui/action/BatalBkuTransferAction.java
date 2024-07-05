package ebkus.ui.action;

import ebkus.model.BukuKasUmum;
import ebkus.model.Pengguna;
import ebkus.services.BatalTransferBopServices;
import ebkus.services.BatalTransferBosServices;
import ebkus.util.BigDecimalPropertyEditor;
import ebkus.util.SqlDatePropertyEditor;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/btltf"})
public class BatalBkuTransferAction {

    private static final Logger log = LoggerFactory.getLogger(BatalBkuTransferAction.class);

    @Autowired
    ServletContext servletContext;
    @Autowired
    BatalTransferBopServices bkuBopServices;
    @Autowired
    BatalTransferBosServices bkuBosServices;
    private final RestTemplate rest;
    private final HttpHeaders headers;
    private HttpStatus status;

    public BatalBkuTransferAction() {
        rest = new RestTemplate();
        headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "*/*");
    }

    @RequestMapping(value = "/batalbop", method = RequestMethod.GET)
    public ModelAndView batalbop(final BukuKasUmum bku, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        return new ModelAndView("transfer/bataltransferbop", "refbku", bku);
    }

    @RequestMapping(value = "/batalbos", method = RequestMethod.GET)
    public ModelAndView batalbos(final BukuKasUmum bku, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");

        return new ModelAndView("transfer/bataltransferbos", "refbku", bku);
    }

    @RequestMapping(value = {"/json/listpembatalan"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    public Map<String, Object> listpembatalan(HttpServletRequest request) {
        Map<String, Object> param = new HashMap(6);
        Integer offset = Integer.valueOf(request.getParameter("iDisplayStart") != null ? Integer.valueOf(request.getParameter("iDisplayStart")).intValue() : 0);
        Integer limit = Integer.valueOf(request.getParameter("iDisplayLength") != null ? Integer.valueOf(request.getParameter("iDisplayLength")).intValue() : 0);
        Integer iSortCol_0 = Integer.valueOf(request.getParameter("iSortCol_0") != null ? Integer.valueOf(request.getParameter("iSortCol_0")).intValue() : 0);
        String sSortDir_0 = request.getParameter("sSortDir_0");

        String idsekolah = request.getParameter("idSekolah");
        String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        String triwulan = request.getParameter("triwulan");
        String kodeotor = request.getParameter("kodeotor");
        String kodesumberdana = request.getParameter("kodesumberdana");
        String kodetransaksi = request.getParameter("kodetransaksi");
        String kodeKegiatanFilter = request.getParameter("kodeKegiatanFilter");
        String namaKegiatanFilter = request.getParameter("namaKegiatanFilter");

        param.put("idsekolah", idsekolah);
        param.put("tahun", tahun);
        param.put("triwulan", triwulan);
        param.put("kodeotor", kodeotor);
        param.put("kodetransaksi", ((kodetransaksi == null) ? "-" : kodetransaksi));
        log.debug("KODE OTOR " + kodeotor);
        if (kodeotor.equals("2")) {
            param.put("dari", '0');
            param.put("ke", '1');
        } else if (kodeotor.equals("1")) {
            param.put("dari", '1');
            param.put("ke", '2');
        } else if (kodeotor.equals("0")) {
            param.put("dari", '2');
            param.put("ke", '3');
        }
        param.put("kodeKegiatanFilter", kodeKegiatanFilter);
        param.put("namaKegiatanFilter", namaKegiatanFilter);
        param.put("offset", offset);
        param.put("limit", limit);
        param.put("iSortCol_0", iSortCol_0);
        param.put("sSortDir_0", sSortDir_0);
        final Map<String, Object> mapData = new HashMap<String, Object>(4);
        long banyak = 0;
        if (kodesumberdana.equals("1")) {
            banyak = bkuBosServices.getBanyakListPembatalan(param);
            mapData.put("aaData", bkuBosServices.getListPembatalan(param));
        } else {
            banyak = bkuBopServices.getBanyakListPembatalan(param);
            mapData.put("aaData", bkuBopServices.getListPembatalan(param));
        }
        mapData.put("sEcho", request.getParameter("sEcho"));
        mapData.put("iTotalRecords", banyak);
        mapData.put("iTotalDisplayRecords", banyak);

        return mapData;
    }

    @RequestMapping(value = "/json/getJumlah", method = RequestMethod.GET)
    public @ResponseBody
    Integer getJumlah(final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>(3);
        final String idsekolah = request.getParameter("idSekolah");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        final String triwulan = request.getParameter("triwulan");
        final String kodeotor = request.getParameter("kodeotor");
        final String kodesumberdana = request.getParameter("kodesumberdana");
        final String kodetransaksi = request.getParameter("kodetransaksi");
        final String kodeKegiatanFilter = request.getParameter("kodeKegiatanFilter");
        final String namaKegiatanFilter = request.getParameter("namaKegiatanFilter");

        param.put("idsekolah", idsekolah);
        param.put("tahun", tahun);
        param.put("triwulan", triwulan);
        param.put("kodeotor", kodeotor);
        param.put("kodetransaksi", ((kodetransaksi == null) ? "-" : kodetransaksi));
        if (kodeotor.equals("2")) {
            param.put("dari", '0');
            param.put("ke", '1');
        } else if (kodeotor.equals("1")) {
            param.put("dari", '1');
            param.put("ke", '2');
        } else if (kodeotor.equals("0")) {
            param.put("dari", '2');
            param.put("ke", '3');
        }
        param.put("kodeKegiatanFilter", kodeKegiatanFilter);
        param.put("namaKegiatanFilter", namaKegiatanFilter);

        if (kodesumberdana.equals("1")) {
            return bkuBosServices.getBanyakListPembatalan(param);
        } else if (kodesumberdana.equals("2")) {
            return bkuBopServices.getBanyakListPembatalan(param);
        }
        return null;
    }

    @RequestMapping(value = "/json/getTotal", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getTotal(final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>();
        final String idsekolah = request.getParameter("idSekolah");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        final String triwulan = request.getParameter("triwulan");
        final String kodeotor = request.getParameter("kodeotor");
        final String kodesumberdana = request.getParameter("kodesumberdana");
        final String kodetransaksi = request.getParameter("kodetransaksi");
        final String kodeKegiatanFilter = request.getParameter("kodeKegiatanFilter");
        final String namaKegiatanFilter = request.getParameter("namaKegiatanFilter");

        param.put("idsekolah", idsekolah);
        param.put("tahun", tahun);
        param.put("triwulan", triwulan);
        param.put("kodeotor", kodeotor);
        param.put("kodetransaksi", ((kodetransaksi == null) ? "-" : kodetransaksi));
        if (kodeotor.equals("2")) {
            param.put("dari", '0');
            param.put("ke", '1');
        } else if (kodeotor.equals("1")) {
            param.put("dari", '1');
            param.put("ke", '2');
        } else if (kodeotor.equals("0")) {
            param.put("dari", '2');
            param.put("ke", '3');
        }
        param.put("kodeKegiatanFilter", kodeKegiatanFilter);
        param.put("namaKegiatanFilter", namaKegiatanFilter);
        final Map<String, Object> mapData = new HashMap<String, Object>();
        if (kodesumberdana.equals("1")) {
            mapData.put("aData", bkuBosServices.getTotalNilai(param));
        } else if (kodesumberdana.equals("2")) {
            mapData.put("aData", bkuBopServices.getTotalNilai(param));
        }
        return mapData;
    }

    @RequestMapping(value = "/json/getPajakPengeluaran", method = RequestMethod.GET)
    public @ResponseBody
    Integer getPajakPengeluaran(final HttpServletRequest request) {
        final Map< String, Object> param = new HashMap<String, Object>(3);
        final String idsekolah = request.getParameter("idSekolah");
        final String tahun = (String) request.getSession().getAttribute("tahunAnggaran");
        final String triwulan = request.getParameter("triwulan");
        final String kodesumberdana = request.getParameter("kodesumberdana");
        final String noBkuMohon = request.getParameter("noBkuMohon");

        param.put("idsekolah", idsekolah);
        param.put("tahun", tahun);
        param.put("triwulan", triwulan);
        param.put("noBkuMohon", noBkuMohon);

        if (kodesumberdana.equals("1")) {
            return bkuBosServices.getPajakPengeluaran(param);
        } else if (kodesumberdana.equals("2")) {
            return bkuBopServices.getPajakPengeluaran(param);
        }
        return null;
    }

    @RequestMapping(value = "/json/prosesbatal", method = RequestMethod.POST)
    public @ResponseBody
    void prosesbatal(@RequestBody List<Map<String, String>> listmapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final Map< String, Object> param = new HashMap<String, Object>();
        for (Map<String, String> mapdata : listmapdata) {
            param.put("tahun", request.getSession().getAttribute("tahunAnggaran"));
            param.put("idsekolah", mapdata.get("idSekolah"));
            param.put("triwulan", mapdata.get("triwulan"));
            param.put("kodeotor", mapdata.get("kodeotor"));
            param.put("kodetransaksi", mapdata.get("kodetransaksi"));
            if (mapdata.get("kodeotor").equals("2")) {
                if (mapdata.get("batal").equals("1")) {
                    param.put("dari", '0');
                    param.put("ke", '1');
                } else if (mapdata.get("batal").equals("0")) {
                    param.put("dari", '1');
                    param.put("ke", '0');
                }
            } else if (mapdata.get("kodeotor").equals("1")) {
                if (mapdata.get("batal").equals("1")) {
                    param.put("dari", '1');
                    param.put("ke", '2');
                } else if (mapdata.get("batal").equals("0")) {
                    param.put("dari", '2');
                    param.put("ke", '1');
                }
            } else if (mapdata.get("kodeotor").equals("0")) {
                if (mapdata.get("batal").equals("1")) {
                    param.put("dari", '2');
                    param.put("ke", '3');
                } else if (mapdata.get("batal").equals("0")) {
                    param.put("dari", '3');
                    param.put("ke", '2');
                }
            }
            param.put("noBkuMohon", mapdata.get("noBkuMohon"));
            param.put("id", pengguna.getIdPengguna());
            param.put("tanggal", new Timestamp(System.currentTimeMillis()));
            if (mapdata.get("kodesumberdana").equals("1")) {
                if (mapdata.get("kodeotor").equals("1")) {
                    bkuBosServices.prosesPembatalan(param);
                }
                bkuBosServices.pengajuanBatal(param);
            } else if (mapdata.get("kodesumberdana").equals("2")) {
                if (mapdata.get("kodeotor").equals("1")) {
                    bkuBopServices.prosesPembatalan(param);
                }
                bkuBopServices.pengajuanBatal(param);
            }
        }
    }

    @RequestMapping(value = "/json/prosesbatalpajak", method = RequestMethod.POST)
    public @ResponseBody
    void prosesbatalpajak(@RequestBody List<Map<String, String>> listmapdata, final HttpServletRequest request) {
        final Pengguna pengguna = (Pengguna) request.getSession().getAttribute("pengguna");
        final Map< String, Object> param = new HashMap<String, Object>();
        for (Map<String, String> mapdata : listmapdata) {
            param.put("tahun", request.getSession().getAttribute("tahunAnggaran"));
            param.put("idsekolah", mapdata.get("idSekolah"));
            param.put("triwulan", mapdata.get("triwulan"));
            param.put("kodeotor", mapdata.get("kodeotor"));
            if (mapdata.get("kodeotor").equals("2")) {
                if (mapdata.get("batal").equals("1")) {
                    param.put("dari", '0');
                    param.put("ke", '1');
                } else if (mapdata.get("batal").equals("0")) {
                    param.put("dari", '1');
                    param.put("ke", '0');
                }
            } else if (mapdata.get("kodeotor").equals("1")) {
                if (mapdata.get("batal").equals("1")) {
                    param.put("dari", '1');
                    param.put("ke", '2');
                } else if (mapdata.get("batal").equals("0")) {
                    param.put("dari", '2');
                    param.put("ke", '1');
                }
            } else if (mapdata.get("kodeotor").equals("0")) {
                if (mapdata.get("batal").equals("1")) {
                    param.put("dari", '2');
                    param.put("ke", '3');
                } else if (mapdata.get("batal").equals("0")) {
                    param.put("dari", '3');
                    param.put("ke", '2');
                }
            }
            param.put("noBkuMohon", mapdata.get("noBkuMohon"));
            param.put("pajakPengeluaran", "uyeh");
            param.put("id", pengguna.getIdPengguna());
            param.put("tanggal", new Timestamp(System.currentTimeMillis()));
            log.debug("INI NO MOHON YG DIBATALKAN = " + mapdata.get("noBkuMohon"));
            if (mapdata.get("kodesumberdana").equals("1")) {
                if (mapdata.get("kodeotor").equals("1")) {
                    bkuBosServices.prosesPembatalan(param);
                }
                bkuBosServices.pengajuanBatal(param);
            } else if (mapdata.get("kodesumberdana").equals("2")) {
                if (mapdata.get("kodeotor").equals("1")) {
                    bkuBopServices.prosesPembatalan(param);
                }
                bkuBopServices.pengajuanBatal(param);
            }
        }
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new SqlDatePropertyEditor());
        webDataBinder.registerCustomEditor(BigDecimal.class, new BigDecimalPropertyEditor());
    }
}
