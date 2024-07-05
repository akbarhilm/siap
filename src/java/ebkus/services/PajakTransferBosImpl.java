/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.services;

import ebkus.entity.GenerateIdMapper;
import ebkus.entity.PajakTransferBosMapper;
import ebkus.model.BkuRinci;
import ebkus.model.BkuTransfer;
import ebkus.model.BukuKasUmum;
import ebkus.model.GenerateId;
import ebkus.model.PajakTransfer;
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
@Service("pajakTfBosServices")
public class PajakTransferBosImpl implements PajakTransferBosServices {

    private static final Logger log = LoggerFactory.getLogger(PajakTransferBosImpl.class);

    @Autowired
    private PajakTransferBosMapper pajakMapper;

    @Autowired
    private GenerateIdMapper genMapper;

    @Override
    public PajakTransfer getDataPajak(final Map<String, Object> param) {
        return pajakMapper.getDataPajak(param);
    }

    @Override
    public List<BkuRinci> getListBkuRinci(final Map<String, Object> param) {
        return pajakMapper.getListBkuRinci(param);
    }

    @Override
    public Integer getBanyakListBkuRinci(final Map<String, Object> param) {
        return pajakMapper.getBanyakListBkuRinci(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateBkuById(BukuKasUmum bku) {
        // create no bku
        final Map genparam = new HashMap();
        genparam.put("tahun", bku.getTahun());
        genparam.put("idsekolah", bku.getIdsekolah());
        bku.setNoBKU(pajakMapper.getBkuNo(genparam));
        BkuTransfer bkuTransfer = new BkuTransfer();
        bkuTransfer.setIdsekolah(bku.getIdsekolah());
        bkuTransfer.setBit11("-");
        bkuTransfer.setIdBku(bku.getIdBku());
        bkuTransfer.setTahun(bku.getTahun());
        bkuTransfer.setKodeSumbdana("1");
        bkuTransfer.setIdEntry(bku.getIdEntry());
        try {
            pajakMapper.updateBkuById(bku);
        } catch (Exception e) {
            //log.error("BKU TRANSFER =============== "+e.getMessage());
            e.printStackTrace();
            bkuTransfer.setPesanError(e.getMessage().toString());
            bkuTransfer.setKodeAction("UPD DPOST PAJAK - BOS");
            GenerateId id1 = new GenerateId();
            id1.setNamaTabel("TMBKUSBANKERROR");
            genMapper.insertGetId(id1);
            Integer idMaster = id1.getId();
            bkuTransfer.setId(idMaster);

            pajakMapper.insertBkuBankError(bkuTransfer);
        }

    }

    @Override
    public BkuTransfer getDataSekolah(final Map<String, Object> param) {
        return pajakMapper.getDataSekolah(param);
    }

    @Override
    public BukuKasUmum getSaldoKas(final Map<String, Object> param) {
        return pajakMapper.getSaldoKas(param);
    }

    @Override
    public String getGeneratedIdChar(String table) {
        GenerateId generateId = new GenerateId();
        generateId.setNamaTabel(table);
        generateId.setKodeApp("02"); // 02 UNTUK PAJAK SIAP-BOS
        genMapper.insertGetIdChar(generateId);
        return generateId.getIdFormat().toString();
    }

    @Override
    @Transactional(readOnly = false)
    public void insertDjpCreate(PajakTransfer pajak) {

        BkuTransfer bkuTransfer = new BkuTransfer();
        bkuTransfer.setIdsekolah(pajak.getIdsekolah());
        bkuTransfer.setBit11(pajak.getBulkIdRequest());
        bkuTransfer.setIdBku(pajak.getIdBku());
        bkuTransfer.setTahun(pajak.getTahun());
        bkuTransfer.setKodeSumbdana(pajak.getKodeApp());
        bkuTransfer.setIdEntry(pajak.getIdEntry());
        try {
            pajakMapper.insertDjpCreate(pajak);
        } catch (Exception e) {
            //log.error("BKU TRANSFER =============== "+e.getMessage());
            e.printStackTrace();
            bkuTransfer.setPesanError(e.getMessage().toString());
            bkuTransfer.setKodeAction("DJP CREATE KODE BILLING");
            GenerateId id1 = new GenerateId();
            id1.setNamaTabel("TMBKUSBANKERROR");
            genMapper.insertGetId(id1);
            Integer idMaster = id1.getId();
            bkuTransfer.setId(idMaster);

            pajakMapper.insertBkuBankError(bkuTransfer);
        }

    }

    @Override
    @Transactional(readOnly = false)
    public void updateDjpCreate(PajakTransfer pajak) {
        BkuTransfer bkuTransfer = new BkuTransfer();
        bkuTransfer.setIdsekolah(pajak.getIdsekolah());
        bkuTransfer.setBit11(pajak.getBulkIdRequest());
        bkuTransfer.setIdBku(pajak.getIdBku());
        bkuTransfer.setTahun(pajak.getTahun());
        bkuTransfer.setKodeSumbdana(pajak.getKodeApp());
        bkuTransfer.setIdEntry(pajak.getIdEntry());

        try {
            pajakMapper.updateDjpCreate(pajak);
        } catch (Exception e) {
            //log.error("BKU TRANSFER =============== "+e.getMessage());
            e.printStackTrace();
            bkuTransfer.setPesanError(e.getMessage().toString());
            bkuTransfer.setKodeAction("UPDATE DJP CREATE KODE BILLING");
            GenerateId id1 = new GenerateId();
            id1.setNamaTabel("TMBKUSBANKERROR");
            genMapper.insertGetId(id1);
            Integer idMaster = id1.getId();
            bkuTransfer.setId(idMaster);

            pajakMapper.insertBkuBankError(bkuTransfer);
        }

    }

    @Override
    @Transactional(readOnly = false)
    public void insertDjpBilling(PajakTransfer pajak) {
        BkuTransfer bkuTransfer = new BkuTransfer();
        bkuTransfer.setIdsekolah(pajak.getIdsekolah());
        bkuTransfer.setBit11(pajak.getBulkIdRequest());
        bkuTransfer.setIdBku(pajak.getIdBku());
        bkuTransfer.setTahun(pajak.getTahun());
        bkuTransfer.setKodeSumbdana(pajak.getKodeApp());
        bkuTransfer.setIdEntry(pajak.getIdEntry());

        try {
            pajakMapper.insertDjpBilling(pajak);
        } catch (Exception e) {
            //log.error("BKU TRANSFER =============== "+e.getMessage());
            e.printStackTrace();
            bkuTransfer.setPesanError(e.getMessage().toString());
            bkuTransfer.setKodeAction("INSERT DJP PAYMENT BILLING");
            GenerateId id1 = new GenerateId();
            id1.setNamaTabel("TMBKUSBANKERROR");
            genMapper.insertGetId(id1);
            Integer idMaster = id1.getId();
            bkuTransfer.setId(idMaster);

            pajakMapper.insertBkuBankError(bkuTransfer);
        }

    }

    @Override
    @Transactional(readOnly = false)
    public void updateDjpBilling(PajakTransfer pajak) {
        BkuTransfer bkuTransfer = new BkuTransfer();
        bkuTransfer.setIdsekolah(pajak.getIdsekolah());
        bkuTransfer.setBit11(pajak.getBulkIdRequest());
        bkuTransfer.setIdBku(pajak.getIdBku());
        bkuTransfer.setTahun(pajak.getTahun());
        bkuTransfer.setKodeSumbdana(pajak.getKodeApp());
        bkuTransfer.setIdEntry(pajak.getIdEntry());

        try {
            pajakMapper.updateDjpBilling(pajak);
        } catch (Exception e) {
            //log.error("BKU TRANSFER =============== "+e.getMessage());
            e.printStackTrace();
            bkuTransfer.setPesanError(e.getMessage().toString());
            bkuTransfer.setKodeAction("UPDATE DJP BILLING");
            GenerateId id1 = new GenerateId();
            id1.setNamaTabel("TMBKUSBANKERROR");
            genMapper.insertGetId(id1);
            Integer idMaster = id1.getId();
            bkuTransfer.setId(idMaster);

            pajakMapper.insertBkuBankError(bkuTransfer);
        }

    }

    @Override
    @Transactional(readOnly = false)
    public void insertDjpReinquiry(PajakTransfer pajak) {
        BkuTransfer bkuTransfer = new BkuTransfer();
        bkuTransfer.setIdsekolah(pajak.getIdsekolah());
        bkuTransfer.setBit11(pajak.getBulkIdRequest());
        bkuTransfer.setIdBku(pajak.getIdBku());
        bkuTransfer.setTahun(pajak.getTahun());
        bkuTransfer.setKodeSumbdana(pajak.getKodeApp());
        bkuTransfer.setIdEntry(pajak.getIdEntry());

        try {
            pajakMapper.insertDjpReinquiry(pajak);
        } catch (Exception e) {
            //log.error("BKU TRANSFER =============== "+e.getMessage());
            e.printStackTrace();
            bkuTransfer.setPesanError(e.getMessage().toString());
            bkuTransfer.setKodeAction("INSERT DJP REINQUIRY");
            GenerateId id1 = new GenerateId();
            id1.setNamaTabel("TMBKUSBANKERROR");
            genMapper.insertGetId(id1);
            Integer idMaster = id1.getId();
            bkuTransfer.setId(idMaster);

            pajakMapper.insertBkuBankError(bkuTransfer);
        }

    }

    @Override
    @Transactional(readOnly = false)
    public void updateDjpReinquiry(PajakTransfer pajak) {
        BkuTransfer bkuTransfer = new BkuTransfer();
        bkuTransfer.setIdsekolah(pajak.getIdsekolah());
        bkuTransfer.setBit11(pajak.getBulkIdRequest());
        bkuTransfer.setIdBku(pajak.getIdBku());
        bkuTransfer.setTahun(pajak.getTahun());
        bkuTransfer.setKodeSumbdana(pajak.getKodeApp());
        bkuTransfer.setIdEntry(pajak.getIdEntry());

        try {
            pajakMapper.updateDjpReinquiry(pajak);
        } catch (Exception e) {
            //log.error("BKU TRANSFER =============== "+e.getMessage());
            e.printStackTrace();
            bkuTransfer.setPesanError(e.getMessage().toString());
            bkuTransfer.setKodeAction("UPDATE DJP REINQUIRY");
            GenerateId id1 = new GenerateId();
            id1.setNamaTabel("TMBKUSBANKERROR");
            genMapper.insertGetId(id1);
            Integer idMaster = id1.getId();
            bkuTransfer.setId(idMaster);

            pajakMapper.insertBkuBankError(bkuTransfer);
        }

    }

    @Override
    @Transactional(readOnly = false)
    public void updateRinciPotPajak(PajakTransfer pajak) {
        pajakMapper.updateRinciPotPajak(pajak);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertRinciPotPajak(PajakTransfer pajak) {

        BkuTransfer bkuTransfer = new BkuTransfer();
        bkuTransfer.setIdsekolah(pajak.getIdsekolah());
        bkuTransfer.setBit11("-");
        bkuTransfer.setIdBku(pajak.getIdBku());
        bkuTransfer.setTahun(pajak.getTahun());
        bkuTransfer.setKodeSumbdana("1");
        bkuTransfer.setIdEntry(pajak.getIdEntry());
        try {
            GenerateId id = new GenerateId();
            id.setNamaTabel("TMBKUSBOSPOTPAJAK");
            genMapper.insertGetId(id);
            Integer idPot = id.getId();
            pajak.setId(idPot);
            pajakMapper.insertRinciPotPajak(pajak);
        } catch (Exception e) {
            //log.error("BKU TRANSFER =============== "+e.getMessage());
            e.printStackTrace();
            bkuTransfer.setPesanError(e.getMessage().toString());
            bkuTransfer.setKodeAction("INS RINCI POT PAJAK BOS");
            GenerateId id1 = new GenerateId();
            id1.setNamaTabel("TMBKUSBANKERROR");
            genMapper.insertGetId(id1);
            Integer idMaster = id1.getId();
            bkuTransfer.setId(idMaster);

            pajakMapper.insertBkuBankError(bkuTransfer);
        }

    }

    @Override
    @Transactional(readOnly = false)
    public void updateInquiryPot(PajakTransfer param) {
        pajakMapper.updateInquiryMasterPot(param);
        pajakMapper.updateInquiryPot(param);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateCetakCount(final Map<String, Object> param) {
        pajakMapper.updateCetakCount(param);
    }
}
