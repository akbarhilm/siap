/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.services;

import ebkus.entity.BkuBopMapper;
import ebkus.entity.GenerateIdMapper;
import ebkus.model.BkuRinci;
import ebkus.model.BukuKasUmum;
import ebkus.model.GenerateId;
import ebkus.model.Kegiatan;
import ebkus.model.Setor;
import ebkus.util.SipkdHelpers;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Zainab
 */
@Transactional(readOnly = true)
@Service("bkuBopServices")
public class BkuBopImpl implements BkuBopServices {

    private static final Logger log = LoggerFactory.getLogger(BkuBopImpl.class);
    @Autowired
    private BkuBopMapper bkuMapper;

    @Autowired
    private GenerateIdMapper genMapper;

    @Override
    public List<BukuKasUmum> getListIndex(final Map<String, Object> param) {
        if (param.get("tipe").equals("1")) {
            return bkuMapper.getListIndexSemua(param);
        } else if (param.get("tipe").equals("2")) {
            return bkuMapper.getListIndexTransfer(param);
        } else if (param.get("tipe").equals("3")) {
            return bkuMapper.getListIndexBelum(param);
        }
        return null;

    }

    @Override
    public Integer getBanyakListIndex(final Map<String, Object> param) {
        log.debug("TIPE2 " + param.get("tipe"));
        if (param.get("tipe").equals("1")) {
            return bkuMapper.getBanyakListIndexSemua(param);
        } else if (param.get("tipe").equals("2")) {
            return bkuMapper.getBanyakListIndexTransfer(param);
        } else if (param.get("tipe").equals("3")) {
            return bkuMapper.getBanyakListIndexBelum(param);
        }
        return null;
    }

    @Override
    public BukuKasUmum getNilaiIndex(final Map<String, Object> param) {
        return bkuMapper.getNilaiIndex(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertBkuJo(BukuKasUmum bku) {
        // create ID BKU MASTER
        GenerateId id1 = new GenerateId();
        id1.setNamaTabel("TMBKUSBOP");
        genMapper.insertGetId(id1);
        Integer idMaster = id1.getId();

        // create no bku mohon
        final Map genparam = new HashMap();
        genparam.put("tahun", bku.getTahun());
        genparam.put("idsekolah", bku.getIdsekolah());
        bku.setNoBkuMohon(bkuMapper.getBkuNoMohon(genparam));

        if ("JO".equals(bku.getKodeTransaksi()) || "ST".equals(bku.getKodeTransaksi())) {
            bku.setNoBKU(bkuMapper.getBkuNo(genparam));
        }

        if (("JG".equals(bku.getKodeTransaksi())) && "PN".equals(bku.getJenisPembayaran())) {
            bku.setNoBKU(bkuMapper.getBkuNo(genparam));
        }

        if (("RT".equals(bku.getKodeTransaksi())) && "PN".equals(bku.getJenisPembayaran())) {
            bku.setNoBKU(bkuMapper.getBkuNo(genparam));
        }

        bku.setId(idMaster);
        bkuMapper.insertBkuJoMaster(bku);

        // create ID BKU RINCI
        GenerateId id2 = new GenerateId();
        id2.setNamaTabel("TMBKUSBOPRINCI");
        genMapper.insertGetId(id2);
        Integer idRinci = id2.getId();

        // insert rinci
        BkuRinci rinci = new BkuRinci();
        rinci.setId(idRinci);
        rinci.setIdBku(idMaster);
        rinci.setNilaiMasuk(bku.getNilaiMasuk());
        rinci.setNilaiKeluar(bku.getNilaiKeluar());
        rinci.setIdEntry(bku.getIdEntry());
        rinci.setIdBas(bku.getIdBas());
        rinci.setKodeakun(bku.getKodeakun());
        bkuMapper.insertBkuJoRinci(rinci);
    }

    @Override
    public BukuKasUmum getBkuEditJo(final Map<String, Object> param) {
        return bkuMapper.getBkuEditJo(param);
    }

    @Override
    public BukuKasUmum getSaldoKasJO(final Map<String, Object> param) {
        return bkuMapper.getSaldoKasJO(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateBkuJo(BukuKasUmum bku) {
        bkuMapper.updateJoMasterById(bku);
        bkuMapper.updateJoRinciById(bku);

    }

    @Override
    @Transactional(readOnly = false)
    public void deleteBkuById(Map param) {
        bkuMapper.deleteRinciById(param);
        bkuMapper.deleteById(param);

        final String kodetrx = String.valueOf(param.get("kodeTransaksi"));
        if ("JJ".equals(kodetrx)) {
            bkuMapper.deleteParamPajak(param);
            bkuMapper.deleteRinciPajakSpj(param);
            bkuMapper.deletePajakSpj(param);
        }
    }

    @Override
    public List<BukuKasUmum> getKodeSetor(final Map<String, Object> param) {
        return bkuMapper.getKodeSetor(param);
    }

    @Override
    public List<Setor> getListSetoran(final Map<String, Object> param) {
        return bkuMapper.getListSetoran(param);
    }

    @Override
    public Integer getBanyakListSetoran(final Map<String, Object> param) {
        return bkuMapper.getBanyakListSetoran(param);
    }

    @Override
    public BukuKasUmum getSisaKas(final Map<String, Object> param) {
        return bkuMapper.getSisaKas(param);
    }

    @Override
    public List<BkuRinci> getListSpj(final Map<String, Object> param) {
        return bkuMapper.getListSpj(param);
    }

    @Override
    public Integer getBanyakListSpj(final Map<String, Object> param) {
        return bkuMapper.getBanyakListSpj(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertBkuSpj(BukuKasUmum bku) {
        // create ID BKU MASTER
        try {
            GenerateId id1 = new GenerateId();
            id1.setNamaTabel("TMBKUSBOP");
            genMapper.insertGetId(id1);
            Integer idMaster = id1.getId();

            // INSERT MASTER
            // create no bku mohon
            final Map genparam = new HashMap();
            genparam.put("tahun", bku.getTahun());
            genparam.put("idsekolah", bku.getIdsekolah());
            genparam.put("triwulan", bku.getTriwulan());
            int maxStatus = getMaxStatus(genparam);
            bku.setNoBkuMohon(bkuMapper.getBkuNoMohon(genparam));
            bku.setId(idMaster);
            log.debug("id bku " + bku.getId());
            bkuMapper.insertBkuSpjMaster(bku);

            //INSERT PARAM PAJAK
            GenerateId id2 = new GenerateId();
            id2.setNamaTabel("TMBKUSDJPPARAM");
            genMapper.insertGetId(id2);
            Integer idParam = id2.getId();
            bku.setIdParam(idParam);
            bkuMapper.insertParamPajak(bku);
            // INSERT RINCI
            final List<BkuRinci> list = bku.getListBkuRinci();
            // create ID BKU RINCI
            GenerateId id3 = new GenerateId();
            Integer idRinci;

            for (BkuRinci bkurinci : list) {
                id3.setNamaTabel("TMBKUSBOPRINCI");
                genMapper.insertGetId(id3);
                idRinci = id3.getId();

                bkurinci.setId(idRinci);
                bkurinci.setIdBku(idMaster);
                if (maxStatus == 0) {
                    bkurinci.setNilaiMohon(bkurinci.getNilaiKeluar());
                }
                bkuMapper.insertBkuSpjRinci(bkurinci);
            }
        } catch (Exception e) {
            log.info("ERROR LEVEL INFO - " + e.getMessage());
        }

    }

    @Override
    public BukuKasUmum getBkuEditSpj(final Map<String, Object> param) {
        return bkuMapper.getBkuEditSpj(param);
    }

    @Override
    public Kegiatan getDataKegiatan(final Map<String, Object> param) {
        return bkuMapper.getDataKegiatan(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateBkuSpj(BukuKasUmum bku) {

        // UPDATE MASTER
        bkuMapper.updateSpjMasterById(bku);

        Integer idMaster = bku.getIdBku();

        //UPDATE PARAM PAJAK
        bkuMapper.updateParamPajak(bku);

        // INSERT RINCI
        final List<BkuRinci> list = bku.getListBkuRinci();
        // create ID BKU RINCI
        GenerateId id2 = new GenerateId();
        Integer idRinci;

        for (BkuRinci bkurinci : list) {
            Integer idrinci = bkurinci.getIdBkuRinci();

            if (idrinci > 0) { // DATA LAMA
                bkuMapper.updateSpjRinciById(bkurinci);

            } else { // DATA BARU

                id2.setNamaTabel("TMBKUSBOPRINCI");
                genMapper.insertGetId(id2);
                idRinci = id2.getId();
                bkurinci.setIdBku(idMaster);
                bkurinci.setId(idRinci);

                bkuMapper.insertBkuSpjRinci(bkurinci);

            }
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void updateBkuSpjBukti(BukuKasUmum bku) {

        // UPDATE MASTER
        bkuMapper.updateSpjMasterByIdBukti(bku);

        //UPDATE PARAM PAJAK
        bkuMapper.updateParamPajak(bku);
    }

    @Override
    public List<BukuKasUmum> getListSpjRinci(final Map<String, Object> param) {
        return bkuMapper.getListSpjRinci(param);
    }

    @Override
    public Integer getBanyakListSpjRinci(final Map<String, Object> param) {
        return bkuMapper.getBanyakListSpjRinci(param);
    }

    @Override
    public List<BukuKasUmum> getListPajakPn(final Map<String, Object> param) {
        return bkuMapper.getListPajakPn(param);
    }

    @Override
    public Integer getBanyakListPajakPn(final Map<String, Object> param) {
        return bkuMapper.getBanyakListPajakPn(param);
    }

    @Override
    public List<BkuRinci> getListPajakSpj(final Map<String, Object> param) {
        return bkuMapper.getListPajakSpj(param);
    }

    @Override
    public Integer getBanyakListPajakSpj(final Map<String, Object> param) {
        return bkuMapper.getBanyakListPajakSpj(param);
    }

    @Override
    public List<BkuRinci> getListPajakPg(final Map<String, Object> param) {
        return bkuMapper.getListPajakPg(param);
    }

    @Override
    public Integer getBanyakListPajakPg(final Map<String, Object> param) {
        return bkuMapper.getBanyakListPajakPg(param);
    }

    @Override
    public BukuKasUmum getDataWP(final Map<String, Object> param) {
        return bkuMapper.getDataWP(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertBkuPajak(BukuKasUmum bku) {
        // create ID BKU MASTER
        GenerateId id1 = new GenerateId();
        id1.setNamaTabel("TMBKUSBOP");
        genMapper.insertGetId(id1);
        Integer idMaster = id1.getId();

        // INSERT MASTER
        // create no bku mohon
        final Map genparam = new HashMap();
        genparam.put("tahun", bku.getTahun());
        genparam.put("idsekolah", bku.getIdsekolah());
        bku.setNoBkuMohon(bkuMapper.getBkuNoMohon(genparam));

        // create no bku (untuk PN)
        if (("PN".equals(bku.getJenisPembayaran())) && ("1".equals(bku.getKodePajak()))) {
            bku.setNoBKU(bkuMapper.getBkuNo(genparam));
        }

        bku.setId(idMaster);
        bkuMapper.insertBkuPajakMaster(bku);

        // INSERT RINCI
        final List<BkuRinci> list = bku.getListBkuRinci();
        // create ID BKU RINCI
        GenerateId id2 = new GenerateId();
        Integer idRinci;

        for (BkuRinci bkurinci : list) {
            id2.setNamaTabel("TMBKUSBOPRINCI");
            genMapper.insertGetId(id2);
            idRinci = id2.getId();

            bkurinci.setId(idRinci);
            bkurinci.setIdBku(idMaster);
            bkuMapper.insertBkuPajakRinci(bkurinci);
            /*
             if (("PN".equals(bku.getJenisPembayaran())) && ("0".equals(bku.getKodePajak()))) {
             bkuMapper.updateSpjNetto(bkurinci);
             }
             */
        }
        // update data nilai pajak spj dan netto spj
        if (("PN".equals(bku.getJenisPembayaran())) && ("0".equals(bku.getKodePajak()))) {
            bkuMapper.procUpdateSpjPjNetto(bku);
        }

    }

    @Override
    public BukuKasUmum getBkuEditPajak(final Map<String, Object> param) {
        return bkuMapper.getBkuEditPajak(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateBkuPajak(BukuKasUmum bku) {

        // UPDATE MASTER
        bkuMapper.updatePajakMasterById(bku);

        Integer idMaster = bku.getIdBku();

        GenerateId id2 = new GenerateId();
        Integer idRinci;

        // UPDATE RINCI
        final List<BkuRinci> list = bku.getListBkuRinci();
        for (BkuRinci bkurinci : list) {
            Integer idrinci = bkurinci.getIdBkuRinci();

            if (idrinci > 0) {
                bkuMapper.updatePajakRinciByIdbkubop(bkurinci);
            } else {
                // create ID BKU RINCI
                id2.setNamaTabel("TMBKUSBOPRINCI");
                genMapper.insertGetId(id2);
                idRinci = id2.getId();

                bkurinci.setId(idRinci);
                bkurinci.setIdBku(idMaster);

                bkuMapper.insertBkuPajakRinci(bkurinci);

            }

            /*if (("PN".equals(bku.getJenisPembayaran())) && ("0".equals(bku.getKodePajak()))) {
             bkuMapper.updateSpjNettoEdit(bkurinci);
             }
             */
        }

        if (("PN".equals(bku.getJenisPembayaran())) && ("0".equals(bku.getKodePajak()))) {
            bkuMapper.procUpdateSpjPjNetto(bku);
        }
    }

    @Override
    public Integer getBanyakDataPjPn(final Map<String, Object> param) {
        return bkuMapper.getBanyakDataPjPn(param);
    }

    @Override
    public Integer getStatusSpj(final Map<String, Object> param) {
        return bkuMapper.getStatusSpj(param);
    }

    @Override
    public Integer getTriwulanByRekap(final Map<String, Object> param) {
        return bkuMapper.getTriwulanByRekap(param);
    }

    @Override
    public Integer getMaxTriwulan(final Map<String, Object> param) {
        return bkuMapper.getMaxTriwulan(param);
    }

    @Override
    public BukuKasUmum getPkBlj(final Map<String, Object> param) {
        return bkuMapper.getPkBlj(param);
    }

    @Override
    public List<BukuKasUmum> getListPnJg(final Map<String, Object> param) {
        return bkuMapper.getListPnJg(param);
    }

    @Override
    public Integer getBanyakListPnJg(final Map<String, Object> param) {
        return bkuMapper.getBanyakListPnJg(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertBkuC12(List<BukuKasUmum> param) {
        // create ID BKU MASTER
        BukuKasUmum bku1 = param.get(0);
        BukuKasUmum bku2 = param.get(1);
        GenerateId id1 = new GenerateId();
        id1.setNamaTabel("TMBKUSBOP");
        genMapper.insertGetId(id1);
        Integer idMaster = id1.getId();

        // create no bku mohon
        final Map genparam = new HashMap();
        genparam.put("tahun", bku1.getTahun());
        genparam.put("idsekolah", bku1.getIdsekolah());
        bku1.setNoBkuMohon(bkuMapper.getBkuNoMohon(genparam));

        bku1.setId(idMaster);
        bkuMapper.insertBkuC12Master(bku1);

        // create ID BKU RINCI
        GenerateId id2 = new GenerateId();
        GenerateId id3 = new GenerateId();
        id2.setNamaTabel("TMBKUSBOPRINCI");
        id3.setNamaTabel("TMBKUSBOPRINCI");
        genMapper.insertGetId(id2);
        genMapper.insertGetId(id3);
        Integer idRinci1 = id2.getId();
        Integer idRinci2 = id3.getId();

        // insert rinci
        BkuRinci rinci1 = new BkuRinci();
        BkuRinci rinci2 = new BkuRinci();

        rinci1.setId(idRinci1);
        rinci1.setIdBku(idMaster);
        rinci1.setNilaiMasuk(bku1.getNilaiMasuk());
        rinci1.setNilaiKeluar(bku1.getNilaiKeluar());
        rinci1.setIdEntry(bku1.getIdEntry());
        rinci1.setIdBas(bku1.getIdBas());
        rinci1.setKodeakun(bku1.getKodeakun());

        rinci2.setId(idRinci2);
        rinci2.setIdBku(idMaster);
        rinci2.setNilaiMasuk(bku2.getNilaiMasuk());
        rinci2.setNilaiKeluar(bku2.getNilaiKeluar());
        rinci2.setIdEntry(bku2.getIdEntry());
        rinci2.setIdBas(bku2.getIdBas());
        rinci2.setKodeakun(bku2.getKodeakun());

        bkuMapper.insertBkuC12Rinci(rinci1);
        bkuMapper.insertBkuC12Rinci(rinci2);
    }

    @Override
    public List<BukuKasUmum> getBkuEditC12(Map<String, Object> param) {
        return bkuMapper.getBkuEditC12(param);
    }

    @Override
    public void updateBkuC12(BukuKasUmum bku) {

        // UPDATE MASTER
        bkuMapper.updateC12MasterById(bku);

        Integer idMaster = bku.getIdBku();

        final List<BkuRinci> list = bkuMapper.getBkuEditC12Rinci(idMaster);
        BkuRinci rinci1 = list.get(0);
        BkuRinci rinci2 = list.get(1);
        rinci1.setIdEntry(bku.getIdEntry());
        rinci2.setIdEntry(bku.getIdEntry());
        if (bku.getKodeTransaksi().equals("C1")) {
            rinci1.setNilaiMasuk(bku.getNilaiMasuk());
            rinci2.setNilaiKeluar(bku.getNilaiKeluar());
            rinci2.setNilaiMasuk(SipkdHelpers.getBigDecimalFromString("0"));
            rinci1.setNilaiKeluar(SipkdHelpers.getBigDecimalFromString("0"));
        } else {
            rinci2.setNilaiMasuk(bku.getNilaiMasuk());
            rinci1.setNilaiKeluar(bku.getNilaiKeluar());
            rinci1.setNilaiMasuk(SipkdHelpers.getBigDecimalFromString("0"));
            rinci2.setNilaiKeluar(SipkdHelpers.getBigDecimalFromString("0"));
        }
        bkuMapper.updateC12RinciById(rinci1);
        bkuMapper.updateC12RinciById(rinci2);
    }

    @Override
    public Integer getMaxStatus(Map<String, Object> param) {
        return bkuMapper.getMaxStatus(param);
    }

    @Override
    public BukuKasUmum getSisaKasSpj(Map<String, Object> param) {
        return bkuMapper.getSisaKasSpj(param);
    }

    @Override
    public List<BkuRinci> getListSpjEdit(final Map<String, Object> param) {
        return bkuMapper.getListSpjEdit(param);
    }

    @Override
    public Integer getBanyakListSpjEdit(final Map<String, Object> param) {
        return bkuMapper.getBanyakListSpjEdit(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void deletePajakPn(BukuKasUmum bku) {

        final Map< String, Object> param = new HashMap<String, Object>(3);
        param.put("tahun", bku.getTahun());
        param.put("kodeTransaksi", bku.getKodeTransaksi());
        param.put("idbku", bku.getIdBku());

        bkuMapper.deleteRinciById(param);
        bkuMapper.deleteById(param);

        if (("PN".equals(bku.getJenisPembayaran())) && ("0".equals(bku.getKodePajak()))) {
            bkuMapper.procUpdateSpjPjNetto(bku);

            // UPDATE RINCI
            /*final List<BkuRinci> list = bku.getListBkuRinci();
             for (BkuRinci bkurinci : list) {
             bkuMapper.updateSpjNettoEdit(bkurinci);
             }*/
        }
    }

    @Override
    public BukuKasUmum getDataParam(Map param) {
        return bkuMapper.getDataParam(param);
    }

    @Override
    public Map validateP2P3(Map param) {
        return bkuMapper.validateP2P3(param);
    }

    @Override
    public String getWpPjPg(Map param) {
        return bkuMapper.getWpPjPg(param);
    }
}
