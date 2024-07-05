/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebkus.entity;

import ebkus.model.CekKonversi;
import ebkus.model.Konversi;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Racka
 */
public interface KonversiRkasMapper {

    List<Konversi> getBudgetKegiatan(Map param);

    List<Konversi> getBudgetKegiatanRinci(Map param);

    List<Konversi> getBudgetKegiatanAkb(Map param);

    List<Konversi> getBudgetDataSekolah(Map param);

    Integer cekDuplicate(Map param);

    Integer cekGiat(Map param);

    CekKonversi cekGiatRinci(Map param);

    void prosesKegiatan(Konversi param);

    void prosesKegiatanRinci(Konversi param);

    void prosesKegiatanAkb(Konversi param);

    void prosesDataSekolah(Konversi param);

    void prosesKegiatanPerubahan(Konversi param);

    void prosesKegiatanRinciPerubahan(Konversi param);

    void prosesKegiatanAkbPerubahan(Konversi param);

    void prosesDataSekolahPerubahan(Konversi param);

}
