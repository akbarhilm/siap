package ebkus.entity;

import ebkus.model.BukuKasUmum;
import ebkus.model.FormBku;
import ebkus.model.Kegiatan;
import ebkus.model.Setor;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Anita
 */
public interface FormBkuMapper {

    List<Map> getnilaiparam(Map param);

    List<FormBku> getSaldoAwalTunai(Map<String, Object> param);

    List<FormBku> getSaldoAwalBank(Map<String, Object> param);

    List<FormBku> getSaldoAwalPanjar(Map<String, Object> param);

    List<FormBku> getAkunBelanja(Map<String, Object> param);

    List<Kegiatan> getKegiatan(Map<String, Object> param);

    List<BukuKasUmum> getWilayah(Map<String, Object> param);

    List<BukuKasUmum> getTriwulan(Map<String, Object> param);

    List<FormBku> getSaldoAwalPajak(Map<String, Object> param);
    
    Integer cekRekap(Map param);
    
    public Integer getBanyakListCetakSetor(Map<String, Object> param);

    public List<Setor> getListCetakSetor(Map<String, Object> param);
}
