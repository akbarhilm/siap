package ebkus.services;

import ebkus.model.MCB;
import ebkus.model.Rkas;
import ebkus.model.Sekolah;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Zainab
 */
public interface SekolahServices {

    Sekolah getSekolah(Map param);

    void updateSekolah1(Sekolah param);

    void updateSekolahRKAS(Sekolah param);

    void updateSekolah2(Rkas param);
    
    void insertMcb(MCB mcb);
    
    void deleteMcb(Integer id);
    
     void updateMcb(MCB mcb);

    List<MCB> getListMcb();

    Integer getBanyakListMcb();
    
    MCB getMcbById(Integer id);
//    Integer getBanyakListSpj(Map<String, Object> param);
//
//    List<BukuKasUmum> getListPajak(Map<String, Object> param);
//
//    Integer getBanyakListPajak(Map<String, Object> param);
//
//    BukuKasUmum getSisaKas(Map<String, Object> param);
//
//    void insertBkuSpj(List<BukuKasUmum> param);
//
//    void insertBku(BukuKasUmum param);
//
//    void insertBkuPajakPg(List<BukuKasUmum> param);
//
//    BukuKasUmum getBkuEdit(Map<String, Object> param);
//
//    List<BukuKasUmum> getListJasaGiroPn(Map<String, Object> param);
//
//    Integer getBanyakListJasaGiroPn(Map<String, Object> param);
//
//    List<BukuKasUmum> getKegiatanPajakPn(Map<String, Object> param);
//
//    Integer getBanyakKegiatanPajakPn(Map<String, Object> param);
//
//    List<BukuKasUmum> getListPajakPn(Map<String, Object> param);
//
//    Integer getBanyakListPajakPn(Map<String, Object> param);
//
//    List<BukuKasUmum> getListIndex(Map<String, Object> param);
//
//    Integer getBanyakListIndex(Map<String, Object> param);
//
//    BukuKasUmum getNilaiIndex(Map<String, Object> param);
//
//    Kegiatan getDataKegiatan(Map<String, Object> param);
//
//    List<BukuKasUmum> getDataPajak(Map<String, Object> param);
//
//
}
