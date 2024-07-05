package ebkus.entity;

import ebkus.model.BukuKasUmum;
import ebkus.model.FormBkuBos;
import ebkus.model.Kegiatan;
import ebkus.model.Setor;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Anita
 */
public interface FormBkuBosMapper {

    List<Map> getnilaiparam(Map param);

    List<FormBkuBos> getSaldoAwalTunai(Map<String, Object> param);

    List<FormBkuBos> getSaldoAwalBank(Map<String, Object> param);

    List<FormBkuBos> getSaldoAwalPanjar(Map<String, Object> param);

    List<FormBkuBos> getAkunBelanja(Map<String, Object> param);

    List<Kegiatan> getKegiatan(Map<String, Object> param);

    List<BukuKasUmum> getWilayah(Map<String, Object> param);

    List<BukuKasUmum> getTriwulan(Map<String, Object> param);
    
    List<BukuKasUmum> getBulan(Map<String, Object> param);
    
    List<BukuKasUmum> getSemester(Map<String, Object> param);

    List<FormBkuBos> getSaldoAwalPajak(Map<String, Object> param); 
        
    Integer cekRekap(Map param);

    public Integer getBanyakListCetakSetor(Map<String, Object> param);

    public List<Setor> getListCetakSetor(Map<String, Object> param);
}
