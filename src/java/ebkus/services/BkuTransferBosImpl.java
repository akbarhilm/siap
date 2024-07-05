/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.services;

import ebkus.entity.BkuBosMapper;
import ebkus.entity.BkuTransferBosMapper;
import ebkus.entity.GenerateIdMapper;
import ebkus.model.BkuRinci;
import ebkus.model.BkuTransfer;
import ebkus.model.BukuKasUmum;
import ebkus.model.GenerateId;
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
@Service("bkuTfBosServices")
public class BkuTransferBosImpl implements BkuTransferBosServices {

    private static final Logger log = LoggerFactory.getLogger(BkuTransferBosImpl.class);
    @Autowired
    private BkuTransferBosMapper bkuMapper;

    @Autowired
    private BkuBosMapper bkuBosMapper;

    @Autowired
    private GenerateIdMapper genMapper;

    @Override
    public List<BkuTransfer> getListTransfer(final Map<String, Object> param) {
        return bkuMapper.getListTransfer(param);
    }

    @Override
    public Integer getBanyakListTransfer(final Map<String, Object> param) {
        return bkuMapper.getBanyakListTransfer(param);
    }

    @Override
    public BukuKasUmum getDataTf(final Map<String, Object> param) {
        return bkuMapper.getDataTf(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateBkuById(BukuKasUmum bku) {
        // create no bku
        final Map genparam = new HashMap();
        genparam.put("tahun", bku.getTahun());
        genparam.put("idsekolah", bku.getIdsekolah());
        bku.setNoBKU(bkuBosMapper.getBkuNo(genparam));
        BkuTransfer bkuTransfer = new BkuTransfer();
        bkuTransfer.setIdsekolah(bku.getIdsekolah());
        bkuTransfer.setBit11("-");
        bkuTransfer.setIdBku(bku.getIdBku());
        bkuTransfer.setTahun(bku.getTahun());
        bkuTransfer.setKodeSumbdana("1");
        bkuTransfer.setIdEntry(bku.getIdEntry());
        try {
            bkuMapper.updateBkuById(bku);
        } catch (Exception e) {
            //log.error("BKU TRANSFER =============== "+e.getMessage());
            e.printStackTrace();
            bkuTransfer.setPesanError(e.getMessage().toString());
            bkuTransfer.setKodeAction("UPD - BOS");
            GenerateId id1 = new GenerateId();
            id1.setNamaTabel("TMBKUSBANKERROR");
            genMapper.insertGetId(id1);
            Integer idMaster = id1.getId();
            bkuTransfer.setId(idMaster);

            bkuMapper.insertBkuBankError(bkuTransfer);
        }
        if ((bku.getKodeTransaksi().equals("JJ")) && (bku.getBanyakPajak() > 0)) {
            final List<BkuRinci> list = bku.getListBkuRinci();

            for (BkuRinci bkurinci : list) {
                bku.setNoBKU(bkuBosMapper.getBkuNo(genparam));
                bku.setKodeTransaksi(bkurinci.getKodeTransaksi());
                bku.setIdBku(bkurinci.getIdBku());

                bkuTransfer = new BkuTransfer();
                bkuTransfer.setIdsekolah(bku.getIdsekolah());
                bkuTransfer.setBit11(null);
                bkuTransfer.setIdBku(bku.getIdBku());
                bkuTransfer.setTahun(bku.getTahun());
                bkuTransfer.setKodeSumbdana("2");
                try {
                    bkuMapper.updatePajakPnById(bku);
                } catch (Exception e) {
                    //log.error("BKU TRANSFER =============== "+e.getMessage());
                    e.printStackTrace();
                    GenerateId id2 = new GenerateId();
                    id2.setNamaTabel("TMBKUSBANKERROR");
                    genMapper.insertGetId(id2);
                    Integer idMaster = id2.getId();
                    bkuTransfer.setId(idMaster);

                    bkuTransfer.setPesanError(e.getMessage().toString());
                    bkuTransfer.setKodeAction("UPD - BOP");
                    bkuMapper.insertBkuBankError(bkuTransfer);
                }
            }
        }
    }

    @Override
    public List<BkuRinci> getListBkuRinci(final Map<String, Object> param) {
        return bkuMapper.getListBkuRinci(param);
    }

    @Override
    public Integer getBanyakListBkuRinci(final Map<String, Object> param) {
        return bkuMapper.getBanyakListBkuRinci(param);
    }

    @Override
    public BkuTransfer getDataBankTujuan(final Map<String, Object> param) {
        return bkuMapper.getDataBankTujuan(param);
    }

    @Override
    public BkuTransfer getDataBankBOS(final Map<String, Object> param) {
        return bkuMapper.getDataBankBOS(param);
    }

    @Override
    public BkuTransfer getDataNpwp(final Map<String, Object> param) {
        return bkuMapper.getDataNpwp(param);
    }

    @Override
    public BukuKasUmum getSaldoKas(final Map<String, Object> param) {
        return bkuMapper.getSaldoKas(param);
    }

    @Override
    public List<BkuRinci> getListPajak(final Map<String, Object> param) {
        return bkuMapper.getListPajak(param);
    }

    @Override
    public Integer getBanyakListPajak(final Map<String, Object> param) {
        return bkuMapper.getBanyakListPajak(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertBkuPajak(BukuKasUmum bku) {
        // delete master dan rinci pajak
        bkuMapper.deletePajakRinci(bku);
        bkuMapper.deletePajakMaster(bku);

        // UPDATE NOL NILAI SPJ PAJAK DAN SPJ NETTO
        final Map jjparam = new HashMap();
        jjparam.put("idspj", bku.getIdTransaksi());
        jjparam.put("idblrinci", bku.getIdBl());
        bkuMapper.updateNilaiSpjNol(jjparam); // nol kan dl nilai pajak dan nett

        // RINCI PASTI SATU ROW
        final List<BkuRinci> list = bku.getListBkuRinci();
        for (BkuRinci bkurinci : list) { // JUMLAH LIST MENUNJUKAN JUMLAH TRANSAKSI PAJAK (1 JENIS PAJAK UNTUK 1 NO BKU MOHON)
            // create ID BKU MASTER
            GenerateId id1 = new GenerateId();
            id1.setNamaTabel("TMBKUSBOS");
            genMapper.insertGetId(id1);
            Integer idMaster = id1.getId();
            bku.setId(idMaster);

            // INSERT MASTER
            // create no bku dan no bku mohon
            final Map genparam = new HashMap();
            genparam.put("tahun", bku.getTahun());
            genparam.put("idsekolah", bku.getIdsekolah());
            bku.setNoBkuMohon(bkuMapper.getBkuNoMohon(genparam));
            bku.setNoBKU(bkuMapper.getBkuNo(genparam));
            bku.setKodeTransaksi(bkurinci.getKeterangan());
            bku.setUraian(bkurinci.getUraian());
            bku.setUraianBukti(bkurinci.getUraian());

            bkuMapper.insertBkuPajakMaster(bku);

            // create ID BKU RINCI
            GenerateId id2 = new GenerateId();
            Integer idRinci;
            id2.setNamaTabel("TMBKUSBOSRINCI");
            genMapper.insertGetId(id2);
            idRinci = id2.getId();

            bkurinci.setId(idRinci);
            bkurinci.setIdBku(idMaster);
            bkuMapper.insertBkuPajakRinci(bkurinci);

            // UPDATE NILAI SPJ PAJAK DAN SPJ NETTO
            final Map spjparam = new HashMap();
            spjparam.put("idspj", bku.getIdTransaksi());
            spjparam.put("idblrinci", bkurinci.getIdBlRinci());
            spjparam.put("nilaipajak", bkurinci.getNilaiMasuk());
            bkuMapper.updateNilaiSpj(spjparam);

        }

    }

    @Override
    public BukuKasUmum getDataPajakSpj(final Map<String, Object> param) {
        return bkuMapper.getDataPajakSpj(param);
    }

    @Override
    public BukuKasUmum getNilaiSpjNetto(final Map<String, Object> param) {
        return bkuMapper.getNilaiSpjNetto(param);
    }

    @Override
    public BkuTransfer getKodeStan(final Map param) {
        return bkuMapper.getKodeStan(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertBkuBank(BkuTransfer bku) {
        GenerateId id1 = new GenerateId();
        id1.setNamaTabel("TMBKUSBANK");
        genMapper.insertGetId(id1);
        Integer idMaster = id1.getId();
        bku.setId(idMaster);

        bkuMapper.insertBkuBank(bku);

    }

    @Override
    @Transactional(readOnly = false)
    public void updateBkuBank(BkuTransfer bku) {

        bkuMapper.updateBkuBank(bku);

    }

    @Override
    public List<BukuKasUmum> getListPajakPnBos(final Map<String, Object> param) {
        return bkuMapper.getListPajakPnBos(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertBkuBankError(BkuTransfer bku) {
        GenerateId id1 = new GenerateId();
        id1.setNamaTabel("TMBKUSBANKERROR");
        genMapper.insertGetId(id1);
        Integer idMaster = id1.getId();
        bku.setId(idMaster);

        bkuMapper.insertBkuBankError(bku);

    }

}
