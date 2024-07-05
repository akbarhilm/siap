package ebkus.entity;

import ebkus.model.BukuKasUmum;
import ebkus.model.FormBkuFormat;
import ebkus.model.Kegiatan;
import ebkus.model.Setor;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Anita
 */
public interface FormBkuFormatMapper {

    List<Map> getnilaiparam(Map param);

    List<FormBkuFormat> getSaldoAwalTunai(Map<String, Object> param);

    List<FormBkuFormat> getSaldoAwalBank(Map<String, Object> param);

    List<FormBkuFormat> getSaldoAwalPanjar(Map<String, Object> param);

    List<FormBkuFormat> getAkunBelanja(Map<String, Object> param);

    List<Kegiatan> getKegiatan(Map<String, Object> param);

    List<BukuKasUmum> getWilayah(Map<String, Object> param);

    List<BukuKasUmum> getTriwulan(Map<String, Object> param);

    List<FormBkuFormat> getSaldoAwalPajak(Map<String, Object> param);

    Integer cekRekap(Map param);

    public Integer getBanyakListCetakSetor(Map<String, Object> param);

    public List<Setor> getListCetakSetor(Map<String, Object> param);

    List<FormBkuFormat> getIdSkpd(Map<String, Object> param);

    List<FormBkuFormat> getKecamatan(Map<String, Object> param);

    List<FormBkuFormat> getSisdik(Map<String, Object> param);

    void updateSisdik(Map param);
}
