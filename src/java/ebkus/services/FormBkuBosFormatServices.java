package ebkus.services;

import ebkus.model.BukuKasUmum;
import ebkus.model.FormBkuBosFormat;
import ebkus.model.Kegiatan;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Anita
 */
public interface FormBkuBosFormatServices {

    List<Map> getnilaiparam(Map param);

    List<FormBkuBosFormat> getSaldoAwalTunai(Map<String, Object> param);

    List<FormBkuBosFormat> getSaldoAwalBank(Map<String, Object> param);

    List<FormBkuBosFormat> getSaldoAwalPanjar(Map<String, Object> param);

    List<FormBkuBosFormat> getAkunBelanja(Map<String, Object> param);

    List<Kegiatan> getKegiatan(Map<String, Object> param);

    List<BukuKasUmum> getWilayah(Map<String, Object> param);

    List<BukuKasUmum> getTriwulan(Map<String, Object> param);

    List<FormBkuBosFormat> getSaldoAwalPajak(Map<String, Object> param);

    Integer cekRekap(Map param);

    List<FormBkuBosFormat> getBulanBku(Map<String, Object> param);

    void insertTIBKUBulan(FormBkuBosFormat bku);
}
