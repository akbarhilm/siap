/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.services;

import ebkus.entity.KonversiRkasMapper;
import ebkus.model.CekKonversi;
import ebkus.model.Konversi;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service("konversiRkasServices")
public class KonversiRkasImpl implements KonversiRkasServices {

    private static final Logger log = LoggerFactory.getLogger(KonversiRkasImpl.class);
    @Autowired
    private KonversiRkasMapper konversiRkas;

    @Override
    public List<Konversi> getBudgetKegiatan(Map param) {
        return konversiRkas.getBudgetKegiatan(param);
    }

    @Override
    public List<Konversi> getBudgetKegiatanRinci(Map param) {
        return konversiRkas.getBudgetKegiatanRinci(param);
    }

    @Override
    public List<Konversi> getBudgetKegiatanAkb(Map param) {
        return konversiRkas.getBudgetKegiatanAkb(param);
    }

    @Override
    public List<Konversi> getBudgetDataSekolah(Map param) {
        return konversiRkas.getBudgetDataSekolah(param);
    }

    @Override
    public Integer cekDuplicate(Map param) {
        return konversiRkas.cekDuplicate(param);
    }

    @Override
    public Integer cekGiat(Map param) {
        return konversiRkas.cekGiat(param);
    }

    @Override
    public CekKonversi cekGiatRinci(Map param) {
        return konversiRkas.cekGiatRinci(param);
    }

    @Override
    public void prosesKegiatan(Konversi param) {
        konversiRkas.prosesKegiatan(param);
    }

    @Override
    public void prosesKegiatanRinci(Konversi param) {
        konversiRkas.prosesKegiatanRinci(param);
    }

    @Override
    public void prosesKegiatanAkb(Konversi param) {
        konversiRkas.prosesKegiatanAkb(param);
    }

    @Override
    public void prosesDataSekolah(Konversi param) {
        konversiRkas.prosesDataSekolah(param);
    }

    @Override
    public void prosesKegiatanPerubahan(Konversi param) {
        konversiRkas.prosesKegiatanPerubahan(param);
    }

    @Override
    public void prosesKegiatanRinciPerubahan(Konversi param) {
        konversiRkas.prosesKegiatanRinciPerubahan(param);
    }

    @Override
    public void prosesKegiatanAkbPerubahan(Konversi param) {
        konversiRkas.prosesKegiatanAkbPerubahan(param);
    }

    @Override
    public void prosesDataSekolahPerubahan(Konversi param) {
        konversiRkas.prosesDataSekolahPerubahan(param);
    }

}
